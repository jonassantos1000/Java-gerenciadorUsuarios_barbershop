/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Connection.connectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Usuarios;

/**
 *
 * @author jonas
 */
public class UsuarioDAO {
    Usuarios usuario;
    List <Usuarios> listUsers = new ArrayList<Usuarios>();
    public UsuarioDAO (Usuarios usuario){
        this.usuario=usuario;
    }
       
    public boolean insert(){
        try{
            String SQLINCLUIR= "INSERT INTO USUARIOS (USUARIO,SENHA,GERENCIA) VALUES (?,?,?)";       
            PreparedStatement pst= Connection.connectionFactory.getconnection().prepareStatement(SQLINCLUIR);
            pst.setString(1, usuario.getUsuario());
            pst.setString(2, usuario.getSenha());
            pst.setString(3, usuario.getGerencia());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso !");
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Já existe um usuario com este login ! \n Informe outro login e tente efetivar");
            return false;
        }
    }
  
    public boolean update(){
        try{
            String SQLUPDATE= "UPDATE USUARIOS SET SENHA=?, GERENCIA=? WHERE USUARIO=?";       
            PreparedStatement pst= Connection.connectionFactory.getconnection().prepareStatement(SQLUPDATE);
            pst.setString(1, usuario.getSenha());
            pst.setString(2, usuario.getGerencia());
            pst.setString(3, usuario.getUsuario());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso !");
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar as alterações! \n Tente efetivar o processo novamente");
            return false;
        }
    }
    
    public boolean delete(){
        try{
            String SQLDELETE= "DELETE FROM USUARIOS WHERE USUARIO=?";       
            PreparedStatement pst= Connection.connectionFactory.getconnection().prepareStatement(SQLDELETE);
            pst.setString(1, usuario.getUsuario());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario apagado com sucesso !");
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao apagar o usuario! \n Tente efetivar o processo novamente");
            e.printStackTrace();
            return false;
        }
    }
    
    public Usuarios selectUsuario(){
        try{
            String CONSULTACADASTRO;
            CONSULTACADASTRO="SELECT * FROM USUARIOS WHERE USUARIO='"+usuario.getUsuario()+"'";
            PreparedStatement pst= Connection.connectionFactory.getconnection().prepareStatement(CONSULTACADASTRO);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Usuarios selectUsuario = new Usuarios(rs.getString("USUARIO"),rs.getString("SENHA"),rs.getString("GERENCIA"));
            return selectUsuario;
            }
                       
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return null;
    }
    
    public List<Usuarios> selectAlteraUsuario(){
        try{
            String CONSULTACADASTRO;
            CONSULTACADASTRO="SELECT * FROM USUARIOS";
            PreparedStatement pst= Connection.connectionFactory.getconnection().prepareStatement(CONSULTACADASTRO);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Usuarios selectUsuario = new Usuarios(rs.getString("USUARIO"),rs.getString("SENHA"),rs.getString("GERENCIA"));
                listUsers.add(selectUsuario);  
            }
            return listUsers;
                       
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            return listUsers;
        }       
    }
}
