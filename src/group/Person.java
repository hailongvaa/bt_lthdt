package group;

import java.time.LocalDate;

/**
 * ============================================================================
 * BÀI TẬP NHÓM - NGÀY 4: Kế thừa (Inheritance) & Trừu tượng (Abstraction)
 * 
 * KIẾN THỨC OOP ÁP DỤNG:
 * 1. Lớp trừu tượng (Abstract Class):
 *    - `Person` đại diện cho một cá nhân chung chung (chưa rõ là Sinh viên hay Giảng viên).
 *    - Được khai báo với từ khóa `abstract`. Ta không thể dùng toán tử `new` để tạo
 *      trực tiếp đối tượng từ lớp này (ví dụ: `new Person(...)` sẽ báo lỗi biên dịch).
 *    - Phù hợp làm lớp cha để định nghĩa các đặc tính chung nhất cho các thực thể kế thừa.
 * 
 * 2. Thuộc tính được bảo vệ (Protected Fields):
 *    - Sử dụng từ khóa `protected` cho `fullName` và `dateOfBirth`.
 *    - Cho phép các lớp con kế thừa từ Person (như Student, Teacher) truy cập trực tiếp
 *      vào các thuộc tính này mà không cần gọi getter/setter ở lớp cha.
 * 
 * 3. Phương thức trừu tượng (Abstract Method):
 *    - Phương thức `getRole()` không có phần thân (body), chỉ khai báo tên và kiểu trả về.
 *    - Ép buộc tất cả các lớp con cụ thể (concrete subclasses) kế thừa từ `Person` 
 *      phải bắt buộc ghi đè (override) để hiện thực hóa phương thức này theo đúng nghiệp vụ 
 *      của riêng chúng.
 * ============================================================================
 */
public abstract class Person {
    protected String fullName;
    protected LocalDate dateOfBirth;

    public Person() {
    }

    public Person(String fullName, LocalDate dateOfBirth) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
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

    /**
     * Phương thức trừu tượng để lấy vai trò của đối tượng.
     * Sẽ được đa hình hóa tại các lớp con.
     */
    public abstract String getRole();

    @Override
    public String toString() {
        return String.format("Họ tên='%s', Ngày sinh=%s", fullName, dateOfBirth);
    }
}
