package group;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Lớp biểu diễn thông tin Sinh viên (Student) trong hệ thống Quản lý điểm.
 * Chứa các chú thích cho Ngày 1, Ngày 2 và Ngày 3.
 */
public class Student implements Comparable<Student> { // NGÀY 3: implements Comparable để hỗ trợ so sánh mặc định

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 1: Định nghĩa dữ liệu (Fields)
    // ==========================================
    private String studentId;
    private String fullName;
    private LocalDate dateOfBirth;
    private String classroom;

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 1: Constructor mặc định & Constructor đầy đủ tham số
    // ==========================================
    public Student() {
    }

    public Student(String studentId, String fullName, LocalDate dateOfBirth, String classroom) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.classroom = classroom;
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 2: Constructor tự động sinh ID sử dụng IdGenerator
    // ==========================================
    public Student(String fullName, LocalDate dateOfBirth, String classroom) {
        this.studentId = IdGenerator.generateStudentId(); // Sử dụng IdGenerator để sinh mã sinh viên tự động
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.classroom = classroom;
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 1: Các phương thức Getter và Setter
    // ==========================================
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 3: Ghi đè phương thức toString()
    // ==========================================
    @Override
    public String toString() {
        return String.format("Student[ID='%s', Họ tên='%s', Ngày sinh=%s, Lớp='%s']",
                studentId, fullName, dateOfBirth, classroom);
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 3: Ghi đè phương thức equals() và hashCode()
    // Hai sinh viên bằng nhau nếu có cùng mã sinh viên (studentId).
    // ==========================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 3: Ghi đè phương thức compareTo()
    // Sắp xếp mặc định: So sánh theo Họ tên (Alphabetical Order).
    // ==========================================
    @Override
    public int compareTo(Student other) {
        if (this.fullName == null || other.fullName == null) {
            return 0;
        }
        return this.fullName.compareToIgnoreCase(other.fullName);
    }
}
