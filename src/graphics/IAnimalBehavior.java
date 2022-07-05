package graphics;

/**
 * Interface that allow we to get information about the animal behavior
 * @author Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 *
 */
public interface IAnimalBehavior {
    public String getAnimalName();
    public int getSize();
    public void eatInc();
    public int getEatCount();
    public boolean getChanges ();
    public void setChanges (boolean state);
    public void setSuspended();
    public void setResumed();
}