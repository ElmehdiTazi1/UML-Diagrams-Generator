package org.mql.java;

import java.awt.Point;

public class Util {

    public static boolean isStartBeforeEnd(Point startPoint, Point endPoint) {
        // Comparaison des coordonnées x
        if (startPoint.x < endPoint.x) {
            return true;
        } else if (startPoint.x > endPoint.x) {
            return false;
        } else {
            // Si les coordonnées x sont égales, comparer les coordonnées y
            return startPoint.y < endPoint.y;
        }
    }
}
