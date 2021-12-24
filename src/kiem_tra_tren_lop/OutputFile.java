/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiem_tra_tren_lop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author PC
 */
public class OutputFile {
    public static void main(String[] args) {
        try {
            // b1: tạo đối tượng luồng
            File  taofile = new File("FileQLSV.txt");
            FileWriter filew = new FileWriter(taofile);
            // b2: ghi dữ liệu
            filew.write("nắng nóng quá ae ơi\n");
            filew.write("chiều mời thầy đi uống bia thôi :))");
            //b3: đóng luồng
            filew.close();
        } catch (IOException e) {
            System.out.println("Lỗi "+e);
        }
    }
}
