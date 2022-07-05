package memento;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import animals.Animal;
import mobility.Point;
import plants.Plant;

/*
 * saves states of the zoo
 */
public class CareTaker {

    private ArrayList<ArrayList<Animal>> animals = new ArrayList<ArrayList<Animal>>();
    private LinkedList<LinkedList<Animal>> animalsQueue = new LinkedList<LinkedList<Animal>>();
    private ArrayList<Plant> centerFoodList = new ArrayList<Plant>();
    private ArrayList<Integer> background_list = new  ArrayList<Integer>();
    public void addMemento(MementoZoo m) {
        if(animals.size()<3) {
            animals.add(m.getState());
            centerFoodList.add(m.getFoodState());
            animalsQueue.add(m.getStateQueue());
            background_list.add(m.getbackground());
        }
        else {
            animals.remove(0);
            animals.add(m.getState());
            centerFoodList.remove(0);
            centerFoodList.add(m.getFoodState());
            animalsQueue.remove(0);
            animalsQueue.add(m.getStateQueue());
            background_list.remove(0);
            background_list.add(m.getbackground());

        }
    }

    public ArrayList<Animal> getMemento() {
        try {
            return animals.remove(animals.size()-1);
        }
        catch(IndexOutOfBoundsException error) {
            ImageIcon icon =new ImageIcon("Mpicture.png");
            JOptionPane.showMessageDialog(null, "no memento saved",
                    "Message", JOptionPane.ERROR_MESSAGE, icon);
        }
        return null;
    }

    public LinkedList<Animal> getQueueMemento() {
        if(animalsQueue.size()>0)
            return animalsQueue.remove(animalsQueue.size()-1);
        return null;

    }

    public Plant getPlantMemento() {
        try {
            return centerFoodList.remove(centerFoodList.size()-1);
        }
        catch(IndexOutOfBoundsException error) {
            ImageIcon icon =new ImageIcon("Mpicture.png");
            JOptionPane.showMessageDialog(null, "no memento saved",
                    "Message", JOptionPane.ERROR_MESSAGE, icon);
        }
        return null;
    }


    public boolean isEmpty() {
        return animals.isEmpty();
    }

    public boolean queueIsEmpty() {
        return animalsQueue.isEmpty();
    }

    public Integer getMementoBackground(){
        try {
            return background_list.remove(background_list.size()-1);
        }
        catch(IndexOutOfBoundsException error) {
            ImageIcon icon =new ImageIcon("Mpicture.png");
            JOptionPane.showMessageDialog(null, "no memento saved",
                    "Message", JOptionPane.ERROR_MESSAGE, icon);
        }
        return null;
    }
}