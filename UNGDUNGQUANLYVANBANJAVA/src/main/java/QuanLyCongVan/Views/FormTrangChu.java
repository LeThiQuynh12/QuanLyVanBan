package QuanLyCongVan.Views;

import QuanLyCongVan.Controllers.LoaiVanBanController;
import QuanLyCongVan.Controllers.NoiBanHanhController;
import QuanLyCongVan.Controllers.PhongBanController;
import QuanLyCongVan.Controllers.QuanLyNguoiDungController;
import QuanLyCongVan.Controllers.SoVanBanController;
import QuanLyCongVan.Controllers.TaiLieuController;
import QuanLyCongVan.Controllers.ThongTinKyDuyetController;
import QuanLyCongVan.Controllers.VanBanDenController;
import QuanLyCongVan.Controllers.VanBanDiController;
import QuanLyCongVan.Controllers.VanBanNoiBoController;
import QuanLyCongVan.Model.LoaiVanBanModel;
import QuanLyCongVan.Model.NoiBanHanhModel;
import QuanLyCongVan.Model.PhongBan;
import QuanLyCongVan.Model.QuanLyNguoiDungModel;
import QuanLyCongVan.Model.SoVanBanModel;
import QuanLyCongVan.Model.TaiLieu;
import QuanLyCongVan.Model.ThongTinKyDuyetModel;
import QuanLyCongVan.Model.VanBanDenModel;
import QuanLyCongVan.Model.VanBanDiModel;
import QuanLyCongVan.Model.VanBanNoiBo;
import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Sheet; 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
import javax.swing.table.JTableHeader;


public final class FormTrangChu extends javax.swing.JFrame {
private DefaultTableModel  tableModelPhongBan = new DefaultTableModel(); 
private final DefaultTableModel tableModelVanBanNoiBo = new DefaultTableModel();
private DefaultTableModel tableModelTaiLieu = new DefaultTableModel();
public static VanBanDenModel vanBanDenChiTiet;
public static VanBanDiModel vanBanDiChiTiet;
    private final DefaultTableModel  tableModel = new DefaultTableModel(); 
DefaultTableModel tablemodel;
    ArrayList<SoVanBanModel> dsvb;
    ArrayList<LoaiVanBanModel> dsl;
    ArrayList<NoiBanHanhModel> dsnoi;
    ArrayList<VanBanDenModel> dsvbden;
    ArrayList<VanBanDiModel> dsvbdi;
    ArrayList<VanBanDenModel> dstimkiemden;
    ArrayList<VanBanDiModel> dstimkiemdi;
    
