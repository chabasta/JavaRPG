package Model;

import javax.swing.*;
import java.awt.*;

public class Damage_item extends Item{
    int x;
    int y;
    Image image;
    ImageIcon i_damage = new ImageIcon("src/main/java/Sources/damage.png");

    public Damage_item(boolean picked_up) {
        super(picked_up);
        this.x = 512;
        this.y = 256;
        this.image = i_damage.getImage();
    }
    /** boost damage of hero **/
    public void boost_damage(Student student) {
        student.setDamage(student.getDamage() + 10);
    }

    public boolean picked_up() {
        return super.isPicked_up();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
