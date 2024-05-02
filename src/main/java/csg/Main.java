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

        double scale = 1.0;

        double brickSize = 30.0 * scale;
        double width = 5.0 * scale;
        double height = 2.0 * scale;
        double boardSize = 100.0 * scale;
        double space = 3.0 * scale;

        double brickHoleIncrease = 0.5;

        // cross brick
        Cross cross = new Cross(brickSize, width, height);
        Geometry3D res1 = cross.getGeometry(csg);
        // to view cross
//        csg.view(res1);

        // circle brick
        Circle circle = new Circle(brickSize-brickHoleIncrease*2, width-brickHoleIncrease*4, height);
        Geometry3D res2 = circle.getGeometry(csg);
        // to view circle
        csg.view(res2);

        // new identical cross and circle bricks, with increased width to make board fit the bricks
        Cross crossCutOut = new Cross(brickSize+brickHoleIncrease, width+brickHoleIncrease, height);
        Circle circleCutOut = new Circle(brickSize+brickHoleIncrease, width+brickHoleIncrease, height);

        // board with cutouts
        Board board  = new Board(boardSize, space, height, brickSize);
        Geometry3D res3 = board.getGeometry(csg, circleCutOut, crossCutOut);
        // to view board
//        csg.view(res3);

    }
}
