package diet;

import animals.Animal;
import animals.Bear;
import animals.Lion;
import factory.AnimalFactory;
import food.EFoodType;
import food.IEdible;
import graphics.ZooPanel;
/**
 * A class organizing the omnivore diet for the animals implementing the canEat and eat methods
 * for the omnivore animals
 * @author  Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 * @see     IDiet
 */
public class Omnivore implements IDiet{
    /**
     * implementation of the canEat method checking if the food that the method gets is vegetables
     * or meat
     * @param food
     *            - a type of food
     * @return boolean
     *            - true if the food is vegetables or meat and false otherwise
     */
    @Override
    public boolean canEat(EFoodType food) {
        if (food == EFoodType.MEAT || food == EFoodType.VEGETABLE) {
            return true;
        }
        return false;
    }
    /**
     * implementation of the eat method eating the food and gaining weight based on the diet
     * 10% for meat and 7% for vegetables
     * @param animal
     *            - the animal that eats
     * @param food
     *            - the food the animal eats
     * @return weight
     *            - returns the weight gained from eating
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        // add 10% to weight when eating meat
        // add 7% to weight when eating vegetable
        double W = 0;
        EFoodType foodType  = food.getFoodtype();
        if(this.canEat(foodType)) {
            if(foodType == EFoodType.MEAT) {
                W = (animal.getWeight()/10);
            }
            if(foodType == EFoodType.VEGETABLE) {
                W = (animal.getWeight()*0.07);
            }
        }

        return W;
    }





}