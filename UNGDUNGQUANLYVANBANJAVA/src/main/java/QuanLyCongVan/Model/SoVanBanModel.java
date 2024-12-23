/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyCongVan.Model;

public class SoVanBanModel {
    private int maSo;
    private String soVanBan;
    private boolean soDen;
    private int nam;
    private boolean daXoa;

    public SoVanBanModel() {
    }

    public SoVanBanModel(int maSo, String soVanBan, boolean soDen, int nam, boolean daXoa) {
        this.maSo = maSo;
        this.soVanBan = soVanBan;
        this.soDen = soDen;
        this.nam = nam;
        this.daXoa = daXoa;
    }

    public int getMaSo() {
        return maSo;
    }

    public void setMaSo(int maSo) {
        this.maSo = maSo;
    }

    public String getSoVanBan() {
        return soVanBan;
    }

    public void setSoVanBan(String soVanBan) {
        this.soVanBan = soVanBan;
    }

    public boolean isSoDen() {
        return soDen;
    }

    public void setSoDen(boolean soDen) {
        this.soDen = soDen;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    @Override
    public String toString() {
        return "SoVanBanModel{" + "maSo=" + maSo + ", soVanBan=" + soVanBan + ", soDen=" + soDen + ", nam=" + nam + ", daXoa=" + daXoa + '}';
    }

    
    
}
