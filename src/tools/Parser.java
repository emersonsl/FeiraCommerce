/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import model.bean.Point;
import model.bean.SpatialObject;

/**
 *
 * @author Emerson
 */
public class Parser {
    public static List <SpatialObject> extractData(String nameFile) throws FileNotFoundException, XMLStreamException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(new FileReader(nameFile));

        HashMap<Long, Point> nodes = new HashMap<>();
        List<SpatialObject> list = new LinkedList<>();

        SpatialObject sp = null;
        while (parser.hasNext()) {
            int event = parser.next();
            
            switch (event) {
                case XMLStreamReader.START_ELEMENT:
                    switch (parser.getName().getLocalPart()) {
                        case "node":
                            Double lat = getAttValueDouble(parser, "lat");
                            Double lgt = getAttValueDouble(parser, "lon");
                            Long id = getAttValueLong(parser, "id");

                            if (id != null && lat != null && lgt != null) {
                                Point p = new Point(id, lat, lgt);
                                sp = new SpatialObject();
                                sp.setPoint(p);
                                sp.setId(id);
                                nodes.put(id, p);
                            }
                            break;
                        case "way":
                            sp = new SpatialObject();
                            Long idSp = getAttValueLong(parser, "id");
                            sp.setId(idSp);
                            break;
                        case "nd":
                            if(sp!=null){
                                Long value = getAttValueLong(parser, "ref");
                                sp.setPoint(nodes.get(value));
                            }
                            break;
                        case "tag":
                            String aux = getAttValue(parser, "k");
                            if (aux.equals("amenity")) {
                                if(sp!=null)
                                    sp.setType(getAttValue(parser, "v"));
                            } else if (aux.equals("name")) {
                                if(sp!=null)
                                    sp.setName(getAttValue(parser, "v"));
                            }
                            break;
                    }
                    break;
                case XMLStreamReader.CHARACTERS:
                    break;
                case XMLStreamReader.END_ELEMENT:
                    
                    if (parser.getName().getLocalPart().equals("node")||parser.getName().getLocalPart().equals("way")) {
                        if (sp != null && sp.isValid()) {
                            list.add(sp);
                            sp = null;
                        }
                    }
                    break;
            }
        }
        System.out.println(list.size() + " objects found!");
        System.out.println(list.toString());
        return list;
    }

    private static Long getAttValueLong(XMLStreamReader parser, String att) {
        String value = getAttValue(parser, att);
        return value == null ? null : Long.parseLong(value);
    }

    private static Double getAttValueDouble(XMLStreamReader parser, String att) {
        String value = getAttValue(parser, att);
        return value == null ? null : Double.parseDouble(value);
    }

    private static String getAttValue(XMLStreamReader parser, String att) {
        for (int i = 0; i < parser.getAttributeCount(); i++) {
            if (parser.getAttributeName(i).getLocalPart().equals(att)) {
                return parser.getAttributeValue(i);
            }
        }
        return null;
    }
    
}
