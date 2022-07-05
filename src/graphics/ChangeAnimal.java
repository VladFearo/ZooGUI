package graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import animals.Bear;
import animals.Elephant;
import animals.Giraffe;
import animals.Lion;
import animals.Turtle;
import decorator.colorDecorator;
import mobility.Point;
import zoo.ZooActions;

/**
 * Class to move animals in the zoo
 * @author   Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 * @see     ZooFrame
 */
public class ChangeAnimal extends JDialog implements ActionListener {

    private Point location;
    private JComboBox<String> animals_box;
    private JComboBox<String> color_box;

    private String[] animals_string;
    private String[] color_string = {"Natural","Red","Blue"};
    private JLabel labelX;
    private JTextField x_field ;
    private JTextField y_field ;
    private JLabel labelY;
    private JPanel displayPanel;
    private JButton change_color;



    public ChangeAnimal(ZooFrame Zoo,ZooPanel zoopanel) {
        super(Zoo, "Change color", true);
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
        change_color = new JButton("OK");
        change_color.addActionListener(this);


        int size = zoopanel.getAnimals().size() ;
        int counter = 0;
        animals_string = new String[size];
        int LionCounter = 0;
        int BearCounter = 0;
        int ElephantCounter = 0;
        int GiraffeCounter = 0;
        int TurtleCounter = 0;




        for(int i = 0 ; i < size; i++) {

            if (zoopanel.getAnimals().get(i) == null)
                break;
            else {
                if (zoopanel.getAnimals().get(i) instanceof Lion ) {
                    LionCounter++;
                    animals_string[i] = zoopanel.getAnimals().get(i).getClass().getSimpleName()+" "+
                            LionCounter+" ("+zoopanel.getAnimals().get(i).getColor()+")";
                }
                if (zoopanel.getAnimals().get(i) instanceof Bear ) {
                    BearCounter++;
                    animals_string[i] =  zoopanel.getAnimals().get(i).getClass().getSimpleName()+" "+
                            BearCounter+" ("+zoopanel.getAnimals().get(i).getColor()+")";
                }
                if (zoopanel.getAnimals().get(i) instanceof Elephant ) {
                    ElephantCounter++;
                    animals_string[i] =  zoopanel.getAnimals().get(i).getClass().getSimpleName()+" "+
                            ElephantCounter+" ("+zoopanel.getAnimals().get(i).getColor()+")";
                }
                if (zoopanel.getAnimals().get(i) instanceof Giraffe ) {
                    GiraffeCounter++;
                    animals_string[i] =  zoopanel.getAnimals().get(i).getClass().getSimpleName()+" "+
                            GiraffeCounter+" ("+zoopanel.getAnimals().get(i).getColor()+")";
                }
                if (zoopanel.getAnimals().get(i) instanceof Turtle ) {
                    TurtleCounter++;
                    animals_string[i] =  zoopanel.getAnimals().get(i).getClass().getSimpleName()+" "+
                            TurtleCounter+" ("+zoopanel.getAnimals().get(i).getColor()+")";
                }
            }

        }
        ImageIcon icon =new ImageIcon("Mpicture.png");
        animals_box = new JComboBox(animals_string);
        animals_box.addActionListener(this);
        displayPanel.add(animals_box);

        color_box = new JComboBox(color_string);
        animals_box.addActionListener(this);
        displayPanel.add(color_box);



        change_color.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                synchronized(this) {
                    int array_index = animals_box.getSelectedIndex();
                    String color_index = color_string[color_box.getSelectedIndex()];
                    colorDecorator change = new colorDecorator(zoopanel.getAnimals().get(array_index));
                    change.changeAnimal(color_index);
                }

                dispose();

            }
        });



        displayPanel.add(change_color);
        this.add(displayPanel);
        this.setSize(250,150);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }




}