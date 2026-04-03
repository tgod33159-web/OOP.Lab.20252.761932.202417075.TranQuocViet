public class TestPassingParameter {

    public static void main(String[] args) {

        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");

        System.out.println("Before swap:");
        System.out.println("jungleDVD title: " + jungleDVD.getTitle());

        changeTitle(jungleDVD, "New Jungle");

        System.out.println("After change:");
        System.out.println("jungleDVD title: " + jungleDVD.getTitle());
    }

    public static void changeTitle(DigitalVideoDisc dvd, String title) {
        dvd = new DigitalVideoDisc(title);
        System.out.println("Inside function: " + dvd.getTitle());
    }
}