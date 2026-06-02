package group;

import java.util.Objects;

/**
 * Lớp biểu diễn điểm số (Grade) của Sinh viên đối với một Môn học.
 * Chứa các chú thích cho Ngày 1, Ngày 2 và Ngày 3.
 */
public class Grade implements Comparable<Grade> { // NGÀY 3: implements Comparable để hỗ trợ so sánh mặc định

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 1: Định nghĩa dữ liệu (Fields)
    // ==========================================
    private Student student;
    private Subject subject;
    private double processGrade; // Điểm quá trình (trọng số 40% hoặc 0.4)
    private double examGrade;    // Điểm thi cuối kỳ (trọng số 60% hoặc 0.6)

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 1: Constructor mặc định & Constructor đầy đủ tham số
    // ==========================================
    public Grade() {
    }

    public Grade(Student student, Subject subject, double processGrade, double examGrade) {
        this.student = student;
        this.subject = subject;
        this.processGrade = processGrade;
        this.examGrade = examGrade;
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 2: Note về việc sử dụng đối tượng sinh ID tự động
    // Lớp Grade là lớp liên kết (Association Class) giữa Student và Subject. 
    // Do đó, nó sử dụng trực tiếp các thực thể Student và Subject đã được sinh ID bằng IdGenerator.
    // ==========================================

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
        this.processGrade = processGrade;
    }

    public double getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(double examGrade) {
        this.examGrade = examGrade;
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 1: Nghiệp vụ tính Điểm tổng kết và Quy đổi điểm chữ
    // ==========================================
    // Điểm tổng kết hệ 10 = Điểm quá trình * 0.4 + Điểm thi * 0.6
    public double getFinalGrade() {
        double rawGrade = (this.processGrade * 0.4) + (this.examGrade * 0.6);
        return Math.round(rawGrade * 100.0) / 100.0; // Làm tròn 2 chữ số thập phân
    }

    // Quy đổi điểm hệ 10 sang điểm chữ (A, B, C, D, F)
    public String getGradeLetter() {
        double finalGrade = getFinalGrade();
        if (finalGrade >= 8.5) return "A";
        if (finalGrade >= 7.0) return "B";
        if (finalGrade >= 5.5) return "C";
        if (finalGrade >= 4.0) return "D";
        return "F";
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
    // Hai đầu điểm bằng nhau nếu trùng cả sinh viên (Student) và môn học (Subject).
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
    // BÀI TẬP NHÓM - NGÀY 3: Ghi đè phương thức compareTo()
    // Sắp xếp mặc định: So sánh theo Điểm tổng kết giảm dần (Điểm cao đứng trước).
    // ==========================================
    @Override
    public int compareTo(Grade other) {
        return Double.compare(other.getFinalGrade(), this.getFinalGrade());
    }
}