    public FormTrangChu() throws SQLException {
        initComponents();
        CardLayout cardLayout = (CardLayout) pnChuaCacCard.getLayout();
        // Thêm từng panel vào CardLayout với key tương ứng
        pnChuaCacCard.add(panelHeThong, "heThong");
        pnChuaCacCard.add(panelQLVanBan, "quanLyVanBan");
        pnChuaCacCard.add(panelQLVBNoiBo, "quanLyVanBanNoiBo");
        pnChuaCacCard.add(panelTimThong, "timThong");
        initTablePhongBan();
        fillDataPhongBan();
        
        
              initTable();
        fillData();
        
       initTableVanBanNoiBo();
       fillDataVanBanNoiBo();
       initTableVanBanNoiBo1();
       
       loadComboboxData();
       loadComboboxData1();
        initTableTaiLieu(); 
        fillDataTaiLieu();
        
        KhoiTao();
        HienThiNamVaVanBan();
        HienThiTableLoaiVanBan();
        HienThiTableNoiBanHanh();
        KhoiTaoVanBanDen();
        KhoiTaoVanBanDi();
        HienThiDanhSachVanBanDen();
        HienThiDanhSachVanBanDi();
        
        KhoiTaoTimKiemVanBanDen();
        //HienThiTimKiemVanBanDen();
        KhoiTaoTimKiemVanBanDi();
        //HienThiTimKiemVanBanDi();
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jFrame1 = new javax.swing.JFrame();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        btnThem1 = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        btnTaiLai1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton31 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        ThanhMenu1 = new javax.swing.JPanel();
        panelButton1 = new javax.swing.JPanel();
        btnHeThong1 = new javax.swing.JButton();
        btnQLVBNoiBo1 = new javax.swing.JButton();
        btnTim_Thong1 = new javax.swing.JButton();
        btnQLVanBan1 = new javax.swing.JButton();
        pnChuaCacCard1 = new javax.swing.JPanel();
        panelHeThong1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        panelQLVanBan1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        panelQLVBNoiBo1 = new javax.swing.JPanel();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        panelTimThong1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jButton60 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jPopupMenu5 = new javax.swing.JPopupMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jPopupMenu6 = new javax.swing.JPopupMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jPopupMenu7 = new javax.swing.JPopupMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSpinField1 = new com.toedter.components.JSpinField();
        jPopupMenu8 = new javax.swing.JPopupMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        ThanhMenu = new javax.swing.JPanel();
        panelButton = new javax.swing.JPanel();
        btnHeThong = new javax.swing.JButton();
        btnQLVBNoiBo = new javax.swing.JButton();
        btnTim_Thong = new javax.swing.JButton();
        btnQLVanBan = new javax.swing.JButton();
        pnChuaCacCard = new javax.swing.JPanel();
        panelHeThong = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        panelQLVanBan = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnSoVanBan = new javax.swing.JButton();
        btnNoiBanHanh = new javax.swing.JButton();
        btnVanBanDen = new javax.swing.JButton();
        btnLoaiVanBan = new javax.swing.JButton();
        btnDSVBDen = new javax.swing.JButton();
        btnDSVBDi = new javax.swing.JButton();
        btnVanBanDi = new javax.swing.JButton();
        panelQLVBNoiBo = new javax.swing.JPanel();
        btnVanBanNoiBo = new javax.swing.JButton();
        btnDsNoiBo = new javax.swing.JButton();
        btnPhongBan = new javax.swing.JButton();
        panelTimThong = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnThongKeVanBanDen = new javax.swing.JButton();
        btnThongKeVBNB = new javax.swing.JButton();
        btnTKVanBandi = new javax.swing.JButton();
        pnThan = new javax.swing.JPanel();
        pnChuaThan = new javax.swing.JPanel();
        pnTrangCHU = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        pnChinh = new javax.swing.JPanel();
        pnChuaNoiDung = new javax.swing.JTabbedPane();
        pnPhongBan = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTaiLai = new javax.swing.JButton();
        btnLuu1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPhongBan = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
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
        btnDinhKemFile = new javax.swing.JButton();
        btnDocFile = new javax.swing.JButton();
        btnXoaFile = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        btnThem8 = new javax.swing.JButton();
        btnLuu7 = new javax.swing.JButton();
        btnTaiLai8 = new javax.swing.JButton();
        pnDSNoibo = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        btnSua9 = new javax.swing.JButton();
        btnThemQuayLaiDeThem = new javax.swing.JButton();
        btnXoa9 = new javax.swing.JButton();
        btnXoa10 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbVanBanNoiBo = new javax.swing.JTable();
        pnQuanLyVanBan = new javax.swing.JPanel();
        pnChuaQuanLyVanBan = new javax.swing.JTabbedPane();
        panelSoVanBan3 = new javax.swing.JPanel();
        jPanel107 = new javax.swing.JPanel();
        btnThemSo = new javax.swing.JButton();
        btnTaiLaiSo = new javax.swing.JButton();
        btnXoaSo = new javax.swing.JButton();
        btnLuuSo = new javax.swing.JButton();
        btnXuatExcelSoVanBan = new javax.swing.JButton();
        jPanel108 = new javax.swing.JPanel();
        jScrollPane28 = new javax.swing.JScrollPane();
        tblSoVanBan = new javax.swing.JTable();
        jLabel189 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        panelLoaiVanBan3 = new javax.swing.JPanel();
        jPanel109 = new javax.swing.JPanel();
        btnThemLoai = new javax.swing.JButton();
        btnTaiLaiLoai = new javax.swing.JButton();
        btnXoaLoai = new javax.swing.JButton();
        btnLuuLoai = new javax.swing.JButton();
        btnXuatExcelLoaiVanBan = new javax.swing.JButton();
        jPanel110 = new javax.swing.JPanel();
        jScrollPane29 = new javax.swing.JScrollPane();
        tblLoaiVanBan = new javax.swing.JTable();
        panelNoiBanHanh3 = new javax.swing.JPanel();
        jPanel111 = new javax.swing.JPanel();
        btnThemNoi = new javax.swing.JButton();
        btnTaiLaiNoi = new javax.swing.JButton();
        btnXoaNoi = new javax.swing.JButton();
        btnLuuNoi = new javax.swing.JButton();
        btnXuatExcelNoiBanHanh = new javax.swing.JButton();
        jPanel112 = new javax.swing.JPanel();
        jScrollPane30 = new javax.swing.JScrollPane();
        tblNoiBanHanh = new javax.swing.JTable();
        panelVanBanDen3 = new javax.swing.JPanel();
        jPanel113 = new javax.swing.JPanel();
        btnThemVanBanDen = new javax.swing.JButton();
        btnXoaVanBanDen = new javax.swing.JButton();
        btnLuuVanBanDen = new javax.swing.JButton();
        btnXuatExcelVanBanDen = new javax.swing.JButton();
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
        jScrollPane11 = new javax.swing.JScrollPane();
        txtTrichYeuDen = new javax.swing.JTextArea();
        jPanel117 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtNoiDungDen = new javax.swing.JTextArea();
        jPanel119 = new javax.swing.JPanel();
        btnDinhKemFile4 = new javax.swing.JButton();
        txtDinhKemFileDen = new javax.swing.JTextField();
        panelVanBanDi3 = new javax.swing.JPanel();
        jPanel120 = new javax.swing.JPanel();
        btnThemVanBanDi = new javax.swing.JButton();
        btnTaiLaiVanBanDi = new javax.swing.JButton();
        btnXoaVanBanDi = new javax.swing.JButton();
        btnLuuVanBanDi = new javax.swing.JButton();
        btnXuatExcelVanBanDi = new javax.swing.JButton();
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
        jScrollPane9 = new javax.swing.JScrollPane();
        txtTrichYeuDi = new javax.swing.JTextArea();
        jPanel124 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtNoiDungDi = new javax.swing.JTextArea();
        jPanel125 = new javax.swing.JPanel();
        btnDinhKemFileDi3 = new javax.swing.JButton();
        txtDinhKemFileDi = new javax.swing.JTextField();
        panelDanhSachDen3 = new javax.swing.JPanel();
        jPanel126 = new javax.swing.JPanel();
        btnThemDSDen = new javax.swing.JButton();
        btnTaiLaiDSDen = new javax.swing.JButton();
        btnXoaDSDen = new javax.swing.JButton();
        btnLuuDSDen = new javax.swing.JButton();
        btnXuatExcelDSDen = new javax.swing.JButton();
        jPanel127 = new javax.swing.JPanel();
        cboNamDen = new javax.swing.JComboBox<>();
        jPanel128 = new javax.swing.JPanel();
        jScrollPane31 = new javax.swing.JScrollPane();
        tblDanhSachVanBanDen = new javax.swing.JTable();
        panelDanhSachDi3 = new javax.swing.JPanel();
        jPanel129 = new javax.swing.JPanel();
        btnThemDanhSachDi = new javax.swing.JButton();
        btnTaiLaiDSDi = new javax.swing.JButton();
        btnXoaDSDi = new javax.swing.JButton();
        btnLuuDSDi = new javax.swing.JButton();
        btnXuatExcelDSDi = new javax.swing.JButton();
        jPanel130 = new javax.swing.JPanel();
        cboNamDi = new javax.swing.JComboBox<>();
        jPanel131 = new javax.swing.JPanel();
        jScrollPane32 = new javax.swing.JScrollPane();
        tblDanhSachVanBanDi = new javax.swing.JTable();
        pnTimKiemThongKe = new javax.swing.JPanel();
        pnChuaTimKiemThongKe = new javax.swing.JTabbedPane();
        ThongKeVanBanDi = new javax.swing.JPanel();
        btnTimKiemVanBanDi = new javax.swing.JButton();
        btnXemChiTiet3 = new javax.swing.JButton();
        btnXuatFileExcel3 = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane33 = new javax.swing.JScrollPane();
        tblTimKiemDanhSachVanBanDi = new javax.swing.JTable();
        jLabel111 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        txtTimKiemDi = new javax.swing.JTextArea();
        dateTimKiemMinDi = new com.toedter.calendar.JDateChooser();
        dateTimKiemMaxDi = new com.toedter.calendar.JDateChooser();
        cboTimKiemSoVanBanDi = new javax.swing.JComboBox<>();
        cboTimKiemLoaiVanBanDi = new javax.swing.JComboBox<>();
        txtTimKiemSoKyHieuDi = new javax.swing.JTextField();
        txtNoiNhanTimKiemDi = new javax.swing.JTextField();
        ThoiGianTimKiemDi = new javax.swing.JComboBox<>();
        ThongKeVanBanDen = new javax.swing.JPanel();
        btnXemChiTiet1 = new javax.swing.JButton();
        btnXuatFileExcel1 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane34 = new javax.swing.JScrollPane();
        tblTimKiemDanhSachVanBanDen = new javax.swing.JTable();
        jLabel94 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        txtTimKiemDen = new javax.swing.JTextArea();
        cboTimKiemSoVanBanDen = new javax.swing.JComboBox<>();
        cboTimKiemLoaiVanBanDen = new javax.swing.JComboBox<>();
        txtTimKiemSoKyHieuDen = new javax.swing.JTextField();
        txtNoiBanHanhTimKiemDen = new javax.swing.JTextField();
        ThoiGianTimKiemDen = new javax.swing.JComboBox<>();
        dateTimKiemMinDen = new com.toedter.calendar.JDateChooser();
        dateTimKiemMaxDen = new com.toedter.calendar.JDateChooser();
        btnTimKiemVanBanDen = new javax.swing.JButton();
        ThongKeVanBanNoiBo = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        nhapTrichYeu = new javax.swing.JTextArea();
        jLabel82 = new javax.swing.JLabel();
        ThoiGianTimKiem = new javax.swing.JComboBox<>();
        dateTimKiemNoiBoMin = new com.toedter.calendar.JDateChooser();
        dateTimKiemNoiBoMax = new com.toedter.calendar.JDateChooser();
        chonCmbLoaiBanHanh = new javax.swing.JComboBox<>();
        chonCmbPhongBanHanh = new javax.swing.JComboBox<>();
        ChonCmbPhongBanNhan = new javax.swing.JComboBox<>();
        nhapSoKyHieu = new javax.swing.JTextField();
        nhapTenVanBan = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnXemChiTiet = new javax.swing.JButton();
        btnXuatFileExcel = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbVanBanNoiBo1 = new javax.swing.JTable();
        jLabel83 = new javax.swing.JLabel();
        btnInBaoCao = new javax.swing.JButton();
        pnNguoiDung = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        btnThem2 = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblNguoiDung = new javax.swing.JTable();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtTenTaiKhoan = new javax.swing.JTextField();
        pwMatKhau = new javax.swing.JPasswordField();
        pnKy = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        txtNguoiKy1 = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        txtNguoiDuyet1 = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        txtNguoiGui = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        txtNguoiNhan1 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        btnLuu2 = new javax.swing.JButton();
        txtNoiBanHanh = new javax.swing.JTextField();
        jButton63 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jButton65 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jMenuItem1.setText("Thêm mới");
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Sửa");
        jPopupMenu2.add(jMenuItem2);

        jMenuItem3.setText("Xóa");
        jPopupMenu3.add(jMenuItem3);

        jMenuItem4.setText("Tải lại");
        jPopupMenu4.add(jMenuItem4);

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jFrame1.setUndecorated(true);

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));

        jToolBar2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jToolBar2.setRollover(true);

        btnThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/insert.png"))); // NOI18N
        btnThem1.setFocusable(false);
        btnThem1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThem1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThem1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThem1MouseExited(evt);
            }
        });
        jToolBar2.add(btnThem1);

        btnSua1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/service.png"))); // NOI18N
        btnSua1.setFocusable(false);
        btnSua1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSua1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSua1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSua1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSua1MouseExited(evt);
            }
        });
        jToolBar2.add(btnSua1);

        btnXoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/trash.png"))); // NOI18N
        btnXoa1.setFocusable(false);
        btnXoa1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXoa1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnXoa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXoa1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXoa1MouseExited(evt);
            }
        });
        jToolBar2.add(btnXoa1);

        btnTaiLai1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/reset.png"))); // NOI18N
        btnTaiLai1.setFocusable(false);
        btnTaiLai1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTaiLai1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTaiLai1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTaiLai1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTaiLai1MouseExited(evt);
            }
        });
        jToolBar2.add(btnTaiLai1);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel10.setBackground(new java.awt.Color(230, 247, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 153), null));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 51));
        jLabel4.setText("QUY TRÌNH QUẢN LÝ VĂN BẢN ĐI VÀ ĐẾN");
        jLabel4.setToolTipText("");

        jButton31.setBackground(new java.awt.Color(255, 249, 196));
        jButton31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton31.setForeground(new java.awt.Color(0, 102, 51));
        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/search.png"))); // NOI18N
        jButton31.setText("Tìm kiếm thống kê");

        jButton33.setBackground(new java.awt.Color(255, 249, 196));
        jButton33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton33.setForeground(new java.awt.Color(0, 102, 51));
        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/open-book.png"))); // NOI18N
        jButton33.setText("Danh sách văn bản đến");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setBackground(new java.awt.Color(255, 249, 196));
        jButton34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton34.setForeground(new java.awt.Color(0, 102, 51));
        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/go-to-location.png"))); // NOI18N
        jButton34.setText("Văn bản đến");

        jButton35.setBackground(new java.awt.Color(255, 249, 196));
        jButton35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton35.setForeground(new java.awt.Color(0, 102, 51));
        jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/software.png"))); // NOI18N
        jButton35.setText("Số văn bản");

        jButton36.setBackground(new java.awt.Color(255, 249, 196));
        jButton36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton36.setForeground(new java.awt.Color(0, 102, 51));
        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/chat.png"))); // NOI18N
        jButton36.setText("Loại văn bản");

        jButton37.setBackground(new java.awt.Color(255, 249, 196));
        jButton37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton37.setForeground(new java.awt.Color(0, 102, 51));
        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/location.png"))); // NOI18N
        jButton37.setText("Nơi ban hành");

        jButton38.setBackground(new java.awt.Color(255, 249, 196));
        jButton38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton38.setForeground(new java.awt.Color(0, 102, 51));
        jButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/back.png"))); // NOI18N
        jButton38.setText("Văn bản đi");

        jButton39.setBackground(new java.awt.Color(255, 249, 196));
        jButton39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton39.setForeground(new java.awt.Color(0, 102, 51));
        jButton39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/previous (1).png"))); // NOI18N
        jButton39.setText("Danh sách văn bản đi");

        jButton40.setBackground(new java.awt.Color(255, 249, 196));
        jButton40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton40.setForeground(new java.awt.Color(0, 102, 51));
        jButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/previous.png"))); // NOI18N
        jButton40.setText("Tìm kiếm thống kê");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/len.png"))); // NOI18N

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/len.png"))); // NOI18N

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/1.png"))); // NOI18N
        jLabel34.setText("jLabel5");

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/len.png"))); // NOI18N

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/1.png"))); // NOI18N
        jLabel37.setText("jLabel5");

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/1.png"))); // NOI18N
        jLabel41.setText("jLabel5");

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/1.png"))); // NOI18N
        jLabel42.setText("jLabel5");

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/xuong.png"))); // NOI18N

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/xuong.png"))); // NOI18N

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/xuong.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(148, 148, 148))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(172, 172, 172))))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton36)
                        .addGap(22, 22, 22)
                        .addComponent(jButton37)
                        .addGap(13, 13, 13)
                        .addComponent(jButton35))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(133, 133, 133)))
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(214, 214, 214)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jButton39)
                        .addGap(43, 43, 43)
                        .addComponent(jButton40))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton33)
                        .addGap(87, 87, 87)
                        .addComponent(jButton31))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jButton34)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(jButton33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel36)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton37, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                        .addComponent(jButton35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addComponent(jLabel32))
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel49)
                .addGap(0, 0, 0)
                .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jPanel11.setBackground(new java.awt.Color(230, 247, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 204), null));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 102, 51));
        jLabel50.setText("QUY TRÌNH QUẢN LÝ VĂN BẢN NỘI BỘ");
        jLabel50.setToolTipText("");

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/1.png"))); // NOI18N
        jLabel51.setText("jLabel5");

        jButton41.setBackground(new java.awt.Color(255, 249, 196));
        jButton41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton41.setForeground(new java.awt.Color(0, 102, 51));
        jButton41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/chat.png"))); // NOI18N
        jButton41.setText("Loại văn bản");

        jButton42.setBackground(new java.awt.Color(255, 249, 196));
        jButton42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton42.setForeground(new java.awt.Color(0, 102, 51));
        jButton42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/security.png"))); // NOI18N
        jButton42.setText("Phòng ban hành");

        jButton43.setBackground(new java.awt.Color(255, 249, 196));
        jButton43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton43.setForeground(new java.awt.Color(0, 102, 51));
        jButton43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/encrypted.png"))); // NOI18N
        jButton43.setText("Phòng ban nhận");

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jButton44.setBackground(new java.awt.Color(255, 249, 196));
        jButton44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton44.setForeground(new java.awt.Color(0, 102, 51));
        jButton44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/compliant.png"))); // NOI18N
        jButton44.setText("Văn bản nội bộ");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        jButton45.setBackground(new java.awt.Color(255, 249, 196));
        jButton45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton45.setForeground(new java.awt.Color(0, 102, 51));
        jButton45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/private-account.png"))); // NOI18N
        jButton45.setText("Danh sách văn bản nội bộ");

        jButton46.setBackground(new java.awt.Color(255, 249, 196));
        jButton46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton46.setForeground(new java.awt.Color(0, 102, 51));
        jButton46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/search.png"))); // NOI18N
        jButton46.setText("Tìm kiếm thống kê");

        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/xuong.png"))); // NOI18N

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/1.png"))); // NOI18N
        jLabel56.setText("jLabel5");

        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/xuong.png"))); // NOI18N

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/xuong.png"))); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(212, 212, 212)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton42)
                        .addGap(18, 18, 18)
                        .addComponent(jButton43)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                            .addGap(84, 84, 84)
                            .addComponent(jLabel59)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                            .addGap(58, 58, 58)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton46, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ThanhMenu1.setBackground(new java.awt.Color(204, 255, 255));

        panelButton1.setBackground(new java.awt.Color(255, 255, 255));

        btnHeThong1.setBackground(new java.awt.Color(204, 204, 255));
        btnHeThong1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHeThong1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/software.png"))); // NOI18N
        btnHeThong1.setText("Hệ thống");
        btnHeThong1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHeThong1ActionPerformed(evt);
            }
        });

        btnQLVBNoiBo1.setBackground(new java.awt.Color(204, 204, 255));
        btnQLVBNoiBo1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQLVBNoiBo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/compliant.png"))); // NOI18N
        btnQLVBNoiBo1.setText("Quản lý văn bản nội bộ");
        btnQLVBNoiBo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLVBNoiBo1ActionPerformed(evt);
            }
        });

        btnTim_Thong1.setBackground(new java.awt.Color(204, 204, 255));
        btnTim_Thong1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTim_Thong1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/analysis.png"))); // NOI18N
        btnTim_Thong1.setText("Tìm kiếm - Thống kê");
        btnTim_Thong1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTim_Thong1ActionPerformed(evt);
            }
        });

        btnQLVanBan1.setBackground(new java.awt.Color(204, 204, 255));
        btnQLVanBan1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQLVanBan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/open-book.png"))); // NOI18N
        btnQLVanBan1.setText("Quản lý văn bản");
        btnQLVanBan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLVanBan1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButton1Layout = new javax.swing.GroupLayout(panelButton1);
        panelButton1.setLayout(panelButton1Layout);
        panelButton1Layout.setHorizontalGroup(
            panelButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButton1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHeThong1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQLVBNoiBo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTim_Thong1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQLVanBan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelButton1Layout.setVerticalGroup(
            panelButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButton1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnHeThong1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnQLVanBan1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnQLVBNoiBo1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTim_Thong1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnChuaCacCard1.setBackground(new java.awt.Color(255, 255, 204));
        pnChuaCacCard1.setLayout(new java.awt.CardLayout());

        panelHeThong1.setBackground(new java.awt.Color(204, 255, 255));
        panelHeThong1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hệ thống", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        panelHeThong1.setLayout(new java.awt.CardLayout());

        jButton47.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/selecting.png"))); // NOI18N
        jButton47.setText("Phân quyền nhân viên");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        jButton48.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/text-box.png"))); // NOI18N
        jButton48.setText("Thông tin người ký duyệt");

        jButton49.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/team.png"))); // NOI18N
        jButton49.setText("Quản lý người dùng");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton48, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(jButton47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton49, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(206, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(355, Short.MAX_VALUE)))
        );

        panelHeThong1.add(jPanel12, "card2");

        pnChuaCacCard1.add(panelHeThong1, "card3");

        panelQLVanBan1.setBackground(new java.awt.Color(204, 255, 255));
        panelQLVanBan1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý văn bản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        panelQLVanBan1.setLayout(new java.awt.CardLayout());

        jButton50.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/text-box.png"))); // NOI18N
        jButton50.setText("Số văn bản");

        jButton51.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/location.png"))); // NOI18N
        jButton51.setText("Nơi ban hành");

        jButton52.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/go-to-location.png"))); // NOI18N
        jButton52.setText("Văn bản đến");

        jButton53.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/chat.png"))); // NOI18N
        jButton53.setText("Loại văn bản");

        jButton54.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/previous (1).png"))); // NOI18N
        jButton54.setText("Danh sách văn bản đến");

        jButton55.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/previous.png"))); // NOI18N
        jButton55.setText("Danh sách văn bản đi");

        jButton56.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/back.png"))); // NOI18N
        jButton56.setText("Văn bản đi");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton51, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(jButton53, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(jButton56, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(jButton54, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        panelQLVanBan1.add(jPanel13, "card2");

        pnChuaCacCard1.add(panelQLVanBan1, "card2");

        panelQLVBNoiBo1.setBackground(new java.awt.Color(204, 255, 255));
        panelQLVBNoiBo1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý văn bản nội bộ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jButton57.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/encrypted.png"))); // NOI18N
        jButton57.setText("Văn bản nội bộ");

        jButton58.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/compliant.png"))); // NOI18N
        jButton58.setText("Danh sách văn bản nội bộ");

        jButton59.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/security.png"))); // NOI18N
        jButton59.setText("Phòng ban");

        javax.swing.GroupLayout panelQLVBNoiBo1Layout = new javax.swing.GroupLayout(panelQLVBNoiBo1);
        panelQLVBNoiBo1.setLayout(panelQLVBNoiBo1Layout);
        panelQLVBNoiBo1Layout.setHorizontalGroup(
            panelQLVBNoiBo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLVBNoiBo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQLVBNoiBo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton59, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelQLVBNoiBo1Layout.setVerticalGroup(
            panelQLVBNoiBo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLVBNoiBo1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton57, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        pnChuaCacCard1.add(panelQLVBNoiBo1, "card4");

        panelTimThong1.setBackground(new java.awt.Color(204, 255, 255));
        panelTimThong1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm - Thống kê", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jButton60.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/seo-report.png"))); // NOI18N
        jButton60.setText("Thống kê văn bản đến");

        jButton61.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/analysis.png"))); // NOI18N
        jButton61.setText("Thống kê văn bản nội bộ");
        jButton61.setToolTipText("");

        jButton62.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/analytics.png"))); // NOI18N
        jButton62.setText("Thống kê văn bản đi");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton61, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(jButton62, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelTimThong1Layout = new javax.swing.GroupLayout(panelTimThong1);
        panelTimThong1.setLayout(panelTimThong1Layout);
        panelTimThong1Layout.setHorizontalGroup(
            panelTimThong1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTimThong1Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTimThong1Layout.setVerticalGroup(
            panelTimThong1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimThong1Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnChuaCacCard1.add(panelTimThong1, "card5");

        javax.swing.GroupLayout ThanhMenu1Layout = new javax.swing.GroupLayout(ThanhMenu1);
        ThanhMenu1.setLayout(ThanhMenu1Layout);
        ThanhMenu1Layout.setHorizontalGroup(
            ThanhMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThanhMenu1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ThanhMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnChuaCacCard1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        ThanhMenu1Layout.setVerticalGroup(
            ThanhMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThanhMenu1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnChuaCacCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(ThanhMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ThanhMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jMenuBar2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 255), new java.awt.Color(204, 255, 255)));

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        jFrame1.setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPopupMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPopupMenu5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPopupMenu5MouseExited(evt);
            }
        });

        jMenuItem5.setBackground(new java.awt.Color(255, 204, 255));
        jMenuItem5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/log-in.png"))); // NOI18N
        jMenuItem5.setText("Đăng nhập lại");
        jMenuItem5.setToolTipText("");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jPopupMenu5.add(jMenuItem5);

        jMenuItem7.setBackground(new java.awt.Color(255, 204, 255));
        jMenuItem7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/button.png"))); // NOI18N
        jMenuItem7.setText("Thoát");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jPopupMenu5.add(jMenuItem7);

        jPopupMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPopupMenu6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPopupMenu6MouseExited(evt);
            }
        });

        jMenuItem8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem8.setText("Sửa thông tin cá nhân");
        jPopupMenu6.add(jMenuItem8);

        jMenuItem9.setText("jMenuItem9");

        jMenu6.setText("jMenu6");

        jMenuItem10.setText("jMenuItem10");

        jMenuItem11.setText("Lưu");
        jPopupMenu7.add(jMenuItem11);

        jMenuItem12.setText("Xuất excel");
        jPopupMenu8.add(jMenuItem12);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("                                                                                                              QUẢN LÝ VĂN BẢN - CÔNG VĂN                                          ");
        setBackground(new java.awt.Color(204, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        ThanhMenu.setBackground(new java.awt.Color(204, 255, 255));

        panelButton.setBackground(new java.awt.Color(255, 255, 255));

        btnHeThong.setBackground(new java.awt.Color(204, 204, 255));
        btnHeThong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHeThong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/software.png"))); // NOI18N
        btnHeThong.setText("Hệ thống");
        btnHeThong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHeThongActionPerformed(evt);
            }
        });

        btnQLVBNoiBo.setBackground(new java.awt.Color(204, 204, 255));
        btnQLVBNoiBo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQLVBNoiBo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/compliant.png"))); // NOI18N
        btnQLVBNoiBo.setText("Quản lý văn bản nội bộ");
        btnQLVBNoiBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLVBNoiBoActionPerformed(evt);
            }
        });

        btnTim_Thong.setBackground(new java.awt.Color(204, 204, 255));
        btnTim_Thong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTim_Thong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/analysis.png"))); // NOI18N
        btnTim_Thong.setText("Tìm kiếm - Thống kê");
        btnTim_Thong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTim_ThongActionPerformed(evt);
            }
        });

        btnQLVanBan.setBackground(new java.awt.Color(204, 204, 255));
        btnQLVanBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQLVanBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/open-book.png"))); // NOI18N
        btnQLVanBan.setText("Quản lý văn bản");
        btnQLVanBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLVanBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHeThong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQLVBNoiBo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTim_Thong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQLVanBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelButtonLayout.setVerticalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnHeThong, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnQLVanBan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnQLVBNoiBo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTim_Thong, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pnChuaCacCard.setBackground(new java.awt.Color(255, 255, 204));
        pnChuaCacCard.setLayout(new java.awt.CardLayout());

        panelHeThong.setBackground(new java.awt.Color(204, 255, 255));
        panelHeThong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hệ thống", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        panelHeThong.setLayout(new java.awt.CardLayout());

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/selecting.png"))); // NOI18N
        jButton2.setText("Thông tin ký duyệt");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/team.png"))); // NOI18N
        jButton6.setText("Quản lý người dùng");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(354, Short.MAX_VALUE)))
        );

        panelHeThong.add(jPanel3, "card2");

        pnChuaCacCard.add(panelHeThong, "card3");

        panelQLVanBan.setBackground(new java.awt.Color(204, 255, 255));
        panelQLVanBan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý văn bản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        panelQLVanBan.setLayout(new java.awt.CardLayout());

        btnSoVanBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSoVanBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/text-box.png"))); // NOI18N
        btnSoVanBan.setText("Số văn bản");
        btnSoVanBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSoVanBanActionPerformed(evt);
            }
        });

        btnNoiBanHanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNoiBanHanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/location.png"))); // NOI18N
        btnNoiBanHanh.setText("Nơi ban hành");
        btnNoiBanHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoiBanHanhActionPerformed(evt);
            }
        });

        btnVanBanDen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVanBanDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/go-to-location.png"))); // NOI18N
        btnVanBanDen.setText("Văn bản đến");
        btnVanBanDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVanBanDenActionPerformed(evt);
            }
        });

        btnLoaiVanBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoaiVanBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/chat.png"))); // NOI18N
        btnLoaiVanBan.setText("Loại văn bản");
        btnLoaiVanBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoaiVanBanActionPerformed(evt);
            }
        });

        btnDSVBDen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDSVBDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/previous (1).png"))); // NOI18N
        btnDSVBDen.setText("Danh sách văn bản đến");
        btnDSVBDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDSVBDenActionPerformed(evt);
            }
        });

        btnDSVBDi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDSVBDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/previous.png"))); // NOI18N
        btnDSVBDi.setText("Danh sách văn bản đi");
        btnDSVBDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDSVBDiActionPerformed(evt);
            }
        });

        btnVanBanDi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVanBanDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/back.png"))); // NOI18N
        btnVanBanDi.setText("Văn bản đi");
        btnVanBanDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVanBanDiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVanBanDen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNoiBanHanh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSoVanBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDSVBDi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(btnLoaiVanBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(btnVanBanDi, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(btnDSVBDen, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSoVanBan, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoaiVanBan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNoiBanHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDSVBDen, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDSVBDi, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        panelQLVanBan.add(jPanel6, "card2");

        pnChuaCacCard.add(panelQLVanBan, "card2");

        panelQLVBNoiBo.setBackground(new java.awt.Color(204, 255, 255));
        panelQLVBNoiBo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý văn bản nội bộ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnVanBanNoiBo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVanBanNoiBo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/encrypted.png"))); // NOI18N
        btnVanBanNoiBo.setText("Văn bản nội bộ");
        btnVanBanNoiBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVanBanNoiBoActionPerformed(evt);
            }
        });

        btnDsNoiBo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDsNoiBo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/compliant.png"))); // NOI18N
        btnDsNoiBo.setText("Danh sách văn bản nội bộ");
        btnDsNoiBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDsNoiBoActionPerformed(evt);
            }
        });

        btnPhongBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPhongBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/security.png"))); // NOI18N
        btnPhongBan.setText("Phòng ban");
        btnPhongBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhongBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelQLVBNoiBoLayout = new javax.swing.GroupLayout(panelQLVBNoiBo);
        panelQLVBNoiBo.setLayout(panelQLVBNoiBoLayout);
        panelQLVBNoiBoLayout.setHorizontalGroup(
            panelQLVBNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLVBNoiBoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQLVBNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDsNoiBo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVanBanNoiBo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPhongBan, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelQLVBNoiBoLayout.setVerticalGroup(
            panelQLVBNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQLVBNoiBoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnVanBanNoiBo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnDsNoiBo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        pnChuaCacCard.add(panelQLVBNoiBo, "card4");

        panelTimThong.setBackground(new java.awt.Color(204, 255, 255));
        panelTimThong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm - Thống kê", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnThongKeVanBanDen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThongKeVanBanDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/seo-report.png"))); // NOI18N
        btnThongKeVanBanDen.setText("Thống kê văn bản đến");
        btnThongKeVanBanDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeVanBanDenActionPerformed(evt);
            }
        });

        btnThongKeVBNB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThongKeVBNB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/analysis.png"))); // NOI18N
        btnThongKeVBNB.setText("Thống kê văn bản nội bộ");
        btnThongKeVBNB.setToolTipText("");
        btnThongKeVBNB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeVBNBActionPerformed(evt);
            }
        });

        btnTKVanBandi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTKVanBandi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/analytics.png"))); // NOI18N
        btnTKVanBandi.setText("Thống kê văn bản đi");
        btnTKVanBandi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKVanBandiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThongKeVanBanDen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongKeVBNB, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(btnTKVanBandi, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnTKVanBandi, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnThongKeVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnThongKeVBNB, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelTimThongLayout = new javax.swing.GroupLayout(panelTimThong);
        panelTimThong.setLayout(panelTimThongLayout);
        panelTimThongLayout.setHorizontalGroup(
            panelTimThongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTimThongLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTimThongLayout.setVerticalGroup(
            panelTimThongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimThongLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnChuaCacCard.add(panelTimThong, "card5");

        javax.swing.GroupLayout ThanhMenuLayout = new javax.swing.GroupLayout(ThanhMenu);
        ThanhMenu.setLayout(ThanhMenuLayout);
        ThanhMenuLayout.setHorizontalGroup(
            ThanhMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThanhMenuLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(ThanhMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnChuaCacCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        ThanhMenuLayout.setVerticalGroup(
            ThanhMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThanhMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnChuaCacCard, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pnThan.setBackground(new java.awt.Color(255, 255, 204));

        pnChuaThan.setBackground(new java.awt.Color(255, 255, 204));
        pnChuaThan.setLayout(new java.awt.CardLayout());

        pnTrangCHU.setBackground(new java.awt.Color(204, 255, 204));
        pnTrangCHU.setLayout(new java.awt.CardLayout());

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        jPanel5.setBackground(new java.awt.Color(230, 247, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 153), null));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 51));
        jLabel2.setText("QUY TRÌNH QUẢN LÝ VĂN BẢN ĐI VÀ ĐẾN");
        jLabel2.setToolTipText("");

        jButton3.setBackground(new java.awt.Color(255, 249, 196));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 102, 51));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/search.png"))); // NOI18N
        jButton3.setText("Tìm kiếm thống kê");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 249, 196));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 51));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/open-book.png"))); // NOI18N
        jButton1.setText("Danh sách văn bản đến");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 249, 196));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 102, 51));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/go-to-location.png"))); // NOI18N
        jButton5.setText("Văn bản đến");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 249, 196));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 102, 51));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/software.png"))); // NOI18N
        jButton7.setText("Số văn bản");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 249, 196));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 102, 51));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/chat.png"))); // NOI18N
        jButton8.setText("Loại văn bản");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 249, 196));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(0, 102, 51));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/location.png"))); // NOI18N
        jButton9.setText("Nơi ban hành");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 249, 196));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(0, 102, 51));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/back.png"))); // NOI18N
        jButton10.setText("Văn bản đi");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(255, 249, 196));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 102, 51));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/previous (1).png"))); // NOI18N
        jButton11.setText("Danh sách văn bản đi");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(255, 249, 196));
        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(0, 102, 51));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/previous.png"))); // NOI18N
        jButton12.setText("Tìm kiếm thống kê");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/len.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/len.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/1.png"))); // NOI18N
        jLabel9.setText("jLabel5");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/len.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/1.png"))); // NOI18N
        jLabel14.setText("jLabel5");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/1.png"))); // NOI18N
        jLabel18.setText("jLabel5");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/1.png"))); // NOI18N
        jLabel19.setText("jLabel5");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/xuong.png"))); // NOI18N

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/xuong.png"))); // NOI18N

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/xuong.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(148, 148, 148))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 289, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(172, 172, 172))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton8)
                        .addGap(22, 22, 22)
                        .addComponent(jButton9)
                        .addGap(13, 13, 13)
                        .addComponent(jButton7))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(133, 133, 133)))
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(214, 214, 214)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jButton11)
                        .addGap(22, 22, 22)
                        .addComponent(jButton12))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton1)
                        .addGap(55, 55, 55)
                        .addComponent(jButton3))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jButton5)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel8)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addComponent(jLabel5))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel27)
                .addGap(0, 0, 0)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jPanel2.setBackground(new java.awt.Color(230, 247, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 204), null));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 51));
        jLabel3.setText("QUY TRÌNH QUẢN LÝ VĂN BẢN NỘI BỘ");
        jLabel3.setToolTipText("");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/1.png"))); // NOI18N
        jLabel11.setText("jLabel5");

        jButton13.setBackground(new java.awt.Color(255, 249, 196));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(0, 102, 51));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/chat.png"))); // NOI18N
        jButton13.setText("Loại văn bản");

        jButton14.setBackground(new java.awt.Color(255, 249, 196));
        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(0, 102, 51));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/security.png"))); // NOI18N
        jButton14.setText("Phòng ban hành");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(255, 249, 196));
        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(0, 102, 51));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/encrypted.png"))); // NOI18N
        jButton15.setText("Phòng ban nhận");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jButton16.setBackground(new java.awt.Color(255, 249, 196));
        jButton16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(0, 102, 51));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/compliant.png"))); // NOI18N
        jButton16.setText("Văn bản nội bộ");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(255, 249, 196));
        jButton17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton17.setForeground(new java.awt.Color(0, 102, 51));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/private-account.png"))); // NOI18N
        jButton17.setText("Danh sách văn bản nội bộ");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(255, 249, 196));
        jButton18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton18.setForeground(new java.awt.Color(0, 102, 51));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/search.png"))); // NOI18N
        jButton18.setText("Tìm kiếm thống kê");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/xuong.png"))); // NOI18N

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/1.png"))); // NOI18N
        jLabel28.setText("jLabel5");

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/2.png"))); // NOI18N

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/xuong.png"))); // NOI18N

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/xuong.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(84, 84, 84)
                            .addComponent(jLabel31)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(58, 58, 58)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton15))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(253, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        pnTrangCHU.add(jPanel4, "card2");

        pnChuaThan.add(pnTrangCHU, "card3");

        pnChinh.setBackground(new java.awt.Color(255, 255, 255));
        pnChinh.setLayout(new java.awt.CardLayout());

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

        pnPhongBan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(220, 220, 255));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/insert.png"))); // NOI18N
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThemMouseExited(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/encrypted.png"))); // NOI18N
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLuuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLuuMouseExited(evt);
            }
        });
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/trash.png"))); // NOI18N
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXoaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXoaMouseExited(evt);
            }
        });
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnTaiLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/reset.png"))); // NOI18N
        btnTaiLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTaiLaiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTaiLaiMouseExited(evt);
            }
        });
        btnTaiLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiLaiActionPerformed(evt);
            }
        });

        btnLuu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/excel.png"))); // NOI18N
        btnLuu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLuu1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLuu1MouseExited(evt);
            }
        });
        btnLuu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuu1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLuu1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(btnLuu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTaiLai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLuu1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnXoa)
        );

        tbPhongBan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        tbPhongBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Phòng ban", "Mã phòng ban"
            }
        ));
        jScrollPane1.setViewportView(tbPhongBan);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN PHÒNG BAN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(102, 102, 255));
        jLabel60.setText("Phòng ban có: ");

        jLabel61.setBackground(new java.awt.Color(153, 51, 255));
        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(153, 0, 153));
        jLabel61.setText("Phòng ban hành: Khởi tạo/gửi tài liệu, quyết định, thông tin nội bộ hoặc ra bên ngoài.");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(204, 0, 204));
        jLabel62.setText("Phòng ban nhận: Tiếp nhận tài liệu, thông tin hoặc công việc từ các phòng ban khác.");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(326, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel62)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel84.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(0, 0, 255));
        jLabel84.setText("BẢNG DỮ LIỆU PHÒNG BAN");

        javax.swing.GroupLayout pnPhongBanLayout = new javax.swing.GroupLayout(pnPhongBan);
        pnPhongBan.setLayout(pnPhongBanLayout);
        pnPhongBanLayout.setHorizontalGroup(
            pnPhongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnPhongBanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnPhongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1087, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPhongBanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(429, 429, 429))
        );
        pnPhongBanLayout.setVerticalGroup(
            pnPhongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPhongBanLayout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel84)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pnChuaNoiDung.addTab("Phòng ban", pnPhongBan);

        pnVanBanNoiBo.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2), "Văn bản nội bộ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 153)))); // NOI18N

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(102, 0, 102));
        jLabel64.setText("Số ký hiệu");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(102, 0, 102));
        jLabel66.setText("Tên văn bản");

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(102, 0, 102));
        jLabel67.setText("Ngày ban hành");

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(102, 0, 102));
        jLabel68.setText("Loại ban hành");

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(102, 0, 102));
        jLabel69.setText("Phòng ban hành");

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(102, 0, 102));
        jLabel70.setText("Phòng nhận");

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(102, 0, 102));
        jLabel71.setText("Người nhận");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(102, 0, 102));
        jLabel72.setText("Người ký");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(102, 0, 102));
        jLabel73.setText("Người duyệt");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNguoiDuyet, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNguoiNhan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                        .addComponent(txtNguoiKy, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 0), 2, true), "Trích yếu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 153))); // NOI18N

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

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 51), 2), "Nội dung", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 153))); // NOI18N

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

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255), 2), "Đính kèm file", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 153))); // NOI18N

        tbDinhKem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã tài liệu", "Tên tài liệu", "Ngày tạo", "Kích cỡ", "Loại", "Đường dẫn"
            }
        ));
        tbDinhKem.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tbDinhKem.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane4.setViewportView(tbDinhKem);

        btnDinhKemFile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDinhKemFile.setText("Đính kèm file");
        btnDinhKemFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDinhKemFileActionPerformed(evt);
            }
        });

        btnDocFile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDocFile.setText("Đọc file");
        btnDocFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocFileActionPerformed(evt);
            }
        });

        btnXoaFile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaFile.setText("Xóa file");
        btnXoaFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDocFile, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(btnDinhKemFile, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(btnXoaFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(309, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(btnDinhKemFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDocFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoaFile, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel28.setBackground(new java.awt.Color(220, 220, 255));

        btnThem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/insert.png"))); // NOI18N
        btnThem8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThem8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThem8MouseExited(evt);
            }
        });
        btnThem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem8ActionPerformed(evt);
            }
        });

        btnLuu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/encrypted.png"))); // NOI18N
        btnLuu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLuu7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLuu7MouseExited(evt);
            }
        });
        btnLuu7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuu7ActionPerformed(evt);
            }
        });

        btnTaiLai8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/reset.png"))); // NOI18N
        btnTaiLai8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTaiLai8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTaiLai8MouseExited(evt);
            }
        });
        btnTaiLai8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiLai8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnThem8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTaiLai8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLuu7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThem8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTaiLai8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnLuu7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnVanBanNoiBoLayout = new javax.swing.GroupLayout(pnVanBanNoiBo);
        pnVanBanNoiBo.setLayout(pnVanBanNoiBoLayout);
        pnVanBanNoiBoLayout.setHorizontalGroup(
            pnVanBanNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnVanBanNoiBoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnVanBanNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnVanBanNoiBoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(pnVanBanNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnVanBanNoiBoLayout.setVerticalGroup(
            pnVanBanNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnVanBanNoiBoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 120, Short.MAX_VALUE))
        );

        pnChuaNoiDung.addTab("Văn bản nội bộ", pnVanBanNoiBo);

        pnDSNoibo.setBackground(new java.awt.Color(255, 255, 255));

        jPanel29.setBackground(new java.awt.Color(220, 220, 255));

        btnSua9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/service.png"))); // NOI18N
        btnSua9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSua9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSua9MouseExited(evt);
            }
        });
        btnSua9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua9ActionPerformed(evt);
            }
        });

        btnThemQuayLaiDeThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/insert.png"))); // NOI18N
        btnThemQuayLaiDeThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemQuayLaiDeThemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThemQuayLaiDeThemMouseExited(evt);
            }
        });
        btnThemQuayLaiDeThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemQuayLaiDeThemActionPerformed(evt);
            }
        });

        btnXoa9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/trash.png"))); // NOI18N
        btnXoa9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXoa9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXoa9MouseExited(evt);
            }
        });
        btnXoa9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa9ActionPerformed(evt);
            }
        });

        btnXoa10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/excel.png"))); // NOI18N
        btnXoa10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXoa10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXoa10MouseExited(evt);
            }
        });
        btnXoa10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnThemQuayLaiDeThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThemQuayLaiDeThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSua9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnXoa9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnXoa10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 51, 255)), "Chọn thời gian lọc thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 153, 153))); // NOI18N

        jComboBox1.setForeground(new java.awt.Color(255, 51, 51));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Tuần này", "Tháng này", "Năm nay", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12", "Năm trước" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 102, 51));
        jLabel74.setText("Từ ngày");

        jLabel75.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 102, 51));
        jLabel75.setText("Đến ngày");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 322, Short.MAX_VALUE)
                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách văn bản nội bộ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 153, 153))); // NOI18N

        tbVanBanNoiBo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số ký hiệu", "Tên văn bản", "Ngày ban hành", "Loại văn bản", "Phòng ban hành", "Phòng ban nhận", "Trích yếu"
            }
        ));
        tbVanBanNoiBo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVanBanNoiBoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbVanBanNoiBo);
        if (tbVanBanNoiBo.getColumnModel().getColumnCount() > 0) {
            tbVanBanNoiBo.getColumnModel().getColumn(4).setResizable(false);
            tbVanBanNoiBo.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnDSNoiboLayout = new javax.swing.GroupLayout(pnDSNoibo);
        pnDSNoibo.setLayout(pnDSNoiboLayout);
        pnDSNoiboLayout.setHorizontalGroup(
            pnDSNoiboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnDSNoiboLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDSNoiboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        pnDSNoiboLayout.setVerticalGroup(
            pnDSNoiboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDSNoiboLayout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 122, Short.MAX_VALUE))
        );

        pnChuaNoiDung.addTab("Danh sách văn bản nội bộ", pnDSNoibo);

        pnChinh.add(pnChuaNoiDung, "card2");

        pnQuanLyVanBan.setBackground(new java.awt.Color(255, 255, 255));
        pnQuanLyVanBan.setLayout(new java.awt.CardLayout());

        pnChuaQuanLyVanBan.setPreferredSize(new java.awt.Dimension(1371, 500));

        btnThemSo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/insert.png"))); // NOI18N
        btnThemSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSoActionPerformed(evt);
            }
        });

        btnTaiLaiSo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/reset.png"))); // NOI18N
        btnTaiLaiSo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaiLaiSoMouseClicked(evt);
            }
        });
        btnTaiLaiSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiLaiSoActionPerformed(evt);
            }
        });

        btnXoaSo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/button.png"))); // NOI18N
        btnXoaSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSoActionPerformed(evt);
            }
        });

        btnLuuSo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/save.png"))); // NOI18N
        btnLuuSo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuSoMouseClicked(evt);
            }
        });
        btnLuuSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuSoActionPerformed(evt);
            }
        });

        btnXuatExcelSoVanBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/excel.png"))); // NOI18N
        btnXuatExcelSoVanBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelSoVanBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel107Layout = new javax.swing.GroupLayout(jPanel107);
        jPanel107.setLayout(jPanel107Layout);
        jPanel107Layout.setHorizontalGroup(
            jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel107Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnThemSo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnLuuSo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXoaSo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTaiLaiSo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXuatExcelSoVanBan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(888, Short.MAX_VALUE))
        );
        jPanel107Layout.setVerticalGroup(
            jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel107Layout.createSequentialGroup()
                .addGroup(jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiLaiSo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuSo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatExcelSoVanBan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel108.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sổ văn bản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        tblSoVanBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Sổ văn bản", "Số đến", "Năm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSoVanBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSoVanBanMouseClicked(evt);
            }
        });
        jScrollPane28.setViewportView(tblSoVanBan);

        jLabel189.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel189.setText("Năm");

        cboNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboNamMouseClicked(evt);
            }
        });
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel108Layout = new javax.swing.GroupLayout(jPanel108);
        jPanel108.setLayout(jPanel108Layout);
        jPanel108Layout.setHorizontalGroup(
            jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel108Layout.createSequentialGroup()
                .addGroup(jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel108Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel189, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel108Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel108Layout.setVerticalGroup(
            jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel108Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel189)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelSoVanBan3Layout = new javax.swing.GroupLayout(panelSoVanBan3);
        panelSoVanBan3.setLayout(panelSoVanBan3Layout);
        panelSoVanBan3Layout.setHorizontalGroup(
            panelSoVanBan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSoVanBan3Layout.createSequentialGroup()
                .addGroup(panelSoVanBan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelSoVanBan3Layout.setVerticalGroup(
            panelSoVanBan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSoVanBan3Layout.createSequentialGroup()
                .addComponent(jPanel107, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel108, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 68, Short.MAX_VALUE))
        );

        pnChuaQuanLyVanBan.addTab("Sổ văn bản", panelSoVanBan3);

        btnThemLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/insert.png"))); // NOI18N
        btnThemLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiActionPerformed(evt);
            }
        });

        btnTaiLaiLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/reset.png"))); // NOI18N
        btnTaiLaiLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaiLaiLoaiMouseClicked(evt);
            }
        });
        btnTaiLaiLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiLaiLoaiActionPerformed(evt);
            }
        });

        btnXoaLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/button.png"))); // NOI18N
        btnXoaLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLoaiActionPerformed(evt);
            }
        });

        btnLuuLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/save.png"))); // NOI18N
        btnLuuLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuLoaiMouseClicked(evt);
            }
        });
        btnLuuLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuLoaiActionPerformed(evt);
            }
        });

        btnXuatExcelLoaiVanBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/excel.png"))); // NOI18N
        btnXuatExcelLoaiVanBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelLoaiVanBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel109Layout = new javax.swing.GroupLayout(jPanel109);
        jPanel109.setLayout(jPanel109Layout);
        jPanel109Layout.setHorizontalGroup(
            jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel109Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnThemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnLuuLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXoaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTaiLaiLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXuatExcelLoaiVanBan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel109Layout.setVerticalGroup(
            jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel109Layout.createSequentialGroup()
                .addGroup(jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiLaiLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatExcelLoaiVanBan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel110.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Danh sách loại văn bản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        tblLoaiVanBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã loại", "Loại văn bản", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane29.setViewportView(tblLoaiVanBan);

        javax.swing.GroupLayout jPanel110Layout = new javax.swing.GroupLayout(jPanel110);
        jPanel110.setLayout(jPanel110Layout);
        jPanel110Layout.setHorizontalGroup(
            jPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel110Layout.createSequentialGroup()
                .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 1093, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel110Layout.setVerticalGroup(
            jPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel110Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelLoaiVanBan3Layout = new javax.swing.GroupLayout(panelLoaiVanBan3);
        panelLoaiVanBan3.setLayout(panelLoaiVanBan3Layout);
        panelLoaiVanBan3Layout.setHorizontalGroup(
            panelLoaiVanBan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoaiVanBan3Layout.createSequentialGroup()
                .addGroup(panelLoaiVanBan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelLoaiVanBan3Layout.createSequentialGroup()
                        .addComponent(jPanel110, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLoaiVanBan3Layout.setVerticalGroup(
            panelLoaiVanBan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoaiVanBan3Layout.createSequentialGroup()
                .addComponent(jPanel109, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel110, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnChuaQuanLyVanBan.addTab("Loại văn bản", panelLoaiVanBan3);

        btnThemNoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/insert.png"))); // NOI18N
        btnThemNoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNoiActionPerformed(evt);
            }
        });

        btnTaiLaiNoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/reset.png"))); // NOI18N
        btnTaiLaiNoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaiLaiNoiMouseClicked(evt);
            }
        });
        btnTaiLaiNoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiLaiNoiActionPerformed(evt);
            }
        });

        btnXoaNoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/button.png"))); // NOI18N
        btnXoaNoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNoiActionPerformed(evt);
            }
        });

        btnLuuNoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/save.png"))); // NOI18N
        btnLuuNoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuNoiMouseClicked(evt);
            }
        });
        btnLuuNoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuNoiActionPerformed(evt);
            }
        });

        btnXuatExcelNoiBanHanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/excel.png"))); // NOI18N
        btnXuatExcelNoiBanHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelNoiBanHanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel111Layout = new javax.swing.GroupLayout(jPanel111);
        jPanel111.setLayout(jPanel111Layout);
        jPanel111Layout.setHorizontalGroup(
            jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel111Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnThemNoi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnLuuNoi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXoaNoi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTaiLaiNoi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXuatExcelNoiBanHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(939, Short.MAX_VALUE))
        );
        jPanel111Layout.setVerticalGroup(
            jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel111Layout.createSequentialGroup()
                .addGroup(jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiLaiNoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaNoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuNoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatExcelNoiBanHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel112.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Danh sách nơi ban hành", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        tblNoiBanHanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Nơi ban hành", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane30.setViewportView(tblNoiBanHanh);

        javax.swing.GroupLayout jPanel112Layout = new javax.swing.GroupLayout(jPanel112);
        jPanel112.setLayout(jPanel112Layout);
        jPanel112Layout.setHorizontalGroup(
            jPanel112Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel112Layout.createSequentialGroup()
                .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 47, Short.MAX_VALUE))
        );
        jPanel112Layout.setVerticalGroup(
            jPanel112Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel112Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelNoiBanHanh3Layout = new javax.swing.GroupLayout(panelNoiBanHanh3);
        panelNoiBanHanh3.setLayout(panelNoiBanHanh3Layout);
        panelNoiBanHanh3Layout.setHorizontalGroup(
            panelNoiBanHanh3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoiBanHanh3Layout.createSequentialGroup()
                .addGroup(panelNoiBanHanh3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelNoiBanHanh3Layout.createSequentialGroup()
                        .addComponent(jPanel112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelNoiBanHanh3Layout.setVerticalGroup(
            panelNoiBanHanh3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNoiBanHanh3Layout.createSequentialGroup()
                .addComponent(jPanel111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );

        pnChuaQuanLyVanBan.addTab("Nơi ban hành", panelNoiBanHanh3);

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

        btnXuatExcelVanBanDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/excel.png"))); // NOI18N
        btnXuatExcelVanBanDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelVanBanDenActionPerformed(evt);
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
                .addGap(0, 0, 0)
                .addComponent(btnXuatExcelVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(914, Short.MAX_VALUE))
        );
        jPanel113Layout.setVerticalGroup(
            jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel113Layout.createSequentialGroup()
                .addGroup(jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiLaiVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatExcelVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
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
                .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel192, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel190, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel191)
                    .addComponent(jLabel193))
                .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel114Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(cboSoVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel114Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboNoiBanHanhDen, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateNgayBanHanhDen, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoKyHieuDen, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
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

        txtTrichYeuDen.setColumns(20);
        txtTrichYeuDen.setRows(5);
        jScrollPane11.setViewportView(txtTrichYeuDen);

        javax.swing.GroupLayout jPanel116Layout = new javax.swing.GroupLayout(jPanel116);
        jPanel116.setLayout(jPanel116Layout);
        jPanel116Layout.setHorizontalGroup(
            jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel116Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel116Layout.setVerticalGroup(
            jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel116Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel117.setBackground(new java.awt.Color(255, 255, 255));
        jPanel117.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Nội Dung", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        txtNoiDungDen.setColumns(20);
        txtNoiDungDen.setRows(5);
        jScrollPane12.setViewportView(txtNoiDungDen);

        javax.swing.GroupLayout jPanel117Layout = new javax.swing.GroupLayout(jPanel117);
        jPanel117.setLayout(jPanel117Layout);
        jPanel117Layout.setHorizontalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel117Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel117Layout.setVerticalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel117Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
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
                .addComponent(txtDinhKemFileDen, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDinhKemFile4)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel119Layout.setVerticalGroup(
            jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel119Layout.createSequentialGroup()
                .addComponent(txtDinhKemFileDen)
                .addContainerGap())
            .addGroup(jPanel119Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDinhKemFile4)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelVanBanDen3Layout = new javax.swing.GroupLayout(panelVanBanDen3);
        panelVanBanDen3.setLayout(panelVanBanDen3Layout);
        panelVanBanDen3Layout.setHorizontalGroup(
            panelVanBanDen3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVanBanDen3Layout.createSequentialGroup()
                .addGroup(panelVanBanDen3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelVanBanDen3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel116, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel114, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel117, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 31, Short.MAX_VALUE))
        );
        panelVanBanDen3Layout.setVerticalGroup(
            panelVanBanDen3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVanBanDen3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel116, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel117, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pnChuaQuanLyVanBan.addTab("Văn bản đến", panelVanBanDen3);

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
                .addContainerGap(945, Short.MAX_VALUE))
        );
        jPanel120Layout.setVerticalGroup(
            jPanel120Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel120Layout.createSequentialGroup()
                .addGroup(jPanel120Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiLaiVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatExcelVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
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
                            .addComponent(jLabel213, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        txtTrichYeuDi.setColumns(20);
        txtTrichYeuDi.setRows(5);
        jScrollPane9.setViewportView(txtTrichYeuDi);

        javax.swing.GroupLayout jPanel123Layout = new javax.swing.GroupLayout(jPanel123);
        jPanel123.setLayout(jPanel123Layout);
        jPanel123Layout.setHorizontalGroup(
            jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel123Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel123Layout.setVerticalGroup(
            jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel123Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel124.setBackground(new java.awt.Color(255, 255, 255));
        jPanel124.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Nội Dung", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        txtNoiDungDi.setColumns(20);
        txtNoiDungDi.setRows(5);
        jScrollPane10.setViewportView(txtNoiDungDi);

        javax.swing.GroupLayout jPanel124Layout = new javax.swing.GroupLayout(jPanel124);
        jPanel124.setLayout(jPanel124Layout);
        jPanel124Layout.setHorizontalGroup(
            jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel124Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1063, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel124Layout.setVerticalGroup(
            jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel124Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
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
                .addGap(67, 67, 67))
        );
        jPanel125Layout.setVerticalGroup(
            jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel125Layout.createSequentialGroup()
                .addGroup(jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDinhKemFileDi, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(btnDinhKemFileDi3))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelVanBanDi3Layout = new javax.swing.GroupLayout(panelVanBanDi3);
        panelVanBanDi3.setLayout(panelVanBanDi3Layout);
        panelVanBanDi3Layout.setHorizontalGroup(
            panelVanBanDi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVanBanDi3Layout.createSequentialGroup()
                .addGroup(panelVanBanDi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel120, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelVanBanDi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel125, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel124, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel123, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel121, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pnChuaQuanLyVanBan.addTab("Văn bản đi", panelVanBanDi3);

        btnThemDSDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/insert.png"))); // NOI18N
        btnThemDSDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDSDenActionPerformed(evt);
            }
        });

        btnTaiLaiDSDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/reset.png"))); // NOI18N
        btnTaiLaiDSDen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaiLaiDSDenMouseClicked(evt);
            }
        });
        btnTaiLaiDSDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiLaiDSDenActionPerformed(evt);
            }
        });

        btnXoaDSDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/button.png"))); // NOI18N
        btnXoaDSDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDSDenActionPerformed(evt);
            }
        });

        btnLuuDSDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/save.png"))); // NOI18N
        btnLuuDSDen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuDSDenMouseClicked(evt);
            }
        });
        btnLuuDSDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuDSDenActionPerformed(evt);
            }
        });

        btnXuatExcelDSDen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/excel.png"))); // NOI18N
        btnXuatExcelDSDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelDSDenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel126Layout = new javax.swing.GroupLayout(jPanel126);
        jPanel126.setLayout(jPanel126Layout);
        jPanel126Layout.setHorizontalGroup(
            jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel126Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnThemDSDen, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnLuuDSDen, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXoaDSDen, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTaiLaiDSDen, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXuatExcelDSDen, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel126Layout.setVerticalGroup(
            jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel126Layout.createSequentialGroup()
                .addGroup(jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemDSDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiLaiDSDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaDSDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuDSDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatExcelDSDen, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel127.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Chọn thời gian lọc thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        cboNamDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamDenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel127Layout = new javax.swing.GroupLayout(jPanel127);
        jPanel127.setLayout(jPanel127Layout);
        jPanel127Layout.setHorizontalGroup(
            jPanel127Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel127Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboNamDen, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel127Layout.setVerticalGroup(
            jPanel127Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel127Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboNamDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel128.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Danh sách văn bản đến", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        tblDanhSachVanBanDen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Sổ văn bản", "Loại văn bản", "Sổ ký hiệu", "Sổ đến", "Ngày ban hành", "Ngày đến", "Đơn vị gửi", "Trích yếu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachVanBanDen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachVanBanDenMouseClicked(evt);
            }
        });
        jScrollPane31.setViewportView(tblDanhSachVanBanDen);

        javax.swing.GroupLayout jPanel128Layout = new javax.swing.GroupLayout(jPanel128);
        jPanel128.setLayout(jPanel128Layout);
        jPanel128Layout.setHorizontalGroup(
            jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel128Layout.createSequentialGroup()
                .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 1149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel128Layout.setVerticalGroup(
            jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel128Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelDanhSachDen3Layout = new javax.swing.GroupLayout(panelDanhSachDen3);
        panelDanhSachDen3.setLayout(panelDanhSachDen3Layout);
        panelDanhSachDen3Layout.setHorizontalGroup(
            panelDanhSachDen3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDanhSachDen3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDanhSachDen3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDanhSachDen3Layout.createSequentialGroup()
                        .addComponent(jPanel126, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDanhSachDen3Layout.createSequentialGroup()
                        .addComponent(jPanel127, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDanhSachDen3Layout.createSequentialGroup()
                        .addComponent(jPanel128, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelDanhSachDen3Layout.setVerticalGroup(
            panelDanhSachDen3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDanhSachDen3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel126, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel127, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel128, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        pnChuaQuanLyVanBan.addTab("Danh sách văn bản đến", panelDanhSachDen3);

        btnThemDanhSachDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/insert.png"))); // NOI18N
        btnThemDanhSachDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDanhSachDiActionPerformed(evt);
            }
        });

        btnTaiLaiDSDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/reset.png"))); // NOI18N
        btnTaiLaiDSDi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaiLaiDSDiMouseClicked(evt);
            }
        });
        btnTaiLaiDSDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiLaiDSDiActionPerformed(evt);
            }
        });

        btnXoaDSDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/button.png"))); // NOI18N
        btnXoaDSDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDSDiActionPerformed(evt);
            }
        });

        btnLuuDSDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/save.png"))); // NOI18N
        btnLuuDSDi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuDSDiMouseClicked(evt);
            }
        });
        btnLuuDSDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuDSDiActionPerformed(evt);
            }
        });

        btnXuatExcelDSDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/excel.png"))); // NOI18N
        btnXuatExcelDSDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelDSDiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel129Layout = new javax.swing.GroupLayout(jPanel129);
        jPanel129.setLayout(jPanel129Layout);
        jPanel129Layout.setHorizontalGroup(
            jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel129Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnThemDanhSachDi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnLuuDSDi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXoaDSDi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTaiLaiDSDi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXuatExcelDSDi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel129Layout.setVerticalGroup(
            jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel129Layout.createSequentialGroup()
                .addGroup(jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemDanhSachDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiLaiDSDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaDSDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuDSDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatExcelDSDi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel130.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Chọn thời gian lọc thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        cboNamDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamDiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel130Layout = new javax.swing.GroupLayout(jPanel130);
        jPanel130.setLayout(jPanel130Layout);
        jPanel130Layout.setHorizontalGroup(
            jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel130Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboNamDi, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel130Layout.setVerticalGroup(
            jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel130Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboNamDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel131.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Danh sách văn bản đi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 204, 204))); // NOI18N

        tblDanhSachVanBanDi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Sổ văn bản", "Loại văn bản", "Sổ ký hiệu", "Số đi", "Ngày ban hành", "Nơi nhận", "Trích yếu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachVanBanDi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachVanBanDiMouseClicked(evt);
            }
        });
        jScrollPane32.setViewportView(tblDanhSachVanBanDi);

        javax.swing.GroupLayout jPanel131Layout = new javax.swing.GroupLayout(jPanel131);
        jPanel131.setLayout(jPanel131Layout);
        jPanel131Layout.setHorizontalGroup(
            jPanel131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel131Layout.createSequentialGroup()
                .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 1164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel131Layout.setVerticalGroup(
            jPanel131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel131Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelDanhSachDi3Layout = new javax.swing.GroupLayout(panelDanhSachDi3);
        panelDanhSachDi3.setLayout(panelDanhSachDi3Layout);
        panelDanhSachDi3Layout.setHorizontalGroup(
            panelDanhSachDi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDanhSachDi3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelDanhSachDi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDanhSachDi3Layout.createSequentialGroup()
                        .addComponent(jPanel129, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDanhSachDi3Layout.createSequentialGroup()
                        .addComponent(jPanel130, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel131, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelDanhSachDi3Layout.setVerticalGroup(
            panelDanhSachDi3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDanhSachDi3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel129, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel130, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel131, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        pnChuaQuanLyVanBan.addTab("Danh sách văn bản đi", panelDanhSachDi3);

        pnQuanLyVanBan.add(pnChuaQuanLyVanBan, "card4");

        pnChinh.add(pnQuanLyVanBan, "card2");

        pnTimKiemThongKe.setBackground(new java.awt.Color(255, 255, 255));
        pnTimKiemThongKe.setLayout(new java.awt.CardLayout());

        pnChuaTimKiemThongKe.setBackground(new java.awt.Color(255, 255, 255));
        pnChuaTimKiemThongKe.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                pnChuaTimKiemThongKeAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        ThongKeVanBanDi.setBackground(new java.awt.Color(255, 255, 255));

        btnTimKiemVanBanDi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKiemVanBanDi.setForeground(new java.awt.Color(51, 0, 153));
        btnTimKiemVanBanDi.setText("Tìm kiếm");
        btnTimKiemVanBanDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemVanBanDiActionPerformed(evt);
            }
        });

        btnXemChiTiet3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXemChiTiet3.setForeground(new java.awt.Color(51, 0, 153));
        btnXemChiTiet3.setText("Xem chi tiết");
        btnXemChiTiet3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTiet3ActionPerformed(evt);
            }
        });

        btnXuatFileExcel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatFileExcel3.setForeground(new java.awt.Color(51, 0, 153));
        btnXuatFileExcel3.setText("Xuất file excel");
        btnXuatFileExcel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcel3ActionPerformed(evt);
            }
        });

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "  DANH SÁCH VĂN BẢN ĐI   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 255))); // NOI18N

        tblTimKiemDanhSachVanBanDi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Sổ văn bản", "Loại văn bản", "Sổ ký hiệu", "Số đi", "Ngày ban hành", "Nơi nhận", "Trích yếu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane33.setViewportView(tblTimKiemDanhSachVanBanDi);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane33, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 39, Short.MAX_VALUE))
        );

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(51, 0, 153));
        jLabel111.setText("THỐNG KÊ VĂN BẢN ĐI");

        jPanel37.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 153), null), "  TÌM KIẾM VĂN BẢN ĐI    ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 255))); // NOI18N
        jPanel37.setPreferredSize(new java.awt.Dimension(900, 169));

        jLabel98.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(51, 0, 153));
        jLabel98.setText("Từ ngày");

        jLabel103.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(51, 0, 153));
        jLabel103.setText("đến ngày");

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(51, 0, 153));
        jLabel127.setText("Sổ văn bản");

        jLabel128.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel128.setForeground(new java.awt.Color(51, 0, 153));
        jLabel128.setText("Loại văn bản");

        jLabel129.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(51, 0, 153));
        jLabel129.setText("Số ký hiệu");

        jLabel130.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(51, 0, 153));
        jLabel130.setText("Nơi nhận");

        jLabel131.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel131.setForeground(new java.awt.Color(51, 0, 153));
        jLabel131.setText("Tìm kiếm theo từ khóa:");

        txtTimKiemDi.setColumns(20);
        txtTimKiemDi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTimKiemDi.setForeground(new java.awt.Color(51, 0, 153));
        txtTimKiemDi.setRows(5);
        jScrollPane17.setViewportView(txtTimKiemDi);

        dateTimKiemMinDi.setDateFormatString("dd/MM/yyyy");

        dateTimKiemMaxDi.setDateFormatString("dd/MM/yyyy");

        ThoiGianTimKiemDi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Tuần này", "Tháng này", "Năm nay", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12", "Năm trước" }));
        ThoiGianTimKiemDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThoiGianTimKiemDiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel37Layout.createSequentialGroup()
                                .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel127)
                                .addGap(32, 32, 32)))
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboTimKiemSoVanBanDi, 0, 180, Short.MAX_VALUE)
                            .addComponent(cboTimKiemLoaiVanBanDi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel129)
                            .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(ThoiGianTimKiemDi, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateTimKiemMinDi, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNoiNhanTimKiemDi, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiemSoKyHieuDi, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel103)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateTimKiemMaxDi, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel98)
                            .addComponent(ThoiGianTimKiemDi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel103)
                            .addComponent(dateTimKiemMinDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateTimKiemMaxDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel131, javax.swing.GroupLayout.Alignment.LEADING))))
                .addGap(6, 6, 6)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel37Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboTimKiemSoVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel127)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel129)
                                .addComponent(txtTimKiemSoKyHieuDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel128)
                                .addComponent(cboTimKiemLoaiVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel130)
                                .addComponent(txtNoiNhanTimKiemDi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ThongKeVanBanDiLayout = new javax.swing.GroupLayout(ThongKeVanBanDi);
        ThongKeVanBanDi.setLayout(ThongKeVanBanDiLayout);
        ThongKeVanBanDiLayout.setHorizontalGroup(
            ThongKeVanBanDiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKeVanBanDiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ThongKeVanBanDiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ThongKeVanBanDiLayout.createSequentialGroup()
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ThongKeVanBanDiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXuatFileExcel3)
                            .addComponent(btnXemChiTiet3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiemVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ThongKeVanBanDiLayout.createSequentialGroup()
                .addGap(398, 398, 398)
                .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ThongKeVanBanDiLayout.setVerticalGroup(
            ThongKeVanBanDiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKeVanBanDiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel111)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongKeVanBanDiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongKeVanBanDiLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnTimKiemVanBanDi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXemChiTiet3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXuatFileExcel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnChuaTimKiemThongKe.addTab("Thống kê văn bản đi", ThongKeVanBanDi);

        ThongKeVanBanDen.setBackground(new java.awt.Color(255, 255, 255));

        btnXemChiTiet1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXemChiTiet1.setForeground(new java.awt.Color(51, 0, 153));
        btnXemChiTiet1.setText("Xem chi tiết");
        btnXemChiTiet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTiet1ActionPerformed(evt);
            }
        });

        btnXuatFileExcel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatFileExcel1.setForeground(new java.awt.Color(51, 0, 153));
        btnXuatFileExcel1.setText("Xuất file excel");
        btnXuatFileExcel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcel1ActionPerformed(evt);
            }
        });

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "  DANH SÁCH VĂN BẢN ĐẾN   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 255))); // NOI18N

        tblTimKiemDanhSachVanBanDen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Sổ văn bản", "Loại văn bản", "Sổ ký hiệu", "Sổ đến", "Ngày ban hành", "Ngày đến", "Đơn vị gửi", "Trích yếu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane34.setViewportView(tblTimKiemDanhSachVanBanDen);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane34, javax.swing.GroupLayout.DEFAULT_SIZE, 1071, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane34, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel94.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(51, 0, 153));
        jLabel94.setText("THỐNG KÊ VĂN BẢN ĐẾN");

        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 153), null), "  TÌM KIẾM VĂN BẢN ĐẾN    ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 255))); // NOI18N
        jPanel34.setPreferredSize(new java.awt.Dimension(900, 169));

        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(51, 0, 153));
        jLabel95.setText("Từ ngày");

        jLabel100.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(51, 0, 153));
        jLabel100.setText("đến ngày");

        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(51, 0, 153));
        jLabel112.setText("Sổ văn bản");

        jLabel113.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(51, 0, 153));
        jLabel113.setText("Loại văn bản");

        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(51, 0, 153));
        jLabel114.setText("Số ký hiệu");

        jLabel115.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(51, 0, 153));
        jLabel115.setText("Nơi ban hành");

        jLabel116.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(51, 0, 153));
        jLabel116.setText("Tìm kiếm theo từ khóa:");

        txtTimKiemDen.setColumns(20);
        txtTimKiemDen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTimKiemDen.setForeground(new java.awt.Color(51, 0, 153));
        txtTimKiemDen.setRows(5);
        jScrollPane14.setViewportView(txtTimKiemDen);

        cboTimKiemSoVanBanDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimKiemSoVanBanDenActionPerformed(evt);
            }
        });

        ThoiGianTimKiemDen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Tuần này", "Tháng này", "Năm nay", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12", "Năm trước" }));
        ThoiGianTimKiemDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThoiGianTimKiemDenActionPerformed(evt);
            }
        });

        dateTimKiemMinDen.setDateFormatString("dd/MM/yyyy");

        dateTimKiemMaxDen.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ThoiGianTimKiemDen, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dateTimKiemMinDen, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel100)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateTimKiemMaxDen, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel112)
                                .addGap(32, 32, 32)))
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboTimKiemSoVanBanDen, 0, 151, Short.MAX_VALUE)
                            .addComponent(cboTimKiemLoaiVanBanDen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNoiBanHanhTimKiemDen, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiemSoKyHieuDen, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)))
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ThoiGianTimKiemDen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel95))
                    .addComponent(jLabel116)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel100)
                            .addComponent(dateTimKiemMinDen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateTimKiemMaxDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboTimKiemSoVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel112))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel113)
                                    .addComponent(cboTimKiemLoaiVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel114)
                                    .addComponent(txtTimKiemSoKyHieuDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNoiBanHanhTimKiemDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel115)))))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        btnTimKiemVanBanDen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKiemVanBanDen.setForeground(new java.awt.Color(51, 0, 153));
        btnTimKiemVanBanDen.setText("Tìm kiếm");
        btnTimKiemVanBanDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemVanBanDenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThongKeVanBanDenLayout = new javax.swing.GroupLayout(ThongKeVanBanDen);
        ThongKeVanBanDen.setLayout(ThongKeVanBanDenLayout);
        ThongKeVanBanDenLayout.setHorizontalGroup(
            ThongKeVanBanDenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKeVanBanDenLayout.createSequentialGroup()
                .addGroup(ThongKeVanBanDenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongKeVanBanDenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ThongKeVanBanDenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ThongKeVanBanDenLayout.createSequentialGroup()
                                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(ThongKeVanBanDenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnXuatFileExcel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnXemChiTiet1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTimKiemVanBanDen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ThongKeVanBanDenLayout.createSequentialGroup()
                        .addGap(415, 415, 415)
                        .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(284, Short.MAX_VALUE))
        );
        ThongKeVanBanDenLayout.setVerticalGroup(
            ThongKeVanBanDenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKeVanBanDenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel94)
                .addGap(0, 0, 0)
                .addGroup(ThongKeVanBanDenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongKeVanBanDenLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnTimKiemVanBanDen, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXemChiTiet1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXuatFileExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnChuaTimKiemThongKe.addTab("Thống kê văn bản đến", ThongKeVanBanDen);

        ThongKeVanBanNoiBo.setBackground(new java.awt.Color(255, 255, 255));
        ThongKeVanBanNoiBo.setForeground(new java.awt.Color(51, 0, 153));

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 153), null), "  TÌM KIẾM VĂN BẢN NỘI BỘ    ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 255))); // NOI18N
        jPanel22.setPreferredSize(new java.awt.Dimension(900, 184));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(51, 0, 153));
        jLabel63.setText("Từ ngày");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(51, 0, 153));
        jLabel65.setText("đến ngày");

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(51, 0, 153));
        jLabel76.setText("Loại ban hành");

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(51, 0, 153));
        jLabel77.setText("Phòng ban hành");

        jLabel78.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(51, 0, 153));
        jLabel78.setText("Phòng ban nhận");

        jLabel79.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(51, 0, 153));
        jLabel79.setText("Số ký hiệu");

        jLabel80.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(51, 0, 153));
        jLabel80.setText("Tên văn bản");

        jLabel81.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(51, 0, 153));
        jLabel81.setText("Trích yếu");

        nhapTrichYeu.setColumns(20);
        nhapTrichYeu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nhapTrichYeu.setForeground(new java.awt.Color(51, 0, 153));
        nhapTrichYeu.setRows(5);
        jScrollPane6.setViewportView(nhapTrichYeu);

        jLabel82.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(51, 0, 153));
        jLabel82.setText("Chọn thời gian ");

        ThoiGianTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Tuần này", "Tháng này", "Năm nay", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12", "Năm trước" }));
        ThoiGianTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThoiGianTimKiemActionPerformed(evt);
            }
        });

        dateTimKiemNoiBoMin.setDateFormatString("dd/MM/yyyy");

        dateTimKiemNoiBoMax.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel77))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(chonCmbLoaiBanHanh, 0, 170, Short.MAX_VALUE)
                                    .addComponent(chonCmbPhongBanHanh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(ThoiGianTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ChonCmbPhongBanNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nhapTenVanBan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(nhapSoKyHieu))
                        .addComponent(dateTimKiemNoiBoMin, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateTimKiemNoiBoMax, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel82)
                        .addComponent(ThoiGianTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel63))
                    .addComponent(jLabel65)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dateTimKiemNoiBoMax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dateTimKiemNoiBoMin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nhapSoKyHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chonCmbLoaiBanHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel79)
                                .addComponent(jLabel81))
                            .addComponent(jLabel76))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel80)
                                .addComponent(nhapTenVanBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel77)
                            .addComponent(chonCmbPhongBanHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel78)
                            .addComponent(ChonCmbPhongBanNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(51, 0, 153));
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnXemChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXemChiTiet.setForeground(new java.awt.Color(51, 0, 153));
        btnXemChiTiet.setText("Xem chi tiết");
        btnXemChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietActionPerformed(evt);
            }
        });

        btnXuatFileExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatFileExcel.setForeground(new java.awt.Color(51, 0, 153));
        btnXuatFileExcel.setText("Xuất file excel");
        btnXuatFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcelActionPerformed(evt);
            }
        });

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "  DANH SÁCH VĂN BẢN NỘI BỘ   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(153, 0, 255))); // NOI18N

        tbVanBanNoiBo1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                " Số ký hiệu", "Tên văn bản", "Ngày ban hành", "Loại ban hành", "Phòng ban hành", "Phòng ban nhận", "Người nhận", "Người ký", "Người duyệt", "Trích yếu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbVanBanNoiBo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVanBanNoiBo1MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbVanBanNoiBo1);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1061, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
        );

        jLabel83.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(51, 0, 153));
        jLabel83.setText("THỐNG KÊ VĂN BẢN NỘI BỘ");

        btnInBaoCao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInBaoCao.setForeground(new java.awt.Color(51, 0, 153));
        btnInBaoCao.setText("In báo cáo");
        btnInBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInBaoCaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThongKeVanBanNoiBoLayout = new javax.swing.GroupLayout(ThongKeVanBanNoiBo);
        ThongKeVanBanNoiBo.setLayout(ThongKeVanBanNoiBoLayout);
        ThongKeVanBanNoiBoLayout.setHorizontalGroup(
            ThongKeVanBanNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKeVanBanNoiBoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ThongKeVanBanNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongKeVanBanNoiBoLayout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ThongKeVanBanNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXemChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXuatFileExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnInBaoCao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongKeVanBanNoiBoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(418, 418, 418))
                    .addGroup(ThongKeVanBanNoiBoLayout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 282, Short.MAX_VALUE))))
        );
        ThongKeVanBanNoiBoLayout.setVerticalGroup(
            ThongKeVanBanNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKeVanBanNoiBoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel83)
                .addGap(2, 2, 2)
                .addGroup(ThongKeVanBanNoiBoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ThongKeVanBanNoiBoLayout.createSequentialGroup()
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXuatFileExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btnInBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 72, Short.MAX_VALUE))
        );

        pnChuaTimKiemThongKe.addTab("Thống kê văn bản nội bộ", ThongKeVanBanNoiBo);

        pnTimKiemThongKe.add(pnChuaTimKiemThongKe, "card2");

        pnChinh.add(pnTimKiemThongKe, "card2");

        jLabel85.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel85.setText("QUẢN LÝ NGƯỜI DÙNG");

        btnThem2.setText("Thêm");
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa2.setText("Xóa");
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
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
        jScrollPane8.setViewportView(tblNguoiDung);

        jLabel86.setText("Họ và tên:");

        jLabel87.setText("Email:");

        jLabel88.setText("Số điện thoại:");

        jLabel89.setText("Tên tài khoản:");

        jLabel90.setText("Mật khẩu: ");

        javax.swing.GroupLayout pnNguoiDungLayout = new javax.swing.GroupLayout(pnNguoiDung);
        pnNguoiDung.setLayout(pnNguoiDungLayout);
        pnNguoiDungLayout.setHorizontalGroup(
            pnNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNguoiDungLayout.createSequentialGroup()
                .addGroup(pnNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnNguoiDungLayout.createSequentialGroup()
                        .addGap(355, 355, 355)
                        .addGroup(pnNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnNguoiDungLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(pnNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnNguoiDungLayout.createSequentialGroup()
                                .addComponent(jLabel88)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSDT))
                            .addGroup(pnNguoiDungLayout.createSequentialGroup()
                                .addGroup(pnNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel86)
                                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(pnNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                        .addComponent(btnThem2)))
                .addGroup(pnNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnNguoiDungLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pwMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnNguoiDungLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnCapNhat)
                        .addGap(34, 34, 34)
                        .addComponent(btnXoa2)))
                .addContainerGap(457, Short.MAX_VALUE))
            .addGroup(pnNguoiDungLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addGap(18, 18, 18))
            .addGroup(pnNguoiDungLayout.createSequentialGroup()
                .addGap(413, 413, 413)
                .addComponent(jLabel85)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnNguoiDungLayout.setVerticalGroup(
            pnNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNguoiDungLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel85)
                .addGap(18, 18, 18)
                .addGroup(pnNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89)
                    .addComponent(txtTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(pnNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel90)
                    .addComponent(pwMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(pnNguoiDungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat)
                    .addComponent(btnXoa2)
                    .addComponent(btnThem2))
                .addGap(49, 49, 49)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
        );

        pnChinh.add(pnNguoiDung, "card5");

        jLabel91.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel91.setText("THIẾT LẬP THÔNG TIN ");

        jLabel92.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel92.setText("Người ký: ");

        txtNguoiKy1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel93.setText("Người duyệt: ");

        txtNguoiDuyet1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel96.setText("Người gửi: ");

        txtNguoiGui.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel97.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel97.setText("Người nhận: ");

        txtNguoiNhan1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel99.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel99.setText("Nơi ban hành: ");

        btnLuu2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLuu2.setText("Lưu");
        btnLuu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuu2ActionPerformed(evt);
            }
        });

        txtNoiBanHanh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout pnKyLayout = new javax.swing.GroupLayout(pnKy);
        pnKy.setLayout(pnKyLayout);
        pnKyLayout.setHorizontalGroup(
            pnKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKyLayout.createSequentialGroup()
                .addContainerGap(371, Short.MAX_VALUE)
                .addGroup(pnKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKyLayout.createSequentialGroup()
                        .addGroup(pnKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel96)
                            .addComponent(jLabel99)
                            .addGroup(pnKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel92, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel97, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(pnKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNguoiKy1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnKyLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(pnKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNguoiDuyet1)
                                    .addComponent(txtNguoiGui)))
                            .addGroup(pnKyLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtNguoiNhan1))
                            .addGroup(pnKyLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(txtNoiBanHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(346, 346, 346))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKyLayout.createSequentialGroup()
                        .addComponent(jLabel91)
                        .addGap(390, 390, 390))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKyLayout.createSequentialGroup()
                        .addComponent(btnLuu2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(395, 395, 395))))
        );
        pnKyLayout.setVerticalGroup(
            pnKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel91)
                .addGap(38, 38, 38)
                .addGroup(pnKyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnKyLayout.createSequentialGroup()
                        .addComponent(txtNguoiKy1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txtNguoiDuyet1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(txtNguoiGui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txtNguoiNhan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txtNoiBanHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnKyLayout.createSequentialGroup()
                        .addComponent(jLabel92)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel93)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel96)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel97)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel99)))
                .addGap(61, 61, 61)
                .addComponent(btnLuu2)
                .addContainerGap(284, Short.MAX_VALUE))
        );

        pnChinh.add(pnKy, "card6");

        pnChuaThan.add(pnChinh, "card2");

        javax.swing.GroupLayout pnThanLayout = new javax.swing.GroupLayout(pnThan);
        pnThan.setLayout(pnThanLayout);
        pnThanLayout.setHorizontalGroup(
            pnThanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnChuaThan, javax.swing.GroupLayout.DEFAULT_SIZE, 1158, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnThanLayout.setVerticalGroup(
            pnThanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnChuaThan, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ThanhMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnThan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(ThanhMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnThan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton63.setText("jButton63");

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jButton65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuanLyCongVan/Images/book.png"))); // NOI18N
        jButton65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton65MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton65MouseExited(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("ỨNG DỤNG QUẢN LÝ VĂN BẢN - CÔNG VĂN");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jButton65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 537, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(604, 604, 604))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(0, 12, Short.MAX_VALUE))
            .addComponent(jButton65, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void KhoiTao()
    {
        btnXoaSo.setToolTipText("Xóa");
        btnLuuSo.setToolTipText("Lưu");
        btnThemSo.setToolTipText("Thêm mới");
        btnTaiLaiSo.setToolTipText("Tải lại");
        btnXuatExcelSoVanBan.setToolTipText("Xuất file excel");
        btnXoaSo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), "xoa");
        btnXoaSo.getActionMap().put("xoa", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnXoaSoActionPerformed(e);
            }
        });
        btnThemSo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "them");
        btnThemSo.getActionMap().put("them", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnThemSoActionPerformed(e); 
            }
        });
        btnLuuSo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "them");
        btnLuuSo.getActionMap().put("luu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               btnLuuSoActionPerformed(e);
            }
        });
        btnTaiLaiSo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "tailai");
        btnTaiLaiSo.getActionMap().put("tailai", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnTaiLaiSoActionPerformed(e); // Thực hiện hành động khi nhấn F5
            }
        });
        
        
        btnXoaLoai.setToolTipText("Xóa");
        btnLuuLoai.setToolTipText("Lưu");
        btnThemLoai.setToolTipText("Thêm mới");
        btnTaiLaiLoai.setToolTipText("Tải lại");
        btnXuatExcelLoaiVanBan.setToolTipText("Xuất file excel");
        btnXoaLoai.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), "xoa");
        btnXoaLoai.getActionMap().put("xoa", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnXoaLoaiActionPerformed(e);
            }
        });
        btnThemLoai.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "them");
        btnThemLoai.getActionMap().put("them", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnThemLoaiActionPerformed(e); 
            }
        });
        btnLuuLoai.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "them");
        btnLuuLoai.getActionMap().put("luu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               btnLuuLoaiActionPerformed(e);
            }
        });
        btnTaiLaiLoai.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "tailai");
        btnTaiLaiLoai.getActionMap().put("tailai", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnTaiLaiLoaiActionPerformed(e); // Thực hiện hành động khi nhấn F5
            }
        });
        
        
        btnXoaNoi.setToolTipText("Xóa");
        btnLuuNoi.setToolTipText("Lưu");
        btnThemNoi.setToolTipText("Thêm mới");
        btnTaiLaiNoi.setToolTipText("Tải lại");
        btnXuatExcelNoiBanHanh.setToolTipText("Xuất file excel");
        btnXoaNoi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), "xoa");
        btnXoaNoi.getActionMap().put("xoa", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnXoaNoiActionPerformed(e);
            }
        });
        btnThemLoai.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "them");
        btnThemNoi.getActionMap().put("them", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnThemNoiActionPerformed(e); 
            }
        });
        btnLuuNoi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "them");
        btnLuuNoi.getActionMap().put("luu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               btnLuuNoiActionPerformed(e);
            }
        });
        btnTaiLaiNoi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "tailai");
        btnTaiLaiNoi.getActionMap().put("tailai", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               btnTaiLaiNoiActionPerformed(e);  // Thực hiện hành động khi nhấn F5
            }
        });
        
        
        btnXoaVanBanDen.setToolTipText("Xóa");
        btnLuuVanBanDen.setToolTipText("Lưu");
        btnThemVanBanDen.setToolTipText("Thêm mới");
        btnTaiLaiVanBanDen.setToolTipText("Tải lại");
        btnXuatExcelVanBanDen.setToolTipText("Xuất file excel");
        btnXoaVanBanDen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), "xoa");
        
        btnThemVanBanDen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "them");
        btnThemVanBanDen.getActionMap().put("them", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnThemVanBanDenActionPerformed(e); 
            }
        });
        btnLuuVanBanDen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "them");
        btnLuuVanBanDen.getActionMap().put("luu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               btnLuuVanBanDenActionPerformed(e);
            }
        });

        
        btnXoaVanBanDi.setToolTipText("Xóa");
        btnLuuVanBanDi.setToolTipText("Lưu");
        btnThemVanBanDi.setToolTipText("Thêm mới");
        btnTaiLaiVanBanDi.setToolTipText("Tải lại");
        btnXuatExcelVanBanDi.setToolTipText("Xuất file excel");
        btnXoaVanBanDi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), "xoa");
        
        btnThemVanBanDi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "them");
        btnThemVanBanDi.getActionMap().put("them", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnThemVanBanDiActionPerformed(e); 
            }
        });
        btnLuuVanBanDi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "them");
        btnLuuVanBanDi.getActionMap().put("luu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               btnLuuVanBanDiActionPerformed(e);
            }
        });
        
        
        btnXoaDSDen.setToolTipText("Xóa");
        btnLuuDSDen.setToolTipText("Lưu");
        btnThemDSDen.setToolTipText("Thêm mới");
        btnTaiLaiDSDen.setToolTipText("Tải lại");
        btnXuatExcelDSDen.setToolTipText("Xuất file excel");
        btnXoaDSDen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), "xoa");
        btnXoaDSDen.getActionMap().put("xoa", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnXoaDSDenActionPerformed(e);
            }
        });
        btnThemDSDen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "them");
        btnThemDSDen.getActionMap().put("them", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnThemDSDenActionPerformed(e); 
            }
        });
        btnTaiLaiDSDen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "tailai");
        btnTaiLaiDSDen.getActionMap().put("tailai", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               btnTaiLaiDSDenActionPerformed(e);  // Thực hiện hành động khi nhấn F5
            }
        });
        
        
        
        btnXoaDSDi.setToolTipText("Xóa");
        btnLuuDSDi.setToolTipText("Lưu");
        btnThemDanhSachDi.setToolTipText("Thêm mới");
        btnTaiLaiDSDi.setToolTipText("Tải lại");
        btnXuatExcelDSDi.setToolTipText("Xuất file excel");
        btnXoaDSDi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), "xoa");
        btnXoaDSDi.getActionMap().put("xoa", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnXoaDSDiActionPerformed(e);
            }
        });
        btnThemDanhSachDi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "them");
        btnThemDanhSachDi.getActionMap().put("them", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnThemDanhSachDiActionPerformed(e); 
            }
        });
        btnLuuDSDi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "them");
        btnLuuDSDi.getActionMap().put("luu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               btnLuuDSDiActionPerformed(e);
            }
        });
        btnTaiLaiDSDi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "tailai");
        btnTaiLaiDSDi.getActionMap().put("tailai", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnTaiLaiDSDiActionPerformed(e); // Thực hiện hành động khi nhấn F5
            }
        });
        HienThiNamVaVanBan();
    }
    private void HienThiTimKiemTheoNgay(Date startDate,Date endDate)
    {
            VanBanDenController vbdenContrl = new VanBanDenController();

        // Lấy danh sách tìm kiếm từ controller
        dstimkiemden = vbdenContrl.HienThiVanBanDenTheoNgay(startDate, endDate);

        // Cập nhật dữ liệu lên bảng
        DefaultTableModel danhSachDen = (DefaultTableModel) tblTimKiemDanhSachVanBanDen.getModel();
        danhSachDen.setRowCount(0); // Xóa dữ liệu cũ trên bảng

        // Kiểm tra xem danh sách có dữ liệu hay không
        if (dstimkiemden.isEmpty()) {
            // Nếu không tìm thấy dữ liệu, hiển thị thông báo và giữ bảng trống
            //JOptionPane.showMessageDialog(null, "Không tìm thấy văn bản nào trong khoảng thời gian này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Lặp qua danh sách tìm kiếm để hiển thị dữ liệu
            for (int i = 0; i < dstimkiemden.size(); i++) {
                VanBanDenModel vb = dstimkiemden.get(i);
                danhSachDen.addRow(new Object[]{
                    i + 1, // STT
                    vb.getTenSo(),
                    vb.getLoaiVanBan(),
                    vb.getSoKyHieu(),
                    vb.getSoDen(),
                    vb.getNgayBanHanh(),
                    vb.getNgayDen(),
                    vb.getNoiBanHanh(),
                    vb.getTrichYeu()
                });
            }
        }

    }

  private void KhoiTaoTimKiemVanBanDen() {
    try {

        // Khởi tạo controller
        VanBanDenController vbController = new VanBanDenController();
        
        // Lấy danh sách số văn bản và thêm vào combobox
        ArrayList<String> danhsachso = vbController.HienThiSoVanBan();
        for (String x : danhsachso) {
            cboTimKiemSoVanBanDen.addItem(x);
        }
        cboTimKiemSoVanBanDen.addItem("Tất cả"); // Thêm lựa chọn "Tất cả"
        
        // Lấy danh sách loại văn bản và thêm vào combobox
        ArrayList<String> danhsachloai = vbController.hienThiLoaiVanBan();
        for (String s : danhsachloai) {
            cboTimKiemLoaiVanBanDen.addItem(s);
        }
        cboTimKiemLoaiVanBanDen.addItem("Tất cả"); // Thêm lựa chọn "Tất cả"
        
        // Mặc định chọn "Tất cả" cho cả hai combobox
        cboTimKiemSoVanBanDen.setSelectedItem("Tất cả");
        cboTimKiemLoaiVanBanDen.setSelectedItem("Tất cả");
        Date currentDate = new Date();
        Date ngaydenmin=currentDate;
        Date ngaydenmax=currentDate;
        HienThiTimKiemTheoNgay(ngaydenmin,ngaydenmax);
        // Làm mới bảng hiển thị
        tblTimKiemDanhSachVanBanDen.revalidate();
        tblTimKiemDanhSachVanBanDen.repaint();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
  private void HienThiTimKiemVanBanDen() {
    try {
        VanBanDenController vbdenContrl = new VanBanDenController();
        // Lấy danh sách tìm kiếm từ controller
        dstimkiemden = vbdenContrl.HienThiTimKiemVanBanDen();

        // Cập nhật dữ liệu lên bảng
        DefaultTableModel danhSachDen = (DefaultTableModel) tblTimKiemDanhSachVanBanDen.getModel();
        danhSachDen.setRowCount(0); // Xóa dữ liệu cũ trên bảng

        // Kiểm tra xem danh sách có dữ liệu hay không
        if (dstimkiemden.isEmpty()) {
            // Nếu không tìm thấy dữ liệu, hiển thị thông báo và giữ bảng trống
            //JOptionPane.showMessageDialog(null, "Không tìm thấy văn bản nào trong khoảng thời gian này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Lặp qua danh sách tìm kiếm để hiển thị dữ liệu
            for (int i = 0; i < dstimkiemden.size(); i++) {
                VanBanDenModel vb = dstimkiemden.get(i);
                danhSachDen.addRow(new Object[]{
                    i + 1, // STT
                    vb.getTenSo(),
                    vb.getLoaiVanBan(),
                    vb.getSoKyHieu(),
                    vb.getSoDen(),
                    vb.getNgayBanHanh(),
                    vb.getNgayDen(),
                    vb.getNoiBanHanh(),
                    vb.getTrichYeu()
                });
            }
        }

        // Làm mới bảng hiển thị
        tblTimKiemDanhSachVanBanDen.revalidate();
        tblTimKiemDanhSachVanBanDen.repaint();

    } catch (Exception ex) {
        // Xử lý ngoại lệ và hiển thị thông báo lỗi
        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
private void HienThiTimKiemTheoNgayDi(Date start,Date end)
{
    VanBanDiController vbienContrl = new VanBanDiController();
        dstimkiemdi = vbienContrl.HienThiVanBanDiTheoNgay(start, end);

        DefaultTableModel danhSachDi = (DefaultTableModel) tblTimKiemDanhSachVanBanDi.getModel();
        danhSachDi.setRowCount(0); 

        if (dstimkiemdi.isEmpty()) {
        } else {
            for (int i = 0; i < dstimkiemdi.size(); i++) {
                VanBanDiModel vbDi = dstimkiemdi.get(i);
                danhSachDi.addRow(new Object[] {
                    i + 1, // STT
                    vbDi.getTenSo(),
                    vbDi.getLoaiVanBan(),
                    vbDi.getSoKyHieu(),
                    vbDi.getSoDi(),
                    vbDi.getNgayBanHanh(),
                    vbDi.getNoiNhan(),
                    vbDi.getTrichYeu()
                });
            }
        }
}
    private void KhoiTaoTimKiemVanBanDi()
    {
        try
        {
            VanBanDiController vbController=new VanBanDiController();
            ArrayList<String> danhsachso=vbController.HienThiSoVanBan();
            for(String x:danhsachso)
            {
                cboTimKiemSoVanBanDi.addItem(x);
            }
            ArrayList<String> danhsachloai=vbController.hienThiLoaiVanBan();
            for(String s:danhsachloai)
            {
                cboTimKiemLoaiVanBanDi.addItem(s);
            }
           // Thêm các phần tử vào ComboBox
            cboTimKiemLoaiVanBanDi.addItem("Tất cả");
            cboTimKiemSoVanBanDi.addItem("Tất cả");

            // Chọn mục "Tất cả" trong ComboBox
            cboTimKiemLoaiVanBanDi.setSelectedItem("Tất cả");
            cboTimKiemSoVanBanDi.setSelectedItem("Tất cả");
            Date currentDate = new Date();
            Date ngaybanmin = currentDate;
            Date ngaybanmax = currentDate;
             // Khởi tạo controller
        HienThiTimKiemTheoNgayDi(ngaybanmin,ngaybanmax);

        // Làm mới bảng hiển thị
        tblTimKiemDanhSachVanBanDi.revalidate();
        tblTimKiemDanhSachVanBanDi.repaint();

    } catch (Exception ex) {
        // Xử lý ngoại lệ và hiển thị thông báo lỗi
        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }
    private void HienThiTimKiemVanBanDi()
    {
        try {
        // Khởi tạo controller
        VanBanDiController vbannContrl = new VanBanDiController();

        dstimkiemdi = vbannContrl.HienThiTimKiemVanBanDi();

        DefaultTableModel danhSachDi = (DefaultTableModel) tblTimKiemDanhSachVanBanDi.getModel();
        danhSachDi.setRowCount(0); // Xóa dữ liệu cũ trên bảng

        // Kiểm tra xem danh sách có dữ liệu hay không
        if (dstimkiemdi.isEmpty()) {
            // Nếu không tìm thấy dữ liệu, vẫn hiển thị bảng trống
            //JOptionPane.showMessageDialog(null, "Không tìm thấy văn bản nào trong khoảng thời gian này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Lặp qua danh sách tìm kiếm để hiển thị dữ liệu
            for (int i = 0; i < dstimkiemdi.size(); i++) {
                VanBanDiModel vbDi = dstimkiemdi.get(i);
                danhSachDi.addRow(new Object[] {
                    i + 1, // STT
                    vbDi.getTenSo(),
                    vbDi.getLoaiVanBan(),
                    vbDi.getSoKyHieu(),
                    vbDi.getSoDi(),
                    vbDi.getNgayBanHanh(),
                    vbDi.getNoiNhan(),
                    vbDi.getTrichYeu()
                });
            }
        }

        // Làm mới bảng hiển thị
        tblTimKiemDanhSachVanBanDi.revalidate();
        tblTimKiemDanhSachVanBanDi.repaint();

    } catch (Exception ex) {
        // Xử lý ngoại lệ và hiển thị thông báo lỗi
        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }
    private void btnHeThongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHeThongActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) pnChuaCacCard.getLayout();
        cl.show(pnChuaCacCard, "heThong"); // Hiển thị panel "Quản lý văn bản nội bộ"

    }//GEN-LAST:event_btnHeThongActionPerformed
    private void HienThiNamVaVanBan()
    {
        int currentYear=java.time.LocalDate.now().getYear();
        for(int i=1990;i<=2024;i++)
        {
            cboNam.addItem(String.valueOf(i));
            cboNamDen.addItem(String.valueOf(i));
            cboNamDi.addItem(String.valueOf(i));
        }
        cboNamDen.addItem("Tất cả");
        cboNamDi.addItem("Tất cả");
        cboNam.setSelectedItem(String.valueOf(currentYear));
        HienThiTable();
    }         
        
    public void HienThiTable() {
    try {
        SoVanBanController soControl = new SoVanBanController();
        int year = Integer.parseInt(cboNam.getSelectedItem().toString()); 
        System.out.println("Năm chọn: " + year); 

        dsvb = soControl.HienThiSoVanBan(year); 
    DefaultTableModel model = (DefaultTableModel) tblSoVanBan.getModel();
        model.setRowCount(0);  

        if (dsvb != null && !dsvb.isEmpty()) {
            for (int i = 0; i < dsvb.size(); i++) {
                int stt = i + 1;  // Số thứ tự
                String soVanBan = dsvb.get(i).getSoVanBan();
                boolean soden = dsvb.get(i).isSoDen();
                int nam = dsvb.get(i).getNam();
                Object[] so = new Object[]{stt, soVanBan, soden, nam};
                model.addRow(so);
            }
        }
        
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
    }
}
public void KhoiTaoVanBanDen()
    {
        try
        {
            VanBanDenController vbController=new VanBanDenController();
          txtSoDen.setText("");
            txtSoKyHieuDen.setText("");
            txtSoTrangDen.setText("");
            txtNguoiDuyetDen.setText("");
            txtNguoiNhanDen.setText("");
            txtNguoiKyDen.setText("");
            txtNoiDungDen.setText("");
            txtTrichYeuDen.setText("");
            txtDinhKemFileDen.setText("");
           
            Date currentDate = new Date();  // Lấy ngày hiện tại
            dateNgayBanHanhDen.setDate(currentDate); 
            
            
            dateNgayDen.setDate(currentDate); 
            btnLuuVanBanDen.setEnabled(false);
            btnXuatExcelVanBanDen.setEnabled(false);
            btnXoaVanBanDen.setEnabled(false);
            ArrayList<String> danhsachso=vbController.HienThiSoVanBan();
            for(String x:danhsachso)
            {
                cboSoVanBanDen.addItem(x);
            }
            Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            dateNgayBanHanhDen.setDate(today);
            dateNgayDen.setDate(today);
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
            cboNamDen.setSelectedItem("Tất cả");
            cboSoVanBanDen.setSelectedIndex(-1);
            cboNoiBanHanhDen.setSelectedIndex(-1);
            cboLoaiVanBanDen.setSelectedIndex(-1);
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,  "Đã xảy ra lỗi: " + ex.getMessage(),  "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void KhoiTaoVanBanDi()
    {
        try
        {
              txtSoKiHieuDi.setText("");
            txtSoDi.setText("");
            txtNoiNhanDi.setText("");
            txtSoLuongBanDi.setText("");
            txtSoTrangDi.setText("");
            txtNguoiDuyetDi.setText("");
            txtNguoiKyDi.setText("");
            txtNguoiGuiDi.setText("");
            txtNoiDungDi.setText("");
            txtTrichYeuDi.setText("");
            txtDinhKemFileDi.setText("");
            
            Date currentDate = new Date();  // Lấy ngày hiện tại
            dateNgayBanHanhDi.setDate(currentDate); 
            
            btnLuuVanBanDi.setEnabled(false);
            btnXuatExcelVanBanDi.setEnabled(false); 
            btnXoaVanBanDi.setEnabled(false); 
            VanBanDiController vbController=new VanBanDiController();
            ArrayList<String> danhsachso=vbController.HienThiSoVanBan();
            for(String x:danhsachso)
            {
                cboSoVanBanDi.addItem(x);
            }
            Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            dateNgayBanHanhDi.setDate(today);
            ArrayList<String> danhsachloai=vbController.hienThiLoaiVanBan();
            for(String s:danhsachloai)
            {
                cboLoaiVanBanDi.addItem(s);
            }
            cboNamDi.setSelectedItem("Tất cả");
            cboLoaiVanBanDi.setSelectedIndex(-1);
            cboSoVanBanDi.setSelectedIndex(-1);
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,  "Đã xảy ra lỗi: " + ex.getMessage(),  "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void HienThiDanhSachVanBanDen() {
    try {
        btnLuuDSDen.setEnabled(false); 
        
        VanBanDenController dsden = new VanBanDenController();
        if(cboNamDen.getSelectedItem().equals("Tất cả"))
        {
            dsvbden =dsden.HienThiTatCaVanBanDen();
        }
        else
        {
             int nam=Integer.parseInt(cboNamDen.getSelectedItem().toString());
        dsvbden = dsden.HienThiDanhSachVanBanDen(nam);
        }
        
       
        DefaultTableModel danhSachDen = (DefaultTableModel) tblDanhSachVanBanDen.getModel();
        
        danhSachDen.setRowCount(0);

        for (int i = 0; i < dsvbden.size(); i++) {
            VanBanDenModel vb = dsvbden.get(i);
            String soVanBan = vb.getTenSo();
            String loaiVanBan = vb.getLoaiVanBan();
            String soKyHieu = vb.getSoKyHieu();
            int soDen = vb.getSoDen();
            Date ngayBanHanh = vb.getNgayBanHanh();
            Date ngayDen = vb.getNgayDen();
            String donViGui = vb.getNoiBanHanh(); 
            String trichYeu = vb.getTrichYeu();

            // Thêm một dòng vào bảng
            danhSachDen.addRow(new Object[]{
                i + 1, 
                soVanBan, 
                loaiVanBan, 
                soKyHieu,
                soDen,
                ngayBanHanh,
                ngayDen,
                donViGui, 
                trichYeu 
            });
        }
    } catch (Exception ex) {
        // Hiển thị thông báo lỗi nếu có
        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
    }
}
    public void HienThiDanhSachVanBanDi() {
    try {
        // Vô hiệu hóa nút lưu (nếu cần)
        btnLuuDSDi.setEnabled(false); 

        // Lấy danh sách văn bản đi
        VanBanDiController vbDiController = new VanBanDiController();

        if (cboNamDi.getSelectedItem().equals("Tất cả")) {
            dsvbdi = vbDiController.HienThiTatCaVanBanDi();
        } else {
            int nam = Integer.parseInt(cboNamDi.getSelectedItem().toString());
            dsvbdi = vbDiController.HienThiDanhSachVanBanDi(nam);
        }

        // Lấy mô hình của bảng
        DefaultTableModel danhSachDi = (DefaultTableModel) tblDanhSachVanBanDi.getModel();

        // Xóa toàn bộ dữ liệu trong bảng
        danhSachDi.setRowCount(0);

        // Duyệt qua danh sách và thêm vào bảng
        for (int i = 0; i < dsvbdi.size(); i++) {
            VanBanDiModel vb = dsvbdi.get(i);
            String soVanBan = vb.getTenSo();
            String loaiVanBan = vb.getLoaiVanBan();
            String soKyHieu = vb.getSoKyHieu();
            int soDi = vb.getSoDi();
            Date ngayBanHanh=vb.getNgayBanHanh();
            String noiNhan = vb.getNoiNhan();
            String trichYeu = vb.getTrichYeu();

            // Thêm một dòng vào bảng
            danhSachDi.addRow(new Object[]{
                i + 1,  // Số thứ tự
                soVanBan, 
                loaiVanBan, 
                soKyHieu, 
                soDi, 
                ngayBanHanh, 
                noiNhan,
                trichYeu 
            });
        }
    } catch (Exception ex) {
        // Hiển thị thông báo lỗi nếu có
        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
    
}

}

    public void HienThiTableLoaiVanBan()
    {
        try {
        // Khởi tạo controller
        LoaiVanBanController loaiVBController = new LoaiVanBanController();
        
        // Lấy danh sách loại văn bản từ controller
        dsl = loaiVBController.HienThiLoaiVanBan();
        
        // Kiểm tra nếu danh sách dsl không null và không rỗng
        if (dsl != null && !dsl.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) tblLoaiVanBan.getModel();
            model.setRowCount(0);  // Xóa hết dữ liệu cũ trong bảng
            
            // Thêm các dòng dữ liệu mới vào bảng
            for (int i = 0; i < dsl.size(); i++) {
                int stt = i + 1;  // Số thứ tự
                String maLoai = dsl.get(i).getMaLoai();
                String loaiVanBan = dsl.get(i).getLoaiVanBan();
                String moTa = dsl.get(i).getMoTa();
                Object[] loai = new Object[]{stt, maLoai, loaiVanBan, moTa};
                model.addRow(loai);  // Thêm dòng vào bảng
            }
        } else {
            // Nếu danh sách rỗng, hiển thị thông báo
            JOptionPane.showMessageDialog(null, "Không có dữ liệu loại văn bản.");
        }
    } catch (Exception ex) {
        // Bắt lỗi và hiển thị thông báo chi tiết lỗi
        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
    }
    }
    public void HienThiTableNoiBanHanh()
    {
        try {
       NoiBanHanhController nbhController = new NoiBanHanhController();
       dsnoi = nbhController.HienThiNoiBanHanh();  
        DefaultTableModel model = (DefaultTableModel) tblNoiBanHanh.getModel();
        model.setRowCount(0);

        for (int i = 0; i < dsnoi.size(); i++) {
            int stt = i + 1;  
            String noiBanHanh = dsnoi.get(i).getNoiBanHanh();  
            String moTa = dsnoi.get(i).getMoTa();  

            Object[] noi = new Object[]{stt, noiBanHanh, moTa};
            model.addRow(noi);  
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        ex.printStackTrace();  
    }
    }
    private void btnQLVBNoiBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLVBNoiBoActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) pnChuaCacCard.getLayout();
        cl.show(pnChuaCacCard, "quanLyVanBanNoiBo"); // Hiển thị panel "Quản lý văn bản nội bộ"
    }//GEN-LAST:event_btnQLVBNoiBoActionPerformed

    private void btnTim_ThongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTim_ThongActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) pnChuaCacCard.getLayout();
        cl.show(pnChuaCacCard, "timThong"); // Hiển thị panel "Quản lý văn bản nội bộ"

    }//GEN-LAST:event_btnTim_ThongActionPerformed

    private void btnQLVanBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLVanBanActionPerformed
        // TODO add your handling code here:
        CardLayout cl = (CardLayout) pnChuaCacCard.getLayout();
        cl.show(pnChuaCacCard, "quanLyVanBan"); // Hiển thị panel "Quản lý văn bản nội bộ"

    }//GEN-LAST:event_btnQLVanBanActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
            // TODO add your handling code here:
        
    pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
    pnChuaNoiDung.setVisible(false); 
   // pnPhongBan.setVisible(true);      
    //pnDSNoibo.setVisible(false);     
    //pnVanBanNoiBo.setVisible(false);  
    pnTimKiemThongKe.setVisible(false);
  //  pnChuaNoiDung.setSelectedIndex(0);
    
    pnQuanLyVanBan.setVisible(false);
    pnNguoiDung.setVisible(false);
    pnKy.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnThem1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem1MouseEntered

    private void btnThem1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem1MouseExited

    private void btnSua1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSua1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSua1MouseEntered

    private void btnSua1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSua1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSua1MouseExited

    private void btnXoa1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoa1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoa1MouseEntered

    private void btnXoa1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoa1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoa1MouseExited

    private void btnTaiLai1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiLai1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaiLai1MouseEntered

    private void btnTaiLai1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiLai1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaiLai1MouseExited

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton44ActionPerformed

    private void btnHeThong1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHeThong1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHeThong1ActionPerformed

    private void btnQLVBNoiBo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLVBNoiBo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQLVBNoiBo1ActionPerformed

    private void btnTim_Thong1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTim_Thong1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTim_Thong1ActionPerformed

    private void btnQLVanBan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLVanBan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQLVanBan1ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton65MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton65MouseExited
        // Khi chuột rời khỏi jButton65, chỉ ẩn jPopupMenu5 nếu chuột không ở trong jPopupMenu5
    if (!jPopupMenu5.getBounds().contains(evt.getPoint())) {
        jPopupMenu5.setVisible(false);
    }
    }//GEN-LAST:event_jButton65MouseExited

    private void jButton65MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton65MouseEntered
    // Hiển thị jPopupMenu5 khi chuột di chuyển vào jButton65
    jPopupMenu5.show(jButton65, 0, jButton65.getHeight());
    }//GEN-LAST:event_jButton65MouseEntered

    private void jPopupMenu5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPopupMenu5MouseEntered
        // TODO add your handling code here:
          // Giữ jPopupMenu5 mở khi chuột vào trong menu
    jPopupMenu5.setVisible(true);
    }//GEN-LAST:event_jPopupMenu5MouseEntered

    private void jPopupMenu5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPopupMenu5MouseExited
       // Khi chuột rời khỏi cả jButton65 và jPopupMenu5, ẩn jPopupMenu5
    if (!jButton65.getBounds().contains(evt.getPoint()) && 
        !jPopupMenu5.getBounds().contains(evt.getPoint())) {
        jPopupMenu5.setVisible(false);
    }
    }//GEN-LAST:event_jPopupMenu5MouseExited

    private void jPopupMenu6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPopupMenu6MouseEntered
        
    jPopupMenu6.setVisible(true);
    }//GEN-LAST:event_jPopupMenu6MouseEntered

    private void jPopupMenu6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPopupMenu6MouseExited
       
    }//GEN-LAST:event_jPopupMenu6MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          pnThan.setVisible(true);
        pnChuaThan.setVisible(true);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(5); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
               
    pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
    pnChuaNoiDung.setVisible(false);
    pnTimKiemThongKe.setVisible(true);
    pnQuanLyVanBan.setVisible(false);
    
    pnChuaTimKiemThongKe.setVisible(true); 
    ThongKeVanBanDi.setVisible(false);      
    ThongKeVanBanDen.setVisible(false);     
    ThongKeVanBanNoiBo.setVisible(false);  
  
    pnChuaTimKiemThongKe.setSelectedIndex(0);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        pnQuanLyVanBan.setVisible(false);
    pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
    pnChuaNoiDung.setVisible(true);  // Hiển thị JTabbedPane pnChuaNoiDung
    pnVanBanNoiBo.setVisible(true);   
    pnPhongBan.setVisible(false);
    pnDSNoibo.setVisible(false);     
    pnTimKiemThongKe.setVisible(false);
    // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
    pnChuaNoiDung.setSelectedIndex(1); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    pnQuanLyVanBan.setVisible(false);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void btnThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseEntered
        // TODO add your handling code here:
           // TODO add your handling code here:
        jPopupMenu1.show(btnThem, 0, btnThem.getHeight()); // dọc gắn liền btnThem
    }//GEN-LAST:event_btnThemMouseEntered

    private void btnThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseExited
     jPopupMenu1.setVisible(false);
    }//GEN-LAST:event_btnThemMouseExited

    private void btnXoaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseEntered
        // TODO add your handling code here:
        jPopupMenu3.show(btnXoa, 0, btnXoa.getHeight());
    }//GEN-LAST:event_btnXoaMouseEntered

    private void btnXoaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseExited
        // TODO add your handling code here:
        jPopupMenu3.setVisible(false);
    }//GEN-LAST:event_btnXoaMouseExited

    private void btnTaiLaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiLaiMouseEntered
        // TODO add your handling code here:
        jPopupMenu4.show(btnTaiLai, 0, btnTaiLai.getHeight());
    }//GEN-LAST:event_btnTaiLaiMouseEntered

    private void btnTaiLaiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiLaiMouseExited
        // TODO add your handling code here:
        jPopupMenu4.setVisible(false);
    }//GEN-LAST:event_btnTaiLaiMouseExited

    private void btnLuuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseEntered
        // TODO add your handling code here:
        jPopupMenu7.show(btnLuu, 0, btnLuu.getHeight());
    }//GEN-LAST:event_btnLuuMouseEntered

    private void btnLuuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseExited
        // TODO add your handling code here:'
        jPopupMenu7.setVisible(false);
    }//GEN-LAST:event_btnLuuMouseExited
     public void initTablePhongBan(){
    
         // mảng chưa Các cột tiêu đề 
    String[] colsName = new String[]{"STT", "TÊN PHÒNG BAN", "MÃ PHÒNG BAN"}; 
    tableModelPhongBan = new DefaultTableModel(colsName, 0);
    tbPhongBan.setModel(tableModelPhongBan);

    // Ẩn cột "MÃ PHÒNG BAN"
    tbPhongBan.getColumnModel().getColumn(2).setMinWidth(0);
    tbPhongBan.getColumnModel().getColumn(2).setMaxWidth(0);
    tbPhongBan.getColumnModel().getColumn(2).setWidth(0);

    }
   public void fillDataPhongBan(){
       ArrayList<PhongBan> lst = null;
    try {
        lst = new PhongBanController().getAll(); // Lấy danh sách từ CSDL
    } catch (SQLException ex) {
        Logger.getLogger(FormTrangChu.class.getName()).log(Level.SEVERE, null, ex);
    }

    tableModelPhongBan.setRowCount(0); // Xóa dữ liệu cũ trên bảng
    for (int i = 0; i < lst.size(); i++) {
        String[] row = {
            String.valueOf(i + 1), // Số thứ tự
            lst.get(i).getTenPhongBan(), // Tên Phòng Ban
            lst.get(i).getMaPhongBan() // Mã Phòng Ban (cột ẩn)
        };
        tableModelPhongBan.addRow(row);
    }
 }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        
     // Tạo dòng mới với số thứ tự tự động tăng
    int rowCount = tableModelPhongBan.getRowCount();
    String[] newRow = { 
        String.valueOf(rowCount + 1), // Số thứ tự tự động tăng
        "", // Tên Phòng Ban (để người dùng nhập)
        "null" // Giá trị mặc định cho cột ẩn "Mã Phòng Ban"
    };
    tableModelPhongBan.addRow(newRow);
      isNewRowAdded = true;
    }//GEN-LAST:event_btnThemActionPerformed

     private boolean isNewRowAdded = false;
    
    private void btnTaiLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiLaiActionPerformed
