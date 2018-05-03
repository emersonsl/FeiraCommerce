/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import model.DAO.SpatialObjectDAO;
import model.bean.SpatialObject;

/**
 *
 * @author Emerson
 */
public class Bot {
    
    public static void downloadFile(){
        try {
            URL mapFSA = new URL("http://www.openstreetmap.org/api/0.6/map?bbox=-39.008%2C-12.3179%2C-38.9149%2C-12.1739");
            
            InputStream in = mapFSA.openStream();
            FileOutputStream out = new FileOutputStream("FeiraDeSantana.osm");
           
            int uByte;
            while((uByte=in.read())!=-1){
                out.write(uByte);
            }
            in.close();
            out.close();
            
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Erro no download dos arquivos de base");
        } catch (IOException ex1) {
            throw new RuntimeException("Erro no download dos arquivos de base");
        }
    }
    
    public static void changeData(String nameFile){
        try {
            salveData(Parser.extractData(nameFile));
        } catch (FileNotFoundException | XMLStreamException ex) {
            throw new RuntimeException("Erro ao carregar dados do arquivo");
        }
    }
    
    private static void salveData(List <SpatialObject> list){
        list.stream().forEach((sp) -> {
            SpatialObjectDAO.create(sp);
        });
    }
    
}
