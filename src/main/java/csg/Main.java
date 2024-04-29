package csg;

import org.abstractica.javacsg.*;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args)
    {
        JavaCSG csg = JavaCSGFactory.createNoCaching();

//        Geometry3D box = csg.box3D(10, 20, 30, true);
//        Geometry3D sphere = csg.sphere3D(20,128, true);
//        box = csg.intersection3D(box, sphere);
//        csg.view(box);

//        List<Vector2D> vertices = new ArrayList<Vector2D>();
//        vertices.add(csg.vector2D(0,0));
//        vertices.add(csg.vector2D(2,0));
//        vertices.add(csg.vector2D(2,3));
//        vertices.add(csg.vector2D(1,2));
//        vertices.add(csg.vector2D(0,3));
//        Geometry2D thing = csg.polygon2D(vertices);
//        thing = csg.scale2D(10,10).transform(thing);
//        thing = csg.translate2DX(10).transform(thing);
//        Geometry3D thing3d = csg.linearExtrude(5,false, thing);
//        Geometry3D thingRotated = csg.rotateExtrude(csg.degrees(360),64, thing);
//        csg.view(thingRotated);

        double brickSize = 30.0;
        double width = 5.0;
        double height = 2.0;
        double boardSize = 100.0;
        double space = 1.0;
        double brickHoleSize = 0.5;

        Cross cross = new Cross(brickSize, width, height);
        Geometry3D res1 = cross.getGeometry(csg);
        // to view cross
//        csg.view(res1);

        Circle circle = new Circle(brickSize, width, height);
        Geometry3D res2 = circle.getGeometry(csg);
        // to view circle
//        csg.view(res2);

        Board board  = new Board(boardSize, space, height, brickSize+brickHoleSize);
        Geometry3D res3 = board.getGeometry(csg, circle, cross);
        // to view board
        csg.view(res3);

    }
}
