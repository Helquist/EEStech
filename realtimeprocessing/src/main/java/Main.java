import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Main {

    public static void main(String[] args) {
        TwitterStreamer twitterStreamer = new TwitterStreamer();
        RealTimeGUI gui = new RealTimeGUI();
        twitterStreamer.stream(gui);
        gui.start();
    }
}
