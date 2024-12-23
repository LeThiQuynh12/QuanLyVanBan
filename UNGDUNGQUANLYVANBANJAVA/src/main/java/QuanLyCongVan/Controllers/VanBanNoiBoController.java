package QuanLyCongVan.Controllers;

import QuanLyCongVan.Model.VanBanNoiBo;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
public class VanBanNoiBoController extends KetNoiCSDL{

    public ArrayList<VanBanNoiBo> getAll() throws SQLException{
        ArrayList<VanBanNoiBo> list  = new ArrayList<VanBanNoiBo>();
      String query = " SELECT \n" +
"    vb.SoKyHieu, vb.TenVanBan,vb.NgayBanHanh,l.loaivanban AS LoaiBanHanh, pbH.TenPhongBan AS PhongBanHanh, pbN.TenPhongBan AS PhongNhan, vb.NguoiNhan, vb.NguoiKy, vb.NguoiDuyet, vb.TrichYeu, vb.NoiDung\n" +
"FROM VanBanNoiBo vb\n" +
"JOIN LOAIVANBAN l ON vb.maloai = l.maloai\n" +
"JOIN tblPhongBan pbH ON vb.MaBanHanh = pbH.MaPhongBan\n" +
"JOIN tblPhongBan pbN ON vb.MaBanNhan = pbN.MaPhongBan;";

        
        PreparedStatement prst = conn.prepareStatement(query);
        ResultSet rs = prst.executeQuery();
        while(rs.next()){
            VanBanNoiBo odj = new VanBanNoiBo(rs);
            list.add(odj);
        }
        return list;
        
    }
    
    public ArrayList<String> getLoaiVanBan() throws SQLException {
    ArrayList<String> loaiVanBanList = new ArrayList<>();
    String query = "SELECT loaivanban FROM LOAIVANBAN";
    try (PreparedStatement prst = conn.prepareStatement(query);
         ResultSet rs = prst.executeQuery()) {
        while (rs.next()) {
            loaiVanBanList.add(rs.getString("loaivanban"));
        }
    }
    return loaiVanBanList;
}

public ArrayList<String> getPhongBan() throws SQLException {
    ArrayList<String> phongBanList = new ArrayList<>();
    String query = "SELECT TenPhongBan FROM tblPhongBan";
    try (PreparedStatement prst = conn.prepareStatement(query);
         ResultSet rs = prst.executeQuery()) {
        while (rs.next()) {
            phongBanList.add(rs.getString("TenPhongBan"));
        }
    }
    return phongBanList;
}

    
    
  public boolean themVanBanNoiBo(String soKyHieu, String tenVanBan, Date ngayBanHanh, String loaiBanHanh, String phongBanHanh, String phongBanNhan, String nguoiNhan, String nguoiKy, String nguoiDuyet, String trichYeu, String noiDung ) throws SQLException {
    String query = "INSERT INTO VanBanNoiBo (SoKyHieu, TenVanBan, NgayBanHanh, maloai, MaBanHanh, MaBanNhan, NguoiNhan, NguoiKy, NguoiDuyet, TrichYeu, NoiDung) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement prst = conn.prepareStatement(query)) {
    prst.setString(1, soKyHieu); // SoKyHieu
    prst.setString(2, tenVanBan); // TenVanBan
    prst.setDate(3, new java.sql.Date(ngayBanHanh.getTime())); // NgayBanHanh
    prst.setString(4, loaiBanHanh); // maloai
    prst.setString(5, phongBanHanh); // MaBanHanh
    prst.setString(6, phongBanNhan); // MaBanNhan
    prst.setString(7, nguoiNhan); // NguoiNhan
    prst.setString(8, nguoiKy); // NguoiKy
    prst.setString(9, nguoiDuyet); // NguoiDuyet
    prst.setString(10, trichYeu); // TrichYeu
    prst.setString(11, noiDung); // NoiDung
            
    
    

    int d = prst.executeUpdate();
    return d > 0;
}
}
public boolean themVanBanNoiBo(
        String soKyHieu,
        String tenVanBan,
        Date ngayBanHanh,
        String maLoaiBanHanh,
        String maPhongBanHanh,
        String maPhongBanNhan,
        String nguoiNhan,
        String nguoiKy,
        String nguoiDuyet,
        String trichYeu,
        String noiDung,
        int maTL) throws SQLException {

    String insertQuery = "INSERT INTO VanBanNoiBo (SoKyHieu, TenVanBan, NgayBanHanh, maloai, MaBanHanh, MaBanNhan, NguoiNhan, NguoiKy, NguoiDuyet, TrichYeu, NoiDung, MaTL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement prst = conn.prepareStatement(insertQuery)) {
        prst.setString(1, soKyHieu);
        prst.setString(2, tenVanBan);
        prst.setDate(3, new java.sql.Date(ngayBanHanh.getTime()));
        prst.setString(4, maLoaiBanHanh);
        prst.setString(5, maPhongBanHanh);
        prst.setString(6, maPhongBanNhan);
        prst.setString(7, nguoiNhan);
        prst.setString(8, nguoiKy);
        prst.setString(9, nguoiDuyet);
        prst.setString(10, trichYeu);
        prst.setString(11, noiDung);
        prst.setInt(12, maTL);

        int rowsInserted = prst.executeUpdate();
        return rowsInserted > 0;
    }
}


 
public String layMaLoaiVanBan(String Loaivanban) throws SQLException {
    String query = "SELECT maloai FROM LOAIVANBAN WHERE loaivanban = ?";
    try (PreparedStatement prst = conn.prepareStatement(query)) {
        prst.setString(1, Loaivanban);
        ResultSet rs = prst.executeQuery();
        if (rs.next()) {
            return rs.getString("maloai");
        }
    }
    return null;
}

