package huffman;
//Karla Maricruz Ruiz Diaz 203440
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {
    static Scanner entrada= new Scanner (System.in);
    public static void main(String[] args) {
        String texto;
        System.out.println("Ingrese primer texto:" );
        texto=entrada.nextLine();

        Huffman h = new Huffman();
        HashMap<String, Integer> tabla = new HashMap<>();
        tabla= h.getFrecuencias(texto);
        System.out.println("Tabla de Frecuencias:\n" + tabla);

        ArrayList<Nodo> lista = new ArrayList<>();
        lista = h.getLista(tabla);
        Collections.sort(lista);
        //System.out.println("Lista de nodos:\n" + lista);
        Nodo raiz = h.getRaiz(lista);
        //System.out.println("Raiz:" + raiz);
        String cadena="";
        HashMap mapa = new HashMap();
        mapa = h.getpreOrder(raiz, cadena);
        System.out.println("Tabla de Bitcodes:\n" + mapa );

        String codigo;
        codigo = Huffman.codificarTexto(texto, mapa);
        System.out.println("Texto codificado:\n" + codigo );
       /* String Original;
        Original = Huffman.decodificar(codigo, mapa);
        System.out.println("Texto original:\n" + Original );*/

        String mensaje;
        System.out.println("Ingresa segundo texto" );
        mensaje=entrada.nextLine();

        Huffman hu =new Huffman();

        HashMap<String, Integer> tabla2 = new HashMap<>();
        tabla2= hu.getFrecuencias(mensaje);

        ArrayList<Nodo> lista2 = new ArrayList<>();
        lista2 = hu.getLista(tabla2);
        Collections.sort(lista2);

        Nodo raiz2 = hu.getRaiz(lista2);

        String Texto="";
        HashMap mapa2 = new HashMap();
        mapa2 = hu.getpreOrder(raiz2, Texto);

        String bitcode;
        bitcode = Huffman.codificarTexto(mensaje, mapa2);

        String Original;
        Original = Huffman.decodificar(bitcode, mapa2);
        System.out.println("Texto Decodificado:\n" + Original);
    }
}
