package group;

/**
 * ==========================================
 * BÀI TẬP NHÓM - NGÀY 2
 * Bổ sung lớp IdGenerator để tự động sinh mã
 * ==========================================
 */
public class IdGenerator {
    private static int studentCounter = 2026000; // Khởi tạo mã bắt đầu từ năm học hiện tại
    private static int subjectCounter = 100;

    // Phương thức sinh mã tự động cho Sinh viên (Student) -> Định dạng: SV2026XXX
    public static synchronized String generateStudentId() {
        studentCounter++;
        return "SV" + studentCounter;
    }

    // Phương thức sinh mã tự động cho Môn học (Subject) -> Định dạng: MHXXX
    public static synchronized String generateSubjectId() {
        subjectCounter++;
        return "MH" + subjectCounter;
    }
}
