package QuanLyCongVan.Controllers;

import QuanLyCongVan.Model.QuanLyNguoiDungModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class QuanLyNguoiDungController extends KetNoiCSDL {
    public QuanLyNguoiDungController() {
        conn = getConnection();  
    }
    
    public ArrayList<QuanLyNguoiDungModel> HienThiNguoiDung() throws SQLException {
        ArrayList<QuanLyNguoiDungModel> ds1 = new ArrayList<>();
        
        try {
            if (conn == null) {
                System.out.println("Kết nối cơ sở dữ liệu không được thiết lập.");
                return ds1;
            }

            String sql = "SELECT HoVaTen, Email, SoDienThoai, TenTaiKhoan, MatKhau FROM tblNguoiDung";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                QuanLyNguoiDungModel nguoiDung = new QuanLyNguoiDungModel(
                    rs.getString("HoVaTen"),
                    rs.getString("Email"),
                    rs.getString("SoDienThoai"),
                    rs.getString("TenTaiKhoan"),
                    rs.getString("MatKhau")
                );
                ds1.add(nguoiDung);  
            }
        } catch (SQLException e) {
            e.printStackTrace();  
        } 
        return ds1;  
    }
    
    //Thêm người dùng 
    public boolean ThemNguoiDung(String hoTen, String email, String soDienThoai, String tenTaiKhoan, String matKhau) throws SQLException {
        // Mã hóa mật khẩu sử dụng SHA-256
        String maHoaMatKhau = MaHoa.maHoaMatKhau(matKhau);
        
        String query = "INSERT INTO tblNguoiDung (HoVaTen, Email, SoDienThoai, TenTaiKhoan, MatKhau) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement prst = conn.prepareStatement(query)) {
            prst.setString(1, hoTen);
            prst.setString(2, email);
            prst.setString(3, soDienThoai);
            prst.setString(4, tenTaiKhoan);
            prst.setString(5, maHoaMatKhau);
            return prst.executeUpdate() > 0;
        }
    }
    
    //Sửa người dùng 
public boolean SuaNguoiDung(String hoTen, String email, String soDienThoai, String tenTaiKhoan, String matKhau) throws SQLException {
    // Mã hóa mật khẩu nếu cần
    String maHoaMatKhau = MaHoa.maHoaMatKhau(matKhau);
    
    String query = "UPDATE tblNguoiDung SET HoVaTen = ?, Email = ?, SoDienThoai = ?, MatKhau = ? WHERE TenTaiKhoan = ?";
    
    try (PreparedStatement prst = conn.prepareStatement(query)) {
        prst.setString(1, hoTen);
        prst.setString(2, email);
        prst.setString(3, soDienThoai);
        prst.setString(4, maHoaMatKhau);  // Mã hóa mật khẩu trước khi lưu
        prst.setString(5, tenTaiKhoan);  // Dùng tên tài khoản để xác định bản ghi
        
        return prst.executeUpdate() > 0;  // Nếu ít nhất 1 dòng bị ảnh hưởng, trả về true
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
}

    
    //Xóa người dùng 
public boolean XoaNguoiDung(String tenTaiKhoan) throws SQLException {
    // Kiểm tra và xóa dữ liệu liên quan nếu cần
    String sqlDeleteConstraint = "DELETE FROM tblNguoiDung WHERE TenTaiKhoan = ?";
    try (PreparedStatement prstDeleteConstraint = conn.prepareStatement(sqlDeleteConstraint)) {
        prstDeleteConstraint.setString(1, tenTaiKhoan);
        prstDeleteConstraint.executeUpdate();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, 
            "Không thể xóa quyền liên quan của người dùng: " + ex.getMessage());
        return false;
    }

    // Thực hiện xóa người dùng
    String sqlDelete = "DELETE FROM tblNguoiDung WHERE TenTaiKhoan = ?";
    try (PreparedStatement prstDelete = conn.prepareStatement(sqlDelete)) {
        prstDelete.setString(1, tenTaiKhoan);
        int rowsAffected = prstDelete.executeUpdate();
        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, 
            "Có lỗi xảy ra trong quá trình xóa: " + ex.getMessage());
        throw ex;
    }
}


    
    //Kiểm tra xem email đã tồn tại hay chưa
    public boolean kiemTraEmail (String email) {
        String sql = "SELECT COUNT(*) FROM tblNguoiDung WHERE Email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;  
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    // Kiểm tra xem số điện thoại đã tồn tại hay chưa
    public boolean kiemTraSDT (String soDienThoai) {
        String sql = "SELECT COUNT(*) FROM tblNguoiDung WHERE SoDienThoai = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, soDienThoai);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;  // Nếu có ít nhất 1 kết quả, số điện thoại đã tồn tại
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Kiểm tra xem tên tài khoản đã tồn tại hay chưa
    public boolean kiemTraTenTaiKhoan (String tenTaiKhoan) {
        String sql = "SELECT COUNT(*) FROM tblNguoiDung WHERE TenTaiKhoan = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenTaiKhoan);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;  
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //
}
