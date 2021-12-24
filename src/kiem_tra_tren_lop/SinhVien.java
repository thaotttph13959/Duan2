/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiem_tra_tren_lop;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class SinhVien implements Serializable{
    private String mssv;
    private String hoten;
    private String skill;
    private String ghichu;
    private double diem;
    private String gioitinh;

    public SinhVien() {
    }

    public SinhVien(String mssv, String hoten, String skill, String ghichu, double diem, String gioitinh) {
        this.mssv = mssv;
        this.hoten = hoten;
        this.skill = skill;
        this.ghichu = ghichu;
        this.diem = diem;
        this.gioitinh = gioitinh;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getMssv() {
        return mssv;
    }

    public String getHoten() {
        return hoten;
    }

    public String getSkill() {
        return skill;
    }

    public String getGhichu() {
        return ghichu;
    }

    public double getDiem() {
        return diem;
    }

    public String getGioitinh() {
        return gioitinh;
    }
    
    

    
}
