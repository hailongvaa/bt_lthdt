package group;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ============================================================================
 * BÀI TẬP NHÓM - NGÀY 4: Demo kế thừa và đa hình (Inheritance & Polymorphism)
 * 
 * KIẾN THỨC OOP TRỌNG TÂM ĐƯỢC DEMO TRONG LỚP NÀY:
 * 1. Tính Đa hình (Polymorphism) qua phân cấp kế thừa Person:
 *    - Tạo danh sách `List<Person> people` chứa cả đối tượng `Student` và `Teacher`.
 *    - Khi duyệt danh sách và gọi phương thức `p.getRole()`, mặc dù kiểu khai báo là `Person`,
 *      nhưng tại runtime, Java tự động gọi hàm `getRole()` tương ứng của `Student` (trả về "Sinh viên")
 *      hoặc `Teacher` (trả về "Giảng viên").
 * 
 * 2. Tính Đa hình qua Subject và Grade:
 *    - Môn học gồm `TheorySubject` và `PracticalSubject`.
 *    - Trong bảng điểm `Grade`, khi tính điểm tổng kết thông qua `getFinalGrade()`, JVM tự áp dụng 
 *      đúng công thức tính của môn lý thuyết (40/60) hoặc thực hành (50/50) mà không cần cấu trúc rẽ nhánh.
 * 
 * 3. Các kỹ thuật Sắp xếp Danh sách phức tạp (Multi-criteria Sorting):
 *    - Sắp xếp mặc định dùng `Comparable` (Điểm tổng kết giảm dần).
 *    - Sắp xếp bằng `Comparator` lớp vô danh (Theo tên Sinh viên).
 *    - Sắp xếp bằng `Lambda` với nhiều tiêu chí kết hợp (Theo tên môn học tăng dần, nếu trùng tên môn
 *      thì so sánh điểm tổng kết giảm dần).
 * ============================================================================
 */