if (isNewRowAdded) {
        // Lặp qua tất cả các dòng trong bảng và xóa các dòng có giá trị "null" trong cột "Mã Phòng Ban"
        int rowCount = tableModelPhongBan.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            if ("null".equals(tableModelPhongBan.getValueAt(i, 2))) {  // Kiểm tra cột "Mã Phòng Ban"
                tableModelPhongBan.removeRow(i); // Xóa dòng nếu có giá trị "null"
            }
        }
        
        // Đánh dấu rằng không còn dòng mới nào
        isNewRowAdded = false;}
    }//GEN-LAST:event_btnTaiLaiActionPerformed

    private void btnThem8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem8MouseEntered

        jPopupMenu1.show(btnThem8, 0, btnThem8.getHeight()); // dọc gắn liền btnThem
    }//GEN-LAST:event_btnThem8MouseEntered

    private void btnThem8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem8MouseExited
        jPopupMenu1.setVisible(false);
    }//GEN-LAST:event_btnThem8MouseExited
  
    private void btnLuu7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuu7MouseEntered
      jPopupMenu7.show(btnLuu7, 0, btnLuu7.getHeight());
    }//GEN-LAST:event_btnLuu7MouseEntered

    private void btnLuu7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuu7MouseExited
         jPopupMenu7.setVisible(false);
    }//GEN-LAST:event_btnLuu7MouseExited

    private void btnTaiLai8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiLai8MouseEntered
        jPopupMenu4.show(btnTaiLai8, 0, btnTaiLai8.getHeight());
    }//GEN-LAST:event_btnTaiLai8MouseEntered

    private void btnTaiLai8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiLai8MouseExited
          jPopupMenu4.setVisible(false);
    }//GEN-LAST:event_btnTaiLai8MouseExited

    private void btnSua9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSua9MouseEntered
        // TODO add your handling code here:
          // TODO add your handling code here:
        jPopupMenu2.show(btnSua9, 0, btnSua9.getHeight());
    }//GEN-LAST:event_btnSua9MouseEntered

    private void btnSua9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSua9MouseExited
        jPopupMenu2.setVisible(false);
    }//GEN-LAST:event_btnSua9MouseExited

    private void btnSua9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua9ActionPerformed
       int selectedRow = tbVanBanNoiBo.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    isEditing = true; // Chuyển sang trạng thái sửa

    String soKyHieu = tbVanBanNoiBo.getValueAt(selectedRow, 0).toString();
    txtSoKyHieu.setText(soKyHieu);
    txtSoKyHieu.setEnabled(false); // Không cho phép sửa số ký hiệu

    // Hiển thị tab chỉnh sửa
    pnThan.setVisible(true);
    pnChinh.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChuaNoiDung.setSelectedIndex(1); // Tab chỉnh sửa
    
    }//GEN-LAST:event_btnSua9ActionPerformed

    private void btnXoa9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoa9MouseEntered
         jPopupMenu3.show(btnXoa9, 0, btnXoa9.getHeight());
    }//GEN-LAST:event_btnXoa9MouseEntered

    private void btnXoa9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoa9MouseExited
        // TODO add your handling code here:
         jPopupMenu3.setVisible(false);
    }//GEN-LAST:event_btnXoa9MouseExited

    private void btnXoa9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa9ActionPerformed
        // TODO add your handling code here:
           int selectedRow = tbVanBanNoiBo.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.");
        return;
    }

     String soKyHieu = txtSoKyHieu.getText();
     
    // Hỏi xác nhận xóa
    int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa phòng ban này?", 
            "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
               try {
                   if(new VanBanNoiBoController().Xoa(soKyHieu)){
                       try {
                           JOptionPane.showMessageDialog(this, "Xóa thành công ");
                           fillDataVanBanNoiBo();
                       } catch (SQLException ex) {
                           Logger.getLogger(FormTrangChu.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   } } catch (SQLException ex) {
                   Logger.getLogger(FormTrangChu.class.getName()).log(Level.SEVERE, null, ex);
               }

    }
        
    }//GEN-LAST:event_btnXoa9ActionPerformed

    private void btnVanBanNoiBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVanBanNoiBoActionPerformed
