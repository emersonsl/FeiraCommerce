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
import model.bean.SpatialObject;

/**
 *
 * @author Emerson
 */
public class SpatialObjectDAO {
    
    public static void create(SpatialObject sp){
        
        PointDAO.create(sp.getPoint());
        
        Connection c = Conection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = c.prepareStatement("INSERT INTO spatial_object (name,type,point_id) VALUES (?,?,?)");
            
            stmt.setString(1, sp.getName());
            stmt.setString(2, sp.getType());
            stmt.setDouble(3, sp.getPoint().getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SpatialObjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conection.closeConnection(c, stmt);
        }
    }
    
}
