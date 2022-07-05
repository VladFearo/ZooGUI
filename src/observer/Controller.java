package observer;

import java.util.Observable;
import java.util.Observer;

import graphics.ZooPanel;
/*
 * class that control at the zoo
 */
@SuppressWarnings("deprecation")
public class Controller extends Thread implements Observer{

    private ZooPanel panel;
    public Controller(ZooPanel panel) {
        super(panel);
        this.panel = panel;
    }


    @Override
    public void run() {

        while(true)
        {
            synchronized(this)
            {
                try
                {
                    wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }


            }
            panel.manageZoo();
            panel.repaint();


        }
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        notify();
    }

}