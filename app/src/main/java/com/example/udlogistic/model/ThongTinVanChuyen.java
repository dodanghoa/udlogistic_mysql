package com.example.udlogistic.model;

public class ThongTinVanChuyen {
    String maThongTinVanChuyen,soXe,soToKhai,ngayDiGiaoHang,noiLayCong,donGia,phiCauDuong,neoXe,tamUng,
    soBill,soConTainer,noiDongHang,nhienLieu,phiNangCong,luongTheoChuyen;
    KhachHang khachHang;

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public ThongTinVanChuyen(String maThongTinVanChuyen, String soXe, String soToKhai, String ngayDiGiaoHang, String noiLayCong, String donGia, String phiCauDuong, String neoXe, String tamUng, String soBill, String soConTainer, String noiDongHang, String nhienLieu, String phiNangCong, String luongTheoChuyen, KhachHang khachHang, NhanVien nhanVien) {
        this.maThongTinVanChuyen = maThongTinVanChuyen;
        this.soXe = soXe;
        this.soToKhai = soToKhai;
        this.ngayDiGiaoHang = ngayDiGiaoHang;
        this.noiLayCong = noiLayCong;
        this.donGia = donGia;
        this.phiCauDuong = phiCauDuong;
        this.neoXe = neoXe;
        this.tamUng = tamUng;
        this.soBill = soBill;
        this.soConTainer = soConTainer;
        this.noiDongHang = noiDongHang;
        this.nhienLieu = nhienLieu;
        this.phiNangCong = phiNangCong;
        this.luongTheoChuyen = luongTheoChuyen;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
    }

    NhanVien nhanVien;


    public String getMaThongTinVanChuyen() {
        return maThongTinVanChuyen;
    }

    public void setMaThongTinVanChuyen(String maThongTinVanChuyen) {
        this.maThongTinVanChuyen = maThongTinVanChuyen;
    }

    public String getSoXe() {
        return soXe;
    }

    public void setSoXe(String soXe) {
        this.soXe = soXe;
    }

    public String getSoToKhai() {
        return soToKhai;
    }

    public void setSoToKhai(String soToKhai) {
        this.soToKhai = soToKhai;
    }

    public String getNgayDiGiaoHang() {
        return ngayDiGiaoHang;
    }

    public void setNgayDiGiaoHang(String ngayDiGiaoHang) {
        this.ngayDiGiaoHang = ngayDiGiaoHang;
    }

    public String getNoiLayCong() {
        return noiLayCong;
    }

    public void setNoiLayCong(String noiLayCong) {
        this.noiLayCong = noiLayCong;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getPhiCauDuong() {
        return phiCauDuong;
    }

    public void setPhiCauDuong(String phiCauDuong) {
        this.phiCauDuong = phiCauDuong;
    }

    public String getNeoXe() {
        return neoXe;
    }

    public void setNeoXe(String neoXe) {
        this.neoXe = neoXe;
    }

    public String getTamUng() {
        return tamUng;
    }

    public void setTamUng(String tamUng) {
        this.tamUng = tamUng;
    }

    public String getSoBill() {
        return soBill;
    }

    public void setSoBill(String soBill) {
        this.soBill = soBill;
    }

    public String getSoConTainer() {
        return soConTainer;
    }

    public void setSoConTainer(String soConTainer) {
        this.soConTainer = soConTainer;
    }

    public String getNoiDongHang() {
        return noiDongHang;
    }

    public void setNoiDongHang(String noiDongHang) {
        this.noiDongHang = noiDongHang;
    }

    public String getNhienLieu() {
        return nhienLieu;
    }

    public void setNhienLieu(String nhienLieu) {
        this.nhienLieu = nhienLieu;
    }

    public String getPhiNangCong() {
        return phiNangCong;
    }

    public void setPhiNangCong(String phiNangCong) {
        this.phiNangCong = phiNangCong;
    }

    public String getLuongTheoChuyen() {
        return luongTheoChuyen;
    }

    public void setLuongTheoChuyen(String luongTheoChuyen) {
        this.luongTheoChuyen = luongTheoChuyen;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public ThongTinVanChuyen() {
    }
}
