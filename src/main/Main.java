package main;

import dao.SinhVienDAO;
import model.SinhVien;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static SinhVienDAO dao = new SinhVienDAO();

    static String chuanHoaTen(String ten) {
        ten = ten.trim().toLowerCase();
        String[] arr = ten.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (String s : arr) {
            sb.append(Character.toUpperCase(s.charAt(0)))
              .append(s.substring(1))
              .append(" ");
        }
        return sb.toString().trim();
    }

    static boolean checkMaSV(String masv, String nganh) {
        if (!masv.matches("\\d{10}")) return false;

        if (nganh.equalsIgnoreCase("CNTT") && masv.startsWith("455105"))
            return true;

        if (nganh.equalsIgnoreCase("KTPM") && masv.startsWith("455109"))
            return true;

        return false;
    }

    static boolean checkTuoi(LocalDate ns) {
        int tuoi = Period.between(ns, LocalDate.now()).getYears();
        return tuoi >= 15 && tuoi <= 110;
    }

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("1. Thêm sinh viên");
                System.out.println("2. Xóa sinh viên");
                System.out.println("3. Cập nhật điểm");
                System.out.println("4. Danh sách tất cả");
                System.out.println("5. Danh sách theo lớp");
                System.out.println("6. Danh sách theo ngành");
                System.out.println("7. Sắp xếp theo điểm");
                System.out.println("8. Sinh viên sinh theo tháng");
                System.out.println("0. Thoát");
                System.out.print("Chọn: ");

                int chon;

                try {
                    chon = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số hợp lệ!");
                    continue;
                }

                switch (chon) {

                    case 1:
                        themSinhVien();
                        break;

                    case 2:
                        System.out.print("Nhập mã SV cần xóa: ");
                        dao.xoaSinhVien(sc.nextLine());
                        System.out.println("Đã xóa!");
                        break;

                    case 3:
                        System.out.print("Nhập mã SV: ");
                        String m = sc.nextLine();

                        System.out.print("Điểm mới: ");
                        double d;

                        try {
                            d = Double.parseDouble(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Điểm phải là số!");
                            break;
                        }

                        if (d < 0 || d > 10) {
                            System.out.println("Điểm không hợp lệ!");
                            break;
                        }

                        dao.capNhatDiem(m, d);
                        System.out.println("Cập nhật thành công!");
                        break;

                    case 4:
                        List<SinhVien> list = dao.getAll();
                        System.out.println("Tổng sinh viên: " + list.size());
                        inDanhSach(list);
                        break;

                    case 5:
                        System.out.print("Nhập lớp: ");
                        inDanhSach(dao.getByLop(sc.nextLine()));
                        break;

                    case 6:
                        System.out.print("Nhập ngành: ");
                        inDanhSach(dao.getByNganh(sc.nextLine()));
                        break;

                    case 7:
                        inDanhSach(dao.sapXepTheoDiem());
                        break;

                    case 8:
                        System.out.print("Nhập tháng (1-12): ");
                        int thang;

                        try {
                            thang = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Tháng phải là số!");
                            break;
                        }

                        inDanhSach(dao.getByMonth(thang));
                        break;

                    case 0:
                        System.out.println("Thoát chương trình!");
                        return;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }

            } catch (Exception e) {
                System.out.println("Có lỗi xảy ra: " + e.getMessage());
            }
        }
    }

    static void themSinhVien() {
        try {
            System.out.print("Mã SV: ");
            String masv = sc.nextLine();

            System.out.print("Ngành (CNTT/KTPM): ");
            String nganh = sc.nextLine();

            if (!checkMaSV(masv, nganh)) {
                System.out.println("Mã sinh viên sai định dạng!");
                return;
            }

            System.out.print("Họ tên: ");
            String hoten = chuanHoaTen(sc.nextLine());

            System.out.print("Ngày sinh (yyyy-MM-dd): ");
            LocalDate ns;

            try {
                ns = LocalDate.parse(sc.nextLine());
                if (!checkTuoi(ns)) {
                    System.out.println("Tuổi không hợp lệ!");
                    return;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Ngày sinh sai định dạng!");
                return;
            }

            System.out.print("Điểm TB: ");
            double diem;

            try {
                diem = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Điểm phải là số!");
                return;
            }

            if (diem < 0 || diem > 10) {
                System.out.println("Điểm không hợp lệ!");
                return;
            }

            System.out.print("Lớp: ");
            String lop = sc.nextLine();

            dao.themSinhVien(new SinhVien(masv, hoten, ns, nganh, diem, lop));
            System.out.println("Thêm thành công!");

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm sinh viên: " + e.getMessage());
        }
    }

    static void inDanhSach(List<SinhVien> list) {
        if (list.isEmpty()) {
            System.out.println("Không có dữ liệu!");
            return;
        }

        for (SinhVien sv : list) {
            System.out.println(sv);
        }
    }
}