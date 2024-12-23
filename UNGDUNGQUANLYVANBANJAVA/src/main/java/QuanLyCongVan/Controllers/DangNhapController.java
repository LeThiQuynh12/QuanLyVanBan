/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyCongVan.Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import QuanLyCongVan.Model.Role;import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
;


public class DangNhapController extends KetNoiCSDL {
    
    // Phương thức mã hóa mật khẩu với Salt
    private String hashPasswordWithSalt(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);  // Thêm Salt vào trước khi mã hóa
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash); // Chuyển mã băm sang chuỗi Base64
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Không tìm thấy thuật toán mã hóa", e);
        }
    }

    // Kiểm tra thông tin đăng nhập (Tên tài khoản và mật khẩu)
    public boolean kiemTraDangNhap(String tenTaiKhoan, String matKhau) {
        String sql = "SELECT MatKhau, Salt FROM tblNguoiDung WHERE TenTaiKhoan = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenTaiKhoan);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Lấy mật khẩu đã mã hóa và Salt từ cơ sở dữ liệu
                String storedPassword = rs.getString("MatKhau");
                byte[] salt = rs.getBytes("Salt");

                // Mã hóa mật khẩu người dùng nhập với Salt
                String hashedInputPassword = hashPasswordWithSalt(matKhau, salt);

                // So sánh mật khẩu đã mã hóa
                if (storedPassword.equals(hashedInputPassword)) {
                    return true;  // Đăng nhập thành công
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;  // Đăng nhập thất bại
    }
      public boolean doiMatKhau(String tenTaiKhoan, String matKhauCu, String matKhauMoi) {
        // Kiểm tra tên tài khoản có tồn tại hay không
    String sql = "SELECT Salt FROM tblNguoiDung WHERE TenTaiKhoan = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tenTaiKhoan);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            // Lấy Salt từ cơ sở dữ liệu
            byte[] salt = rs.getBytes("Salt");

            // Mã hóa mật khẩu mới
            String hashedNewPassword = hashPasswordWithSalt(matKhauMoi, salt);

            // Cập nhật mật khẩu mới vào cơ sở dữ liệu
            String updateSql = "UPDATE tblNguoiDung SET MatKhau = ? WHERE TenTaiKhoan = ?";
            PreparedStatement updatePs = conn.prepareStatement(updateSql);
            updatePs.setString(1, hashedNewPassword);
            updatePs.setString(2, tenTaiKhoan);
            int result = updatePs.executeUpdate();

            return result > 0;  // Nếu số dòng cập nhật > 0, nghĩa là đã thay đổi thành công
        } else {
            // Tên tài khoản không tồn tại
            return false;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return false;  // Có lỗi trong quá trình thực hiện
    }
      public boolean isTenTaiKhoanAndEmailValid(String tenTaiKhoan, String email) {
    String sql = "SELECT COUNT(*) FROM tblNguoiDung WHERE TenTaiKhoan = ? AND Email = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tenTaiKhoan);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            // Nếu kết quả trả về > 0, tức là tên tài khoản và email hợp lệ
            return rs.getInt(1) > 0;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return false;  // Nếu không tìm thấy tên tài khoản và email khớp
}
public boolean changePassword(String tenTaiKhoan, String matKhauMoi) {
    // Lấy Salt từ cơ sở dữ liệu dựa trên tên tài khoản
    String sql = "SELECT Salt FROM tblNguoiDung WHERE TenTaiKhoan = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tenTaiKhoan);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            // Lấy Salt từ cơ sở dữ liệu
            byte[] salt = rs.getBytes("Salt");

            // Mã hóa mật khẩu mới với Salt
            String hashedNewPassword = hashPasswordWithSalt(matKhauMoi, salt);

            // Cập nhật mật khẩu mới vào cơ sở dữ liệu
            String updateSql = "UPDATE tblNguoiDung SET MatKhau = ? WHERE TenTaiKhoan = ?";
            PreparedStatement updatePs = conn.prepareStatement(updateSql);
            updatePs.setString(1, hashedNewPassword);
            updatePs.setString(2, tenTaiKhoan);
            int result = updatePs.executeUpdate();

            return result > 0;  // Nếu cập nhật thành công, trả về true
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return false;  // Nếu không tìm thấy tài khoản hoặc có lỗi trong quá trình thực hiện
}

}
