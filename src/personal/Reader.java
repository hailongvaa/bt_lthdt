package personal;

import java.util.Objects;

/**
 * ============================================================================
 * Lớp biểu diễn thông tin Độc giả (Reader) trong Thư viện mini.
 * 
 * KIẾN THỨC OOP ÁP DỤNG:
 * 1. Đóng gói (Encapsulation):
 *    - Các thuộc tính (readerId, name, email, phoneNumber, address) đều là private.
 *    - Cung cấp các phương thức public getter/setter để tương tác với dữ liệu.
 * 
 * 2. Nạp chồng Constructor (Constructor Overloading):
 *    - Constructor mặc định không tham số.
 *    - Constructor đầy đủ tham số để truyền mã độc giả tự định nghĩa.
 *    - Constructor tự động sinh mã độc giả bằng cách gọi `IdGenerator.generateReaderId()`.
 * 
 * 3. Ghi đè phương thức (Method Overriding) & Đa hình:
 *    - `toString()`: Trả về thông tin hiển thị định dạng chuỗi dễ đọc.
 *    - `equals()` và `hashCode()`: Định nghĩa cơ chế so sánh bằng nhau dựa trên thuộc tính
 *      `readerId` thay vì địa chỉ ô nhớ mặc định của đối tượng.
 * 
 * 4. So sánh đối tượng (`Comparable`):
 *    - Kế thừa `Comparable<Reader>` và ghi đè `compareTo(Reader other)`.
 *    - Quy định cách thức sắp xếp mặc định là theo Tên độc giả (name) tăng dần (Alphabetical).
 * ============================================================================
 */
public class Reader implements Comparable<Reader> {

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Định nghĩa dữ liệu (Fields)
    // ==========================================
    private String readerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Constructor mặc định
    // ==========================================
    public Reader() {
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Constructor đầy đủ tham số
    // ==========================================
    public Reader(String readerId, String name, String email, String phoneNumber, String address) {
        this.readerId = readerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 2: Constructor tự động sinh ID sử dụng IdGenerator
    // ==========================================
    public Reader(String name, String email, String phoneNumber, String address) {
        this.readerId = IdGenerator.generateReaderId(); // Tự động sinh ID duy nhất
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Các phương thức Getter và Setter
    // ==========================================
    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 3: Ghi đè phương thức toString()
    // ==========================================
    @Override
    public String toString() {
        return String.format("Reader[ID='%s', Tên='%s', Email='%s', SĐT='%s', Địa chỉ='%s']",
                readerId, name, email, phoneNumber, address);
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 3: Ghi đè phương thức equals() và hashCode()
    // Hai độc giả bằng nhau nếu có cùng mã độc giả (readerId).
    // ==========================================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return Objects.equals(readerId, reader.readerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readerId);
    }

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 3: Ghi đè phương thức compareTo() từ Comparable
    // Sắp xếp mặc định: So sánh theo Tên độc giả (Alphabetical Order).
    // ==========================================
    @Override
    public int compareTo(Reader other) {
        if (this.name == null || other.name == null) {
            return 0;
        }
        return this.name.compareToIgnoreCase(other.name);
    }
}
