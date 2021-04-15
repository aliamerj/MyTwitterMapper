package twitter;

import twitter4j.Status;
import util.ImageCache;

import java.util.*;


public abstract class TwitterSource extends Observable {
    protected boolean doLogging = true;
    // The set of terms to look for in the stream of tweets
    protected Set<String> terms = new HashSet<>();

    // Called each time a new set of filter terms has been established
    abstract protected void sync();

    protected void log(Status status) {
        if (doLogging) {
            System.out.println(status.getUser().getName() + ": " + status.getText());
        }
        ImageCache.getInstance().loadImage(status.getUser().getProfileImageURL());
    }

    public void setFilterTerms(Collection<String> newTerms) {
        terms.clear();
        terms.addAll(newTerms);
        sync();
    }

    public List<String> getFilterTerms() {
        return new ArrayList<>(terms);
    }


    protected void handleTweet(Status status) {
        setChanged();
        notifyObservers(status);
    }
}
