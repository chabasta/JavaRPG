package Model;

import Controller.MainController;
import Controller.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Student extends Person{
    private Image               player;
    ImageIcon icon_player_down = new ImageIcon("src/main/java/Sources/player_down.png");
    ImageIcon icon_player_right = new ImageIcon("src/main/java/Sources/player_right.png");
    ImageIcon icon_player_left = new ImageIcon("src/main/java/Sources/player_left.png");
    ImageIcon icon_player_up = new ImageIcon("src/main/java/Sources/player_up.png");
    private final Map           map;
    private final BufferedImage background;
    private String              item_hold;
    private String[]            inventory = {"sword", ""};
    private int                 hero_way;


    public Student(int position_x, int position_y, int damage, int hp, Map map, BufferedImage image, int level_data) {
        super(position_x, position_y, damage, hp);

        this.player = icon_player_down.getImage();
        this.map = map;
        this.background = image;
        this.item_hold = inventory[0];
        if (level_data == 1) this.inventory[1] = "key";
    }
    private static Logger logger = Logger.getLogger(Student.class.getName());

    //set item holding
    public void item_picked(int a){
        item_hold = inventory[a];
    }

    public void find_key(){
        inventory[1] = "key";
    }
    /*if player holding sword - try to attack (check for a collision with the enemy) if player holding sword - try to open the door
    * @ArrayList<Employer> employers - array of enemyes
    * */
    public void use(ArrayList<Employer> employers, Game_over door, MainController mainController){
        if (item_hold == "sword"){
            for (Employer e: employers){
                switch(hero_way){
                    case 1:
                        if (super.getPosition_y()+15 >= e.getPosition_y()-15   && super.getPosition_y()+15 <= e.getPosition_y()+46  ){
                            if (e.getPosition_x()+31 <= super.getPosition_x() && e.getPosition_x()+41 >= super.getPosition_x()){
                                e.setHp(e.getHp() - super.getDamage());
                            }
                        }
                    case 2:
                            if (super.getPosition_y()+15 >= e.getPosition_y()-15   && super.getPosition_y()+15 <= e.getPosition_y()+46  ){
                                if (e.getPosition_x() >= super.getPosition_x()+31 && e.getPosition_x() <= super.getPosition_x()+41) {
                                    e.setHp(e.getHp() - super.getDamage());
                                }
                            }
                    case 3:
                            if (super.getPosition_x()+15 >= e.getPosition_x()-15 && super.getPosition_x()+15 <= e.getPosition_x()+46){
                                if (e.getPosition_y()+31 <= super.getPosition_y() && e.getPosition_y()+31 >= super.getPosition_y()-10){
                                    e.setHp(e.getHp() - super.getDamage());
                                }
                            }
                    case 4:
                        if (super.getPosition_x()+15 >= e.getPosition_x()-15 && super.getPosition_x()+15 <= e.getPosition_x()+46) {
                            if (e.getPosition_y() >= super.getPosition_y()+31 && e.getPosition_y() <= super.getPosition_y()+41) {
                                e.setHp(e.getHp() - super.getDamage());
                            }
                        }
                }
            }
        }
        if (item_hold == "key"){
            if (door.try_open(this)){
                mainController.setWin_game(true);
                logger.info("Game over, you win");
            }
        }
    }

    public String[] getInventory() {
        return inventory;
    }
    /* check pixels on color and move if color not black*/
    public void move_left(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 1)) {
            this.player = icon_player_left.getImage();
            super.setPosition_x(x-4);
            this.hero_way = 1;
        }
    }
    /* check pixels on color and move if color not black*/
    public void move_right(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 2)) {
            this.player = icon_player_right.getImage();
            super.setPosition_x(x + 4);
            this.hero_way = 2;
        }
    }
    /* check pixels on color and move if color not black*/
    public void move_up(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 3)) {
            this.player = icon_player_up.getImage();
            super.setPosition_y(y - 4);
            this.hero_way = 3;
        }
    }
    /* check pixels on color and move if color not black*/
    public void move_down(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 4)) {
            this.player = icon_player_down.getImage();
            super.setPosition_y(y + 4);
            this.hero_way = 4;
        }
    }


    public Image getPlayerImage() { return player; }

    public String getItem_hold() {
        return item_hold;
    }

    public void setItem_hold(String item_peacked) {
        this.item_hold = item_peacked;
    }

}
