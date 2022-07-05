package graphics;


import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import animals.*;
import diet.IDiet;
import factory.AnimalFactory;
import factory.FactoryProducer;

/**
 * Class that add animals to the zoo
 *
 * @author   Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 * @see     ZooFrame
 */


public class AddAnimalDialog extends JDialog  implements ActionListener {

    private JLabel label;
    private JComboBox<String> box;
    private JLabel size_label;
    private JLabel horizon_speed_label;
    private JLabel vertical_speed_label;
    private  JTextField size_field;
    private  JTextField horizon_speed_field;
    private  JTextField vertical_speed_field;
    private JPanel displayPanel;
    private JPanel display_type;
    private JComboBox<String> animal_color;
    private JButton create;
    private ZooPanel zoopanel;
    private String colors[]= {"Natural","Red","Blue"};
    private String[] animals  ;
    private String animalType = null;




    public AddAnimalDialog(ZooPanel zoopanel, ZooFrame Zoo ) {
        super(Zoo, "Add animal", true);
        displayPanel = new JPanel();
        this.animalType = zoopanel.getAnimalType();
        this.zoopanel = zoopanel;
        create = new JButton("Create");
        create.addActionListener(this);

        if(this.animalType == "Carnivore")
            animals = new String[]{"Lion"};

        else if(this.animalType == "Omnivore")
            animals = new String[]{"Bear"};

        else
            animals = new String[]{"Elephant","Giraffe","Turtle"};



        box = new JComboBox(animals);
        box.addActionListener(this);
        animal_color = new JComboBox(colors);
        animal_color.addActionListener(this);
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
        ImageIcon icon =new ImageIcon("Mpicture.png");


        this.label = new JLabel();
        this.label = new JLabel("Select animal");
        this.label.setVerticalTextPosition(JLabel.TOP);
        this.label.setHorizontalAlignment(JLabel.LEFT);
        displayPanel.add(label);
        displayPanel.add(box);
        displayPanel.add(size_label = new JLabel("Size:"));
        displayPanel.add(size_field= new JTextField());
        displayPanel.add(horizon_speed_label = new JLabel("Horizon speed:"));
        displayPanel.add(horizon_speed_field= new JTextField());
        displayPanel.add(vertical_speed_label = new JLabel("Vertical speed:"));
        displayPanel.add(vertical_speed_field= new JTextField());
        displayPanel.add(animal_color);

        create.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    String animal_choosen = box.getItemAt(box.getSelectedIndex());
                    int size = Integer.parseInt(size_field.getText());
                    int horizon_speed = Integer.parseInt(horizon_speed_field.getText());
                    int vertical_speed = Integer.parseInt(vertical_speed_field.getText());
                    String color = animal_color.getItemAt(animal_color.getSelectedIndex());
                    String name = "default";
                    float weight = 0;



                    if(50 > size || size > 300 || horizon_speed < 1 || horizon_speed > 10
                            ||vertical_speed < 1 || vertical_speed > 10 )
                        throw new NumberFormatException();

                    AnimalFactory animalfac = FactoryProducer.getFactory(zoopanel.getAnimalType());


                    switch(animal_choosen) {
                        case "Lion":
                            weight = (float) (size * 0.8);
                            break;

                        case "Bear":
                            weight = (float) (size * 1.5);
                            break;

                        case "Giraffe":
                            weight = (float) (size * 2.2);
                            break;


                        case "Elephant":
                            weight = size * 10;
                            break;


                        case "Turtle":
                            weight = (float) (size * 0.5);
                            break;


                    }
                    Animal animal = animalfac.createAnimal(animal_choosen,weight,color,zoopanel);
                    animal.setHorSpeed(horizon_speed);
                    animal.setVerSpeed(vertical_speed);
                    animal.setSize(size);
                    zoopanel.addAnimallist(animal);

                }
                catch(NumberFormatException err) {
                    JOptionPane.showMessageDialog(null, "Wrong input entered",
                            "Message", JOptionPane.ERROR_MESSAGE, icon);
                }




                dispose();

            }});

        displayPanel.add(create);
        this.add(displayPanel);
        this.pack();
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}//class