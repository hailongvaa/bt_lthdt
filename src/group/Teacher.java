package group;

import java.time.LocalDate;
import java.util.Objects;

/**
 * ============================================================================
 * BÀI TẬP NHÓM - NGÀY 4: Kế thừa (Inheritance)
 * Lớp Teacher đại diện cho Giảng viên, kế thừa từ Person.
 * 
 * KIẾN THỨC OOP ÁP DỤNG:
 * 1. Kế thừa (Inheritance):
 *    - Lớp `Teacher` kế thừa các thuộc tính và hành vi chung của `Person`.
 * 
 * 2. Từ khóa `super`:
 *    - `super(fullName, dateOfBirth)`: Gọi hàm dựng của Person để khởi tạo.
 *    - `super.toString()`: Gọi mô tả dạng chuỗi của Person.
 * 
 * 3. Ghi đè (Override):
 *    - `getRole()` trả về vai trò đặc trưng "Giảng viên".
 *    - Ghi đè equals, hashCode theo `teacherId`.
 * ============================================================================
 */
public class Teacher extends Person implements Comparable<Teacher> {
    private String teacherId;
    private String department;

    public Teacher() {
        super();
    }

    public Teacher(String teacherId, String fullName, LocalDate dateOfBirth, String department) {
        super(fullName, dateOfBirth);
        this.teacherId = teacherId;
        this.department = department;
    }

    public Teacher(String fullName, LocalDate dateOfBirth, String department) {
        super(fullName, dateOfBirth);
        this.teacherId = IdGenerator.generateTeacherId(); // Sinh mã GV tự động
        this.department = department;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getRole() {
        return "Giảng viên";
    }

    @Override
    public String toString() {
        return String.format("Teacher[ID='%s', %s, Khoa='%s']",
                teacherId, super.toString(), department);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId);
    }

    @Override
    public int compareTo(Teacher other) {
        return Person.compareVietnameseNames(this.fullName, other.fullName);
    }
}
