package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public CompactDisc(int id, String title, String category, float cost, String director, int length, String artist) {
        super(id, title, category, cost, director, length);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
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

    // Thực thi phương thức play(): Phát CD và phát từng bài hát trong CD đó
    @Override
    public void play() {
        System.out.println("Playing CD: " + this.getTitle() + " by " + this.getArtist());
        System.out.println("CD total length: " + this.getLength() + " mins");
        System.out.println("----------------------------------------");
        // Vòng lặp đa hình gọi hàm play() của từng track con
        for (Track track : tracks) {
            track.play();
        }
    }

    @Override
    public String toString() {
        return "CD - ID: " + getId() + " - " + getTitle() + " - " + getCategory() + " - Artist: " + artist + " - Total Length: " + getLength() + " mins: " + getCost() + " $";
    }
}