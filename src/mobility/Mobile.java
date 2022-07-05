package mobility;

import java.util.Observable;

/**
 * An abstract class describing animal mobility implements the Ilocatable interface
 *
 * @author Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 * @see     Ilocatable
 */
@SuppressWarnings("deprecation")
public abstract class Mobile extends Observable implements Ilocatable {

    private Point location;
    private double totalDistance;
    /**
     * Getter method for the location variable
     *
     * @return location
     *            - Returns the location of the animal
     */
    @Override
    public Point getLocation() {
        return this.location;
    }
    /**
     * Setter method for the location variable
     * checks if the location is within boundaries and sets the location if so
     *
     * @param location
     *            - location of the Animal
     *
     * @return boolean
     *            - Return true if the setter succeeded and false otherwise
     */
    @Override
    public boolean setLocation(Point point) {
        if (Point.checkBoundaris(point)) {
            this.location = new Point(point);
            return true;
        }
        return false;
    }
    /**
     * A constructor for the mobile class.
     * sets the starting distance to 0.
     *
     * @param location
     *            - location of the Animal
     */
    public Mobile(Point location) {
        this.location = new Point(location.get_x(),location.get_y());
        this.totalDistance = 0;
    }
    /**
     * gets a distance checks if it's valid and adds its to the total distance
     *
     * @param distance
     *            - a float number representing distance traveled
     */
    public void addTotalDistance(double distance) {
        if(distance > 0) {
            this.totalDistance+=distance;
        }
    }
    /**
     * calculates a distance between the current point and a given point using the pythagorean theorem
     *
     * @param point
     *            - a point object representing the point to calculate the distance from where the animal is right now
     */
    public double calcDistance(Point point) {

        return Math.sqrt(Math.pow((location.get_x()-point.get_x()) ,2)
                +Math.pow((location.get_y()-point.get_y()) ,2));


    }
    /**
     * moves from the current point to a point given and adding the total distance travaled
     *
     * @param point
     *            - a point object
     */
    public double move(Point Move)
    {
        double temp = calcDistance(Move);
        this.addTotalDistance(temp);
        return temp;
    }
    /**
     * Getter method for the distance variable
     *
     * @return distance
     *            - Returns the distance the animal has traveled
     */
    public double getDistance() {
        return this.totalDistance;
    }
}