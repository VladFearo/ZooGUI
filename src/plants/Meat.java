package plants;

import food.EFoodType;
import graphics.ZooPanel;
import utilities.MessageUtility;



public class Meat extends Plant
{

    public static Meat meat = null;

    public static Meat getInstance(ZooPanel panel) {
        if(meat == null)
            meat = new Meat(panel);
        return  meat;
    }

    private Meat(ZooPanel pan)
    {
        super(pan);
        this.loadImages("meat.gif");
    }

    @Override
    public EFoodType getFoodtype() {
        //MessageUtility.logGetter(this.getClass().getSimpleName(), "getFoodType", EFoodType.MEAT);
        return EFoodType.MEAT;
    }
} //class Meat extends Plant