pnQuanLyVanBan.setVisible(false);
    pnThan.setVisible(true);
            pnNguoiDung.setVisible(false);
    pnKy.setVisible(false);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
    pnChuaNoiDung.setVisible(true);  // Hiển thị JTabbedPane pnChuaNoiDung
    pnVanBanNoiBo.setVisible(true);   
    pnPhongBan.setVisible(false);
    pnDSNoibo.setVisible(false);     
    pnTimKiemThongKe.setVisible(false);
    // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
    pnChuaNoiDung.setSelectedIndex(1); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    pnQuanLyVanBan.setVisible(false);
    }//GEN-LAST:event_btnVanBanNoiBoActionPerformed

    private void btnDsNoiBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDsNoiBoActionPerformed
        pnNguoiDung.setVisible(false);
    pnKy.setVisible(false);
    pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
    pnChuaNoiDung.setVisible(true);  // Hiển thị JTabbedPane pnChuaNoiDung
    pnDSNoibo.setVisible(true);     
    pnPhongBan.setVisible(false);    
    pnVanBanNoiBo.setVisible(false); 
    pnTimKiemThongKe.setVisible(false);

    pnChuaNoiDung.setSelectedIndex(2);
    }//GEN-LAST:event_btnDsNoiBoActionPerformed

    private void btnPhongBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhongBanActionPerformed

    pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
    pnChuaNoiDung.setVisible(true); 
    pnPhongBan.setVisible(true);      
    pnDSNoibo.setVisible(false);     
    pnVanBanNoiBo.setVisible(false);  
  pnTimKiemThongKe.setVisible(false);
    pnChuaNoiDung.setSelectedIndex(0);
            pnNguoiDung.setVisible(false);
    pnKy.setVisible(false);
    pnQuanLyVanBan.setVisible(false);
    }//GEN-LAST:event_btnPhongBanActionPerformed

    private void tbVanBanNoiBoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVanBanNoiBoMouseClicked
     try {
        int row = this.tbVanBanNoiBo.getSelectedRow();
        String sokyhieu = this.tbVanBanNoiBo.getValueAt(row, 0).toString();
        String tenvanban = this.tbVanBanNoiBo.getValueAt(row, 1).toString();
        String ngayBanHanhStr = this.tbVanBanNoiBo.getValueAt(row, 2).toString();
        String loaibanhanh = this.tbVanBanNoiBo.getValueAt(row, 3).toString();
        String phongbanhanh = this.tbVanBanNoiBo.getValueAt(row, 4).toString();
        String phongbannhan = this.tbVanBanNoiBo.getValueAt(row, 5).toString();
        String trichyeu = this.tbVanBanNoiBo.getValueAt(row, 6).toString();

        // Set giá trị cho các trường
        this.txtSoKyHieu.setText(sokyhieu);
        this.txtTenVanBan.setText(tenvanban);

        if (ngayBanHanhStr != null && !ngayBanHanhStr.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Thay đổi định dạng thành "yyyy-MM-dd"
            try {
                Date ngaybanhanh = sdf.parse(ngayBanHanhStr);
                this.dateNgayBanHanh.setDate(ngaybanhanh);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Ngày ban hành không hợp lệ!");
                e.printStackTrace();
            }
        }

        // Cập nhật các ComboBox
        if (((DefaultComboBoxModel) this.cmbLoaiBanHanh.getModel()).getIndexOf(loaibanhanh) != -1) {
            this.cmbLoaiBanHanh.setSelectedItem(loaibanhanh);
        }
        if (((DefaultComboBoxModel) this.cmbPhongBanHanh.getModel()).getIndexOf(phongbanhanh) != -1) {
            this.cmbPhongBanHanh.setSelectedItem(phongbanhanh);
        }
        if (((DefaultComboBoxModel) this.cmbPhongBanNhan.getModel()).getIndexOf(phongbannhan) != -1) {
            this.cmbPhongBanNhan.setSelectedItem(phongbannhan);
        }

        this.txtTrichYeu.setText(trichyeu);
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + ex.getMessage());
    }
        
    }//GEN-LAST:event_tbVanBanNoiBoMouseClicked

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
     if (tableModelPhongBan == null) {
        initTablePhongBan();
    }
    PhongBanController controller = new PhongBanController();
    
    // Duyệt qua các dòng trong bảng
    for (int i = 0; i < tableModelPhongBan.getRowCount(); i++) {
        String tenPhongBan = (String) tableModelPhongBan.getValueAt(i, 1); // Cột tên phòng ban
        String maPhongBan = (String) tableModelPhongBan.getValueAt(i, 2); // Cột mã phòng ban (ẩn)
        
        // Kiểm tra dữ liệu rỗng
        if (tenPhongBan == null || tenPhongBan.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền tên phòng ban ở dòng " + (i + 1) + ".");
            return;
        }

        // Kiểm tra trùng lặp trong bảng
        for (int j = 0; j < tableModelPhongBan.getRowCount(); j++) {
            if (i != j) { // Không so sánh với chính dòng hiện tại
                String tenPhongBanKhac = (String) tableModelPhongBan.getValueAt(j, 1);
                if (tenPhongBan.equalsIgnoreCase(tenPhongBanKhac)) {
                    JOptionPane.showMessageDialog(this, "Dữ liệu nhập ở dòng " + (i + 1) + " trùng với dòng " + (j + 1) + " trong bảng.");
                    return;
                }
            }
        }

        try {
            if (maPhongBan == null || maPhongBan.trim().isEmpty() || "null".equals(maPhongBan)) {
                // Thêm mới nếu mã phòng ban không tồn tại
                boolean isSuccess = controller.ThemPhongBan(tenPhongBan);
                if (!isSuccess) {
                    JOptionPane.showMessageDialog(this, "Thêm mới phòng ban thất bại: " + tenPhongBan);
                }
            } else {
                // Cập nhật nếu mã phòng ban đã tồn tại
                boolean isSuccess = controller.SuaPhongBan(maPhongBan, tenPhongBan);
                if (!isSuccess) {
                    JOptionPane.showMessageDialog(this, "Cập nhật phòng ban thất bại: " + tenPhongBan);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormTrangChu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi lưu dữ liệu phòng: " + tenPhongBan);
        }
    }

    tableModelPhongBan.setRowCount(0); // Xóa dữ liệu hiện tại trên giao diện
    fillDataPhongBan(); // Nạp lại dữ liệu mới từ CSDL
    loadComboboxData();
    JOptionPane.showMessageDialog(this, "Lưu toàn bộ dữ liệu thành công.");
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
          int selectedRow = tbPhongBan.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.");
        return;
    }

    // Lấy mã phòng ban từ cột ẩn (giả sử cột MaPhongBan là cột thứ 2)
    String maPhongBan = (String) tableModelPhongBan.getValueAt(selectedRow, 2);

    // Hỏi xác nhận xóa
    int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa phòng ban này?", 
            "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
              try {
                  PhongBanController controller = new PhongBanController();
                  
                  if(new PhongBanController().XoaPhongBan(maPhongBan)){
                      JOptionPane.showMessageDialog(this, "Xóa phòng ban thành công");
                      fillDataPhongBan();
                     
                  }     } catch (SQLException ex) {
                  Logger.getLogger(FormTrangChu.class.getName()).log(Level.SEVERE, null, ex);
              }

    }
        
    }//GEN-LAST:event_btnXoaActionPerformed

    private void pnChuaNoiDungAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_pnChuaNoiDungAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_pnChuaNoiDungAncestorAdded

    private void btnThem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem8ActionPerformed
     // Xóa nội dung các trường nhập liệu
    txtSoKyHieu.setText("");
    txtTenVanBan.setText("");
    txtNguoiNhan.setText("");
    txtNguoiKy.setText("");
    txtNguoiDuyet.setText("");
    txtTrichYeu.setText("");
    txtNoiDung.setText("");

    // Đặt lại ngày hiện tại cho trường Ngày ban hành
    dateNgayBanHanh.setDate(null);

    // Đặt giá trị mặc định cho các combobox
    cmbLoaiBanHanh.setSelectedIndex(0);
    cmbPhongBanHanh.setSelectedIndex(0);
    cmbPhongBanNhan.setSelectedIndex(0);

    // Khôi phục trạng thái hiển thị và kích hoạt của txtSoKyHieu
    txtSoKyHieu.setVisible(true);
    txtSoKyHieu.setEnabled(true);

    // Đặt con trỏ lại trường "Số ký hiệu"
    txtSoKyHieu.requestFocus();

    }//GEN-LAST:event_btnThem8ActionPerformed
    public void initTableVanBanNoiBo(){
         // mảng chưa Các cột tiêu đề 
         
        String[] colsName = new String[]{"Số ký hiệu","Tên văn bản","Ngày ban hành","Loại ban hành","Phòng ban hành","Phòng ban nhận","Người nhận","Người ký","Người duyệt","Trích yếu","Nội dung"}; 
        tableModelVanBanNoiBo.setColumnIdentifiers(colsName);// hiển thị tiêu dề dựa vaod các cột trong bảng
        tbVanBanNoiBo.setModel(tableModelVanBanNoiBo);
       
    }
    
    public void initTableVanBanNoiBo1(){
         // mảng chưa Các cột tiêu đề 
         
        String[] colsName = new String[]{"Số ký hiệu","Tên văn bản","Ngày ban hành","Loại ban hành","Phòng ban hành","Phòng ban nhận","Người nhận","Người ký","Người duyệt","Trích yếu","Nội dung"}; 
        tableModelVanBanNoiBo.setColumnIdentifiers(colsName);// hiển thị tiêu dề dựa vaod các cột trong bảng
        tbVanBanNoiBo1.setModel(tableModelVanBanNoiBo);
    }
    public void fillDataVanBanNoiBo() throws SQLException{
       // fill Data
            ArrayList<VanBanNoiBo> lst = null;
            lst = new VanBanNoiBoController().getAll();
            tableModelVanBanNoiBo.setRowCount(0);
            for(int i=0 ; i<lst.size() ; i++){
                String row[] = {
                    // i=1 // lặp qua tất cả phần tử trong ds và truy xuất 
                    lst.get(i).getSoKyHieu(),
                    lst.get(i).getTenVanBan(),
                    lst.get(i).getNgayBanHanh(),
                    lst.get(i).getLoaiBanHanh(),
                    lst.get(i).getPhongBanHanh(),
                    lst.get(i).getPhongNhan(),
                    lst.get(i).getNguoiNhan(),
                    lst.get(i).getNguoiKy(),
                    lst.get(i).getNguoiDuyet(),
                    lst.get(i).getTrichYeu(),
                    lst.get(i).getNoiDung()
                };
                tableModelVanBanNoiBo.addRow(row);
      
            }
 }
    
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
     public void loadComboboxData1() {
   try {
        VanBanNoiBoController controller = new VanBanNoiBoController();
        
        // Load dữ liệu cho cmbLoaiBanHanh
        ArrayList<String> loaiVanBanList = controller.getLoaiVanBan();
        chonCmbLoaiBanHanh.removeAllItems(); // Xóa dữ liệu cũ
        chonCmbLoaiBanHanh.addItem("Tất cả"); // Thêm lựa chọn "Tất cả"
        for (String loaiVanBan : loaiVanBanList) {
            chonCmbLoaiBanHanh.addItem(loaiVanBan); // Thêm từng mục vào combobox
        }

        // Load dữ liệu cho cmbPhongBanHanh và cmbPhongBanNhan
        ArrayList<String> phongBanList = controller.getPhongBan();
        chonCmbPhongBanHanh.removeAllItems();
        ChonCmbPhongBanNhan.removeAllItems();
        chonCmbPhongBanHanh.addItem("Tất cả"); // Thêm lựa chọn "Tất cả"
        ChonCmbPhongBanNhan.addItem("Tất cả"); // Thêm lựa chọn "Tất cả"
        for (String phongBan : phongBanList) {
            chonCmbPhongBanHanh.addItem(phongBan);
            ChonCmbPhongBanNhan.addItem(phongBan); // Phòng nhận dùng cùng dữ liệu
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu lên Combobox: " + e.getMessage());
    }
}

private boolean isEditing = false;
    private void btnLuu7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuu7ActionPerformed
 try {
    int[] selectedRows = tbDinhKem.getSelectedRows(); // Lấy tất cả hàng được chọn

    if (selectedRows.length == 0) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài liệu.");
        return;
    }

    String soKyHieu = txtSoKyHieu.getText();
    String tenVanBan = txtTenVanBan.getText();
    Date ngayBanHanh = dateNgayBanHanh.getDate();
    String tenLoaiBanHanh = cmbLoaiBanHanh.getSelectedItem().toString();
    String tenPhongBanHanh = cmbPhongBanHanh.getSelectedItem().toString();
    String tenPhongBanNhan = cmbPhongBanNhan.getSelectedItem().toString();
    String nguoiNhan = txtNguoiNhan.getText();
    String nguoiKy = txtNguoiKy.getText();
    String nguoiDuyet = txtNguoiDuyet.getText();
    String trichYeu = txtTrichYeu.getText();
    String noiDung = txtNoiDung.getText();

    VanBanNoiBoController controller = new VanBanNoiBoController();

    if (soKyHieu.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập số ký hiệu.");
        txtSoKyHieu.requestFocus();
        return;
    }

    if (tenVanBan.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập tên văn bản.");
        txtTenVanBan.requestFocus();
        return;
    }

    if (ngayBanHanh == null) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày ban hành.");
        dateNgayBanHanh.requestFocus();
        return;
    }

    if (!isEditing && controller.kiemTraSoKyHieuTonTai(soKyHieu)) {
        JOptionPane.showMessageDialog(this, "Số ký hiệu đã tồn tại. Vui lòng nhập số ký hiệu khác.");
        txtSoKyHieu.requestFocus();
        return;
    }

    String maLoaiBanHanh = controller.layMaLoaiVanBan(tenLoaiBanHanh);
    String maPhongBanHanh = controller.layMaPhongBan(tenPhongBanHanh);
    String maPhongBanNhan = controller.layMaPhongBan(tenPhongBanNhan);

    // Duyệt qua từng hàng được chọn
    boolean success = true;
    for (int row : selectedRows) {
        int maTL = Integer.parseInt(tableModelTaiLieu.getValueAt(row, 0).toString());

        if (isEditing) {
            // Gọi hàm cập nhật
            if (!controller.capNhatVanBanNoiBo(soKyHieu, tenVanBan, ngayBanHanh, maLoaiBanHanh, maPhongBanHanh, maPhongBanNhan, nguoiNhan, nguoiKy, nguoiDuyet, trichYeu, noiDung, maTL)) {
                success = false;
            }
        } else {
            // Gọi hàm thêm mới
            if (!controller.themVanBanNoiBo(soKyHieu, tenVanBan, ngayBanHanh, maLoaiBanHanh, maPhongBanHanh, maPhongBanNhan, nguoiNhan, nguoiKy, nguoiDuyet, trichYeu, noiDung, maTL)) {
                success = false;
            }
        }
    }

    // Hiển thị thông báo thành công hoặc thất bại
    if (success) {
        JOptionPane.showMessageDialog(this, (isEditing ? "Cập nhật" : "Thêm") + " thành công.");
        isEditing = false; // Reset trạng thái
        fillDataVanBanNoiBo();
    } else {
        JOptionPane.showMessageDialog(this, (isEditing ? "Cập nhật" : "Thêm") + " thất bại cho một hoặc nhiều tài liệu.");
    }
} catch (SQLException e) {
    JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
}

    }//GEN-LAST:event_btnLuu7ActionPerformed

    private void btnTaiLai8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiLai8ActionPerformed
     // Xóa nội dung các trường nhập liệu
    txtSoKyHieu.setText("");
    txtTenVanBan.setText("");
    txtNguoiNhan.setText("");
    txtNguoiKy.setText("");
    txtNguoiDuyet.setText("");
    txtTrichYeu.setText("");
    txtNoiDung.setText("");

    // Đặt lại ngày hiện tại cho trường Ngày ban hành
    dateNgayBanHanh.setDate(null);

    // Đặt giá trị mặc định cho các combobox
    cmbLoaiBanHanh.setSelectedIndex(0);
    cmbPhongBanHanh.setSelectedIndex(0);
    cmbPhongBanNhan.setSelectedIndex(0);

 
    // Khôi phục trạng thái hiển thị và kích hoạt của txtSoKyHieu
    txtSoKyHieu.setVisible(true);
    txtSoKyHieu.setEnabled(true);

    // Thông báo (nếu cần)
    JOptionPane.showMessageDialog(this, "Các trường đã được đặt lại!");
        
    }//GEN-LAST:event_btnTaiLai8ActionPerformed

    private void btnThemQuayLaiDeThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemQuayLaiDeThemActionPerformed
      pnThan.setVisible(true);
        pnChuaThan.setVisible(true);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(true);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(true);
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);

        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaNoiDung.setSelectedIndex(1); // Chuyển đến tab đầu tiên (VanBanNoiBo)
