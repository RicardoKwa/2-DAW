/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. /home/ricardok/Escritorio/gato.pgm
 */
package imagenentregable;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author ricardok
 */
public class ExecPGM {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Image img = null;
        String opcion = "";
        String nombreFichero = "";
        
        boolean cargaCorrecta = false;

        do {
            System.out.print("Introduce el fichero(ruta) : ");
            nombreFichero = sc.nextLine();

            try {
                img = new Image(nombreFichero);
                cargaCorrecta = true;

            } catch (NumberFormatException e) {
                System.out.println("Formato de fichero incorrecto");
            } catch (FileNotFoundException e) {
                System.out.println("No se ha encontrado el fichero");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error inesperado al abrir el fichero");
            }

        } while (!cargaCorrecta);
        do {
            opcion = menu(sc);
            switch (opcion) {
                case "1":
                    img.girarIzquierda();
                    break;
                case "2":
                    img.girarDerecha();
                    break;
                case "3":
                    img.flipHorizontal();
                    break;
                case "4":
                    img.flipVertical();
                    break;
                case "5":
                    img.negativo();
                    break;
                case "6":
                    img.filtroCaja();
                    break;
                case "G":
                    try {
                        System.out.print("Introduzca el nombre y la ruta del fichero : ");
                        nombreFichero = sc.nextLine();
                        img.guardarFichero(nombreFichero);
                    } catch (IOException ex) {
                        System.out.println("Se ha producido un error durante la escritura del archivo");
                    } catch (Exception e) {
                        System.out.println("Ha ocurrido un error inesperado al guardar el fichero");
                    }
                    break;
            }

        } while (!(opcion.equals("S")));

    }

    public static String menu(Scanner sc) {
        String opcion = "";
        System.out.println("1.- Girar Izquierda");
        System.out.println("2.- Girar Derecha");
        System.out.println("3.- Flip Horizontal");
        System.out.println("4.- Flip Vertical");
        System.out.println("5.- Obtener Negativo");
        System.out.println("6.- Filtro Caja");
        System.out.println("G.- Guardar Fichero");
        System.out.println("S.- Salir");
        do {
            System.out.print("Elige una opción : ");
            opcion = sc.nextLine().toUpperCase();
        } while (!(opcion.equals("1") || opcion.equals("2") || opcion.equals("3") || opcion.equals("4")
                || opcion.equals("5") || opcion.equals("6") || opcion.equals("G") || opcion.equals("S")));

        return opcion;
    }
}
