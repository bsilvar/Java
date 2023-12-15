import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class RandomWalk implements RandomWalkInterface {

    // Instance data
    private Random random;
    private int gridSize;
    private boolean done;
    private ArrayList<Point> path;
    private Point start, current, end;

    // 1st Constructor
    public RandomWalk(int size) {
        this.random = new Random();
        this.gridSize = size;
        this.done = false;
        this.path = new ArrayList<Point>();
        this.start = new Point(0,gridSize -1);
        this.current = new Point(start.x,start.y);
        this.end = new Point(gridSize-1,0);
        this.path.add(start);
    }

    // 2nd Constructor
    public RandomWalk(int size, long seed) {
        this.random = new Random(seed);
        this.gridSize = size;
        this.done = false;
        this.path = new ArrayList<Point>();
        this.start = new Point(0,gridSize-1);
        this.current = new Point(start.x,start.y);
        this.end = new Point(gridSize-1,0);
        this.path.add(current);
    }

    public void step() {
        int x = current.x;
        int y = current.y;

        if(!done) {
            int value = random.nextInt(2); // generates 0 or 1
            if(value == 0) { // move right
                if(x == end.x && y != end.y) { // hit side of grid but can move up
                    y--; // move up
                } else {
                    x++; // move right
                }
            } else { // move up
                if(y == end.y && x != end.x) { // hit top of grid but can move right
                    x++; // move right
                } else {
                    y--; // move up
                }
            }
            current = new Point(x,y); // update current
            path.add(current); // add step to path

            if(current.x == end.x && current.y == end.y) { // this step is the last step
                done = true;
            }
        }
    }

    public void createWalk() {
        while(!isDone()) {
            step();
        }
    }

    public boolean isDone() {
        return done;
    }

    public int getGridSize() {
        return gridSize;
    }

    public Point getStartPoint() {
        return start;
    }

    public Point getEndPoint() {
        return end;
    }

    public Point getCurrentPoint() {
        return current;
    }

    public ArrayList<Point> getPath() {
        ArrayList<Point> copy = new ArrayList<Point>();
        for(Point point : path) {
            copy.add(point);
        }
        return copy;
    }

    public String toString() {
        String string = "";
        for(Point point : path) {
            string += "[" + point.x + "," + point.y + "] ";
        }
        return string;
    }

}

