package QuanLyCongVan.Controllers;

import QuanLyCongVan.Model.TaiLieu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class TaiLieuController extends KetNoiCSDL{
  public ArrayList<TaiLieu> getAll() throws SQLException{
        ArrayList<TaiLieu> lst = new ArrayList<TaiLieu>();
        String query = "Select * from TAILIEU";
        
        // Taoj truy van
        PreparedStatement prst = conn.prepareStatement(query);
        ResultSet rs  = prst.executeQuery();
        while(rs.next()){
            TaiLieu tm  = new TaiLieu(rs);
            lst.add(tm);
        }
        return lst;
  }
    public boolean ThemTaiLieu(String TenTaiLieu, java.sql.Date NgayTao, String KichCo,String Loai, String DuongDan) throws SQLException{
    
    String query = "INSERT INTO TAILIEU (TenTL, NgayTao, KichCo, Loai, DuongDan) VALUES (?,?,?,?,?)";
    PreparedStatement prst = conn.prepareStatement(query);

    prst.setString(1, TenTaiLieu);
    prst.setDate(2, NgayTao);  // Sử dụng java.sql.Date thay vì java.util.Date
    prst.setString(3, KichCo);
    prst.setString(4, Loai);
    prst.setString(5, DuongDan);
    int d = prst.executeUpdate();
    prst.close();
   // conn.close();
    return d > 0;
}
        
      public boolean XoaFile(String MaTaiLieu) throws SQLException {
    // Kiểm tra xem MaTaiLieu có tồn tại trong bảng VanBanNoiBo hay không
    String checkQuery = "SELECT COUNT(*) FROM VanBanNoiBo WHERE MaTL = ?";
    PreparedStatement checkPrst = conn.prepareStatement(checkQuery);
    checkPrst.setString(1, MaTaiLieu);
    ResultSet rs = checkPrst.executeQuery();
    
    if (rs.next()) {
        int count = rs.getInt(1);
        if (count > 0) {
            // Nếu MaTaiLieu tồn tại trong bảng VanBanNoiBo thì không cho xóa
            JOptionPane.showMessageDialog(null, "Không thể xóa tài liệu vì nó đang được tham chiếu trong bảng VanBanNoiBo.");
            rs.close();
            checkPrst.close();
            return false;
        }
    }
    
    // Nếu không có ràng buộc khóa ngoại, tiếp tục xóa tài liệu
    String query = "DELETE FROM TAILIEU WHERE MaTL = ?";
    PreparedStatement prst = conn.prepareStatement(query);
    prst.setString(1, MaTaiLieu);
    int d = prst.executeUpdate();
    
    prst.close();
    return d > 0;
}

      
      
    }
  
 
       
  
       
       
  

    
    

