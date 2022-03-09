package Model;

import javax.swing.*;
import java.awt.*;

public class Game_over {
    private Image door;
    private int x;
    private int y;

    public Game_over(int x, int y){
        ImageIcon icon_door = new ImageIcon("src/main/java/Sources/door.png");
        this.door = icon_door.getImage();
        this.x = x;
        this.y = y;
    }

    /** try to open the door (game over)
     @param student - player **/
    public boolean try_open(Student student){
        if (student.getPosition_x() >= x - 10 && student.getPosition_x() <= x+41){
            if (student.getPosition_y() >= y - 10 && student.getPosition_y() <= y+41){
                return true;
            }
        }
        return false;
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

    public Image getDoor() {
        return door;
    }

}
