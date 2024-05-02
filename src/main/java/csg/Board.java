package csg;

import org.abstractica.javacsg.Geometry2D;
import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;

public class Board {
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

    private Geometry2D getBoard2D(JavaCSG csg) {
        Geometry2D rect = csg.rectangle2D(boardSize, boardSize);
        return rect;
    }

    private Geometry3D getBoard3D(JavaCSG csg) {
        Geometry2D rect2D = getBoard2D(csg);
        Geometry3D res = csg.linearExtrude(height, false, rect2D);
        return res;
    }

    public Geometry3D getGeometry(JavaCSG csg, Circle circle, Cross cross)
    {
        // initializes a circle and a cross
        Geometry3D circleBrick = circle.getGeometry(csg);
        Geometry3D crossBrick = cross.getGeometry(csg);

        // combines the circle and cross to a brick cutout
        Geometry3D brick = csg.union3D(circleBrick, crossBrick);

        // initializes a board
        Geometry3D board = getBoard3D(csg);

        // moves the brick cutout up by half the height, so that it only cuts it from the top
        brick = csg.translate3DZ(height/2).transform(brick);

        // moves the cutout up in the top right corner
        brick = csg.translate3D((brickHoleSize + space) * 2, (brickHoleSize + space) * 2, 0).transform(brick);

        for (int i = 1; i <= 3; i++)
        {
            for (int j = 1; j <= 3; j++)
            {
                // move the brick
                Geometry3D translatedBrick = csg.translate3D(-(brickHoleSize + space) * i, -(brickHoleSize + space) * j, 0).transform(brick);

                // Cut out the translated brick from the board
                board = csg.difference3D(board, translatedBrick);
            }
        }
        return board;
    }
}