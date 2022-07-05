package diet;
import animals.*;
import factory.AnimalFactory;
import  food.*;
import graphics.ZooPanel;
/**
 * An interface organizing the diet of the animals declaring the canEat and eat methods
 *
 * @author  Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 * @see     EFoodType
 */

public interface IDiet {
    /*
     * A declaration of the canEat method
     * */

    public boolean canEat(EFoodType food);//return true if the animal cat eat this prey
    /*
     * A declaration of the eat method
     * */
    public double eat(Animal animal, IEdible food); // check if is edible and calculate the weight


}