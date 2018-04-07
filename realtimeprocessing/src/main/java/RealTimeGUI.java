import javax.swing.*;

import twitter4j.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

public class RealTimeGUI {
    private JPanel mainPanel;
    private JPanel streamPanel;
    private JPanel top10Panel;
    private JPanel graphPanel;
    private JTextPane top10user;
    private JTextPane top10followers;
    private JTextPane textWord1;
    private JTextPane textWord2;
    private JTextPane textWord3;
    private JTextArea streamTextArea;


    public static void main(String[] args) {

    }

    public void start(){
        JFrame frame = new JFrame("RealTimeGUI");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        streamTextArea.setLineWrap(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension streamDim = Toolkit.getDefaultToolkit().getScreenSize();
        streamDim.setSize(streamDim.getWidth()*0.75,streamDim.getHeight());
        top10Panel.setSize(streamDim);
    }



    public RealTimeGUI() {



    }

    public void displayTweet(Status status){
        streamTextArea.append(status.getText());


        //streamTextArea.setCaretPosition(streamTextArea.getDocument().getLength());


        if(streamTextArea.hasFocus()==false){
            streamTextArea.setCaretPosition(streamTextArea.getDocument().getLength());
        }

    }

    public void refreshTop(String[] users, int[] followers){

        /*
            for (int i = 0; i < 10; i++) {
                System.out.println((i + 1) + ". " + users[i] + " - " + followers[i]);
            }
            System.out.println("------------------");
        */

        String top10u = "";
        String top10f = "";
        for(int i=0; i<10; i++){
            top10u = top10u + (i+1) + " - " + users[i] + "\n";
            top10f = top10f + followers[i] + "\n";
        }
        top10user.setText(top10u);
        top10followers.setText(top10f);

    }

    public void refreshLang(ArrayList<String> languages, ArrayList<Integer> noOfTweets, int word, String w){
        if(word == 1){
            int N= languages.size();
            String str = w + '\n';
            for(int i =0; i<N; i++){
                str = str + languages.get(i) + "\t" + noOfTweets.get(i) + "\n";
            }
            textWord1.setText(str);
        } else if(word==2){
            int N= languages.size();
            String str = w + '\n';
            for(int i =0; i<N; i++){
                str = str + languages.get(i) + "\t" + noOfTweets.get(i) + "\n";
            }
            textWord2.setText(str);

        } else if(word==3){

            int N= languages.size();
            String str = w + '\n';
            for(int i =0; i<N; i++){
                str = str + languages.get(i) + "\t" + noOfTweets.get(i) + "\n";
            }
            textWord3.setText(str);
        }

    }


}
