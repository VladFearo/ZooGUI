package diet;
import animals.Animal;
import animals.Lion;
import factory.AnimalFactory;
import food.EFoodType;
import food.IEdible;
import graphics.ZooPanel;

/**
 * A class organizing the carnivore diet for the animals implementing the canEat and eat methods
 * for the carnivore animals
 * @author  Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 * @see     IDiet
 */

public class Carnivore implements IDiet {
    /**
     * implementation of the canEat method checking if the food that the method gets is meat
     *
     * @param food
     *            - a type of food
     * @return boolean
     *            - true if the food is meat and false otherwise
     */
    @Override
    public boolean canEat(EFoodType food) {
        if (food == EFoodType.MEAT) {
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
        double weight = 0;
        EFoodType foodType  = food.getFoodtype();
        if(this.canEat(foodType))
            weight = (animal.getWeight()/10);
        return weight;
    }}