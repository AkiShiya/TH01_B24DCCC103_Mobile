package QuanLySinhVien;

import java.util.Scanner;

public class SinhVien {
    private String maSV;
    private String hoTen;
    private int namSinh;
    private double diemTB;

    public SinhVien() {}

    public void nhap(Scanner sc) {
        System.out.print("Nhập mã sinh viên: ");
        maSV = sc.nextLine();
        System.out.print("Nhập họ tên: ");
        hoTen = sc.nextLine();
        System.out.print("Nhập năm sinh: ");
        namSinh = sc.nextInt();
        System.out.print("Nhập điểm trung bình: ");
        diemTB = sc.nextDouble();
        sc.nextLine();
    }

    public String xepLoai() {
        if (diemTB >= 8.0) return "Giỏi";
        if (diemTB >= 6.5) return "Khá";
        if (diemTB >= 5.0) return "Trung bình";
        return "Yếu";
    }

    public void xuat() {
        System.out.printf("%-12s %-22s %-10d %-10.2f %-12s\n",
                maSV, hoTen, namSinh, diemTB, xepLoai());
    }

    public double getDiemTB() {
        return diemTB;
    }
}