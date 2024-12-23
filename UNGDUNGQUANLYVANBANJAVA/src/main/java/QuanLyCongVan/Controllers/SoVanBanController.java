/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyCongVan.Controllers;

import QuanLyCongVan.Controllers.KetNoiCSDL;
import QuanLyCongVan.Model.SoVanBanModel;
import java.beans.*;
import java.net.SocketAddress;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class SoVanBanController extends KetNoiCSDL{
    public ArrayList<SoVanBanModel> HienThiSoVanBan(int year) {
    ArrayList<SoVanBanModel> dsvb = new ArrayList<>();
    try {
        if (conn == null) {
            conn = getConnection();  // Khởi tạo kết nối nếu conn là null
        }

        String sql = "SELECT * FROM SOVANBAN WHERE daxoa = 0 AND nam = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, year);  // Gán năm vào câu lệnh SQL
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            SoVanBanModel so = new SoVanBanModel();
            so.setMaSo(rs.getInt("maSo"));
            so.setSoVanBan(rs.getString("soVanBan"));
            so.setSoDen(rs.getBoolean("soDen"));
            so.setNam(rs.getInt("nam"));
            so.setDaXoa(rs.getBoolean("daxoa"));
            dsvb.add(so);
        }
    } catch (Exception ex) {
        System.out.println("Lỗi: " + ex.getMessage());
    }
    return dsvb;
}

    public int Luu(SoVanBanModel so)
    {
        try
        {
            if (conn == null || conn.isClosed()) {
                conn = getConnection(); // Tái tạo kết nối
            }
            String sql = "UPDATE SOVANBAN SET SOVANBAN=?, SODEN=?, NAM=? WHERE MASO=?";
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setString(1, so.getSoVanBan());
            prst.setBoolean(2, so.isSoDen());
            prst.setInt(3, so.getNam());
            prst.setInt(4, so.getMaSo());

            int result = prst.executeUpdate();
            if (result > 0) {
                System.out.println("Cập nhật thành công: " + so.getSoVanBan());
            }
            return result;
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
            }
            return -1;
        
    }
   public int Them(SoVanBanModel so) {
    try {
        if (conn == null || conn.isClosed()) {
            conn = getConnection(); // Tái tạo kết nối nếu cần
        }

        // Câu lệnh INSERT để thêm vào bảng
        String sql = "INSERT INTO SOVANBAN (soVanBan, soDen, nam, daXoa) VALUES (?, ?, ?, ?)";
        PreparedStatement prst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); // Chú ý thêm RETURN_GENERATED_KEYS

        prst.setString(1, so.getSoVanBan());
        prst.setBoolean(2, so.isSoDen());
        prst.setInt(3, so.getNam());
        prst.setBoolean(4, false); // Mặc định daXoa là false

        // Thực thi câu lệnh INSERT
        int affectedRows = prst.executeUpdate();

        if (affectedRows > 0) {
            // Lấy giá trị tự động tăng (maSo) từ cơ sở dữ liệu
            ResultSet generatedKeys = prst.getGeneratedKeys();
            if (generatedKeys.next()) {
                int maSo = generatedKeys.getInt(1); // Lấy giá trị tự động tăng
                so.setMaSo(maSo); // Gán giá trị cho đối tượng SoVanBanModel
                System.out.println("Mã số được tạo tự động: " + maSo);
            }
            return affectedRows; // Trả về số dòng bị ảnh hưởng
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
    }
    return -1; // Trả về -1 nếu có lỗi
}
 




    public int XoaTaiLieu(SoVanBanModel so) {
    try {
        // Kiểm tra kết nối trước khi thực hiện thao tác
        if (conn == null || conn.isClosed()) {
            conn = getConnection(); // Tái tạo kết nối nếu cần
        }

        // Cập nhật trường DaXoa thành true để đánh dấu tài liệu là đã xóa
        String sql = "UPDATE SOVANBAN SET DaXoa = ? WHERE MaSo = ?";
        PreparedStatement prst = conn.prepareStatement(sql);
        prst.setBoolean(1, true);  // Đánh dấu là đã xóa
        prst.setInt(2, so.getMaSo());  // Truyền mã số tài liệu

        // Thực thi câu lệnh UPDATE
        int affectedRows = prst.executeUpdate();

        // Kiểm tra số dòng bị ảnh hưởng
        if (affectedRows > 0) {
            return affectedRows; // Trả về số dòng bị ảnh hưởng (1 nếu xóa thành công, 0 nếu không có gì thay đổi)
        }
    } catch (Exception ex) {
        // Thông báo lỗi nếu có sự cố
        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
    }
    return -1; // Trả về -1 nếu có lỗi
}


}
