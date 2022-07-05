package plants;

import java.awt.Graphics;
import graphics.ZooPanel;
import utilities.MessageUtility;

/**
 * @author baroh
 *
 */
public class Lettuce extends Plant {

    public static Lettuce lettuce = null;

    public static Lettuce getInstance(ZooPanel panel) {
        if(lettuce == null)
            lettuce = new Lettuce(panel);
        return  lettuce;
    }

    private Lettuce(ZooPanel zoopanel) {
        super(zoopanel);
        //MessageUtility.logConstractor("Lettuce", "Lettuce");
    }

    @Override
    public String getColor() {
        // TODO Auto-generated method stub
        return null;
    }

}