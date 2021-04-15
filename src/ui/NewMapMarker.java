package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import util.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NewMapMarker extends MapMarkerSimple {
    public static final double defaultMarkerSize = 17.0;
    public BufferedImage img1;
    public String tweet1;
    public String profileImageUrl;

    public NewMapMarker(Layer layer, Coordinate coordinate, Color color, String profileImageURL, String tweet) {
        super(layer, coordinate);
        img1 = Util.imageFromURL(profileImageURL);
        tweet1 = tweet;
        profileImageUrl = profileImageURL;
    }

    public String getTweet() {
        return this.tweet1;
    }

    public String getProfileImageUrl() {
        return this.profileImageUrl;
    }

    @Override
    public void paint(Graphics g, Point position, int radius) {
        int size = radius * 2;
        if (g instanceof Graphics2D && this.getBackColor() != null) {
            Graphics2D g2 = (Graphics2D) g;
            Composite oldComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3));
            g2.setPaint(this.getBackColor());
            g.fillOval(position.x - radius, position.y - radius, size, size);
            g2.setComposite(oldComposite);
            g.drawImage(img1, position.x - 10, position.y - 10, 20,20,null);
        }
    }

}