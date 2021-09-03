/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Jonas Santos
 */
public class connectionFactory {
    private static Connection conexao;
    
    private static String getDataSource(){
        String path = "C:\\Program Files (x86)\\Conatus\\parameters\\datasource.ini";
        String teste=null;
        teste=null;
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line=null;
            line = br.readLine();
            return line;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Arquivo datasource.ini não encontrado");
            System.exit(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Falha ao consultar arquivo datasource.ini");
            System.exit(0);
        }
        return null;
    }
       
    public static Connection getconnection(){
        try{
            if(conexao==null){
                Class.forName("org.firebirdsql.jdbc.FBDriver"); 
                String criptografia="?encoding=ISO8859_1";
                String Base=getDataSource();
                conexao= DriverManager.getConnection(
                    "jdbc:firebirdsql:"+Base+criptografia,
                    "SYSDBA", "masterkey");
            }
            
            return conexao;
        }
        catch (ClassNotFoundException cnfe) {
                JOptionPane.showMessageDialog(null, "Erro no driver JBDC");
                System.exit(0);
                return null;
            }
        catch(SQLException SQLex){
            SQLex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possivel se conectar ao Banco de dados");
            System.exit(0);
            return null;
        }
    } 
}
