package personal;

import java.time.LocalDate;
import java.util.Objects;

/**
 * ============================================================================
 * Lớp biểu diễn thông tin Phiếu mượn sách (Loan) trong Thư viện mini.
 * 
 * KIẾN THỨC OOP ÁP DỤNG:
 * 1. Quan hệ kết hợp (Association / Composition):
 *    - Lớp Loan chứa các thuộc tính có kiểu dữ liệu là đối tượng của các lớp khác:
 *      `Reader reader` (Người mượn) và `Book book` (Sách được mượn).
 *    - Thể hiện sự liên kết giữa các thực thể khác nhau trong hệ thống thế giới thực.
 * 
 * 2. Đóng gói (Encapsulation):
 *    - Tất cả các thuộc tính đều được giới hạn truy cập bằng `private`.
 *    - Truy xuất thông qua getter và setter.
 * 
 * 3. Ghi đè phương thức (Method Overriding):
 *    - `toString()`: Hiển thị thông tin phiếu mượn, có xử lý null an toàn (null-safe)
 *      cho trường hợp reader hoặc book bị null.
 *    - `equals()` và `hashCode()`: So sánh hai phiếu mượn dựa trên thuộc tính định danh duy nhất
 *      là `loanId`.
 * 
 * 4. So sánh nâng cao (`Comparable`):
 *    - implements `Comparable<Loan>` và ghi đè `compareTo(Loan other)`.
 *    - Ở đây, tiêu chí sắp xếp mặc định là theo Ngày mượn (borrowDate) GIẢM DẦN
 *      (tức là phiếu mượn mới nhất sẽ được đưa lên đầu danh sách).
 * ============================================================================
 */
public class Loan implements Comparable<Loan> {

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Định nghĩa dữ liệu (Fields)
    // ==========================================
    private String loanId;
    private Reader reader; // Kết hợp với lớp Reader
    private Book book;     // Kết hợp với lớp Book
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status; // Ví dụ: "BORROWED" (Đang mượn), "RETURNED" (Đã trả), "OVERDUE" (Quá hạn)

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Constructor mặc định
    // ==========================================
    public Loan() {
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Constructor đầy đủ tham số
    // ==========================================
    public Loan(String loanId, Reader reader, Book book, LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate, String status) {
        this.loanId = loanId;
        this.reader = reader;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 2: Constructor tự động sinh ID sử dụng IdGenerator
    // Mặc định khi lập phiếu mượn mới thì ngày trả thực tế là null và trạng thái là "BORROWED".
    // ==========================================
    public Loan(Reader reader, Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.loanId = IdGenerator.generateLoanId(); // Tự động sinh mã phiếu mượn duy nhất
        this.reader = reader;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = null; // Mới mượn nên chưa có ngày trả thực tế
        this.status = "BORROWED";
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Các phương thức Getter và Setter
    // ==========================================
    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 3: Ghi đè phương thức toString()
    // Sử dụng toán tử ba ngôi để tránh NullPointerException khi in ra màn hình.
    // ==========================================
    @Override
    public String toString() {
        return String.format("Loan[ID='%s', Độc giả='%s', Sách='%s', Ngày mượn=%s, Hạn trả=%s, Trạng thái='%s']",
                loanId, 
                reader != null ? reader.getName() : "N/A", 
                book != null ? book.getTitle() : "N/A", 
                borrowDate, 
                dueDate, 
                status);
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 3: Ghi đè phương thức equals() và hashCode()
    // Hai phiếu mượn bằng nhau nếu có cùng mã phiếu mượn (loanId).
    // ==========================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(loanId, loan.loanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId);
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 3: Ghi đè phương thức compareTo() từ Comparable
    // Sắp xếp mặc định: So sánh theo Ngày mượn giảm dần (giảm dần - từ mới nhất đến cũ nhất).
    // Logic: `other.borrowDate.compareTo(this.borrowDate)` đảo ngược thứ tự so sánh tăng dần.
    // ==========================================
    @Override
    public int compareTo(Loan other) {
        if (this.borrowDate == null || other.borrowDate == null) {
            return 0;
        }
        return other.borrowDate.compareTo(this.borrowDate); // Sắp xếp giảm dần (mới nhất lên đầu)
    }
}
