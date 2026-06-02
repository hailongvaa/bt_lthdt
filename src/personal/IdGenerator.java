package personal;

/**
 * ==========================================
 * BÀI TẬP CÁ NHÂN - NGÀY 2
 * Bổ sung lớp IdGenerator để tự động sinh mã
 * ==========================================
 */
public class IdGenerator {
    private static int bookCounter = 1000;
    private static int readerCounter = 1000;
    private static int loanCounter = 1000;

    // Phương thức sinh mã tự động cho Sách (Book) -> Định dạng: B-XXXX
    public static synchronized String generateBookId() {
        bookCounter++;
        return "B-" + bookCounter;
    }

    // Phương thức sinh mã tự động cho Độc giả (Reader) -> Định dạng: R-XXXX
    public static synchronized String generateReaderId() {
        readerCounter++;
        return "R-" + readerCounter;
    }

    // Phương thức sinh mã tự động cho Phiếu mượn (Loan) -> Định dạng: L-XXXX
    public static synchronized String generateLoanId() {
        loanCounter++;
        return "L-" + loanCounter;
    }
}
