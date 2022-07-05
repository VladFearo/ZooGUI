package factory;

import animals.Animal;
import diet.IDiet;
import graphics.ZooPanel;
/*
 * interface to create animals
 */
public interface AnimalFactory {

    public Animal createAnimal(String animalType ,float weight , String color,ZooPanel panel);

}