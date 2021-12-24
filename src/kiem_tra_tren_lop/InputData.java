/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiem_tra_tren_lop;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class InputData {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            FileInputStream fis = new FileInputStream("QLSV.txt");
            DataInputStream dis = new DataInputStream(fis);
            
            int n = dis.readInt();
            double m = dis.readDouble();
            
            fis.close();
            fis.close();
            
            System.out.println("số nguyên: "+n);
            System.out.println("số thực: "+m);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
