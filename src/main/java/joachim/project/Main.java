package main.java.joachim.project;
import joachim.project.display.Screen;

import javax.swing.*;
import java.io.IOException;

public class Main {
    private JFrame root;
    private JLayeredPane layeredPane;
    private Screen screen;
    public static void main(String[] args) {

    }

    private void initialize(){
        root = new JFrame();
        layeredPane = new JLayeredPane();
        screen = new Screen();

        root.setTitle("Game");
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setSize(1920, 1080);
        root.setVisible(true);
        SwingUtilities.invokeLater(() -> {
            screen.requestFocus();
        });
    }
}