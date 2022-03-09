package Model;

import javax.swing.*;
import java.awt.*;

public class Key extends Item{
    int x;
    int y;
    Image image;
    ImageIcon i_key = new ImageIcon("src/main/java/Sources/key.png");

    public Key(boolean picked_up) {
        super(picked_up);
        this.x = 256;
        this.y = 200;
        this.image = i_key.getImage();
    }

    public boolean picked_up(){
        return super.isPicked_up();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
}
