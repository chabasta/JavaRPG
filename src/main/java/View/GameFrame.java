package View;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
//import java.util.logging.Logger;

public class GameFrame extends JFrame {
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    GamePanel gamePanel;

    public GameFrame() throws IOException, AWTException {
        gamePanel = new GamePanel(screen);
        this.add(gamePanel);
        this.setTitle("Pass exams or die");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(640, 480);
        this.setLocation((screen.width - this.getWidth())/2, (screen.height - this.getHeight())/2); //centering the window
        this.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);

    }
}
