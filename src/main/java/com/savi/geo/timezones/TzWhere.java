//?? TODO:  REVIEW:  How much flexibility should we support right now?
// - Mainly, do/will we need to provide the option to, instead of using just the
//   default shapefile (classpath resource files in the Jar file), use another
//   shapefile(s) at the same time (in the same run and without classloader
//   trickiness)?  (Relates to how to structure code now (re interface for
//   internal IndexImpl, re static vs. non-static methods).)
//
// - Should we currently provide the option to override the default shapefile
//   with one static replacement (probably by defining system property used to
//   override default classpath resource pathname to shapefile files)?

//?? TODO:  REVIEW:  Should proper-noun--based class name "TzWhere" be changed
// to more-typical class name (common-noun--based noun phrase; trying to describe
// class, perhaps "LocToTimeZoneMapper"?
//
// Some candidates:
// - GeolocToTimeZoneMapper?
// - LocToTimeZoneMapper?
// - PointToTimeZoneMapper?
// - TimeZoneMapper? (con: more risk of sounding like "mapper" in sense of showing TZ region on map)
// - TimeZoneIndex?
// - <prefix>TimeZoneIndex?
// - something else?

//?? TODO:  REVIEW:  (After resolving class name:)
// If class is changed from TzWhere:  Should package name include "tzwhere"?
// If so, should it be com.savi.geo.timezones.tzwhere or com.savi.geo.tzwhere?

package com.savi.geo.timezones;


/**
 * Utility to map geographic locations to containing time zones.
 *
 * <p>
 *   Uses time zone geographic area data (copied) from
 *   http://efele.net/maps/tz/world/.
 * </p>
 */
public class TzWhere {

    /** (To prevent instantiation.) */
    private TzWhere() {
    }

    private static IndexImpl geolocToTzIndex = new IndexImpl();


    /**
     * Gets ID of time zone at location specified by given latitude and longitude.
     *
     * @param  latitude  latitude, in degrees north
     * @param  longitude  longitude, in degrees east
     * @return  the ID of the specified point's containing time zone, if any
     *   found; otherwise null
     */
    public static String getLatLongTimeZoneId(double latitude, double longitude) {
        return geolocToTzIndex.getLatLongTimeZoneId(latitude, longitude);
    }

}
