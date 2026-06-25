# Tài Liệu Hướng Dẫn Bài Tập Lập Trình Hướng Đối Tượng (OOP)

Chào mừng bạn đến với dự án bài tập OOP cá nhân và nhóm. Tài liệu này giúp bạn hiểu cấu trúc mã nguồn, ý nghĩa của các lớp và cách chạy thử nghiệm chương trình.

---

## 📌 Cấu trúc dự án
Mã nguồn được tổ chức thành 2 package chính nằm trong thư mục `src`:
* **`personal`** (Quản lý thư viện mini - Bài tập cá nhân)
* **`group`** (Quản lý điểm sinh viên - Bài tập nhóm)

```
d:\btvn_ltcb\
├── README.md               <-- File này (Hướng dẫn chung)
├── src\
│   ├── personal\           <-- Bài tập cá nhân
│   │   ├── IdGenerator.java
│   │   ├── Book.java
│   │   ├── Reader.java
│   │   ├── Loan.java
│   │   └── PersonalDemo.java
│   └── group\              <-- Bài tập nhóm
│       ├── IdGenerator.java
│       ├── Person.java
│       ├── Student.java
│       ├── Teacher.java
│       ├── Subject.java
│       ├── TheorySubject.java
│       ├── PracticalSubject.java
│       ├── Grade.java
│       └── GroupDemo.java
└── bin\                    <-- Chứa các file .class sau khi biên dịch (tự động tạo)
```

---

## 📅 Nội dung bài học và Hiện thực qua từng ngày

### 🗓️ NGÀY 1: Định nghĩa dữ liệu và phương thức cơ bản
* **Mục tiêu**: Làm quen với cách khai báo thuộc tính (fields), constructor (hàm khởi tạo), phương thức getter/setter, và các nghiệp vụ cơ bản trong Java.
* **Chi tiết hiện thực**:
  * **Bài cá nhân**: Khai báo các thuộc tính cơ bản cho `Book` (Sách), `Reader` (Độc giả), và `Loan` (Phiếu mượn). Cung cấp đầy đủ hàm khởi tạo không tham số, đầy đủ tham số và các getter/setter.
  * **Bài nhóm**: Khai báo thực thể học tập bao gồm `Student` (Sinh viên), `Subject` (Môn học), và lớp liên kết `Grade` (Điểm số). Trong `Grade`, viết thêm phương thức nghiệp vụ để tính điểm tổng kết và quy đổi điểm chữ học lực (A, B, C, D, F).

### 🗓️ NGÀY 2: Bổ sung lớp `IdGenerator` để sinh mã tự động
* **Mục tiêu**: Hiểu về từ khóa `static` trong Java và cách tạo ra các công cụ sinh dữ liệu duy nhất mà không cần truyền thủ công từ bên ngoài.
* **Chi tiết hiện thực**:
  * Cả hai gói `personal` và `group` đều có một lớp `IdGenerator`.
  * Các biến bộ đếm `counter` được khai báo dạng `private static` để lưu trạng thái đếm xuyên suốt chương trình.
  * Phương thức sinh mã dạng `public static synchronized` giúp sinh các mã định dạng chuẩn như `B-1001`, `R-1001`, `SV2026001` một cách an toàn.
  * Cập nhật các constructor của các đối tượng để tự động gọi `IdGenerator` khi tạo đối tượng mới (ví dụ: `new Student("Tên",...)` sẽ tự có ID mà không cần nhập ID).

### 🗓️ NGÀY 3: Nạp chồng / Ghi đè phương thức hệ thống và Sắp xếp nâng cao
* **Mục tiêu**: Nắm vững tính đa hình thông qua Ghi đè (Override), hiểu về cơ chế quản lý đối tượng của Java Virtual Machine (JVM) và cách sắp xếp danh sách đối tượng.
* **Chi tiết hiện thực**:
  1. **Ghi đè `toString()`**: Chuyển đổi trạng thái đối tượng thành chuỗi văn bản dễ đọc để phục vụ in ấn/debug.
  2. **Ghi đè `equals()` & `hashCode()`**: Định nghĩa tiêu chí so sánh hai đối tượng bằng nhau (so sánh theo mã ID duy nhất thay vì so sánh tham chiếu ô nhớ mặc định).
  3. **Triển khai `Comparable` (so sánh mặc định)**: 
     * Lớp implement interface `Comparable<T>` và ghi đè phương thức `compareTo(T o)`.
     * Giúp danh sách đối tượng tự động sắp xếp được bằng lệnh `Collections.sort(list)`.
  4. **Triển khai `Comparator` (sắp xếp theo nhiều tùy chọn)**:
     * Dùng khi muốn sắp xếp theo các tiêu chí khác nhau (ví dụ: Sách sắp theo Năm xuất bản, Số lượng; Điểm sắp theo tên Sinh viên hoặc tên Môn học).
     * Triển khai bằng Anonymous Class hoặc biểu thức Lambda.

