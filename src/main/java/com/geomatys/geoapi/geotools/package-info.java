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

/**
 * Implementation of GeoAPI 3.0 interfaces as wrappers around the GeoTools library.
 * Projects using GeoAPI interfaces for their metadata and referencing services have
 * more flexibility for changing implementation according their needs. It also makes
 * possible to use GeoTools with projects designed for GeoAPI. For example, it makes
 * possible to run the <a href="https://github.com/IOGP-GIGS/GIGSGeoAPI">GIGS tests</a>
 * against GeoTools.
 *
 * @author Martin Desruisseaux (Geomatys)
 *
 * @see <a href="https://www.geoapi.org/">GeoAPI web site</a>
 */
package com.geomatys.geoapi.geotools;
