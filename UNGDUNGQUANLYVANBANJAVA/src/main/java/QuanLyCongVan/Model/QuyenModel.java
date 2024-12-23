package QuanLyCongVan.Model;

public class QuyenModel {
    private String tenQuyen; // Tên quyền
    private String moTa;     // Mô tả quyền

    public QuyenModel() {
    }

    public QuyenModel(String tenQuyen, String moTa) {
        this.tenQuyen = tenQuyen;
        this.moTa = moTa;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "QuyenModel{" +
                "tenQuyen='" + tenQuyen + '\'' +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
