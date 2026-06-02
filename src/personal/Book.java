package personal;

import java.util.Objects;

/**
 * Lớp biểu diễn thông tin Sách (Book) trong Thư viện mini.
 * Chứa các chú thích cho Ngày 1, Ngày 2 và Ngày 3.
 */
public class Book implements Comparable<Book> { // NGÀY 3: implements Comparable để hỗ trợ so sánh mặc định

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Định nghĩa dữ liệu (Fields)
    // ==========================================
    private String bookId;
    private String title;
    private String author;
    private String publisher;
    private int publishYear;
    private int quantity;

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Constructor mặc định & Constructor đầy đủ tham số
    // ==========================================
    public Book() {
    }

    public Book(String bookId, String title, String author, String publisher, int publishYear, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.quantity = quantity;
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 2: Constructor tự động sinh ID sử dụng IdGenerator
    // ==========================================
    public Book(String title, String author, String publisher, int publishYear, int quantity) {
        this.bookId = IdGenerator.generateBookId(); // Sử dụng IdGenerator để sinh mã sách tự động
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.quantity = quantity;
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Các phương thức Getter và Setter
    // ==========================================
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 3: Ghi đè phương thức toString()
    // ==========================================
    @Override
    public String toString() {
        return String.format("Book[ID='%s', Tên='%s', Tác giả='%s', NXB='%s', Năm='%d', Số lượng=%d]",
                bookId, title, author, publisher, publishYear, quantity);
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 3: Ghi đè phương thức equals() và hashCode()
    // Hai cuốn sách được coi là bằng nhau nếu chúng có cùng mã sách (bookId).
    // ==========================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 3: Ghi đè phương thức compareTo()
    // Sắp xếp mặc định: So sánh theo tên sách (Alphabetical Order).
    // ==========================================
    @Override
    public int compareTo(Book other) {
        if (this.title == null || other.title == null) {
            return 0;
        }
        return this.title.compareToIgnoreCase(other.title);
    }
}
