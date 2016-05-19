package org.sensoranalytics;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.index.strtree.STRtree;
import org.apache.commons.math3.util.Pair;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TzWhere {

    static STRtree buildIndex() throws IOException {
        File zones = new File("./Input data/tz_world.txt");
        List<String> tz_labels = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(zones))) {
            for(String line; (line = br.readLine()) != null; ) {
                if (line.contains("TZID")) continue;
                tz_labels.add(line.trim());
            }
        }

        File file = new File("./Input data/tz_world.shp");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", file.toURI().toURL());

        DataStore dataStore = DataStoreFinder.getDataStore(map);
        String typeName = dataStore.getTypeNames()[0];

        FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
        Filter filter = Filter.INCLUDE;

        FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
        FeatureIterator<SimpleFeature> features = collection.features();

        STRtree tzIndex = new STRtree();

        int zone_index = 0;
        while (features.hasNext()) {
            SimpleFeature feature = features.next();
            String zone_name = tz_labels.get(zone_index++);
            Pair<SimpleFeature, String> pair = new Pair<SimpleFeature, String>(feature, zone_name);
            Geometry geom = (Geometry) feature.getDefaultGeometry();
            tzIndex.insert(geom.getEnvelopeInternal(), pair);
        }
        System.out.println("Processed " + zone_index + " features from " + tz_labels.size()+ " zones.");
        tzIndex.build();
        return tzIndex;
    }

    private static void testTwo(STRtree index) {
        Coordinate query = new Coordinate(-86.764727, 37.952126);
        List<Pair<SimpleFeature, String>> secondList = index.query(new Envelope(query));

        GeometryFactory gf = new GeometryFactory();

        Point point = gf.createPoint(query);

        for (Pair<SimpleFeature, String> feat: secondList){
            SimpleFeature first = feat.getFirst();
            Geometry geom = (Geometry) first.getDefaultGeometry();
            if (geom.contains(point)) {
                System.out.println(feat.getSecond());
            }
        }
    }

    private static void testOne(STRtree index) {
        List<Pair<SimpleFeature, String>> list = index.query(new Envelope(new Coordinate(-77.0920, 38.8044)));

        for (Pair<SimpleFeature, String> feat: list){
           System.out.println(feat.getSecond());
        }
    }

    public static void main(String... args) throws IOException {
        STRtree index = TzWhere.buildIndex();
        testOne(index);
        testTwo(index);
    }
}
