package Model;

import javax.swing.*;
import java.awt.*;

public class Heal_item extends Item{
    int x;
    int y;
    Image image;
    ImageIcon i_heal = new ImageIcon("src/main/java/Sources/heal.png");

    public Heal_item(boolean picked_up) {
        super(picked_up);
        this.x = 256;
        this.y = 256;
        this.image = i_heal.getImage();
    }
    /** boost player hp **/
    public void boost_hp(Student student){
        student.setHp(student.getHp() + 20);
    }

    public boolean picked_up(){
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
