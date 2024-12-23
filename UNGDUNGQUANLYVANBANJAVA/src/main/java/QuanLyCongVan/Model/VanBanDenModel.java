package QuanLyCongVan.Model;

import java.util.Date;

public class VanBanDenModel {
    private int id;
    private String tenSo; // Tương ứng với cột `tenso` (nvarchar)
    private int nam;
    private String soKyHieu; // Tương ứng với `soKyHieu` (varchar)
    private Date ngayBanHanh; // Tương ứng với `ngayBanHanh` (datetime)
    private String noiBanHanh; // Tương ứng với `noiBanHanh` (nvarchar)
    private String loaiVanBan; // Tương ứng với `loaiVanBan` (nvarchar)
    private int soDen; // Tương ứng với `soDen` (int)
    private Date ngayDen; // Tương ứng với `ngayDen` (datetime)
    private int soTrang; // Tương ứng với `soTrang` (int)
    private String nguoiNhan; // Tương ứng với `nguoiNhan` (nvarchar)
    private String nguoiKy; // Tương ứng với `nguoiKy` (nvarchar)
    private String nguoiDuyet; // Tương ứng với `nguoiDuyet` (nvarchar)
    private String trichYeu; // Tương ứng với `trichYeu` (nvarchar(max))
    private String noiDung; // Tương ứng với `noiDung` (nvarchar(max))
    private String duongDanFile; // Tương ứng với `dinhKemfile` (nvarchar(max))
    private boolean daXoa; // Tương ứng với `daXoa` (bit)

    public VanBanDenModel() {
    }

    public VanBanDenModel(int id, String tenSo, int nam, String soKyHieu, Date ngayBanHanh, String noiBanHanh, String loaiVanBan, int soDen, Date ngayDen, int soTrang, String nguoiNhan, String nguoiKy, String nguoiDuyet, String trichYeu, String noiDung, String duongDanFile, boolean daXoa) {
        this.id = id;
        this.tenSo = tenSo;
        this.nam = nam;
        this.soKyHieu = soKyHieu;
        this.ngayBanHanh = ngayBanHanh;
        this.noiBanHanh = noiBanHanh;
        this.loaiVanBan = loaiVanBan;
        this.soDen = soDen;
        this.ngayDen = ngayDen;
        this.soTrang = soTrang;
        this.nguoiNhan = nguoiNhan;
        this.nguoiKy = nguoiKy;
        this.nguoiDuyet = nguoiDuyet;
        this.trichYeu = trichYeu;
        this.noiDung = noiDung;
        this.duongDanFile = duongDanFile;
        this.daXoa = daXoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSo() {
        return tenSo;
    }

    public void setTenSo(String tenSo) {
        this.tenSo = tenSo;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public String getSoKyHieu() {
        return soKyHieu;
    }

    public void setSoKyHieu(String soKyHieu) {
        this.soKyHieu = soKyHieu;
    }

    public Date getNgayBanHanh() {
        return ngayBanHanh;
    }

    public void setNgayBanHanh(Date ngayBanHanh) {
        this.ngayBanHanh = ngayBanHanh;
    }

    public String getNoiBanHanh() {
        return noiBanHanh;
    }

    public void setNoiBanHanh(String noiBanHanh) {
        this.noiBanHanh = noiBanHanh;
    }

    public String getLoaiVanBan() {
        return loaiVanBan;
    }

    public void setLoaiVanBan(String loaiVanBan) {
        this.loaiVanBan = loaiVanBan;
    }

    public int getSoDen() {
        return soDen;
    }

    public void setSoDen(int soDen) {
        this.soDen = soDen;
    }

    public Date getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public String getNguoiNhan() {
        return nguoiNhan;
    }

    public void setNguoiNhan(String nguoiNhan) {
        this.nguoiNhan = nguoiNhan;
    }

    public String getNguoiKy() {
        return nguoiKy;
    }

    public void setNguoiKy(String nguoiKy) {
        this.nguoiKy = nguoiKy;
    }

    public String getNguoiDuyet() {
        return nguoiDuyet;
    }

    public void setNguoiDuyet(String nguoiDuyet) {
        this.nguoiDuyet = nguoiDuyet;
    }

    public String getTrichYeu() {
        return trichYeu;
    }

    public void setTrichYeu(String trichYeu) {
        this.trichYeu = trichYeu;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getDuongDanFile() {
        return duongDanFile;
    }

    public void setDuongDanFile(String duongDanFile) {
        this.duongDanFile = duongDanFile;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    @Override
    public String toString() {
        return "VanBanDenModel{" + "id=" + id + ", tenSo=" + tenSo + ", nam=" + nam + ", soKyHieu=" + soKyHieu + ", ngayBanHanh=" + ngayBanHanh + ", noiBanHanh=" + noiBanHanh + ", loaiVanBan=" + loaiVanBan + ", soDen=" + soDen + ", ngayDen=" + ngayDen + ", soTrang=" + soTrang + ", nguoiNhan=" + nguoiNhan + ", nguoiKy=" + nguoiKy + ", nguoiDuyet=" + nguoiDuyet + ", trichYeu=" + trichYeu + ", noiDung=" + noiDung + ", duongDanFile=" + duongDanFile + ", daXoa=" + daXoa + '}';
    }

    
}
