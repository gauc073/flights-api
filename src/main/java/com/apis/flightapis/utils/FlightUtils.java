package com.apis.flightapis.utils;

public class FlightUtils {
    public static boolean doesStringValuePresent(String stringValue) {
        return stringValue != null && stringValue.trim().length() > 0;
    }
}
