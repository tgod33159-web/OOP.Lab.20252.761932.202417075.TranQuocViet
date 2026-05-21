package hust.soict.dsai.aims.media;

public class Disc extends Media {
    private String director;
    private int length;

    // Constructor mặc định
    public Disc() {
        super();
    }

    // Constructor đầy đủ tham số sử dụng super() gọi lên lớp cha Media
    public Disc(int id, String title, String category, float cost, String director, int length) {
        super(id, title, category, cost);
        this.director = director;
        this.length = length;
    }

    // Getter methods cho director và length
    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }
}