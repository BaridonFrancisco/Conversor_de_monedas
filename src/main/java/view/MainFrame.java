package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JPanel jPanel=new JPanel();

    public MainFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(840,600);
        Button button=new Button("conversion");
        button.setSize(60,20);
        jPanel.add(button);
        add(this.jPanel);

    }
}
class Marco{
    public static void main(String[] args) {
        MainFrame mainFrame=new MainFrame("Conversor");
    }
}
