package graphics;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import animals.Animal;
import animals.Bear;
import animals.Elephant;
import animals.Giraffe;
import animals.Lion;
import animals.Turtle;
import diet.Carnivore;
import diet.IDiet;
import factory.AnimalFactory;
import factory.FactoryProducer;

/**
 * Class that the main frame of the zoo.
 * @author Vladislav Shevtsov id: 322162553; Omer Halfon id: 315429951
 *
 */
public class ZooFrame extends JFrame implements ActionListener {

    private JMenuBar menubar;
    private JMenu file;
    private JMenu background;
    private JMenu help;
    private JMenuItem image;
    private JMenuItem exit;
    private JMenuItem green;
    private JMenuItem none;
    private JMenuItem help_item;
    private JButton add_animal;
    private JButton sleep;
    private JButton wake_up;
    private JButton clear;
    private JButton food;
    private JButton info;
    private JButton exit_button;
    private JButton change_color;
    private JButton save_state;
    private JButton restore_state;
    private ImageIcon icon;
    private ZooPanel zoopanel;
    private JComboBox<String> animals_box;
    private String[] animals_string;
    private static final int LETTUCE = 1;
    private static final int CABBAGE = 2;
    private static final int MEAT = 3;
    private static final int DELETE = 4;

    public ZooFrame() {
        super("Zoo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.menubar = new JMenuBar();
        this.setJMenuBar(this.menubar);
        zoopanel = ZooPanel.getInstance();
        zoopanel.startPanelThread();
        icon =new ImageIcon("Mpicture.png");

        this.file = new JMenu("File");
        this.menubar.add(this.file);
        this.exit = new JMenuItem("Exit");
        this.exit.addActionListener(this);
        this.file.add(exit);


        this.background = new JMenu("Background");
        this.menubar.add(this.background);
        this.image = new JMenuItem("Image");
        this.background.add(this.image);
        this.image.addActionListener(this);

        this.green = new JMenuItem("Green");
        this.green.addActionListener(this);
        this.background.add(green);
        this.none = new JMenuItem("None");
        this.background.add(none);
        this.none.addActionListener(this);

        this.help = new JMenu("Help");
        this.menubar.add(this.help);
        this.help_item = new JMenuItem("Help");
        this.help.add(help_item);
        this.help_item.addActionListener(this);


        JPanel southPanel = new  JPanel();
        southPanel.setLayout(new FlowLayout());

        this.add_animal = new JButton("Add animal");
        southPanel.add(add_animal);
        this.add_animal.addActionListener(this);
        this.change_color = new JButton("Change color");
        this.change_color.addActionListener(this);
        southPanel.add(change_color);
        this.sleep = new JButton("Sleep");
        southPanel.add(this.sleep);
        this.sleep.addActionListener(this);
        this.wake_up = new JButton("Wake up");
        southPanel.add(this.wake_up);
        this.wake_up.addActionListener(this);
        southPanel.add(clear = new JButton("Clear"));
        clear.addActionListener(this);
        southPanel.add(this.food = new JButton("Food"));
        food.addActionListener(this);
        southPanel.add(info = new JButton("Info"));
        info.addActionListener(this);

        southPanel.add(save_state = new JButton("save state"));
        save_state.addActionListener(this);

        southPanel.add(restore_state = new JButton("restore state"));
        restore_state.addActionListener(this);

        southPanel.add(exit_button = new JButton("Exit"));
        exit_button.addActionListener(this);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(zoopanel);

        this.setVisible(true);
        this.pack();
        this.setSize(880,680);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.exit) {
            System.exit(0);
        }

        if (e.getSource() == this.image) {
            zoopanel.setBackground(0);
            zoopanel.setVisible(true);


        }
        if(e.getSource() == this.green) {
            zoopanel.setBackground(1);
            zoopanel.setVisible(true);

        }

        if(e.getSource() == this.none) {
            zoopanel.setBackground(2);
            zoopanel.setVisible(true);
        }

        if (e.getSource() ==this.help_item) {
            JOptionPane.showMessageDialog(null, "Home Work 2 GUI",
                    "Message", JOptionPane.INFORMATION_MESSAGE, icon);
        }


        if (e.getSource() ==this.add_animal) {

            JDialog typeDialog = new JDialog(this , "Type of animals", true);
            JButton carnivore = new JButton ("Carnivore");
            JButton omnivore = new JButton ("Omnivore");
            JButton herbivore = new JButton ("Herbivore");
            carnivore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) {

                    zoopanel.setAnimalType(1);
                    typeDialog.dispose();

                }
            });

            omnivore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) {

                    zoopanel.setAnimalType(2);
                    typeDialog.dispose();


                }
            });
            herbivore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) {
                    zoopanel.setAnimalType(3);
                    typeDialog.dispose();


                }
            });

            JLabel typeLabel = new JLabel("Please choose type");
            typeLabel.setIcon(icon);
            typeLabel.setIconTextGap(30);
            JPanel typepanel = new JPanel();
            JPanel typebuttonPanel = new JPanel();
            typebuttonPanel.setLayout(new FlowLayout());
            typebuttonPanel.add(carnivore);
            typebuttonPanel.add(omnivore);
            typebuttonPanel.add(herbivore);
            typepanel.setLayout(new BorderLayout());
            typepanel.add(typebuttonPanel, BorderLayout.SOUTH);
            typepanel.add(typeLabel,BorderLayout.CENTER);
            typeDialog.add(typepanel);
            typeDialog.pack();
            typeDialog.setVisible(true);
            this.setVisible(true);

            if(zoopanel.getAnimalType() != null)
                new AddAnimalDialog(zoopanel, this);

            zoopanel.setAnimalType(4);

        }

        if(e.getSource() == this.change_color) {
            new ChangeAnimal(this , zoopanel);



        }


        if (e.getSource() ==this.sleep) {
            zoopanel.sleep();

        }

        if (e.getSource() ==this.wake_up) {
            zoopanel.wakeUp();

        }

        if(e.getSource() == clear) {
            zoopanel.DeleteAllAnimals();
            zoopanel.setPlant(DELETE);
            zoopanel.repaint();
        }

        if(e.getSource() == this.food) {

            JDialog foodDialog = new JDialog(this , "Food for animals", true);
            JButton Lettuce = new JButton ("Lettuce");
            Lettuce.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e1) {

                    if(zoopanel != null) {

                        zoopanel.setPlant(LETTUCE);
                        foodDialog.dispose();

                    }
                }
            });

            JButton Cabbage = new JButton ("Cabbage");
            Cabbage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) {

                    if(zoopanel != null) {
                        zoopanel.setPlant(CABBAGE);
                        foodDialog.dispose();
                    }
                }
            });
            JButton Meat = new JButton ("Meat");
            Meat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) {

                    if(zoopanel != null) {
                        zoopanel.setPlant(MEAT);
                        foodDialog.dispose();
                    }
                }
            });
            JLabel foodLabel = new JLabel("Please choose food");
            foodLabel.setIcon(icon);
            foodLabel.setIconTextGap(30);
            JPanel panel = new JPanel();
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(Lettuce);
            buttonPanel.add(Cabbage);
            buttonPanel.add(Meat);
            panel.setLayout(new BorderLayout());
            panel.add(buttonPanel, BorderLayout.SOUTH);
            panel.add(foodLabel,BorderLayout.CENTER);
            foodDialog.add(panel);
            foodDialog.pack();
            foodDialog.setVisible(true);
            this.setVisible(true);

        }

        if(e.getSource() == info) {
            JDialog info_dialog = new JDialog(this,"Zoo info",true);
            AnimalTable at = new AnimalTable(zoopanel);
            JTable table = new JTable(at);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setPreferredScrollableViewportSize(new Dimension(500, 70));
            table.setFillsViewportHeight(true);
            info_dialog.add(new JScrollPane(table));

            info_dialog.pack();
            info_dialog.setVisible(true);

        }

        if(e.getSource() == save_state) {
            try {
                zoopanel.saveState();
            } catch (CloneNotSupportedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        if(e.getSource() == restore_state) {
            zoopanel.restoreState();
        }



        if(e.getSource() == exit_button) {
            zoopanel.setExit();
            System.exit(0);

        }


    }


    public static void main(String[] args) {


        new ZooFrame();


    }





}