### 🗓️ NGÀY 4: Áp dụng Kế thừa (Inheritance) và Đa hình (Polymorphism)
* **Mục tiêu**: Vận dụng các đặc trưng cốt lõi của Lập trình hướng đối tượng (Kế thừa, Đa hình, Trừu tượng hóa, Đóng gói) để xây dựng hệ thống phân cấp thực thể và giải quyết các logic nghiệp vụ linh hoạt.
* **Chi tiết hiện thực**:
  1. **Nhánh kế thừa `Person`**:
     * Khai báo lớp trừu tượng `Person` chứa thông tin chung (`fullName`, `dateOfBirth`) và phương thức abstract `getRole()`.
     * Cho lớp `Student` kế thừa `Person`, bổ sung `studentId`, `classroom` và ghi đè `getRole()`.
     * Tạo thêm lớp con `Teacher` (Giảng viên) kế thừa `Person`, bổ sung `teacherId`, `department` và ghi đè `getRole()`.
     * Thực nghiệm tính đa hình: Quản lý danh sách chung dạng `List<Person>` chứa cả đối tượng `Student` và `Teacher`, duyệt danh sách để hiển thị thông tin đa hình.
  2. **Nhánh kế thừa `Subject`**:
     * Chuyển `Subject` thành lớp trừu tượng và khai báo phương thức trừu tượng `calculateFinalGrade(processGrade, examGrade)`.
     * Tạo lớp con `TheorySubject` (Môn lý thuyết) kế thừa `Subject`, tính điểm tổng kết theo công thức: **40% quá trình + 60% thi cuối kỳ**.
     * Tạo lớp con `PracticalSubject` (Môn thực hành) kế thừa `Subject`, tính điểm tổng kết theo công thức: **50% quá trình + 50% thi cuối kỳ**.
     * Thực nghiệm tính đa hình trong lớp `Grade`: Phương thức `getFinalGrade()` gọi phương thức đa hình `subject.calculateFinalGrade(...)`. Bảng điểm tự động áp dụng đúng công thức tính điểm của từng loại môn học mà không cần dùng cấu trúc rẽ nhánh `if-else` phức tạp.

---

## 🛠️ Hướng dẫn Biên dịch và Chạy chương trình

Do mã nguồn sử dụng các ghi chú tiếng Việt có dấu, bạn cần chỉ định bảng mã UTF-8 khi làm việc trên terminal Windows.

### 1. Biên dịch mã nguồn
Mở CMD hoặc PowerShell tại thư mục `d:\btvn_ltcb` và chạy:
```powershell
javac -encoding UTF-8 -d bin src/personal/*.java src/group/*.java
```

### 2. Chạy Demo phần cá nhân (Quản lý thư viện)
```powershell
java "-Dfile.encoding=UTF-8" -cp bin personal.PersonalDemo
```

### 3. Chạy Demo phần nhóm (Quản lý điểm sinh viên)
```powershell
java "-Dfile.encoding=UTF-8" -cp bin group.GroupDemo
```

---

## 💡 Các kiến thức OOP chính được áp dụng trong bài tập
* **Encapsulation (Đóng gói)**: Sử dụng các phạm vi truy cập `private` cho thuộc tính và cung cấp `public getter/setter` để bảo vệ dữ liệu.
* **Inheritance & Polymorphism (Kế thừa & Đa hình)**: Ghi đè các phương thức từ lớp cha cao nhất `Object` (`toString`, `equals`, `hashCode`) và kế thừa interface `Comparable`.
* **Abstraction (Trừu tượng hóa)**: Mô hình hóa các thực thể ngoài đời thực (Sách, Sinh viên, Bảng điểm) thành các lớp trong chương trình.

---

## 👥 Phân chia công việc nhóm (src/group)

Dưới đây là bảng phân chia công việc chi tiết cho từng thành viên trong nhóm 6 người thực hiện phần bài tập nhóm (`src/group`), bao gồm phạm vi file và các dòng mã nguồn cụ thể mà mỗi thành viên chịu trách nhiệm phát triển:

