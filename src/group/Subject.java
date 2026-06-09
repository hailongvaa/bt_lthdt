package group;

import java.util.Objects;

/**
 * ============================================================================
 * Lớp trừu tượng biểu diễn thông tin Môn học (Subject) trong hệ thống Quản lý điểm.
 * 
 * KIẾN THỨC OOP ÁP DỤNG:
 * 1. Lớp trừu tượng (Abstract Class):
 *    - `Subject` không đại diện cho một môn học cụ thể nào có thể tự tính điểm, mà nó chỉ
 *      chứa thông tin cấu trúc chung của một môn học (mã, tên, số tín chỉ).
 *    - Đóng vai trò làm bộ khung (template) cho các loại môn học thực tế kế thừa.
 * 
 * 2. Phương thức trừu tượng (Abstract Method):
 *    - `calculateFinalGrade(...)`: Mỗi loại môn học (Lý thuyết, Thực hành, Đồ án...)
 *      có quy tắc tính điểm tổng kết khác nhau.
 *    - Do đó, phương thức này được khai báo abstract để lớp con tự định nghĩa công thức
 *      tính điểm phù hợp.
 * ============================================================================
 */
public abstract class Subject implements Comparable<Subject> {

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 1: Định nghĩa dữ liệu (Fields)
    // ==========================================
    protected String subjectId;
    protected String subjectName;
    protected int credits;

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 1: Constructor mặc định & Constructor đầy đủ tham số
    // ==========================================
    public Subject() {
    }

    public Subject(String subjectId, String subjectName, int credits) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credits = credits;
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 2: Constructor tự động sinh ID sử dụng IdGenerator
    // ==========================================
    public Subject(String subjectName, int credits) {
        this.subjectId = IdGenerator.generateSubjectId(); // Tự động sinh ID duy nhất
        this.subjectName = subjectName;
        this.credits = credits;
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 1: Các phương thức Getter và Setter
    // ==========================================
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Phương thức trừu tượng tính điểm tổng kết môn học.
     * @param processGrade Điểm quá trình
     * @param examGrade Điểm thi cuối kỳ
     * @return Điểm tổng kết sau khi làm tròn 2 chữ số thập phân
     */
    public abstract double calculateFinalGrade(double processGrade, double examGrade);

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 3: Ghi đè phương thức toString()
    // ==========================================
    @Override
    public String toString() {
        return String.format("Subject[ID='%s', Tên môn='%s', Tín chỉ=%d]",
                subjectId, subjectName, credits);
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 3: Ghi đè phương thức equals() và hashCode()
    // Hai môn học bằng nhau nếu có cùng mã môn học (subjectId).
    // ==========================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(subjectId, subject.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId);
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 3: Ghi đè phương thức compareTo() từ Comparable
    // Sắp xếp mặc định: So sánh theo Tên môn học (Alphabetical Order).
    // ==========================================
    @Override
    public int compareTo(Subject other) {
        if (this.subjectName == null || other.subjectName == null) {
            return 0;
        }
        return this.subjectName.compareToIgnoreCase(other.subjectName);
    }
}
