package hust.soict.dsai.test.disc;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class TestPassingParameter {
    public static void main(String[] args) {
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");
        System.out.println("--- Test Swap ---");
        System.out.println("Before swap:");
        System.out.println("jungleDVD title: " + jungleDVD.getTitle());
        System.out.println("cinderellaDVD title: " + cinderellaDVD.getTitle());
        swap(jungleDVD, cinderellaDVD);
        System.out.println("After swap:");
        System.out.println("jungleDVD title: " + jungleDVD.getTitle());
        System.out.println("cinderellaDVD title: " + cinderellaDVD.getTitle());
        System.out.println("\n--- Test Change Title ---");
        changeTitle(jungleDVD, "New Jungle");
        System.out.println("After changeTitle:");
        System.out.println("jungleDVD title: " + jungleDVD.getTitle());
    }
    public static void swap(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        String oldTitle = dvd1.getTitle();
        dvd1.setTitle(dvd2.getTitle());
        dvd2.setTitle(oldTitle);
    }
    public static void changeTitle(DigitalVideoDisc dvd, String title) {
        dvd.setTitle(title); 
    }
}