package QuanLyCongVan.Model;

public class DangKyModel {
    private String coQuan;        // Thêm trường CoQuan
    private String hoVaTen;
    private String email;
    private String soDienThoai;
    private String tenTaiKhoan;
    private String matKhau;
    private String vaiTro;        // Thêm trường VaiTro
    private byte[] salt;          // Thêm trường Salt (byte[] tương ứng với VARBINARY trong cơ sở dữ liệu)

    // Constructor mặc định
    public DangKyModel() {
    }

    // Constructor với tất cả các trường
    public DangKyModel(String coQuan, String hoVaTen, String email, String soDienThoai, String tenTaiKhoan, String matKhau, String vaiTro, byte[] salt) {
        this.coQuan = coQuan;
        this.hoVaTen = hoVaTen;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.salt = salt;
    }

    // Getter và Setter
    public String getCoQuan() {
        return coQuan;
    }

    public void setCoQuan(String coQuan) {
        this.coQuan = coQuan;
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

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    // Phương thức toString() để hiển thị thông tin đối tượng
    @Override
    public String toString() {
        return "DangKyModel{" +
                "coQuan='" + coQuan + '\'' +
                ", hoVaTen='" + hoVaTen + '\'' +
                ", email='" + email + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", tenTaiKhoan='" + tenTaiKhoan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", vaiTro='" + vaiTro + '\'' +
                ", salt=" + (salt != null ? "exists" : "null") + '}';
    }
}
