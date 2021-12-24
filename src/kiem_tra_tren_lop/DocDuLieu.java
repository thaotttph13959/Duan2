/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiem_tra_tren_lop;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class DocDuLieu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            FileOutputStream fos = new FileOutputStream("QLSV.txt");
            DataOutputStream dos = new DataOutputStream(fos);
            
            System.out.println("Nhập số:");
            int a = Integer.parseInt(sc.nextLine());
            dos.writeInt(a);
            dos.writeDouble(27.5);
            
            fos.flush();
            fos.close();
            System.out.println("done!");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            
        }
    }
}
