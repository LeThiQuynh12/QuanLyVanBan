package QuanLyCongVan.Controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class DangKyController extends KetNoiCSDL {

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

    // Tạo Salt ngẫu nhiên
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];  // Độ dài của Salt
        random.nextBytes(salt);
        return salt;
    }

    // Thêm người dùng mới vào cơ sở dữ liệu với mật khẩu được mã hóa
    public void ThemNguoiDung(String CoQuan, String HoVaTen, String SoDienThoai, String Email, String TenTaiKhoan, String MatKhau, String VaiTro) {
        String sql = "INSERT INTO tblNguoiDung (CoQuan, HoVaTen, SoDienThoai, Email, TenTaiKhoan, MatKhau, VaiTro, Salt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            // Tạo Salt ngẫu nhiên
            byte[] salt = generateSalt();

            // Mã hóa mật khẩu với Salt
            String hashedPassword = hashPasswordWithSalt(MatKhau, salt);

            // Thực thi câu lệnh SQL để thêm người dùng vào cơ sở dữ liệu
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, CoQuan);  // Thêm trường CoQuan vào câu lệnh SQL
            ps.setString(2, HoVaTen);
            ps.setString(3, SoDienThoai);
            ps.setString(4, Email);
            ps.setString(5, TenTaiKhoan);
            ps.setString(6, hashedPassword); // Mã hóa mật khẩu trước khi lưu
            ps.setString(7, VaiTro);
            ps.setBytes(8, salt);  // Lưu Salt vào cơ sở dữ liệu
            
            int rs = ps.executeUpdate();
            if (rs != 0) {
                System.out.println("Thêm thành công!");
            } else {
                System.out.println("Thêm thất bại!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Kiểm tra xem số điện thoại đã tồn tại hay chưa
    public boolean isPhoneNumberExist(String SoDienThoai) {
        String sql = "SELECT COUNT(*) FROM tblNguoiDung WHERE SoDienThoai = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, SoDienThoai);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;  // Nếu có ít nhất 1 kết quả, số điện thoại đã tồn tại
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Kiểm tra xem email đã tồn tại hay chưa
    public boolean isEmailExist(String Email) {
        String sql = "SELECT COUNT(*) FROM tblNguoiDung WHERE Email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Email);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;  
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Kiểm tra xem tên tài khoản đã tồn tại hay chưa
    public boolean isUsernameExist(String TenTaiKhoan) {
        String sql = "SELECT COUNT(*) FROM tblNguoiDung WHERE TenTaiKhoan = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, TenTaiKhoan);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;  
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
