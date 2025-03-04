/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership. You may not use this
 * file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.geomatys.geoapi.geotools;

import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;


/**
 * Base class of all wrappers between GeoAPI and GeoTools.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
abstract class Wrapper {
    /**
     * Creates a new wrapper.
     */
    Wrapper() {
    }

    /**
     * Returns the {@code org.geotools.api} instances used an the implementation.
     *
     * @return the backing {@code org.geotools.api} instance
     */
    abstract Object implementation();

    /**
     * Returns the backing implementation if the given object is wrapper, or the wrapper itself otherwise.
     *
     * @param obj the object to unwrap.
     * @return the unwrapped object, or {@code obj} if the given object is not a wrapper.
     */
    private static Object unwrap(final Object obj) {
        return (obj instanceof Wrapper) ? ((Wrapper) obj).implementation() : obj;
    }

    /**
     * Returns a view of the given iterator with all elements replaced on-the-fly.
     * If the given iterator is {@code null}, then this method returns {@code null}.
     *
     * @param <S>     the type of elements in the source iterator.
     * @param <T>     the type of elements in the target iterator (the view).
     * @param source  the source iterator for which to provide a view.
     * @param wrapper the {@code wrap(…)} function to invoke for wrapping each element of the given iterator.
     * @return an iterator of wrappers around the elements of the source collection.
     */
    private static <S,T> Iterator<T> wrap(final Iterator<S> source, final Function<S,T> wrapper) {
        if (source == null) {   // Should never happen, but we are paranoiac.
            return null;
        }
        return new Iterator<>() {
            @Override public boolean hasNext() {return source.hasNext();}
            @Override public T       next()    {return wrapper.apply(source.next());}
            @Override public void    remove()  {source.remove();}
        };
    }

    /**
     * Returns a view of the given iterator with all elements replaced on-the-fly.
     * If the given iterator is {@code null}, then this method returns {@code null}.
     *
     * @param <S>     the type of elements in the source iterator.
     * @param <T>     the type of elements in the target iterator (the view).
     * @param source  the source iterator for which to provide a view.
     * @param wrapper the {@code wrap(…)} function to invoke for wrapping each element of the given iterator.
     * @return an iterator of wrappers around the elements of the source collection.
     */
    private static <S,T> ListIterator<T> wrap(final ListIterator<S> source, final Function<S,T> wrapper) {
        if (source == null) {   // Should never happen, but we are paranoiac.
            return null;
        }
        return new ListIterator<>() {
            @Override public boolean hasNext()       {return source.hasNext();}
            @Override public boolean hasPrevious()   {return source.hasPrevious();}
            @Override public T       next()          {return wrapper.apply(source.next());}
            @Override public T       previous()      {return wrapper.apply(source.previous());}
            @Override public int     nextIndex()     {return source.nextIndex();}
            @Override public int     previousIndex() {return source.previousIndex();}
            @Override public void    remove()        {source.remove();}
            @Override public void    set(T e)        {throw new UnsupportedOperationException();}
            @Override public void    add(T e)        {throw new UnsupportedOperationException();}
        };
    }

    /**
     * Returns a view of the given iterable with all elements replaced on-the-fly.
     * If the given iterable is {@code null}, then this method returns {@code null}.
     *
     * @param <S>     the type of elements in the source iterable.
     * @param <T>     the type of elements in the target iterable (the view).
     * @param source  the source iterable for which to provide a view.
     * @param wrapper the {@code wrap(…)} function to invoke for wrapping each element of the given iterable.
     * @return an iterable of wrappers around the elements of the source iterable.
     */
    static <S,T> Iterable<T> wrap(final Iterable<S> source, final Function<S,T> wrapper) {
        switch (source) {
            case null: return null;
            case Collection<S> c: return wrap(c, wrapper);
            default: return () -> wrap(source.iterator(), wrapper);
        }
    }

    /**
     * Returns a view of the given collection with all elements replaced on-the-fly.
     * If the given collection is {@code null}, then this method returns {@code null}.
     *
     * @param <S>     the type of elements in the source collection.
     * @param <T>     the type of elements in the target collection (the view).
     * @param source  the source collection for which to provide a view.
     * @param wrapper the {@code wrap(…)} function to invoke for wrapping each element of the given collection.
     * @return a collection of wrappers around the elements of the source collection.
     */
    static <S,T> Collection<T> wrap(final Collection<S> source, final Function<S,T> wrapper) {
        switch (source) {
            case null:      return null;
            case Set<S>  c: return wrap(c, wrapper);
            case List<S> c: return wrap(c, wrapper);
            default: return new AbstractCollection<T>() {
                @Override public int     size()             {return source.size();}
                @Override public boolean isEmpty()          {return source.isEmpty();}
                @Override public String  toString()         {return source.toString();}
                @Override public boolean remove(Object o)   {return source.remove(unwrap(o));}
                @Override public boolean contains(Object o) {return source.contains(unwrap(o));}
                @Override public Iterator<T> iterator()     {return wrap(source.iterator(), wrapper);}
                @Override public   Stream<T> stream()       {return source.stream().map(wrapper);}
            };
        }
    }

    /**
     * Returns a view of the given set with all elements replaced on-the-fly.
     * If the given set is {@code null}, then this method returns {@code null}.
     *
     * @param <S>     the type of elements in the source set.
     * @param <T>     the type of elements in the target set (the view).
     * @param source  the source set for which to provide a view.
     * @param wrapper the {@code wrap(…)} function to invoke for wrapping each element of the given set.
     * @return a set of wrappers around the elements of the source collection.
     */
    static <S,T> Set<T> wrap(final Set<S> source, final Function<S,T> wrapper) {
        if (source == null) {
            return null;
        }
        return new AbstractSet<>() {
            @Override public int     size()             {return source.size();}
            @Override public boolean isEmpty()          {return source.isEmpty();}
            @Override public String  toString()         {return source.toString();}
            @Override public boolean remove(Object o)   {return source.remove(unwrap(o));}
            @Override public boolean contains(Object o) {return source.contains(unwrap(o));}
            @Override public Iterator<T> iterator()     {return wrap(source.iterator(), wrapper);}
            @Override public   Stream<T> stream()       {return source.stream().map(wrapper);}
        };
    }

    /**
     * Returns a view of the given list with all elements replaced on-the-fly.
     * If the given list is {@code null}, then this method returns {@code null}.
     *
     * @param <S>     the type of elements in the source list.
     * @param <T>     the type of elements in the target list (the view).
     * @param source  the source list for which to provide a view.
     * @param wrapper the {@code wrap(…)} function to invoke for wrapping each element of the given list.
     * @return a list of wrappers around the elements of the source collection.
     */
    static <S,T> List<T> wrap(final List<S> source, final Function<S,T> wrapper) {
        if (source == null) {
            return null;
        }
        return new AbstractList<>() {
            @Override public int     size()                {return source.size();}
            @Override public boolean isEmpty()             {return source.isEmpty();}
            @Override public String  toString()            {return source.toString();}
            @Override public boolean remove(Object o)      {return source.remove(unwrap(o));}
            @Override public boolean contains(Object o)    {return source.contains(unwrap(o));}
            @Override public int     indexOf(Object o)     {return source.indexOf(unwrap(o));}
            @Override public int     lastIndexOf(Object o) {return source.lastIndexOf(unwrap(o));}
            @Override public T       get(int index)        {return wrapper.apply(source.get(index));}
            @Override public Stream<T>       stream()      {return source.stream().map(wrapper);}
            @Override public Iterator<T>     iterator()    {return wrap(source.iterator(), wrapper);}
            @Override public ListIterator<T> listIterator(final int index) {
                return wrap(source.listIterator(index), wrapper);
            }
            @Override public List<T> subList(int fromIndex, int toIndex) {
                return wrap(source.subList(fromIndex, toIndex), wrapper);
            }
        };
    }

    /**
     * Returns a view of the given map with all elements replaced on-the-fly.
     * If the given map is {@code null}, then this method returns {@code null}.
     *
     * @param <SK>    the type of keys in the source map.
     * @param <SV>    the type of values in the source map.
     * @param <TK>    the type of keys in the target map (the view).
     * @param <TV>    the type of values in the target map (the view).
     * @param source  the source map for which to provide a view.
     * @param keyWrapper the {@code wrap(…)} function to invoke for wrapping each key of the given map.
     * @param valueWrapper the {@code wrap(…)} function to invoke for wrapping each value of the given map.
     * @return a map of wrappers around the elements of the source map.
     */
    static <SK,SV,TK,TV> Map<TK,TV> wrap(final Map<SK,SV> source, final Function<SK,TK> keyWrapper, final Function<SV,TV> valueWrapper) {
        if (source == null) {
            return null;
        }
        return new AbstractMap<>() {
            @Override public int            size()             {return source.size();}
            @Override public boolean        isEmpty()          {return source.isEmpty();}
            @Override public String         toString()         {return source.toString();}
            @Override public TV             get(Object key)    {return valueWrapper.apply(source.get(unwrap(key)));}
            @Override public TV             remove(Object key) {return valueWrapper.apply(source.remove(unwrap(key)));}
            @Override public Set<TK>        keySet()           {return wrap(source.keySet(), keyWrapper);}
            @Override public Collection<TV> values()           {return wrap(source.values(), valueWrapper);}
            @Override public Set<Entry<TK,TV>> entrySet() {
                return wrap(source.entrySet(), (e) -> new SimpleImmutableEntry<>(
                        keyWrapper  .apply(e.getKey()),
                        valueWrapper.apply(e.getValue())));
            }
        };
    }

    /**
     * {@return whether this wrapper is equal to the given object}.
     * Two wrappers are considered equal if they are of the same class and the wrapped implementations are equal.
     *
     * @param obj the object to compare, or {@code null}
     */
    @Override
    public final boolean equals(final Object obj) {
        return (obj != null) && obj.getClass() == getClass() && implementation().equals(((Wrapper) obj).implementation());
    }

    /**
     * {@return a hash code value for this wrapper}.
     */
    @Override
    public final int hashCode() {
        return implementation().hashCode() ^ getClass().hashCode();
    }

    /**
     * {@return the string representation of the implementation}.
     */
    @Override
    public final String toString() {
        return implementation().toString();
    }
}
