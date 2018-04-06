import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
                System.out.println(status.getText());
            }

            public void saveTweet(Status status) throws IOException {

                String str = status.getUser().getName()+';'+status.getLang()+';'+status.getText().trim().replace('\n',' ');
                BufferedWriter writer = new BufferedWriter(new FileWriter("TweetBase.csv", true));
                //writer.append(';');
                //String str = status.getLang();
                //writer.append(';');
               // String str = status.getText();
                writer.append(str);
                writer.append('\n');
                writer.close();
            }

            public void saveTweet2(Status status) throws IOException {
                String str = status.getUser().getName()+' '+status.getLang()+' '+status.getText();
                BufferedWriter writer = new BufferedWriter(new FileWriter("TweetBase.txt", true));
                //writer.append(';');
                //String str = status.getLang();
                //writer.append(';');
                // String str = status.getText();
                writer.append(str);
                writer.append('\n');
                writer.close();
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
        twitterStream.filter(new FilterQuery().track("DUDA", "UK", "USA"));


    }
}
