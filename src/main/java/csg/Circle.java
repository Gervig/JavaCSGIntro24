package csg;

import org.abstractica.javacsg.Geometry2D;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class Circle
{
    private final double brickSize;
    private final double width;
    private final double height;

    public Circle(double brickSize, double width, double height) {
        this.brickSize = brickSize;
        this.width = width;
        this.height = height;
    }

    public Geometry3D getGeometry(JavaCSG csg)
    {
        Geometry2D circle2D = getCircle2D(csg);
        Geometry3D res = csg.linearExtrude(height, false, circle2D);
        return res;
    }
    private Geometry2D getCircle2D(JavaCSG csg)
    {
//        Geometry2D circle1 = csg.circle2D(brickSize, 64);
//        Geometry2D circle2 = csg.circle2D(brickSize-width, 64);
//        Geometry2D circleFinal = csg.intersection2D(circle1, circle2);
//        return circleFinal;

        Geometry2D circle = csg.ring2D(brickSize-width, brickSize,64);
        return circle;
    }

}
