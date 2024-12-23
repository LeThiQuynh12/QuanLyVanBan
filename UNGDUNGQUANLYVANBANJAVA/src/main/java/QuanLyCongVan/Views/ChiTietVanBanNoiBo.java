package QuanLyCongVan.Views;

import QuanLyCongVan.Controllers.VanBanNoiBoController;
import java.sql.Connection;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

public class ChiTietVanBanNoiBo extends javax.swing.JFrame {

    private DefaultTableModel tableModelTaiLieu = new DefaultTableModel();

public ChiTietVanBanNoiBo(
        String sokyhieu, String tenvanban, String ngayBanHanhStr,
        String loaibanhanh, String phongbanhanh, String phongbannhan,
        String nguoinhan, String nguoiky, String nguoiduyet,
        String trichyeu, String noidung) {

    initComponents();
    initTableTaiLieu();

    // Nạp dữ liệu vào combobox
    loadComboboxData();

    // Hiển thị dữ liệu văn bản nội bộ
    txtSoKyHieu.setText(sokyhieu);
    txtTenVanBan.setText(tenvanban);

    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(ngayBanHanhStr);
        dateNgayBanHanh.setDate(date);
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Gán giá trị cho combobox
    cmbLoaiBanHanh.setSelectedItem(loaibanhanh);
    cmbPhongBanHanh.setSelectedItem(phongbanhanh);
    cmbPhongBanNhan.setSelectedItem(phongbannhan);

    txtNguoiNhan.setText(nguoinhan);
    txtNguoiKy.setText(nguoiky);
    txtNguoiDuyet.setText(nguoiduyet);
    txtTrichYeu.setText(trichyeu);
    txtNoiDung.setText(noidung);

    // Thiết lập bảng chỉ đọc
    txtSoKyHieu.setEditable(false);
    txtTenVanBan.setEditable(false);
    dateNgayBanHanh.setEnabled(false);
    cmbLoaiBanHanh.setEnabled(false);
    cmbPhongBanHanh.setEnabled(false);
    cmbPhongBanNhan.setEnabled(false);
    txtNguoiNhan.setEditable(false);
    txtNguoiKy.setEditable(false);
    txtNguoiDuyet.setEditable(false);
    txtTrichYeu.setEditable(false);
    txtNoiDung.setEditable(false);

    // Hiển thị danh sách tài liệu đính kèm
    hienThiTaiLieu(sokyhieu);
}

    private void hienThiTaiLieu(String soKyHieu) {
        DefaultTableModel model = (DefaultTableModel) tbDinhKem.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ nếu có

        String query = """
        SELECT 
            t.MaTL,
            t.TenTL, 
            t.NgayTao, 
            t.KichCo, 
            t.Loai, 
            t.DuongDan
        FROM VanBanNoiBo vb
        JOIN TAILIEU t ON vb.MaTL = t.MaTL
        WHERE vb.SoKyHieu = ?
    """;

        KetNoiCSDL ketNoi = new KetNoiCSDL();
        try (Connection conn = ketNoi.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, soKyHieu);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maTL = rs.getString("MaTL");
                    String tenTL = rs.getString("TenTL");
                    Date ngayTao = rs.getDate("NgayTao");
                    String kichCo = rs.getString("KichCo");
                    String loai = rs.getString("Loai");
                    String duongDan = rs.getString("DuongDan");

                    // Thêm dòng vào JTable (phải đúng thứ tự với colsName)
                    model.addRow(new Object[]{maTL, tenTL, ngayTao, kichCo, loai, duongDan});
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn tài liệu đính kèm: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public class KetNoiCSDL {

        protected java.sql.Connection conn = null;

        public KetNoiCSDL() {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String username = "sa";
                String password = "11101978";
                String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=QUANLYCONGVAN;encrypt=false";
                conn = DriverManager.getConnection(dbUrl, username, password);
                System.out.println("Kết nối thành công");
            } catch (Exception ex) {
                System.out.println("Lỗi kết nối: " + ex.getMessage());
                ex.printStackTrace(); // In chi tiết lỗi để dễ dàng xác định
            }
        }

        public java.sql.Connection getConnection() {
            return conn;
        }
    }

