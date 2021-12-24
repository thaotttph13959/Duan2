/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiem_tra_tren_lop;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author PC
 */
public class IOData {
     public static Object readObject(String path){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            Object doituong = ois.readObject();
            ois.close();
            return doituong;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void writeObject(String path, Object data){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(data);
            oos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
