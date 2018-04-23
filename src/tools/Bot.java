/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
}
