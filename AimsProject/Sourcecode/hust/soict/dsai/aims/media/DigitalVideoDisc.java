package hust.soict.dsai.aims.media;

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
        this.setCategory(category);
        this.setTitle(title);
        this.setCost(cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, director, length);
    }

    // Thực thi phương thức play() từ giao diện Playable
    @Override
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength() + " mins");
    }

    @Override
    public String toString() {
        return "DVD - ID: " + getId() + " - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + " mins: " + getCost() + " $";
    }
}