public String layMaPhongBan(String tenPhongBan) throws SQLException {
    String query = "SELECT MaPhongBan FROM tblPhongBan WHERE TenPhongBan = ?";
    try (PreparedStatement prst = conn.prepareStatement(query)) {
        prst.setString(1, tenPhongBan);
        ResultSet rs = prst.executeQuery();
        if (rs.next()) {
            return rs.getString("MaPhongBan");
        }
    }
    return null;
}
public boolean kiemTraSoKyHieuTonTai(String soKyHieu) throws SQLException {
    String query = "SELECT COUNT(*) AS count FROM VanBanNoiBo WHERE SoKyHieu = ?";
    try (PreparedStatement prst = conn.prepareStatement(query)) {
        prst.setString(1, soKyHieu);
        ResultSet rs = prst.executeQuery();
        if (rs.next()) {
            return rs.getInt("count") > 0; // Nếu count > 0 thì số ký hiệu đã tồn tại
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }
    return false;
}


public boolean SuaVanBanNoiBo(String soKyHieu, String tenVanBan, Date ngayBanHanh, String loaiBanHanh, String phongBanHanh, String phongBanNhan, String nguoiNhan, String nguoiKy, String nguoiDuyet, String trichYeu, String noiDung) {
    String query = "UPDATE VanBanNoiBo SET TenVanBan = ?, NgayBanHanh = ?, maloai = ?, MaBanHanh = ?, MaBanNhan = ?, NguoiNhan = ?, NguoiKy = ?, NguoiDuyet = ?, TrichYeu = ?, NoiDung = ? WHERE SoKyHieu = ?";
    
    try (PreparedStatement prst = conn.prepareStatement(query)) {
        // Gán giá trị vào các tham số
        prst.setString(1, tenVanBan);
        prst.setDate(2, new java.sql.Date(ngayBanHanh.getTime())); // Convert java.util.Date thành java.sql.Date
        prst.setString(3, loaiBanHanh);
        prst.setString(4, phongBanHanh);
        prst.setString(5, phongBanNhan);
        prst.setString(6, nguoiNhan);
        prst.setString(7, nguoiKy);
        prst.setString(8, nguoiDuyet);
        prst.setString(9, trichYeu);
        prst.setString(10, noiDung);
        prst.setString(11, soKyHieu);

        // Thực thi câu lệnh SQL
        int result = prst.executeUpdate();

        // Trả về true nếu cập nhật thành công
        return result > 0;
    } catch (SQLException e) {
        e.printStackTrace(); // Log lỗi
        return false; // Trả về false nếu có lỗi xảy ra
    }
}
public boolean capNhatVanBanNoiBo(String soKyHieu, String tenVanBan, Date ngayBanHanh, String loaiBanHanh, String phongBanHanh, String phongBanNhan, String nguoiNhan, String nguoiKy, String nguoiDuyet, String trichYeu, String noiDung, int maTL) throws SQLException {
    String query = "UPDATE VanBanNoiBo SET TenVanBan = ?, NgayBanHanh = ?, maloai = ?, MaBanHanh = ?, MaBanNhan = ?, NguoiNhan = ?, NguoiKy = ?, NguoiDuyet = ?, TrichYeu = ?, NoiDung = ?, MaTL = ? WHERE SoKyHieu = ?";

    try (PreparedStatement prst = conn.prepareStatement(query)) {
        prst.setString(1, tenVanBan);
        prst.setDate(2, new java.sql.Date(ngayBanHanh.getTime()));
        prst.setString(3, loaiBanHanh);
        prst.setString(4, phongBanHanh);
        prst.setString(5, phongBanNhan);
        prst.setString(6, nguoiNhan);
        prst.setString(7, nguoiKy);
        prst.setString(8, nguoiDuyet);
        prst.setString(9, trichYeu);
        prst.setString(10, noiDung);
        prst.setInt(11, maTL);  // Thêm MaTL vào câu lệnh
        prst.setString(12, soKyHieu);

        return prst.executeUpdate() > 0;
    }
}

public boolean Xoa(String soKyHieu) throws SQLException{
    String query = "DELETE FROM VanBanNoiBo WHERE SoKyHieu = ?";
    PreparedStatement prst = conn.prepareStatement(query);
    prst.setString(1, soKyHieu);
    int d = prst.executeUpdate();
    return d > 0;
   
}

public ArrayList<VanBanNoiBo> filterDataByDateRange(Date startDate, Date endDate) throws SQLException {
    ArrayList<VanBanNoiBo> list = new ArrayList<>();
    String query = "SELECT vb.SoKyHieu, vb.TenVanBan, vb.NgayBanHanh, l.loaivanban AS LoaiBanHanh, "
                 + "pbH.TenPhongBan AS PhongBanHanh, pbN.TenPhongBan AS PhongNhan, "
                 + "vb.NguoiNhan, vb.NguoiKy, vb.NguoiDuyet, vb.TrichYeu, vb.NoiDung "
                 + "FROM VanBanNoiBo vb "
                 + "JOIN LOAIVANBAN l ON vb.maloai = l.maloai "
                 + "JOIN tblPhongBan pbH ON vb.MaBanHanh = pbH.MaPhongBan "
                 + "JOIN tblPhongBan pbN ON vb.MaBanNhan = pbN.MaPhongBan "
                 + "WHERE vb.NgayBanHanh BETWEEN ? AND ?";
    
    PreparedStatement prst = conn.prepareStatement(query);
    prst.setDate(1, new java.sql.Date(startDate.getTime()));
    prst.setDate(2, new java.sql.Date(endDate.getTime()));
    
    ResultSet rs = prst.executeQuery();
    while (rs.next()) {
        VanBanNoiBo obj = new VanBanNoiBo(rs);
        list.add(obj);
    }
    return list;
}


public ArrayList<VanBanNoiBo> search(String loaiBanHanh, String phongBanHanh, String phongBanNhan, 
                                      String soKyHieu, String tenVanBan, String trichYeu, 
                                      Date startDate, Date endDate) throws SQLException {
    ArrayList<VanBanNoiBo> list = new ArrayList<>();
    StringBuilder query = new StringBuilder("SELECT vb.SoKyHieu, vb.TenVanBan, vb.NgayBanHanh, l.loaivanban AS LoaiBanHanh, " +
                                            "pbH.TenPhongBan AS PhongBanHanh, pbN.TenPhongBan AS PhongNhan, " +
                                            "vb.NguoiNhan, vb.NguoiKy, vb.NguoiDuyet, vb.TrichYeu, vb.NoiDung " +
                                            "FROM VanBanNoiBo vb " +
                                            "JOIN LOAIVANBAN l ON vb.maloai = l.maloai " +
                                            "JOIN tblPhongBan pbH ON vb.MaBanHanh = pbH.MaPhongBan " +
                                            "JOIN tblPhongBan pbN ON vb.MaBanNhan = pbN.MaPhongBan " +
                                            "WHERE 1 = 1");

    // Điều kiện lọc cho các combobox, bỏ qua "Tất cả"
    if (loaiBanHanh != null && !loaiBanHanh.equals("Tất cả")) {
        query.append(" AND l.loaivanban LIKE ?");
    }
    if (phongBanHanh != null && !phongBanHanh.equals("Tất cả")) {
        query.append(" AND pbH.TenPhongBan LIKE ?");
    }
    if (phongBanNhan != null && !phongBanNhan.equals("Tất cả")) {
        query.append(" AND pbN.TenPhongBan LIKE ?");
    }
    if (soKyHieu != null && !soKyHieu.isEmpty()) {
        query.append(" AND vb.SoKyHieu LIKE ?");
    }
    if (tenVanBan != null && !tenVanBan.isEmpty()) {
        query.append(" AND vb.TenVanBan LIKE ?");
    }
    if (trichYeu != null && !trichYeu.isEmpty()) {
        query.append(" AND vb.TrichYeu LIKE ?");
    }

    // Thêm điều kiện thời gian nếu có
    if (startDate != null && endDate != null) {
        query.append(" AND vb.NgayBanHanh BETWEEN ? AND ?");
    }
    
    PreparedStatement prst = conn.prepareStatement(query.toString());

    // Set giá trị cho các tham số trong câu lệnh SQL
    int index = 1;
    if (loaiBanHanh != null && !loaiBanHanh.equals("Tất cả")) {
        prst.setString(index++, "%" + loaiBanHanh + "%");
    }
    if (phongBanHanh != null && !phongBanHanh.equals("Tất cả")) {
        prst.setString(index++, "%" + phongBanHanh + "%");
    }
    if (phongBanNhan != null && !phongBanNhan.equals("Tất cả")) {
        prst.setString(index++, "%" + phongBanNhan + "%");
    }
    if (soKyHieu != null && !soKyHieu.isEmpty()) {
        prst.setString(index++, "%" + soKyHieu + "%");
    }
    if (tenVanBan != null && !tenVanBan.isEmpty()) {
        prst.setString(index++, "%" + tenVanBan + "%");
    }
    if (trichYeu != null && !trichYeu.isEmpty()) {
        prst.setString(index++, "%" + trichYeu + "%");
    }
    if (startDate != null && endDate != null) {
        prst.setDate(index++, new java.sql.Date(startDate.getTime()));
        prst.setDate(index++, new java.sql.Date(endDate.getTime()));
    }

    ResultSet rs = prst.executeQuery();
    while (rs.next()) {
        VanBanNoiBo obj = new VanBanNoiBo(rs);
        list.add(obj);
    }
    return list;
}

    
}



