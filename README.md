# GeoAPI wrappers for GeoTools

Implementation of [GeoAPI 3.0](https://www.geoapi.org/) interfaces as wrappers around the GeoTools library.
Projects using GeoAPI interfaces for their metadata and referencing services have more flexibility
for changing implementation according their needs.
It also makes possible to use GeoTools with projects designed for GeoAPI.
For example, it makes possible to run the [GIGS tests](https://github.com/IOGP-GIGS/GIGSGeoAPI) against GeoTools.

## Build
Simply run `mvn install` in a local clone of this repository.
