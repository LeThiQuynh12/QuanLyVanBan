/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QuanLyCongVan.Views;

import QuanLyCongVan.Controllers.VanBanDiController;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


public class FormChiTietVanBanDi extends javax.swing.JFrame {

    public FormChiTietVanBanDi() {
        initComponents();
        KhoiTao();
        //FormChiTietVanBanDi form=new FormChiTietVanBanDi();
        //form.setEnabled(false);
    }

    private void KhoiTao()
    { 
        VanBanDiController vbController=new VanBanDiController();
        ArrayList<String> danhsachso=vbController.HienThiSoVanBan();
            for(String x:danhsachso)
            {
                cboSoVanBanDi.addItem(x);
            };
            ArrayList<String> danhsachloai=vbController.hienThiLoaiVanBan();
            for(String s:danhsachloai)
            {
                cboLoaiVanBanDi.addItem(s);
            }
           
        btnThemVanBanDi.setEnabled(false);
        btnLuuVanBanDi.setEnabled(false);
        btnXoaVanBanDi.setEnabled(false);
        btnTaiLaiVanBanDi.setEnabled(false);
        btnXuatExcelVanBanDi.setEnabled(false);
        System.out.println(FormTrangChu.vanBanDiChiTiet.toString());
        cboSoVanBanDi.setSelectedItem(FormTrangChu.vanBanDiChiTiet.getTenSo() + "-" + String.valueOf(FormTrangChu.vanBanDiChiTiet.getNam()));
        System.out.println(FormTrangChu.vanBanDiChiTiet.getTenSo() + "-" + String.valueOf(FormTrangChu.vanBanDiChiTiet.getNam()));
        
        txtSoKiHieuDi.setText(FormTrangChu.vanBanDiChiTiet.getSoKyHieu());
        txtSoDi.setText(String.valueOf(FormTrangChu.vanBanDiChiTiet.getSoDi()));
        dateNgayBanHanhDi.setDate(FormTrangChu.vanBanDiChiTiet.getNgayBanHanh());
        System.out.println(FormTrangChu.vanBanDiChiTiet.getLoaiVanBan());
        cboLoaiVanBanDi.setSelectedItem(FormTrangChu.vanBanDiChiTiet.getLoaiVanBan());
  
        txtSoTrangDi.setText(String.valueOf(FormTrangChu.vanBanDiChiTiet.getSoTrang()));
        txtSoLuongBanDi.setText(String.valueOf(FormTrangChu.vanBanDiChiTiet.getSlBan()));
        txtNguoiGuiDi.setText(FormTrangChu.vanBanDiChiTiet.getNguoiGui());
        txtNguoiKyDi.setText(FormTrangChu.vanBanDiChiTiet.getNguoiKy());
        txtNguoiDuyetDi.setText(FormTrangChu.vanBanDiChiTiet.getNguoiDuyet());
        txtTrichYeuDi.setText(FormTrangChu.vanBanDiChiTiet.getTrichYeu());
        txtNoiDungDi.setText(FormTrangChu.vanBanDiChiTiet.getNoiDung()); 
        txtDinhKemFileDi.setText(FormTrangChu.vanBanDiChiTiet.getDuongDanFile());
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelVanBanDi3 = new javax.swing.JPanel();
        jPanel120 = new javax.swing.JPanel();
        btnThemVanBanDi = new javax.swing.JButton();
        btnTaiLaiVanBanDi = new javax.swing.JButton();
        btnXoaVanBanDi = new javax.swing.JButton();
        btnLuuVanBanDi = new javax.swing.JButton();
        btnXuatExcelVanBanDi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel121 = new javax.swing.JPanel();
        jLabel203 = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        jLabel207 = new javax.swing.JLabel();
        jLabel208 = new javax.swing.JLabel();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jLabel212 = new javax.swing.JLabel();
        jLabel213 = new javax.swing.JLabel();
        txtSoKiHieuDi = new javax.swing.JTextField();
        cboSoVanBanDi = new javax.swing.JComboBox<>();
        cboLoaiVanBanDi = new javax.swing.JComboBox<>();
        txtSoLuongBanDi = new javax.swing.JTextField();
        txtSoTrangDi = new javax.swing.JTextField();
        txtNguoiGuiDi = new javax.swing.JTextField();
        txtNguoiDuyetDi = new javax.swing.JTextField();
        txtNguoiKyDi = new javax.swing.JTextField();
        txtNoiNhanDi = new javax.swing.JTextField();
        txtSoDi = new javax.swing.JTextField();
        dateNgayBanHanhDi = new com.toedter.calendar.JDateChooser();
        jPanel123 = new javax.swing.JPanel();
        txtTrichYeuDi = new javax.swing.JTextField();
        jPanel124 = new javax.swing.JPanel();
        txtNoiDungDi = new javax.swing.JTextField();
        jPanel125 = new javax.swing.JPanel();
        btnDinhKemFileDi3 = new javax.swing.JButton();
        txtDinhKemFileDi = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        btnThemVanBanDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/insert.png"))); // NOI18N
        btnThemVanBanDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVanBanDiActionPerformed(evt);
            }
        });

        btnTaiLaiVanBanDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/reset.png"))); // NOI18N
        btnTaiLaiVanBanDi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaiLaiVanBanDiMouseClicked(evt);
            }
        });
        btnTaiLaiVanBanDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiLaiVanBanDiActionPerformed(evt);
            }
        });

        btnXoaVanBanDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/button.png"))); // NOI18N
        btnXoaVanBanDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaVanBanDiActionPerformed(evt);
            }
        });

        btnLuuVanBanDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/save.png"))); // NOI18N
        btnLuuVanBanDi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuVanBanDiMouseClicked(evt);
            }
        });
        btnLuuVanBanDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuVanBanDiActionPerformed(evt);
            }
        });

        btnXuatExcelVanBanDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/excel.png"))); // NOI18N
        btnXuatExcelVanBanDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelVanBanDiActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel120Layout = new javax.swing.GroupLayout(jPanel120);
        jPanel120.setLayout(jPanel120Layout);
        jPanel120Layout.setHorizontalGroup(
            jPanel120Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel120Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnThemVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnLuuVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXoaVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTaiLaiVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXuatExcelVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 907, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel120Layout.setVerticalGroup(
            jPanel120Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel120Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnThemVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnTaiLaiVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnXoaVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnLuuVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnXuatExcelVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel121.setBackground(new java.awt.Color(255, 255, 255));
        jPanel121.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Văn Bản Đi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        jLabel203.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel203.setForeground(new java.awt.Color(51, 0, 153));
        jLabel203.setText("Sổ văn bản");

        jLabel204.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel204.setForeground(new java.awt.Color(51, 0, 153));
        jLabel204.setText("Ngày ban hành");

        jLabel205.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel205.setForeground(new java.awt.Color(51, 0, 153));
        jLabel205.setText("Số ký hiệu");

        jLabel206.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel206.setForeground(new java.awt.Color(51, 0, 153));
        jLabel206.setText("Nơi nhận");

        jLabel207.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel207.setForeground(new java.awt.Color(51, 0, 153));
        jLabel207.setText("Loại văn bản");

        jLabel208.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel208.setForeground(new java.awt.Color(51, 0, 153));
        jLabel208.setText("Số lượng bản");

        jLabel209.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel209.setForeground(new java.awt.Color(51, 0, 153));
        jLabel209.setText("Số đi");

        jLabel210.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel210.setForeground(new java.awt.Color(51, 0, 153));
        jLabel210.setText("Số trang");

        jLabel211.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel211.setForeground(new java.awt.Color(51, 0, 153));
        jLabel211.setText("Người gửi");

        jLabel212.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel212.setForeground(new java.awt.Color(51, 0, 153));
        jLabel212.setText("Người ký");

        jLabel213.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel213.setForeground(new java.awt.Color(51, 0, 153));
        jLabel213.setText("Người duyệt");

        txtSoKiHieuDi.setPreferredSize(new java.awt.Dimension(80, 22));

        cboSoVanBanDi.setName(""); // NOI18N
        cboSoVanBanDi.setPreferredSize(new java.awt.Dimension(80, 22));

        cboLoaiVanBanDi.setPreferredSize(new java.awt.Dimension(80, 22));

        txtSoLuongBanDi.setPreferredSize(new java.awt.Dimension(80, 22));

        txtSoTrangDi.setPreferredSize(new java.awt.Dimension(80, 22));

        txtNguoiGuiDi.setPreferredSize(new java.awt.Dimension(80, 22));

        txtNguoiDuyetDi.setPreferredSize(new java.awt.Dimension(80, 22));

        txtNguoiKyDi.setPreferredSize(new java.awt.Dimension(80, 22));

        txtNoiNhanDi.setPreferredSize(new java.awt.Dimension(80, 22));

        txtSoDi.setPreferredSize(new java.awt.Dimension(80, 22));

        dateNgayBanHanhDi.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel121Layout = new javax.swing.GroupLayout(jPanel121);
        jPanel121.setLayout(jPanel121Layout);
        jPanel121Layout.setHorizontalGroup(
            jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel121Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel206, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel205, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel203, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel204, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNoiNhanDi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboSoVanBanDi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSoKiHieuDi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateNgayBanHanhDi, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel121Layout.createSequentialGroup()
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel209, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel208, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel210, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoTrangDi, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtSoLuongBanDi, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                .addComponent(txtSoDi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel121Layout.createSequentialGroup()
                        .addComponent(jLabel207, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(cboLoaiVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(103, 103, 103)
                .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel211)
                    .addComponent(jLabel212, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel213, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNguoiGuiDi, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(txtNguoiKyDi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNguoiDuyetDi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42))
        );
        jPanel121Layout.setVerticalGroup(
            jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel121Layout.createSequentialGroup()
                .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel121Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel203))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel121Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cboSoVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel205)
                    .addComponent(txtSoKiHieuDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateNgayBanHanhDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel204, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(17, 17, 17)
                .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel206)
                    .addComponent(txtNoiNhanDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
            .addGroup(jPanel121Layout.createSequentialGroup()
                .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel121Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel211)
                            .addComponent(txtNguoiGuiDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNguoiKyDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel212))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel213)
                            .addComponent(txtNguoiDuyetDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel121Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboLoaiVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel207))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel209))
                        .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel121Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(txtSoTrangDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel121Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSoLuongBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel208))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel210)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel123.setBackground(new java.awt.Color(255, 255, 255));
        jPanel123.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Trích Yếu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        javax.swing.GroupLayout jPanel123Layout = new javax.swing.GroupLayout(jPanel123);
        jPanel123.setLayout(jPanel123Layout);
        jPanel123Layout.setHorizontalGroup(
            jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel123Layout.createSequentialGroup()
                .addComponent(txtTrichYeuDi, javax.swing.GroupLayout.PREFERRED_SIZE, 1176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel123Layout.setVerticalGroup(
            jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTrichYeuDi, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );

        jPanel124.setBackground(new java.awt.Color(255, 255, 255));
        jPanel124.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Nội Dung", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        javax.swing.GroupLayout jPanel124Layout = new javax.swing.GroupLayout(jPanel124);
        jPanel124.setLayout(jPanel124Layout);
        jPanel124Layout.setHorizontalGroup(
            jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtNoiDungDi)
        );
        jPanel124Layout.setVerticalGroup(
            jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtNoiDungDi, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
        );

        jPanel125.setBackground(new java.awt.Color(255, 255, 255));
        jPanel125.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Đính Kèm File", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        btnDinhKemFileDi3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDinhKemFileDi3.setText("Đính kèm file");
        btnDinhKemFileDi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDinhKemFileDi3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel125Layout = new javax.swing.GroupLayout(jPanel125);
        jPanel125.setLayout(jPanel125Layout);
        jPanel125Layout.setHorizontalGroup(
            jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel125Layout.createSequentialGroup()
                .addComponent(txtDinhKemFileDi)
                .addGap(18, 18, 18)
                .addComponent(btnDinhKemFileDi3)
                .addGap(33, 33, 33))
        );
        jPanel125Layout.setVerticalGroup(
            jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel125Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDinhKemFileDi3)
                .addContainerGap(83, Short.MAX_VALUE))
            .addGroup(jPanel125Layout.createSequentialGroup()
                .addComponent(txtDinhKemFileDi)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelVanBanDi3Layout = new javax.swing.GroupLayout(panelVanBanDi3);
        panelVanBanDi3.setLayout(panelVanBanDi3Layout);
        panelVanBanDi3Layout.setHorizontalGroup(
            panelVanBanDi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel120, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(panelVanBanDi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jPanel125, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel124, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel123, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1186, Short.MAX_VALUE)
                .addComponent(jPanel121, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelVanBanDi3Layout.setVerticalGroup(
            panelVanBanDi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVanBanDi3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel120, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel121, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel123, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel124, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1185, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(panelVanBanDi3, javax.swing.GroupLayout.PREFERRED_SIZE, 1185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 716, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(panelVanBanDi3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemVanBanDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVanBanDiActionPerformed
       
    }//GEN-LAST:event_btnThemVanBanDiActionPerformed

    private void btnTaiLaiVanBanDiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiLaiVanBanDiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaiLaiVanBanDiMouseClicked

    private void btnTaiLaiVanBanDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiLaiVanBanDiActionPerformed
     
    }//GEN-LAST:event_btnTaiLaiVanBanDiActionPerformed

    private void btnXoaVanBanDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaVanBanDiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaVanBanDiActionPerformed

    private void btnLuuVanBanDiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuVanBanDiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuVanBanDiMouseClicked

    private void btnLuuVanBanDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuVanBanDiActionPerformed
       
    }//GEN-LAST:event_btnLuuVanBanDiActionPerformed

    private void btnDinhKemFileDi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDinhKemFileDi3ActionPerformed
    
    }//GEN-LAST:event_btnDinhKemFileDi3ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.hide();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnXuatExcelVanBanDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelVanBanDiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatExcelVanBanDiActionPerformed

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
            java.util.logging.Logger.getLogger(FormChiTietVanBanDi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormChiTietVanBanDi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormChiTietVanBanDi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormChiTietVanBanDi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormChiTietVanBanDi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDinhKemFileDi3;
    private javax.swing.JButton btnLuuVanBanDi;
    private javax.swing.JButton btnTaiLaiVanBanDi;
    private javax.swing.JButton btnThemVanBanDi;
    private javax.swing.JButton btnXoaVanBanDi;
    private javax.swing.JButton btnXuatExcelVanBanDi;
    private javax.swing.JComboBox<String> cboLoaiVanBanDi;
    private javax.swing.JComboBox<String> cboSoVanBanDi;
    private com.toedter.calendar.JDateChooser dateNgayBanHanhDi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JPanel jPanel120;
    private javax.swing.JPanel jPanel121;
    private javax.swing.JPanel jPanel123;
    private javax.swing.JPanel jPanel124;
    private javax.swing.JPanel jPanel125;
    private javax.swing.JPanel panelVanBanDi3;
    private javax.swing.JTextField txtDinhKemFileDi;
    private javax.swing.JTextField txtNguoiDuyetDi;
    private javax.swing.JTextField txtNguoiGuiDi;
    private javax.swing.JTextField txtNguoiKyDi;
    private javax.swing.JTextField txtNoiDungDi;
    private javax.swing.JTextField txtNoiNhanDi;
    private javax.swing.JTextField txtSoDi;
    private javax.swing.JTextField txtSoKiHieuDi;
    private javax.swing.JTextField txtSoLuongBanDi;
    private javax.swing.JTextField txtSoTrangDi;
    private javax.swing.JTextField txtTrichYeuDi;
    // End of variables declaration//GEN-END:variables
}
