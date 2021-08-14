/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.security.MessageDigest;

/**
 *
 * @author jonas
 */
public class Criptografia {
    public static String criptografar (String senha) {
        try{              
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
               hexString.append(String.format("%02X", 0xFF & b));
            }
            String senhahex = hexString.toString();
            
        return senhahex;
        }catch(Exception e){
            return null;
        }
        
    }
}