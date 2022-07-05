
package animals;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import diet.*;
import factory.AnimalFactory;
import utilities.*;
import zoo.ZooActions;
import food.*;
import graphics.*;
import memento.MementoZoo;
import mobility.*;
import mobility.Point;


/**
 * An abstract class describing animals inherits from mobile class and implaments IEdible interface
 *
 * @author  Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 * @see     Mobile, IEdible
 */

public abstract class Animal extends Mobile implements IEdible,IAnimalBehavior,IDrawable,Runnable,Cloneable {

    private String name;
    private double weight;
    private IDiet diet;
    private final int EAT_DISTANCE = 10;
    private int size;
    private String col;
    private int horSpeed;
    private int verSpeed;
    private boolean coordChanged;
    private int x_dir = 1;
    private int y_dir = 1;
    private int eatCount;
    private ZooPanel pan;
    private BufferedImage img1, img2;
    protected boolean threadSuspended = false;
    private int new_x = 0;
    private int new_y = 0;
    private boolean alive = true;
    private float centercoor_x ;
    private float centercoor_y ;
    private boolean change_state =false;



    /**
     * A constructor for the animal class.
     *
     * @param name
     *            - Name of the Animal
     * @param location
     *            - Starting location of the animal
     */
    public Animal(String name, Point location , String col ,ZooPanel zoopanel) {
        super(location);
        this.name = name;
        MessageUtility.logConstractor("Animal", name);
        eatCount = 0 ;
        this.col = col;
        this.img1 = null;
        this.img2 =null;
        this.pan = zoopanel;
        new_x = location.get_x();
        new_y = location.get_y();
        centercoor_x =(int)pan.getWidth()/2 - 20;
        centercoor_y = (int)pan.getHeight()/2 - 20;

    }

//	public Animal(Animal animal) {
//		super(animal.getLocation());
//		this.name = animal.getName();
//		MessageUtility.logConstractor("Animal", name);
//		eatCount = animal.getEatCount() ;
//		this.col = animal.getColor();
//		this.img1 = animal.getImage1();
//		this.img2 =animal.getImage2();
//		this.pan = animal.getPanel();
//		new_x = animal.getLocation().get_x();
//		new_y = animal.getLocation().get_y();
//		centercoor_x =(int)pan.getWidth()/2 - 20;
//		centercoor_y = (int)pan.getHeight()/2 - 20;
//	}
//


