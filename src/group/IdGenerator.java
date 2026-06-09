package group;

/**
 * ============================================================================
 * BÀI TẬP NHÓM - NGÀY 2
 * Lớp IdGenerator: Công cụ tự động sinh mã định danh (ID) duy nhất cho các thực thể.
 * 
 * KIẾN THỨC OOP ÁP DỤNG:
 * 1. Từ khóa `static` (Tĩnh):
 *    - Các biến bộ đếm (counters) và phương thức sinh mã được khai báo là static.
 *    - Bộ đếm được dùng chung cho toàn bộ chương trình, đảm bảo mã số sinh ra liên tục.
 * 
 * 2. Đóng gói (Encapsulation):
 *    - Các bộ đếm counter được đặt phạm vi truy cập là `private static` để bảo vệ dữ liệu.
 * 
 * 3. An toàn đa luồng (Thread-safety với `synchronized`):
 *    - Từ khóa `synchronized` đảm bảo rằng tại một thời điểm chỉ có duy nhất một luồng
 *      (thread) được phép thực thi phương thức sinh mã, ngăn chặn hiện tượng trùng lặp ID.
 * ============================================================================
 */
public class IdGenerator {
    // Mã sinh viên bắt đầu từ năm học hiện tại
    private static int studentCounter = 2026000;
    private static int subjectCounter = 100;
    private static int teacherCounter = 500;

    /**
     * Phương thức sinh mã tự động cho Sinh viên (Student) -> Định dạng: SV2026XXX
     */
    public static synchronized String generateStudentId() {
        studentCounter++;
        return "SV" + studentCounter;
    }

    /**
     * Phương thức sinh mã tự động cho Môn học (Subject) -> Định dạng: MHXXX
     */
    public static synchronized String generateSubjectId() {
        subjectCounter++;
        return "MH" + subjectCounter;
    }

    /**
     * Phương thức sinh mã tự động cho Giảng viên (Teacher) -> Định dạng: GVXXX
     */
    public static synchronized String generateTeacherId() {
        teacherCounter++;
        return "GV" + teacherCounter;
    }
}

