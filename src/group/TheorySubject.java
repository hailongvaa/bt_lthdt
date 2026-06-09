package group;

/**
 * ============================================================================
 * BÀI TẬP NHÓM - NGÀY 4: Kế thừa & Đa hình (Inheritance & Polymorphism)
 * 
 * KIẾN THỨC OOP ÁP DỤNG:
 * 1. Hiện thực hóa Phương thức trừu tượng (Overriding Abstract Method):
 *    - `TheorySubject` kế thừa `Subject` nên bắt buộc phải triển khai phương thức
 *      `calculateFinalGrade()`.
 *    - Điểm quá trình chiếm 40%, Điểm thi chiếm 60%.
 *    - Sử dụng `Math.round()` để làm tròn 2 chữ số thập phân, đảm bảo tính chuẩn xác.
 * 
 * 2. Ghi đè toString():
 *    - Tái định nghĩa cách hiển thị thông tin để bổ sung thêm thuộc tính "Loại" môn học.
 * ============================================================================
 */
public class TheorySubject extends Subject {

    public TheorySubject() {
        super();
    }

    public TheorySubject(String subjectId, String subjectName, int credits) {
        super(subjectId, subjectName, credits);
    }

    public TheorySubject(String subjectName, int credits) {
        super(subjectName, credits);
    }

    /**
     * Quy tắc tính điểm tổng kết môn Lý thuyết:
     * Điểm tổng kết = 40% quá trình + 60% thi cuối kỳ.
     */
    @Override
    public double calculateFinalGrade(double processGrade, double examGrade) {
        double rawGrade = (processGrade * 0.4) + (examGrade * 0.6);
        return Math.round(rawGrade * 100.0) / 100.0; // Làm tròn 2 chữ số thập phân
    }

    @Override
    public String toString() {
        // Tái sử dụng getter từ lớp cha
        return String.format("TheorySubject[ID='%s', Tên môn='%s', Tín chỉ=%d, Loại='Lý thuyết (40%%/60%%)']",
                getSubjectId(), getSubjectName(), getCredits());
    }
}
