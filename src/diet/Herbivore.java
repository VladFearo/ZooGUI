package diet;

import animals.Animal;
import animals.Elephant;
import animals.Giraffe;
import animals.Lion;
import animals.Turtle;
import factory.AnimalFactory;
import food.EFoodType;
import food.IEdible;
import graphics.ZooPanel;
/**
 * A class organizing the herbivore diet for the animals implementing the canEat and eat methods
 * for the herbivore animals
 * @author  Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 * @see     IDiet
 */
public class Herbivore implements IDiet{
    /**
     * implementation of the canEat method checking if the food that the method gets is vegetables
     *
     * @param food
     *            - a type of food
     * @return boolean
     *            - true if the food is vegetables and false otherwise
     */
    @Override
    public boolean canEat(EFoodType food) {
        if (food == EFoodType.VEGETABLE) {
            return true;
        }

        return false;
    }
    /**
     * implementation of the eat method eating the food and gaining weight based on the diet
     *
     * @param animal
     *            - the animal that eats
     * @param food
     *            - the food the animal eats
     * @return weight
     *            - returns the weight gained from eating
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        // add 7% to weight when eating vegetable
        double W = 0;
        if(this.canEat(food.getFoodtype()))
            W = (animal.getWeight()*0.07);

        return W;
    }




}