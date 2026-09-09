import QuanLySanPham.LuuTruSanPham;
import QuanLySinhVien.LuuTruSinhVien;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int chon;

        do {
            System.out.println("\n================ MỜI BẠN CHỌN BÀI TẬP ================");
            System.out.println("1. Quản Lý Sinh Viên (Đề 1)");
            System.out.println("2. Quản Lý Sản Phẩm  (Đề 2)");
            System.out.println("0. Thoát chương trình");
            System.out.print("Nhập lựa chọn (0-2): ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                chon = -1;
            }

            switch (chon) {
                case 1 -> LuuTruSinhVien.Menu(sc);
                case 2 -> LuuTruSanPham.Menu(sc);
                case 0 -> System.out.println("\nCảm ơn bạn đã sử dụng chương trình. Tạm biệt!");
                default -> System.out.println("\nLựa chọn không hợp lệ, vui lòng thử lại!");
            }
        } while (chon != 0);

        sc.close();
    }
}