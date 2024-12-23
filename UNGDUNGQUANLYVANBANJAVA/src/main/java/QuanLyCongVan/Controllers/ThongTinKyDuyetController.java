
package QuanLyCongVan.Controllers;

import QuanLyCongVan.Model.ThongTinKyDuyetModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThongTinKyDuyetController extends KetNoiCSDL{
     // Thêm thông tin người ký duyệt mới vào CSDL
    public boolean themThongTinKyDuyet(String nguoiKy, String nguoiDuyet, String nguoiGui, String nguoiNhan, String noiBanHanh) throws SQLException {
        String sql1 = "INSERT INTO ThongTinKyDuyet (nguoiKy, nguoiDuyet, nguoiGui, nguoiNhan, noiBanHanh) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql1);
        try{
            ps.setString(1, nguoiKy);
            ps.setString(2, nguoiDuyet);
            ps.setString(3, nguoiGui);
            ps.setString(4, nguoiNhan);
            ps.setString(5, noiBanHanh);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy tất cả thông tin người ký duyệt từ CSDL
    public List<ThongTinKyDuyetModel> layTatCaThongTinKyDuyet() {
        List<ThongTinKyDuyetModel> list = new ArrayList<>();
        String sql2 = "SELECT * FROM ThongTinKyDuyet";
        try{
            PreparedStatement ps = conn.prepareStatement(sql2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nguoiKy = rs.getString("nguoiKy");
                String nguoiDuyet = rs.getString("nguoiDuyet");
                String nguoiGui = rs.getString("nguoiGui");
                String nguoiNhan = rs.getString("nguoiNhan");
                String noiBanHanh = rs.getString("noiBanHanh");

                ThongTinKyDuyetModel model = new ThongTinKyDuyetModel(nguoiKy, nguoiDuyet, nguoiGui, nguoiNhan, noiBanHanh);
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
}
