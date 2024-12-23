/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QuanLyCongVan.Views;

import QuanLyCongVan.Controllers.ThongTinKyDuyetController;
import QuanLyCongVan.Model.ThongTinKyDuyetModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Windows
 */
public class FormThongTinNguoiKyDuyet extends javax.swing.JFrame {

    /**
     * Creates new form FormThongTinNguoiKyDuyet
     */
    public FormThongTinNguoiKyDuyet() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNguoiKy = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNguoiDuyet = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNguoiGui = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNguoiNhan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        txtNoiBanHanh = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("THIẾT LẬP THÔNG TIN ");

        jLabel2.setText("Người ký: ");

        jLabel3.setText("Người duyệt: ");

        jLabel4.setText("Người gửi: ");

        jLabel5.setText("Người nhận: ");

        jLabel6.setText("Nơi ban hành: ");

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(167, 167, 167)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2)
                            .addGap(30, 30, 30)
                            .addComponent(txtNguoiKy, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNguoiDuyet)
                                .addComponent(txtNguoiGui)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(txtNguoiNhan))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNoiBanHanh)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(btnLuu)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNguoiKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNguoiDuyet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNguoiGui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNoiBanHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(btnLuu)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    String nguoiKy = txtNguoiKy.getText();
    String nguoiDuyet = txtNguoiDuyet.getText();
    String nguoiGui = txtNguoiGui.getText();
    String nguoiNhan = txtNguoiNhan.getText();
    String noiBanHanh = txtNoiBanHanh.getText();
    
    // Kiểm tra xem có trường nào bị bỏ trống không
    if (nguoiKy.isEmpty() || nguoiDuyet.isEmpty() || nguoiGui.isEmpty() || nguoiNhan.isEmpty() || noiBanHanh.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
        return;
    }
    
    // Kiểm tra từng trường có chứa ký tự hợp lệ không (chỉ chữ cái)
    String nameRegex = "^[\\p{L} .'-]+$";  // Chỉ cho phép chữ cái, dấu cách, dấu chấm, dấu gạch nối, dấu nháy đơn
    if (!nguoiKy.matches(nameRegex)) {
        JOptionPane.showMessageDialog(this, "Người ký chỉ chứa chữ cái và không có số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!nguoiDuyet.matches(nameRegex)) {
        JOptionPane.showMessageDialog(this, "Người duyệt chỉ chứa chữ cái và không có số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!nguoiGui.matches(nameRegex)) {
        JOptionPane.showMessageDialog(this, "Người gửi chỉ chứa chữ cái và không có số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!nguoiNhan.matches(nameRegex)) {
        JOptionPane.showMessageDialog(this, "Người nhận chỉ chứa chữ cái và không có số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!noiBanHanh.matches(nameRegex)) {
        JOptionPane.showMessageDialog(this, "Nơi ban hành chỉ chứa chữ cái và không có số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Tiến hành lưu thông tin sau khi kiểm tra hợp lệ
    try {
        ThongTinKyDuyetController ctrTTKD = new ThongTinKyDuyetController();
        ThongTinKyDuyetModel mdlTTKD = new ThongTinKyDuyetModel();
        mdlTTKD.setNguoiKy(nguoiKy);
        mdlTTKD.setNguoiDuyet(nguoiDuyet);
        mdlTTKD.setNguoiGui(nguoiGui);
        mdlTTKD.setNguoiNhan(nguoiNhan);
        mdlTTKD.setNoiBanHanh(noiBanHanh);
        
        if (ctrTTKD.themThongTinKyDuyet(nguoiKy, nguoiDuyet, nguoiGui, nguoiNhan, noiBanHanh)) {
            JOptionPane.showMessageDialog(this, "Lưu thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Lưu thất bại!");
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi trong quá trình đăng ký!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnLuuActionPerformed

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
            java.util.logging.Logger.getLogger(FormThongTinNguoiKyDuyet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormThongTinNguoiKyDuyet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormThongTinNguoiKyDuyet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormThongTinNguoiKyDuyet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormThongTinNguoiKyDuyet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNguoiDuyet;
    private javax.swing.JTextField txtNguoiGui;
    private javax.swing.JTextField txtNguoiKy;
    private javax.swing.JTextField txtNguoiNhan;
    private javax.swing.JTextField txtNoiBanHanh;
    // End of variables declaration//GEN-END:variables
}
