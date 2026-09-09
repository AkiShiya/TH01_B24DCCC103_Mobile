package QuanLySanPham;

import java.util.Scanner;

public class SanPham {
    private String maSP;
    private String tenSP;
    private int gia;
    private int soLuong;

    public SanPham() {}

    public void nhap(Scanner sc) {
        System.out.print("Nhập mã sản phẩm: ");
        maSP = sc.nextLine();
        System.out.print("Nhập tên sản phẩm: ");
        tenSP = sc.nextLine();
        System.out.print("Nhập giá bán (VD: 1000000): ");
        gia = sc.nextInt();
        System.out.print("Nhập số lượng tồn kho: ");
        soLuong = sc.nextInt();
        sc.nextLine();
    }

    public int tinhTongTien() {
        return gia * soLuong;
    }

    public void xuat() {
        System.out.printf("%-12s %-20s %-12d %-10d %-15d\n",
                maSP, tenSP, gia, soLuong, tinhTongTien());
    }

    public int getGia() {
        return gia;
    }
}