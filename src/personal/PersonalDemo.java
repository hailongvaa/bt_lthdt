package personal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ============================================================================
 * BÀI TẬP CÁ NHÂN - NGÀY 3: Demo sắp xếp và chạy chương trình
 * 
 * KIẾN THỨC CỐT LÕI VỀ SẮP XẾP TRONG JAVA:
 * 1. Comparable vs Comparator:
 *    - `Comparable`: Định nghĩa tiêu chí sắp xếp "mặc định" và nội bộ của lớp. Lớp phải
 *      implements Comparable và ghi đè compareTo(). Phù hợp cho tiêu chí sắp xếp tự nhiên nhất.
 *    - `Comparator`: Định nghĩa tiêu chí sắp xếp "ngoại vi" (bên ngoài). Giúp ta sắp xếp danh sách
 *      theo nhiều tiêu chí khác nhau (ví dụ: theo năm xuất bản, theo số lượng...) mà không cần sửa
 *      nội dung code của lớp gốc.
 * 
 * 2. Các cách viết Comparator trong Java:
 *    - Cách 1: Sử dụng Lớp vô danh (Anonymous Class) - Cách viết truyền thống (từ Java 7 trở về trước).
 *    - Cách 2: Sử dụng biểu thức Lambda (Lambda Expression) - Cách viết ngắn gọn, tối giản (từ Java 8).
 *    - Cách 3: Sử dụng Method Reference kết hợp các hàm tiện ích của Comparator (tối tân từ Java 8).
 * ============================================================================
 */
public class PersonalDemo {
    public static void main(String[] args) {
        System.out.println("=== CHƯƠNG TRÌNH QUẢN LÝ THƯ VIỆN MINI ===");

        // 1. Tạo dữ liệu mẫu sử dụng Constructor tự động sinh ID (Ngày 2)
        System.out.println("\n--- Khởi tạo đối tượng & Tự động sinh ID (Ngày 2) ---");
        
        Book book1 = new Book("Lập trình hướng đối tượng với Java", "Nguyễn Văn A", "NXB Giáo Dục", 2022, 5);
        Book book2 = new Book("Cấu trúc dữ liệu và giải thuật", "Trần Thị B", "NXB Khoa Học", 2020, 12);
        Book book3 = new Book("Cơ sở dữ liệu", "Phạm Văn C", "NXB Công Nghệ", 2021, 8);
        Book book4 = new Book("Mạng máy tính", "Lê Văn D", "NXB Giáo Dục", 2019, 15);
        Book book5 = new Book("Hệ điều hành", "Hoàng Thị E", "NXB Công Nghệ", 2023, 3);

        Reader reader1 = new Reader("Nguyễn Hoàng Long", "longnh@gmail.com", "0912345678", "Hà Nội");
        Reader reader2 = new Reader("Trần Thu Trang", "trangtt@gmail.com", "0987654321", "Đà Nẵng");
        Reader reader3 = new Reader("Lê Minh Quân", "quanlm@gmail.com", "0905112233", "TP. HCM");

        Loan loan1 = new Loan(reader1, book1, LocalDate.of(2026, 5, 10), LocalDate.of(2026, 5, 24));
        Loan loan2 = new Loan(reader2, book3, LocalDate.of(2026, 5, 15), LocalDate.of(2026, 5, 29));
        Loan loan3 = new Loan(reader3, book2, LocalDate.of(2026, 5, 20), LocalDate.of(2026, 6, 3));

        // In các đối tượng kiểm chứng toString() (Ngày 3)
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(reader1);
        System.out.println(reader2);
        System.out.println(loan1);
        System.out.println(loan2);

        // 2. Kiểm chứng equals() và hashCode() (Ngày 3)
        System.out.println("\n--- Kiểm chứng equals() và hashCode() (Ngày 3) ---");
        // bookDuplicate có ID trùng với book1 nhưng các trường khác khác nhau
        Book bookDuplicate = new Book(book1.getBookId(), "Java Nâng Cao", "Nguyễn Văn A", "NXB Giáo Dục", 2024, 2);
        System.out.println("book1 ID: " + book1.getBookId() + " | Tên: " + book1.getTitle());
        System.out.println("bookDuplicate ID: " + bookDuplicate.getBookId() + " | Tên: " + bookDuplicate.getTitle());
        // Do ta đã ghi đè equals dựa trên bookId nên kết quả trả về true
        System.out.println("book1.equals(bookDuplicate)? -> " + book1.equals(bookDuplicate));
        System.out.println("book1.hashCode() == bookDuplicate.hashCode()? -> " + (book1.hashCode() == bookDuplicate.hashCode()));

        // 3. Sắp xếp danh sách Sách theo nhiều tiêu chí (Ngày 3 - B2)
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        System.out.println("\n--- Danh sách Sách gốc ---");
        printBookList(books);

        // Tiêu chí 1: Sắp xếp mặc định bằng Comparable (Theo Tên sách Alphabetical)
        // Lệnh Collections.sort(list) tự động gọi compareTo() được viết trong Book.java
        System.out.println("\n--- Sắp xếp 1: Theo tên sách tăng dần (Mặc định - Comparable) ---");
        Collections.sort(books);
        printBookList(books);

        // Tiêu chí 2: Sắp xếp theo Năm xuất bản tăng dần (sử dụng Comparator với Anonymous Class)
        System.out.println("\n--- Sắp xếp 2: Theo năm xuất bản tăng dần (Comparator) ---");
        books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return Integer.compare(b1.getPublishYear(), b2.getPublishYear());
            }
        });
        printBookList(books);

        // Tiêu chí 3: Sắp xếp theo Số lượng giảm dần (sử dụng Comparator với Lambda expression)
        // Cú pháp rút gọn từ việc bỏ khai báo kiểu dữ liệu và chữ new Comparator
        System.out.println("\n--- Sắp xếp 3: Theo số lượng giảm dần (Comparator - Lambda) ---");
        books.sort((b1, b2) -> Integer.compare(b2.getQuantity(), b1.getQuantity()));
        printBookList(books);

        // Tiêu chí 4: Sắp xếp theo Tác giả Alphabetical (sử dụng Comparator.comparing + Method Reference)
        // Book::getAuthor là Method Reference, tương đương với b -> b.getAuthor()
        System.out.println("\n--- Sắp xếp 4: Theo tác giả Alphabetical (Comparator.comparing) ---");
        books.sort(Comparator.comparing(Book::getAuthor));
        printBookList(books);
    }

    private static void printBookList(List<Book> list) {
        for (Book b : list) {
            System.out.printf("  - ID: %s | Tên: %-35s | Tác giả: %-15s | Năm NXB: %d | SL: %d\n",
                    b.getBookId(), b.getTitle(), b.getAuthor(), b.getPublishYear(), b.getQuantity());
        }
    }
}
