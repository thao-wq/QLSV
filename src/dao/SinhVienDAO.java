package dao;

import model.SinhVien;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {

    public void themSinhVien(SinhVien sv) throws Exception {
        String sql = "INSERT INTO sinhvien VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sv.getMaSV());
            ps.setString(2, sv.getHoTen());
            ps.setDate(3, Date.valueOf(sv.getNgaySinh()));
            ps.setString(4, sv.getNganh());
            ps.setDouble(5, sv.getDiemTB());
            ps.setString(6, sv.getLop());

            ps.executeUpdate();
        }
    }

    public void xoaSinhVien(String masv) throws Exception {
        String sql = "DELETE FROM sinhvien WHERE masv=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, masv);
            ps.executeUpdate();
        }
    }

    public void capNhatDiem(String masv, double diem) throws Exception {
        String sql = "UPDATE sinhvien SET diemTB=? WHERE masv=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, diem);
            ps.setString(2, masv);
            ps.executeUpdate();
        }
    }

    public List<SinhVien> getAll() throws Exception {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM sinhvien";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        }
        return list;
    }

    public List<SinhVien> getByLop(String lop) throws Exception {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM sinhvien WHERE lop=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, lop);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        }
        return list;
    }

    public List<SinhVien> getByNganh(String nganh) throws Exception {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM sinhvien WHERE nganh=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nganh);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        }
        return list;
    }

    public List<SinhVien> sapXepTheoDiem() throws Exception {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM sinhvien ORDER BY diemTB DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        }
        return list;
    }

    public List<SinhVien> getByMonth(int month) throws Exception {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM sinhvien WHERE MONTH(ngaysinh)=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        }
        return list;
    }

    private SinhVien mapResultSet(ResultSet rs) throws Exception {
        return new SinhVien(
                rs.getString("masv"),
                rs.getString("hoten"),
                rs.getDate("ngaysinh").toLocalDate(),
                rs.getString("nganh"),
                rs.getDouble("diemTB"),
                rs.getString("lop")
        );
    }
}