package com.example.udlogistic.database;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.example.udlogistic.model.KhachHang;
import com.example.udlogistic.model.NhanVien;
import com.example.udlogistic.model.PhongBan;
import com.example.udlogistic.model.ThongTinVanChuyen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQL_Manage {
    String classs = "com.mysql.jdbc.Driver";
    //Địa chỉ ip:port
    // 192.168.1.11:3308
    //+  tên database
    //     UD_Logistic
    String url = "jdbc:mysql://192.168.1.11:3308/UD_Logistic?useUnicode=true&characterEncoding=UTF-8";
    //user name pass đăng nhập mysql
    String un = "danghoawe";
    String password = "danghoawe";
    Statement statement;
    Statement statement2;
    ResultSet resultSet;
    ResultSet result2;
    ResultSetMetaData rsmd;
    ResultSetMetaData rsmd2;
    Connection conn = null;

    public MySQL_Manage() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName(classs);
            conn = DriverManager.getConnection(url, un, password);
            Log.d("Alert: ", "Connected");
            statement = conn.createStatement();
            statement2 = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeThongTinVanChuyen(ThongTinVanChuyen tt) {
        try {
            String sql = new String((
                    "INSERT INTO `thongtinvanchuyen`(`maThongTinVanChuyen`, `soXe`, `soToKhai`, `ngayDiGiaoHang`, `noiLayCong`, `donGia`, `phiCauDuong`, `neoXe`, `tamUng`, `soBill`, `soConTainer`, `noiDongHang`, `nhienLieu`, `phiNangCong`, `luongTheoChuyen`, `khachHang`, `nhanVien`) " +
                    "VALUES ('"+tt.getMaThongTinVanChuyen()+"','"+tt.getSoXe()+"','"+tt.getSoToKhai()+"'" +
                            ",'"+tt.getNgayDiGiaoHang()+"','"+tt.getNoiLayCong()+"','"+tt.getDonGia()+"'" +
                            ",'"+tt.getPhiCauDuong()+"','"+tt.getNeoXe()+"','"+tt.getTamUng()+"','"+tt.getSoBill()+"'" +
                            ",'"+tt.getSoConTainer()+"','"+tt.getNoiDongHang()+"','"+tt.getNhienLieu()+"','"+tt.getPhiNangCong()+"'" +
                            ",'"+tt.getLuongTheoChuyen()+"','"+tt.getKhachHang().getMaKhachHang()+"','"+tt.getNhanVien().getMaNhanVien()+"')"));
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteThongTinVanChuyen(ThongTinVanChuyen thongTinVanChuyen) {
        try {
            String sql = "DELETE FROM `thongtinvanchuyen` WHERE maThongTinVanChuyen = '" + thongTinVanChuyen.getMaThongTinVanChuyen() + "'";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void updateThongTinVanChuyen(ThongTinVanChuyen tt) {
        try {
            String sql = "UPDATE `thongtinvanchuyen` SET " +
                    "`soXe`='"+tt.getSoXe()+"',`soToKhai`='"+tt.getSoToKhai()+"'" +
                    ",`ngayDiGiaoHang`='"+tt.getNgayDiGiaoHang()+"'" +
                    ",`noiLayCong`='"+tt.getNoiLayCong()+"',`donGia`='"+tt.getDonGia()+"'" +
                    ",`phiCauDuong`='"+tt.getPhiCauDuong()+"',`neoXe`='"+tt.getNeoXe()+"'" +
                    ",`tamUng`='"+tt.getTamUng()+"'" +
                    ",`soBill`='"+tt.getSoBill()+"',`soConTainer`='"+tt.getSoConTainer()+"'" +
                    ",`noiDongHang`='"+tt.getNoiDongHang()+"',`nhienLieu`='"+tt.getNhienLieu()+"'" +
                    ",`phiNangCong`='"+tt.getPhiNangCong()+"',`luongTheoChuyen`='"+tt.getLuongTheoChuyen()+"'" +
                    ",`khachHang`='"+tt.getKhachHang().getMaKhachHang()+"',`nhanVien`='"+tt.getNhanVien().getMaNhanVien()+"'" +
                    " WHERE `maThongTinVanChuyen`='"+tt.getMaThongTinVanChuyen()+"'";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public ArrayList<ThongTinVanChuyen> getThongTinVanChuyen() {
        ArrayList<ThongTinVanChuyen> thongTinVanChuyens = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("select * from thongtinvanchuyen");
            //Lấy tên cột

            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                KhachHang khachHang =  getKhachHangByID(resultSet.getString(16));
                NhanVien nhanVien =  getNhanVienByID(resultSet.getString(17));
                thongTinVanChuyens.add(new ThongTinVanChuyen(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6),resultSet.getString(7),
                        resultSet.getString(8),resultSet.getString(9),
                        resultSet.getString(10),resultSet.getString(11),
                        resultSet.getString(12),resultSet.getString(13),
                        resultSet.getString(14),resultSet.getString(15),
                        khachHang,nhanVien));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return thongTinVanChuyens;
    }
    public void updateNhanVien(NhanVien nhanVien) {
        try {
            String sql = "UPDATE `nhanvien` SET " +
                    "`hoTen`='" + nhanVien.getHoTen() + "'" + "," +
                    "`diaChi`='" + nhanVien.getDiaChi() + "'" + "," + "`soDienThoai`='"
                    + nhanVien.getSoDienThoai() + "'" + "," + "`boPhan`='"
                    + nhanVien.getBoPhan() + "'" + "," + "`chucVu`='"
                    + nhanVien.getChucVu() + "'" +
                    " WHERE maNhanVien = '" + nhanVien.getMaNhanVien() + "'";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void writeNhanVien(NhanVien nhanVien) {
        try {
            String sql = new String(("INSERT INTO `nhanvien`(`maNhanVien`, `hoTen`, `diaChi`, `soDienThoai`,`boPhan`,`chucVu`) VALUES " +
                    "('" + nhanVien.getMaNhanVien() + "'" + ",'" + nhanVien.getHoTen() + "'" + "" +
                    ",'" + nhanVien.getDiaChi() + "'" + ",'" + nhanVien.getSoDienThoai() + "'" + ",'"
                    + nhanVien.getBoPhan() + "'" + ",'" + nhanVien.getChucVu() + "'" + ")"));
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteNhanVien(NhanVien nhanVien) {
        try {
            String sql = "DELETE FROM `nhanvien` WHERE maNhanVien = '" + nhanVien.getMaNhanVien() + "'";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<NhanVien> getNhanVien() {
        ArrayList<NhanVien> nhanViens = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("select * from nhanvien");
            //Lấy tên cột
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                nhanViens.add(new NhanVien(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nhanViens;
    }

    public NhanVien getNhanVienByID(String ID) {
        NhanVien nhanVien = new NhanVien();
        try {
            result2 = statement2.executeQuery("select * from nhanvien where maNhanVien='" + ID + "' ");
            //Lấy tên cột
            rsmd2 = result2.getMetaData();
            while (result2.next()) {
                nhanVien = new NhanVien(resultSet.getString(1),
                        result2.getString(2),
                        result2.getString(3), result2.getString(4), result2.getString(5), result2.getString(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nhanVien;
    }

    public KhachHang getKhachHangByID(String ID) {
        KhachHang khachHang = new KhachHang();
        try {
            result2 = statement2.executeQuery("select * from khachhang where maKhachHang='" + ID + "' ");
            //Lấy tên cột
            rsmd = result2.getMetaData();
            while (result2.next()) {
                    khachHang = new KhachHang(result2.getString(1),
                            result2.getString(2), result2.getString(3), result2.getString(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return khachHang;
    }
    public void writeKhachHang(KhachHang khachHang) {
        try {
            String sql = new String(("INSERT INTO `khachhang`(`maKhachHang`, `tenKhachHang`, `diaChi`, `soDienThoai`) VALUES " +
                    "('" + khachHang.getMaKhachHang() + "'" + ",'" + khachHang.getHoTen() + "'" + "" +
                    ",'" + khachHang.getDiaChi() + "'" + ",'" + khachHang.getSoDienThoai() + "'" + ")"));
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteKhachHang(KhachHang khachHang) {
        try {
            String sql = "DELETE FROM `khachhang` WHERE maKhachHang = '" + khachHang.getMaKhachHang() + "'";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateKhachHang(KhachHang khachHang) {
        try {
            String sql = "UPDATE `khachhang` SET " +
                    "`tenKhachHang`='" + khachHang.getHoTen() + "'" + "," +
                    "`diaChi`='" + khachHang.getDiaChi() + "'" + "," + "`soDienThoai`='" + khachHang.getSoDienThoai() + "'" +
                    " WHERE maKhachHang = '" + khachHang.getMaKhachHang() + "'";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<KhachHang> getKhachHang() {
        ArrayList<KhachHang> khachHangs = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("select * from khachHang");
            //Lấy tên cột
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                khachHangs.add(new KhachHang(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return khachHangs;
    }


    public void delePhongBan(PhongBan phongBan) {
        try {
            String sql = "DELETE FROM `phongban` WHERE maPhong = '" + phongBan.getMaPhong() + "'";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void writePhongBan(PhongBan phongBan) {
        try {
            String sql = new String(("INSERT INTO `phongban`(`maPhong`, `tenPhong`) VALUES " +
                    "('" + phongBan.getMaPhong() + "','" + phongBan.getTenPhong() + "')"));
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updatePhongBan(PhongBan phongBan) {
        try {
            String sql = "UPDATE `phongban`" +
                    " set tenPhong='" + phongBan.getTenPhong() +
                    "' where maPhong='" + phongBan.getMaPhong() + "'";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<PhongBan> getPhongBan() {
        ArrayList<PhongBan> phongBans = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("select * from phongban");
            //Lấy tên cột
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                phongBans.add(new PhongBan(resultSet.getString(1), resultSet.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return phongBans;
    }

}
