package org.mql.java;

import java.awt.Point;

public class Util {

    public static boolean isStartBeforeEnd(Point startPoint, Point endPoint) {
        if (startPoint.x < endPoint.x) {
            return true;
        } else if (startPoint.x > endPoint.x) {
            return false;
        } else {
            return startPoint.y < endPoint.y;
        }
    }
}
