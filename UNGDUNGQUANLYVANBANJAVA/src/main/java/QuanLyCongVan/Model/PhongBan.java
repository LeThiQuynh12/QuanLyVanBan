package QuanLyCongVan.Model;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PhongBan {
    private String MaPhongBan;
    private String TenPhongBan;

    // Lay va gan gia tri tuong ung trong bang
    public PhongBan(ResultSet rs) throws SQLException{
      // Đặt tên cột khớp với bảng trong DB
      this.MaPhongBan = rs.getString("MaPhongBan");
      this.TenPhongBan = rs.getString("TenPhongBan");
    }
    
    public PhongBan() {
    }

    public PhongBan(String MaPhongBan, String TenPhongBan) {
        this.MaPhongBan = MaPhongBan;
        this.TenPhongBan = TenPhongBan;
    }

    public String getMaPhongBan() {
        return MaPhongBan;
    }

    public String getTenPhongBan() {
        return TenPhongBan;
    }

    public void setMaPhongBan(String MaPhongBan) {
        this.MaPhongBan = MaPhongBan;
    }

    public void setTenPhongBan(String TenPhongBan) {
        this.TenPhongBan = TenPhongBan;
    }
    
    
    
    
}
