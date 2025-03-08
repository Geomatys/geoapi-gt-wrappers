# GeoAPI wrappers for GeoTools

Implementation of [GeoAPI 3.0](https://www.geoapi.org/) interfaces as wrappers around the GeoTools library.
GeoAPI is a set of implementation-neutral interfaces standardized by the Open Geospatial Consortium (OGC).
Projects using GeoAPI interfaces for their metadata and referencing services have more flexibility
for changing implementation according their needs.
It also makes possible to use GeoTools with projects designed for GeoAPI.
For example, it makes possible to run the [GIGS tests](https://github.com/IOGP-GIGS/GIGSGeoAPI) against GeoTools.

All wrappers in this project are bidirectional:
GeoTools objects can be viewed as GeoAPI objects, or conversely.
The latter should, in theory, allow the use of alternative referencing library
such as [Apache SIS](https://sis.apache.org/) or [PROJ](https://github.com/OSGeo/PROJ-JNI) inside GeoTools.
However, as of Mars 2025, it has not been tested.


## Usage
This project provides only two public classes: `Wrappers` and `Services`.
For viewing a GeoTools object as a GeoAPI object, simply invoke the following:

```java
var geotools = CRS.decode("EPSG:4326");
var geoapi = Wrappers.geoapi(geotools);
```

The `geoapi` method is overloaded with all types of objects supported by this project.
Conversely, a GeoAPI object can be unwrapped or viewed as a GeoTools object like below:

```java
var geotools = Wrappers.geotools(geoapi);
```

### Factories
The following methods of GeoTools are reproduced in `Services` with the same signature,
but returning the GeoTools factory wrapped in a GeoAPI factory:

* `CRS.getAuthorityFactory(longitudeFirst)`
* `CRS.getCoordinateOperationFactory(lenient)`


## Build from source code
Simply run `mvn install` in a local clone of this repository.


## Possible donation to OSGeo
A better home for this project would be the [OSGeo foundation](https://www.osgeo.org/), which is where GeoTools is hosted.
Deployment on the OSGeo Maven repository would be convenient since it would be the same repository as for GeoTools.
Having this project hosted by OSGeo has been proposed in the end of 2024, but OSGeo declined.
If another volunteer has some evidence of a user base and is willing to ask again to OSGeo,
we would be happy to donate this project, including a transfer of copyright to OSGeo if desired.
