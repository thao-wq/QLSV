package model;

import java.time.LocalDate; 

public class SinhVien {
	private String maSV;
	private String hoTen;
	private LocalDate ngaySinh;
	private String nganh;
	private double diemTB;
	private String lop;
	
	public SinhVien(String maSV, String hoTen, LocalDate ngaySinh, String nganh, double diemTB, String lop) {
		this.maSV = maSV;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.nganh = nganh;
		this.diemTB = diemTB;
		this.lop = lop;
	}
	public String getMaSV() {
		return maSV;
	}

	public String getHoTen() {
		return hoTen;
	}
	
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	
	public String getNganh() {
		return nganh;
	}
	
	public double getDiemTB() {
		return diemTB;
	}
	
	public String getLop() {
		return lop;
	}
	 @Override
	    public String toString() {
	        return "Mã SV: " + maSV +
	                " | Họ tên: " + hoTen +
	                " | Ngày sinh: " + ngaySinh +
	                " | Ngành: " + nganh +
	                " | Điểm TB: " + diemTB +
	                " | Lớp: " + lop;
	    }
}