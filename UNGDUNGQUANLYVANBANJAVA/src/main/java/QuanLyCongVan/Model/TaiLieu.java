package QuanLyCongVan.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiLieu {
    private String MaTL;
    private String TenTL;
    private String NgayTao;
    private String KichCo;
    private String  Loai;
    private String DuongDan;
    
    public TaiLieu(ResultSet rs) throws SQLException{
        this.MaTL = rs.getString("MaTL");
        this.TenTL = rs.getString("TenTL");
        this.NgayTao = rs.getString("NgayTao");
        this.KichCo = rs.getString("KichCo");
        this.Loai = rs.getString("Loai");
        this.DuongDan = rs.getString("DuongDan");
    }

    public TaiLieu() {
    }

    public TaiLieu(String MaTL, String TenTL, String NgayTao, String KichCo, String Loai, String DuongDan) {
        this.MaTL = MaTL;
        this.TenTL = TenTL;
        this.NgayTao = NgayTao;
        this.KichCo = KichCo;
        this.Loai = Loai;
        this.DuongDan = DuongDan;
    }

    public String getMaTL() {
        return MaTL;
    }

    public String getTenTL() {
        return TenTL;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public String getKichCo() {
        return KichCo;
    }

    public String getLoai() {
        return Loai;
    }

    public String getDuongDan() {
        return DuongDan;
    }

    public void setMaTL(String MaTL) {
        this.MaTL = MaTL;
    }

    public void setTenTL(String TenTL) {
        this.TenTL = TenTL;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public void setKichCo(String KichCo) {
        this.KichCo = KichCo;
    }

    public void setLoai(String Loai) {
        this.Loai = Loai;
    }

    public void setDuongDan(String DuongDan) {
        this.DuongDan = DuongDan;
    }

   
    

}
