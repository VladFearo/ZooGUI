package mobility;
/**
 * A class describing animal a point
 *
 * @author  Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 */
public class Point {
    private int x;
    private int y;
    private final int max_x = 800;
    private final int max_y = 600;
    private final int min_x = 0;
    private final int min_y = 0;
    /**
     * A constructor for the Point class.
     *
     * @param x
     *            - x axis coordinate
     * @param y
     *            - y axis coordinate
     */
    public Point(int x,int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * a static method that checks if a given point is within the boundaries given (x within 0 and 800 and y within 0 and 600)
     *
     * @param point
     *            - a point object
     * @return boolean
     *            - true if within bounds and false otherwise
     */
    public static boolean checkBoundaris(Point point) {
        if (point.get_x() <= point.get_max_x()&& point.get_x() >= point.get_min_x() && point.get_y() <= point.get_max_y() && point.get_y() >= point.get_min_y()){
            return true;
        }
        return false;
    }

    /**
     * A copy constructor for the Point class.
     *
     * @param location
     *            - a point object
     */
    public Point(Point location) {
        this.x = location.get_x();
        this.y = location.get_y();
    }

    /**
     * Getter method for the max_x variable
     *
     * @return max_x
     *            - Returns the max_x coordinate
     */
    public int get_max_x() {
        return this.max_x;
    }
    /**
     * Getter method for the max_y variable
     *
     * @return max_y
     *            - Returns the max_y coordinate
     */
    public int get_max_y() {
        return this.max_y;
    }
    /**
     * Getter method for the min_x variable
     *
     * @return min_x
     *            - Returns the min_x coordinate
     */
    public int get_min_x() {
        return this.min_x;
    }
    /**
     * Getter method for the min_y variable
     *
     * @return min_y
     *            - Returns the min_y coordinate
     */
    public int get_min_y() {
        return this.min_y;
    }
    /**
     * Getter method for the x variable
     *
     * @return x
     *            - Returns the x coordinate
     */
    public int get_x() {
        return this.x;
    }
    /**
     * Getter method for the y variable
     *
     * @return y
     *            - Returns the y coordinate
     */
    public int get_y() {
        return this.y;
    }
    public void set_x(int x) {
        this.x = x;
    }

    public void set_y(int y) {
        this.y = y;
    }

}