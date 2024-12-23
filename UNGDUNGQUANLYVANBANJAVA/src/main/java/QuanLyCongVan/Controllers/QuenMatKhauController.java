/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyCongVan.Controllers;

import static QuanLyCongVan.Controllers.MaHoa.maHoaMatKhau;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Windows
 */
public class QuenMatKhauController extends KetNoiCSDL {
    public boolean kiemTraTaiKhoan(String tenTaiKhoan, String email){
        if (conn == null){
            System.out.println("Kết nối cơ sở dữ liệu không được thiết lập");
            return false;
        }
        
        String sql = "SELECT * FROM tblNguoiDung WHERE TenTaiKhoan = ? AND Email = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenTaiKhoan);
            ps.setString(2, email);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean capNhatMatKhau(String matKhau){
        // Mã hóa mật khẩu sử dụng SHA-256
        String maHoaMatKhau = MaHoa.maHoaMatKhau(matKhau);
        String sql = "UPDATE tblNguoiDung SET MatKhau = ? WHERE Email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            matKhau = maHoaMatKhau(matKhau);
            ps.setString(1, matKhau);
            
            int rowsUpdated = ps.executeUpdate();  // dùng executeUpdate cho insert,... (trừ select)
            return rowsUpdated > 0; // Trả về true nếu có ít nhất một hàng được cập nhật
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
    }
    }  
}
