package animals;

import java.awt.Graphics;

import diet.*;

import food.EFoodType;
import food.IEdible;
import graphics.ZooPanel;
import mobility.Point;
import utilities.MessageUtility;
/**
 * A class describing an Turtle inherits from ChewAnimal
 *
 * @author  Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 * @see     ChewAnimal
 */
public class Turtle extends ChewAnimal{

    private int Age;
    private static final Point starting_location = new Point(80,0);
    private static final Herbivore diet = new Herbivore();
    private ZooPanel panel;
    /**
     * A constructor for the Turtle class.
     * sets default Age and weight and diet.
     *
     * @param name
     *            - Name of the Turtle
     * @param location
     *            - Starting location of the Turtle
     */

    /**
     * A constructor for the Turtle class with a default starting location.
     * sets default nechLength and weight and diet.
     *
     * @param name
     *            - Name of the Turtle
     */
    public Turtle(String name,float weight,String color,ZooPanel panel) {
        super(name,starting_location,color,panel);
        super.setWeight(weight);
        super.setDiet(diet);
        this.Age = 1;
        MessageUtility.logConstractor("Turtle", name);
        this.loadImages("trt");
        this.panel = panel;
        this.panel.repaint();

    }
    /**
     * Setter method for the Age variable
     * must be between 0 and 500
     *
     * @param Age
     *            - Age of the Turtle
     *
     * @return boolean
     *            - Return true if the setter succeeded and false otherwise
     */
    public boolean setAge(int Age) {
        if(0 <= Age && Age <= 500) {
            this.Age = Age;
            MessageUtility.logSetter(super.getName(), "setAge", Age, true);
            return true;
        }
        MessageUtility.logSetter(super.getName(), "setAge", Age, false);
        return false;

    }

    /**
     * Getter method for the Foodtype variable
     *
     * @return Foodtype
     *            - Returns what type of food the animal is
     */

    @Override
    public EFoodType getFoodtype() {
        MessageUtility.logGetter(this.getClass().getSimpleName(), "getFoodType", EFoodType.MEAT);
        return EFoodType.MEAT;
    }
    /**
     * an eat method for the Turtle gets a type of food and checks weather it can eat it and adds weight if eating is possible otherwise returning false
     *
     * @param food
     *            - a type of food
     *
     * @return boolean
     *            - Return true if the Giraffe can eat this food and false if not
     */
    @Override
    public boolean eat(IEdible food) {
        double W = diet.eat(this, food);
        if(W > 0) {
            super.setWeight(super.getWeight()+W);
            super.makeSound();
            return true;
        }
        return false;
    }
    /**
     * An implementation of the Chew method using the MessageUtility method and the Turtle special message
     */
    @Override
    public void chew() {
        MessageUtility.logSound(super.getName(), "Retracts its head in then eats quietly");

    }


}
