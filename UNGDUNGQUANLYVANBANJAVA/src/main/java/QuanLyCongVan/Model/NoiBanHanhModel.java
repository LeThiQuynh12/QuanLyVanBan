/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyCongVan.Model;

public class NoiBanHanhModel {
    private int maNoiBanHanh;
    private String noiBanHanh;
    private String moTa;
    private boolean daXoa;

    public NoiBanHanhModel() {
    }

    public NoiBanHanhModel(int maNoiBanHanh, String noiBanHanh, String moTa, boolean daXoa) {
        this.maNoiBanHanh = maNoiBanHanh;
        this.noiBanHanh = noiBanHanh;
        this.moTa = moTa;
        this.daXoa = daXoa;
    }

    public int getMaNoiBanHanh() {
        return maNoiBanHanh;
    }

    public void setMaNoiBanHanh(int maNoiBanHanh) {
        this.maNoiBanHanh = maNoiBanHanh;
    }

    public String getNoiBanHanh() {
        return noiBanHanh;
    }

    public void setNoiBanHanh(String noiBanHanh) {
        this.noiBanHanh = noiBanHanh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public boolean isDaXoa() {
        return daXoa;
    }

    public void setDaXoa(boolean daXoa) {
        this.daXoa = daXoa;
    }

    @Override
    public String toString() {
        return "NoiBanHanhModel{" + "maNoiBanHanh=" + maNoiBanHanh + ", noiBanHanh=" + noiBanHanh + ", moTa=" + moTa + ", daXoa=" + daXoa + '}';
    }
    
}
