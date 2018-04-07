import twitter4j.*;
import org.testng.annotations.ITestOrConfiguration;
import twitter4j.conf.ConfigurationBuilder;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Read {
    public void readTweetBasecsv()throws IOException,ClassNotFoundException{
        String name= "TweetBase.bin";
        ObjectInputStream toread=null;
        try{
            toread=new ObjectInputStream(new FileInputStream(name));
            ArrayList<Status> status= (ArrayList<Status>) toread.readObject();
            //System.out.println(status.getUser()+" "+status.getLang()+" "+status.getText());
        } catch (EOFException ex) {
            System.out.println("Koniec pliku");
        }
        finally{
            if(toread!=null)
                toread.close();
        }
    }
}
