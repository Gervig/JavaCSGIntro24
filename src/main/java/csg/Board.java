package csg;

import org.abstractica.javacsg.Geometry2D;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class Board
{
    private final double boardSize;
    private final double space;
    private final double height;
    private final double brickHoleSize;

    public Board(double boardSize, double space, double height, double brickHoleSize) {
        this.boardSize = boardSize;
        this.space = space;
        this.height = height;
        this.brickHoleSize = brickHoleSize;
    }
//    public Geometry3D getGeometry(JavaCSG csg)
//    {
//        return intersectBoard(csg, circle.getGeometry(csg), )
//    }
    private Geometry2D getBoard2D(JavaCSG csg)
    {
        Geometry2D rect = csg.rectangle2D(boardSize, boardSize);
        return rect;
    }
    private Geometry3D getBoard3D(JavaCSG csg)
    {
        Geometry2D rect2D = getBoard2D(csg);
        Geometry3D res = csg.linearExtrude(height, false, rect2D);
        return res;
    }
    public Geometry3D getGeometry(JavaCSG csg, Circle circle, Cross cross)
    {
        Geometry3D circleBrick = circle.getGeometry(csg);
        Geometry3D crossBrick = cross.getGeometry(csg);
        Geometry3D brick = csg.union3D(circleBrick, crossBrick);
        Geometry3D board = getBoard3D(csg);
        // moves the brick cut out up by 1 mm, so that it only cuts it from the top

        brick = csg.translate3DZ(1).transform(brick);

        // moves the cut out up in the top left corner
        brick = csg.translate3D(-(brickHoleSize-space), -(brickHoleSize-space), 0).transform(brick);

        board = csg.difference3D(board, brick);
        for(int i = 0; i < 3; i++)
        {
            //todo make the cut out happen 2 more times to the right of it (remember to add spaces)
//            brick = csg.translate3D(space + (i * brickHoleSize), space, 0).transform(brick);

            for(int j = 0; j < 3; j++)
            {
                //todo make the cut out happen 2 more times beneath it (remember to add spaces)
//                brick = csg.translate3D(space, space + (i * brickHoleSize), 0).transform(brick);

            }
        }
        return board;
    }
}