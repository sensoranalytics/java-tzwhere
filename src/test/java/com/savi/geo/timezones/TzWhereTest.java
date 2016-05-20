package com.savi.geo.timezones;

import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class TzWhereTest {

    @Test
    public void testSomePointGetsItsTimeZoneId() {
        final double latitude  = 38.8044;
        final double longitude = -77.0920;
        final String actual = TzWhere.getLatLongTimeZoneId(latitude, longitude);
        final String expected = "America/New_York";

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testPointInCollidedTileGetsRightTimeZone() {
        // A point in an index tile(?) that maps to two timezone portions and
        // needs resolution via the actual geometry (as of 2016-05):
        // (37.952126 N / -86.764727) is in America/Indiana/Tell_City but
        // America/Chicago also gets indexed into same internal tile.
        final double latitude  = 37.952126;
        final double longitude = -86.764727;
        final String actual = TzWhere.getLatLongTimeZoneId(latitude, longitude);
        final String expected = "America/Indiana/Tell_City";
    }

    @Test
    public void testPointAtBoundaryVertex() {
        // A point that is vertex on boundary polygon of Europe/Vatican:
        // (Attempted exact values--but unconfirmed.)
        final double latitude  = 41.9063606262207;
        final double longitude = 12.45091629028328;
        final String actual = TzWhere.getLatLongTimeZoneId(latitude, longitude);
        final String expected = "Europe/Vatican";

        assertThat(actual, equalTo(expected));
    }
    @Test
    public void testPointNearBoundaryVertex1() {
        // A point that is just inside vertex on boundary polygon of Europe/Vatican:
        final double latitude  = 41.9063606262207 - 0.002; // on northern part
        final double longitude = 12.45091629028328;
        final String actual = TzWhere.getLatLongTimeZoneId(latitude, longitude);
        final String expected = "Europe/Vatican";

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testPointNearBoundaryVertex2() {
        // A point that is just outside vertex on boundary polygon of Europe/Vatican:
        final double latitude  = 41.9063606262207 + 0.002; // on northern part
        final double longitude = 12.45091629028328;
        final String actual = TzWhere.getLatLongTimeZoneId(latitude, longitude);
        final String expected = "Europe/Rome";

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testMidoceanTimeZonesNotSupportedYet() {
        final double latitude  = 0;
        final double longitude = 0;
        final String actual = TzWhere.getLatLongTimeZoneId(latitude, longitude);
        final String expected = null;

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testCoastalTimeZonesNotSupportedYet() {
        final double latitude  = 38.4514;
        final double longitude = -75.049;
        final String actual = TzWhere.getLatLongTimeZoneId(latitude, longitude);
        final String expected = null;

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testDelawareOffShorePerGoogle1() {
        final double latitude  = 38.4514;
        final double longitude = -75.048;
        final String actual = TzWhere.getLatLongTimeZoneId(latitude, longitude);
        final String expected = null;

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testDelawareOffShorePerGoogleMaps2() {
        final double latitude  = 38.4514;
        final double longitude = -75.049;
        final String actual = TzWhere.getLatLongTimeZoneId(latitude, longitude);
        final String expected = null;

        assertThat(actual, equalTo(expected));
    }

    @Test
    @Ignore("until accuracy is checked")
    public void testDelawareOnShorePerGoogleMaps1() {
        final double latitude  = 38.4514;
        final double longitude = -75.050;
        final String actual = TzWhere.getLatLongTimeZoneId(latitude, longitude);
        final String expected = "America/New_York";

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testDelawareOnShorePerGoogleMaps2() {
        final double latitude  = 38.4514;
        final double longitude = -75.060;
        final String actual = TzWhere.getLatLongTimeZoneId(latitude, longitude);
        final String expected = "America/New_York";

        assertThat(actual, equalTo(expected));
    }

}
