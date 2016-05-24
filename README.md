# java-tzwhere
A better Java library for determining Olson TZ Database time zones from latitude and longitude.

## Rationale
Existing Java implementations of timezone lookup (that we could find) were either inefficient
(i.e., not indexed) or not packaged for reproducable builds, updates, and consumption. The
goal of this project is to provide an efficient Java timezone lookup using standard
Java tools and techniques.

## Technique
Currently, the tool loads and indexes the [tz_world](http://efele.net/maps/tz/world/) shapefiles
on initialization. It uses [Geotools](http://docs.geotools.org/stable/userguide/library/data/shape.html) for
shapefile handling and [JTS](http://www.vividsolutions.com/jts/JTSHome.htm) for spatial indexing with their
[Sort-Tile-Recursive R-Tree](https://en.wikipedia.org/wiki/R-tree)
[implementation](http://www.vividsolutions.com/jts/javadoc/com/vividsolutions/jts/index/strtree/STRtree.html).

## Caveats
### Timezones at Sea
The tz_world data is limited to only timezones on land. We'd like to add territorial waters, but we need shapes first.

## TODOs
* Improved documentation and examples
* Added test coverage and configuration for automated CI
* Publication to Maven Central
