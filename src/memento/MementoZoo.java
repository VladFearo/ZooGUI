package memento;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import animals.Animal;
import graphics.ZooPanel;
import mobility.Point;
import plants.Plant;

public class MementoZoo {


    private ArrayList<Animal> animals ;
    private LinkedList<Animal> queue_animals ;
    private Animal animal ;
    private Plant centerFood;
    private int background;

    /*
     * class that restore old states of the zoo
     */

    public MementoZoo(ArrayList<Animal> animals,LinkedList<Animal> queue_animals, Plant centerFood,ZooPanel panel) throws CloneNotSupportedException {
        this.animals = new  ArrayList<Animal>();
        this.queue_animals = new  LinkedList<Animal>();
        for(Animal animal : animals) {
            this.animal = (Animal)animal.clone();
            this.animals.add(this.animal);
        }

        for(Animal animal : queue_animals) {
            this.animal = (Animal)animal.clone();
            this.queue_animals.add(this.animal);
        }
        this.centerFood =Plant.getInstance(panel);
        this.centerFood = centerFood;
        this.background = panel.getBackGround();
    }

    public ArrayList<Animal> getState() {
        return animals;
    }

    public LinkedList<Animal> getStateQueue() {
        return queue_animals;
    }

    public Plant getFoodState() {
        return centerFood;
    }

    public int getbackground() {
        return this.background;
    }



}