    public void initTableTaiLieu() {
        // Mảng chứa các cột tiêu đề
        String[] colsName = new String[]{"Mã tài liệu", "Tên tài liệu", "Ngày tạo", "Kích cỡ", "Loại", "Đường dẫn"};
        tableModelTaiLieu = new DefaultTableModel(colsName, 0);
        tbDinhKem.setModel(tableModelTaiLieu);

        // Ẩn cột "Mã tài liệu"
        tbDinhKem.getColumnModel().getColumn(0).setMinWidth(0);
        tbDinhKem.getColumnModel().getColumn(0).setMaxWidth(0);
        tbDinhKem.getColumnModel().getColumn(0).setWidth(0);

        // Ẩn cột "Đường dẫn"
        tbDinhKem.getColumnModel().getColumn(5).setMinWidth(0);
        tbDinhKem.getColumnModel().getColumn(5).setMaxWidth(0);
        tbDinhKem.getColumnModel().getColumn(5).setWidth(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnChuaNoiDung = new javax.swing.JTabbedPane();
        pnVanBanNoiBo = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        txtTenVanBan = new javax.swing.JTextField();
        txtSoKyHieu = new javax.swing.JTextField();
        txtNguoiNhan = new javax.swing.JTextField();
        txtNguoiDuyet = new javax.swing.JTextField();
        txtNguoiKy = new javax.swing.JTextField();
        dateNgayBanHanh = new com.toedter.calendar.JDateChooser();
        cmbLoaiBanHanh = new javax.swing.JComboBox<>();
        cmbPhongBanHanh = new javax.swing.JComboBox<>();
        cmbPhongBanNhan = new javax.swing.JComboBox<>();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTrichYeu = new javax.swing.JTextArea();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNoiDung = new javax.swing.JTextArea();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbDinhKem = new javax.swing.JTable();
        btnDocFile = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));
        setUndecorated(true);