public class GroupDemo {
    public static void main(String[] args) {
        System.out.println("=== CHƯƠNG TRÌNH QUẢN LÝ ĐIỂM SINH VIÊN (PHIÊN BẢN KẾ THỪA & ĐA HÌNH) ===");

        // 1. Tạo dữ liệu mẫu sử dụng Constructor tự động sinh ID (Ngày 2 & 4)
        System.out.println("\n--- Khởi tạo Sinh viên & Môn học (Ngày 2, 4) ---");
        
        Student sv1 = new Student("Nguyễn Hoàng Long", LocalDate.of(2005, 3, 12), "CNTT-01");
        Student sv2 = new Student("Trần Thu Trang", LocalDate.of(2005, 8, 25), "CNTT-02");
        Student sv3 = new Student("Lê Minh Quân", LocalDate.of(2005, 11, 5), "CNTT-01");
        Student sv4 = new Student("Phạm Đức Anh", LocalDate.of(2005, 1, 30), "CNTT-02");
        Student sv5 = new Student("Vũ Thị Mai", LocalDate.of(2005, 6, 18), "CNTT-01");

        // Sử dụng lớp con TheorySubject và PracticalSubject thể hiện Abstraction & Polymorphism (Ngày 4)
        Subject mh1 = new TheorySubject("Lập trình hướng đối tượng", 3);
        Subject mh2 = new TheorySubject("Cấu trúc dữ liệu & Giải thuật", 4);
        Subject mh3 = new TheorySubject("Cơ sở dữ liệu", 3);
        Subject mh4 = new PracticalSubject("Thực hành Lập trình hướng đối tượng", 1);
        Subject mh5 = new PracticalSubject("Thực hành Cơ sở dữ liệu", 1);

        System.out.println(sv1);
        System.out.println(sv2);
        System.out.println(mh1);
        System.out.println(mh4);

        // 2. Khởi tạo Giảng viên (Lớp Teacher mới - Ngày 4)
        System.out.println("\n--- Khởi tạo đối tượng Giảng viên (Ngày 4) ---");
        Teacher gv1 = new Teacher("Phạm Văn Hải", LocalDate.of(1980, 5, 20), "Công nghệ phần mềm");
        Teacher gv2 = new Teacher("Trần Thị Hồng", LocalDate.of(1985, 12, 10), "Hệ thống thông tin");
        System.out.println(gv1);
        System.out.println(gv2);

        // 3. Demo tính Đa hình của Person (Lưu danh sách gồm cả Student và Teacher)
        // Đây là ví dụ kinh điển về tính Đa hình (Polymorphism):
        // List lưu kiểu cha Person, nhưng chứa các tham chiếu đến các đối tượng con Student, Teacher.
        System.out.println("\n--- Demo tính Đa hình của Person (Ngày 4) ---");
        List<Person> people = new ArrayList<>();
        people.add(sv1);
        people.add(sv2);
        people.add(sv3);
        people.add(gv1);
        people.add(gv2);
        for (Person p : people) {
            // p.getRole() và p.toString() sẽ được liên kết động (Dynamic Binding) để gọi đúng lớp con thực tế
            System.out.printf("  - [%s] %s | %s\n", 
                    p.getRole(), p.getFullName(), p.toString());
        }

        // Tạo bảng điểm (Grade) liên kết Sinh viên và Môn học
        List<Grade> grades = new ArrayList<>();
        // Môn lý thuyết (OOP - 40% QT, 60% Thi)
        grades.add(new Grade(sv1, mh1, 8.5, 9.0)); // Long - OOP: 8.5*0.4 + 9.0*0.6 = 8.80
        grades.add(new Grade(sv2, mh1, 7.0, 6.5)); // Trang - OOP: 7.0*0.4 + 6.5*0.6 = 6.70
        grades.add(new Grade(sv3, mh1, 9.0, 9.5)); // Quân - OOP: 9.0*0.4 + 9.5*0.6 = 9.30
        
        // Môn thực hành (TH OOP - 50% QT, 50% Thi)
        grades.add(new Grade(sv1, mh4, 8.5, 9.0)); // Long - TH OOP: 8.5*0.5 + 9.0*0.5 = 8.75
        grades.add(new Grade(sv2, mh4, 7.0, 6.5)); // Trang - TH OOP: 7.0*0.5 + 6.5*0.5 = 6.75
        
        // Thêm một số điểm môn khác
        grades.add(new Grade(sv4, mh2, 5.5, 4.0)); // Anh - DSA (Theory): 5.5*0.4 + 4.0*0.6 = 4.60
        grades.add(new Grade(sv5, mh2, 8.0, 8.0)); // Mai - DSA (Theory): 8.0*0.4 + 8.0*0.6 = 8.00

        // 4. Kiểm chứng equals() và hashCode() (Ngày 3)
        System.out.println("\n--- Kiểm chứng equals() và hashCode() (Ngày 3) ---");
        Student svDuplicate = new Student(sv1.getStudentId(), "Nguyễn H. Long", LocalDate.of(2005, 3, 12), "CNTT-01");
        System.out.println("sv1 ID: " + sv1.getStudentId() + " | Tên: " + sv1.getFullName());
        System.out.println("svDuplicate ID: " + svDuplicate.getStudentId() + " | Tên: " + svDuplicate.getFullName());
        System.out.println("sv1.equals(svDuplicate)? -> " + sv1.equals(svDuplicate));
        System.out.println("sv1.hashCode() == svDuplicate.hashCode()? -> " + (sv1.hashCode() == svDuplicate.hashCode()));

        // 5. Sắp xếp danh sách điểm theo nhiều tùy chọn (Ngày 3 - B2 & Ngày 4)
        System.out.println("\n--- Bảng điểm gốc (Chưa sắp xếp) ---");
        printGradeList(grades);

        // Tùy chọn 1: Sắp xếp mặc định bằng Comparable (Theo Điểm tổng kết giảm dần)
        System.out.println("\n--- Sắp xếp 1: Điểm tổng kết giảm dần (Mặc định - Comparable) ---");
        Collections.sort(grades);
        printGradeList(grades);

        // Tùy chọn 2: Sắp xếp theo tên sinh viên tăng dần (Alphabetical - sử dụng Comparator)
        System.out.println("\n--- Sắp xếp 2: Theo tên sinh viên Alphabetical (Comparator) ---");
        grades.sort(new Comparator<Grade>() {
            @Override
            public int compare(Grade g1, Grade g2) {
                return g1.getStudent().getFullName().compareToIgnoreCase(g2.getStudent().getFullName());
            }
        });
        printGradeList(grades);

        // Tùy chọn 3: Sắp xếp theo tên môn học rồi đến điểm tổng kết giảm dần (Sử dụng biểu thức Lambda)
        System.out.println("\n--- Sắp xếp 3: Theo Tên môn học -> Điểm tổng kết giảm dần ---");
        grades.sort((g1, g2) -> {
            // Sắp xếp môn học trước
            int compSubj = g1.getSubject().getSubjectName().compareToIgnoreCase(g2.getSubject().getSubjectName());
            if (compSubj != 0) {
                return compSubj;
            }
            // Nếu trùng môn học, sắp xếp theo điểm tổng kết giảm dần
            return Double.compare(g2.getFinalGrade(), g1.getFinalGrade());
        });
        printGradeList(grades);

        // Sắp xếp danh sách Sinh viên riêng biệt theo Lớp -> Tên (Ngày 3 - B2)
        List<Student> students = new ArrayList<>();
        students.add(sv1);
        students.add(sv2);
        students.add(sv3);
        students.add(sv4);
        students.add(sv5);

        System.out.println("\n--- Danh sách sinh viên ban đầu ---");
        printStudentList(students);

        System.out.println("\n--- Sắp xếp Sinh viên theo Lớp -> Họ tên tăng dần ---");
        students.sort((s1, s2) -> {
            int compClass = s1.getClassroom().compareToIgnoreCase(s2.getClassroom());
            if (compClass != 0) {
                return compClass;
            }
            return s1.getFullName().compareToIgnoreCase(s2.getFullName());
        });
        printStudentList(students);
    }

    private static void printGradeList(List<Grade> list) {
        for (Grade g : list) {
            String subjectType = (g.getSubject() instanceof TheorySubject) ? "Lý thuyết" : "Thực hành";
            System.out.printf("  - SV: %-18s (%s) | Môn: %-30s (%-10s) | QT: %3.1f | Thi: %3.1f | TK: %4.2f (%s)\n",
                    g.getStudent().getFullName(),
                    g.getStudent().getStudentId(),
                    g.getSubject().getSubjectName(),
                    subjectType,
                    g.getProcessGrade(),
                    g.getExamGrade(),
                    g.getFinalGrade(),
                    g.getGradeLetter());
        }
    }

    private static void printStudentList(List<Student> list) {
        for (Student s : list) {
            System.out.printf("  - ID: %s | Tên: %-18s | Ngày sinh: %s | Lớp: %s\n",
                    s.getStudentId(), s.getFullName(), s.getDateOfBirth(), s.getClassroom());
        }
    }
}
