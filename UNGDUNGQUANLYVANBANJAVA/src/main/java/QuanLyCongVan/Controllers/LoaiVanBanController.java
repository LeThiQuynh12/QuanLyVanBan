/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyCongVan.Controllers;

import QuanLyCongVan.Model.LoaiVanBanModel;
import QuanLyCongVan.Model.SoVanBanModel;
import java.sql.*;
import java.util.ArrayList;

public class LoaiVanBanController extends KetNoiCSDL{
    public ArrayList<LoaiVanBanModel> HienThiLoaiVanBan() {
        ArrayList<LoaiVanBanModel> dsl = new ArrayList<>();
        try {
            if (conn == null) {
                conn = getConnection();  
            }

            String sql = "SELECT * FROM LOAIVANBAN WHERE DAXOA=0";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                LoaiVanBanModel loaiVanBan = new LoaiVanBanModel();
                loaiVanBan.setMaLoai(rs.getString("maLoai"));
                loaiVanBan.setLoaiVanBan(rs.getString("loaiVanBan"));
                loaiVanBan.setMoTa(rs.getString("moTa"));
                dsl.add(loaiVanBan);
            }
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
        return dsl;
    }
    public int Xoa(String maLoai)
    {
        try {
        if (conn == null) {
            conn = getConnection(); // Khởi tạo kết nối nếu chưa có
        }
        String sql = "UPDATE LOAIVANBAN SET DAXOA = 1 WHERE MALOAI = ?";
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, maLoai); // Thay 0 thành 1
        return prst.executeUpdate(); // Trả về số hàng bị cập nhật
    } catch (Exception ex) {
        System.out.println("Lỗi: " + ex.getMessage());
    }
        return -1;
    }
    public int Them(LoaiVanBanModel loai) {
        try {
            if (conn == null) {
                conn = getConnection();
            }
            String sql = "INSERT INTO LOAIVANBAN (MALOAI, LOAIVANBAN, MOTA) VALUES (?, ?, ?)";
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setString(1, loai.getMaLoai());
            prst.setString(2, loai.getLoaiVanBan());
            prst.setString(3, loai.getMoTa());
            int result = prst.executeUpdate();
            prst.close();
            return result;
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
        return -1;
    }

    public boolean kiemTraTonTai(String maLoai) {
        String query = "SELECT COUNT(*) FROM LOAIVANBAN WHERE MALOAI = ?";
        try {
            if (conn == null) {
                conn = getConnection();
            }
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, maLoai);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                boolean exists = rs.getInt(1) > 0;
                rs.close();
                stmt.close();
                return exists;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int Update(LoaiVanBanModel loaiModel) {
        try {
            if (conn == null) {
                conn = getConnection();
            }
            String sql = "UPDATE LOAIVANBAN SET LOAIVANBAN = ?, MOTA = ?, DAXOA = ? WHERE MALOAI = ?";
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setString(1, loaiModel.getLoaiVanBan());
            prst.setString(2, loaiModel.getMoTa());
            prst.setBoolean(3, loaiModel.getDaXoa());
            prst.setString(4, loaiModel.getMaLoai());
            int result = prst.executeUpdate();
            prst.close();
            return result;
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
        return -1;
    }
}
