/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyCongVan.Model;

import java.util.Date;

public class VanBanDiModel {
     private int id;
    private String tenSo;
    private int nam;
    private String soKyHieu;
    private Date ngayBanHanh;
    private String noiNhan;
    private String loaiVanBan;
    private int soDi;
    private int slBan;
    private int soTrang;
    private String nguoiGui;
    private String nguoiKy;
    private String nguoiDuyet;
    private String trichYeu; // Tương ứng với `trichYeu` (nvarchar(max))
    private String noiDung; // Tương ứng với `noiDung` (nvarchar(max))
    private String duongDanFile; // Tương ứng với `dinhKemfile` (nvarchar(max))
    private boolean daXoa; // Tương ứng với `daXoa` (bit)

    public VanBanDiModel() {
    }

    public VanBanDiModel(int id, String tenSo, int nam, String soKyHieu, Date ngayBanHanh, String noiNhan, String loaiVanBan, int soDi, int slBan, int soTrang, String nguoiGui, String nguoiKy, String nguoiDuyet, String trichYeu, String noiDung, String duongDanFile, boolean daXoa) {
        this.id = id;
        this.tenSo = tenSo;
        this.nam = nam;
        this.soKyHieu = soKyHieu;
        this.ngayBanHanh = ngayBanHanh;
        this.noiNhan = noiNhan;
        this.loaiVanBan = loaiVanBan;
        this.soDi = soDi;
        this.slBan = slBan;
        this.soTrang = soTrang;
        this.nguoiGui = nguoiGui;
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

    public String getNoiNhan() {
        return noiNhan;
    }

    public void setNoiNhan(String noiNhan) {
        this.noiNhan = noiNhan;
    }

    public String getLoaiVanBan() {
        return loaiVanBan;
    }

    public void setLoaiVanBan(String loaiVanBan) {
        this.loaiVanBan = loaiVanBan;
    }

    public int getSoDi() {
        return soDi;
    }

    public void setSoDi(int soDi) {
        this.soDi = soDi;
    }

    public int getSlBan() {
        return slBan;
    }

    public void setSlBan(int slBan) {
        this.slBan = slBan;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public String getNguoiGui() {
        return nguoiGui;
    }

    public void setNguoiGui(String nguoiGui) {
        this.nguoiGui = nguoiGui;
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
        return "VanBanDiModel{" + "id=" + id + ", tenSo=" + tenSo + ", nam=" + nam + ", soKyHieu=" + soKyHieu + ", ngayBanHanh=" + ngayBanHanh + ", noiNhan=" + noiNhan + ", loaiVanBan=" + loaiVanBan + ", soDi=" + soDi + ", slBan=" + slBan + ", soTrang=" + soTrang + ", nguoiGui=" + nguoiGui + ", nguoiKy=" + nguoiKy + ", nguoiDuyet=" + nguoiDuyet + ", trichYeu=" + trichYeu + ", noiDung=" + noiDung + ", duongDanFile=" + duongDanFile + ", daXoa=" + daXoa + '}';
    }

    public void setNguoiNhan(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
