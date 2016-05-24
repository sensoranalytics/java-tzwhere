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