| Thành viên | File đảm nhận | Dòng code chi tiết | Nội dung công việc chính |
| :--- | :--- | :--- | :--- |
| **Nguyễn Hải Long** (Trưởng nhóm) | [IdGenerator.java](file:///d:/btvn_ltcb/src/group/IdGenerator.java)<br>[GroupDemo.java](file:///d:/btvn_ltcb/src/group/GroupDemo.java) | [IdGenerator.java:L1-52](file:///d:/btvn_ltcb/src/group/IdGenerator.java#L1-L52) (Toàn bộ)<br>[GroupDemo.java:L1-179](file:///d:/btvn_ltcb/src/group/GroupDemo.java#L1-L179) (Toàn bộ) | - Xây dựng bộ sinh ID tự động tĩnh (`static synchronized`).<br>- Viết hàm main điều khiển, khởi tạo dữ liệu mẫu, demo đa hình và chạy các kịch bản sắp xếp. |
| **Phương Linh** | [Person.java](file:///d:/btvn_ltcb/src/group/Person.java)<br>[Student.java](file:///d:/btvn_ltcb/src/group/Student.java) | [Person.java:L1-67](file:///d:/btvn_ltcb/src/group/Person.java#L1-L67) (Toàn bộ)<br>[Student.java:L1-74](file:///d:/btvn_ltcb/src/group/Student.java#L1-L74) | - Thiết lập lớp trừu tượng cha [Person](file:///d:/btvn_ltcb/src/group/Person.java) (thuộc tính protected, hàm dựng, getter/setter).<br>- Định nghĩa phần cơ bản của lớp [Student](file:///d:/btvn_ltcb/src/group/Student.java) kế thừa `Person` (thuộc tính riêng, hàm dựng ủy quyền `super` và getter/setter). |
| **Khánh Linh** | [Student.java](file:///d:/btvn_ltcb/src/group/Student.java)<br>[Teacher.java](file:///d:/btvn_ltcb/src/group/Teacher.java) | [Student.java:L75-122](file:///d:/btvn_ltcb/src/group/Student.java#L75-L122)<br>[Teacher.java:L1-92](file:///d:/btvn_ltcb/src/group/Teacher.java#L1-L92) (Toàn bộ) | - Hoàn thiện các phương thức đa hình và so sánh của [Student](file:///d:/btvn_ltcb/src/group/Student.java) (`getRole()`, `toString()`, `equals()`, `hashCode()`, `compareTo()`).<br>- Thiết lập lớp [Teacher](file:///d:/btvn_ltcb/src/group/Teacher.java) kế thừa `Person` đầy đủ các hàm dựng, getter/setter, đa hình và Comparable. |
| **Phúc** | [Subject.java](file:///d:/btvn_ltcb/src/group/Subject.java) | [Subject.java:L1-125](file:///d:/btvn_ltcb/src/group/Subject.java#L1-L125) (Toàn bộ) | - Xây dựng lớp trừu tượng [Subject](file:///d:/btvn_ltcb/src/group/Subject.java) định nghĩa khung môn học, phương thức tính điểm trừu tượng `calculateFinalGrade()` và các phương thức so sánh mặc định. |
| **Hân** | [TheorySubject.java](file:///d:/btvn_ltcb/src/group/TheorySubject.java)<br>[PracticalSubject.java](file:///d:/btvn_ltcb/src/group/PracticalSubject.java) | [TheorySubject.java:L1-49](file:///d:/btvn_ltcb/src/group/TheorySubject.java#L1-L49) (Toàn bộ)<br>[PracticalSubject.java:L1-49](file:///d:/btvn_ltcb/src/group/PracticalSubject.java#L1-L49) (Toàn bộ) | - Hiện thực hóa lớp môn học lý thuyết [TheorySubject](file:///d:/btvn_ltcb/src/group/TheorySubject.java) (tính điểm theo tỉ lệ 40%/60% và làm tròn).<br>- Hiện thực hóa lớp môn học thực hành [PracticalSubject](file:///d:/btvn_ltcb/src/group/PracticalSubject.java) (tính điểm theo tỉ lệ 50%/50% và làm tròn). |
| **Kim Ngân** | [Grade.java](file:///d:/btvn_ltcb/src/group/Grade.java) | [Grade.java:L1-156](file:///d:/btvn_ltcb/src/group/Grade.java#L1-L156) (Toàn bộ) | - Thiết kế lớp liên kết [Grade](file:///d:/btvn_ltcb/src/group/Grade.java) kết nối `Student` và `Subject`.<br>- Hiện thực tính đa hình động qua môn học (`getFinalGrade()`), quy đổi điểm chữ, override so sánh mặc định (`Comparable`). |

# bt_lthdt
