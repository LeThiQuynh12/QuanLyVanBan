/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyCongVan.Model;

public class LoaiVanBanModel {
    private String maLoai;
    private String loaiVanBan;
    private String moTa;
    private Boolean daXoa;

    public LoaiVanBanModel() {
    }

    public LoaiVanBanModel(String maLoai, String loaiVanBan, String moTa, Boolean daXoa) {
        this.maLoai = maLoai;
        this.loaiVanBan = loaiVanBan;
        this.moTa = moTa;
        this.daXoa = daXoa;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getLoaiVanBan() {
        return loaiVanBan;
    }

    public void setLoaiVanBan(String loaiVanBan) {
        this.loaiVanBan = loaiVanBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean getDaXoa() {
        return daXoa;
    }

    public void setDaXoa(Boolean daXoa) {
        this.daXoa = daXoa;
    }

    @Override
    public String toString() {
        return "LoaiVanBanModel{" + "maLoai=" + maLoai + ", loaiVanBan=" + loaiVanBan + ", moTa=" + moTa + ", daXoa=" + daXoa + '}';
    }

    
}
