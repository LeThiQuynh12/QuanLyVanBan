package QuanLyCongVan.Controllers;

import QuanLyCongVan.Model.QuyenModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class QuanLyQuyenController extends KetNoiCSDL {

    public QuanLyQuyenController() {
        conn = getConnection();
    }

    // Hiển thị danh sách quyền
    public ArrayList<QuyenModel> HienThiQuyen() throws SQLException {
        ArrayList<QuyenModel> dsQuyen = new ArrayList<>();

        String sql = "SELECT ten_quyen, mo_ta FROM quyen";
        try {
            if (conn == null) {
                System.out.println("Kết nối cơ sở dữ liệu không được thiết lập.");
                return dsQuyen;
            }

            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                QuyenModel quyen = new QuyenModel(
                        rs.getString("ten_quyen"),
                        rs.getString("mo_ta")
                );
                dsQuyen.add(quyen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsQuyen;
    }

    // Thêm quyền
    public boolean ThemQuyen(String tenQuyen, String moTa) throws SQLException {
        String query = "INSERT INTO quyen (ten_quyen, mo_ta) VALUES (?, ?)";
        try (PreparedStatement prst = conn.prepareStatement(query)) {
            prst.setString(1, tenQuyen);
            prst.setString(2, moTa);
            return prst.executeUpdate() > 0;
        }
    }

    // Sửa quyền
    public boolean SuaQuyen(String tenQuyen, String moTa) throws SQLException {
        String query = "UPDATE quyen SET mo_ta = ? WHERE ten_quyen = ?";
        try (PreparedStatement prst = conn.prepareStatement(query)) {
            prst.setString(1, moTa);
            prst.setString(2, tenQuyen);
            return prst.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Xóa quyền
    public boolean XoaQuyen(String tenQuyen) throws SQLException {
        String sqlDelete = "DELETE FROM quyen WHERE ten_quyen = ?";
        try (PreparedStatement prstDelete = conn.prepareStatement(sqlDelete)) {
            prstDelete.setString(1, tenQuyen);
            return prstDelete.executeUpdate() > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Có lỗi xảy ra trong quá trình xóa: " + ex.getMessage());
            return false;
        }
    }

    // Kiểm tra xem quyền đã tồn tại hay chưa
    public boolean KiemTraQuyen(String tenQuyen) {
        String sql = "SELECT COUNT(*) FROM quyen WHERE ten_quyen = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenQuyen);
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
