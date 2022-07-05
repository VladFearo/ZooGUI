package animals;

import graphics.ZooFrame;
import graphics.ZooPanel;
import mobility.Point;

/**
 * An abstract class describing animals that roar implementing the makeSound method and defines the roar method, inherits from Animal class
 *
 * @author  Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 * @see     Animal
 */
public abstract class RoarAnimal extends Animal{
    /**
     * A constructor for the RoarAnimal class.
     *
     * @param name
     *            - Name of the RoarAnimal
     * @param location
     *            - Starting location of the RoarAnimal
     */
    public RoarAnimal(String name, Point location,String color,ZooPanel panel) {
        super(name, location,color,panel);
    }

    /**
     * An implementation of the makeSound method calling the roar method
     */
    public void makeSound() {
        this.roar();

    }
    /**
     * A declaration of the roar method.
     */
    public abstract void roar();


}
