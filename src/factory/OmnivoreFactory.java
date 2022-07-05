package factory;

import animals.Animal;
import animals.Bear;
import graphics.ZooPanel;
/*
 * facrotry of omnivore
 */
public class OmnivoreFactory implements AnimalFactory {

    @Override
    public Animal createAnimal(String animalType, float weight, String color, ZooPanel panel) {
        if(animalType.equalsIgnoreCase("Bear"))
            return new Bear("default",weight ,color ,panel);

        return null;
    }


}