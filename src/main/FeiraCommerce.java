/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import model.DAO.PointDAO;
import model.DAO.SpatialObjectDAO;
import model.bean.Point;
import model.bean.SpatialObject;
import tools.Bot;

/**
 *
 * @author Emerson
 */
public class FeiraCommerce {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bot.downloadFile();
        Bot.changeData("FeiraDeSantana.osm");
        
        Point p = new Point(1,1.0,1.0);
        SpatialObject sp = new SpatialObject("nome", "tipo", p);
        //SpatialObjectDAO.create(sp);
        
        
    }
    
}
