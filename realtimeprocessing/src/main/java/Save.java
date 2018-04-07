import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;

public class Save {
    public static void main(String[] args) {
        final Configuration configuration = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey("cHkOtFiELcZ7nwloE5Nfq1iDs") // paste your consumer key
                .setOAuthConsumerSecret("9d2ykzFUb3OCO8enoo9Mv0Czxl2yV1zvyKVsjRGEibnpfaMJ4D") //paste your CONSUMER_SECRET
                .setOAuthAccessToken("981949789192507393-Li90Uw21NiPVWKc5HeK8A89ENTe7fzh") //paste your OAUTH_ACCESS_TOKEN
                .setOAuthAccessTokenSecret("Pwe13QzbzbzIjAM3p6TjoXrJwEUbi0vIM5zs6Xc58GzEo") // paste your OAUTH_ACCESS_TOKEN
                .build();
        StatusListener listener = new StatusListener() {

            public void onStatus(Status status) {
                try {
                    saveTweet(status);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    saveTweet2(status);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(status.getText());
            }

            public void saveTweet(Status status) throws IOException {
                String str = status.getUser().getCreatedAt()+status.getUser().getName().replace(';',' ').trim()+';'+status.getLang()+';'+status.getText().replace(';',' ').replace('\n',' ');
                BufferedWriter writer = new BufferedWriter(new FileWriter("TweetBase.csv", true));
                writer.append(str);
                writer.append('\n');
                writer.close();
            }

            public void saveTweet2(Status status)throws IOException{
                ObjectOutputStream tosave = null;
                try {
                    String Base = "TweetBase.bin";
                    tosave = new ObjectOutputStream(new FileOutputStream(Base));
                    tosave.writeObject(status);
                }catch (IOException ex) {
                    System.out.println("Blad w zapisie do pliku Tweetbase.bin");
                }
                finally {
                    if(tosave!=null)
                        tosave.close();
                }
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }

            public void onScrubGeo(long l, long l1) {

            }

            public void onStallWarning(StallWarning stallWarning) {

            }

            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        TwitterStream twitterStream = new TwitterStreamFactory(configuration).getInstance();
        twitterStream.addListener(listener);
        twitterStream.filter(new FilterQuery().track("Russia", "UK", "USA", "TRUMP", "Terrorist", "Vegan", "China", "Facebook"));
    }
}
