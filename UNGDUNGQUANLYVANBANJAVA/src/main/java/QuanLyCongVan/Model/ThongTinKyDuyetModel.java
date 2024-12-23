/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyCongVan.Model;

public class ThongTinKyDuyetModel {
    private String nguoiKy;
    private String nguoiDuyet;
    private String nguoiGui;
    private String nguoiNhan;
    private String noiBanHanh;

    public ThongTinKyDuyetModel() {
    }

    public ThongTinKyDuyetModel(String nguoiKy, String nguoiDuyet, String nguoiGui, String nguoiNhan, String noiBanHanh) {
        this.nguoiKy = nguoiKy;
        this.nguoiDuyet = nguoiDuyet;
        this.nguoiGui = nguoiGui;
        this.nguoiNhan = nguoiNhan;
        this.noiBanHanh = noiBanHanh;
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

    public String getNguoiGui() {
        return nguoiGui;
    }

    public void setNguoiGui(String nguoiGui) {
        this.nguoiGui = nguoiGui;
    }

    public String getNguoiNhan() {
        return nguoiNhan;
    }

    public void setNguoiNhan(String nguoiNhan) {
        this.nguoiNhan = nguoiNhan;
    }

    public String getNoiBanHanh() {
        return noiBanHanh;
    }

    public void setNoiBanHanh(String noiBanHanh) {
        this.noiBanHanh = noiBanHanh;
    }

    @Override
    public String toString() {
        return "ThongTinKyDuyetModel{" + "nguoiKy=" + nguoiKy + ", nguoiDuyet=" + nguoiDuyet + ", nguoiGui=" + nguoiGui + ", nguoiNhan=" + nguoiNhan + ", noiBanHanh=" + noiBanHanh + '}';
    }
    
}
