package factory;

import animals.Animal;
import animals.Lion;
import graphics.ZooPanel;
/*
 * Factory of carnivorous
 */
public class CarnivoreFactory implements AnimalFactory {

    @Override
    public Animal createAnimal(String animalType ,float weight , String color,ZooPanel panel) {

        if(animalType.equalsIgnoreCase("Lion"))
            return new Lion("default", weight, color, panel);

        return null;
    }



}