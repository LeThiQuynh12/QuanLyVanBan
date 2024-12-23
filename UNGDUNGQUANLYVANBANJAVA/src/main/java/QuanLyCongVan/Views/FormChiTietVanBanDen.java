/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QuanLyCongVan.Views;

import QuanLyCongVan.Controllers.VanBanDenController;
import QuanLyCongVan.Views.FormTrangChu;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class FormChiTietVanBanDen extends javax.swing.JFrame {

    
    public FormChiTietVanBanDen() {
        initComponents();
        KhoiTao();
    }
    private void KhoiTao()
    { 
        VanBanDenController vbController=new VanBanDenController();
        ArrayList<String> danhsachso=vbController.HienThiSoVanBan();
            for(String x:danhsachso)
            {
                cboSoVanBanDen.addItem(x);
            }
            Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

            ArrayList<String> danhsachloai=vbController.hienThiLoaiVanBan();
            for(String s:danhsachloai)
            {
                cboLoaiVanBanDen.addItem(s);
            }
            ArrayList<String> danhsachnoi=vbController.HienThiNoiBanHanh();
            for(String x:danhsachnoi)
            {
                cboNoiBanHanhDen.addItem(x);
            }
        btnThemVanBanDen.setEnabled(false);
        btnLuuVanBanDen.setEnabled(false);
        btnXoaVanBanDen.setEnabled(false);
        btnTaiLaiVanBanDen.setEnabled(false);

        System.out.println(FormTrangChu.vanBanDenChiTiet.toString());
        cboSoVanBanDen.setSelectedItem(FormTrangChu.vanBanDenChiTiet.getTenSo() + "-" + String.valueOf(FormTrangChu.vanBanDenChiTiet.getNam()));
        System.out.println(FormTrangChu.vanBanDenChiTiet.getTenSo() + "-" + String.valueOf(FormTrangChu.vanBanDenChiTiet.getNam()));
        
        txtSoKyHieuDen.setText(FormTrangChu.vanBanDenChiTiet.getSoKyHieu());
        txtSoDen.setText(String.valueOf(FormTrangChu.vanBanDenChiTiet.getSoDen()));
        dateNgayBanHanhDen.setDate(FormTrangChu.vanBanDenChiTiet.getNgayBanHanh());
        cboLoaiVanBanDen.setSelectedItem(FormTrangChu.vanBanDenChiTiet.getLoaiVanBan());
        System.out.println(FormTrangChu.vanBanDenChiTiet.getLoaiVanBan());
        cboNoiBanHanhDen.setSelectedItem(FormTrangChu.vanBanDenChiTiet.getNoiBanHanh());
        System.out.println(FormTrangChu.vanBanDenChiTiet.getNoiBanHanh());
        dateNgayDen.setDate(FormTrangChu.vanBanDenChiTiet.getNgayDen());
        
        txtSoTrangDen.setText(String.valueOf(FormTrangChu.vanBanDenChiTiet.getSoTrang()));
        txtNguoiNhanDen.setText(FormTrangChu.vanBanDenChiTiet.getNguoiNhan());
        txtNguoiKyDen.setText(FormTrangChu.vanBanDenChiTiet.getNguoiKy());
        txtNguoiDuyetDen.setText(FormTrangChu.vanBanDenChiTiet.getNguoiDuyet());
        txtTrichYeuDen.setText(FormTrangChu.vanBanDenChiTiet.getTrichYeu());
        txtNoiDungDen.setText(FormTrangChu.vanBanDenChiTiet.getNoiDung()); 
        txtDinhKemFileDen.setText(FormTrangChu.vanBanDenChiTiet.getDuongDanFile());
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelVanBanDen3 = new javax.swing.JPanel();
        jPanel113 = new javax.swing.JPanel();
        btnThemVanBanDen = new javax.swing.JButton();
        btnXoaVanBanDen = new javax.swing.JButton();
        btnLuuVanBanDen = new javax.swing.JButton();
        btnTaiLaiVanBanDen = new javax.swing.JButton();
        jPanel114 = new javax.swing.JPanel();
        jLabel190 = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        jLabel192 = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
        jLabel196 = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        jLabel198 = new javax.swing.JLabel();
        jLabel199 = new javax.swing.JLabel();
        jLabel200 = new javax.swing.JLabel();
        txtSoKyHieuDen = new javax.swing.JTextField();
        cboSoVanBanDen = new javax.swing.JComboBox<>();
        cboNoiBanHanhDen = new javax.swing.JComboBox<>();
        txtSoDen = new javax.swing.JTextField();
        txtSoTrangDen = new javax.swing.JTextField();
        txtNguoiNhanDen = new javax.swing.JTextField();
        txtNguoiDuyetDen = new javax.swing.JTextField();
        txtNguoiKyDen = new javax.swing.JTextField();
        cboLoaiVanBanDen = new javax.swing.JComboBox<>();
        dateNgayBanHanhDen = new com.toedter.calendar.JDateChooser();
        dateNgayDen = new com.toedter.calendar.JDateChooser();
        jPanel116 = new javax.swing.JPanel();
        txtTrichYeuDen = new javax.swing.JTextField();
        jPanel117 = new javax.swing.JPanel();
        txtNoiDungDen = new javax.swing.JTextField();
        jPanel119 = new javax.swing.JPanel();
        btnDinhKemFile4 = new javax.swing.JButton();
        txtDinhKemFileDen = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        btnThemVanBanDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/insert.png"))); // NOI18N
        btnThemVanBanDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVanBanDenActionPerformed(evt);
            }
        });

        btnXoaVanBanDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/button.png"))); // NOI18N
        btnXoaVanBanDen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaVanBanDenMouseClicked(evt);
            }
        });
        btnXoaVanBanDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaVanBanDenActionPerformed(evt);
            }
        });

        btnLuuVanBanDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/save.png"))); // NOI18N
        btnLuuVanBanDen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuVanBanDenMouseClicked(evt);
            }
        });
        btnLuuVanBanDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuVanBanDenActionPerformed(evt);
            }
        });

        btnTaiLaiVanBanDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/reset.png"))); // NOI18N
        btnTaiLaiVanBanDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiLaiVanBanDenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel113Layout = new javax.swing.GroupLayout(jPanel113);
        jPanel113.setLayout(jPanel113Layout);
        jPanel113Layout.setHorizontalGroup(
            jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel113Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnThemVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnLuuVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXoaVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTaiLaiVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(938, Short.MAX_VALUE))
        );
        jPanel113Layout.setVerticalGroup(
            jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel113Layout.createSequentialGroup()
                .addGroup(jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiLaiVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel114.setBackground(new java.awt.Color(255, 255, 255));
        jPanel114.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Văn Bản Đến", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        jLabel190.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel190.setForeground(new java.awt.Color(51, 0, 153));
        jLabel190.setText("Sổ văn bản");

        jLabel191.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel191.setForeground(new java.awt.Color(51, 0, 153));
        jLabel191.setText("Ngày ban hành");

        jLabel192.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel192.setForeground(new java.awt.Color(51, 0, 153));
        jLabel192.setText("Số ký hiệu");

        jLabel193.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel193.setForeground(new java.awt.Color(51, 0, 153));
        jLabel193.setText("Nơi ban hành");

        jLabel194.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel194.setForeground(new java.awt.Color(51, 0, 153));
        jLabel194.setText("Loại văn bản");

        jLabel195.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel195.setForeground(new java.awt.Color(51, 0, 153));
        jLabel195.setText("Ngày đến");

        jLabel196.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel196.setForeground(new java.awt.Color(51, 0, 153));
        jLabel196.setText("Số đến");

        jLabel197.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel197.setForeground(new java.awt.Color(51, 0, 153));
        jLabel197.setText("Số trang");

        jLabel198.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel198.setForeground(new java.awt.Color(51, 0, 153));
        jLabel198.setText("Người nhận");

        jLabel199.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel199.setForeground(new java.awt.Color(51, 0, 153));
        jLabel199.setText("Người ký");

        jLabel200.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel200.setForeground(new java.awt.Color(51, 0, 153));
        jLabel200.setText("Người duyệt");

        dateNgayBanHanhDen.setDateFormatString("dd/MM/yyyy");

        dateNgayDen.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel114Layout = new javax.swing.GroupLayout(jPanel114);
        jPanel114.setLayout(jPanel114Layout);
        jPanel114Layout.setHorizontalGroup(
            jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel114Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel114Layout.createSequentialGroup()
                        .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel192, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel190, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel191))
                        .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel114Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(cboSoVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel114Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboNoiBanHanhDen, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateNgayBanHanhDen, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoKyHieuDen, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel193, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel196, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel194, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel195, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel197, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel114Layout.createSequentialGroup()
                        .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateNgayDen, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtSoDen, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboLoaiVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(114, 114, 114)
                        .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel199, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel198)
                            .addComponent(jLabel200, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNguoiNhanDen, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(txtNguoiKyDen)
                            .addComponent(txtNguoiDuyetDen)))
                    .addComponent(txtSoTrangDen, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );
        jPanel114Layout.setVerticalGroup(
            jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel114Layout.createSequentialGroup()
                .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel114Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel114Layout.createSequentialGroup()
                                .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel114Layout.createSequentialGroup()
                                        .addComponent(txtNguoiNhanDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(54, 54, 54))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel114Layout.createSequentialGroup()
                                        .addComponent(jLabel198)
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel199)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNguoiDuyetDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel200)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel114Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtNguoiKyDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))))
                    .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel114Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboSoVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel194)
                                .addComponent(jLabel190))
                            .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel114Layout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel192)
                                        .addComponent(txtSoKyHieuDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                    .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel191)
                                        .addComponent(dateNgayBanHanhDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(11, 11, 11)
                                    .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel193)
                                        .addComponent(cboNoiBanHanhDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel114Layout.createSequentialGroup()
                                    .addGap(45, 45, 45)
                                    .addComponent(jLabel195)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(jPanel114Layout.createSequentialGroup()
                            .addComponent(cboLoaiVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSoDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel196))
                            .addGap(11, 11, 11)
                            .addComponent(dateNgayDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSoTrangDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel197)))))
                .addGap(17, 17, 17))
        );

        jPanel116.setBackground(new java.awt.Color(255, 255, 255));
        jPanel116.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Trích Yếu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        javax.swing.GroupLayout jPanel116Layout = new javax.swing.GroupLayout(jPanel116);
        jPanel116.setLayout(jPanel116Layout);
        jPanel116Layout.setHorizontalGroup(
            jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTrichYeuDen)
        );
        jPanel116Layout.setVerticalGroup(
            jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTrichYeuDen, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );

        jPanel117.setBackground(new java.awt.Color(255, 255, 255));
        jPanel117.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Nội Dung", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        javax.swing.GroupLayout jPanel117Layout = new javax.swing.GroupLayout(jPanel117);
        jPanel117.setLayout(jPanel117Layout);
        jPanel117Layout.setHorizontalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel117Layout.createSequentialGroup()
                .addComponent(txtNoiDungDen)
                .addContainerGap())
        );
        jPanel117Layout.setVerticalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtNoiDungDen, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );

        jPanel119.setBackground(new java.awt.Color(255, 255, 255));
        jPanel119.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Đính Kèm File", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        btnDinhKemFile4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDinhKemFile4.setText("Đính kèm file");
        btnDinhKemFile4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDinhKemFile4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel119Layout = new javax.swing.GroupLayout(jPanel119);
        jPanel119.setLayout(jPanel119Layout);
        jPanel119Layout.setHorizontalGroup(
            jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel119Layout.createSequentialGroup()
                .addComponent(txtDinhKemFileDen, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDinhKemFile4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel119Layout.setVerticalGroup(
            jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel119Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(btnDinhKemFile4)
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(jPanel119Layout.createSequentialGroup()
                .addComponent(txtDinhKemFileDen)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelVanBanDen3Layout = new javax.swing.GroupLayout(panelVanBanDen3);
        panelVanBanDen3.setLayout(panelVanBanDen3Layout);
        panelVanBanDen3Layout.setHorizontalGroup(
            panelVanBanDen3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVanBanDen3Layout.createSequentialGroup()
                .addComponent(jPanel113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel114, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel116, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel117, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel119, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelVanBanDen3Layout.setVerticalGroup(
            panelVanBanDen3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVanBanDen3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelVanBanDen3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel116, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel117, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelVanBanDen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelVanBanDen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemVanBanDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVanBanDenActionPerformed
       
    }//GEN-LAST:event_btnThemVanBanDenActionPerformed

    private void btnXoaVanBanDenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaVanBanDenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaVanBanDenMouseClicked

    private void btnXoaVanBanDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaVanBanDenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaVanBanDenActionPerformed

    private void btnLuuVanBanDenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuVanBanDenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuVanBanDenMouseClicked

    private void btnLuuVanBanDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuVanBanDenActionPerformed
        
    }//GEN-LAST:event_btnLuuVanBanDenActionPerformed

    private void btnTaiLaiVanBanDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiLaiVanBanDenActionPerformed
       
    }//GEN-LAST:event_btnTaiLaiVanBanDenActionPerformed

    private void btnDinhKemFile4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDinhKemFile4ActionPerformed
        
    }//GEN-LAST:event_btnDinhKemFile4ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.hide();
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FormChiTietVanBanDen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormChiTietVanBanDen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormChiTietVanBanDen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormChiTietVanBanDen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormChiTietVanBanDen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDinhKemFile4;
    private javax.swing.JButton btnLuuVanBanDen;
    private javax.swing.JButton btnTaiLaiVanBanDen;
    private javax.swing.JButton btnThemVanBanDen;
    private javax.swing.JButton btnXoaVanBanDen;
    private javax.swing.JComboBox<String> cboLoaiVanBanDen;
    private javax.swing.JComboBox<String> cboNoiBanHanhDen;
    private javax.swing.JComboBox<String> cboSoVanBanDen;
    private com.toedter.calendar.JDateChooser dateNgayBanHanhDen;
    private com.toedter.calendar.JDateChooser dateNgayDen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JPanel jPanel113;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel116;
    private javax.swing.JPanel jPanel117;
    private javax.swing.JPanel jPanel119;
    private javax.swing.JPanel panelVanBanDen3;
    private javax.swing.JTextField txtDinhKemFileDen;
    private javax.swing.JTextField txtNguoiDuyetDen;
    private javax.swing.JTextField txtNguoiKyDen;
    private javax.swing.JTextField txtNguoiNhanDen;
    private javax.swing.JTextField txtNoiDungDen;
    private javax.swing.JTextField txtSoDen;
    private javax.swing.JTextField txtSoKyHieuDen;
    private javax.swing.JTextField txtSoTrangDen;
    private javax.swing.JTextField txtTrichYeuDen;
    // End of variables declaration//GEN-END:variables
}
