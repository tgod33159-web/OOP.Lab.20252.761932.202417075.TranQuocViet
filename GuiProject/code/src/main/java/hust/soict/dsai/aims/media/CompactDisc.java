package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

import hust.soict.dsai.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private final List<Track> tracks = new ArrayList<Track>();

    public CompactDisc(int id, String title, String category, float cost, String director, int length, String artist) {
        super(id, title, category, cost, director, length);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void addTrack(Track track) {
        if (track == null) {
            System.out.println("Cannot add a null track.");
            return;
        }
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Track \"" + track.getTitle() + "\" has been added to CD.");
        } else {
            System.out.println("Track \"" + track.getTitle() + "\" already exists in this CD.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track \"" + track.getTitle() + "\" has been removed from CD.");
        } else {
            System.out.println("Track \"" + track.getTitle() + "\" does not exist in this CD.");
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            String message = "ERROR: CD length is non-positive!";
            System.err.println(message);
            throw new PlayerException(message);
        }

        System.out.println("Playing CD: " + this.getTitle() + " by " + this.getArtist());
        System.out.println("CD total length: " + this.getLength() + " mins");
        System.out.println("----------------------------------------");
        for (Track track : tracks) {
            try {
                track.play();
            } catch (PlayerException e) {
                throw e;
            }
        }
    }

    @Override
    public String toString() {
        return "CD - ID: " + getId() + " - " + getTitle() + " - " + getCategory()
                + " - Artist: " + artist + " - Total Length: " + getLength() + " mins: " + getCost() + " $";
    }
}
