/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_u1_practica2;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Aaron
 */
public class XLMBase {
    private  Alumno[] Alumno;
    private  Alumno eliminado;
    
    
    public XLMBase(){
        Alumno = new Alumno[100];
        for(int i = 0; i<Alumno.length; i++){
            Alumno[i] = new Alumno();
        }
    }
    
    
  public boolean insertar(Alumno data, int indice){
      if(indice >=0 && indice <=100){
          Alumno[indice] = data;
          return true;
      }else{
            return false;
            }
          
      }
      
  public boolean eliminar(int indice){
      if(indice >=0 && indice <=100){
          eliminado = new Alumno();
          eliminado = Alumno[indice];
           Alumno[indice] = new Alumno();
          return true;
      }else{
            return false;
            }
          
      }
  
      public Alumno mostrarEliminado(){
          return eliminado;
      }
  
  public Alumno  buscar(int indice){
      if(indice >=0 && indice <=100){
         return Alumno[indice];
         
      }else{
            return null;
            }
    
  }
  
  public boolean guardar(){
      String cadenaDatos = "";
      for(int i=0;i<Alumno.length;i++){
          cadenaDatos += Alumno[i].getNombre()+"&"+Alumno[i].getId()+"&"+Alumno[i].getPromedio()+"!!";
      }
      try{
          OutputStreamWriter archivo = new OutputStreamWriter (new FileOutputStream("archivo.txt"));
          archivo.write(cadenaDatos);
          archivo.close();
          
          return true;
      }catch(Exception e){
          return false;
      }
      
  }
     
  public boolean cargar(){
      try{
      BufferedReader archivo = new BufferedReader(new FileReader("archivo.txt"));
      String cadenaDatos = archivo.readLine();
      String temporal[] = cadenaDatos.split("!!");
      
      for(int i=0;i<Alumno.length;i++){
          Alumno a = new Alumno();
          String temporal2[] = temporal[i].split("&");
          a.setNombre(temporal2[0]);
          a.setId(Integer.parseInt(temporal2[1]));
          a.setPromedio(Float.parseFloat(temporal2[1]));
      }
      return true;
      }catch(Exception e){
          return false;
      } 
      
      
  }
  
   public   static void generar(String name, ArrayList<String> key,ArrayList<String> value, ArrayList<String> promedio) throws Exception{
       Alumno datos = new Alumno();
            
        
       if(key.isEmpty() || value.isEmpty() || promedio.isEmpty() || key.size()!=value.size()|| key.size()!=promedio.size()){
            System.out.println("ERROR empty ArrayList");
            return;
        }else{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, name, null);
            document.setXmlVersion("1.0");

           
            Element raiz = document.getDocumentElement();
           
            for(int i=0; i<key.size();i++){
             
             
                Element itemNode = document.createElement("Alumno"); 
              
                Element keyNode = document.createElement("Nombre"); 
                Text nodeKeyValue = document.createTextNode(key.get(i));
                keyNode.appendChild(nodeKeyValue);      
                
                 
                Element valueNode = document.createElement("NControl"); 
                Text nodeValueValue = document.createTextNode(value.get(i));
                valueNode.appendChild(nodeValueValue);
                 
                
                 Element promedioNode = document.createElement("Promedio"); 
                Text nodepromedioValue = document.createTextNode(promedio.get(i));
                promedioNode.appendChild(nodepromedioValue);
                
                itemNode.appendChild(keyNode);
                itemNode.appendChild(valueNode);
                itemNode.appendChild(promedioNode);
                
                raiz.appendChild(itemNode); 
              
       }
            
            Source source = new DOMSource(document);
            
            Result result = new StreamResult(new java.io.File(name+".xml")); 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }
       
      
    }
       
       
       
       
      
   
   
   
      
}  
    
  

