package QuanLyCongVan.Views;

import QuanLyCongVan.Controllers.QuanLyQuyenController;
import QuanLyCongVan.Model.QuyenModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class FormQuanLyQuyen extends javax.swing.JFrame {
    private final DefaultTableModel tableModel = new DefaultTableModel();

    public FormQuanLyQuyen() {
        initComponents();
        initTable();
        fillData();
    }

    public void initTable() {
        // Mảng chứa các cột tiêu đề
        String[] colsName = new String[]{"Tên quyền", "Mô tả"};

        // Thiết lập tên cột cho bảng
        tableModel.setColumnIdentifiers(colsName);

        // Gán DefaultTableModel cho JTable
        tblQuyen.setModel(tableModel);

        // Ngừng chỉnh sửa các ô trong bảng (chỉ đọc)
        tblQuyen.getTableHeader().setReorderingAllowed(false); // Không cho phép thay đổi vị trí cột
        tblQuyen.setEnabled(false); // Tắt toàn bộ bảng, không cho phép chọn hay tương tác
    }

    public void fillData() {
        try {
            ArrayList<QuyenModel> lst = new QuanLyQuyenController().HienThiQuyen();
            tableModel.setRowCount(0);
            for (QuyenModel quyen : lst) {
                String row[] = {
                        quyen.getTenQuyen(),
                        quyen.getMoTa()
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQuyen = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenQuyen = new javax.swing.JTextField();
        txtMoTa = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ QUYỀN");

        btnThem.setText("Thêm");
        btnThem.addActionListener(evt -> btnThemActionPerformed(evt));

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(evt -> btnCapNhatActionPerformed(evt));

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(evt -> btnXoaActionPerformed(evt));

        tblQuyen.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                        "Tên quyền", "Mô tả"
                }
        ));
        tblQuyen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuyenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQuyen);

        jLabel2.setText("Tên quyền:");

        jLabel3.setText("Mô tả:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTenQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnThem)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCapNhat)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnXoa)))
                                .addContainerGap())
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtTenQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnThem)
                                .addComponent(btnCapNhat)
                                .addComponent(btnXoa))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 12, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-BEGIN:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String tenQuyen = txtTenQuyen.getText();
        String moTa = txtMoTa.getText();

        // Kiểm tra tính hợp lệ
        if (tenQuyen.isEmpty() || moTa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
            return;
        }

        try {
            QuanLyQuyenController controller = new QuanLyQuyenController();
            if (controller.ThemQuyen(tenQuyen, moTa)) {
                JOptionPane.showMessageDialog(this, "Thêm quyền thành công!");
                fillData(); // Cập nhật lại bảng sau khi thêm
            } else {
                JOptionPane.showMessageDialog(this, "Thêm quyền thất bại!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        String tenQuyen = txtTenQuyen.getText();
        String moTa = txtMoTa.getText();

        // Kiểm tra tính hợp lệ
        if (tenQuyen.isEmpty() || moTa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn quyền để cập nhật!");
            return;
        }

        try {
            QuanLyQuyenController controller = new QuanLyQuyenController();
            if (controller.SuaQuyen(tenQuyen, moTa)) {
                JOptionPane.showMessageDialog(this, "Cập nhật quyền thành công!");
                fillData(); // Cập nhật lại bảng sau khi sửa
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật quyền thất bại!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        String tenQuyen = txtTenQuyen.getText();

        if (tenQuyen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn quyền để xóa!");
            return;
        }

        try {
            QuanLyQuyenController controller = new QuanLyQuyenController();
            if (controller.XoaQuyen(tenQuyen)) {
                JOptionPane.showMessageDialog(this, "Xóa quyền thành công!");
                fillData(); // Cập nhật lại bảng sau khi xóa
            } else {
                JOptionPane.showMessageDialog(this, "Xóa quyền thất bại!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblQuyenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuyenMouseClicked
        int selectedRow = tblQuyen.getSelectedRow();
        if (selectedRow != -1) {
            String tenQuyen = tblQuyen.getValueAt(selectedRow, 0).toString();
            String moTa = tblQuyen.getValueAt(selectedRow, 1).toString();
            txtTenQuyen.setText(tenQuyen);
            txtMoTa.setText(moTa);
        }
    }//GEN-LAST:event_tblQuyenMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FormQuanLyQuyen().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblQuyen;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtTenQuyen;
    // End of variables declaration//GEN-END:variables
}
