package View;

import Controller.MainController;
import Controller.Map;
import Controller.OnKeyPressed;
import Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GamePanel extends JPanel{

    BufferedImage   image = ImageIO.read(new File("src/main/java/Sources/background.png "));
    BufferedImage   win =   ImageIO.read(new File("src/main/java/Sources/win.jpg"));
    BufferedImage   lose =  ImageIO.read(new File("src/main/java/Sources/lose.png"));
    FileReader      level = new FileReader("src/main/java/Levels/level1.txt");

    MainController  controller;
    Student         student;
    Game_over       door;
    Key             key;
    ArrayList<Employer> employers = new ArrayList();
    Map             map;
    Heal_item       heal_item;
    Damage_item     damage_item;
    JLabel          label;

    public GamePanel(Dimension screen) throws IOException, AWTException {
        Scanner scan = new Scanner(level);
        int i = 0;
        int[] level_data = new int[6];
        while(scan.hasNextLine()){
            level_data[i] = Integer.parseInt(scan.nextLine());
            i++;
        }

        map             =   new Map();
        student         =   new Student(64,64, level_data[0], level_data[1], map, image, level_data[2]);

        if (level_data[2] == 1){
            key = new Key(true);
        }else {
            key = new Key(false);
        }

        label           =   new JLabel();
        add(label);

        for (int a = 0; a < level_data[3]; a++){
            employers.add(new Employer(512+(a*50), 160, level_data[4],level_data[5], map, image));
        }

        heal_item       =   new Heal_item( false);
        damage_item     =   new Damage_item(false);
        door            =   new Game_over(64, 160);
        controller      =   new MainController(this, student, employers, image, map, heal_item, damage_item,key, door,screen);
//        setBackground(Color.white);
        addKeyListener(new OnKeyPressed(controller));
        setFocusable(true);

    }



    public void drawElements() {
        label.setText("Health: " + student.getHp() + "Damage: " + student.getDamage() + "   Inventory: 1) " + student.getInventory()[0] + " 2) " + student.getInventory()[1] + " Holden item: " + student.getItem_hold());
        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (controller.isInGame() && !controller.isWin_game()) {
            g.drawImage(image, 0, 0, this);
            g.drawImage(door.getDoor(), door.getX(), door.getY(), this);
            g.drawImage(student.getPlayerImage(), student.getPosition_x(), student.getPosition_y(), this);
            if (controller.getEmployers().isEmpty() && !key.isPicked_up()) {
                g.drawImage(key.getImage(), key.getX(), key.getY(), this);
            }
            for (Employer e : controller.getEmployers()) {
                g.drawImage(e.getPlayerImage(), e.getPosition_x(), e.getPosition_y(), this);
            }
            if (!heal_item.isPicked_up()) {
                g.drawImage(heal_item.getImage(), heal_item.getX(), heal_item.getY(), this);
            }
            if (!damage_item.isPicked_up()) {
                g.drawImage(damage_item.getImage(), damage_item.getX(), damage_item.getY(), this);
            }
        }if (!controller.isInGame()){
            g.drawImage(lose, 0, 0, this);
        }if (controller.isWin_game()){
            g.drawImage(win, 0, 0, this);
        }

    }


}
