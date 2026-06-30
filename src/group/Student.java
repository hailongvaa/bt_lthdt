package group;

import java.time.LocalDate;
import java.util.Objects;

/**
 * ============================================================================
 * Lớp biểu diễn thông tin Sinh viên (Student) trong hệ thống Quản lý điểm.
 * 
 * KIẾN THỨC OOP ÁP DỤNG:
 * 1. Kế thừa (Inheritance):
 *    - Sử dụng từ khóa `extends Person` để kế thừa toàn bộ thuộc tính và hành vi
 *      của lớp cha Person (như fullName, dateOfBirth).
 * 
 * 2. Từ khóa `super`:
 *    - `super(fullName, dateOfBirth)` trong constructor: Gọi đến constructor tương ứng
 *      của lớp cha Person để khởi tạo giá trị cho các thuộc tính dùng chung.
 *    - `super.toString()`: Gọi phương thức `toString()` của lớp cha Person để tái sử dụng
 *      chuỗi mô tả họ tên và ngày sinh, giảm thiểu lặp code.
 * 
 * 3. Ghi đè phương thức (Method Overriding):
 *    - Triển khai phương thức trừu tượng `getRole()` của lớp cha, trả về giá trị "Sinh viên".
 *    - Ghi đè `toString()`, `equals()` và `hashCode()` theo logic quản lý của sinh viên (so sánh bằng studentId).
 * 
 * 4. Hiện thực hóa Interface `Comparable`:
 *    - Implement `Comparable<Student>` để cung cấp cách so sánh mặc định khi sắp xếp
 *      sinh viên, ở đây sắp xếp theo Họ tên (`fullName`) tăng dần.
 * ============================================================================
 */
public class Student extends Person implements Comparable<Student> {

    // Thuộc tính đặc trưng riêng của Sinh viên bên cạnh các thuộc tính kế thừa từ Person
    private String studentId;
    private String classroom;

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 4: Các hàm khởi tạo (Constructors)
    // ==========================================
    public Student() {
        super(); // Gọi constructor mặc định của lớp cha Person
    }

    public Student(String studentId, String fullName, LocalDate dateOfBirth, String classroom) {
        super(fullName, dateOfBirth); // Ủy quyền khởi tạo các thuộc tính cha cho Person
        this.studentId = studentId;
        this.classroom = classroom;
    }

    // Constructor tự động sinh ID sử dụng IdGenerator
    public Student(String fullName, LocalDate dateOfBirth, String classroom) {
        super(fullName, dateOfBirth); // Ủy quyền khởi tạo cho Person
        this.studentId = IdGenerator.generateStudentId(); // Sinh mã SV tự động
        this.classroom = classroom;
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 4: Các phương thức Getter và Setter
    // ==========================================
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 4: Ghi đè phương thức getRole() từ Person
    // Thể hiện tính Đa hình: Khi đối tượng Person thực tế là Student, Java sẽ gọi phiên bản này.
    // ==========================================
    @Override
    public String getRole() {
        return "Sinh viên";
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 4: Ghi đè phương thức toString() sử dụng super.toString()
    // ==========================================
    @Override
    public String toString() {
        return String.format("Student[ID='%s', %s, Lớp='%s']",
                studentId, super.toString(), classroom);
    }

    // ==========================================
    // BÀI TẬP NHÓM - NGÀY 3 & 4: Ghi đè phương thức equals() và hashCode()
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
    // BÀI TẬP NHÓM - NGÀY 3 & 4: Ghi đè phương thức compareTo() từ Comparable
    // Sắp xếp mặc định: So sánh theo Họ tên (Alphabetical Order).
    // ==========================================
    @Override
    public int compareTo(Student other) {
        return Person.compareVietnameseNames(this.fullName, other.fullName);
    }
}
