package animals;

import java.awt.Graphics;

import diet.Herbivore;
import food.EFoodType;
import food.IEdible;
import graphics.ZooPanel;
import mobility.Point;
import utilities.MessageUtility;
/**
 * A class describing an giraffe inherits from ChewAnimal
 *
 * @author  Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 * @see     ChewAnimal
 */
public class Giraffe extends ChewAnimal {


    private static final Herbivore diet = new Herbivore();
    private static final Point starting_location = new Point(50,0);
    private double neckLength;
    private ZooPanel panel;

    /**
     * A constructor for the Giraffe class.
     * sets default nechLength and weight and diet.
     *
     * @param name
     *            - Name of the Giraffe
     * @param location
     *            - Starting location of the Giraffe
     */
//	public Giraffe(String name,Point location,float weight){
//		super(name,location);
//		super.setWeight(weight);
//		super.setDiet(diet);
//		MessageUtility.logConstractor("Giraffe", name);
//		this.neckLength = 1.5 ;
//
//	}
    /**
     * A constructor for the Giraffe class with a default starting location.
     * sets default nechLength and weight and diet.
     *
     * @param name
     *            - Name of the Giraffe
     */
    public Giraffe(String name,float weight,String color,ZooPanel panel) {
        super(name,starting_location,color,panel);
        super.setWeight(weight);
        super.setDiet(diet);
        MessageUtility.logConstractor("Giraffe", name);
        this.neckLength = 1.5 ;
        this.loadImages("grf");
        this.panel = panel;
        this.panel.repaint();

    }
    /**
     * Setter method for the neckLength variable
     * must be between 1 and 2.5
     *
     * @param neck
     *            - neck length of the Giraffe
     *
     * @return boolean
     *            - Return true if the setter succeeded and false otherwise
     */
    public boolean setNeck(double neck) {
        if(1 <= neck && neck >= 2.5) {
            this.neckLength = neck;
            MessageUtility.logSetter(super.getName(), "setNeck", neck, true);
            return true;
        }
        MessageUtility.logSetter(super.getName(), "setNeck", neck, false);
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
     * an eat method for the Giraffe gets a type of food and checks weather it can eat it and adds weight if eating is possible otherwise returning false
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
            MessageUtility.logBooleanFunction(this.getName(), "eat", food.getClass().getSimpleName(), true);
            return true;
        }
        MessageUtility.logBooleanFunction(this.getName(), "eat", food.getClass().getSimpleName(), false);
        return false;
    }
    /**
     * An implementation of the Chew method using the MessageUtility method and the Giraffe special message
     */
    @Override
    public void chew() {
        MessageUtility.logSound(super.getName(), "Bleats and Stomps its legs, then chews");

    }


}
