package group;

import java.util.Objects;

/**
 * ============================================================================
 * Lớp biểu diễn điểm số (Grade) của Sinh viên đối với một Môn học.
 * 
 * KIẾN THỨC OOP ÁP DỤNG:
 * 1. Lớp liên kết (Association Class):
 *    - `Grade` đóng vai trò là một liên kết "nhiều-nhiều" giữa Sinh viên (`Student`) 
 *      và Môn học (`Subject`). Nó chứa các thuộc tính bổ sung cho mối quan hệ này:
 *      `processGrade` (Điểm quá trình) và `examGrade` (Điểm thi).
 * 
 * 2. Tính Đa hình động (Dynamic Binding / Polymorphism):
 *    - Trong phương thức `getFinalGrade()`, ta gọi `this.subject.calculateFinalGrade(...)`.
 *    - Mặc dù biến `subject` được khai báo kiểu dữ liệu tĩnh là lớp cha `Subject` (abstract),
 *      nhưng tại thời điểm chạy (runtime), Java Virtual Machine (JVM) sẽ tự động phát hiện
 *      đối tượng thực tế là `TheorySubject` hay `PracticalSubject` để thực thi đúng công thức
 *      tính điểm tương ứng.
 *    - Tránh hoàn toàn việc sử dụng các cấu trúc kiểm tra kiểu dữ liệu thô sơ và khó bảo trì 
 *      như `if (subject instanceof TheorySubject) ... else if ...`.
 * 
 * 3. Ghi đè (Override) & Độc lập dữ liệu:
 *    - `equals()` và `hashCode()` được định nghĩa dựa trên cả 2 đối tượng `student` và `subject`.
 *      Có nghĩa là: Một sinh viên chỉ có duy nhất một bản ghi điểm cho một môn học cụ thể.
 * 
 * 4. So sánh (`Comparable`):
 *    - Triển khai `Comparable<Grade>` và override `compareTo()`.
 *    - Sắp xếp mặc định: Thứ tự Điểm tổng kết GIẢM DẦN (Điểm cao đứng trước).
 * ============================================================================
 */
public class Grade implements Comparable<Grade> {

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 1: Định nghĩa dữ liệu (Fields)
    // ==========================================
    private Student student;
    private Subject subject;
    private double processGrade; // Điểm quá trình
    private double examGrade;    // Điểm thi cuối kỳ

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 1: Constructor mặc định & Constructor đầy đủ tham số
    // ==========================================
    public Grade() {
    }

    private void validateGrade(double grade, String name) {
        if (grade < 0.0 || grade > 10.0) {
            throw new IllegalArgumentException(name + " phải nằm trong khoảng từ 0.0 đến 10.0");
        }
    }

    public Grade(Student student, Subject subject, double processGrade, double examGrade) {
        validateGrade(processGrade, "Điểm quá trình");
        validateGrade(examGrade, "Điểm thi cuối kỳ");
        this.student = student;
        this.subject = subject;
        this.processGrade = processGrade;
        this.examGrade = examGrade;
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 1: Các phương thức Getter và Setter
    // ==========================================
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getProcessGrade() {
        return processGrade;
    }

    public void setProcessGrade(double processGrade) {
        validateGrade(processGrade, "Điểm quá trình");
        this.processGrade = processGrade;
    }

    public double getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(double examGrade) {
        validateGrade(examGrade, "Điểm thi cuối kỳ");
        this.examGrade = examGrade;
    }

    /**
     * TÍNH ĐA HÌNH TRONG NGHIỆP VỤ:
     * Tự động tính điểm tổng kết dựa trên công thức của lớp con thực tế (Theory/Practical).
     */
    public double getFinalGrade() {
        if (this.subject == null) {
            return 0.0;
        }
        // Gọi phương thức đa hình của đối tượng Subject cụ thể
        return this.subject.calculateFinalGrade(this.processGrade, this.examGrade);
    }

    /**
     * Quy đổi điểm hệ 10 sang điểm chữ (A, B, C, D, F) theo chuẩn tín chỉ
     */
    public String getGradeLetter() {
        double finalGrade = getFinalGrade();
        if (finalGrade >= 8.5) return "A";
        if (finalGrade >= 7.0) return "B";
        if (finalGrade >= 5.5) return "C";
        if (finalGrade >= 4.0) return "D";
        return "F";
    }

    /**
     * Kiểm tra sinh viên có qua môn học này hay không (Điểm tổng kết >= 4.0).
     */
    public boolean isPassed() {
        return getFinalGrade() >= 4.0;
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 3: Ghi đè phương thức toString()
    // ==========================================
    @Override
    public String toString() {
        return String.format("Grade[Sinh viên='%s', Môn='%s', Quá trình=%.1f, Thi=%.1f, Tổng kết=%.2f, Điểm chữ='%s']",
                student != null ? student.getFullName() : "N/A",
                subject != null ? subject.getSubjectName() : "N/A",
                processGrade,
                examGrade,
                getFinalGrade(),
                getGradeLetter());
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 3: Ghi đè phương thức equals() và hashCode()
    // Một bản ghi điểm được xác định duy nhất bởi cặp (Sinh viên, Môn học).
    // ==========================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(student, grade.student) && Objects.equals(subject, grade.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, subject);
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 3: Ghi đè phương thức compareTo() từ Comparable
    // Sắp xếp mặc định: So sánh theo Điểm tổng kết giảm dần (Điểm cao đứng trước).
    // Logic: `Double.compare(other.getFinalGrade(), this.getFinalGrade())`
    // ==========================================
    @Override
    public int compareTo(Grade other) {
        return Double.compare(other.getFinalGrade(), this.getFinalGrade());
    }
}
