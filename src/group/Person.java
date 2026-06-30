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

    /**
     * So sánh hai chuỗi họ tên theo quy tắc tiếng Việt (Tên -> Tên đệm -> Họ).
     * Ví dụ: "Nguyễn Hoàng Long" sẽ đứng sau "Phạm Đức Anh" vì tên "Long" > "Anh".
     */
    public static int compareVietnameseNames(String name1, String name2) {
        if (name1 == null) return name2 == null ? 0 : -1;
        if (name2 == null) return 1;

        String[] parts1 = name1.trim().split("\\s+");
        String[] parts2 = name2.trim().split("\\s+");

        if (parts1.length == 0) return parts2.length == 0 ? 0 : -1;
        if (parts2.length == 0) return 1;

        // So sánh tên chính (từ cuối cùng) trước
        String firstName1 = parts1[parts1.length - 1];
        String firstName2 = parts2[parts2.length - 1];
        int cmp = firstName1.compareToIgnoreCase(firstName2);
        if (cmp != 0) {
            return cmp;
        }

        // Nếu trùng tên chính, so sánh từ đầu đến trước tên chính
        int minLen = Math.min(parts1.length, parts2.length);
        for (int i = 0; i < minLen - 1; i++) {
            cmp = parts1[i].compareToIgnoreCase(parts2[i]);
            if (cmp != 0) {
                return cmp;
            }
        }

        // Nếu tất cả các phần trùng nhau, so sánh theo độ dài mảng từ
        return Integer.compare(parts1.length, parts2.length);
    }

    @Override
    public String toString() {
        return String.format("Họ tên='%s', Ngày sinh=%s", fullName, dateOfBirth);
    }
}
