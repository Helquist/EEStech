import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.awt.*;

public class RealTimeGUI {
    private JPanel mainPanel;
    private JPanel streamPanel;
    private JScrollPane twitterScrollPane;


    public static void main(String[] args) {

    }

    public void start(){
        JFrame frame = new JFrame("RealTimeGUI");
        frame.setContentPane(new RealTimeGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);





    }

    public void displayTweet(Status status){
        System.out.println(status.getText());
    }

    public RealTimeGUI() {

    }


}
