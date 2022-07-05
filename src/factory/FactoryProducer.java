package factory;

import animals.Animal;
import diet.Carnivore;
import diet.Herbivore;
import diet.IDiet;
import diet.Omnivore;
/*
 * factroy of factory's  to produce animals
 */

public class FactoryProducer {



    public static AnimalFactory getFactory(String st) {


        if (st.equalsIgnoreCase("Carnivore"))
            return new CarnivoreFactory();

        else if (st.equalsIgnoreCase("Herbivore"))
            return new HerbivoreFactory();

        else if (st.equalsIgnoreCase("Omnivore"))
            return new OmnivoreFactory();

        return null;
    }

}