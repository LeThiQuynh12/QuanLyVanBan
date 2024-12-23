package QuanLyCongVan.Controllers;

import QuanLyCongVan.Model.PhongBan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PhongBanController extends KetNoiCSDL {
    // Lấy toàn bộ danh sách phòng ban
    public ArrayList<PhongBan> getAll() throws SQLException {
        ArrayList<PhongBan> list = new ArrayList<>();
        String query = "SELECT * FROM tblPhongBan";
        try (PreparedStatement prst = conn.prepareStatement(query);
             ResultSet rs = prst.executeQuery()) {
            while (rs.next()) {
                PhongBan obj = new PhongBan(rs);
                list.add(obj);
            }
        }
        return list;
    }

    // Thêm phòng ban
    public boolean ThemPhongBan(String tenPhongBan) throws SQLException {
        String query = "INSERT INTO tblPhongBan (TenPhongBan) VALUES (?)";
        try (PreparedStatement prst = conn.prepareStatement(query)) {
            prst.setString(1, tenPhongBan);
            return prst.executeUpdate() > 0;
        }
    }

    // Sửa thông tin phòng ban
    public boolean SuaPhongBan(String maPhongBan, String tenPhongBan) throws SQLException {
        String query = "UPDATE tblPhongBan SET TenPhongBan = ? WHERE MaPhongBan = ?";
        try (PreparedStatement prst = conn.prepareStatement(query)) {
            prst.setString(1, tenPhongBan);
            prst.setString(2, maPhongBan);
            return prst.executeUpdate() > 0;
        }
    }
    

 public boolean XoaPhongBan(String maPhongBan) throws SQLException {
    // Kiểm tra xem có bản ghi nào trong bảng VanBanNoiBo tham chiếu đến phòng ban này không
    String queryCheck = "SELECT COUNT(*) FROM VanBanNoiBo WHERE MaBanNhan = ? OR MaBanHanh = ?";
    try (PreparedStatement prstCheck = conn.prepareStatement(queryCheck)) {
        prstCheck.setString(1, maPhongBan);
        prstCheck.setString(2, maPhongBan);
        ResultSet rsCheck = prstCheck.executeQuery();
        if (rsCheck.next()) {
            int count = rsCheck.getInt(1);
            if (count > 0) {
                // Có bản ghi liên quan, xử lý
                JOptionPane.showMessageDialog(null, "Không thể xóa phòng ban vì có dữ liệu liên quan trong VanBanNoiBo.");
                return false;  // Không thực hiện xóa
            }
        }
    }

    // Thực hiện xóa phòng ban nếu không có dữ liệu liên quan
    String queryDelete = "DELETE FROM tblPhongBan WHERE MaPhongBan = ?";
    try (PreparedStatement prstDelete = conn.prepareStatement(queryDelete)) {
        prstDelete.setString(1, maPhongBan);
        int rowsAffected = prstDelete.executeUpdate();
        return rowsAffected > 0;  // Trả về true nếu xóa thành công
    }
}


    // Kiểm tra phòng ban đã tồn tại
    public boolean KiemTraPhongBanTonTai(String tenPhongBan) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tblPhongBan WHERE TenPhongBan = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tenPhongBan);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // Tồn tại
                }
            }
        }
        return false; // Không tồn tại
    }
}

