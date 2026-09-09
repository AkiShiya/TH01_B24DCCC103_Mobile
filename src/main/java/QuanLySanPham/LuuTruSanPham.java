package QuanLySanPham;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LuuTruSanPham {
    private static final String FILE_NAME = "danh_sach_san_pham.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Đọc dữ liệu
    public static List<SanPham> docFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();
        try (Reader reader = new FileReader(file)) {
            List<SanPham> list = gson.fromJson(reader, new TypeToken<ArrayList<SanPham>>(){}.getType());
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    // Lưu dự liệu
    public static void luuFile(List<SanPham> list) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(list, writer);
            System.out.println("Đã lưu dữ liệu thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi lưu dữ liệu: " + e.getMessage());
        }
    }

    // Nhập sản phẩm cần thêm
    public static void nhapDanhSach(List<SanPham> ds, Scanner sc) {
        int n = 0;
        while (true) {
            try {
                System.out.print("\nNhập số lượng sản phẩm cần thêm: ");
                n = Integer.parseInt(sc.nextLine());
                if (n > 0) break;
                System.out.println("Số lượng phải lớn hơn 0!");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ!");
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println("\nNhập sản phẩm thứ " + (i + 1) + ":");
            SanPham sp = new SanPham();
            sp.nhap(sc);
            ds.add(sp);
        }
        luuFile(ds);
    }

    // Hiển thị danh sách sản phẩm kèm tổng tiền từng sản phẩm
    public static void inDanhSach(List<SanPham> ds) {
        if (ds.isEmpty()) {
            System.out.println("\nDanh sách sản phẩm đang trống!");
            return;
        }
        System.out.println("\n================ DANH SÁCH SẢN PHẨM ================");
        System.out.printf("%-12s %-20s %-12s %-10s %-15s\n", "Mã SP", "Tên SP", "Giá", "Số Lượng", "Thành Tiền");
        System.out.println("----------------------------------------------------------------------");
        for (SanPham sp : ds) {
            sp.xuat();
        }
    }

    // Tìm và hiển thị sản phẩm có giá cao nhất
    public static void timMaxGia(List<SanPham> ds) {
        if (ds.isEmpty()) {
            System.out.println("\nDanh sách sản phẩm đang trống!");
            return;
        }
        double maxGia = ds.get(0).getGia();
        for (SanPham sp : ds) {
            if (sp.getGia() > maxGia) {
                maxGia = sp.getGia();
            }
        }
        System.out.println("\n================ SẢN PHẨM CÓ GIÁ CAO NHẤT ================");
        System.out.printf("%-12s %-20s %-12s %-10s %-15s\n", "Mã SP", "Tên SP", "Giá", "Số Lượng", "Thành Tiền");
        System.out.println("----------------------------------------------------------------------");
        for (SanPham sp : ds) {
            if (sp.getGia() == maxGia) {
                sp.xuat();
            }
        }
    }

    // Xóa danh sách hiện tại
    public static void xoaDanhSach(List<SanPham> ds, Scanner sc) {
        if (ds.isEmpty()) {
            System.out.println("\nDanh sách hiện tại đã trống, không có gì để xóa!");
            return;
        }
        System.out.print("\nBạn có chắc chắn muốn xóa TOÀN BỘ sản phẩm không? (y/n): ");
        String xacNhan = sc.nextLine();
        if (xacNhan.equalsIgnoreCase("y")) {
            ds.clear();
            luuFile(ds);
            System.out.println("Đã xóa toàn bộ danh sách sản phẩm!");
        } else {
            System.out.println("Hủy thao tác xóa.");
        }
    }

    public static void Menu(Scanner sc) {
        List<SanPham> ds = docFile();
        int chon = 0;
        do {
            System.out.println("\n----------- QUẢN LÝ SẢN PHẨM -----------");
            System.out.println("Dữ liệu hiện có: " + ds.size() + " sản phẩm.");
            System.out.println("1. Nhập danh sách sản phẩm");
            System.out.println("2. Hiển thị danh sách sản phẩm kèm tổng tiền");
            System.out.println("3. Tìm sản phẩm có giá cao nhất");
            System.out.println("4. Xóa toàn bộ danh sách sản phẩm");
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
                case 3 -> timMaxGia(ds);
                case 4 -> xoaDanhSach(ds, sc);
                case 5 -> System.out.println("Đã quay lại Menu chính.");
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
            }
        } while (chon != 5);
    }
}