// Xóa nội dung các trường nhập liệu
    txtSoKyHieu.setText("");
    txtTenVanBan.setText("");
    txtNguoiNhan.setText("");
    txtNguoiKy.setText("");
    txtNguoiDuyet.setText("");
    txtTrichYeu.setText("");
    txtNoiDung.setText("");

    // Đặt lại ngày hiện tại cho trường Ngày ban hành
    dateNgayBanHanh.setDate(null);

    // Đặt giá trị mặc định cho các combobox
    cmbLoaiBanHanh.setSelectedIndex(0);
    cmbPhongBanHanh.setSelectedIndex(0);
    cmbPhongBanNhan.setSelectedIndex(0);

    // Khôi phục trạng thái hiển thị và kích hoạt của txtSoKyHieu
    txtSoKyHieu.setVisible(true);
    txtSoKyHieu.setEnabled(true);

    // Đặt con trỏ lại trường "Số ký hiệu"
    txtSoKyHieu.requestFocus();

    }//GEN-LAST:event_btnThemQuayLaiDeThemActionPerformed

    private void btnThemQuayLaiDeThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemQuayLaiDeThemMouseExited
        jPopupMenu1.setVisible(false);
    }//GEN-LAST:event_btnThemQuayLaiDeThemMouseExited

    private void btnThemQuayLaiDeThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemQuayLaiDeThemMouseEntered

        jPopupMenu1.show(btnThemQuayLaiDeThem, 0, btnThemQuayLaiDeThem.getHeight()); // dọc gắn liền btnThem
    }//GEN-LAST:event_btnThemQuayLaiDeThemMouseEntered
private Date getTodayStart() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
}

private Date getTodayEnd() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal.getTime();
}

private Date getWeekStart() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
}

private Date getWeekEnd() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
    cal.add(Calendar.DAY_OF_WEEK, 6); // Tăng 6 ngày để đến cuối tuần
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal.getTime();
}

private Date getMonthStart() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_MONTH, 1); // Ngày đầu tiên của tháng
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
}

private Date getMonthEnd() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // Ngày cuối cùng của tháng
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal.getTime();
}

private Date getMonthStart(int month) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.MONTH, month - 1); // Tháng bắt đầu từ 0 (tháng 1 là 0)
    cal.set(Calendar.DAY_OF_MONTH, 1); // Ngày đầu tiên của tháng
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
}

private Date getMonthEnd(int month) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.MONTH, month - 1);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // Ngày cuối cùng của tháng
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal.getTime();
}

private Date getYearStart() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.MONTH, Calendar.JANUARY); // Tháng 1
    cal.set(Calendar.DAY_OF_MONTH, 1); // Ngày đầu tiên của năm
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
}

private Date getYearEnd() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.MONTH, Calendar.DECEMBER); // Tháng 12
    cal.set(Calendar.DAY_OF_MONTH, 31); // Ngày cuối cùng của năm
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal.getTime();
}
private Date getPreviousYearStart() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.YEAR, -1); // Lùi lại 1 năm
    cal.set(Calendar.MONTH, Calendar.JANUARY); // Tháng 1
    cal.set(Calendar.DAY_OF_MONTH, 1); // Ngày đầu tiên
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
}

private Date getPreviousYearEnd() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.YEAR, -1); // Lùi lại 1 năm
    cal.set(Calendar.MONTH, Calendar.DECEMBER); // Tháng 12
    cal.set(Calendar.DAY_OF_MONTH, 31); // Ngày cuối cùng
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal.getTime();
}


    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
    // Lấy giá trị đã chọn từ JComboBox
    String selectedItem = (String) jComboBox1.getSelectedItem();
    Date startDate = null, endDate = null;

    // Kiểm tra giá trị đã chọn và thiết lập thời gian bắt đầu và kết thúc
    switch (selectedItem) {
        case "Hôm nay":
            startDate = getTodayStart();
            endDate = getTodayEnd();
            break;
        case "Tuần này":
            startDate = getWeekStart();
            endDate = getWeekEnd();
            break;
        case "Tháng này":
            startDate = getMonthStart();
            endDate = getMonthEnd();
            break;
        case "Năm nay":
            startDate = getYearStart();
            endDate = getYearEnd();
            break;
        case "Tháng 1":
            startDate = getMonthStart(1);
            endDate = getMonthEnd(1);
            break;
              case "Tháng 2":
            startDate = getMonthStart(2);
            endDate = getMonthEnd(2);
            break;
             case "Tháng 3":
            startDate = getMonthStart(3);
            endDate = getMonthEnd(3);
            break;
             case "Tháng 4":
            startDate = getMonthStart(4);
            endDate = getMonthEnd(4);
            break;
             case "Tháng 5":
            startDate = getMonthStart(5);
            endDate = getMonthEnd(5);
            break;
             case "Tháng 6":
            startDate = getMonthStart(6);
            endDate = getMonthEnd(6);
            break;
             case "Tháng 7":
            startDate = getMonthStart(7);
            endDate = getMonthEnd(7);
            break;
             case "Tháng 8":
            startDate = getMonthStart(8);
            endDate = getMonthEnd(8);
            break;
             case "Tháng 9":
            startDate = getMonthStart(9);
            endDate = getMonthEnd(9);
            break;
             case "Tháng 10":
            startDate = getMonthStart(10);
            endDate = getMonthEnd(10);
            break;
             case "Tháng 11":
            startDate = getMonthStart(11);
            endDate = getMonthEnd(11);
            break;
             case "Tháng 12":
            startDate = getMonthStart(12);
            endDate = getMonthEnd(12);
            break;
        case "Năm trước":
            startDate = getPreviousYearStart();
            endDate = getPreviousYearEnd();
            break;
        default:
            break;
    }

    // Kiểm tra và thiết lập giá trị cho jDateChooser2 và jDateChooser3
    if (startDate != null && endDate != null) {
        jDateChooser2.setDate(startDate); // Đặt giá trị "từ ngày"
        jDateChooser3.setDate(endDate);   // Đặt giá trị "đến ngày"

        filterAndDisplayVanBan(startDate, endDate);
    }
    }//GEN-LAST:event_jComboBox1ActionPerformed
