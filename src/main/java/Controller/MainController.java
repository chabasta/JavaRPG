package Controller;

import Model.*;
import View.GamePanel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MainController extends JPanel implements ActionListener {
    Student             student;
    ArrayList<Employer> employers;
    BufferedImage       background;
    Map                 map;
    Dimension           screen;
    Game_over           door;
    Heal_item           item;
    Damage_item         item2;
    Key                 key;

    private GamePanel   gamePanel;
    private Timer       timer;
    private boolean     left = false;
    private boolean     right = false;
    private boolean     up = false;
    private boolean     down = false;
    private int         enemy_way;
    private boolean     win_game = false;
    private boolean     inGame = true;



    public MainController(GamePanel gp, Student student, ArrayList<Employer> employers, BufferedImage image, Map map, Heal_item item, Damage_item item2, Key key, Game_over door, Dimension screen) throws AWTException {
        this.background = image;
        this.gamePanel = gp;
        this.student = student;
        this.map = map;
        this.screen = screen;
        this.item = item;
        this.item2 = item2;
        this.employers = employers;
        this.door = door;
        this.key = key;
        initGame();

    }

    private static Logger logger = Logger.getLogger(MainController.class.getName());

    public void initGame() throws AWTException {
        timer = new Timer(50, this);
        timer.start();
    }
    /**check if item was picked up
    taking the middle of hero coord x and compare it with coord x +- half size item
    than do the same things whith coord y
     **/
    public void check_item_picked_up() {
        if (!item.picked_up()) {
            if (student.getPosition_x()+15 >= item.getX() && student.getPosition_x()+15 <= item.getX() + 31) {
                if (student.getPosition_y()+15 <= item.getY() + 31 && student.getPosition_y()+15 >= item.getY()) {
                    item.setPicked_up(true);
                    logger.info("Item for heal was picked up");
                    item.boost_hp(student);
                }
            }

        }
        if (!item2.picked_up()) {
            if (student.getPosition_x() + 15 >= item2.getX() && student.getPosition_x() + 15 <= item2.getX() + 31) {
                if (student.getPosition_y() + 15 <= item2.getY() + 31 && student.getPosition_y() + 15 >= item2.getY()) {
                    item2.setPicked_up(true);
                    logger.info("Item for damage was picked up");
                    item2.boost_damage(student);
                }
            }
        }
        if (employers.isEmpty()) {
            if (!key.picked_up()) {
                if (student.getPosition_x()+15 >= key.getX() && student.getPosition_x()+15 <= key.getX() + 31) {
                    if (student.getPosition_y()+15 <= key.getY() + 31 && student.getPosition_y()+15 >= key.getY()) {
                        key.setPicked_up(true);
                        logger.info("Key was picked up");
                        student.find_key();
                    }

                }
            }
        }
    }
    /** do move hero, update array of enemyes, check if items was picked up, check if the game was end **/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (student.getHp() <= 0){inGame = false;}
        if (inGame) {
            move();
            updating_list_enemy();
            for (Employer employer: employers) {
                move_enemy();
            }
            check_item_picked_up();
        }
        gamePanel.drawElements();
        if (!inGame || win_game){
            logger.info("Game over");
            timer.stop();
        }
    }

    public boolean getLeft(){ return left; }

    public boolean getRight(){ return right; }

    public boolean getUp(){ return up; }

    public boolean getDown(){ return down; }

    public void setLeft(boolean left) { this.left = left; }

    public void setRight(boolean right) { this.right = right; }

    public void setUp(boolean up) { this.up = up; }

    public void setDown(boolean down) { this.down = down; }

    public int getEnemy_way() { return enemy_way; }

    public void setEnemy_way(int enemy_way) { this.enemy_way = enemy_way; }

    public boolean isWin_game() {
        return win_game;
    }

    public void setWin_game(boolean win_game) {
        this.win_game = win_game;
    }

    public boolean isInGame() {
        return inGame;
    }

    public ArrayList<Employer> getEmployers() {
        return employers;
    }

    public void save_game() throws IOException {
        FileWriter file = new FileWriter("src/main/java/Levels/save_game.txt");
        file.write("Student_x " + student.getPosition_x()
                    +"\nStudent_y " + student.getPosition_y()
                    +"\nCount_enemy " + employers.size()
                    +"\nHeal_item " + item.isPicked_up()
                    +"\nDamage_item " + item2.isPicked_up()
                    +"\nKey " + key.isPicked_up());
        file.close();
        logger.info("The game was saved");
    }

    /** check if enemy is dead, and remove him from the array if he is **/
    public void updating_list_enemy(){
        if (!employers.isEmpty()) {
            for (int a = 0; a < employers.size(); a++){
                if (employers.get(a).getHp() <= 0) {
                    employers.remove(employers.get(a));
                    logger.info("enemy was killed");
                }
            }
        }
    }

    public void player_interaction(){
        student.use(employers, door, this);
    }
    public void set_item_hold(int a){
        student.item_picked(a);
    }

    /** takes the direction of movement hero and do the move **/
    public void move() {
        if (left){
            student.move_left();
        }
        if (right){
            student.move_right();
        }
        if (up){
            student.move_up();
        }
        if (down){
            student.move_down();
        }
    }
    /** takes the direction of movement enemy and do the move, during this, check if exist the colisions with coordinations of hero and if they are - attack hero **/
    public  void  move_enemy(){
        for (Employer employer: employers) {
            int i = employer.generate_random_way();
            if (i == 0) {
                employer.move_left();
                if (employer.try_attack(student, 1)){
                    student.setHp(student.getHp() - employer.getDamage());
                }
            }
            if (i == 1) {
                employer.move_right();
                if (employer.try_attack(student, 2)){
                    student.setHp(student.getHp() - employer.getDamage());
                }
            }
            if (i == 2) {
                employer.move_up();
                if (employer.try_attack(student, 3)){
                    student.setHp(student.getHp() - employer.getDamage());
                }
            }
            if (i == 3) {
                employer.move_down();
                if (employer.try_attack(student, 4)){
                    student.setHp(student.getHp() - employer.getDamage());
                }
            }
        }
    }
}

