package plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.*;
import utilities.MessageUtility;

/**
 * @author baroh
 *
 */
public class Plant  implements IEdible, Ilocatable,IDrawable,Cloneable {

    private double height;

    private Point location;

    private double weight;
    private ZooPanel zoopanel;
    private BufferedImage plant_image = null;

    public static Plant plant = null;

    public static Plant getInstance(ZooPanel panel) {
        if(plant == null)
            plant = new Plant(panel);
        return  plant;
    }

    protected Plant(ZooPanel zoopanel) {
        Random rand = new Random();
        int x = rand.nextInt(30);
        int y = rand.nextInt(12);
        this.location = new Point(x, y);
        this.height = rand.nextInt(30);
        this.weight = rand.nextInt(12);
        MessageUtility.logConstractor("Plant", "Plant");
        this.zoopanel = zoopanel;
    }

    /*
     * (non-Javadoc)
     *
     * @see food.IFood#getFoodtype()
     */
    @Override
    public EFoodType getFoodtype() {
        //MessageUtility.logGetter(this.getClass().getSimpleName(), "getFoodType", EFoodType.VEGETABLE);
        return EFoodType.VEGETABLE;
    }

    /**
     * @return
     */
    public double getHeight() {
        MessageUtility.logGetter(this.getClass().getSimpleName(), "getHeight", this.height);
        return this.height;
    }

    /*
     * (non-Javadoc)
     *
     * @see mobility.ILocatable#getLocation()
     */
    @Override
    public Point getLocation() {
        //MessageUtility.logGetter(this.getClass().getSimpleName(), "getLocation", this.location);
        return this.location;
    }

    /**
     * @return
     */
    public double getWeight() {
        //MessageUtility.logGetter(this.getClass().getSimpleName(), "getWeight", this.weight);
        return weight;
    }

    /**
     * @param height
     * @return
     */
    public boolean setHeight(double height) {

        boolean isSuccess = (height >= 0);
        if (isSuccess) {
            this.height = height;
        } else {
            this.height = 0;
        }
        //MessageUtility.logSetter(this.getClass().getSimpleName(), "setHeight", height, isSuccess);
        return isSuccess;
    }

    /*
     * (non-Javadoc)
     *
     * @see mobility.ILocatable#setLocation(mobility.Point)
     */
    @Override
    public boolean setLocation(Point newLocation) {
        boolean isSuccess = Point.checkBoundaris(newLocation);
        if (isSuccess) {
            this.location = newLocation;
        }
        //MessageUtility.logSetter(this.getClass().getSimpleName(), "setLocation", newLocation, isSuccess);
        return isSuccess;
    }

    /**
     * @param weight
     * @return
     */
    public boolean setWeight(double weight) {
        boolean isSuccess = (weight >= 0);
        if (isSuccess) {
            this.weight = weight;
        } else {
            this.weight = 0;
        }
        //MessageUtility.logSetter(this.getClass().getSimpleName(), "setWeight", weight, isSuccess);

        return isSuccess;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] ";
    }

    public void drawObject(Graphics g)
    {
        if (plant_image != null)
            g.drawImage(plant_image, zoopanel.getWidth()/2 - 20, zoopanel.getHeight()/2 - 20, 40, 40, zoopanel);
        zoopanel.manageZoo();
    }

    public void loadImages(String nm)
    {
        try
        {
            plant_image = ImageIO.read(new File(PICTURE_PATH +"\\"+ nm));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String getColor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}