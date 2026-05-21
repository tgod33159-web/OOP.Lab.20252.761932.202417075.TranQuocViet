package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

public class PolymorphismTest {
    public static void main(String[] args) {
        // 1. Tạo một danh sách kiểu dữ liệu cha Media
        List<Media> mediae = new ArrayList<Media>();
        // 2. Khởi tạo các đối tượng con khác nhau và thêm vào danh sách
        // Tạo một đĩa CD (Sử dụng constructor bài 5)
        CompactDisc cd = new CompactDisc(1, "Space Jam", "Soundtrack", 18.5f, "Warner Bros", 0, "Various Artists");
        
        // Tạo một đĩa DVD
        DigitalVideoDisc dvd = new DigitalVideoDisc("Sci-Fi", "Inception", 24.95f);
        
        // Tạo một cuốn sách và thêm tác giả (Sử dụng phương thức addAuthor bài 3)
        Book book = new Book();
        book.setId(3);
        book.setTitle("Clean Code");
        book.setCategory("Technology");
        book.setCost(35.0f);
        book.addAuthor("Robert C. Martin");

        // Thêm tất cả vào danh sách đa hình
        mediae.add(cd);
        mediae.add(dvd); 
        mediae.add(book); 

        // 3. Duyệt qua danh sách và gọi phương thức toString()
        System.out.println("=== TESTING POLYMORPHISM WITH toString() ===");
        for (Media m : mediae) { 
            // Dù 'm' có kiểu khai báo là Media, Java vẫn tự động gọi 
            // hàm toString() cụ thể của CD, DVD hoặc Book tại thời điểm chạy (Runtime)
            System.out.println(m.toString());
        }
    }
}