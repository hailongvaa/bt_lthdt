package personal;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Lớp biểu diễn thông tin Phiếu mượn sách (Loan) trong Thư viện mini.
 * Chứa các chú thích cho Ngày 1, Ngày 2 và Ngày 3.
 */
public class Loan implements Comparable<Loan> { // NGÀY 3: implements Comparable để hỗ trợ so sánh mặc định

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Định nghĩa dữ liệu (Fields)
    // ==========================================
    private String loanId;
    private Reader reader;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status; // Ví dụ: "BORROWED" (Đang mượn), "RETURNED" (Đã trả), "OVERDUE" (Quá hạn)

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Constructor mặc định & Constructor đầy đủ tham số
    // ==========================================
    public Loan() {
    }

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
    // ==========================================
    public Loan(Reader reader, Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.loanId = IdGenerator.generateLoanId(); // Sử dụng IdGenerator để sinh mã phiếu mượn tự động
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
    // BÀI TẬP CÁ NHÂN - NGÀY 3: Ghi đè phương thức compareTo()
    // Sắp xếp mặc định: So sánh theo Ngày mượn (giảm dần - từ mới nhất đến cũ nhất).
    // ==========================================
    @Override
    public int compareTo(Loan other) {
        if (this.borrowDate == null || other.borrowDate == null) {
            return 0;
        }
        return other.borrowDate.compareTo(this.borrowDate); // Sắp xếp giảm dần (mới nhất lên đầu)
    }
}
