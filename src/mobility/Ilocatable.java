package mobility;

/**
 * An interface organizing the location of the animals declaring the getLocation and setLocation methods
 *
 * @author  Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 */
public interface Ilocatable {
    /*
     * A declaration of the getLocation method
     * */
    public Point getLocation();
    /*
     * A declaration of the setLocation method
     * */
    public boolean setLocation(Point point);
}