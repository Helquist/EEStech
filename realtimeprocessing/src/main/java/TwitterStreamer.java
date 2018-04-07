import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Date;

public class TwitterStreamer {
    String w1 = "facebook";
    String w2 = "Donald";
    String w3 = "China";

    String[] top10User = new String[11];
    int[] top10Followers = new int[11];

    private ArrayList<String> languages1 = new ArrayList<String>();
    private ArrayList<Integer> noOfTweets1 = new ArrayList<Integer>();
    private ArrayList<String> languages2 = new ArrayList<String>();
    private ArrayList<Integer> noOfTweets2 = new ArrayList<Integer>();
    private ArrayList<String> languages3 = new ArrayList<String>();
    private ArrayList<Integer> noOfTweets3 = new ArrayList<Integer>();

    public void stream(final RealTimeGUI gui){
        final Configuration configuration = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey("cHkOtFiELcZ7nwloE5Nfq1iDs") // paste your consumer key
                .setOAuthConsumerSecret("9d2ykzFUb3OCO8enoo9Mv0Czxl2yV1zvyKVsjRGEibnpfaMJ4D") //paste your CONSUMER_SECRET
                .setOAuthAccessToken("981949789192507393-Li90Uw21NiPVWKc5HeK8A89ENTe7fzh") //paste your OAUTH_ACCESS_TOKEN
                .setOAuthAccessTokenSecret("Pwe13QzbzbzIjAM3p6TjoXrJwEUbi0vIM5zs6Xc58GzEo") // paste your OAUTH_ACCESS_TOKEN
                .build();

        for(int i=0; i<11; i++){
            top10User[i]="";
            top10Followers[i]=0;
        }

        StatusListener listener = new StatusListener() {

            public void onStatus(Status status) {
                String text = status.getText();
                User user = status.getUser();
                String language = user.getLang();

                if(text.contains(w1)) {
                    if (languages1.contains(language)) {
                        int pos = languages1.indexOf(language);
                        int tw = noOfTweets1.get(pos);
                        noOfTweets1.remove(pos);
                        noOfTweets1.add(pos, tw + 1);

                    } else {
                        languages1.add(language);
                        noOfTweets1.add(1);
                    }
                    gui.refreshLang(languages1, noOfTweets1, 1, w1);
                } else if(text.contains(w2)){
                    if (languages2.contains(language)) {
                        int pos = languages2.indexOf(language);
                        int tw = noOfTweets2.get(pos);
                        noOfTweets2.remove(pos);
                        noOfTweets2.add(pos, tw + 1);

                    } else {
                        languages2.add(language);
                        noOfTweets2.add(1);
                    }
                    gui.refreshLang(languages2, noOfTweets2, 2, w2);
                } else if(text.contains(w3)){
                    if (languages3.contains(language)) {
                        int pos = languages3.indexOf(language);
                        int tw = noOfTweets3.get(pos);
                        noOfTweets3.remove(pos);
                        noOfTweets3.add(pos, tw + 1);

                    } else {
                        languages3.add(language);
                        noOfTweets3.add(1);
                    }
                    gui.refreshLang(languages3, noOfTweets3, 3, w3);
                }

                if(checkTop(status)){
                    gui.refreshTop(top10User, top10Followers);
                }

                gui.displayTweet(status);

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
        twitterStream.filter(new FilterQuery().track(w1, w2, w3));
    }


    public boolean checkTop(Status status){
        int followers = status.getUser().getFollowersCount();
        String user = status.getUser().getName();

        if(followers<top10Followers[9])
            return false;
        else {
            for(int i=0; i<10; i++){
                if(status.getUser().getName().equals(top10User[i])){
                    return false;
                }
            }

            for(int i=9; i>=0; i--){
                if(followers>=top10Followers[i]){
                    top10User[i+1]=top10User[i];
                    top10User[i]=user;
                    top10Followers[i+1]=top10Followers[i];
                    top10Followers[i]=followers;
                } else {
                    return true;
                }
            }
        }
        return true;
    }

}
