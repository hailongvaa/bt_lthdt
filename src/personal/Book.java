package personal;

import java.util.Objects;

/**
 * ============================================================================
 * Lớp biểu diễn thông tin Sách (Book) trong Thư viện mini.
 * 
 * KIẾN THỨC OOP ÁP DỤNG:
 * 1. Đóng gói (Encapsulation):
 *    - Các thuộc tính (fields) như bookId, title, author... đều để phạm vi truy cập
 *      là `private` để che giấu trạng thái bên trong của đối tượng.
 *    - Việc truy cập (đọc/ghi) thuộc tính bắt buộc phải đi qua các phương thức public
 *      getter/setter. Việc này giúp kiểm soát tính đúng đắn của dữ liệu đầu vào nếu cần.
 * 
 * 2. Nạp chồng Constructor (Constructor Overloading):
 *    - Có 3 hàm khởi tạo (constructors) với danh sách tham số khác nhau:
 *      + Constructor mặc định (không tham số).
 *      + Constructor đầy đủ tham số (nhập thủ công mã sách).
 *      + Constructor tự động sinh mã sách (Ngày 2) bằng cách dùng IdGenerator.
 * 
 * 3. Ghi đè phương thức (Method Overriding):
 *    - Ghi đè `toString()` từ lớp cha cao nhất trong Java là `Object` để trả về mô tả
 *      dạng chuỗi thân thiện cho người đọc.
 *    - Ghi đè `equals()` và `hashCode()` để thay đổi logic so sánh mặc định của Java
 *      (so sánh tham chiếu/địa chỉ ô nhớ) sang so sánh theo nghiệp vụ: Hai đối tượng Sách
 *      được coi là bằng nhau nếu chúng có trùng `bookId`.
 * 
 * 4. Hiện thực hóa Interface `Comparable` (Đa hình giao diện):
 *    - Lớp implements `Comparable<Book>` và triển khai phương thức `compareTo()`.
 *    - Định nghĩa tiêu chí sắp xếp mặc định (Natural Ordering) cho các đối tượng Sách.
 *    - Ở đây, tiêu chí mặc định là so sánh theo Tên sách (title) bỏ qua chữ hoa/chữ thường.
 * ============================================================================
 */
public class Book implements Comparable<Book> {

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Định nghĩa dữ liệu (Fields)
    // Áp dụng Đóng gói (Encapsulation)
    // ==========================================
    private String bookId;
    private String title;
    private String author;
    private String publisher;
    private int publishYear;
    private int quantity;

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Constructor mặc định
    // ==========================================
    public Book() {
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Constructor đầy đủ tham số
    // Dùng khi ta muốn chỉ định cứng mã sách từ trước (ví dụ từ CSDL hoặc khi phục dựng dữ liệu).
    // ==========================================
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
    // Dùng khi tạo mới một cuốn sách lần đầu trong hệ thống.
    // ==========================================
    public Book(String title, String author, String publisher, int publishYear, int quantity) {
        this.bookId = IdGenerator.generateBookId(); // Gọi IdGenerator tĩnh để tự sinh mã duy nhất
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.quantity = quantity;
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Các phương thức Getter và Setter
    // Thể hiện cơ chế Đóng gói (Encapsulation)
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
    // Giúp chuyển đổi trạng thái của đối tượng thành chuỗi ký tự phục vụ việc in ấn/ghi log.
    // ==========================================
    @Override
    public String toString() {
        return String.format("Book[ID='%s', Tên='%s', Tác giả='%s', NXB='%s', Năm='%d', Số lượng=%d]",
                bookId, title, author, publisher, publishYear, quantity);
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 3: Ghi đè phương thức equals() và hashCode()
    // Cặp bài trùng này luôn phải ghi đè đồng thời. Nếu hai đối tượng được xem là equals = true,
    // thì hashCode của chúng phải giống nhau. Điều này rất quan trọng khi lưu trữ đối tượng trong 
    // các Collection dạng bảng băm như HashSet, HashMap.
    // Ở đây, tiêu chí so sánh bằng nhau dựa duy nhất vào mã sách (bookId).
    // ==========================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // So sánh cùng tham chiếu ô nhớ
        if (o == null || getClass() != o.getClass()) return false; // Tránh null và kiểm tra kiểu lớp
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId); // So sánh giá trị thuộc tính bookId
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId); // Sinh hashCode dựa trên bookId
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 3: Ghi đè phương thức compareTo() từ interface Comparable
    // So sánh đối tượng hiện tại (this) với đối tượng truyền vào (other).
    // Trả về số âm nếu this < other, số 0 nếu bằng nhau, số dương nếu this > other.
    // Dùng để Collections.sort() biết cách tự động sắp xếp.
    // ==========================================
    @Override
    public int compareTo(Book other) {
        if (this.title == null || other.title == null) {
            return 0;
        }
        // So sánh theo tên sách không phân biệt chữ hoa/thường
        return this.title.compareToIgnoreCase(other.title);
    }
}