    /*
     * life cycle of animal that work as thread
     */
    public void run() {

        while(alive) {
            while(!threadSuspended) {


                if(!alive)
                    break;

                try {
                    Thread.sleep(20);

                } catch (InterruptedException e) { }


                if(pan.getCenterFood() != null && pan.getCenterFoodFlag()&& this.diet.canEat(pan.getCenterFood())) {

                    if(new_x >= centercoor_x) {
                        x_dir = -1;
                    }
                    if(new_x <= centercoor_x) {
                        x_dir = 1;
                    }
                    if(new_y >= centercoor_y) {
                        y_dir = -1;
                    }
                    if(new_y <= centercoor_y) {
                        y_dir = 1;
                    }

                    if(Math.abs(new_x - centercoor_x)  < 9 )
                        new_y = new_y + (verSpeed * y_dir);


                    else if(Math.abs(new_y - centercoor_y ) < 9 )
                        new_x = new_x + (horSpeed * x_dir);



                    else {
                        new_x = new_x + (horSpeed * x_dir);
                        new_y = new_y + (verSpeed * y_dir);
                    }



                }

                else {

                    if(new_x >= 800) {
                        x_dir = -1;
                    }
                    if(new_x <= 0) {
                        x_dir = 1;
                    }
                    if(new_y >= 600) {
                        y_dir = -1;
                    }
                    if(new_y <= 0) {
                        y_dir = 1;
                    }
                    new_x = new_x + (horSpeed * x_dir);
                    new_y = new_y + (verSpeed * y_dir);




                }//else

                Point point = new Point(new_x,new_y);
                this.Move(point);
                this.setLocation(point);
                this.setChanged();
                this.notifyObservers();



            }//inside loop

            while(threadSuspended) {
                synchronized(this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }


        }//alive


    }




    /**
     * An abstract declaration for the makeSound method to be initialized in later classes
     */
    public abstract void makeSound();

    /**
     * An abstract declaration for the eat method to be initialized in later classes
     */
    public abstract boolean eat(IEdible food);
    /**
     * Setter method for the weight variable
     *
     * @param weight
     *            - Weight of the Animal
     *
     * @return boolean
     *            - Return true if the setter succeeded and false otherwise
     */
    public boolean setWeight(double weight) {
        if (weight > 0) {
            this.weight = weight;
            //MessageUtility.logSetter(this.name, "setWeight", this.weight, true);
            return true;
        }
        //MessageUtility.logSetter(this.name, "setWeight", this.weight, false);
        return false;
    }
    /**
     * Setter method for the diet variable
     *
     * @param diet
     *            - Diet of the Animal
     *
     * @return boolean
     *            - Return true if the setter succeeded
     */
    public boolean setDiet(IDiet diet) {
        this.diet = diet;
        //MessageUtility.logSetter(this.name, "setDiet", this.diet.getClass().getSimpleName(), true);
        return true;
    }


    /**
     * Getter method for the name variable
     *
     * @return name
     *            - Returns the name of the animal
     */
    public String getName() {
        //MessageUtility.logGetter(this.name, "getName", this.name);
        return this.name;
    }
    /**
     * Getter method for the weight variable
     *
     * @return weight
     *            - Returns the weight of the animal
     */
    public double getWeight() {
        //	MessageUtility.logGetter(this.name, "getWeight", this.weight);
        return this.weight;
    }

    /**
     * toString method of the class
     *
     *
     * @return String
     *            - Returns String representation of the object of this class
     */
    public String toString() {

        return "[" + this.getClass().getSimpleName() +  "]" + this.name + "\ttotal distance:" + String.format("%.2f", super.getDistance())+ "\tweight:" + this.getWeight();

    }

    public String getAnimalName() {

        return this.getClass().getSimpleName();

    }
    public int getSize() {

        return this.size;

    }
    public void eatInc() {
        this.eatCount++;

    }
    public int getEatCount() {
        return this.eatCount;

    }

    public boolean getChanges () {

        return this.coordChanged;

    }

    public void setChanges (boolean state) {

        this.coordChanged = state;

    }

    public String getColor() {
        return this.col;
    }




    public int getHorSpeed() {

        return this.horSpeed;
    }




    public int getVerSpeed() {

        return this.verSpeed;
    }

    public boolean setEat(int eat) {
        this.eatCount = eat;
        return true;
    }

    public void setSize(int size) {
        this.size = size;
    }




    public boolean setHorSpeed(int hor) {
        this.horSpeed = hor;
        return true;
    }

    public boolean setVerSpeed(int hor) {
        this.verSpeed = hor;
        return true;
    }

    /**
     * set color for the animals
     *
     * @param nm - string of the picture name
     *
     */
    public synchronized void loadImages(String nm)

    {
        if(this.col == "Red")
        {
            try
            {
                this.img1 = ImageIO.read(new File(PICTURE_PATH +"\\"+ nm + "_r_1.png"));
                this.img2 = ImageIO.read(new File(PICTURE_PATH +"\\"+ nm + "_r_2.png"));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if(this.col == "Blue")
        {
            try
            {
                this.img1 = ImageIO.read(new File(PICTURE_PATH +"\\"+ nm + "_b_1.png"));
                this.img2 = ImageIO.read(new File(PICTURE_PATH +"\\"+ nm + "_b_2.png"));
            }
            catch (IOException e)
            {
                e.printStackTrace();

            }
        }
        else
        {
            try
            {
                this.img1 = ImageIO.read(new File(PICTURE_PATH +"\\"+ nm + "_n_1.png"));
                this.img2 = ImageIO.read(new File(PICTURE_PATH +"\\"+ nm + "_n_2.png"));
            }
            catch (IOException e)
            {
                e.printStackTrace();

            }
        }
    }


    public String getPicturePath() {
        return this.PICTURE_PATH;
    }

    public BufferedImage getImage1() {
        return this.img1;
    }

    public BufferedImage getImage2() {
        return this.img2;
    }




    public int getXdir() {
        return this.x_dir;
    }

    public ZooPanel getPanel() {
        return this.pan;
    }

    /**
     * function that draw the animal
     * @param g draw the pictures
     *
     */
    public void drawObject (Graphics g)
    {
        int size =this.getSize();


        if(this.getXdir() == 1)
            g.drawImage(this.getImage1(), this.new_x-size/2, this.new_y-size/10, size, size, pan);

        else
            g.drawImage(this.getImage2(), this.new_x-size/2, this.new_y-size/10, size, size, pan);
    }


    public IDiet getDiet() {

        return this.diet;

    }

    public int getEatDistance() {
        return this.EAT_DISTANCE;
    }


    public synchronized void setSuspended() {
        this.threadSuspended = true;

    }

    public synchronized void setResumed() {
        this.notify();
        this.threadSuspended = false;
    }

    public int getNewX() {
        return this.new_x;
    }

    public int getNewY() {
        return this.new_y;
    }

    public boolean getSuspended(){
        return this.threadSuspended;

    }

    public void kill() {

        this.alive = false;
        //task.cancel(true);

    }
    //	public void setFuture(Future task) {
//
//		this.task = task;
//	}
//
    public void Move(Point point) {

        weight = weight - (this.move(point)*weight*0.00025);

    }

    public void setColor(String color) {
        this.col = color;
    }



    @Override
    public boolean setLocation(Point point) {
        super.setLocation(point);
        this.new_x = point.get_x();
        this.new_y = point.get_y();
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }



}