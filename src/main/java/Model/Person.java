package Model;

import javax.swing.*;
import java.awt.*;

public class Person {

    private int position_x;
    private int position_y;
    private int damage;
    private int hp;


    public Person (int position_x, int position_y, int damage, int hp) {
        this.position_x = position_x;
        this.position_y = position_y;
        this.damage = damage;
        this.hp = hp;
    }

    public int getHp() { return hp; }

    public void setHp(int hp) { this.hp = hp; }

    public int getDamage() { return damage; }

    public void setDamage(int damage) { this.damage = damage; }

    public int getPosition_x() {
        return position_x;
    }

    public int getPosition_y() {
        return position_y;
    }

    public void setPosition_x(int position_x) {
        this.position_x = position_x;
    }

    public void setPosition_y(int position_y) {
        this.position_y = position_y;
    }

}
