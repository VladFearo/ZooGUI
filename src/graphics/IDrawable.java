package graphics;

import java.awt.Graphics;

/**
 * Interface that draw the animals
 * @author Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 *
 */

public interface IDrawable {
    public final static String PICTURE_PATH = "C:\\Users\\user\\Desktop\\assignment2_pictures\\assignment2_pictures";
    public void loadImages(String nm);
    public void drawObject (Graphics g);
    public String getColor();

}