
package QuanLyCongVan.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VanBanNoiBo {
    private String SoKyHieu;
    private String TenVanBan;
    private String NgayBanHanh;
    private String LoaiBanHanh;
    private String PhongBanHanh;
    private String PhongNhan;
    private String NguoiNhan;
    private String NguoiKy;
    private String NguoiDuyet;
    private String TrichYeu;
    private String NoiDung;
    // Danh sách tài liệu đính kèm
    private List<TaiLieu> danhSachTaiLieu;
       
    

    public VanBanNoiBo() {
        danhSachTaiLieu = new ArrayList<>();
    }
public VanBanNoiBo(ResultSet rs) throws SQLException{
    this.SoKyHieu = rs.getString("SoKyHieu");
    this.TenVanBan = rs.getString("TenVanBan");
    this.NgayBanHanh = rs.getString("NgayBanHanh");
    this.LoaiBanHanh = rs.getString("LoaiBanHanh");
    this.PhongBanHanh = rs.getString("PhongBanHanh");
    this.PhongNhan = rs.getString("PhongNhan");
    this.NguoiNhan = rs.getString("NguoiNhan");
    this.NguoiKy = rs.getString("NguoiKy");
    this.NguoiDuyet = rs.getString("NguoiDuyet");
    this.NoiDung = rs.getString("NoiDung");
    this.TrichYeu = rs.getString("TrichYeu");
        
}



    public VanBanNoiBo(String SoKyHieu, String TenVanBan, String NgayBanHanh, String LoaiBanHanh, String PhongBanHanh, String PhongNhan, String NguoiNhan, String NguoiKy, String NguoiDuyet, String TrichYeu, String NoiDung,  List<TaiLieu> danhSachTaiLieu) {
        this.SoKyHieu = SoKyHieu;
        this.TenVanBan = TenVanBan;
        this.NgayBanHanh = NgayBanHanh;
        this.LoaiBanHanh = LoaiBanHanh;
        this.PhongBanHanh = PhongBanHanh;
        this.PhongNhan = PhongNhan;
        this.NguoiNhan = NguoiNhan;
        this.NguoiKy = NguoiKy;
        this.NguoiDuyet = NguoiDuyet;
        this.TrichYeu = TrichYeu;
        this.NoiDung = NoiDung;
         this.danhSachTaiLieu = danhSachTaiLieu;
    }



    public String getSoKyHieu() {
        return SoKyHieu;
    }

    public String getTenVanBan() {
        return TenVanBan;
    }

    public String getNgayBanHanh() {
        return NgayBanHanh;
    }

    public String getLoaiBanHanh() {
        return LoaiBanHanh;
    }

    public String getPhongBanHanh() {
        return PhongBanHanh;
    }

    public String getPhongNhan() {
        return PhongNhan;
    }

    public String getNguoiNhan() {
        return NguoiNhan;
    }

    public String getNguoiKy() {
        return NguoiKy;
    }

    public String getNguoiDuyet() {
        return NguoiDuyet;
    }

    public String getTrichYeu() {
        return TrichYeu;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setSoKyHieu(String SoKyHieu) {
        this.SoKyHieu = SoKyHieu;
    }

    public void setTenVanBan(String TenVanBan) {
        this.TenVanBan = TenVanBan;
    }

    public void setNgayBanHanh(String NgayBanHanh) {
        this.NgayBanHanh = NgayBanHanh;
    }

    public void setLoaiBanHanh(String LoaiBanHanh) {
        this.LoaiBanHanh = LoaiBanHanh;
    }

    public void setPhongBanHanh(String PhongBanHanh) {
        this.PhongBanHanh = PhongBanHanh;
    }

    public void setPhongNhan(String PhongNhan) {
        this.PhongNhan = PhongNhan;
    }

    public void setNguoiNhan(String NguoiNhan) {
        this.NguoiNhan = NguoiNhan;
    }

    public void setNguoiKy(String NguoiKy) {
        this.NguoiKy = NguoiKy;
    }

    public void setNguoiDuyet(String NguoiDuyet) {
        this.NguoiDuyet = NguoiDuyet;
    }

    public void setTrichYeu(String TrichYeu) {
        this.TrichYeu = TrichYeu;
    }

 

    public void setNoiDung(String NoiDung) {
        this.NoiDung = NoiDung;
    }
    @Override
public String toString() {
    return "VanBanNoiBo{" +
            "SoKyHieu='" + SoKyHieu + '\'' +
            ", TenVanBan='" + TenVanBan + '\'' +
            ", NgayBanHanh=" + NgayBanHanh +
            ", LoaiBanHanh='" + LoaiBanHanh + '\'' +
            ", PhongBanHanh='" + PhongBanHanh + '\'' +
            ", PhongNhan='" + PhongNhan + '\'' +
            ", NguoiNhan='" + NguoiNhan + '\'' +
            ", NguoiKy='" + NguoiKy + '\'' +
            ", NguoiDuyet='" + NguoiDuyet + '\'' +
            ", TrichYeu='" + TrichYeu + '\'' +
            ", NoiDung='" + NoiDung + '\'' +
            '}';
}
   public List<TaiLieu> getDanhSachTaiLieu() {
        return danhSachTaiLieu;
    }

    public void setDanhSachTaiLieu(List<TaiLieu> danhSachTaiLieu) {
        this.danhSachTaiLieu = danhSachTaiLieu;
    }

    // Thêm tài liệu vào danh sách
    public void themTaiLieu(TaiLieu taiLieu) {
        if (danhSachTaiLieu == null) {
            danhSachTaiLieu = new ArrayList<>();
        }
        danhSachTaiLieu.add(taiLieu);
    }
    
}
