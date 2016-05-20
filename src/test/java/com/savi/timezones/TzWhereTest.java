package com.savi.timezones;

import com.vividsolutions.jts.geom.Coordinate;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


import java.io.IOException;


public class TzWhereTest {


    @Test
    public void testSomePointGetsItsTimeZoneId() {
        final double latitude  = 38.8044;
        final double longitude = -77.0920;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
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
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = "America/Indiana/Tell_City";
    }


    @Test
    public void testXxxx10() {
        final double latitude  = 40;
        final double longitude = 10;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = null; //??? CHECK

        assertThat(actual, equalTo(expected));
    }
    @Test
    public void testXxxx9() {
        final double latitude  = 41;
        final double longitude = 12;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = null; //??? CHECK

        assertThat(actual, equalTo(expected));
    }
    @Test
    public void testXxxx8() {
        final double latitude  = 41.9;
        final double longitude = 12.4;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = "Europe/Rome"; //??? CHECK

        assertThat(actual, equalTo(expected));
    }
    @Test
    public void testXxxx7() {
        final double latitude  = 41.90;
        final double longitude = 12.45;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = "Europe/Rome"; //??? CHECK

        assertThat(actual, equalTo(expected));
    }
    @Test
    public void testXxxx6() {
        final double latitude  = 41.906;
        final double longitude = 12.450;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = "Europe/Vatican"; //??? CHECK

        assertThat(actual, equalTo(expected));
    }
    @Test
    public void testXxxx5() {
        final double latitude  = 41.90636;
        final double longitude = 12.45091;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = "Europe/Vatican"; //??? CHECK

        assertThat(actual, equalTo(expected));
    }
    @Test
    public void testXxxx4() {
        final double latitude  = 41.9063606;
        final double longitude = 12.4509162;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = "Europe/Vatican"; //??? CHECK

        assertThat(actual, equalTo(expected));
    }
    @Test
    public void testXxxx3() {
        final double latitude  = 41.906360626;
        final double longitude = 12.450916290;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = "Europe/Vatican"; //??? CHECK

        assertThat(actual, equalTo(expected));
    }
    @Test
    public void testXxxx2() {
        final double latitude  = 41.90636062622;
        final double longitude = 12.45091629028;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = "Europe/Vatican"; //??? CHECK

        assertThat(actual, equalTo(expected));
    }
    @Test
    public void testXxxx1() {
        final double latitude  = 41.9063606262207;
        final double longitude = 12.4509162902832;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = "Europe/Vatican";

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testPointAtBoundaryVertex() {
        // A point that is vertex on boundary polygon of Europe/Vatican:
        final double latitude  = 41.9063606262207;
        final double longitude = 12.4509162902832;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = "Europe/Vatican"; //??? CHECK

        assertThat(actual, equalTo(expected));
    }
    @Test
    public void testPointNearBoundaryVertex1() {
        // A point that is just inside vertex on boundary polygon of Europe/Vatican:
        final double latitude  = 41.9063606262207 - 1e-6;
        final double longitude = 12.4509162902832;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = "Europe/Vatican"; //??? CHECK

        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testPointNearBoundaryVertex2() {
        // A point that is just inside vertex on boundary polygon of Europe/Vatican:
        final double latitude  = 41.9063606262207 + 1e-6;
        final double longitude = 12.4509162902832;
        final String actual = TzWhere.getLatLogTimeZoneId(latitude, longitude);
        final String expected = "Europe/Vatican"; //??? CHECK

        assertThat(actual, equalTo(expected));
    }


    @Test
    public void testx1() {
        final String zoneId = TzWhere.getLatLogTimeZoneId(90, 0);
        System.err.println("zoneId = " + zoneId);
    }

    @Test
    public void testx2() {
        final String zoneId = TzWhere.getLatLogTimeZoneId(-90, 0);
        System.err.println("zoneId = " + zoneId);
    }

    @Test
    public void testx3() {
        final String zoneId = TzWhere.getLatLogTimeZoneId(89.9, 0);
        System.err.println("zoneId = " + zoneId);
    }

    @Test
    public void testx4() {
        final String zoneId = TzWhere.getLatLogTimeZoneId(-89.9, 0);
        System.err.println("zoneId = " + zoneId);
    }


}
