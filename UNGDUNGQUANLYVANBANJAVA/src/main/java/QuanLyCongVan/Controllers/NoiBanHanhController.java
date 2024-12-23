/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyCongVan.Controllers;

import QuanLyCongVan.Model.NoiBanHanhModel;
import QuanLyCongVan.Model.NoiBanHanhModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class NoiBanHanhController extends KetNoiCSDL{

    public NoiBanHanhController() {
        conn = getConnection();  // Gọi phương thức getConnection để khởi tạo conn
    }

    public ArrayList<NoiBanHanhModel> HienThiNoiBanHanh() {
        ArrayList<NoiBanHanhModel> dsnoi = new ArrayList<>();
        try {
            String sql = "SELECT * FROM NOIBANHANH WHERE DAXOA=0";
            PreparedStatement prst = conn.prepareStatement(sql);  // Sử dụng conn từ lớp cha
            ResultSet rs = prst.executeQuery();  // Thực thi câu truy vấn

            while (rs.next()) {
                NoiBanHanhModel model = new NoiBanHanhModel();
                model.setMaNoiBanHanh(rs.getInt("maNoiBanHanh"));
                model.setNoiBanHanh(rs.getString("noiBanHanh"));
                model.setMoTa(rs.getString("moTa"));
                model.setDaXoa(false);
                dsnoi.add(model);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }
        return dsnoi;
    }
   public int ThemNoiBanHanh(NoiBanHanhModel model) {
    try {
        String sql = "INSERT INTO NOIBANHANH (noiBanHanh, moTa, daXoa) VALUES (?, ?, ?)";
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, model.getNoiBanHanh());
        prst.setString(2, model.getMoTa());
        prst.setBoolean(3, model.isDaXoa());

        return prst.executeUpdate(); // Trả về số hàng được thêm
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Lỗi khi thêm nơi ban hành: " + ex.getMessage());
    }
    return -1; // Trả về -1 nếu thêm thất bại
}

public int CapNhatNoiBanHanh(NoiBanHanhModel model) {
    try {
        String sql = "UPDATE NOIBANHANH SET noiBanHanh = ?, moTa = ? WHERE maNoiBanHanh = ?";
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setString(1, model.getNoiBanHanh());
        prst.setString(2, model.getMoTa());
        prst.setInt(3, model.getMaNoiBanHanh());

        return prst.executeUpdate(); // Trả về số hàng được cập nhật
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật nơi ban hành: " + ex.getMessage());
    }
    return -1; // Trả về -1 nếu cập nhật thất bại
}

public int XoaNoiBanHanh(NoiBanHanhModel noibanhanh) {
    try {
        String sql = "UPDATE NOIBANHANH SET daXoa = 1 WHERE maNoiBanHanh = ?";
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setInt(1, noibanhanh.getMaNoiBanHanh());
        return prst.executeUpdate(); // Trả về số hàng được cập nhật
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Lỗi khi xóa nơi ban hành: " + ex.getMessage());
    }
    return -1; // Trả về -1 nếu cập nhật thất bại
}



}