private void filterAndDisplayVanBan(Date startDate, Date endDate) {
    
    VanBanNoiBoController controller = new VanBanNoiBoController();
    try {
        // Lấy danh sách văn bản nội bộ theo khoảng thời gian
        ArrayList<VanBanNoiBo> vanBanList = controller.filterDataByDateRange(startDate, endDate);
        
        // Xóa toàn bộ dữ liệu cũ trong bảng
        tableModelVanBanNoiBo.setRowCount(0);
        
        // Thêm dữ liệu mới
        for (VanBanNoiBo vanBan : vanBanList) {
            tableModelVanBanNoiBo.addRow(new Object[]{
                vanBan.getSoKyHieu(),
                vanBan.getTenVanBan(),
                vanBan.getNgayBanHanh(),
                vanBan.getLoaiBanHanh(),
                vanBan.getPhongBanHanh(),
                vanBan.getPhongNhan(),
                vanBan.getNguoiNhan(),
                vanBan.getNguoiKy(),
                vanBan.getNguoiDuyet(),
                vanBan.getTrichYeu(),
                vanBan.getNoiDung()
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
    }
}



public void initTableTaiLieu() {
    // Mảng chứa các cột tiêu đề
    String[] colsName = new String[]{"Mã tài liệu","Tên tài liệu", "Ngày tạo", "Kích cỡ ", "Loại","Đường dẫn"}; 
    tableModelTaiLieu = new DefaultTableModel(colsName, 0);
    tbDinhKem.setModel(tableModelTaiLieu);
    
     // Ẩn cột "MÃ Ptài liệu"
    tbDinhKem.getColumnModel().getColumn(0).setMinWidth(0);
    tbDinhKem.getColumnModel().getColumn(0).setMaxWidth(0);
    tbDinhKem.getColumnModel().getColumn(0).setWidth(0);

    // Ẩn cột "Đường dẫn"
    tbDinhKem.getColumnModel().getColumn(5).setMinWidth(0);
    tbDinhKem.getColumnModel().getColumn(5).setMaxWidth(0);
    tbDinhKem.getColumnModel().getColumn(5).setWidth(0);

}

public void fillDataTaiLieu() {
    try {
        // Lấy danh sách tài liệu từ Controller
      TaiLieuController tlController = new TaiLieuController();
        ArrayList<TaiLieu> lst = tlController.getAll(); // Gọi phương thức lấy dữ liệu

        // Xóa dữ liệu cũ trên bảng
        tableModelTaiLieu.setRowCount(0);

        // Thêm dữ liệu mới
        for (TaiLieu tl : lst) {
            String[] row = {
                tl.getMaTL(),
                tl.getTenTL(), // Tên tài liệu
                tl.getNgayTao(), // Ngày tạo
                tl.getKichCo(), // Kích cỡ
                tl.getLoai(), // Loại
                tl.getDuongDan()
            };
            tableModelTaiLieu.addRow(row);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi load dữ liệu: " + e.getMessage());
    }
}

// Phương thức lấy phần mở rộng của tệp tin
private String getFileExtension(File file) {
    String fileName = file.getName();
    int dotIndex = fileName.lastIndexOf(".");
    if (dotIndex > 0) {
        return fileName.substring(dotIndex + 1).toLowerCase();  // Lấy phần mở rộng, chuyển sang chữ thường
    }
    return "";  // Trả về chuỗi rỗng nếu không có phần mở rộng
}

    private void btnDinhKemFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDinhKemFileActionPerformed
      initTableTaiLieu(); // Khởi tạo bảng tài liệu
    try {
        JFileChooser fileChooser = new JFileChooser(); // Khởi tạo JFileChooser

        // Thư mục "VanBanNoiBo" trong thư mục gốc dự án
        Path projectRoot = Paths.get(System.getProperty("user.dir"));
        Path allowedFolder = projectRoot.resolve("VanBanNoiBo");

        // Kiểm tra nếu thư mục "VanBanNoiBo" tồn tại, thiết lập làm thư mục mặc định
        File startFolder = allowedFolder.toFile();
        if (startFolder.exists() && startFolder.isDirectory()) {
            fileChooser.setCurrentDirectory(startFolder); // **Thiết lập thư mục mặc định tại đây**
        } else {
            JOptionPane.showMessageDialog(this, "Thư mục VanBanNoiBo không tồn tại. Vui lòng kiểm tra lại.");
            return;
        }

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Chế độ chọn tệp tin

        int result = fileChooser.showOpenDialog(this); // Hiển thị JFileChooser
        if (result == JFileChooser.APPROVE_OPTION) {
            // Xử lý logic khi chọn file
            File selectedFile = fileChooser.getSelectedFile();
            
            // Đường dẫn tuyệt đối của tệp đã chọn
            Path absolutePath = selectedFile.toPath();
            
            // Kiểm tra xem tệp có nằm trong thư mục "VanBanNoiBo" hay không
            if (!absolutePath.startsWith(allowedFolder)) {
                JOptionPane.showMessageDialog(this, "Tệp đã chọn không nằm trong thư mục VanBanNoiBo của dự án.");
                return;
            }
            
            // Chuyển đổi sang đường dẫn tương đối
            Path relativePath = projectRoot.relativize(absolutePath);
            String duongDanTuongDoi = relativePath.toString().replace("\\", "/");

            // Thông tin tài liệu
            String tenTL = selectedFile.getName();
            java.sql.Date sqlNgayTao = new java.sql.Date(System.currentTimeMillis());
            String kichCo = String.valueOf(selectedFile.length()); // Kích thước tệp tin
            
            // Lấy phần mở rộng của tệp
            String loaiTL = getFileExtension(selectedFile); // Lấy phần mở rộng của tệp tin
            
            if (new TaiLieuController().ThemTaiLieu(tenTL, sqlNgayTao, kichCo, loaiTL, duongDanTuongDoi)) {
                JOptionPane.showMessageDialog(this, "Đính kèm tệp tin thành công");
                fillDataTaiLieu();
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi tải lên.");
            }
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }

    }//GEN-LAST:event_btnDinhKemFileActionPerformed

    private void btnXoaFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaFileActionPerformed
        // TODO add your handling code here: // TODO add your handling code here:
        int row = tbDinhKem.getSelectedRow();
        if(row>=0){
            // Lấy ra cột 1 của dòng đã đã chọn
            String MaTaiLieu = tbDinhKem.getValueAt(row, 0).toString();
            try {
                if(new TaiLieuController().XoaFile(MaTaiLieu)){
                    JOptionPane.showMessageDialog(null, "Xóa tài liệu thành công!");
                      fillDataTaiLieu();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Xóa tài liệu thất bại!");
                }
            } catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        }
        else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.");
        }
    }//GEN-LAST:event_btnXoaFileActionPerformed

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

    private void pnChuaTimKiemThongKeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_pnChuaTimKiemThongKeAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_pnChuaTimKiemThongKeAncestorAdded

    private void btnTKVanBandiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKVanBandiActionPerformed
        pnNguoiDung.setVisible(false);
    pnKy.setVisible(false);         
    pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
    pnChuaNoiDung.setVisible(false);
    pnTimKiemThongKe.setVisible(true);
    pnQuanLyVanBan.setVisible(false);
    
    pnChuaTimKiemThongKe.setVisible(true); 
    ThongKeVanBanDi.setVisible(false);      
    ThongKeVanBanDen.setVisible(false);     
    ThongKeVanBanNoiBo.setVisible(false);  
  
    pnChuaTimKiemThongKe.setSelectedIndex(0);
    }//GEN-LAST:event_btnTKVanBandiActionPerformed

    private void btnThongKeVanBanDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeVanBanDenActionPerformed
        // TODO add your handling code here:
                 pnQuanLyVanBan.setVisible(false);
                         pnNguoiDung.setVisible(false);
    pnKy.setVisible(false);
    pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
    pnChuaNoiDung.setVisible(false);
    pnTimKiemThongKe.setVisible(true);
    
    pnChuaTimKiemThongKe.setVisible(true); 
    ThongKeVanBanDi.setVisible(false);      
    ThongKeVanBanDen.setVisible(true);     
    ThongKeVanBanNoiBo.setVisible(false);  
  
    pnChuaTimKiemThongKe.setSelectedIndex(1);
    }//GEN-LAST:event_btnThongKeVanBanDenActionPerformed

    private void btnThongKeVBNBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeVBNBActionPerformed
        // TODO add your handling code here:
                 pnQuanLyVanBan.setVisible(false);
                         pnNguoiDung.setVisible(false);
    pnKy.setVisible(false);
    pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
     pnChuaNoiDung.setVisible(false);
      pnTimKiemThongKe.setVisible(true);
    pnChuaTimKiemThongKe.setVisible(true); 
    ThongKeVanBanDi.setVisible(false);      
    ThongKeVanBanDen.setVisible(false);     
    ThongKeVanBanNoiBo.setVisible(true);  
  
    pnChuaTimKiemThongKe.setSelectedIndex(2);
    }//GEN-LAST:event_btnThongKeVBNBActionPerformed



    private void tbVanBanNoiBo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVanBanNoiBo1MouseClicked

        int row = this.tbVanBanNoiBo1.getSelectedRow();
        if(row!=-1){
        String sokyhieu = this.tbVanBanNoiBo1.getValueAt(row, 0).toString();
        String tenvanban = this.tbVanBanNoiBo1.getValueAt(row, 1).toString();
        String ngayBanHanhStr = this.tbVanBanNoiBo1.getValueAt(row, 2).toString();
        String loaibanhanh = this.tbVanBanNoiBo1.getValueAt(row, 3).toString();
        String phongbanhanh = this.tbVanBanNoiBo1.getValueAt(row, 4).toString();
        String phongbannhan = this.tbVanBanNoiBo1.getValueAt(row, 5).toString();
        String nguoinhan = this.tbVanBanNoiBo1.getValueAt(row, 6).toString();
        String nguoiky = this.tbVanBanNoiBo1.getValueAt(row, 7).toString();
        String nguoiduyet = this.tbVanBanNoiBo1.getValueAt(row, 8).toString();
        String trichyeu = this.tbVanBanNoiBo1.getValueAt(row, 9).toString();
        String noidung = this.tbVanBanNoiBo1.getValueAt(row, 10).toString();
        
        }
        else{
            JOptionPane.showMessageDialog(this, "Vui long chon 1 hang");
        }
        
    }//GEN-LAST:event_tbVanBanNoiBo1MouseClicked

    private void btnXemChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietActionPerformed
       int row = this.tbVanBanNoiBo1.getSelectedRow();
    if (row != -1) {
        // Lấy thông tin từ dòng được chọn
        String sokyhieu = this.tbVanBanNoiBo1.getValueAt(row, 0).toString();
        String tenvanban = this.tbVanBanNoiBo1.getValueAt(row, 1).toString();
        String ngayBanHanhStr = this.tbVanBanNoiBo1.getValueAt(row, 2).toString();
        String loaibanhanh = this.tbVanBanNoiBo1.getValueAt(row, 3).toString();
        String phongbanhanh = this.tbVanBanNoiBo1.getValueAt(row, 4).toString();
        String phongbannhan = this.tbVanBanNoiBo1.getValueAt(row, 5).toString();
        String nguoinhan = this.tbVanBanNoiBo1.getValueAt(row, 6).toString();
        String nguoiky = this.tbVanBanNoiBo1.getValueAt(row, 7).toString();
        String nguoiduyet = this.tbVanBanNoiBo1.getValueAt(row, 8).toString();
        String trichyeu = this.tbVanBanNoiBo1.getValueAt(row, 9).toString();
        String noidung = this.tbVanBanNoiBo1.getValueAt(row, 10).toString();
        
        // Mở form Chi tiết và truyền dữ liệu
        ChiTietVanBanNoiBo vbChiTiet = new ChiTietVanBanNoiBo(
            sokyhieu, tenvanban, ngayBanHanhStr, loaibanhanh, phongbanhanh, phongbannhan,
            nguoinhan, nguoiky, nguoiduyet, trichyeu, noidung);
        vbChiTiet.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng!");
    }
    }//GEN-LAST:event_btnXemChiTietActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
 
    String loaiBanHanh = (String) chonCmbLoaiBanHanh.getSelectedItem();
    String phongBanHanh = (String) chonCmbPhongBanHanh.getSelectedItem();
    String phongBanNhan = (String) ChonCmbPhongBanNhan.getSelectedItem();
    String soKyHieu = nhapSoKyHieu.getText().trim();
    String tenVanBan = nhapTenVanBan.getText().trim();
    String trichYeu = nhapTrichYeu.getText().trim();  // Trường Trích Yếu

    // Lấy giá trị thời gian đã chọn
    String selectedItem = (String) ThoiGianTimKiem.getSelectedItem();
    Date startDate = null, endDate = null;

    // Kiểm tra giá trị đã chọn và thiết lập thời gian bắt đầu và kết thúc
    switch (selectedItem) {
        case "Hôm nay":
            startDate = getTodayStart();
            endDate = getTodayEnd();
            break;
        case "Tuần này":
            startDate = getWeekStart();
            endDate = getWeekEnd();
            break;
        case "Tháng này":
            startDate = getMonthStart();
            endDate = getMonthEnd();
            break;
        case "Năm nay":
            startDate = getYearStart();
            endDate = getYearEnd();
            break;
        case "Năm trước":
            startDate = getPreviousYearStart();
            endDate = getPreviousYearEnd();
            break;
        default:
            break;
    }

    // Nếu chọn "Tất cả" thì sẽ đưa về null (hoặc giá trị mặc định để không ảnh hưởng đến truy vấn)
    if (loaiBanHanh.equals("Tất cả")) loaiBanHanh = null;
    if (phongBanHanh.equals("Tất cả")) phongBanHanh = null;
    if (phongBanNhan.equals("Tất cả")) phongBanNhan = null;

    // Tạo đối tượng controller để tìm kiếm
    VanBanNoiBoController controller = new VanBanNoiBoController();
    try {
        // Tìm kiếm và nhận kết quả
        ArrayList<VanBanNoiBo> resultList = controller.search(loaiBanHanh, phongBanHanh, phongBanNhan, 
                                                                soKyHieu, tenVanBan, trichYeu, startDate, endDate);
        
        // Hiển thị kết quả lên bảng
        DefaultTableModel model = (DefaultTableModel) tbVanBanNoiBo.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ
        for (VanBanNoiBo item : resultList) {
            model.addRow(new Object[] {
                item.getSoKyHieu(),
                item.getTenVanBan(),
                item.getNgayBanHanh(),
                item.getLoaiBanHanh(),
                item.getPhongBanHanh(),
                item.getPhongNhan(),
                item.getNguoiNhan(),
                item.getNguoiKy(),
                item.getNguoiDuyet(),
                item.getTrichYeu(),
                item.getNoiDung()
            });
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage());
    }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnXuatFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcelActionPerformed
        JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    
    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filePath.endsWith(".xlsx")) {
            filePath += ".xlsx"; // Thêm phần mở rộng nếu chưa có
        }
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("VanBanNoiBo");
            TableModel tableModel = tbVanBanNoiBo1.getModel();
            
            // Tạo header (dòng tiêu đề)
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(tableModel.getColumnName(i));

                // Tạo style và định dạng font
                CellStyle style = workbook.createCellStyle();
                org.apache.poi.ss.usermodel.Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }
            
            // Ghi dữ liệu từ bảng vào file Excel
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    Cell cell = dataRow.createCell(j);
                    Object value = tableModel.getValueAt(i, j);
                    if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }
            
            // Ghi file ra đĩa
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            
            JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            
            // Mở file Excel vừa xuất
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                File excelFile = new File(filePath);
                if (excelFile.exists()) {
                    desktop.open(excelFile); // Mở file Excel
                }
            } else {
                JOptionPane.showMessageDialog(this, "Máy tính của bạn không hỗ trợ mở file tự động.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btnXuatFileExcelActionPerformed
//private void initTimKiemVanBanDen() {
//    // Gọi controller để lấy tất cả các văn bản đến
//    VanBanDenController controller = new VanBanDenController();
//    try {
//        // Truy vấn tất cả các văn bản đến mà không có điều kiện tìm kiếm
//        ArrayList<VanBanDenModel> results = controller.searchVanBanDen(null, null, null, null, null, null, null);
//
//        // Hiển thị kết quả vào bảng
//        DefaultTableModel model = (DefaultTableModel) tblTimKiemDanhSachVanBanDen.getModel();
//        model.setRowCount(0); // Xóa dữ liệu cũ
//        for (VanBanDenModel item : results) {
//            model.addRow(new Object[]{
//                item.getTenSo(),
//                item.getNam(),
//                item.getSoKyHieu(),
//                item.getNgayBanHanh(),
//                item.getNoiBanHanh(),
//                item.getLoaiVanBan(),
//                item.getSoDen(),
//                item.getNgayDen(),
//                item.getSoTrang(),
//                item.getNguoiNhan(),
//                item.getNguoiKy(),
//                item.getNguoiDuyet(),
//                item.getTrichYeu(),
//                item.getNoiDung(),
//                item.getDuongDanFile(),
//            });
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Lỗi khi tải tất cả văn bản đến: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//    }
//}

    private void btnTimKiemVanBanDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemVanBanDenActionPerformed
    try {
    String selectedItem = (String) ThoiGianTimKiemDen.getSelectedItem();
    Date startDate = null, endDate = null;

    // Kiểm tra giá trị đã chọn và thiết lập thời gian bắt đầu và kết thúc
    switch (selectedItem) {
        case "Hôm nay":
            startDate = getTodayStart();
            endDate = getTodayEnd();
            break;
        case "Tuần này":
            startDate = getWeekStart();
            endDate = getWeekEnd();
            break;
        case "Tháng này":
            startDate = getMonthStart();
            endDate = getMonthEnd();
            break;
        case "Năm nay":
            startDate = getYearStart();
            endDate = getYearEnd();
            break;
        case "Năm trước":
            startDate = getPreviousYearStart();
            endDate = getPreviousYearEnd();
            break;
        default:
            break;
    }

    // Lấy giá trị tìm kiếm từ các trường nhập liệu
    String input = cboTimKiemSoVanBanDen.getSelectedItem().toString().trim();
    String[] parts = input.split("-");

    String soVanBan = parts.length > 0 ? parts[0].trim() : "";
    int nam = parts.length > 1 ? Integer.parseInt(parts[1].trim()) : 0;
    String loaiVanBanDi = cboTimKiemLoaiVanBanDen.getSelectedItem().toString().trim();
    String soKyHieu = txtTimKiemSoKyHieuDen.getText().trim();
    String noiBanHanh = txtNoiBanHanhTimKiemDen.getText().trim();
    String noiDung = txtTimKiemDen.getText().trim();

    // Kiểm tra và gán null cho các giá trị "Tất cả"
    if (soVanBan.equals("Tất cả")) soVanBan = null;
    if (loaiVanBanDi.equals("Tất cả")) loaiVanBanDi = null;

    System.out.println(soVanBan + " " + loaiVanBanDi + " " + soKyHieu + " " + noiBanHanh + " "  + " " + noiDung + " " + nam);

    // Gọi phương thức tìm kiếm văn bản đến
    VanBanDenController vbdiContrl = new VanBanDenController();
    ArrayList<VanBanDenModel> dsden = vbdiContrl.searchVanBanDen(soVanBan,nam, loaiVanBanDi, soKyHieu, noiBanHanh, noiDung, startDate, endDate);

    // Lấy model bảng để hiển thị kết quả
    DefaultTableModel danhSachDen = (DefaultTableModel) tblTimKiemDanhSachVanBanDen.getModel();
    danhSachDen.setRowCount(0); // Xóa dữ liệu cũ trên bảng

    // Kiểm tra và hiển thị kết quả
    if (dsden.isEmpty()) {
        // Nếu không tìm thấy dữ liệu
        JOptionPane.showMessageDialog(null, "Không tìm thấy văn bản nào thỏa mãn.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    } else {
        // Hiển thị kết quả tìm kiếm
        for (int i = 0; i < dsden.size(); i++) {
            VanBanDenModel vbDen = dsden.get(i);
            danhSachDen.addRow(new Object[] {
                i + 1, // STT
                vbDen.getTenSo(),
                vbDen.getLoaiVanBan(),
                vbDen.getSoKyHieu(),
                vbDen.getSoDen(),
                vbDen.getNgayBanHanh(),
                vbDen.getNgayDen(),
                vbDen.getNoiBanHanh(),
                vbDen.getTrichYeu()
            });
        }
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
}

    }//GEN-LAST:event_btnTimKiemVanBanDenActionPerformed

    private void btnXemChiTiet1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTiet1ActionPerformed
    

    DefaultTableModel tableTimKiemDen = (DefaultTableModel) tblTimKiemDanhSachVanBanDen.getModel();
    int row = tblTimKiemDanhSachVanBanDen.getSelectedRow();
    
    // Kiểm tra xem có dòng nào được chọn không
    if (row >= 0) {
        
        vanBanDenChiTiet = dstimkiemden.get(row);
        System.out.println(vanBanDenChiTiet.toString());
         FormChiTietVanBanDen form = new FormChiTietVanBanDen();
    form.show();
    } else {
        System.out.println("Chưa chọn dòng nào.");
    }
    }//GEN-LAST:event_btnXemChiTiet1ActionPerformed

    private void btnXuatFileExcel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcel1ActionPerformed
            try {
    // Tạo JFileChooser để chọn nơi lưu file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filePath.endsWith(".xlsx")) {
            filePath += ".xlsx"; // Thêm phần mở rộng nếu chưa có
        }

        // Lấy dữ liệu từ JTable
        JTable table = tblDanhSachVanBanDen; // Đảm bảo sử dụng đúng tên biến cho bảng của bạn
        int rowCount = table.getRowCount();
        int columnCount = table.getColumnCount();

        // Tạo một Workbook Excel mới
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Văn Bản Đến");

            // Thêm tiêu đề vào Excel
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < columnCount; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(table.getColumnName(col));

                // Tạo style cho tiêu đề
                CellStyle style = workbook.createCellStyle();
                org.apache.poi.ss.usermodel.Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Thêm dữ liệu bảng vào Excel
            for (int row = 0; row < rowCount; row++) {
                Row excelRow = sheet.createRow(row + 1); // Dữ liệu bắt đầu từ dòng 2
                for (int col = 0; col < columnCount; col++) {
                    Cell cell = excelRow.createCell(col);
                    Object value = table.getValueAt(row, col);
                    if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }

            // Ghi file ra đĩa
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Mở file Excel sau khi xuất
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                File excelFile = new File(filePath);
                if (excelFile.exists()) {
                    desktop.open(excelFile); // Mở file Excel
                }
            } else {
                JOptionPane.showMessageDialog(this, "Máy tính của bạn không hỗ trợ mở file tự động.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }
}}
catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        
    
    }
            
    }//GEN-LAST:event_btnXuatFileExcel1ActionPerformed

    private void btnSoVanBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoVanBanActionPerformed
        pnThan.setVisible(true);
        pnNguoiDung.setVisible(false);
    pnKy.setVisible(false);
        pnChuaThan.setVisible(true);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(0); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_btnSoVanBanActionPerformed

    private void btnThemSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSoActionPerformed
        // Kiểm tra nếu danh sách dsvb là null, cần khởi tạo nó
        if (dsvb == null) {
            dsvb = new ArrayList<>();
        }

        DefaultTableModel tablemodel = (DefaultTableModel) tblSoVanBan.getModel(); // Lấy mô hình bảng hiện tại

        // Kiểm tra nếu bảng đã có một dòng trống, không thêm dòng mới nữa
        if (tablemodel.getRowCount() > 0) {
            Object lastRowData = tablemodel.getValueAt(tablemodel.getRowCount() - 1, 1);
            if (lastRowData == null || ((String) lastRowData).trim().isEmpty()) {
                // Nếu dòng cuối cùng có dữ liệu trống, không thêm dòng mới
                return;
            }
        }

        // Tạo đối tượng mới với giá trị mặc định
        SoVanBanModel newSoVanBan = new SoVanBanModel();
        newSoVanBan.setSoVanBan("");  // Giá trị mặc định
        newSoVanBan.setSoDen(false);  // Giá trị mặc định
        newSoVanBan.setNam(Integer.parseInt(cboNam.getSelectedItem().toString())); // Giá trị mặc định

        // Thêm đối tượng vào danh sách dsvb
        dsvb.add(newSoVanBan);
        // Thêm dòng trống vào bảng
        tablemodel.addRow(new Object[] {tablemodel.getRowCount() + 1, "", false, Integer.parseInt(cboNam.getSelectedItem().toString())});
        // Đảm bảo cột STT không được chỉnh sửa, và các cột khác có thể chỉnh sửa
        tblSoVanBan.getColumnModel().getColumn(0).setCellEditor(null); // Cột STT không thể chỉnh sửa
        tblSoVanBan.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JTextField())); // Cột "Sổ Văn Bản" có thể chỉnh sửa
        tblSoVanBan.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JCheckBox())); // Cột "Sổ Đến" có thể chỉnh sửa (Checkbox)
        tblSoVanBan.getColumnModel().getColumn(3).setCellEditor(null);  // Cột "Năm" có thể chỉnh sửa
    }//GEN-LAST:event_btnThemSoActionPerformed

    private void btnTaiLaiSoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiLaiSoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaiLaiSoMouseClicked

    private void btnXoaSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSoActionPerformed
        // Lấy chỉ số dòng đã chọn trong bảng
        int selectedRow = tblSoVanBan.getSelectedRow();

        // Kiểm tra nếu không có dòng nào được chọn
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài liệu cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Hiển thị hộp thoại xác nhận xóa
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa tài liệu này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.NO_OPTION) {
            return;  // Nếu người dùng chọn "No", không thực hiện xóa
        }

        // Lấy đối tượng SoVanBanModel từ danh sách dsvb dựa trên chỉ số dòng
        SoVanBanModel soVanBanModel = dsvb.get(selectedRow); // Lấy đối tượng tại dòng được chọn

        // Tạo controller để gọi phương thức xóa
        SoVanBanController soControl = new SoVanBanController();
        int result = soControl.XoaTaiLieu(soVanBanModel);  // Gọi phương thức xóa tài liệu

        // Kiểm tra kết quả xóa
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Xóa tài liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            // Cập nhật lại bảng sau khi xóa thành công
            dsvb.remove(selectedRow);  // Loại bỏ đối tượng khỏi danh sách dsvb
            HienThiTable(); // Cập nhật lại bảng
        } else {
            JOptionPane.showMessageDialog(this, "Xóa tài liệu thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnXoaSoActionPerformed

    private void btnLuuSoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuSoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuSoMouseClicked

    private void btnLuuSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuSoActionPerformed
        try {
            DefaultTableModel tablemodel = (DefaultTableModel) tblSoVanBan.getModel(); // Lấy mô hình bảng hiện tại
            boolean isSuccess = true;

            SoVanBanController soController = new SoVanBanController();

            // Duyệt qua từng dòng của bảng
            for (int row = 0; row < tablemodel.getRowCount(); row++) {
                String soVanBan = (String) tablemodel.getValueAt(row, 1);
                Boolean soDen = (Boolean) tablemodel.getValueAt(row, 2);
                Integer nam = (Integer) tablemodel.getValueAt(row, 3);

                if (soVanBan == null || soVanBan.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Sổ văn bản không được để trống tại dòng " + (row + 1), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                SoVanBanModel soVanBanModel = dsvb.get(row);
                int maSo = soVanBanModel.getMaSo();
                soVanBanModel.setSoVanBan(soVanBan);
                soVanBanModel.setSoDen(soDen);
                soVanBanModel.setNam(nam);

                int result;
                if (maSo == 0) {
                    // Bản ghi mới -> gọi hàm Them
                    result = soController.Them(soVanBanModel);
                } else {
                    // Bản ghi cũ -> gọi hàm Luu
                    result = soController.Luu(soVanBanModel);
                }

                if (result <= 0) {
                    isSuccess = false;
                    JOptionPane.showMessageDialog(this, "Lưu thất bại tại dòng: " + (row + 1), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "Lưu dữ liệu thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi ngoại lệ", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLuuSoActionPerformed

    private void btnXuatExcelSoVanBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelSoVanBanActionPerformed
                    try {
    // Lấy dữ liệu từ JTable
    JTable table = tblSoVanBan; // Đảm bảo sử dụng đúng tên biến cho bảng của bạn
    int rowCount = table.getRowCount();
    int columnCount = table.getColumnCount();

    // Tạo một Workbook Excel mới
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Sổ văn bản");

    // Thêm tiêu đề vào Excel
    XSSFRow headerRow = sheet.createRow(0);
    for (int col = 0; col < columnCount; col++) {
        XSSFCell cell = headerRow.createCell(col);
        cell.setCellValue(table.getColumnName(col));
    }

    // Thêm dữ liệu bảng vào Excel
    for (int row = 0; row < rowCount; row++) {
        XSSFRow excelRow = sheet.createRow(row + 1); // Dữ liệu bắt đầu từ dòng 2
        for (int col = 0; col < columnCount; col++) {
            XSSFCell cell = excelRow.createCell(col);
            Object value = table.getValueAt(row, col);
            if (value != null) {
                cell.setCellValue(value.toString());
            } else {
                cell.setCellValue(""); // Thêm giá trị mặc định nếu là null
            }
        }
    }

    // Đảm bảo thư mục tồn tại
    File directory = new File("Tai xuong"); // Đường dẫn tương đối
    if (!directory.exists()) {
        directory.mkdirs(); // Tạo thư mục nếu chưa tồn tại
    }

    // Đường dẫn mới để lưu file Excel
    File file = new File(directory, "Sovanban.xlsx");
    try (FileOutputStream fileOut = new FileOutputStream(file)) {
        workbook.write(fileOut);
        JOptionPane.showMessageDialog(this, "Xuất Excel thành công!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi lưu file Excel: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            workbook.close(); // Đảm bảo đóng workbook sau khi hoàn thành
        } catch (Exception e) {
            // Xử lý nếu có lỗi khi đóng workbook
            e.printStackTrace();
        }
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_btnXuatExcelSoVanBanActionPerformed

    private void cboNamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboNamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNamMouseClicked

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        HienThiTable();
    }//GEN-LAST:event_cboNamActionPerformed

    private void btnThemLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiActionPerformed
        try {
        // Khởi tạo danh sách nếu chưa tồn tại
        if (dsl == null) {
            dsl = new ArrayList<>();
        }

        // Lấy mô hình bảng
        DefaultTableModel tablemodel = (DefaultTableModel) tblLoaiVanBan.getModel();

        // Kiểm tra nếu bảng đã có một dòng trống, không thêm dòng mới
        if (tablemodel.getRowCount() > 0) {
            Object lastRowData = tablemodel.getValueAt(tablemodel.getRowCount() - 1, 1);
            if (lastRowData == null || lastRowData.toString().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Dòng cuối cùng còn trống. Vui lòng hoàn thành trước khi thêm dòng mới.", 
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Tạo đối tượng LoaiVanBanModel mới
        LoaiVanBanModel newLoai = new LoaiVanBanModel();
        newLoai.setMaLoai(""); // Giá trị mặc định trống
        newLoai.setLoaiVanBan(""); // Giá trị mặc định
        newLoai.setMoTa(""); // Giá trị mặc định

        // Thêm đối tượng mới vào danh sách
        dsl.add(newLoai);

        // Thêm dòng trống vào bảng
        tablemodel.addRow(new Object[] {
            tablemodel.getRowCount() + 1, // STT
            "",                          // Mã loại
            "",          // Loại văn bản
            ""             // Mô tả
        });

       

    } catch (Exception ex) {
        // Xử lý lỗi chung
        JOptionPane.showMessageDialog(this, 
            "Đã xảy ra lỗi: " + ex.getMessage(), 
            "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnThemLoaiActionPerformed

    private void btnTaiLaiLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiLaiLoaiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaiLaiLoaiMouseClicked

    private void btnTaiLaiLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiLaiLoaiActionPerformed
        HienThiTableLoaiVanBan();
    }//GEN-LAST:event_btnTaiLaiLoaiActionPerformed

    private void btnXoaLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLoaiActionPerformed
        try {
        DefaultTableModel model = (DefaultTableModel) tblLoaiVanBan.getModel();
        int rowSelected = tblLoaiVanBan.getSelectedRow();

        // Kiểm tra nếu không có hàng nào được chọn
        if (rowSelected == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài liệu để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy mã loại từ bảng
        String maLoai = tblLoaiVanBan.getValueAt(rowSelected, 1).toString(); // Cột mã loại
        System.out.println(maLoai);

        // Hiển thị hộp thoại xác nhận
        int lc = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa tài liệu này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);

        if (lc == JOptionPane.YES_OPTION) {
            // Gọi phương thức xóa từ controller
            LoaiVanBanController loaiVBController = new LoaiVanBanController();
            int result = loaiVBController.Xoa(maLoai);

            if (result > 0) {
                // Xóa thành công: cập nhật lại bảng
                JOptionPane.showMessageDialog(this, "Xóa tài liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                HienThiTableLoaiVanBan(); // Hàm cập nhật lại bảng
            } else {
                // Xóa thất bại
                JOptionPane.showMessageDialog(this, "Xóa tài liệu thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (Exception ex) {
        // Xử lý lỗi chung
        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnXoaLoaiActionPerformed

    private void btnLuuLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuLoaiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuLoaiMouseClicked

    private void btnLuuLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuLoaiActionPerformed
       try {
            DefaultTableModel tablemodel = (DefaultTableModel) tblLoaiVanBan.getModel(); // Lấy mô hình bảng hiện tại
            boolean isSuccess = true;

            LoaiVanBanController loaiController = new LoaiVanBanController();

            // Duyệt qua từng dòng của bảng
            for (int row = 0; row < tablemodel.getRowCount(); row++) {
                String maLoai = (String) tablemodel.getValueAt(row, 1);
                String tenLoai = (String) tablemodel.getValueAt(row, 2);
                String moTa = (String) tablemodel.getValueAt(row, 3);

                if (maLoai == null || maLoai.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Mã loại văn bản không được để trống tại dòng " + (row + 1), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (tenLoai == null || tenLoai.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Loại văn bản không được để trống tại dòng " + (row + 1), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                LoaiVanBanModel loaiVanBanModel = dsl.get(row);
                loaiVanBanModel.setMaLoai(maLoai);
                loaiVanBanModel.setLoaiVanBan(tenLoai);
                loaiVanBanModel.setMoTa(moTa);
                loaiVanBanModel.setDaXoa(false);

                int result;
                if (!loaiController.kiemTraTonTai(maLoai)) {
                    // Bản ghi mới -> gọi hàm Them
                    result = loaiController.Them(loaiVanBanModel);
                } else {
                    // Bản ghi cũ -> gọi hàm Luu
                    result = loaiController.Update(loaiVanBanModel);
                }

                if (result <= 0) {
                    isSuccess = false;
                    JOptionPane.showMessageDialog(this, "Lưu thất bại tại dòng: " + (row + 1), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "Lưu dữ liệu thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi ngoại lệ", JOptionPane.ERROR_MESSAGE);
        }                         
    
    }//GEN-LAST:event_btnLuuLoaiActionPerformed

    private void btnXuatExcelLoaiVanBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelLoaiVanBanActionPerformed
    try {
    // Tạo JFileChooser để chọn nơi lưu file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filePath.endsWith(".xlsx")) {
            filePath += ".xlsx"; // Thêm phần mở rộng nếu chưa có
        }

        // Lấy dữ liệu từ JTable
        JTable table = tblLoaiVanBan; // Đảm bảo sử dụng đúng tên biến cho bảng của bạn
        int rowCount = table.getRowCount();
        int columnCount = table.getColumnCount();

        // Tạo một Workbook Excel mới
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Loại văn bản");

            // Thêm tiêu đề vào Excel
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < columnCount; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(table.getColumnName(col));

                // Tạo style cho header
                CellStyle style = workbook.createCellStyle();
                org.apache.poi.ss.usermodel.Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Thêm dữ liệu bảng vào Excel
            for (int row = 0; row < rowCount; row++) {
                Row excelRow = sheet.createRow(row + 1); // Dữ liệu bắt đầu từ dòng 2
                for (int col = 0; col < columnCount; col++) {
                    Cell cell = excelRow.createCell(col);
                    Object value = table.getValueAt(row, col);
                    if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }

            // Ghi file ra đĩa
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Mở file Excel vừa xuất
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                File excelFile = new File(filePath);
                if (excelFile.exists()) {
                    desktop.open(excelFile); // Mở file Excel
                }
            } else {
                JOptionPane.showMessageDialog(this, "Máy tính của bạn không hỗ trợ mở file tự động.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_btnXuatExcelLoaiVanBanActionPerformed

    private void btnThemNoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNoiActionPerformed
             // Kiểm tra nếu danh sách dsvb là null, cần khởi tạo nó
        if (dsnoi == null) {
            dsnoi = new ArrayList<>();
        }

        DefaultTableModel tablemodel = (DefaultTableModel) tblNoiBanHanh.getModel(); // Lấy mô hình bảng hiện tại

        // Kiểm tra nếu bảng đã có một dòng trống, không thêm dòng mới nữa
        if (tablemodel.getRowCount() > 0) {
            Object lastRowData = tablemodel.getValueAt(tablemodel.getRowCount() - 1, 1);
            if (lastRowData == null || ((String) lastRowData).trim().isEmpty()) {
                // Nếu dòng cuối cùng có dữ liệu trống, không thêm dòng mới
                return;
            }
        }

        // Tạo đối tượng mới với giá trị mặc định
        NoiBanHanhModel newPlace = new NoiBanHanhModel();
        newPlace.setNoiBanHanh("");  // Giá trị mặc định
        newPlace.setMoTa("");

        // Thêm đối tượng vào danh sách dsvb
        dsnoi.add(newPlace);
        // Thêm dòng trống vào bảng
        tablemodel.addRow(new Object[] {tablemodel.getRowCount() + 1, "", "", ""});
        // Đảm bảo cột STT không được chỉnh sửa, và các cột khác có thể chỉnh sửa
        tblNoiBanHanh.getColumnModel().getColumn(0).setCellEditor(null); 
        tblNoiBanHanh.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JTextField())); 
        tblNoiBanHanh.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JTextField())); 
 
    }//GEN-LAST:event_btnThemNoiActionPerformed

    private void btnTaiLaiNoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiLaiNoiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaiLaiNoiMouseClicked

    private void btnTaiLaiNoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiLaiNoiActionPerformed
        HienThiTableNoiBanHanh();
    }//GEN-LAST:event_btnTaiLaiNoiActionPerformed

    private void btnXoaNoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNoiActionPerformed
        
        int selectedRow = tblNoiBanHanh.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nơi ban hành cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nơi ban hành này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.NO_OPTION) {
            return; 
        }

        NoiBanHanhModel noiModel = dsnoi.get(selectedRow);
        for(NoiBanHanhModel noi:dsnoi)
        {
            System.out.println(noi.toString());
        }
        NoiBanHanhController noiControl = new NoiBanHanhController();
        int result = noiControl.XoaNoiBanHanh(noiModel);  // Gọi phương thức xóa tài liệu

        // Kiểm tra kết quả xóa
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Xóa tài liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            //dsnoi.remove(selectedRow);
            HienThiTableNoiBanHanh(); // Cập nhật lại bảng
        } else {
            JOptionPane.showMessageDialog(this, "Xóa tài liệu thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        HienThiTable();
    }//GEN-LAST:event_btnXoaNoiActionPerformed

    private void btnLuuNoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuNoiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuNoiMouseClicked

    private void btnLuuNoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuNoiActionPerformed
        try {
        DefaultTableModel tablemodel = (DefaultTableModel) tblNoiBanHanh.getModel(); // Lấy mô hình bảng hiện tại
        boolean isSuccess = true;

        NoiBanHanhController noiController = new NoiBanHanhController();

        // Duyệt qua từng dòng của bảng
        for (int row = 0; row < tablemodel.getRowCount(); row++) {
            String noiBanHanh = (String) tablemodel.getValueAt(row, 1);
            String moTa = (String) tablemodel.getValueAt(row, 2);

            if (noiBanHanh == null || noiBanHanh.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nơi ban hành không được để trống tại dòng " + (row + 1), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }

            NoiBanHanhModel noiModel = dsnoi.get(row);
            int maNoi = noiModel.getMaNoiBanHanh();
            noiModel.setNoiBanHanh(noiBanHanh);
            noiModel.setMoTa(moTa);
            noiModel.setDaXoa(false);

            int result;
            if (maNoi == 0) {
                // Bản ghi mới -> gọi hàm Them
                result = noiController.ThemNoiBanHanh(noiModel);
            } else {
                // Bản ghi cũ -> gọi hàm CapNhat
                result = noiController.CapNhatNoiBanHanh(noiModel);
            }

            if (result <= 0) {
                isSuccess = false;
                JOptionPane.showMessageDialog(this, "Lưu thất bại tại dòng: " + (row + 1), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (isSuccess) {
            JOptionPane.showMessageDialog(this, "Lưu dữ liệu thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi ngoại lệ", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnLuuNoiActionPerformed

    private void btnXuatExcelNoiBanHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelNoiBanHanhActionPerformed
        try {
    // Tạo JFileChooser để chọn nơi lưu file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filePath.endsWith(".xlsx")) {
            filePath += ".xlsx"; // Thêm phần mở rộng nếu chưa có
        }

        // Lấy dữ liệu từ JTable
        JTable table = tblNoiBanHanh; // Đảm bảo sử dụng đúng tên biến cho bảng của bạn
        int rowCount = table.getRowCount();
        int columnCount = table.getColumnCount();

        // Tạo một Workbook Excel mới
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Nơi ban hành");

            // Thêm tiêu đề vào Excel
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < columnCount; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(table.getColumnName(col));

                // Tạo style cho header
                CellStyle style = workbook.createCellStyle();
                org.apache.poi.ss.usermodel.Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Thêm dữ liệu bảng vào Excel
            for (int row = 0; row < rowCount; row++) {
                Row excelRow = sheet.createRow(row + 1); // Dữ liệu bắt đầu từ dòng 2
                for (int col = 0; col < columnCount; col++) {
                    Cell cell = excelRow.createCell(col);
                    Object value = table.getValueAt(row, col);
                    if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }

            // Ghi file ra đĩa
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Mở file Excel vừa xuất
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                File excelFile = new File(filePath);
                if (excelFile.exists()) {
                    desktop.open(excelFile); // Mở file Excel
                }
            } else {
                JOptionPane.showMessageDialog(this, "Máy tính của bạn không hỗ trợ mở file tự động.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
}

    }//GEN-LAST:event_btnXuatExcelNoiBanHanhActionPerformed

    private void btnThemVanBanDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVanBanDenActionPerformed
   VanBanDenController vbController = new VanBanDenController();
   
// Kiểm tra dữ liệu nhập
String input = cboSoVanBanDen.getSelectedItem().toString().trim();
if (input == null || input.isEmpty() || !input.contains("-")) {
    JOptionPane.showMessageDialog(this, "Chuỗi không hợp lệ. Vui lòng nhập theo định dạng 'Tên văn bản - Năm'.", "Thông báo", JOptionPane.WARNING_MESSAGE);
    return;
}

VanBanDenModel vb = new VanBanDenModel();
String tenso = null;
int nam = 0;

String[] parts = input.split("-");
if (parts.length == 2) {
    tenso = parts[0].trim();
    try {
        nam = Integer.parseInt(parts[1].trim());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Năm không hợp lệ. Vui lòng nhập năm hợp lệ.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }
} else {
    JOptionPane.showMessageDialog(this, "Chuỗi không đúng định dạng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
    return;
}

vb.setTenSo(tenso);
vb.setNam(nam);

// Kiểm tra các trường nhập liệu khác
if (cboNoiBanHanhDen.getSelectedItem() == null || cboNoiBanHanhDen.getSelectedItem().toString().trim().isEmpty()) {
    JOptionPane.showMessageDialog(this, "Vui lòng chọn nơi ban hành.", "Thông báo", JOptionPane.WARNING_MESSAGE);
    return;
}

if (cboLoaiVanBanDen.getSelectedItem() == null || cboLoaiVanBanDen.getSelectedItem().toString().trim().isEmpty()) {
    JOptionPane.showMessageDialog(this, "Vui lòng chọn loại văn bản.", "Thông báo", JOptionPane.WARNING_MESSAGE);
    return;
}

// Kiểm tra ngày tháng
if (dateNgayBanHanhDen.getDate() == null || dateNgayDen.getDate() == null) {
    JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ ngày tháng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
    return;
}

vb.setSoKyHieu(txtSoKyHieuDen.getText().trim());
vb.setNgayBanHanh(dateNgayBanHanhDen.getDate());
vb.setNoiBanHanh(cboNoiBanHanhDen.getSelectedItem().toString().trim());
vb.setLoaiVanBan(cboLoaiVanBanDen.getSelectedItem().toString().trim());

try {
    vb.setSoDen(Integer.parseInt(txtSoDen.getText().trim()));
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Số đến phải là một số hợp lệ.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
    return;
}

vb.setNgayDen(dateNgayDen.getDate());
vb.setSoTrang(Integer.parseInt(txtSoTrangDen.getText().trim()));
vb.setNguoiNhan(txtNguoiNhanDen.getText().trim());
vb.setNguoiKy(txtNguoiKyDen.getText().trim());
vb.setNguoiDuyet(txtNguoiDuyetDen.getText().trim());
vb.setTrichYeu(txtTrichYeuDen.getText().trim());
vb.setNoiDung(txtNoiDungDen.getText().trim());
vb.setDuongDanFile(txtDinhKemFileDen.getText().trim());
vb.setDaXoa(false); // Mặc định là chưa xóa

// Kiểm tra xem file đính kèm có hợp lệ không
String filePath = txtDinhKemFileDen.getText().trim();
if (filePath == null || filePath.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Vui lòng chọn file đính kèm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
    return;
}

int result = vbController.Them(vb);
if (result > 0) {
    JOptionPane.showMessageDialog(this, "Thêm văn bản đến thành công!");

    // Tiến hành tải xuống file từ thư mục đích
    File sourceFile = new File("Quan ly van ban/" + filePath);
    File destinationDirectory = new File("Van ban den");

    try {
        // Kiểm tra file nguồn
        if (!sourceFile.exists()) {
            JOptionPane.showMessageDialog(this, "File nguồn không tồn tại: " + sourceFile.getPath(), "Lỗi file", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Đảm bảo thư mục đích tồn tại
        if (!destinationDirectory.exists()) {
            boolean created = destinationDirectory.mkdirs();
            if (!created) {
                JOptionPane.showMessageDialog(this, "Không thể tạo thư mục đích: " + destinationDirectory.getPath(), "Lỗi thư mục", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Đường dẫn file đích
        File destinationFile = new File(destinationDirectory, sourceFile.getName());
        System.out.println("Đang sao chép file từ: " + sourceFile.getPath() + " đến: " + destinationFile.getPath());

        // Sao chép file
        Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi sao chép file: " + e.getMessage(), "Lỗi tải file", JOptionPane.ERROR_MESSAGE);
    }
} else {
    JOptionPane.showMessageDialog(this, "Thêm văn bản đến thất bại!");
}

    }//GEN-LAST:event_btnThemVanBanDenActionPerformed

    private void btnXoaVanBanDenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaVanBanDenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaVanBanDenMouseClicked

    private void btnLuuVanBanDenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuVanBanDenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuVanBanDenMouseClicked

    private void btnLuuVanBanDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuVanBanDenActionPerformed
      try {
        VanBanDenController vbDenContrl = new VanBanDenController();
        
        // Lấy văn bản đã chọn từ danh sách và lấy id
        VanBanDenModel vanbanden = dsvbden.get(rowSelectedDen);
        int id = vanbanden.getId();
        System.out.println("ID LA:" + id);
        
        // Khởi tạo đối tượng Văn Bản Đến
        VanBanDenModel vb = new VanBanDenModel();
        vb.setId(id);  // Gán id vào văn bản

        String input = cboSoVanBanDen.getSelectedItem().toString().trim();
        String tenso = null;
        int nam = 0;

        // Kiểm tra định dạng chuỗi
        if (input == null || !input.contains("-")) {
            JOptionPane.showMessageDialog(this, "Chuỗi không hợp lệ. Vui lòng nhập theo định dạng 'Tên văn bản - Năm'.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            String[] parts = input.split("-");
            if (parts.length == 2) {
                tenso = parts[0].trim();
                try {
                    nam = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Năm không hợp lệ. Vui lòng nhập năm hợp lệ.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chuỗi không đúng định dạng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        vb.setTenSo(tenso);
        vb.setNam(nam);

        // Kiểm tra ngày tháng
        if (dateNgayBanHanhDen.getDate() == null || dateNgayDen.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ ngày tháng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        vb.setSoKyHieu(txtSoKyHieuDen.getText().trim());
        vb.setNgayBanHanh(dateNgayBanHanhDen.getDate());
        vb.setNoiBanHanh(cboNoiBanHanhDen.getSelectedItem().toString().trim());
        vb.setLoaiVanBan(cboLoaiVanBanDen.getSelectedItem().toString().trim());

        // Kiểm tra số đến
        try {
            vb.setSoDen(Integer.parseInt(txtSoDen.getText().trim()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số đến phải là một số hợp lệ.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
            return;
        }

        vb.setNgayDen(dateNgayDen.getDate());
        vb.setSoTrang(Integer.parseInt(txtSoTrangDen.getText().trim()));
        vb.setNguoiNhan(txtNguoiNhanDen.getText().trim());
        vb.setNguoiKy(txtNguoiKyDen.getText().trim());
        vb.setNguoiDuyet(txtNguoiDuyetDen.getText().trim());
        vb.setTrichYeu(txtTrichYeuDen.getText().trim());
        vb.setNoiDung(txtNoiDungDen.getText().trim());
        vb.setDuongDanFile(txtDinhKemFileDen.getText().trim());
        vb.setDaXoa(false); // Mặc định là chưa xóa

        // Cập nhật văn bản đến
        int result = vbDenContrl.CapNhat(vb);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Cập nhật văn bản đến thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật văn bản đến thất bại!");
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi ngoại lệ", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnLuuVanBanDenActionPerformed

    private void btnXuatExcelVanBanDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelVanBanDenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatExcelVanBanDenActionPerformed

    private void btnTaiLaiVanBanDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiLaiVanBanDenActionPerformed
        btnThemVanBanDen.setEnabled(true);
                VanBanDenController vbController=new VanBanDenController();
          txtSoDen.setText("");
    txtSoKyHieuDen.setText("");
    txtSoTrangDen.setText("");
    txtNguoiDuyetDen.setText("");
    txtNguoiNhanDen.setText("");
    txtNguoiKyDen.setText("");
    txtNoiDungDen.setText("");
    txtTrichYeuDen.setText("");
    txtDinhKemFileDen.setText("");
    cboSoVanBanDen.setSelectedIndex(-1);
    cboNoiBanHanhDen.setSelectedIndex(-1);
    cboLoaiVanBanDen.setSelectedIndex(-1);
    Date currentDate = new Date();  // Lấy ngày hiện tại
    dateNgayBanHanhDen.setDate(currentDate); 
    dateNgayDen.setDate(currentDate); 
    }//GEN-LAST:event_btnTaiLaiVanBanDenActionPerformed

    private void btnDinhKemFile4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDinhKemFile4ActionPerformed
         try {
             txtDinhKemFileDen.setText("");
    // Đặt thư mục cho phép chọn file (đường dẫn tương đối)
    JFileChooser fileChooser = new JFileChooser();
    File allowedDirectory = new File("Quan ly van ban"); // Đường dẫn tương đối
    File destinationDirectory = new File("Van ban di");  // Đường dẫn tương đối

    // Thiết lập thư mục mặc định khi mở JFileChooser
    fileChooser.setCurrentDirectory(allowedDirectory);
    fileChooser.setDialogTitle("Chọn file đính kèm từ thư mục cho phép");

    // Hiển thị hộp thoại để chọn file
    int userSelection = fileChooser.showOpenDialog(this);

    // Kiểm tra nếu người dùng đã chọn file
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();

        // Kiểm tra nếu file được chọn nằm trong thư mục cho phép
        if (selectedFile.getCanonicalPath().startsWith(allowedDirectory.getCanonicalPath())) {
            txtDinhKemFileDen.setText(selectedFile.getName()); 
            // Kiểm tra và tạo thư mục đích nếu chưa tồn tại
            if (!destinationDirectory.exists()) {
                destinationDirectory.mkdirs();
            }

            // Tạo file đích và sao chép file vào thư mục đích
            File destinationFile = new File(destinationDirectory, selectedFile.getName());
            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

           
        } else {
            // Nếu file không nằm trong thư mục cho phép, thông báo cảnh báo
            JOptionPane.showMessageDialog(this, "Bạn chỉ được chọn file trong thư mục: " + allowedDirectory.getAbsolutePath(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }
} catch(Exception ex) {
    // Bắt lỗi và hiển thị thông báo
    JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi ngoại lệ", JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_btnDinhKemFile4ActionPerformed

    private void btnThemVanBanDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVanBanDiActionPerformed
     // Lấy dữ liệu nhập từ giao diện

    String input = cboSoVanBanDi.getSelectedItem().toString().trim();
    if (input == null || input.isEmpty() || !input.contains("-")) {
        JOptionPane.showMessageDialog(this, "Chuỗi không hợp lệ. Vui lòng nhập theo định dạng 'Tên văn bản - Năm'.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    VanBanDiModel vbDi = new VanBanDiModel();
    String tenso = null;
    int nam = 0;

    String[] parts = input.split("-");
    if (parts.length == 2) {
        tenso = parts[0].trim();
        try {
            nam = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Năm không hợp lệ. Vui lòng nhập năm hợp lệ.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
    } else {
        JOptionPane.showMessageDialog(this, "Chuỗi không đúng định dạng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    vbDi.setTenSo(tenso);
    vbDi.setNam(nam);

    // Kiểm tra các trường nhập liệu khác
    if(txtNoiNhanDi.getText().toString().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn nơi nhận.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (cboLoaiVanBanDi.getSelectedItem() == null || cboLoaiVanBanDi.getSelectedItem().toString().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn loại văn bản.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Kiểm tra ngày tháng
    if (dateNgayBanHanhDi.getDate() == null || dateNgayBanHanhDi.getDate() == null) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ ngày tháng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    vbDi.setSoKyHieu(txtSoKiHieuDi.getText().trim());
    vbDi.setNgayBanHanh(dateNgayBanHanhDi.getDate());
    vbDi.setNoiNhan(txtNoiNhanDi.getText().toString().trim());
    vbDi.setLoaiVanBan(cboLoaiVanBanDi.getSelectedItem().toString().trim());

    try {
        vbDi.setSoDi(Integer.parseInt(txtSoDi.getText().trim()));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Số đi phải là một số hợp lệ.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
        return;
    }

    vbDi.setNgayBanHanh(dateNgayBanHanhDi.getDate());
    vbDi.setSoTrang(Integer.parseInt(txtSoTrangDi.getText().trim()));
    vbDi.setNguoiGui(txtNguoiGuiDi.getText().trim());
    vbDi.setNguoiKy(txtNguoiKyDi.getText().trim());
    vbDi.setNguoiDuyet(txtNguoiDuyetDi.getText().trim());
    vbDi.setTrichYeu(txtTrichYeuDi.getText().trim());
    vbDi.setNoiDung(txtNoiDungDi.getText().trim());
    vbDi.setDuongDanFile(txtDinhKemFileDi.getText().trim());
    vbDi.setDaXoa(false); // Mặc định là chưa xóa

    // Kiểm tra xem file đính kèm có hợp lệ không
    String filePath = txtDinhKemFileDi.getText().trim();
    if (filePath == null || filePath.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn file đính kèm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Thêm văn bản đi vào cơ sở dữ liệu
    VanBanDiController vbDiController = new VanBanDiController();
    int result = vbDiController.Them(vbDi);
    if (result > 0) {
        JOptionPane.showMessageDialog(this, "Thêm văn bản đi thành công!");

        // Tiến hành tải xuống file từ thư mục đích
        File sourceFile = new File("Quan ly van ban/" + filePath);
        File destinationDirectory = new File("Van ban di");

        try {
            // Kiểm tra file nguồn
            if (!sourceFile.exists()) {
                JOptionPane.showMessageDialog(this, "File nguồn không tồn tại: " + sourceFile.getPath(), "Lỗi file", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Đảm bảo thư mục đích tồn tại
            if (!destinationDirectory.exists()) {
                boolean created = destinationDirectory.mkdirs();
                if (!created) {
                    JOptionPane.showMessageDialog(this, "Không thể tạo thư mục đích: " + destinationDirectory.getPath(), "Lỗi thư mục", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Đường dẫn file đích
            File destinationFile = new File(destinationDirectory, sourceFile.getName());
            System.out.println("Đang sao chép file từ: " + sourceFile.getPath() + " đến: " + destinationFile.getPath());

            // Sao chép file
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi sao chép file: " + e.getMessage(), "Lỗi tải file", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Thêm văn bản đi thất bại!");
    }
    }//GEN-LAST:event_btnThemVanBanDiActionPerformed

    private void btnTaiLaiVanBanDiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiLaiVanBanDiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaiLaiVanBanDiMouseClicked

    private void btnTaiLaiVanBanDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiLaiVanBanDiActionPerformed
        btnThemVanBanDi.setEnabled(true);    
            txtSoKiHieuDi.setText("");
            txtSoDi.setText("");
            txtNoiNhanDi.setText("");
            txtSoLuongBanDi.setText("");
            txtSoTrangDi.setText("");
            txtNguoiDuyetDi.setText("");
            txtNguoiKyDi.setText("");
            txtNguoiGuiDi.setText("");
            txtNoiDungDi.setText("");
            txtTrichYeuDi.setText("");
            txtDinhKemFileDi.setText("");
            cboLoaiVanBanDi.setSelectedIndex(-1);
            cboSoVanBanDi.setSelectedIndex(-1);
            Date currentDate = new Date();  // Lấy ngày hiện tại
            dateNgayBanHanhDi.setDate(currentDate); 
    }//GEN-LAST:event_btnTaiLaiVanBanDiActionPerformed

    private void btnXoaVanBanDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaVanBanDiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaVanBanDiActionPerformed

    private void btnLuuVanBanDiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuVanBanDiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuVanBanDiMouseClicked

    private void btnLuuVanBanDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuVanBanDiActionPerformed
   try {
        VanBanDiController vbDiController = new VanBanDiController();

        // Lấy văn bản đã chọn từ danh sách và id (nếu có danh sách trước đó)
        VanBanDiModel vanbandi = dsvbdi.get(rowSelectedDi); // rowSelectedDi là hàng đang chọn
        int id = vanbandi.getId(); 
        System.out.println("ID LÀ: " + id);

        // Khởi tạo đối tượng Văn Bản Đi
        VanBanDiModel vb = new VanBanDiModel();
        vb.setId(id); // Gán id văn bản đi (nếu cập nhật)

        String input = cboSoVanBanDi.getSelectedItem().toString().trim();
        String tenso = null;
        int nam = 0;

        // Kiểm tra định dạng chuỗi
        if (input == null || !input.contains("-")) {
            JOptionPane.showMessageDialog(this, "Chuỗi không hợp lệ. Vui lòng nhập theo định dạng 'Tên văn bản - Năm'.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            String[] parts = input.split("-");
            if (parts.length == 2) {
                tenso = parts[0].trim();
                try {
                    nam = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Năm không hợp lệ. Vui lòng nhập năm hợp lệ.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Chuỗi không đúng định dạng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Gán thông tin vào đối tượng Văn Bản Đi
        vb.setTenSo(tenso);
        vb.setNam(nam);

        // Kiểm tra ngày tháng
        if (dateNgayBanHanhDi.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày ban hành.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        vb.setNgayBanHanh(dateNgayBanHanhDi.getDate());
        vb.setSoKyHieu(txtSoKiHieuDi.getText().trim());
        vb.setNoiNhan(txtNoiNhanDi.getText().trim());
        vb.setLoaiVanBan(cboLoaiVanBanDi.getSelectedItem().toString().trim());

        // Kiểm tra số đi
        try {
            vb.setSoDi(Integer.parseInt(txtSoDi.getText().trim()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số đi phải là một số hợp lệ.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Kiểm tra số lượng bản và số trang
        try {
            vb.setSlBan(Integer.parseInt(txtSoLuongBanDi.getText().trim()));
            vb.setSoTrang(Integer.parseInt(txtSoTrangDi.getText().trim()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng bản và số trang phải là các số hợp lệ.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
            return;
        }

        vb.setNguoiGui(txtNguoiGuiDi.getText().trim());
        vb.setNguoiKy(txtNguoiKyDi.getText().trim());
        vb.setNguoiDuyet(txtNguoiDuyetDi.getText().trim());
        vb.setTrichYeu(txtTrichYeuDi.getText().trim());
        vb.setNoiDung(txtNoiDungDi.getText().trim());
        vb.setDuongDanFile(txtDinhKemFileDi.getText().trim());
        vb.setDaXoa(false); // Mặc định là chưa xóa

        // Gọi phương thức cập nhật văn bản đi
        int result = vbDiController.CapNhat(vb);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Cập nhật văn bản đi thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật văn bản đi thất bại!");
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi ngoại lệ", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnLuuVanBanDiActionPerformed

    private void btnDinhKemFileDi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDinhKemFileDi3ActionPerformed
     try {
         txtDinhKemFileDi.setText("");
        // Lấy thư mục làm việc hiện tại (working directory)
        String workingDirectory = System.getProperty("user.dir");

        // Đường dẫn tương đối cho thư mục nguồn và đích
        File allowedDirectory = new File(workingDirectory, "Quan ly van ban");
        File destinationDirectory = new File(workingDirectory, "Van ban di");

        if (!allowedDirectory.exists()) {
            JOptionPane.showMessageDialog(this, "Thư mục nguồn không tồn tại: " + allowedDirectory.getAbsolutePath(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Khởi tạo JFileChooser với thư mục hiện tại là allowedDirectory
        JFileChooser fileChooser = new JFileChooser(allowedDirectory);
        fileChooser.setDialogTitle("Chọn file đính kèm từ thư mục cho phép");

        // Hiển thị hộp thoại để chọn file
        int userSelection = fileChooser.showOpenDialog(this);

        // Kiểm tra nếu người dùng đã chọn file
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Kiểm tra nếu file được chọn nằm trong thư mục cho phép
            if (selectedFile.getCanonicalPath().startsWith(allowedDirectory.getCanonicalPath())) {
                txtDinhKemFileDi.setText(selectedFile.getName()); // Hiển thị tên file lên UI

                // Kiểm tra và tạo thư mục đích nếu chưa tồn tại
                if (!destinationDirectory.exists()) {
                    destinationDirectory.mkdirs();
                }

                // Tạo file đích và sao chép file vào thư mục đích
                File destinationFile = new File(destinationDirectory, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                

            } else {
                // Nếu file không nằm trong thư mục cho phép, thông báo cảnh báo
                JOptionPane.showMessageDialog(this, "Bạn chỉ được chọn file trong thư mục: " + allowedDirectory.getAbsolutePath(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
        }
    } catch (Exception ex) {
        // Bắt lỗi và hiển thị thông báo
        JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi ngoại lệ", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnDinhKemFileDi3ActionPerformed

    private void btnThemDSDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDSDenActionPerformed
         pnChuaQuanLyVanBan.setSelectedIndex(3);
    }//GEN-LAST:event_btnThemDSDenActionPerformed

    private void btnTaiLaiDSDenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiLaiDSDenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaiLaiDSDenMouseClicked

    private void btnTaiLaiDSDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiLaiDSDenActionPerformed
        
        HienThiDanhSachVanBanDen();
    }//GEN-LAST:event_btnTaiLaiDSDenActionPerformed

    private void btnXoaDSDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDSDenActionPerformed
                                   
       try {
        // Lấy dòng được chọn trong bảng tblDanhSachVanBanDen
        int selectedRow = tblDanhSachVanBanDen.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một văn bản để xóa!");
            return;
        }
        VanBanDenModel vanbanden = dsvbden.get(selectedRow); 
        int idVanBan = vanbanden.getId();
        int confirm = JOptionPane.showConfirmDialog(
            null, 
            "Bạn có chắc chắn muốn xóa văn bản này không?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            VanBanDenController controller = new VanBanDenController();

            VanBanDenModel vanBan = new VanBanDenModel();
            vanBan.setId(idVanBan);

            int result = controller.XoaVanBanDen(vanBan);

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Xóa văn bản thành công!");
                HienThiDanhSachVanBanDen(); // Tải lại danh sách văn bản sau khi xóa
            } else {
                JOptionPane.showMessageDialog(null, "Xóa văn bản thất bại!");
            }
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
    }        
    }//GEN-LAST:event_btnXoaDSDenActionPerformed

    private void btnLuuDSDenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuDSDenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuDSDenMouseClicked

    private void btnLuuDSDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuDSDenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuDSDenActionPerformed

    private void btnXuatExcelDSDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelDSDenActionPerformed
        try {
    // Tạo JFileChooser để chọn nơi lưu file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filePath.endsWith(".xlsx")) {
            filePath += ".xlsx"; // Thêm phần mở rộng nếu chưa có
        }

        // Lấy dữ liệu từ JTable
        JTable table = tblDanhSachVanBanDen; // Đảm bảo sử dụng đúng tên biến cho bảng của bạn
        int rowCount = table.getRowCount();
        int columnCount = table.getColumnCount();

        // Tạo một Workbook Excel mới
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Văn Bản Đến");

            // Thêm tiêu đề vào Excel
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < columnCount; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(table.getColumnName(col));

                // Tạo style cho tiêu đề
                CellStyle style = workbook.createCellStyle();
                org.apache.poi.ss.usermodel.Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Thêm dữ liệu bảng vào Excel
            for (int row = 0; row < rowCount; row++) {
                Row excelRow = sheet.createRow(row + 1); // Dữ liệu bắt đầu từ dòng 2
                for (int col = 0; col < columnCount; col++) {
                    Cell cell = excelRow.createCell(col);
                    Object value = table.getValueAt(row, col);
                    if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }

            // Ghi file ra đĩa
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Mở file Excel sau khi xuất
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                File excelFile = new File(filePath);
                if (excelFile.exists()) {
                    desktop.open(excelFile); // Mở file Excel
                }
            } else {
                JOptionPane.showMessageDialog(this, "Máy tính của bạn không hỗ trợ mở file tự động.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
}

    }//GEN-LAST:event_btnXuatExcelDSDenActionPerformed

    private void cboNamDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamDenActionPerformed
        HienThiDanhSachVanBanDen();
    }//GEN-LAST:event_cboNamDenActionPerformed

    private void btnThemDanhSachDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDanhSachDiActionPerformed
       pnChuaQuanLyVanBan.setSelectedIndex(4);
    }//GEN-LAST:event_btnThemDanhSachDiActionPerformed

    private void btnTaiLaiDSDiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaiLaiDSDiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaiLaiDSDiMouseClicked

    private void btnTaiLaiDSDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiLaiDSDiActionPerformed
        HienThiDanhSachVanBanDi();
    }//GEN-LAST:event_btnTaiLaiDSDiActionPerformed

    private void btnXoaDSDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDSDiActionPerformed
           try {
        int selectedRow = tblDanhSachVanBanDi.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một văn bản để xóa!");
            return;
        }
        VanBanDiModel vanbandi = dsvbdi.get(selectedRow); 
        int idVanBan = vanbandi.getId();
        int confirm = JOptionPane.showConfirmDialog(
            null, 
            "Bạn có chắc chắn muốn xóa văn bản này không?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            VanBanDiController controller = new VanBanDiController();


            int result = controller.XoaVanBanDi(idVanBan);

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Xóa văn bản thành công!");
                HienThiDanhSachVanBanDi(); // Tải lại danh sách văn bản sau khi xóa
            } else {
                JOptionPane.showMessageDialog(null, "Xóa văn bản thất bại!");
            }
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
    }        
    }//GEN-LAST:event_btnXoaDSDiActionPerformed

    private void btnLuuDSDiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuDSDiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuDSDiMouseClicked

    private void btnLuuDSDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuDSDiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuDSDiActionPerformed

    private void btnXuatExcelDSDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelDSDiActionPerformed
         try {
    // Tạo JFileChooser để chọn nơi lưu file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filePath.endsWith(".xlsx")) {
            filePath += ".xlsx"; // Thêm phần mở rộng nếu chưa có
        }

        // Lấy dữ liệu từ JTable
        JTable table = tblDanhSachVanBanDi; // Đảm bảo sử dụng đúng tên biến cho bảng của bạn
        int rowCount = table.getRowCount();
        int columnCount = table.getColumnCount();

        // Tạo một Workbook Excel mới
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Văn Bản Đi");

            // Thêm tiêu đề vào Excel
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < columnCount; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(table.getColumnName(col));

                // Tạo style cho header
                CellStyle style = workbook.createCellStyle();
                org.apache.poi.ss.usermodel.Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Thêm dữ liệu bảng vào Excel
            for (int row = 0; row < rowCount; row++) {
                Row excelRow = sheet.createRow(row + 1); // Dữ liệu bắt đầu từ dòng 2
                for (int col = 0; col < columnCount; col++) {
                    Cell cell = excelRow.createCell(col);
                    Object value = table.getValueAt(row, col);
                    if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }

            // Ghi file ra đĩa
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Mở file Excel vừa xuất
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                File excelFile = new File(filePath);
                if (excelFile.exists()) {
                    desktop.open(excelFile); // Mở file Excel
                }
            } else {
                JOptionPane.showMessageDialog(this, "Máy tính của bạn không hỗ trợ mở file tự động.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_btnXuatExcelDSDiActionPerformed

    private void cboNamDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamDiActionPerformed
        HienThiDanhSachVanBanDi();
    }//GEN-LAST:event_cboNamDiActionPerformed

    private void btnLoaiVanBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoaiVanBanActionPerformed
        pnThan.setVisible(true);
                pnNguoiDung.setVisible(false);
    pnKy.setVisible(false);
        pnChuaThan.setVisible(true);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(1); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_btnLoaiVanBanActionPerformed

    private void btnNoiBanHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoiBanHanhActionPerformed
        pnThan.setVisible(true);
        pnChuaThan.setVisible(true);
                pnNguoiDung.setVisible(false);
    pnKy.setVisible(false);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(2); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_btnNoiBanHanhActionPerformed

    private void btnVanBanDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVanBanDenActionPerformed
        pnThan.setVisible(true);
                pnNguoiDung.setVisible(false);
    pnKy.setVisible(false);
        pnChuaThan.setVisible(true);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(3); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_btnVanBanDenActionPerformed

    private void btnVanBanDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVanBanDiActionPerformed
        pnThan.setVisible(true);
                pnNguoiDung.setVisible(false);
    pnKy.setVisible(false);
        pnChuaThan.setVisible(true);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(4); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_btnVanBanDiActionPerformed

    private void btnDSVBDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDSVBDenActionPerformed
        pnThan.setVisible(true);
        pnChuaThan.setVisible(true);
                pnNguoiDung.setVisible(false);
    pnKy.setVisible(false);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(5); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_btnDSVBDenActionPerformed

    private void btnDSVBDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDSVBDiActionPerformed
        pnThan.setVisible(true);
                pnNguoiDung.setVisible(false);
    pnKy.setVisible(false);
        pnChuaThan.setVisible(true);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(6); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_btnDSVBDiActionPerformed

    private void btnTimKiemVanBanDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemVanBanDiActionPerformed
       try {
           
    String selectedItem = (String) ThoiGianTimKiemDi.getSelectedItem();
    Date startDate = null, endDate = null;

    // Kiểm tra giá trị đã chọn và thiết lập thời gian bắt đầu và kết thúc
    switch (selectedItem) {
        case "Hôm nay":
            startDate = getTodayStart();
            endDate = getTodayEnd();
            break;
        case "Tuần này":
            startDate = getWeekStart();
            endDate = getWeekEnd();
            break;
        case "Tháng này":
            startDate = getMonthStart();
            endDate = getMonthEnd();
            break;
        case "Năm nay":
            startDate = getYearStart();
            endDate = getYearEnd();
            break;
        case "Năm trước":
            startDate = getPreviousYearStart();
            endDate = getPreviousYearEnd();
            break;
        default:
            break;
    }
        String input = cboTimKiemSoVanBanDi.getSelectedItem().toString().trim();
        String[] parts = input.split("-"); // Tách chuỗi tại dấu '-'

        String soVanBan = parts.length > 0 ? parts[0].trim() : "";
        int nam=parts.length > 1 ? Integer.parseInt(parts[1].trim()) : 0; 
        String loaiVanBanDi = cboTimKiemLoaiVanBanDi.getSelectedItem().toString().trim();
        String soKyHieu = txtTimKiemSoKyHieuDi.getText();
        String noiNhan = txtNoiNhanTimKiemDi.getText();
        String noiDung = txtTimKiemDi.getText();
        if(soVanBan.equals("Tất cả")) soVanBan=null;
        if(loaiVanBanDi.equals("Tất cả")) loaiVanBanDi=null;
        System.out.println(soVanBan + " " + loaiVanBanDi + " " + soKyHieu + " " + noiNhan + " "  + " " + noiDung+" "+nam);

        
        
            VanBanDiController vbdiContrl = new VanBanDiController();
            ArrayList<VanBanDiModel> dsdi = vbdiContrl.search(soVanBan, nam, loaiVanBanDi, soKyHieu, noiNhan, noiDung,startDate,endDate);
            
            DefaultTableModel danhSachDi = (DefaultTableModel) tblTimKiemDanhSachVanBanDi.getModel();
            danhSachDi.setRowCount(0); // Xóa dữ liệu cũ trên bảng
            
            if (dsdi.isEmpty()) {
                // Nếu không tìm thấy dữ liệu
                JOptionPane.showMessageDialog(null, "Không tìm thấy văn bản nào thỏa mãn.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Hiển thị kết quả tìm kiếm
                for (int i = 0; i < dsdi.size(); i++) {
                    VanBanDiModel vbDi = dsdi.get(i);
                    danhSachDi.addRow(new Object[] {
                        i + 1, // STT
                        vbDi.getTenSo(),
                        vbDi.getLoaiVanBan(),
                        vbDi.getSoKyHieu(),
                        vbDi.getSoDi(),
                        vbDi.getNgayBanHanh(),
                        vbDi.getNoiNhan(),
                        vbDi.getTrichYeu()
                    });
                }
            }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnTimKiemVanBanDiActionPerformed

    private void btnXemChiTiet3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTiet3ActionPerformed
    VanBanDenController vbdenContrl=new VanBanDenController();
        dstimkiemden = vbdenContrl.HienThiTimKiemVanBanDen();
    int row = tblTimKiemDanhSachVanBanDi.getSelectedRow();
    
    // Kiểm tra xem có dòng nào được chọn không
    if (row >= 0) {
        vanBanDiChiTiet=dstimkiemdi.get(row);
        System.out.println(vanBanDiChiTiet.toString());
         FormChiTietVanBanDi form = new FormChiTietVanBanDi();
    form.show();
    } else {
        System.out.println("Chưa chọn dòng nào.");
    }
    }//GEN-LAST:event_btnXemChiTiet3ActionPerformed

    private void btnXuatFileExcel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcel3ActionPerformed
             try {
    // Tạo JFileChooser để chọn nơi lưu file
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filePath.endsWith(".xlsx")) {
            filePath += ".xlsx"; // Thêm phần mở rộng nếu chưa có
        }

        // Lấy dữ liệu từ JTable
        JTable table = tblDanhSachVanBanDi; // Đảm bảo sử dụng đúng tên biến cho bảng của bạn
        int rowCount = table.getRowCount();
        int columnCount = table.getColumnCount();

        // Tạo một Workbook Excel mới
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Văn Bản Đi");

            // Thêm tiêu đề vào Excel
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < columnCount; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(table.getColumnName(col));

                // Tạo style cho header
                CellStyle style = workbook.createCellStyle();
                org.apache.poi.ss.usermodel.Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Thêm dữ liệu bảng vào Excel
            for (int row = 0; row < rowCount; row++) {
                Row excelRow = sheet.createRow(row + 1); // Dữ liệu bắt đầu từ dòng 2
                for (int col = 0; col < columnCount; col++) {
                    Cell cell = excelRow.createCell(col);
                    Object value = table.getValueAt(row, col);
                    if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }

            // Ghi file ra đĩa
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Mở file Excel vừa xuất
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                File excelFile = new File(filePath);
                if (excelFile.exists()) {
                    desktop.open(excelFile); // Mở file Excel
                }
            } else {
                JOptionPane.showMessageDialog(this, "Máy tính của bạn không hỗ trợ mở file tự động.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_btnXuatFileExcel3ActionPerformed

    private void btnTaiLaiSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiLaiSoActionPerformed
        HienThiTable();
    }//GEN-LAST:event_btnTaiLaiSoActionPerformed
    int rowSelectedDi;
    private void tblDanhSachVanBanDiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachVanBanDiMouseClicked
        btnLuuVanBanDi.setEnabled(true);
        btnThemVanBanDi.setEnabled(false);

    if (evt.getClickCount() == 2 && !evt.isConsumed()) {
    evt.consume();
    rowSelectedDi = tblDanhSachVanBanDi.getSelectedRow();  
    pnChuaQuanLyVanBan.setSelectedIndex(4); 

    if (rowSelectedDi >= 0) {  // Kiểm tra xem có dòng nào được chọn không
        VanBanDiModel vanbandi = dsvbdi.get(rowSelectedDi);  
        // Cập nhật các trường trong giao diện với thông tin từ đối tượng vanbandi
        cboSoVanBanDi.setSelectedItem(vanbandi.getTenSo()+"-"+vanbandi.getNam());  // Cập nhật tên sổ
        txtSoKiHieuDi.setText(vanbandi.getSoKyHieu());  // Cập nhật số ký hiệu

        // Cập nhật ngày ban hành
        dateNgayBanHanhDi.setDate(vanbandi.getNgayBanHanh());

        // Cập nhật nơi nhận
        cboLoaiVanBanDi.setSelectedItem(vanbandi.getNoiNhan());

        // Cập nhật loại văn bản
        cboLoaiVanBanDi.setSelectedItem(vanbandi.getLoaiVanBan());
        txtNoiNhanDi.setText(vanbandi.getNoiNhan());
        // Cập nhật số đi
        txtSoDi.setText(String.valueOf(vanbandi.getSoDi()));
        txtSoLuongBanDi.setText(String.valueOf(vanbandi.getSlBan()));
        txtSoTrangDi.setText(String.valueOf(vanbandi.getSoTrang()));

        // Cập nhật người nhận
        txtNguoiGuiDi.setText(vanbandi.getNguoiGui());

        // Cập nhật người ký
        txtNguoiKyDi.setText(vanbandi.getNguoiKy());

        // Cập nhật người duyệt
        txtNguoiDuyetDi.setText(vanbandi.getNguoiDuyet());
        txtTrichYeuDi.setText(vanbandi.getTrichYeu());
        txtNoiDungDi.setText(vanbandi.getNoiDung());
        txtDinhKemFileDi.setText(vanbandi.getDuongDanFile());
    }
}

    }//GEN-LAST:event_tblDanhSachVanBanDiMouseClicked

    private void ThoiGianTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThoiGianTimKiemActionPerformed

       // Lấy giá trị đã chọn từ JComboBox
    String selectedItem = (String) ThoiGianTimKiem.getSelectedItem();
    Date startDate = null, endDate = null;

    // Kiểm tra giá trị đã chọn và thiết lập thời gian bắt đầu và kết thúc
    switch (selectedItem) {
        case "Hôm nay":
            startDate = getTodayStart();
            endDate = getTodayEnd();
            break;
        case "Tuần này":
            startDate = getWeekStart();
            endDate = getWeekEnd();
            break;
        case "Tháng này":
            startDate = getMonthStart();
            endDate = getMonthEnd();
            break;
        case "Năm nay":
            startDate = getYearStart();
            endDate = getYearEnd();
            break;
        case "Tháng 1":
            startDate = getMonthStart(1);
            endDate = getMonthEnd(1);
            break;
        case "Tháng 2":
            startDate = getMonthStart(2);
            endDate = getMonthEnd(2);
            break;
             case "Tháng 3":
            startDate = getMonthStart(3);
            endDate = getMonthEnd(3);
            break;
             case "Tháng 4":
            startDate = getMonthStart(4);
            endDate = getMonthEnd(4);
            break;
             case "Tháng 5":
            startDate = getMonthStart(5);
            endDate = getMonthEnd(5);
            break;
             case "Tháng 6":
            startDate = getMonthStart(6);
            endDate = getMonthEnd(6);
            break;
             case "Tháng 7":
            startDate = getMonthStart(7);
            endDate = getMonthEnd(7);
            break;
             case "Tháng 8":
            startDate = getMonthStart(8);
            endDate = getMonthEnd(8);
            break;
             case "Tháng 9":
            startDate = getMonthStart(9);
            endDate = getMonthEnd(9);
            break;
             case "Tháng 10":
            startDate = getMonthStart(10);
            endDate = getMonthEnd(10);
            break;
             case "Tháng 11":
            startDate = getMonthStart(11);
            endDate = getMonthEnd(11);
            break;
             case "Tháng 12":
            startDate = getMonthStart(12);
            endDate = getMonthEnd(12);
            break;
        case "Năm trước":
            startDate = getPreviousYearStart();
            endDate = getPreviousYearEnd();
            break;
        default:
            break;
    }

    // Kiểm tra và thiết lập giá trị cho jDateChooser2 và jDateChooser3
    if (startDate != null && endDate != null) {
        dateTimKiemNoiBoMin.setDate(startDate); // Đặt giá trị "từ ngày"
        dateTimKiemNoiBoMax.setDate(endDate);   // Đặt giá trị "đến ngày"

        // Gọi phương thức để hiển thị danh sách văn bản trong khoảng thời gian
        filterAndDisplayVanBan(startDate, endDate);
    }
    }//GEN-LAST:event_ThoiGianTimKiemActionPerformed

    private void btnLuu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuu1MouseEntered
        // TODO add your handling code here:
        jPopupMenu8.show(btnLuu1, 0, btnLuu1.getHeight());
    }//GEN-LAST:event_btnLuu1MouseEntered

    private void btnLuu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuu1MouseExited
        // TODO add your handling code here:
        jPopupMenu8.setVisible(false);
    }//GEN-LAST:event_btnLuu1MouseExited

    private void btnLuu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuu1ActionPerformed
        // TODO add your handling code here:
          JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

int userSelection = fileChooser.showSaveDialog(this);
if (userSelection == JFileChooser.APPROVE_OPTION) {
    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
    if (!filePath.endsWith(".xls")) {
        filePath += ".xls"; // Thêm phần mở rộng nếu chưa có
    }

    try (Workbook workbook = new XSSFWorkbook()) {
        Sheet sheet = workbook.createSheet("VanBanNoiBo");
        TableModel tableModel = tbPhongBan.getModel();

        // Tạo header (dòng tiêu đề), bỏ qua cột "Mã Phòng Ban" (index 2)
        Row headerRow = sheet.createRow(0);
        int colIndex = 0;
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            if (i == 2) continue; // Bỏ qua cột "Mã Phòng Ban"
            Cell cell = headerRow.createCell(colIndex++);
            cell.setCellValue(tableModel.getColumnName(i));

            // Tạo style và định dạng font
            CellStyle style = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }

        // Ghi dữ liệu từ bảng vào file Excel, bỏ qua cột "Mã Phòng Ban" (index 2)
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            colIndex = 0;
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                if (j == 2) continue; // Bỏ qua cột "Mã Phòng Ban"
                Cell cell = dataRow.createCell(colIndex++);
                Object value = tableModel.getValueAt(i, j);
                if (value instanceof Number) {
                    cell.setCellValue(((Number) value).doubleValue());
                } else {
                    cell.setCellValue(value != null ? value.toString() : "");
                }
            }
        }

        // Ghi file ra đĩa
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        // Mở file Excel vừa xuất
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            File excelFile = new File(filePath);
            if (excelFile.exists()) {
                desktop.open(excelFile); // Mở file Excel
            }
        } else {
            JOptionPane.showMessageDialog(this, "Máy tính của bạn không hỗ trợ mở file tự động.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    }//GEN-LAST:event_btnLuu1ActionPerformed

    private void btnXoa10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoa10MouseEntered
        // TODO add your handling code here:
        jPopupMenu8.show(btnXoa10, 0, btnXoa10.getHeight());
    }//GEN-LAST:event_btnXoa10MouseEntered

    private void btnXoa10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoa10MouseExited
        // TODO add your handling code here:
        jPopupMenu8.setVisible(false);
    }//GEN-LAST:event_btnXoa10MouseExited

    private void btnXoa10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa10ActionPerformed
       // TODO add your handling code here:
            JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    
    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filePath.endsWith(".xlsx")) {
            filePath += ".xlsx"; // Thêm phần mở rộng nếu chưa có
        }
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("VanBanNoiBo");
            TableModel tableModel = tbVanBanNoiBo.getModel();
            
            // Tạo header (dòng tiêu đề)
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(tableModel.getColumnName(i));

                // Tạo style và định dạng font
                CellStyle style = workbook.createCellStyle();
                org.apache.poi.ss.usermodel.Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }
            
            // Ghi dữ liệu từ bảng vào file Excel
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    Cell cell = dataRow.createCell(j);
                    Object value = tableModel.getValueAt(i, j);
                    if (value instanceof Number number) {
                        cell.setCellValue(number.doubleValue());
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }
            
            // Ghi file ra đĩa
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            
            JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            
            // Mở file Excel vừa xuất
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                File excelFile = new File(filePath);
                if (excelFile.exists()) {
                    desktop.open(excelFile); // Mở file Excel
                }
            } else {
                JOptionPane.showMessageDialog(this, "Máy tính của bạn không hỗ trợ mở file tự động.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    }//GEN-LAST:event_btnXoa10ActionPerformed

    private void btnInBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInBaoCaoActionPerformed
        // Kiểm tra nếu không có dữ liệu trong bảng
        if (tbVanBanNoiBo1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu để in!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return; // Dừng thực hiện nếu không có dữ liệu
        }

        // Tăng kích cỡ chữ trong JTable
        tbVanBanNoiBo1.setFont(new Font("Arial", Font.PLAIN, 12)); // Thay đổi kích cỡ chữ của nội dung bảng
        tbVanBanNoiBo1.setRowHeight(22); // Tăng chiều cao dòng

        // Tăng kích cỡ và làm đậm tiêu đề cột
        JTableHeader tableHeader = tbVanBanNoiBo1.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 12)); // Font in đậm và to hơn
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 22)); // Tăng chiều cao tiêu đề cột

        // Tiêu đề và chân trang
        MessageFormat header = new MessageFormat("Danh sách văn bản nội bộ");
        MessageFormat footer = new MessageFormat("Quản lý văn bản");

        try {
            // In bảng
            tbVanBanNoiBo1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Không thể in! " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnInBaoCaoActionPerformed
private void filterAndDisplayVanBanDen(Date startDate, Date endDate) {
    VanBanDenController controller = new VanBanDenController(); // Controller xử lý văn bản đến

    try {
        // Chuyển đổi java.util.Date thành java.sql.Date
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        // Lấy danh sách văn bản đến theo khoảng thời gian
        ArrayList<VanBanDenModel> vanBanDenList = controller.filterVanBanDenByDateRange(sqlStartDate, sqlEndDate);

        // Lấy DefaultTableModel từ bảng tblDanhSachVanBanDen
        DefaultTableModel danhSachDenModel = (DefaultTableModel) tblDanhSachVanBanDen.getModel();

        // Xóa toàn bộ dữ liệu cũ trong bảng
        danhSachDenModel.setRowCount(0);

        // Thêm dữ liệu mới vào bảng
        for (VanBanDenModel vanBanDen : vanBanDenList) {
            danhSachDenModel.addRow(new Object[]{
                vanBanDen.getId(),
                vanBanDen.getNam(),
                vanBanDen.getTenSo(),
                vanBanDen.getSoKyHieu(),
                vanBanDen.getNgayBanHanh(),
                vanBanDen.getNoiBanHanh(),
                vanBanDen.getLoaiVanBan(),
                vanBanDen.getSoDen(),
                vanBanDen.getNgayDen(),
                vanBanDen.getSoTrang(),
                vanBanDen.getNguoiNhan(),
                vanBanDen.getNguoiKy(),
                vanBanDen.getNguoiDuyet(),
                vanBanDen.getTrichYeu(),
                vanBanDen.getNoiDung(),
                vanBanDen.getDuongDanFile()
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu văn bản đến: " + e.getMessage());
    }
}

private void filterAndDisplayVanBanDi(Date startDate, Date endDate) {
    try {
        VanBanDiController controller = new VanBanDiController();
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        ArrayList<VanBanDiModel> vanBanDiList = controller.filterVanBanDiByDateRange(sqlStartDate, sqlEndDate);

        // Lấy model từ bảng
        DefaultTableModel danhSachDi = (DefaultTableModel) tblDanhSachVanBanDi.getModel();
        danhSachDi.setRowCount(0); // Xóa dữ liệu cũ

        for (VanBanDiModel vanBanDi : vanBanDiList) {
            danhSachDi.addRow(new Object[]{
                vanBanDi.getId(),
                vanBanDi.getNam(),
                vanBanDi.getTenSo(),
                vanBanDi.getSoKyHieu(),
                vanBanDi.getNgayBanHanh(),
                vanBanDi.getNoiNhan(),
                vanBanDi.getLoaiVanBan(),
                vanBanDi.getSoDi(),
                vanBanDi.getSoTrang(),
                vanBanDi.getNguoiGui(),
                vanBanDi.getNguoiKy(),
                vanBanDi.getNguoiDuyet(),
                vanBanDi.getTrichYeu(),
                vanBanDi.getNoiDung(),
                vanBanDi.getDuongDanFile()
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu văn bản đi: " + e.getMessage());
    }
}

    private void ThoiGianTimKiemDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThoiGianTimKiemDiActionPerformed
        // TODO add your handling code here:
        
       // Lấy giá trị đã chọn từ JComboBox
    String selectedItem = (String) ThoiGianTimKiemDi.getSelectedItem();
    Date startDate = null, endDate = null;

    // Kiểm tra giá trị đã chọn và thiết lập thời gian bắt đầu và kết thúc
    switch (selectedItem) {
        case "Hôm nay":
            startDate = getTodayStart();
            endDate = getTodayEnd();
            break;
        case "Tuần này":
            startDate = getWeekStart();
            endDate = getWeekEnd();
            break;
        case "Tháng này":
            startDate = getMonthStart();
            endDate = getMonthEnd();
            break;
        case "Năm nay":
            startDate = getYearStart();
            endDate = getYearEnd();
            break;
        case "Tháng 1":
            startDate = getMonthStart(1);
            endDate = getMonthEnd(1);
            break;
        case "Tháng 2":
            startDate = getMonthStart(2);
            endDate = getMonthEnd(2);
            break;
             case "Tháng 3":
            startDate = getMonthStart(3);
            endDate = getMonthEnd(3);
            break;
             case "Tháng 4":
            startDate = getMonthStart(4);
            endDate = getMonthEnd(4);
            break;
             case "Tháng 5":
            startDate = getMonthStart(5);
            endDate = getMonthEnd(5);
            break;
             case "Tháng 6":
            startDate = getMonthStart(6);
            endDate = getMonthEnd(6);
            break;
             case "Tháng 7":
            startDate = getMonthStart(7);
            endDate = getMonthEnd(7);
            break;
             case "Tháng 8":
            startDate = getMonthStart(8);
            endDate = getMonthEnd(8);
            break;
             case "Tháng 9":
            startDate = getMonthStart(9);
            endDate = getMonthEnd(9);
            break;
             case "Tháng 10":
            startDate = getMonthStart(10);
            endDate = getMonthEnd(10);
            break;
             case "Tháng 11":
            startDate = getMonthStart(11);
            endDate = getMonthEnd(11);
            break;
             case "Tháng 12":
            startDate = getMonthStart(12);
            endDate = getMonthEnd(12);
            break;
        case "Năm trước":
            startDate = getPreviousYearStart();
            endDate = getPreviousYearEnd();
            break;
        default:
            break;
    }

    // Kiểm tra và thiết lập giá trị cho jDateChooser2 và jDateChooser3
    if (startDate != null && endDate != null) {
       dateTimKiemMinDi.setDate(startDate); // Đặt giá trị "từ ngày"
       dateTimKiemMaxDi.setDate(endDate);   // Đặt giá trị "đến ngày
        HienThiTimKiemTheoNgayDi(startDate, endDate);
    }
    }//GEN-LAST:event_ThoiGianTimKiemDiActionPerformed

    private void ThoiGianTimKiemDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThoiGianTimKiemDenActionPerformed
            // TODO add your handling code here:
        
       // Lấy giá trị đã chọn từ JComboBox
    String selectedItem = (String) ThoiGianTimKiemDen.getSelectedItem();
    Date startDate = null, endDate = null;

    // Kiểm tra giá trị đã chọn và thiết lập thời gian bắt đầu và kết thúc
    switch (selectedItem) {
        case "Hôm nay":
            startDate = getTodayStart();
            endDate = getTodayEnd();
            break;
        case "Tuần này":
            startDate = getWeekStart();
            endDate = getWeekEnd();
            break;
        case "Tháng này":
            startDate = getMonthStart();
            endDate = getMonthEnd();
            break;
        case "Năm nay":
            startDate = getYearStart();
            endDate = getYearEnd();
            break;
        case "Tháng 1":
            startDate = getMonthStart(1);
            endDate = getMonthEnd(1);
            break;
        case "Tháng 2":
            startDate = getMonthStart(2);
            endDate = getMonthEnd(2);
            break;
             case "Tháng 3":
            startDate = getMonthStart(3);
            endDate = getMonthEnd(3);
            break;
             case "Tháng 4":
            startDate = getMonthStart(4);
            endDate = getMonthEnd(4);
            break;
             case "Tháng 5":
            startDate = getMonthStart(5);
            endDate = getMonthEnd(5);
            break;
             case "Tháng 6":
            startDate = getMonthStart(6);
            endDate = getMonthEnd(6);
            break;
             case "Tháng 7":
            startDate = getMonthStart(7);
            endDate = getMonthEnd(7);
            break;
             case "Tháng 8":
            startDate = getMonthStart(8);
            endDate = getMonthEnd(8);
            break;
             case "Tháng 9":
            startDate = getMonthStart(9);
            endDate = getMonthEnd(9);
            break;
             case "Tháng 10":
            startDate = getMonthStart(10);
            endDate = getMonthEnd(10);
            break;
             case "Tháng 11":
            startDate = getMonthStart(11);
            endDate = getMonthEnd(11);
            break;
             case "Tháng 12":
            startDate = getMonthStart(12);
            endDate = getMonthEnd(12);
            break;
        case "Năm trước":
            startDate = getPreviousYearStart();
            endDate = getPreviousYearEnd();
            break;
        default:
            break;
    }

    // Kiểm tra và thiết lập giá trị cho jDateChooser2 và jDateChooser3
    if (startDate != null && endDate != null) {
       dateTimKiemMinDen.setDate(startDate); // Đặt giá trị "từ ngày"
       dateTimKiemMaxDen.setDate(endDate);   // Đặt giá trị "đến ngày"

        HienThiTimKiemTheoNgay(startDate,endDate);
    }
    }//GEN-LAST:event_ThoiGianTimKiemDenActionPerformed

    private void cboTimKiemSoVanBanDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimKiemSoVanBanDenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTimKiemSoVanBanDenActionPerformed

    private void tblSoVanBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSoVanBanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSoVanBanMouseClicked
    int rowSelectedDen;
    private void tblDanhSachVanBanDenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachVanBanDenMouseClicked
        btnLuuVanBanDen.setEnabled(true);
        btnThemVanBanDen.setEnabled(false);
    // Kiểm tra sự kiện double click
    if (evt.getClickCount() == 2 && !evt.isConsumed()) {
        evt.consume(); // Ngăn chặn các sự kiện khác xử lý đồng thời
        rowSelectedDen = tblDanhSachVanBanDen.getSelectedRow();  // Lấy chỉ mục dòng đã chọn
        pnChuaQuanLyVanBan.setSelectedIndex(3);  // Chuyển sang tab khác (tab 3)

        if (rowSelectedDen >= 0) {  // Kiểm tra xem có dòng nào được chọn không
            VanBanDenModel vanbanden = dsvbden.get(rowSelectedDen);  // Lấy đối tượng từ danh sách dsvbden

            // Cập nhật các trường trong giao diện với thông tin từ đối tượng vanbanden
            cboSoVanBanDen.setSelectedItem(vanbanden.getTenSo()+"-"+vanbanden.getNam());  // Cập nhật tên sổ
            txtSoKyHieuDen.setText(vanbanden.getSoKyHieu());  // Cập nhật số ký hiệu

            // Cập nhật ngày ban hành
            dateNgayBanHanhDen.setDate(vanbanden.getNgayBanHanh());

            // Cập nhật nơi ban hành
            cboNoiBanHanhDen.setSelectedItem(vanbanden.getNoiBanHanh());

            // Cập nhật loại văn bản
            cboLoaiVanBanDen.setSelectedItem(vanbanden.getLoaiVanBan());

            // Cập nhật số đến
            txtSoDen.setText(String.valueOf(vanbanden.getSoDen()));
            dateNgayDen.setDate(vanbanden.getNgayDen());
            txtSoTrangDen.setText(String.valueOf(vanbanden.getSoTrang()));

            // Cập nhật người nhận
            txtNguoiNhanDen.setText(vanbanden.getNguoiNhan());

            // Cập nhật người ký
            txtNguoiKyDen.setText(vanbanden.getNguoiKy());

            // Cập nhật người duyệt
            txtNguoiDuyetDen.setText(vanbanden.getNguoiDuyet());
            txtTrichYeuDen.setText(vanbanden.getTrichYeu());
            txtNoiDungDen.setText(vanbanden.getNoiDung());
            txtDinhKemFileDen.setText(vanbanden.getDuongDanFile());
        }
    }
    }//GEN-LAST:event_tblDanhSachVanBanDenMouseClicked

    private void btnXoaVanBanDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaVanBanDenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaVanBanDenActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         pnThan.setVisible(true);
        pnChuaThan.setVisible(true);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(0); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
         pnThan.setVisible(true);
        pnChuaThan.setVisible(true);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(2); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
          pnThan.setVisible(true);
        pnChuaThan.setVisible(true);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(3); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
          pnThan.setVisible(true);
        pnChuaThan.setVisible(true);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(1); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
         pnThan.setVisible(true);
        pnChuaThan.setVisible(true);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(6); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
          pnThan.setVisible(true);
        pnChuaThan.setVisible(true);
        pnTrangCHU.setVisible(false);
        pnChinh.setVisible(true);
        pnChuaNoiDung.setVisible(false);  // Hiển thị JTabbedPane pnChuaNoiDung
        pnVanBanNoiBo.setVisible(false);   
        pnPhongBan.setVisible(false);
        pnDSNoibo.setVisible(false);     
        pnTimKiemThongKe.setVisible(false);
        pnQuanLyVanBan.setVisible(true);
        // Đặt tab tương ứng trong JTabbedPane (ví dụ: tab đầu tiên)
        pnChuaQuanLyVanBan.setSelectedIndex(4); // Chuyển đến tab đầu tiên (VanBanNoiBo)
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
         pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
    pnChuaNoiDung.setVisible(true);  // Hiển thị JTabbedPane pnChuaNoiDung
    pnDSNoibo.setVisible(true);     
    pnPhongBan.setVisible(false);    
    pnVanBanNoiBo.setVisible(false); 
    pnTimKiemThongKe.setVisible(false);

    pnChuaNoiDung.setSelectedIndex(2);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
          pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
    pnChuaNoiDung.setVisible(true); 
    pnPhongBan.setVisible(true);      
    pnDSNoibo.setVisible(false);     
    pnVanBanNoiBo.setVisible(false);  
  pnTimKiemThongKe.setVisible(false);
    pnChuaNoiDung.setSelectedIndex(0);
    
    pnQuanLyVanBan.setVisible(false);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            // TODO add your handling code here:
                 pnQuanLyVanBan.setVisible(false);
    pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
    pnChuaNoiDung.setVisible(false);
    pnTimKiemThongKe.setVisible(true);
    
    pnChuaTimKiemThongKe.setVisible(true); 
    ThongKeVanBanDi.setVisible(false);      
    ThongKeVanBanDen.setVisible(true);     
    ThongKeVanBanNoiBo.setVisible(false);  
  
    pnChuaTimKiemThongKe.setSelectedIndex(1);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
             // TODO add your handling code here:
                 pnQuanLyVanBan.setVisible(false);
    pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
     pnChuaNoiDung.setVisible(false);
      pnTimKiemThongKe.setVisible(true);
    pnChuaTimKiemThongKe.setVisible(true); 
    ThongKeVanBanDi.setVisible(false);      
    ThongKeVanBanDen.setVisible(false);     
    ThongKeVanBanNoiBo.setVisible(true);  
  
    pnChuaTimKiemThongKe.setSelectedIndex(2);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        
    pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
    pnChuaNoiDung.setVisible(true); 
    pnPhongBan.setVisible(true);      
    pnDSNoibo.setVisible(false);     
    pnVanBanNoiBo.setVisible(false);  
  pnTimKiemThongKe.setVisible(false);
    pnChuaNoiDung.setSelectedIndex(0);
    
    pnQuanLyVanBan.setVisible(false);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        FormDangNhap dn = new FormDangNhap();
        dn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed

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
    }//GEN-LAST:event_btnThem2ActionPerformed

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

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
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
    }//GEN-LAST:event_btnXoa2ActionPerformed

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

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
    pnThan.setVisible(true);
    pnChuaThan.setVisible(true);
    pnTrangCHU.setVisible(false);
    pnChinh.setVisible(true);
    pnChuaNoiDung.setVisible(false); 
   // pnPhongBan.setVisible(true);      
    //pnDSNoibo.setVisible(false);     
    //pnVanBanNoiBo.setVisible(false);  
    pnTimKiemThongKe.setVisible(false);
  //  pnChuaNoiDung.setSelectedIndex(0);
    
    pnQuanLyVanBan.setVisible(false);
    pnNguoiDung.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnLuu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuu2ActionPerformed
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
    }//GEN-LAST:event_btnLuu2ActionPerformed

    
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormTrangChu().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FormTrangChu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ChonCmbPhongBanNhan;
    private javax.swing.JPanel ThanhMenu;
    private javax.swing.JPanel ThanhMenu1;
    private javax.swing.JComboBox<String> ThoiGianTimKiem;
    private javax.swing.JComboBox<String> ThoiGianTimKiemDen;
    private javax.swing.JComboBox<String> ThoiGianTimKiemDi;
    private javax.swing.JPanel ThongKeVanBanDen;
    private javax.swing.JPanel ThongKeVanBanDi;
    private javax.swing.JPanel ThongKeVanBanNoiBo;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnDSVBDen;
    private javax.swing.JButton btnDSVBDi;
    private javax.swing.JButton btnDinhKemFile;
    private javax.swing.JButton btnDinhKemFile4;
    private javax.swing.JButton btnDinhKemFileDi3;
    private javax.swing.JButton btnDocFile;
    private javax.swing.JButton btnDsNoiBo;
    private javax.swing.JButton btnHeThong;
    private javax.swing.JButton btnHeThong1;
    private javax.swing.JButton btnInBaoCao;
    private javax.swing.JButton btnLoaiVanBan;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnLuu1;
    private javax.swing.JButton btnLuu2;
    private javax.swing.JButton btnLuu7;
    private javax.swing.JButton btnLuuDSDen;
    private javax.swing.JButton btnLuuDSDi;
    private javax.swing.JButton btnLuuLoai;
    private javax.swing.JButton btnLuuNoi;
    private javax.swing.JButton btnLuuSo;
    private javax.swing.JButton btnLuuVanBanDen;
    private javax.swing.JButton btnLuuVanBanDi;
    private javax.swing.JButton btnNoiBanHanh;
    private javax.swing.JButton btnPhongBan;
    private javax.swing.JButton btnQLVBNoiBo;
    private javax.swing.JButton btnQLVBNoiBo1;
    private javax.swing.JButton btnQLVanBan;
    private javax.swing.JButton btnQLVanBan1;
    private javax.swing.JButton btnSoVanBan;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnSua9;
    private javax.swing.JButton btnTKVanBandi;
    private javax.swing.JButton btnTaiLai;
    private javax.swing.JButton btnTaiLai1;
    private javax.swing.JButton btnTaiLai8;
    private javax.swing.JButton btnTaiLaiDSDen;
    private javax.swing.JButton btnTaiLaiDSDi;
    private javax.swing.JButton btnTaiLaiLoai;
    private javax.swing.JButton btnTaiLaiNoi;
    private javax.swing.JButton btnTaiLaiSo;
    private javax.swing.JButton btnTaiLaiVanBanDen;
    private javax.swing.JButton btnTaiLaiVanBanDi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnThem8;
    private javax.swing.JButton btnThemDSDen;
    private javax.swing.JButton btnThemDanhSachDi;
    private javax.swing.JButton btnThemLoai;
    private javax.swing.JButton btnThemNoi;
    private javax.swing.JButton btnThemQuayLaiDeThem;
    private javax.swing.JButton btnThemSo;
    private javax.swing.JButton btnThemVanBanDen;
    private javax.swing.JButton btnThemVanBanDi;
    private javax.swing.JButton btnThongKeVBNB;
    private javax.swing.JButton btnThongKeVanBanDen;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemVanBanDen;
    private javax.swing.JButton btnTimKiemVanBanDi;
    private javax.swing.JButton btnTim_Thong;
    private javax.swing.JButton btnTim_Thong1;
    private javax.swing.JButton btnVanBanDen;
    private javax.swing.JButton btnVanBanDi;
    private javax.swing.JButton btnVanBanNoiBo;
    private javax.swing.JButton btnXemChiTiet;
    private javax.swing.JButton btnXemChiTiet1;
    private javax.swing.JButton btnXemChiTiet3;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JButton btnXoa10;
    private javax.swing.JButton btnXoa2;
    private javax.swing.JButton btnXoa9;
    private javax.swing.JButton btnXoaDSDen;
    private javax.swing.JButton btnXoaDSDi;
    private javax.swing.JButton btnXoaFile;
    private javax.swing.JButton btnXoaLoai;
    private javax.swing.JButton btnXoaNoi;
    private javax.swing.JButton btnXoaSo;
    private javax.swing.JButton btnXoaVanBanDen;
    private javax.swing.JButton btnXoaVanBanDi;
    private javax.swing.JButton btnXuatExcelDSDen;
    private javax.swing.JButton btnXuatExcelDSDi;
    private javax.swing.JButton btnXuatExcelLoaiVanBan;
    private javax.swing.JButton btnXuatExcelNoiBanHanh;
    private javax.swing.JButton btnXuatExcelSoVanBan;
    private javax.swing.JButton btnXuatExcelVanBanDen;
    private javax.swing.JButton btnXuatExcelVanBanDi;
    private javax.swing.JButton btnXuatFileExcel;
    private javax.swing.JButton btnXuatFileExcel1;
    private javax.swing.JButton btnXuatFileExcel3;
    private javax.swing.JComboBox<String> cboLoaiVanBanDen;
    private javax.swing.JComboBox<String> cboLoaiVanBanDi;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboNamDen;
    private javax.swing.JComboBox<String> cboNamDi;
    private javax.swing.JComboBox<String> cboNoiBanHanhDen;
    private javax.swing.JComboBox<String> cboSoVanBanDen;
    private javax.swing.JComboBox<String> cboSoVanBanDi;
    private javax.swing.JComboBox<String> cboTimKiemLoaiVanBanDen;
    private javax.swing.JComboBox<String> cboTimKiemLoaiVanBanDi;
    private javax.swing.JComboBox<String> cboTimKiemSoVanBanDen;
    private javax.swing.JComboBox<String> cboTimKiemSoVanBanDi;
    private javax.swing.JComboBox<String> chonCmbLoaiBanHanh;
    private javax.swing.JComboBox<String> chonCmbPhongBanHanh;
    private javax.swing.JComboBox<String> cmbLoaiBanHanh;
    private javax.swing.JComboBox<String> cmbPhongBanHanh;
    private javax.swing.JComboBox<String> cmbPhongBanNhan;
    private com.toedter.calendar.JDateChooser dateNgayBanHanh;
    private com.toedter.calendar.JDateChooser dateNgayBanHanhDen;
    private com.toedter.calendar.JDateChooser dateNgayBanHanhDi;
    private com.toedter.calendar.JDateChooser dateNgayDen;
    private com.toedter.calendar.JDateChooser dateTimKiemMaxDen;
    private com.toedter.calendar.JDateChooser dateTimKiemMaxDi;
    private com.toedter.calendar.JDateChooser dateTimKiemMinDen;
    private com.toedter.calendar.JDateChooser dateTimKiemMinDi;
    private com.toedter.calendar.JDateChooser dateTimKiemNoiBoMax;
    private com.toedter.calendar.JDateChooser dateTimKiemNoiBoMin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel109;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel110;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel112;
    private javax.swing.JPanel jPanel113;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel116;
    private javax.swing.JPanel jPanel117;
    private javax.swing.JPanel jPanel119;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel120;
    private javax.swing.JPanel jPanel121;
    private javax.swing.JPanel jPanel123;
    private javax.swing.JPanel jPanel124;
    private javax.swing.JPanel jPanel125;
    private javax.swing.JPanel jPanel126;
    private javax.swing.JPanel jPanel127;
    private javax.swing.JPanel jPanel128;
    private javax.swing.JPanel jPanel129;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel130;
    private javax.swing.JPanel jPanel131;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JPopupMenu jPopupMenu5;
    private javax.swing.JPopupMenu jPopupMenu6;
    private javax.swing.JPopupMenu jPopupMenu7;
    private javax.swing.JPopupMenu jPopupMenu8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private com.toedter.components.JSpinField jSpinField1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTextField nhapSoKyHieu;
    private javax.swing.JTextField nhapTenVanBan;
    private javax.swing.JTextArea nhapTrichYeu;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelButton1;
    private javax.swing.JPanel panelDanhSachDen3;
    private javax.swing.JPanel panelDanhSachDi3;
    private javax.swing.JPanel panelHeThong;
    private javax.swing.JPanel panelHeThong1;
    private javax.swing.JPanel panelLoaiVanBan3;
    private javax.swing.JPanel panelNoiBanHanh3;
    private javax.swing.JPanel panelQLVBNoiBo;
    private javax.swing.JPanel panelQLVBNoiBo1;
    private javax.swing.JPanel panelQLVanBan;
    private javax.swing.JPanel panelQLVanBan1;
    private javax.swing.JPanel panelSoVanBan3;
    private javax.swing.JPanel panelTimThong;
    private javax.swing.JPanel panelTimThong1;
    private javax.swing.JPanel panelVanBanDen3;
    private javax.swing.JPanel panelVanBanDi3;
    private javax.swing.JPanel pnChinh;
    private javax.swing.JPanel pnChuaCacCard;
    private javax.swing.JPanel pnChuaCacCard1;
    private javax.swing.JTabbedPane pnChuaNoiDung;
    private javax.swing.JTabbedPane pnChuaQuanLyVanBan;
    private javax.swing.JPanel pnChuaThan;
    private javax.swing.JTabbedPane pnChuaTimKiemThongKe;
    private javax.swing.JPanel pnDSNoibo;
    private javax.swing.JPanel pnKy;
    private javax.swing.JPanel pnNguoiDung;
    private javax.swing.JPanel pnPhongBan;
    private javax.swing.JPanel pnQuanLyVanBan;
    private javax.swing.JPanel pnThan;
    private javax.swing.JPanel pnTimKiemThongKe;
    private javax.swing.JPanel pnTrangCHU;
    private javax.swing.JPanel pnVanBanNoiBo;
    private javax.swing.JPasswordField pwMatKhau;
    private javax.swing.JTable tbDinhKem;
    private javax.swing.JTable tbPhongBan;
    private javax.swing.JTable tbVanBanNoiBo;
    private javax.swing.JTable tbVanBanNoiBo1;
    private javax.swing.JTable tblDanhSachVanBanDen;
    private javax.swing.JTable tblDanhSachVanBanDi;
    private javax.swing.JTable tblLoaiVanBan;
    private javax.swing.JTable tblNguoiDung;
    private javax.swing.JTable tblNoiBanHanh;
    private javax.swing.JTable tblSoVanBan;
    private javax.swing.JTable tblTimKiemDanhSachVanBanDen;
    private javax.swing.JTable tblTimKiemDanhSachVanBanDi;
    private javax.swing.JTextField txtDinhKemFileDen;
    private javax.swing.JTextField txtDinhKemFileDi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtNguoiDuyet;
    private javax.swing.JTextField txtNguoiDuyet1;
    private javax.swing.JTextField txtNguoiDuyetDen;
    private javax.swing.JTextField txtNguoiDuyetDi;
    private javax.swing.JTextField txtNguoiGui;
    private javax.swing.JTextField txtNguoiGuiDi;
    private javax.swing.JTextField txtNguoiKy;
    private javax.swing.JTextField txtNguoiKy1;
    private javax.swing.JTextField txtNguoiKyDen;
    private javax.swing.JTextField txtNguoiKyDi;
    private javax.swing.JTextField txtNguoiNhan;
    private javax.swing.JTextField txtNguoiNhan1;
    private javax.swing.JTextField txtNguoiNhanDen;
    private javax.swing.JTextField txtNoiBanHanh;
    private javax.swing.JTextField txtNoiBanHanhTimKiemDen;
    private javax.swing.JTextArea txtNoiDung;
    private javax.swing.JTextArea txtNoiDungDen;
    private javax.swing.JTextArea txtNoiDungDi;
    private javax.swing.JTextField txtNoiNhanDi;
    private javax.swing.JTextField txtNoiNhanTimKiemDi;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoDen;
    private javax.swing.JTextField txtSoDi;
    private javax.swing.JTextField txtSoKiHieuDi;
    private javax.swing.JTextField txtSoKyHieu;
    private javax.swing.JTextField txtSoKyHieuDen;
    private javax.swing.JTextField txtSoLuongBanDi;
    private javax.swing.JTextField txtSoTrangDen;
    private javax.swing.JTextField txtSoTrangDi;
    private javax.swing.JTextField txtTenTaiKhoan;
    private javax.swing.JTextField txtTenVanBan;
    private javax.swing.JTextArea txtTimKiemDen;
    private javax.swing.JTextArea txtTimKiemDi;
    private javax.swing.JTextField txtTimKiemSoKyHieuDen;
    private javax.swing.JTextField txtTimKiemSoKyHieuDi;
    private javax.swing.JTextArea txtTrichYeu;
    private javax.swing.JTextArea txtTrichYeuDen;
    private javax.swing.JTextArea txtTrichYeuDi;
    // End of variables declaration//GEN-END:variables

    
    
}
