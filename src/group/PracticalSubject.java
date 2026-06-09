package group;

/**
 * ============================================================================
 * BÀI TẬP NHÓM - NGÀY 4: Kế thừa & Đa hình (Inheritance & Polymorphism)
 * 
 * KIẾN THỨC OOP ÁP DỤNG:
 * 1. Hiện thực hóa Phương thức trừu tượng (Overriding Abstract Method):
 *    - `PracticalSubject` kế thừa `Subject` nên bắt buộc phải triển khai phương thức
 *      `calculateFinalGrade()`.
 *    - Điểm quá trình chiếm 50%, Điểm thi chiếm 50%.
 *    - Sử dụng `Math.round()` để làm tròn 2 chữ số thập phân.
 * 
 * 2. Ghi đè toString():
 *    - Tái định nghĩa cách hiển thị thông tin để bổ sung thêm thuộc tính "Loại" môn học.
 * ============================================================================
 */
public class PracticalSubject extends Subject {

    public PracticalSubject() {
        super();
    }

    public PracticalSubject(String subjectId, String subjectName, int credits) {
        super(subjectId, subjectName, credits);
    }

    public PracticalSubject(String subjectName, int credits) {
        super(subjectName, credits);
    }

    /**
     * Quy tắc tính điểm tổng kết môn Thực hành:
     * Điểm tổng kết = 50% quá trình + 50% thi cuối kỳ.
     */
    @Override
    public double calculateFinalGrade(double processGrade, double examGrade) {
        double rawGrade = (processGrade * 0.5) + (examGrade * 0.5);
        return Math.round(rawGrade * 100.0) / 100.0; // Làm tròn 2 chữ số thập phân
    }

    @Override
    public String toString() {
        // Tái sử dụng getter từ lớp cha
        return String.format("PracticalSubject[ID='%s', Tên môn='%s', Tín chỉ=%d, Loại='Thực hành (50%%/50%%)']",
                getSubjectId(), getSubjectName(), getCredits());
    }
}
