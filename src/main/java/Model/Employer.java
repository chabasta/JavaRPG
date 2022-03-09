package Model;

import Controller.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Employer extends Person{
    private Image employer;
    ImageIcon icon_enemy_down = new ImageIcon("src/main/java/Sources/enemy_down.png");
    ImageIcon icon_enemy_right = new ImageIcon("src/main/java/Sources/enemy_right.png");
    ImageIcon icon_enemy_left = new ImageIcon("src/main/java/Sources/enemy_left.png");
    ImageIcon icon_enemy_up = new ImageIcon("src/main/java/Sources/enemy_up.png");
    private final Map map;
    private final BufferedImage background;
    int counter = 0;
    int way;
    Random random = new Random();

    public Employer(int position_x, int position_y, int damage, int hp, Map map, BufferedImage image) {
        super(position_x, position_y, damage, hp);
        this.employer = icon_enemy_down.getImage();
        this.map = map;
        this.background = image;
    }
    /** generate random way for enemyes for the next 15 steps **/
    public int generate_random_way(){
        counter++;

        if (counter == 15){
            way = random.nextInt(4);
            counter = 0;
        }
        return way;
    }

    public Image getPlayerImage() { return employer; }

    /** depending on the direction, reads out if there is a collision with the player's coordinates, if there it is - attack
     @param way_move - way of move enemy
     @param student - player
     **/
    public boolean try_attack(Student student, int way_move){

        switch (way_move){
            case 1:
                if (student.getPosition_x()+32 == super.getPosition_x()){
                    int y = student.getPosition_y();
                    for (int a = 0; a < 32; a++){
                        y++;
                        if (y >= super.getPosition_y() && y <= super.getPosition_y()+31){
                            return true;
                        }
                    }
                }else return false;
            case 2:
                if (student.getPosition_x()-1 == super.getPosition_x()+31){
                    int y = student.getPosition_y();
                    for (int a = 0; a < 32; a++){
                        y++;
                        if (y >= super.getPosition_y() && y <= super.getPosition_y()+31){
                            return true;
                        }
                    }
                }else return false;
            case 3:
                if (student.getPosition_y()+32 == super.getPosition_y()){
                    int x = student.getPosition_x();
                    for (int a = 0; a < 32; a++) {
                        x++;
                        if (x >= super.getPosition_x() && x <= super.getPosition_x() + 31) {
                            return true;
                        }
                    }
                }else return false;
            case 4:
                if (student.getPosition_y()-1 == super.getPosition_y()+31){
                    int x = student.getPosition_x();
                    for (int a = 0; a < 32; a++) {
                        x++;
                        if (x >= super.getPosition_x() && x <= super.getPosition_x() + 31) {
                            return true;
                        }
                    }

                }else return false;
            default:
                return false;
        }

    }
    /** check pixels on color and move if color not black **/
    public void move_left(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 1)) {
            this.employer = icon_enemy_left.getImage();
            super.setPosition_x(x-4);
        }
    }
    /** check pixels on color and move if color not black **/
    public void move_right(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 2)) {
            this.employer = icon_enemy_right.getImage();
            super.setPosition_x(x + 4);
        }
    }
    /** check pixels on color and move if color not black **/
    public void move_up(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 3)) {
            this.employer = icon_enemy_up.getImage();
            super.setPosition_y(y - 4);
        }
    }
    /** check pixels on color and move if color not black **/
    public void move_down(){
        int x = super.getPosition_x();
        int y = super.getPosition_y();
        if (map.CheckPixelColor(background, x, y, 4)) {
            this.employer = icon_enemy_down.getImage();
            super.setPosition_y(y + 4);
        }
    }

}
