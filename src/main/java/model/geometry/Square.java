package model.geometry;

import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Rectangle;

public class Square extends Rectangle {

    public Square(Point bl, double b) {
        super(bl, b, b);
    }
}
