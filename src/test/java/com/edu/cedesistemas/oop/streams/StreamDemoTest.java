package com.edu.cedesistemas.oop.streams;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import com.edu.cedesistemas.oop.model.geometry.Circle;
import com.edu.cedesistemas.oop.model.geometry.Point;
import com.edu.cedesistemas.oop.model.geometry.Rectangle;
import com.edu.cedesistemas.oop.model.geometry.Shape;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StreamDemoTest {
    @Test
    public void testFilterShapes() {
        Circle c1 = new Circle(20);
        Circle c2 = new Circle(10);
        Circle c3 = new Circle(30);
        Circle c4 = new Circle(15);
        Circle c5 = new Circle(5);

        List<Circle> circles = Arrays.asList(c1, c2, c3, c4, c5);
        List<? extends Shape> newCircles = StreamDemo.filterShapes(1000, circles);
        assertThat(newCircles, contains(c2, c4, c5));

        Rectangle r1 = new Rectangle(Point.of(0, 0), 10, 20);
        Rectangle r2 = new Rectangle(Point.of(0, 0), 15, 5);
        Rectangle r3 = new Rectangle(Point.of(0, 0), 5, 8);
        Rectangle r4 = new Rectangle(Point.of(0, 0), 4, 15);
        Rectangle r5 = new Rectangle(Point.of(0, 0), 3, 7);

        List<Rectangle> rectangles = Arrays.asList(r1, r2, r3, r4, r5);
        List<? extends Shape> newRectangles = StreamDemo.filterShapes(60, rectangles);
        assertThat(newRectangles, contains(r3, r4, r5));
    }

    @Test
    public void testScale() {
        Circle c1 = new Circle(5);
        Circle c2 = new Circle(10);
        Circle c3 = new Circle(15);
        List<Circle> circles = Arrays.asList(c1, c2, c3);
        List<Circle> newCircles = StreamDemo.scale(circles, 200D);

        Integer sum = newCircles.stream()
                .mapToInt(c -> (int) c.getRadius())
                .sum();
        assertThat(sum, equalTo(60));

        ScalableImpl s1 = new ScalableImpl(2);
        ScalableImpl s2 = new ScalableImpl(4);
        ScalableImpl s3 = new ScalableImpl(6);

        List<ScalableImpl> scalables = Arrays.asList(s1, s2, s3);
        List<ScalableImpl> newScalables = StreamDemo.scale(scalables, 100D);

        Integer sSum = newScalables.stream()
                .mapToInt(s -> (int) s.getV())
                .sum();
        assertThat(sSum, equalTo(1200));
    }
}
