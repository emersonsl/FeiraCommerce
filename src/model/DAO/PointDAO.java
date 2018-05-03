/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import conection.Conection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Point;

/**
 *
 * @author Emerson
 */
public class PointDAO {
    
    public static void create(Point p){
        Connection c = Conection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = c.prepareStatement("INSERT INTO point (id,latitude,longitude) VALUES (?,?,?)");
            
            stmt.setDouble(1, p.getId());
            stmt.setDouble(2, p.getLatitude());
            stmt.setDouble(3, p.getLongitude());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar ponto");
        }finally{
            Conection.closeConnection(c, stmt);
        }
        
    }
    
}
