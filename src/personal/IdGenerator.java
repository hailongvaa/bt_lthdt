package personal;

/**
 * ============================================================================
 * BÀI TẬP CÁ NHÂN - NGÀY 2
 * Lớp IdGenerator: Công cụ tự động sinh mã định danh (ID) duy nhất cho các thực thể.
 * 
 * KIẾN THỨC OOP ÁP DỤNG:
 * 1. Từ khóa `static` (Tĩnh):
 *    - Các biến bộ đếm (counters) và phương thức sinh mã được khai báo là static.
 *    - Điều này có nghĩa là chúng thuộc về lớp IdGenerator chứ không thuộc về bất kỳ
 *      đối tượng cụ thể nào được tạo ra từ lớp này.
 *    - Nhờ đó, bộ đếm được chia sẻ chung trong toàn bộ chương trình, đảm bảo mã số
 *      sinh ra liên tục và không bị trùng lặp.
 * 
 * 2. Đóng gói (Encapsulation):
 *    - Các bộ đếm counter được đặt phạm vi truy cập là `private static` để ngăn chặn
 *      việc truy cập trực tiếp hoặc thay đổi giá trị bộ đếm ngoài ý muốn từ các lớp khác.
 *    - Việc sinh mã chỉ được thực hiện thông qua các phương thức public được định nghĩa sẵn.
 * 
 * 3. An toàn đa luồng (Thread-safety với `synchronized`):
 *    - Từ khóa `synchronized` đảm bảo rằng tại một thời điểm chỉ có duy nhất một luồng
 *      (thread) được phép thực thi phương thức này.
 *    - Nếu nhiều luồng cố gắng tạo ID đồng thời (ví dụ: trong môi trường web hoặc đa luồng),
 *      `synchronized` ngăn chặn hiện tượng "race condition" (nhiều luồng đọc cùng một giá trị
 *      counter cũ dẫn đến sinh trùng ID).
 * ============================================================================
 */
public class IdGenerator {
    // Các bộ đếm tĩnh, được lưu trên vùng nhớ static của JVM (Method Area)
    private static int bookCounter = 1000;
    private static int readerCounter = 1000;
    private static int loanCounter = 1000;

    /**
     * Phương thức sinh mã tự động cho Sách (Book) -> Định dạng: B-XXXX
     * Mỗi lần gọi sẽ tăng bộ đếm lên 1 và trả về mã mới dạng chuỗi.
     */
    public static synchronized String generateBookId() {
        bookCounter++;
        return "B-" + bookCounter;
    }

    /**
     * Phương thức sinh mã tự động cho Độc giả (Reader) -> Định dạng: R-XXXX
     */
    public static synchronized String generateReaderId() {
        readerCounter++;
        return "R-" + readerCounter;
    }

    /**
     * Phương thức sinh mã tự động cho Phiếu mượn (Loan) -> Định dạng: L-XXXX
     */
    public static synchronized String generateLoanId() {
        loanCounter++;
        return "L-" + loanCounter;
    }
}
