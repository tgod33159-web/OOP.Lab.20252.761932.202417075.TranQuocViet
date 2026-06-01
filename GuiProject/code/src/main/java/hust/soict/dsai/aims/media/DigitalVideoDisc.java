package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {
    private static int nbDigitalVideoDiscs = 0;

    public DigitalVideoDisc(String title) {
        super();
        nbDigitalVideoDiscs++;
        this.setId(nbDigitalVideoDiscs);
        this.setTitle(title);
    }

    public DigitalVideoDisc(String category, String title, float cost) {
        super();
        nbDigitalVideoDiscs++;
        this.setId(nbDigitalVideoDiscs);
        this.setCategory(category);
        this.setTitle(title);
        this.setCost(cost);
    }

    public DigitalVideoDisc(String director, String category, String title, float cost) {
        super();
        nbDigitalVideoDiscs++;
        this.setId(nbDigitalVideoDiscs);
        this.setDirector(director);
        this.setCategory(category);
        this.setTitle(title);
        this.setCost(cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, director, length);
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength() + " mins");
        } else {
            String message = "ERROR: DVD length is non-positive!";
            System.err.println(message);
            throw new PlayerException(message);
        }
    }

    @Override
    public String toString() {
        return "DVD - ID: " + getId() + " - " + getTitle() + " - " + getCategory()
                + " - " + getDirector() + " - " + getLength() + " mins: " + getCost() + " $";
    }
}
