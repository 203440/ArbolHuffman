package huffman;

//Karla Maricruz Ruiz Diaz 203440
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Huffman {
   
   public HashMap<String, Integer> getFrecuencias(String texto){
        HashMap<String, Integer> mapaFrecuencia = new HashMap<>();
        for (int i=0; i < texto.length(); i++){
            String car = "" + texto.charAt(i);
            if (mapaFrecuencia.containsKey(car)) {
                mapaFrecuencia.put(car, mapaFrecuencia.get(car) + 1);
            }else{
                mapaFrecuencia.put(car,1);
            }

        }
        return mapaFrecuencia;
    }
    public ArrayList<Nodo> getLista(HashMap<String, Integer> h){
        ArrayList<Nodo> lista = new ArrayList<>();
        h.forEach( (k,v) -> {
                        Nodo nodo = new Nodo(k,v);
                        lista.add(nodo);
                     }
                );
        return lista;
    }
    public Nodo getRaiz(ArrayList<Nodo> Nodos){
        while (Nodos.size() > 1){
            Nodo nodo0 = null, nodo1 = null;
            nodo0 = Nodos.remove(0);
            nodo1 = Nodos.remove(0);
            Nodo nodoNuevo = new Nodo(nodo0.getLetra()+nodo1.getLetra(), nodo0.getFrecuencia()+nodo1.getFrecuencia());
            nodoNuevo.setIzq(nodo0);
            nodoNuevo.setDer(nodo1);
            Nodos.add(nodoNuevo);
            Collections.sort(Nodos);
            //System.out.println("Nodo Nuevo " + nodoNuevo);
        }
        Nodo raiz = Nodos.get(0);
        //System.out.println("lista Nodos proceso: " + Nodos);
        return raiz;

    }
    HashMap<String, String> bitcodes = new HashMap <String, String> ();
    public HashMap getpreOrder(Nodo reco, String cadena){
      
        if(reco!= null){
                 if(reco.getIzq()== null && reco.getDer() == null){
                 //System.out.println("hoja: " + reco.getLetra() + " " + cadena);
              
                 bitcodes.put(reco.getLetra(), cadena);
                }
            getpreOrder(reco.getIzq(), cadena+"0");
            getpreOrder(reco.getDer(), cadena+"1");
        }
        return bitcodes;
    }

    public static String codificarTexto(String texto, HashMap<String,String> Tabla){
        String bitcodes= "";
        for(int i=0;i<texto.length();i++){            
            if(Tabla.containsKey(texto.charAt(i)+"")){
                bitcodes+=Tabla.get(texto.charAt(i)+"");
            }
        }
        return bitcodes;
    }


    
    public static String decodificar(String codigo, HashMap<String,String> Tabla){
        return decodificar(0,codigo,Tabla);
    }
    private static String decodificar(int primero,String codigo, HashMap<String,String> Tabla){
        String regresarOriginal = "";
        if(primero==0 && codigo.length()==0) 
        return regresarOriginal;
        else if(codigo.length()==0) 
        return regresarOriginal;
        else{
            for (HashMap.Entry<String,String> entry : Tabla.entrySet()){
                int tam = entry.getValue().length();
                if(tam<=codigo.length()){
                    if(entry.getValue().equals(codigo.subSequence(primero,tam))){
                        regresarOriginal+=entry.getKey();
                        String cod2;
                        cod2= codigo.substring(tam,codigo.length());
                        regresarOriginal+=decodificar(0,cod2,Tabla);
                    }
                }
            }
            return regresarOriginal;
        }        
    }
    
}
