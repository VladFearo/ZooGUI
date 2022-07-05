package graphics;
import animals.Animal;


import diet.*;
import food.EFoodType;
import memento.CareTaker;
import memento.MementoZoo;
import mobility.Point;
import observer.Controller;
import animals.*;
import plants.Cabbage;
import plants.Lettuce;
import plants.Meat;
import plants.Plant;
import zoo.ZooActions;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Class that show visual the animals on frame
 * @author   Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 * @see     ZooFrame
 */

public class ZooPanel extends JPanel implements Runnable , ActionListener{

    private static final String BACKGROUND_PATH = "C:\\Users\\user\\Desktop\\assignment2_pictures\\assignment2_pictures";
    private static final int GREEN = 1;
    private static final int WHITE = 0;
    private static final int NULLFOOD = 4;
    private BufferedImage img = null;
    private BufferedImage food_img = null;
    private ArrayList<Animal> animals_list;
    private LinkedList<Animal> animals_queue;
    private Plant plant;
    private int color;
    private Boolean flag = false;
    private Controller controller;
    private boolean exit = false;
    private boolean centerFoodFlag = false ;
    private ExecutorService executor ;
    private final int THREADS_COUNT = 10 ;
    private static ZooPanel zoopanel = null;
    private String animalType = null;
    private MementoZoo momento;
    private CareTaker caretaker;
    private int background = 2 ;




    public static ZooPanel getInstance() {
        if (zoopanel == null)
            zoopanel = new ZooPanel();
        return zoopanel;
    }


