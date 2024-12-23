package QuanLyCongVan.Controllers;

import QuanLyCongVan.Model.VanBanDenModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class VanBanDenController extends KetNoiCSDL {

    // Hiển thị danh sách Sổ văn bản
    public ArrayList<String> HienThiSoVanBan() {
        ArrayList<String> tenso = new ArrayList<>();
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            if (conn == null) {
                conn = getConnection();  
            }

            String sql = "SELECT SOVANBAN, NAM FROM SOVANBAN WHERE DAXOA = 0 AND SOVANBAN = N'Số văn bản đến'";
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();

            while (rs.next()) {
                String ten = rs.getString("SOVANBAN");
                int nam = rs.getInt("NAM");
                tenso.add(ten + "-" + nam); // Thêm vào danh sách
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // In lỗi ra console để gỡ lỗi
        } 
        return tenso;
    }
    
    // Hiển thị danh sách Loại văn bản
    public ArrayList<String> hienThiLoaiVanBan() {
        ArrayList<String> loaivb = new ArrayList<>();
        PreparedStatement prst = null;
        ResultSet rs = null;
        
        try {
            String sql = "SELECT LOAIVANBAN FROM LOAIVANBAN WHERE DAXOA=0";
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();

            while (rs.next()) {
                String loai = rs.getString("LOAIVANBAN");
                loaivb.add(loai);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loaivb;
    }

    // Hiển thị danh sách Nơi ban hành
    public ArrayList<String> HienThiNoiBanHanh() {
        ArrayList<String> dsnoi = new ArrayList<>();
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT NOIBANHANH FROM NOIBANHANH WHERE DAXOA=0";
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();

            while (rs.next()) {
                String noi = rs.getString("NOIBANHANH");
                dsnoi.add(noi);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return dsnoi;
    }

    // Hiển thị danh sách văn bản đến
    public ArrayList<VanBanDenModel> HienThiDanhSachVanBanDen(int nam) {
        ArrayList<VanBanDenModel> dsden = new ArrayList<>();
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM VANBANDEN WHERE DAXOA = 0 and nam=?";
            prst = conn.prepareStatement(sql);
            prst.setInt(1, nam);
            rs = prst.executeQuery();
            
            while (rs.next()) {
                VanBanDenModel vbden = new VanBanDenModel();
                vbden.setId(rs.getInt("ID"));
                vbden.setNam(rs.getInt("Nam"));
                vbden.setTenSo(rs.getString("TENSO"));
                vbden.setNam(rs.getInt("Nam"));
                vbden.setSoKyHieu(rs.getString("SOKYHIEU"));
                vbden.setNgayBanHanh(rs.getDate("NGAYBANHANH"));
                vbden.setNoiBanHanh(rs.getString("NOIBANHANH"));
                vbden.setLoaiVanBan(rs.getString("LOAIVANBAN"));
                vbden.setSoDen(rs.getInt("SODEN"));
                vbden.setNgayDen(rs.getDate("NGAYDEN"));
                vbden.setSoTrang(rs.getInt("SOTRANG"));
                vbden.setNguoiNhan(rs.getString("NGUOINHAN"));
                vbden.setNguoiKy(rs.getString("NGUOIKY"));
                vbden.setNguoiDuyet(rs.getString("NGUOIDUYET"));
                vbden.setTrichYeu(rs.getString("TRICHYEU"));
                vbden.setNoiDung(rs.getString("NOIDUNG"));
                vbden.setDuongDanFile(rs.getString("dinhKemfile"));
                vbden.setDaXoa(rs.getBoolean("DAXOA"));

                dsden.add(vbden);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return dsden;
    }
    public ArrayList<VanBanDenModel> HienThiTatCaVanBanDen() {
        ArrayList<VanBanDenModel> dsden = new ArrayList<>();
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM VANBANDEN WHERE DAXOA = 0";
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();
            
            while (rs.next()) {
                VanBanDenModel vbden = new VanBanDenModel();
                vbden.setId(rs.getInt("ID"));
                vbden.setNam(rs.getInt("Nam"));
                vbden.setTenSo(rs.getString("TENSO"));
                vbden.setNam(rs.getInt("Nam"));
                vbden.setSoKyHieu(rs.getString("SOKYHIEU"));
                vbden.setNgayBanHanh(rs.getDate("NGAYBANHANH"));
                vbden.setNoiBanHanh(rs.getString("NOIBANHANH"));
                vbden.setLoaiVanBan(rs.getString("LOAIVANBAN"));
                vbden.setSoDen(rs.getInt("SODEN"));
                vbden.setNgayDen(rs.getDate("NGAYDEN"));
                vbden.setSoTrang(rs.getInt("SOTRANG"));
                vbden.setNguoiNhan(rs.getString("NGUOINHAN"));
                vbden.setNguoiKy(rs.getString("NGUOIKY"));
                vbden.setNguoiDuyet(rs.getString("NGUOIDUYET"));
                vbden.setTrichYeu(rs.getString("TRICHYEU"));
                vbden.setNoiDung(rs.getString("NOIDUNG"));
                vbden.setDuongDanFile(rs.getString("dinhKemfile"));
                vbden.setDaXoa(rs.getBoolean("DAXOA"));

                dsden.add(vbden);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return dsden;
    }
    
    public static java.sql.Date dateConversion(Date utilDate) {
    if (utilDate == null) {
        throw new IllegalArgumentException("Ngày không được để trống!");
    }

    // In giá trị của java.util.Date
    System.out.println("java.util.Date: " + utilDate);

    // Chuyển đổi sang java.sql.Date
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    System.out.println("java.sql.Date: " + sqlDate);

    return sqlDate; // Trả về giá trị đã chuyển đổi
}

    
public ArrayList<VanBanDenModel> HienThiVanBanDenTheoNgay(Date ngaydenmin, Date ngaydenmax) {
    ArrayList<VanBanDenModel> dsden = new ArrayList<>();
    PreparedStatement prst = null;
    ResultSet rs = null;

    try {
        // Chuyển đổi và in ra giá trị ngày
        java.sql.Date sqlNgayDenMin = dateConversion(ngaydenmin);
        java.sql.Date sqlNgayDenMax = dateConversion(ngaydenmax);

        // Câu lệnh SQL
        String sql = "SELECT * FROM VANBANDEN WHERE DAXOA = 0 AND NGAYDEN BETWEEN ? AND ?";
        prst = conn.prepareStatement(sql);

        // Đặt giá trị cho các tham số
        prst.setDate(1, sqlNgayDenMin); // Tham số đầu tiên
        prst.setDate(2, sqlNgayDenMax); // Tham số thứ hai

        // Thực thi truy vấn
        rs = prst.executeQuery();

        // Duyệt kết quả trả về
        while (rs.next()) {
            VanBanDenModel vbden = new VanBanDenModel();
            vbden.setId(rs.getInt("ID"));
            vbden.setNam(rs.getInt("Nam"));
            vbden.setTenSo(rs.getString("TENSO"));
            vbden.setSoKyHieu(rs.getString("SOKYHIEU"));
            vbden.setNgayBanHanh(rs.getDate("NGAYBANHANH"));
            vbden.setNoiBanHanh(rs.getString("NOIBANHANH"));
            vbden.setLoaiVanBan(rs.getString("LOAIVANBAN"));
            vbden.setSoDen(rs.getInt("SODEN"));
            vbden.setNgayDen(rs.getDate("NGAYDEN"));
            vbden.setSoTrang(rs.getInt("SOTRANG"));
            vbden.setNguoiNhan(rs.getString("NGUOINHAN"));
            vbden.setNguoiKy(rs.getString("NGUOIKY"));
            vbden.setNguoiDuyet(rs.getString("NGUOIDUYET"));
            vbden.setTrichYeu(rs.getString("TRICHYEU"));
            vbden.setNoiDung(rs.getString("NOIDUNG"));
            vbden.setDuongDanFile(rs.getString("DINHKEMFILE"));
            vbden.setDaXoa(rs.getBoolean("DAXOA"));

            dsden.add(vbden);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } 
    return dsden;
}
public ArrayList<VanBanDenModel> HienThiTimKiemVanBanDen() {
    ArrayList<VanBanDenModel> dsden = new ArrayList<>();
    PreparedStatement prst = null;
    ResultSet rs = null;

    try {

        // Câu lệnh SQL
        String sql = "SELECT * FROM VANBANDEN WHERE DAXOA = 0";
        prst = conn.prepareStatement(sql);
        rs = prst.executeQuery();

        // Duyệt kết quả trả về
        while (rs.next()) {
            VanBanDenModel vbden = new VanBanDenModel();
            vbden.setId(rs.getInt("ID"));
            vbden.setNam(rs.getInt("Nam"));
            vbden.setTenSo(rs.getString("TENSO"));
            vbden.setSoKyHieu(rs.getString("SOKYHIEU"));
            vbden.setNgayBanHanh(rs.getDate("NGAYBANHANH"));
            vbden.setNoiBanHanh(rs.getString("NOIBANHANH"));
            vbden.setLoaiVanBan(rs.getString("LOAIVANBAN"));
            vbden.setSoDen(rs.getInt("SODEN"));
            vbden.setNgayDen(rs.getDate("NGAYDEN"));
            vbden.setSoTrang(rs.getInt("SOTRANG"));
            vbden.setNguoiNhan(rs.getString("NGUOINHAN"));
            vbden.setNguoiKy(rs.getString("NGUOIKY"));
            vbden.setNguoiDuyet(rs.getString("NGUOIDUYET"));
            vbden.setTrichYeu(rs.getString("TRICHYEU"));
            vbden.setNoiDung(rs.getString("NOIDUNG"));
            vbden.setDuongDanFile(rs.getString("DINHKEMFILE"));
            vbden.setDaXoa(rs.getBoolean("DAXOA"));

            dsden.add(vbden);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } 
    return dsden;
}

  public ArrayList<VanBanDenModel> filterVanBanDenByDateRange(java.sql.Date startDate, java.sql.Date endDate) {
    ArrayList<VanBanDenModel> list = new ArrayList<>();
    try {
        String query = "SELECT * FROM VANBANDEN WHERE NGAYBANHANH BETWEEN ? AND ? AND DAXOA = 0";
        PreparedStatement prst = conn.prepareStatement(query);
        prst.setDate(1, startDate);
        prst.setDate(2, endDate);

        ResultSet rs = prst.executeQuery();
        while (rs.next()) {
            VanBanDenModel vanBanDen = new VanBanDenModel();
            // Ánh xạ các trường từ ResultSet vào VanBanDenModel
            vanBanDen.setId(rs.getInt("ID"));
            vanBanDen.setNam(rs.getInt("Nam"));
            vanBanDen.setTenSo(rs.getString("TENSO"));
            vanBanDen.setSoKyHieu(rs.getString("SOKYHIEU"));
            vanBanDen.setNgayBanHanh(rs.getDate("NGAYBANHANH"));
            vanBanDen.setNoiBanHanh(rs.getString("NOIBANHANH"));
            vanBanDen.setLoaiVanBan(rs.getString("LOAIVANBAN"));
            vanBanDen.setSoDen(rs.getInt("SODEN"));
            vanBanDen.setNgayDen(rs.getDate("NGAYDEN"));
            vanBanDen.setSoTrang(rs.getInt("SOTRANG"));
            vanBanDen.setNguoiNhan(rs.getString("NGUOINHAN"));
            vanBanDen.setNguoiKy(rs.getString("NGUOIKY"));
            vanBanDen.setNguoiDuyet(rs.getString("NGUOIDUYET"));
            vanBanDen.setTrichYeu(rs.getString("TRICHYEU"));
            vanBanDen.setNoiDung(rs.getString("NOIDUNG"));
            vanBanDen.setDuongDanFile(rs.getString("DINHKEMFILE"));
            list.add(vanBanDen);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return list;
}


    
    // Thực hiện việc thêm một văn bản đến
   public int Them(VanBanDenModel vanBan) {
    PreparedStatement prst = null;

    try {
        // Kiểm tra kết nối
        if (conn == null) {
            conn = getConnection();
        }

        // Câu lệnh SQL để thêm văn bản
        String sql = "INSERT INTO VANBANDEN (TENSO,Nam, SOKYHIEU, NGAYBANHANH, NOIBANHANH, LOAIVANBAN, SODEN, NGAYDEN, SOTRANG, NGUOINHAN, NGUOIKY, NGUOIDUYET, TRICHYEU, NOIDUNG, dinhKemfile, DaXoa) "
           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        // Chuẩn bị statement và thực hiện
        prst = conn.prepareStatement(sql);
        prst.setString(1, vanBan.getTenSo());
        prst.setInt(2, vanBan.getNam());
        prst.setString(3, vanBan.getSoKyHieu());
        prst.setDate(4, new java.sql.Date(vanBan.getNgayBanHanh().getTime()));
        prst.setString(5, vanBan.getNoiBanHanh());
        prst.setString(6, vanBan.getLoaiVanBan());
        prst.setInt(7, vanBan.getSoDen());
        prst.setDate(8, new java.sql.Date(vanBan.getNgayDen().getTime()));
        prst.setInt(9, vanBan.getSoTrang());
        prst.setString(10, vanBan.getNguoiNhan());
        prst.setString(11, vanBan.getNguoiKy());
        prst.setString(12, vanBan.getNguoiDuyet());
        prst.setString(13, vanBan.getTrichYeu());
        prst.setString(14, vanBan.getNoiDung());
        prst.setString(15, vanBan.getDuongDanFile());
        prst.setBoolean(16, vanBan.isDaXoa());  // Thêm cột DaXoa cuối cùng

        // Thực thi câu lệnh SQL
        return prst.executeUpdate();  // Trả về số bản ghi bị ảnh hưởng
    } catch (SQLException ex) {
        ex.printStackTrace();  // In lỗi ra console
    
    }

    return -1;  // Trả về -1 nếu có lỗi
}
// Hàm xóa tất cả văn bản đến theo năm
public int XoaVanBanDen(VanBanDenModel vbden) {
    PreparedStatement prst = null;

    try {
        // Kiểm tra kết nối
        if (conn == null) {
            conn = getConnection();
        }

        // Câu lệnh SQL cập nhật cột DaXoa thành 1 theo năm
        String sql = "UPDATE VANBANDEN SET DAXOA = 1 WHERE id = ?";
        prst = conn.prepareStatement(sql);
        prst.setInt(1, vbden.getId());
        // Thực thi câu lệnh SQL
        return prst.executeUpdate(); // Trả về số bản ghi bị ảnh hưởng
    } catch (SQLException ex) {
        ex.printStackTrace(); // In lỗi ra console
    } 

    return -1; // Trả về -1 nếu có lỗi
}

public ArrayList<VanBanDenModel> searchVanBanDen(String soVanBan, int nam, String loaiVanBan, String soKyHieu, 
                                                 String noiBanHanh, String noiDung, 
                                                 Date startDate, Date endDate) throws SQLException {
    ArrayList<VanBanDenModel> list = new ArrayList<>();
    
    // Tạo truy vấn SQL cơ bản
    StringBuilder query = new StringBuilder("SELECT * FROM VanBanDen WHERE daXoa = 0");

    // Thêm các điều kiện tìm kiếm nếu có
    if (soVanBan != null && !soVanBan.isEmpty() && !soVanBan.equals("Tất cả")) {
        query.append(" AND TenSo LIKE ?");
    }

    if (loaiVanBan != null && !loaiVanBan.isEmpty() && !loaiVanBan.equals("Tất cả")) {
        query.append(" AND loaiVanBan LIKE ?");
    }
    if (soKyHieu != null && !soKyHieu.isEmpty()) {
        query.append(" AND soKyHieu LIKE ?");
    }
    if (noiBanHanh != null && !noiBanHanh.isEmpty()) {
        query.append(" AND noiBanHanh LIKE ?");
    }
    if (startDate != null && endDate != null) {
        query.append(" AND ngayDen BETWEEN ? AND ?");
    }
    if (noiDung != null && !noiDung.isEmpty()) {
        query.append(" AND noiDung LIKE ?");
    }
    if (nam > 0) {
        query.append(" AND nam = ?");
    }

    // Chuẩn bị câu lệnh truy vấn
    PreparedStatement prst = conn.prepareStatement(query.toString());
    int index = 1;

    // Gán các tham số cho truy vấn
    if (soVanBan != null && !soVanBan.isEmpty() && !soVanBan.equals("Tất cả")) {
        prst.setString(index++, "%" + soVanBan + "%");  // Gán tham số cho 'soVanBan'
    }
    if (loaiVanBan != null && !loaiVanBan.isEmpty() && !loaiVanBan.equals("Tất cả")) {
        prst.setString(index++, "%" + loaiVanBan + "%");
    }
    if (soKyHieu != null && !soKyHieu.isEmpty()) {
        prst.setString(index++, "%" + soKyHieu + "%");
    }
    if (noiBanHanh != null && !noiBanHanh.isEmpty()) {
        prst.setString(index++, "%" + noiBanHanh + "%");
    }
    if (startDate != null && endDate != null) {
        // Chuyển startDate và endDate thành java.sql.Date
        prst.setDate(index++, new java.sql.Date(startDate.getTime()));
        prst.setDate(index++, new java.sql.Date(endDate.getTime()));
    }
    if (noiDung != null && !noiDung.isEmpty()) {
        prst.setString(index++, "%" + noiDung + "%");
    }
    if (nam > 0) {
        prst.setInt(index++, nam);  // Gán tham số cho 'nam'
    }

    // Thực thi truy vấn
    ResultSet rs = prst.executeQuery();
    while (rs.next()) {
        // Lấy dữ liệu từ ResultSet và tạo đối tượng VanBanDenModel
        VanBanDenModel obj = new VanBanDenModel(
            rs.getInt("id"),
            rs.getString("TenSo"),
            rs.getInt("nam"),
            rs.getString("soKyHieu"),
            rs.getDate("ngayBanHanh"),
            rs.getString("noiBanHanh"),
            rs.getString("loaiVanBan"),
            rs.getInt("soDen"),
            rs.getDate("ngayDen"),
            rs.getInt("soTrang"),
            rs.getString("nguoiNhan"),
            rs.getString("nguoiKy"),
            rs.getString("nguoiDuyet"),
            rs.getString("trichYeu"),
            rs.getString("noiDung"),
            rs.getString("dinhKemFile"),
            rs.getBoolean("daXoa")
        );
        list.add(obj);
    }
    return list;
}


public boolean kiemTraId(int id) {
    PreparedStatement prst = null;
    ResultSet rs = null;

    try {
        // Kiểm tra kết nối
        if (conn == null) {
            conn = getConnection();
        }

        // Câu lệnh SQL kiểm tra ID đã tồn tại hay chưa
        String sql = "SELECT COUNT(*) FROM VANBANDEN WHERE ID = ? AND DAXOA = 0";
        prst = conn.prepareStatement(sql);
        prst.setInt(1, id); // Gán giá trị ID vào câu lệnh SQL

        rs = prst.executeQuery();

        // Kiểm tra nếu có kết quả trả về lớn hơn 0, nghĩa là ID đã tồn tại
        if (rs.next() && rs.getInt(1) > 0) {
            return true; // ID đã tồn tại
        }
    } catch (SQLException ex) {
        ex.printStackTrace(); // In lỗi ra console nếu có
    } 

    return false; // ID không tồn tại
}

public int CapNhat(VanBanDenModel vanBan) {
    PreparedStatement prst = null;

    try {
        // Kiểm tra kết nối
        if (conn == null) {
            conn = getConnection();
        }

        // Câu lệnh SQL để cập nhật văn bản đến
        String sql = "UPDATE VANBANDEN SET "
                     + "TENSO = ?, "
                     + "Nam = ?, "
                     + "SOKYHIEU = ?, "
                     + "NGAYBANHANH = ?, "
                     + "NOIBANHANH = ?, "
                     + "LOAIVANBAN = ?, "
                     + "SODEN = ?, "
                     + "NGAYDEN = ?, "
                     + "SOTRANG = ?, "
                     + "NGUOINHAN = ?, "
                     + "NGUOIKY = ?, "
                     + "NGUOIDUYET = ?, "
                     + "TRICHYEU = ?, "
                     + "NOIDUNG = ?, "
                     + "DINHKEMFILE = ?, "
                     + "DAXOA = ? "
                     + "WHERE ID = ?"; // Cập nhật theo ID

        // Chuẩn bị statement và thực hiện
        prst = conn.prepareStatement(sql);
        prst.setString(1, vanBan.getTenSo());
        prst.setInt(2, vanBan.getNam());
        prst.setString(3, vanBan.getSoKyHieu());
        prst.setDate(4, new java.sql.Date(vanBan.getNgayBanHanh().getTime()));
        prst.setString(5, vanBan.getNoiBanHanh());
        prst.setString(6, vanBan.getLoaiVanBan());
        prst.setInt(7, vanBan.getSoDen());
        prst.setDate(8, new java.sql.Date(vanBan.getNgayDen().getTime()));
        prst.setInt(9, vanBan.getSoTrang());
        prst.setString(10, vanBan.getNguoiNhan());
        prst.setString(11, vanBan.getNguoiKy());
        prst.setString(12, vanBan.getNguoiDuyet());
        prst.setString(13, vanBan.getTrichYeu());
        prst.setString(14, vanBan.getNoiDung());
        prst.setString(15, vanBan.getDuongDanFile());
        prst.setBoolean(16, vanBan.isDaXoa());  // Đảm bảo truyền đúng giá trị boolean
        prst.setInt(17, vanBan.getId());  // Cập nhật dựa trên ID

        // Thực thi câu lệnh SQL và trả về số bản ghi bị ảnh hưởng
        return prst.executeUpdate();
    } catch (Exception ex) {
        ex.printStackTrace();  // In lỗi ra console nếu có lỗi
    }

    return -1;  // Trả về -1 nếu có lỗi
}



}
