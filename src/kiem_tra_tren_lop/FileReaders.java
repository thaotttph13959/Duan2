/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiem_tra_tren_lop;

import java.io.BufferedReader;
import java.io.*;

/**
 *
 * @author PC
 */
public class FileReaders {
    public static void main(String[] args) {
        try {
            //b1: tạo đối tượng
            File f = new File("FileQLSV.txt");
            FileReader filedoc = new FileReader(f);
            //b2: dọc dữ liệu
            BufferedReader br = new BufferedReader(filedoc);
            String s;
            int i=0;
            while((s = br.readLine()) != null){
                // đọc từng dòng đến khi đọc đến null -> kết thúc
                i++;
                System.out.println(i+" "+s);
            }
            // b3: đóng luồng
            filedoc.close();
            br.close();
        } catch (Exception e) {
        }
    }
}
