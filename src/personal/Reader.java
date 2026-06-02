package personal;

import java.util.Objects;

/**
 * Lớp biểu diễn thông tin Độc giả (Reader) trong Thư viện mini.
 * Chứa các chú thích cho Ngày 1, Ngày 2 và Ngày 3.
 */
public class Reader implements Comparable<Reader> { // NGÀY 3: implements Comparable để hỗ trợ so sánh mặc định

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Định nghĩa dữ liệu (Fields)
    // ==========================================
    private String readerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;

    // ==========================================
    // BÀI TẬP CÁ NHÂN - NGÀY 1: Constructor mặc định & Constructor đầy đủ tham số
    // ==========================================
    public Reader() {
    }

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
        this.readerId = IdGenerator.generateReaderId(); // Sử dụng IdGenerator để sinh mã độc giả tự động
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
    // BÀI TẬP CÁ NHÂN - NGÀY 3: Ghi đè phương thức compareTo()
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
