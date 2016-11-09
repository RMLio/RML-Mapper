package be.ugent.mmlab.rml.function;

import geocoordinate.GeoCoordinateProcessor;

public class DBpediaFunctions {


    public static String latFunction(String coordinate, String degrees, String minutes, String seconds, String direction) {
        return GeoCoordinateProcessor.latFunction(coordinate, degrees, minutes, seconds,direction);
    }


    public static String lonFunction(String coordinate, String degrees, String minutes, String seconds, String direction) {
        return GeoCoordinateProcessor.lonFunction(coordinate, degrees, minutes, seconds, direction);
    }

}