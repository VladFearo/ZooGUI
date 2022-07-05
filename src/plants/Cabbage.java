package plants;

import graphics.ZooPanel;
import utilities.MessageUtility;

/**
 * @author baroh
 *
 */
public class Cabbage extends Plant {

    public static Cabbage cabbage = null;

    public static Cabbage getInstance(ZooPanel panel) {
        if(cabbage == null)
            cabbage = new Cabbage(panel);
        return  cabbage;
    }

    private Cabbage(ZooPanel zoopanel) {
        super(zoopanel);
        //MessageUtility.logConstractor("Cabbage", "Cabbage");
    }

}