    private ZooPanel() {
        animals_list = new ArrayList<Animal>();
        animals_queue = new LinkedList<Animal>();
        controller = new Controller(this);
        plant = null;
        caretaker = new CareTaker();
        this.setBackground(null);
        this.setVisible(true);
        try
        {
            img = ImageIO.read(new File(BACKGROUND_PATH +"\\savanna.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        executor = Executors.newFixedThreadPool(THREADS_COUNT);

    }

    @Override
    public void run() {
        while(!exit) {
            if(isChange())  //if the animals moves
                repaint();


        }
    }

    public void startPanelThread() {
        this.controller.start();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(flag)
        {
            Dimension size = this.getSize();
            gr.drawImage(img, 0, 0, size.width, size.height, this);
        }
        try
        {

            for(Animal animal : animals_list )
                animal.drawObject(gr);

            plant.drawObject(gr);
        }

        catch(Exception e)
        {
            return;
        }
    }

    public void setBackground(int num)
    {
        this.background = num;
        if(num == 0)
        {
            flag = true;
            setBackground(null);
            this.paintComponent(this.getGraphics());
        }
        if(num == 1)
        {
            flag = false;
            setBackground(null);
            this.paintComponent(this.getGraphics());
            setBackground(Color.GREEN);
        }
        else if(num == 2)
        {
            flag = false;
            setBackground(null);
        }
    }



    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }


    /**
     * The controller that manage the zoo
     */
    public synchronized void manageZoo() {


        for(Animal animal : animals_list) {
            boolean chase = false;
            if(animal.getDiet() instanceof Carnivore || animal.getDiet() instanceof Omnivore) {
                for(Animal prey : animals_list ) {

                    if(animal.calcDistance(prey.getLocation()) < prey.getSize())
                        if(animal.getWeight() > 2 * prey.getWeight())
                            if(animal.eat(prey)) {

                                animals_list.remove(prey);
                                prey.kill();
                                animal.eatInc();
                                if(animals_queue!= null && !animals_queue.isEmpty()) {
                                    this.executor.execute(animals_queue.peek());
                                    this.animals_list.add(animals_queue.remove());

                                }
                                repaint();
                                chase = true;
                                break;

                            }
                }

            } // bear or lion
            if(chase)
                break;

            if(this.plant != null) {

                if( animal.calcDistance(new Point((int)this.getWidth()/2 - 20,(int)this.getHeight()/2 - 20)) < animal.getEatDistance() && animal.eat(this.plant)) {
                    this.setPlant(NULLFOOD);
                    animal.eatInc();
                    repaint();

                }


            }


        }//outside for


    }


    private boolean isChange() {
        for(Animal animal: animals_list) {
            if(animal.getChanges())
                return true;
        }
        return false;

    }

    public int getAnimalSize() {
        return this.animals_list.size();
    }

    public ArrayList<Animal> getAnimals() {

        return this.animals_list;
    }

    public synchronized void addAnimallist(Animal animal) {
        try {
            if(animals_list.size() == 10)
                if(animals_queue != null && animals_queue.size() < 5)
                    this.animals_queue.add(animal);
                else
                    throw new ArrayIndexOutOfBoundsException();
            else {
                animal.addObserver(controller);
                animals_list.add(animal);
                executor.execute(animal);
            }



        }
        catch(ArrayIndexOutOfBoundsException error) {
            ImageIcon icon =new ImageIcon("Mpicture.png");
            JOptionPane.showMessageDialog(null, "You cannot add more than 10 animlas and 5 animlas waiting",
                    "Message", JOptionPane.ERROR_MESSAGE, icon);
        }
    }

    public synchronized void DeleteAllAnimals() {
        for(Animal animal : animals_list) {
            animal.kill();

        }
        animals_queue.clear();
        animals_list.clear();
        //this.executor.shutdown();



    }



    /**
     * the method selected the the food type
     * @param option- 1 : lettuce . 2: cabbage . 3:meat
     *
     */
    public void setPlant(int option ) {
        if(option == 1) {
            this.plant = Lettuce.getInstance(this);
            centerFoodFlag = true;
            this.plant.loadImages("lettuce.png");
            this.repaint();



        }
        else if(option == 2) {
            this.plant = Cabbage.getInstance(this);
            centerFoodFlag = true;
            this.plant.loadImages("cabbage.png");
            this.repaint();

        }
        else if (option == 3) {;
            this.plant = Meat.getInstance(this);
            centerFoodFlag = true;
            this.plant.loadImages("meat.gif");
            this.repaint();
        }
        else {
            centerFoodFlag = false;
            this.plant = null;
        }
    }

    public void setImage(String image) {
        if(image == null) {
            this.img = null;

        }
        else {
            try {
                this.img = ImageIO.read(new File(BACKGROUND_PATH+"\\"+image));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }}

    public void setColor(int color) { //0 = white , 1 = green
        this.color = color;
    }

    public void setFlag(Boolean st) {
        this.flag = st;
    }


    public synchronized void sleep() {

        for(Animal animal : animals_list)
            animal.setSuspended();

    }





    public synchronized void wakeUp() {

        for(Animal animal : animals_list)
            animal.setResumed();

    }

    public void setExit() {
        this.exit = true;
    }

    public boolean getExit() {

        return this.exit;
    }



    public  boolean getCenterFoodFlag() {
        return this.centerFoodFlag;
    }

    public  EFoodType getCenterFood()
    {
        if(plant != null)
            return this.plant.getFoodtype();
        return null;

    }

    public void setAnimalType(int type) {

        if(type == 1)
            animalType  = "Carnivore";

        else if(type == 2)
            animalType  = "Omnivore";

        else if(type == 3)
            animalType  = "Herbivore";
        else
            animalType = null;

    }

    public String getAnimalType() {
        return animalType;
    }

    public void saveState() throws CloneNotSupportedException {

        momento = new MementoZoo(animals_list,animals_queue,plant,this);

        caretaker.addMemento(momento);
    }

    public synchronized void restoreState() {

        if(!caretaker.isEmpty()) {
            ArrayList<Animal> memento_list = caretaker.getMemento();
            Plant mementoPlant = caretaker.getPlantMemento();
            LinkedList<Animal> queue_list = caretaker.getQueueMemento();

            this.DeleteAllAnimals();
            for(Animal animal : memento_list) {
                this.addAnimallist(animal);
            }
            this.plant = mementoPlant;
            centerFoodFlag = true;

            if(!caretaker.queueIsEmpty()) {
                for(Animal animal : queue_list) {
                    this.addAnimallist(animal);
                }
            }
            this.setBackground(caretaker.getMementoBackground());

        }

        else {
            ImageIcon icon =new ImageIcon("Mpicture.png");
            JOptionPane.showMessageDialog(null, "no memento saved",
                    "Message", JOptionPane.ERROR_MESSAGE, icon);
        }

        repaint();

    }
    public int getBackGround() {

        return this.background;
    }
}