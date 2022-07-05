package factory;

import animals.Animal;
import animals.Elephant;
import animals.Giraffe;
import animals.Turtle;
import graphics.ZooPanel;
/*
 * factory of herbivorous
 */
public class HerbivoreFactory implements AnimalFactory {

    @Override
    public Animal createAnimal(String animalType, float weight, String color, ZooPanel panel) {
        if(animalType.equalsIgnoreCase("Turtle")) {
            return new Turtle("default",weight ,color ,panel);
        }
        else if(animalType.equalsIgnoreCase("Elephant")) {
            return new Elephant("default",weight ,color ,panel);
        }

        else if(animalType.equalsIgnoreCase("Giraffe")) {
            return new Giraffe("default",weight ,color ,panel);
        }


        return null;
    }


}