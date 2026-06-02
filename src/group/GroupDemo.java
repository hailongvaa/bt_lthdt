package group;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ==========================================
 * BÀI TẬP NHÓM - NGÀY 3: Demo sắp xếp và chạy chương trình
 * ==========================================
 */
public class GroupDemo {
    public static void main(String[] args) {
        System.out.println("=== CHƯƠNG TRÌNH QUẢN LÝ ĐIỂM SINH VIÊN ===");

        // 1. Tạo dữ liệu mẫu sử dụng Constructor tự động sinh ID (Ngày 2)
        System.out.println("\n--- Khởi tạo đối tượng & Tự động sinh ID (Ngày 2) ---");
        
        Student sv1 = new Student("Nguyễn Hoàng Long", LocalDate.of(2005, 3, 12), "CNTT-01");
        Student sv2 = new Student("Trần Thu Trang", LocalDate.of(2005, 8, 25), "CNTT-02");
        Student sv3 = new Student("Lê Minh Quân", LocalDate.of(2005, 11, 5), "CNTT-01");
        Student sv4 = new Student("Phạm Đức Anh", LocalDate.of(2005, 1, 30), "CNTT-02");
        Student sv5 = new Student("Vũ Thị Mai", LocalDate.of(2005, 6, 18), "CNTT-01");

        Subject mh1 = new Subject("Lập trình hướng đối tượng", 3);
        Subject mh2 = new Subject("Cấu trúc dữ liệu & Giải thuật", 4);
        Subject mh3 = new Subject("Cơ sở dữ liệu", 3);

        System.out.println(sv1);
        System.out.println(sv2);
        System.out.println(sv3);
        System.out.println(mh1);
        System.out.println(mh2);

        // Tạo bảng điểm (Grade) liên kết Sinh viên và Môn học
        List<Grade> grades = new ArrayList<>();
        grades.add(new Grade(sv1, mh1, 8.5, 9.0)); // Long - OOP
        grades.add(new Grade(sv2, mh1, 7.0, 6.5)); // Trang - OOP
        grades.add(new Grade(sv3, mh1, 9.0, 9.5)); // Quân - OOP
        grades.add(new Grade(sv4, mh1, 5.5, 4.0)); // Anh - OOP
        grades.add(new Grade(sv5, mh1, 8.0, 8.0)); // Mai - OOP
        
        // Thêm một số điểm môn khác để đa dạng
        grades.add(new Grade(sv1, mh2, 7.5, 8.0)); // Long - DSA
        grades.add(new Grade(sv2, mh2, 9.0, 8.5)); // Trang - DSA

        // 2. Kiểm chứng equals() và hashCode() (Ngày 3 - B1)
        System.out.println("\n--- Kiểm chứng equals() và hashCode() (Ngày 3) ---");
        Student svDuplicate = new Student(sv1.getStudentId(), "Nguyễn H. Long", LocalDate.of(2005, 3, 12), "CNTT-01");
        System.out.println("sv1 ID: " + sv1.getStudentId() + " | Tên: " + sv1.getFullName());
        System.out.println("svDuplicate ID: " + svDuplicate.getStudentId() + " | Tên: " + svDuplicate.getFullName());
        System.out.println("sv1.equals(svDuplicate)? -> " + sv1.equals(svDuplicate));
        System.out.println("sv1.hashCode() == svDuplicate.hashCode()? -> " + (sv1.hashCode() == svDuplicate.hashCode()));

        // 3. Sắp xếp danh sách điểm theo nhiều tùy chọn (Ngày 3 - B2)
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

        // Tùy chọn 3: Sắp xếp theo tên môn học rồi đến điểm tổng kết giảm dần
        System.out.println("\n--- Sắp xếp 3: Theo Tên môn học -> Điểm tổng kết giảm dần ---");
        grades.sort((g1, g2) -> {
            int compSubj = g1.getSubject().getSubjectName().compareToIgnoreCase(g2.getSubject().getSubjectName());
            if (compSubj != 0) {
                return compSubj;
            }
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
            System.out.printf("  - SV: %-18s (%s) | Môn: %-25s | QT: %3.1f | Thi: %3.1f | TK: %4.2f (%s)\n",
                    g.getStudent().getFullName(),
                    g.getStudent().getStudentId(),
                    g.getSubject().getSubjectName(),
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
