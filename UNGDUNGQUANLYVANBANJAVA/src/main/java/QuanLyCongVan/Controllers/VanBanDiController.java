package QuanLyCongVan.Controllers;

import QuanLyCongVan.Model.VanBanDenModel;
import QuanLyCongVan.Model.VanBanDiModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class VanBanDiController extends KetNoiCSDL {
    
    // Method to display the list of document numbers
    public ArrayList<String> HienThiSoVanBan() {
        ArrayList<String> tenso = new ArrayList<>();
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            if (conn == null) {
                conn = getConnection();  
            }

            String sql = "SELECT SOVANBAN,NAM FROM SOVANBAN WHERE DAXOA = 0 AND Sovanban = N'Số văn bản đi'";
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();

            while (rs.next()) {
                String ten = rs.getString("SOVANBAN"); 
                int nam = rs.getInt("Nam");
                tenso.add(ten + "-" + nam); 
            }
        } catch (Exception ex) {
            ex.printStackTrace(); 
        }
        return tenso;
    }

    // Method to add a new 'VanBanDi' record
    public int Them(VanBanDiModel vanBanDi) {
        PreparedStatement prst = null;

        try {
            if (conn == null) {
                conn = getConnection();
            }

            String sql = "INSERT INTO VANBANDI (TENSO, NAM, SOKYHIEU, NGAYBANHANH, noiNhan, LOAIVANBAN, SODI, SLBAN, SOTRANG, NGUOIGUI, NGUOIKY, NGUOIDUYET, TRICHYEU, NOIDUNG, DINHKEMFILE, DAXOA) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            prst = conn.prepareStatement(sql);
            prst.setString(1, vanBanDi.getTenSo());
            prst.setInt(2, vanBanDi.getNam());
            prst.setString(3, vanBanDi.getSoKyHieu());
            prst.setDate(4, new java.sql.Date(vanBanDi.getNgayBanHanh().getTime()));
            prst.setString(5, vanBanDi.getNoiNhan());
            prst.setString(6, vanBanDi.getLoaiVanBan());
            prst.setInt(7, vanBanDi.getSoDi());
            prst.setInt(8, vanBanDi.getSlBan());
            prst.setInt(9, vanBanDi.getSoTrang());
            prst.setString(10, vanBanDi.getNguoiGui());
            prst.setString(11, vanBanDi.getNguoiKy());
            prst.setString(12, vanBanDi.getNguoiDuyet());
            prst.setString(13, vanBanDi.getTrichYeu());
            prst.setString(14, vanBanDi.getNoiDung());
            prst.setString(15, vanBanDi.getDuongDanFile());
            prst.setBoolean(16, vanBanDi.isDaXoa());

            return prst.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    public int CapNhat(VanBanDiModel vb) {
    int result = 0;
    String sql = "UPDATE VANBANDI SET TENSO = ?, NAM = ?, NGAYBANHANH = ?, SOKYHIEU = ?, NOINHAN = ?, LOAIVANBAN = ?, SODI = ?, SLBAN = ?, SOTRANG = ?, NGUOIGUI = ?, NGUOIKY = ?, NGUOIDUYET = ?, TRICHYEU = ?, NOIDUNG = ?, dinhKemfile = ?, DAXOA = ? WHERE ID = ?";
    try (PreparedStatement prst = conn.prepareStatement(sql)) {
        prst.setString(1, vb.getTenSo());
        prst.setInt(2, vb.getNam());
        prst.setDate(3, new java.sql.Date(vb.getNgayBanHanh().getTime()));
        prst.setString(4, vb.getSoKyHieu());
        prst.setString(5, vb.getNoiNhan());
        prst.setString(6, vb.getLoaiVanBan());
        prst.setInt(7, vb.getSoDi());
        prst.setInt(8, vb.getSlBan());
        prst.setInt(9, vb.getSoTrang());
        prst.setString(10, vb.getNguoiGui());
        prst.setString(11, vb.getNguoiKy());
        prst.setString(12, vb.getNguoiDuyet());
        prst.setString(13, vb.getTrichYeu());
        prst.setString(14, vb.getNoiDung());
        prst.setString(15, vb.getDuongDanFile());
        prst.setBoolean(16, vb.isDaXoa());
        prst.setInt(17, vb.getId());
        result = prst.executeUpdate();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return result;
}
 public ArrayList<VanBanDiModel> HienThiVanBanDiTheoNgay(Date ngaybanmin, Date ngaybanmax) {
    ArrayList<VanBanDiModel> dsDi = new ArrayList<>();
    PreparedStatement prst = null;
    ResultSet rs = null;

    try {
        // Chuyển đổi và in ra giá trị ngày
        java.sql.Date sqlNgayBanMin = new java.sql.Date(ngaybanmin.getTime());
        java.sql.Date sqlNgayBanMax = new java.sql.Date(ngaybanmax.getTime());

        // Câu lệnh SQL tìm kiếm văn bản đi theo ngày
        String sql = "SELECT * FROM VANBANDI WHERE DAXOA = 0 AND NGAYBANHANH BETWEEN ? AND ?";
        prst = conn.prepareStatement(sql);

        // Đặt giá trị cho các tham số
        prst.setDate(1, sqlNgayBanMin); // Tham số ngày bắt đầu
        prst.setDate(2, sqlNgayBanMax); // Tham số ngày kết thúc

        // Thực thi truy vấn
        rs = prst.executeQuery();

        // Duyệt kết quả trả về
        while (rs.next()) {
            VanBanDiModel vbDi = new VanBanDiModel();
            vbDi.setId(rs.getInt("ID"));
            vbDi.setNam(rs.getInt("NAM"));
            vbDi.setTenSo(rs.getString("TENSO"));
            vbDi.setSoKyHieu(rs.getString("SOKYHIEU"));
            vbDi.setNgayBanHanh(rs.getDate("NGAYBANHANH"));
            vbDi.setNoiNhan(rs.getString("NOINHAN"));
            vbDi.setLoaiVanBan(rs.getString("LOAIVANBAN"));
            vbDi.setSoDi(rs.getInt("SODI"));
            vbDi.setSlBan(rs.getInt("SLBAN"));
            vbDi.setSoTrang(rs.getInt("SOTRANG"));
            vbDi.setNguoiGui(rs.getString("NGUOIGUI"));
            vbDi.setNguoiKy(rs.getString("NGUOIKY"));
            vbDi.setNguoiDuyet(rs.getString("NGUOIDUYET"));
            vbDi.setTrichYeu(rs.getString("TRICHYEU"));
            vbDi.setNoiDung(rs.getString("NOIDUNG"));
            vbDi.setDuongDanFile(rs.getString("DINHKEMFILE"));
            vbDi.setDaXoa(rs.getBoolean("DAXOA"));

            dsDi.add(vbDi);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } catch (Exception ex) {
        ex.printStackTrace();
    } 
    return dsDi;
}
 
 public ArrayList<VanBanDiModel> HienThiTimKiemVanBanDi() {
    ArrayList<VanBanDiModel> dsDi = new ArrayList<>();
    PreparedStatement prst = null;
    ResultSet rs = null;

    try {
        
        String sql = "SELECT * FROM VANBANDI WHERE DAXOA = 0";
        prst = conn.prepareStatement(sql);

        // Thực thi truy vấn
        rs = prst.executeQuery();

        // Duyệt kết quả trả về
        while (rs.next()) {
            VanBanDiModel vbDi = new VanBanDiModel();
            vbDi.setId(rs.getInt("ID"));
            vbDi.setNam(rs.getInt("NAM"));
            vbDi.setTenSo(rs.getString("TENSO"));
            vbDi.setSoKyHieu(rs.getString("SOKYHIEU"));
            vbDi.setNgayBanHanh(rs.getDate("NGAYBANHANH"));
            vbDi.setNoiNhan(rs.getString("NOINHAN"));
            vbDi.setLoaiVanBan(rs.getString("LOAIVANBAN"));
            vbDi.setSoDi(rs.getInt("SODI"));
            vbDi.setSlBan(rs.getInt("SLBAN"));
            vbDi.setSoTrang(rs.getInt("SOTRANG"));
            vbDi.setNguoiGui(rs.getString("NGUOIGUI"));
            vbDi.setNguoiKy(rs.getString("NGUOIKY"));
            vbDi.setNguoiDuyet(rs.getString("NGUOIDUYET"));
            vbDi.setTrichYeu(rs.getString("TRICHYEU"));
            vbDi.setNoiDung(rs.getString("NOIDUNG"));
            vbDi.setDuongDanFile(rs.getString("DINHKEMFILE"));
            vbDi.setDaXoa(rs.getBoolean("DAXOA"));

            dsDi.add(vbDi);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } catch (Exception ex) {
        ex.printStackTrace();
    } 
    return dsDi;
}
 public ArrayList<VanBanDiModel> search(String soVanBan, int nam, String loaiVanBan, String soKyHieu, String noiNhan
                                        , String noiDung, Date startDate, Date endDate) throws SQLException {
    ArrayList<VanBanDiModel> list = new ArrayList<>();
    StringBuilder query = new StringBuilder("SELECT * FROM VANBANDI WHERE DAXOA = 0");

    // Kiểm tra điều kiện của các tham số và xây dựng câu lệnh SQL động
    if (soVanBan != null && !soVanBan.isEmpty() && !soVanBan.equals("Tất cả") ) {
        query.append(" AND TENSO LIKE ?");
    }
    if (loaiVanBan != null && !loaiVanBan.isEmpty() && !loaiVanBan.equals("Tất cả")) {
        query.append(" AND LOAIVANBAN LIKE ?");
    }
    if (soKyHieu != null && !soKyHieu.isEmpty()) {
        query.append(" AND SOKYHIEU LIKE ?");
    }
    if (noiNhan != null && !noiNhan.isEmpty()) {
        query.append(" AND NOINHAN LIKE ?");
    }
 
    if (noiDung != null && !noiDung.isEmpty()) {
        query.append(" AND NOIDUNG LIKE ?");
    }
    if (startDate != null && endDate != null) {
        query.append(" AND NGAYBANHANH BETWEEN ? AND ?");
    }
    if (nam > 0) {
        query.append(" AND NAM = ?");
    }

    PreparedStatement prst = conn.prepareStatement(query.toString());

    // Gán các giá trị vào PreparedStatement
    int index = 1;
     if (soVanBan != null && !soVanBan.isEmpty() && !soVanBan.equals("Tất cả") ) {
        prst.setString(index++, "%" + soVanBan + "%");
    }
    if (loaiVanBan != null && !loaiVanBan.isEmpty() && !loaiVanBan.equals("Tất cả")) {
        prst.setString(index++, "%" + loaiVanBan + "%");
    }
    if (soKyHieu != null && !soKyHieu.isEmpty()) {
        prst.setString(index++, "%" + soKyHieu + "%");
    }
    if (noiNhan != null && !noiNhan.isEmpty()) {
        prst.setString(index++, "%" + noiNhan + "%");
    }
    if (noiDung != null && !noiDung.isEmpty()) {
        prst.setString(index++, "%" + noiDung + "%");
    }
    if (startDate != null && endDate != null) {
        prst.setDate(index++, new java.sql.Date(startDate.getTime()));
        prst.setDate(index++, new java.sql.Date(endDate.getTime()));
    }
    if (nam > 0) {
        prst.setInt(index++, nam);
    }

    // Thực thi truy vấn
    ResultSet rs = prst.executeQuery();
    while (rs.next()) {
        VanBanDiModel vbDi = new VanBanDiModel();
        vbDi.setId(rs.getInt("ID"));
        vbDi.setNam(rs.getInt("NAM"));
        vbDi.setTenSo(rs.getString("TENSO"));
        vbDi.setSoKyHieu(rs.getString("SOKYHIEU"));
        vbDi.setNgayBanHanh(rs.getDate("NGAYBANHANH"));
        vbDi.setNoiNhan(rs.getString("NOINHAN"));
        vbDi.setLoaiVanBan(rs.getString("LOAIVANBAN"));
        vbDi.setSoDi(rs.getInt("SODI"));
        vbDi.setSlBan(rs.getInt("SLBAN"));
        vbDi.setSoTrang(rs.getInt("SOTRANG"));
        vbDi.setNguoiGui(rs.getString("NGUOIGUI"));
        vbDi.setNguoiKy(rs.getString("NGUOIKY"));
        vbDi.setNguoiDuyet(rs.getString("NGUOIDUYET"));
        vbDi.setTrichYeu(rs.getString("TRICHYEU"));
        vbDi.setNoiDung(rs.getString("NOIDUNG"));
        vbDi.setDuongDanFile(rs.getString("DINHKEMFILE"));
        vbDi.setDaXoa(rs.getBoolean("DAXOA"));
        list.add(vbDi);
    }

    return list;
}

//    
//public ArrayList<VanBanDiModel> TimKiem(Date ngaybanhanhmin, Date ngaybanhanhmax, String soVanBan, int nam, String loaiVanBan, String soKyHieu, String noiNhan, String trichYeu, String noiDung) {
//    ArrayList<VanBanDiModel> dstimkiemden = new ArrayList<>();
//    PreparedStatement prst = null;
//    ResultSet rs = null;
//
//    try {
//        StringBuilder sql = new StringBuilder("SELECT * FROM VANBANDI WHERE DAXOA = 0");
//
//        if (ngaybanhanhmin != null && ngaybanhanhmax != null) {
//            sql.append(" AND NGAYBANHANH BETWEEN ? AND ?");
//        }
//        if ((!soVanBan.isEmpty())&&(!soVanBan.equals("Tất cả"))) {
//            sql.append(" AND TENSO = ?");
//        }
//        if (nam > 0) {
//            sql.append(" AND NAM = ?");
//        }
//        if ((!loaiVanBan.isEmpty())&&(!loaiVanBan.equals("Tất cả"))) {
//            sql.append(" AND LOAIVANBAN = ?");
//        }
//        if (!soKyHieu.isEmpty()) {
//            sql.append(" AND SOKYHIEU LIKE ?");
//        }
//        if (!noiNhan.isEmpty()) {
//            sql.append(" AND NOINHAN LIKE ?");
//        }
//        if (!trichYeu.isEmpty()) {
//            sql.append(" AND TRICHYEU LIKE ?");
//        }
//        if (!noiDung.isEmpty()) {
//            sql.append(" AND NOIDUNG LIKE ?");
//        }
//
//        prst = conn.prepareStatement(sql.toString());
//
//        int paramIndex = 1;
//        if (ngaybanhanhmin != null && ngaybanhanhmax != null) {
//            prst.setDate(paramIndex++, new java.sql.Date(ngaybanhanhmin.getTime()));
//            prst.setDate(paramIndex++, new java.sql.Date(ngaybanhanhmax.getTime()));
//        }
//        if (!soVanBan.isEmpty()) {
//            prst.setString(paramIndex++, soVanBan);
//        }
//        if (nam > 0) {
//            prst.setInt(paramIndex++, nam);
//        }
//        if (!loaiVanBan.isEmpty()) {
//            prst.setString(paramIndex++, loaiVanBan);
//        }
//        if (!soKyHieu.isEmpty()) {
//            prst.setString(paramIndex++, "%" + soKyHieu + "%");
//        }
//        if (!noiNhan.isEmpty()) {
//            prst.setString(paramIndex++, "%" + noiNhan + "%");
//        }
//        if (!trichYeu.isEmpty()) {
//            prst.setString(paramIndex++, "%" + trichYeu + "%");
//        }
//        if (!noiDung.isEmpty()) {
//            prst.setString(paramIndex++, "%" + noiDung + "%");
//        }
//
//        rs = prst.executeQuery();
//        while (rs.next()) {
//            VanBanDiModel vbDi = new VanBanDiModel();
//            vbDi.setId(rs.getInt("ID"));
//            vbDi.setNam(rs.getInt("NAM"));
//            vbDi.setTenSo(rs.getString("TENSO"));
//            vbDi.setSoKyHieu(rs.getString("SOKYHIEU"));
//            vbDi.setNgayBanHanh(rs.getDate("NGAYBANHANH"));
//            vbDi.setNoiNhan(rs.getString("NOINHAN"));
//            vbDi.setLoaiVanBan(rs.getString("LOAIVANBAN"));
//            vbDi.setSoDi(rs.getInt("SODI"));
//            vbDi.setTrichYeu(rs.getString("TRICHYEU"));
//            vbDi.setNoiDung(rs.getString("NOIDUNG"));
//
//            dstimkiemden.add(vbDi);
//        }
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//    } 
//    return dstimkiemden;
//}


    public ArrayList<VanBanDiModel> HienThiTatCaVanBanDi() {
        ArrayList<VanBanDiModel> dsDi = new ArrayList<>();
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
        if (conn == null) {
            conn = getConnection(); // Đảm bảo kết nối không bị null
        }

        String sql = "SELECT * FROM VANBANDI WHERE DAXOA = 0";
        prst = conn.prepareStatement(sql);
        rs = prst.executeQuery();

        while (rs.next()) {
            VanBanDiModel vbDi = new VanBanDiModel();
            vbDi.setId(rs.getInt("ID"));
            vbDi.setNam(rs.getInt("NAM"));
            vbDi.setTenSo(rs.getString("TENSO"));
            vbDi.setSoKyHieu(rs.getString("SOKYHIEU"));
            vbDi.setNgayBanHanh(rs.getDate("NGAYBANHANH"));
            vbDi.setNoiNhan(rs.getString("NoiNhan"));
            vbDi.setLoaiVanBan(rs.getString("LOAIVANBAN"));
            vbDi.setSoDi(rs.getInt("SODI"));
            vbDi.setSlBan(rs.getInt("SLBAN"));
            vbDi.setSoTrang(rs.getInt("SOTRANG"));
            vbDi.setNguoiGui(rs.getString("NGUOIGUI"));
            vbDi.setNguoiKy(rs.getString("NGUOIKY"));
            vbDi.setNguoiDuyet(rs.getString("NGUOIDUYET"));
            vbDi.setTrichYeu(rs.getString("TRICHYEU"));
            vbDi.setNoiDung(rs.getString("NOIDUNG"));
            vbDi.setDuongDanFile(rs.getString("DINHKEMFILE"));
            vbDi.setDaXoa(rs.getBoolean("DAXOA"));

            dsDi.add(vbDi);
        }
    } catch (Exception ex) {
        ex.printStackTrace(); 
    }
    return dsDi;
    }
    public ArrayList<String> hienThiLoaiVanBan() {
        ArrayList<String> loaivb = new ArrayList<>();
        try {
            String sql = "SELECT LOAIVANBAN FROM LOAIVANBAN WHERE DAXOA = 0";
            PreparedStatement prst = conn.prepareStatement(sql);
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                String loai = rs.getString("LOAIVANBAN");
                loaivb.add(loai);
            }
        } catch (Exception ex) {
            ex.printStackTrace(); 
        }
        return loaivb;
    }

    // Method to display all outgoing documents
    public ArrayList<VanBanDiModel> HienThiDanhSachVanBanDi(int nam) {
    ArrayList<VanBanDiModel> dsDi = new ArrayList<>();
    PreparedStatement prst = null;
    ResultSet rs = null;

    try {
        if (conn == null) {
            conn = getConnection(); // Đảm bảo kết nối không bị null
        }

        String sql = "SELECT * FROM VANBANDI WHERE DAXOA = 0 AND NAM=?";
        prst = conn.prepareStatement(sql);
        prst.setInt(1, nam);
        rs = prst.executeQuery();

        while (rs.next()) {
            VanBanDiModel vbDi = new VanBanDiModel();
            vbDi.setId(rs.getInt("ID"));
            vbDi.setNam(rs.getInt("NAM"));
            vbDi.setTenSo(rs.getString("TENSO"));
            vbDi.setSoKyHieu(rs.getString("SOKYHIEU"));
            vbDi.setNgayBanHanh(rs.getDate("NGAYBANHANH"));
            vbDi.setNoiNhan(rs.getString("NoiNhan"));
            vbDi.setLoaiVanBan(rs.getString("LOAIVANBAN"));
            vbDi.setSoDi(rs.getInt("SODI"));
            vbDi.setSlBan(rs.getInt("SLBAN"));
            vbDi.setSoTrang(rs.getInt("SOTRANG"));
            vbDi.setNguoiGui(rs.getString("NGUOIGUI"));
            vbDi.setNguoiKy(rs.getString("NGUOIKY"));
            vbDi.setNguoiDuyet(rs.getString("NGUOIDUYET"));
            vbDi.setTrichYeu(rs.getString("TRICHYEU"));
            vbDi.setNoiDung(rs.getString("NOIDUNG"));
            vbDi.setDuongDanFile(rs.getString("DINHKEMFILE"));
            vbDi.setDaXoa(rs.getBoolean("DAXOA"));

            dsDi.add(vbDi);
        }
    } catch (Exception ex) {
        ex.printStackTrace(); 
    }
    return dsDi;
}
public int XoaVanBanDi(int idVanBanDi) {
    PreparedStatement prst = null;

    try {
        // Đảm bảo kết nối cơ sở dữ liệu
        if (conn == null) {
            conn = getConnection();
        }

        // In log thông tin ID của văn bản cần xóa (hữu ích cho kiểm tra lỗi)
        System.out.println("Đang xóa văn bản ID: " + idVanBanDi);

        // Câu lệnh SQL cập nhật cột DAXOA thành 1 với điều kiện ID
        String sql = "UPDATE VANBANDI SET DAXOA = 1 WHERE ID = ?";
        prst = conn.prepareStatement(sql);
        prst.setInt(1, idVanBanDi);

        // Thực thi câu lệnh SQL và trả về số bản ghi bị ảnh hưởng
        return prst.executeUpdate();
    } catch (SQLException ex) {
        // Ghi log lỗi nếu xảy ra ngoại lệ
        ex.printStackTrace();
    
    }

    return -1; // Trả về -1 nếu có lỗi xảy ra
}

public ArrayList<String> LaySoVanBan() {
        ArrayList<String> tenso = new ArrayList<>();
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            if (conn == null) {
                conn = getConnection();  
            }

            String sql = "SELECT SOVANBAN FROM SOVANBAN WHERE DAXOA = 0 AND SOVANBAN = N'Số văn bản đến'";
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery();

            while (rs.next()) {
                String ten = rs.getString("SOVANBAN");
                tenso.add(ten); // Thêm vào danh sách
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // In lỗi ra console để gỡ lỗi
        } 
        return tenso;
    }

public ArrayList<VanBanDiModel> filterVanBanDiByDateRange(java.sql.Date startDate, java.sql.Date endDate) {
    ArrayList<VanBanDiModel> list = new ArrayList<>();
    try {
        String query = "SELECT * FROM VANBANDI WHERE NGAYBANHANH BETWEEN ? AND ? AND DAXOA = 0";
        PreparedStatement prst = conn.prepareStatement(query);
        prst.setDate(1, startDate);
        prst.setDate(2, endDate);

        ResultSet rs = prst.executeQuery();
        while (rs.next()) {
            VanBanDiModel vanBanDi = new VanBanDiModel();
            // Ánh xạ các trường từ ResultSet vào VanBanDiModel
            vanBanDi.setId(rs.getInt("ID"));
            vanBanDi.setNam(rs.getInt("Nam"));
            vanBanDi.setTenSo(rs.getString("TENSO"));
            vanBanDi.setSoKyHieu(rs.getString("SOKYHIEU"));
            vanBanDi.setNgayBanHanh(rs.getDate("NGAYBANHANH"));
            vanBanDi.setNoiNhan(rs.getString("NOINHAN"));
            vanBanDi.setLoaiVanBan(rs.getString("LOAIVANBAN"));
            vanBanDi.setSoDi(rs.getInt("SODI"));
            vanBanDi.setSoTrang(rs.getInt("SOTRANG"));
            vanBanDi.setNguoiGui(rs.getString("NGUOINHAN"));
            vanBanDi.setNguoiKy(rs.getString("NGUOIKY"));
            vanBanDi.setNguoiDuyet(rs.getString("NGUOIDUYET"));
            vanBanDi.setTrichYeu(rs.getString("TRICHYEU"));
            vanBanDi.setNoiDung(rs.getString("NOIDUNG"));
            vanBanDi.setDuongDanFile(rs.getString("DINHKEMFILE"));
            list.add(vanBanDi);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return list;
}



}
