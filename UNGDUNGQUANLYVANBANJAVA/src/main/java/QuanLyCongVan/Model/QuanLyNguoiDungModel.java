
package QuanLyCongVan.Model;

public class QuanLyNguoiDungModel {
    private String hoVaTen;
    private String email;
    private String soDienThoai;
    private String tenTaiKhoan;
    private String matKhau;

    public QuanLyNguoiDungModel(String hoVaTen, String email, String soDienThoai, String tenTaiKhoan, String matKhau) {
        this.hoVaTen = hoVaTen;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }

    public QuanLyNguoiDungModel() {
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "QuanLyNguoiDungModel{" + "hoVaTen=" + hoVaTen + ", email=" + email + ", soDienThoai=" + soDienThoai + ", tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + '}';
    }
}
