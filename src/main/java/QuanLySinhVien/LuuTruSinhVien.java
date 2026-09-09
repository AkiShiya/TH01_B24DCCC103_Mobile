package QuanLySinhVien;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LuuTruSinhVien {
    private static final String FILE_NAME = "danh_sach_sinh_vien.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Đọc dữ liệu
    public static List<SinhVien> docFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();
        try (Reader reader = new FileReader(file)){
            List<SinhVien> list = gson.fromJson(reader, new TypeToken<ArrayList<SinhVien>>(){}.getType());
            return list != null ? list : new ArrayList<>();
        } catch (IOException e){
            return new ArrayList<>();
        }
    }

    // Lưu dự liệu
    public static void luuFile(List<SinhVien> list){
        try (Writer writer = new FileWriter(FILE_NAME)){
            gson.toJson(list, writer);
            System.out.println("Đã lưu dữ liệu thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi lưu dữ liệu: " + e.getMessage());
        }
    }

    // Nhập sinh viên cần thêm
    public static void nhapDanhSach(List<SinhVien> ds, Scanner sc) {
        int n = 0;
        while (true) {
            try {
                System.out.print("\nNhập số lượng sinh viên cần thêm: ");
                n = Integer.parseInt(sc.nextLine());
                if (n > 0) break;
                System.out.println("Số lượng phải lớn hơn 0!");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ!");
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println("\nNhập sinh viên thứ " + (i + 1) + ":");
            SinhVien sv = new SinhVien();
            sv.nhap(sc);
            ds.add(sv);
        }
        luuFile(ds);
    }

    // In danh sách sinh viên kèm xếp loại học lực
    public static void inDanhSach(List<SinhVien> ds) {
        if (ds.isEmpty()) {
            System.out.println("\nDanh sách sinh viên đang trống!");
            return;
        }
        System.out.println("\n================ DANH SÁCH SINH VIÊN ================");
        System.out.printf("%-12s %-22s %-10s %-10s %-12s\n", "Mã SV", "Họ Tên", "Năm Sinh", "Điểm TB", "Xếp Loại");
        System.out.println("------------------------------------------------------------------");
        for (SinhVien sv : ds) {
            sv.xuat();
        }
    }

    //Tìm và hiển thị sinh viên có điểm trung bình cao nhất
    public static void timMaxDiemTB(List<SinhVien> ds) {
        if (ds.isEmpty()) {
            System.out.println("\nDanh sách sinh viên đang trống!");
            return;
        }
        double maxDiem = ds.get(0).getDiemTB();
        for (SinhVien sv : ds) {
            if (sv.getDiemTB() > maxDiem) {
                maxDiem = sv.getDiemTB();
            }
        }
        System.out.println("\n================ SINH VIÊN CÓ ĐIỂM TB CAO NHẤT ================");
        System.out.printf("%-12s %-22s %-10s %-10s %-12s\n", "Mã SV", "Họ Tên", "Năm Sinh", "Điểm TB", "Xếp Loại");
        System.out.println("------------------------------------------------------------------");
        for (SinhVien sv : ds) {
            if (sv.getDiemTB() == maxDiem) {
                sv.xuat();
            }
        }
    }

    // Xóa danh sách hiện tại
    public static void xoaDanhSach(List<SinhVien> ds, Scanner sc) {
        if (ds.isEmpty()) {
            System.out.println("\nDanh sách hiện tại đã trống, không có gì để xóa!");
            return;
        }
        System.out.print("\nBạn có chắc chắn muốn xóa TOÀN BỘ sinh viên không? (y/n): ");
        String xacNhan = sc.nextLine();
        if (xacNhan.equalsIgnoreCase("y")) {
            ds.clear();
            luuFile(ds);
            System.out.println("Đã xóa toàn bộ danh sách sinh viên!");
        } else {
            System.out.println("Hủy thao tác xóa.");
        }
    }

    public static void Menu(Scanner sc) {
        List<SinhVien> ds = docFile();
        int chon = 0;
        do {
            System.out.println("\n----------- QUẢN LÝ SINH VIÊN -----------");
            System.out.println("Dữ liệu hiện có: " + ds.size() + " sinh viên.");
            System.out.println("1. Nhập danh sách sinh viên");
            System.out.println("2. In danh sách sinh viên kèm xếp loại");
            System.out.println("3. Tìm sinh viên có điểm trung bình cao nhất");
            System.out.println("4. Xóa toàn bộ danh sách sinh viên");
            System.out.println("5. Quay lại Menu chính");
            System.out.print("Mời bạn chọn (1-5): ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                chon = 0;
            }

            switch (chon) {
                case 1 -> nhapDanhSach(ds, sc);
                case 2 -> inDanhSach(ds);
                case 3 -> timMaxDiemTB(ds);
                case 4 -> xoaDanhSach(ds, sc);
                case 5 -> System.out.println("Đã quay lại Menu chính.");
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
            }
        } while (chon != 5);
    }
}