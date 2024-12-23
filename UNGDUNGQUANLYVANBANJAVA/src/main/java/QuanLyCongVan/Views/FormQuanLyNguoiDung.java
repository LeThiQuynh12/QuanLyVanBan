/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QuanLyCongVan.Views;

import QuanLyCongVan.Controllers.MaHoa;
import QuanLyCongVan.Controllers.QuanLyNguoiDungController;
import QuanLyCongVan.Model.QuanLyNguoiDungModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FormQuanLyNguoiDung extends javax.swing.JFrame {
    private final DefaultTableModel  tableModel = new DefaultTableModel(); 
    public FormQuanLyNguoiDung() {
        initComponents();
        initTable();
        fillData();
    }
public void initTable() {
    // Mảng chứa các cột tiêu đề
    String[] colsName = new String[]{"Họ và tên", "Email", "Số điện thoại", "Tên tài khoản", "Mật khẩu"};
    
    // Thiết lập tên cột cho bảng
    tableModel.setColumnIdentifiers(colsName);
    
    // Gán DefaultTableModel cho JTable
    tblNguoiDung.setModel(tableModel);
    
    // Ngừng chỉnh sửa các ô trong bảng (chỉ đọc)
    //tblNguoiDung.setDefaultEditor(Object.class, null); // Ngừng chỉnh sửa mọi ô
    
    // Đảm bảo bảng không cho phép chỉnh sửa từng ô (bảng hoàn toàn chỉ đọc)
    tblNguoiDung.getTableHeader().setReorderingAllowed(false); // Không cho phép thay đổi vị trí cột
    tblNguoiDung.setEnabled(false); // Tắt toàn bộ bảng, không cho phép chọn hay tương tác
}

    public void fillData(){
        try{
            ArrayList<QuanLyNguoiDungModel> lst = new QuanLyNguoiDungController().HienThiNguoiDung();
            tableModel.setRowCount(0);
            for(int i=0 ; i<lst.size() ; i++){
                String row[] = {
                // i=1 // lặp qua tất cả phần tử trong ds và truy xuất bởi người dùng
                lst.get(i).getHoVaTen(),
                lst.get(i).getEmail(), 
                lst.get(i).getSoDienThoai(), 
                lst.get(i).getTenTaiKhoan(), 
                lst.get(i).getMatKhau()
                };
                 tableModel.addRow(row);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra : " + ex.getMessage());
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
        tblNguoiDung = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtTenTaiKhoan = new javax.swing.JTextField();
        pwMatKhau = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ NGƯỜI DÙNG");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        tblNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Họ và tên", "Email", "Số điện thoại", "Tên tài khoản", "Mật khẩu"
            }
        ));
        tblNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguoiDungMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNguoiDung);

        jLabel2.setText("Họ và tên:");

        jLabel3.setText("Email:");

        jLabel4.setText("Số điện thoại:");

        jLabel5.setText("Tên tài khoản:");

        jLabel6.setText("Mật khẩu: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(355, 355, 355)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSDT))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThem)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pwMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(btnCapNhat)
                                .addGap(34, 34, 34)
                                .addComponent(btnXoa)))
                        .addGap(0, 174, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(pwMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat)
                    .addComponent(btnXoa)
                    .addComponent(btnThem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void tblNguoiDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoiDungMouseClicked
        // TODO add your handling code here:
        // Xử ký bảng khi Click vào sẽ hiển thị bảng lên các trường dữ liệu là Sự kiện Mouse Click -- lấy chỉ số dòng đang chọn
        try{
            //Lấy chỉ số dòng đang chọn
            int row = this.tblNguoiDung.getSelectedRow();
            String hoTen = (this.tblNguoiDung.getValueAt(row, 0)).toString();
            String email = (this.tblNguoiDung.getValueAt(row, 1)).toString();
            String sdt = (this.tblNguoiDung.getValueAt(row, 2)).toString();
            String tenTaiKhoan = (this.tblNguoiDung.getValueAt(row, 3)).toString();
            String matKhau = (this.tblNguoiDung.getValueAt(row, 4)).toString();

            this.txtHoTen.setText(hoTen);
            this.txtEmail.setText(email);
            this.txtSDT.setText(sdt);
            this.txtTenTaiKhoan.setText(tenTaiKhoan);
            this.pwMatKhau.setText(matKhau);
        }
        catch(Exception ex){
        }
        // Sau khi hoàn thành cập nhật, trở lại chế độ chỉ đọc
        tblNguoiDung.setEnabled(false);  // Tắt chế độ chọn bảng
    }//GEN-LAST:event_tblNguoiDungMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        // Cho phép người dùng chọn dòng trong bảng (chế độ chỉ đọc vẫn bật)
        tblNguoiDung.setEnabled(true);  // Bật chế độ chọn dòng

        // Kiểm tra xem người dùng đã chọn dòng hay chưa
        int selectedRow = tblNguoiDung.getSelectedRow();
        if (selectedRow == -1) { // Không có dòng nào được chọn
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong bảng để xóa!");
            return;
        }

        // Lấy thông tin tên tài khoản từ dòng được chọn
        String TenTaiKhoan = tblNguoiDung.getValueAt(selectedRow, 3).toString(); // Cột thứ 3 là tên tài khoản
        if (TenTaiKhoan == null || TenTaiKhoan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ, vui lòng chọn lại dòng.");
            return;
        }

        // Hỏi xác nhận trước khi xóa
        int confirm = JOptionPane.showConfirmDialog(this,
            "Bạn có chắc chắn muốn xóa người dùng với tên tài khoản: " + TenTaiKhoan + "?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION);

        // Nếu chọn "Yes" thì tiến hành xóa
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                QuanLyNguoiDungController ctrQLND = new QuanLyNguoiDungController();
                if (ctrQLND.XoaNguoiDung(TenTaiKhoan)) {
                    JOptionPane.showMessageDialog(this, "Không thể xóa người dùng!");

                } else {
                    JOptionPane.showMessageDialog(this, "Xóa người dùng thành công! ");
                    fillData(); // Cập nhật lại bảng sau khi xóa
                    txtTenTaiKhoan.setText("");
                    pwMatKhau.setText("");
                    txtHoTen.setText("");
                    txtSDT.setText("");
                    txtEmail.setText("");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "");
            }
        }

        // Sau khi xóa, vô hiệu hóa bảng trở về chế độ chỉ đọc
        tblNguoiDung.setEnabled(false);
        tblNguoiDung.clearSelection(); // Xóa lựa chọn trong bảng
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed

        // Cho phép chọn dữ liệu trong bảng
        tblNguoiDung.setEnabled(true);  // Cho phép chọn dòng trong bảng

        // Ngừng chỉnh sửa trực tiếp từ giao diện bảng (chỉ có thể sửa thông qua các trường nhập liệu)
        tblNguoiDung.setCellSelectionEnabled(false); // Ngừng cho phép chỉnh sửa các ô trong bảng
        String hoVaTen = txtHoTen.getText();
        String email = txtEmail.getText();
        String soDienThoai = txtSDT.getText();
        String tenTaiKhoan = txtTenTaiKhoan.getText();
        String matKhau = new String(pwMatKhau.getPassword());

        //Kiểm tra tính hợp lệ
        //Kiểm tra khi nhập vào rỗng
        if (hoVaTen.isEmpty() || email.isEmpty() || soDienThoai.isEmpty() || tenTaiKhoan.isEmpty() || matKhau.isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn người dùng!");
            return;
        }
        // Kiểm tra tên chỉ chứa chữ cái và ít nhất 2 ký tự
        String nameRegex = "^[\\p{L} .'-]{2,}$";
        if(!hoVaTen.matches(nameRegex)){
            JOptionPane.showMessageDialog(this, "Họ và tên chỉ chứa chữ cái và ít nhất 2 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Kiểm tra Email phải có @gmail.com
        String emailRegex = "^[A-Za-z0-9+_.-]+@gmail\\.com$";
        if (!email.matches(emailRegex)){
            JOptionPane.showMessageDialog(this, "Email phải có định dạng @gmail.com!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Kiểm tra số điện thoại có 10 chữ số và bắt đầu bằng 0
        if (soDienThoai.length() != 10 || !soDienThoai.startsWith("0") || !soDienThoai.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số và bắt đầu bằng 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Kiểm tra độ dài tên tài khoản > 6
        if (tenTaiKhoan.length() < 6) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (matKhau.length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        QuanLyNguoiDungController ctrQLND = new QuanLyNguoiDungController();
        try {
            if(ctrQLND.SuaNguoiDung(hoVaTen, email, soDienThoai, tenTaiKhoan, matKhau)){
                JOptionPane.showMessageDialog(this, "Cập nhật người dùng thành công!");
                tableModel.setRowCount(0); // Xóa dữ liệu cũ
                fillData();
            }
            else{
                JOptionPane.showMessageDialog(this, "Cập nhật người dùng thất bại!");
            }
        } catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        String hoVaTen = txtHoTen.getText();
        String email = txtEmail.getText();
        String soDienThoai = txtSDT.getText();
        String tenTaiKhoan = txtTenTaiKhoan.getText();
        String matKhau = new String(pwMatKhau.getPassword());

        //Kiểm tra tính hợp lệ
        //Kiểm tra khi nhập vào rỗng
        if (hoVaTen.isEmpty() || email.isEmpty() || soDienThoai.isEmpty() || tenTaiKhoan.isEmpty() || matKhau.isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
            return;
        }
        // Kiểm tra tên chỉ chứa chữ cái và ít nhất 2 ký tự
        String nameRegex = "^[\\p{L} .'-]{2,}$";
        if(!hoVaTen.matches(nameRegex)){
            JOptionPane.showMessageDialog(this, "Họ và tên chỉ chứa chữ cái và ít nhất 2 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Kiểm tra Email phải có @gmail.com
        String emailRegex = "^[A-Za-z0-9+_.-]+@gmail\\.com$";
        if (!email.matches(emailRegex)){
            JOptionPane.showMessageDialog(this, "Email phải có định dạng @gmail.com!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Kiểm tra số điện thoại có 10 chữ số và bắt đầu bằng 0
        if (soDienThoai.length() != 10 || !soDienThoai.startsWith("0") || !soDienThoai.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số và bắt đầu bằng 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Kiểm tra độ dài tên tài khoản > 6
        if (tenTaiKhoan.length() < 6) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (matKhau.length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        QuanLyNguoiDungController ctrQLND = new QuanLyNguoiDungController();
        //Kiểm tra email đã tồn tại ?
        if (ctrQLND.kiemTraEmail(email)){
            JOptionPane.showMessageDialog(this, "Email đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Kiểm tra số điện thoại đã tồn tại ?
        if (ctrQLND.kiemTraSDT(soDienThoai)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Kiểm tra tên tài khoản đã tồn tại ?
        if (ctrQLND.kiemTraTenTaiKhoan(tenTaiKhoan)){
            JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Kiểm tra thêm người dùng
            if (ctrQLND.ThemNguoiDung(hoVaTen, email, soDienThoai, tenTaiKhoan, matKhau)) {
                JOptionPane.showMessageDialog(this, "Thêm mới người dùng thành công");
                // Xóa dữ liệu cũ để hiển thị mới
                tableModel.setRowCount(0);
                // Hiển thị vào bảng
                fillData();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm mới người dùng thất bại");
            }
        } catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnThemActionPerformed

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
            java.util.logging.Logger.getLogger(FormQuanLyNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyNguoiDung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQuanLyNguoiDung().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField pwMatKhau;
    private javax.swing.JTable tblNguoiDung;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