        pnChuaNoiDung.setBackground(new java.awt.Color(255, 255, 255));
        pnChuaNoiDung.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                pnChuaNoiDungAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        pnVanBanNoiBo.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Văn bản nội bộ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 255)))); // NOI18N

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel64.setText("Số ký hiệu");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel66.setText("Tên văn bản");

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel67.setText("Ngày ban hành");

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel68.setText("Loại ban hành");

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel69.setText("Phòng ban hành");

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel70.setText("Phòng nhận");

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel71.setText("Người nhận");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel72.setText("Người ký");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel73.setText("Người duyệt");

        cmbPhongBanHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPhongBanHanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateNgayBanHanh, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSoKyHieu, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                    .addComponent(txtTenVanBan))))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel68))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbLoaiBanHanh, 0, 168, Short.MAX_VALUE)
                            .addComponent(cmbPhongBanNhan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbPhongBanHanh, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNguoiDuyet, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNguoiNhan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                        .addComponent(txtNguoiKy, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(jLabel68)
                    .addComponent(jLabel71)
                    .addComponent(cmbLoaiBanHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoKyHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel66)
                                    .addComponent(jLabel69)
                                    .addComponent(cmbPhongBanHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel72)
                                    .addComponent(txtNguoiKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel67))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbPhongBanNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNguoiDuyet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel73)))))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel70)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(txtTenVanBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(dateNgayBanHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trích yếu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N

        txtTrichYeu.setColumns(20);
        txtTrichYeu.setRows(5);
        jScrollPane2.setViewportView(txtTrichYeu);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nội dung", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N

        txtNoiDung.setColumns(20);
        txtNoiDung.setRows(5);
        jScrollPane3.setViewportView(txtNoiDung);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đính kèm file", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        tbDinhKem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã tài liệu", "Tên tài liệu", "Ngày tạo", "Kích cỡ", "Loại", "Đường dẫn"
            }
        ));
        tbDinhKem.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(tbDinhKem);

        btnDocFile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDocFile.setText("Đọc file");
        btnDocFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
                .addGap(17, 17, 17)
                .addComponent(btnDocFile, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btnDocFile)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnVanBanNoiBoLayout = new javax.swing.GroupLayout(pnVanBanNoiBo);
        pnVanBanNoiBo.setLayout(pnVanBanNoiBoLayout);
        pnVanBanNoiBoLayout.setHorizontalGroup(
            pnVanBanNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnVanBanNoiBoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnVanBanNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnVanBanNoiBoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(pnVanBanNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnVanBanNoiBoLayout.createSequentialGroup()
                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnVanBanNoiBoLayout.setVerticalGroup(
            pnVanBanNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnVanBanNoiBoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnChuaNoiDung.addTab("Văn bản nội bộ", pnVanBanNoiBo);

        jButton1.setFont(new java.awt.Font("STZhongsong", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnChuaNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnChuaNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void loadComboboxData() {
        try {
            VanBanNoiBoController controller = new VanBanNoiBoController();

            // Load dữ liệu cho cmbLoaiBanHanh
            ArrayList<String> loaiVanBanList = controller.getLoaiVanBan();
            cmbLoaiBanHanh.removeAllItems(); // Xóa dữ liệu cũ
            for (String loaiVanBan : loaiVanBanList) {
                cmbLoaiBanHanh.addItem(loaiVanBan); // Thêm từng mục vào combobox
            }

            // Load dữ liệu cho cmbPhongBanHanh
            ArrayList<String> phongBanList = controller.getPhongBan();
            cmbPhongBanHanh.removeAllItems();
            cmbPhongBanNhan.removeAllItems();
            for (String phongBan : phongBanList) {
                cmbPhongBanHanh.addItem(phongBan);
                cmbPhongBanNhan.addItem(phongBan); // Phòng nhận dùng cùng dữ liệu
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu lên Combobox: " + e.getMessage());
        }
    }
    private void btnDocFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocFileActionPerformed
        int row = tbDinhKem.getSelectedRow();
        if (row != -1) {
            // Lấy đường dẫn tệp từ cột thứ 6 (index 5)
            String duongDanFile = (String) tbDinhKem.getValueAt(row, 5);

            if (duongDanFile != null && !duongDanFile.isEmpty()) {
                // Đảm bảo thay thế dấu "\" bằng "/"
                String filePath = duongDanFile.replace("\\", "/");

                File fileTaiXuong = new File(filePath);
                if (fileTaiXuong.exists()) {
                    try {
                        // Mở tài liệu bằng Desktop API
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(fileTaiXuong); // Mở tệp
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(this, "Không thể mở tệp: " + e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Tệp không tồn tại.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy đường dẫn tài liệu.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài liệu.");
        }
    }//GEN-LAST:event_btnDocFileActionPerformed

    private void pnChuaNoiDungAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_pnChuaNoiDungAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_pnChuaNoiDungAncestorAdded

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbPhongBanHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPhongBanHanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPhongBanHanhActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChiTietVanBanNoiBo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietVanBanNoiBo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietVanBanNoiBo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietVanBanNoiBo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String sokyhieu = "KYHIEU01";
                String tenvanban = "Văn bản A";
                String ngayBanHanhStr = "2024-11-27";
                String loaibanhanh = "Loại A";
                String phongbanhanh = "Phòng X";
                String phongbannhan = "Phòng Y";
                String nguoinhan = "Người A";
                String nguoiky = "Người Ký A";
                String nguoiduyet = "Người Duyệt A";
                String trichyeu = "Trích yếu văn bản A";
                String noidung = "Nội dung văn bản A";

                // Tạo đối tượng ChiTietVanBanNoiBo với tham số
                new ChiTietVanBanNoiBo(sokyhieu, tenvanban, ngayBanHanhStr, loaibanhanh, phongbanhanh, phongbannhan,
                        nguoinhan, nguoiky, nguoiduyet, trichyeu, noidung).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDocFile;
    private javax.swing.JComboBox<String> cmbLoaiBanHanh;
    private javax.swing.JComboBox<String> cmbPhongBanHanh;
    private javax.swing.JComboBox<String> cmbPhongBanNhan;
    private com.toedter.calendar.JDateChooser dateNgayBanHanh;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane pnChuaNoiDung;
    private javax.swing.JPanel pnVanBanNoiBo;
    private javax.swing.JTable tbDinhKem;
    private javax.swing.JTextField txtNguoiDuyet;
    private javax.swing.JTextField txtNguoiKy;
    private javax.swing.JTextField txtNguoiNhan;
    private javax.swing.JTextArea txtNoiDung;
    private javax.swing.JTextField txtSoKyHieu;
    private javax.swing.JTextField txtTenVanBan;
    private javax.swing.JTextArea txtTrichYeu;
    // End of variables declaration//GEN-END:variables
}
