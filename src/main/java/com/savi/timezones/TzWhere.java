//?? TODO:  REVIEW:  What should package name be?
// - Should it be com.savi.SOMETHING.timezones?  (E.g., "os" for open source, or
//   something re "utilities")
// - Should part reflect ~project name "Java-TZWhere"?
// - Should there be part under "timezones" that is specific to these
//   classes (roughly like ...timezones.geolookup or ...timezones.javatzwhere)?
package com.savi.timezones;


//??: TODO: Probably RENAME class (to follow normal naming practice of being based
// on (non-proper) noun; related to mapping geo to TZ, or looking up, getting TZ.
// - TimeZoneMapper?
// - GeolocToTimeZoneMapper?
// - LatLongToTimeZoneMapper?
// - PointToTimeZoneMapper?
// - <prefix>TimeZoneIndex?
// - other?
// )
/**
 * ??? TODO: Doc. a bit:
 * - shapefiles from http://....
 * - classpath resource pathname xxx ...
 * - [if impl.] property to override resource pathame
 */
public class TzWhere {

    private static IndexImpl geolocToTzIndex = new IndexImpl();

    /**
     * Gets ID of time zone at location specified by given latitude and longitude.
     *
     * @param  latitude  latitude, in degrees north
     * @param  longitude  longitude, in degrees east
     * @return  the ID of the specified point's containing time zone, if any
     *   found; otherwise null
     */
    public static String getLatLogTimeZoneId(double latitude, double longitude) {
        return geolocToTzIndex.getLatLongTimeZoneId(latitude, longitude);
    }

}
