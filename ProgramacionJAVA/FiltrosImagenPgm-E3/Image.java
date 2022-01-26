/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenentregable;

import java.io.*;
import java.util.*;

/**
 *
 * @author ricardok
 */
public class Image {

    private String nombreFile;
    private int ancho;
    private int alto;
    private double bAbs;
    private double[][] matriz;

    public Image(String nombreF) throws Exception {
        Scanner sc = null;
        File f = new File(nombreF);
        try {
            sc = new Scanner(f);
            sc.nextLine();
            sc.nextLine();
            this.ancho = Integer.parseInt(sc.next());
            this.alto = Integer.parseInt(sc.next());
            this.bAbs = Integer.parseInt(sc.next());
            this.matriz = new double[this.alto][this.ancho];
            for (int i = 0; i < this.alto; i++) {
                for (int j = 0; j < this.ancho; j++) {
                    this.matriz[i][j] = Integer.parseInt(sc.next()) * (255 / this.bAbs);
                }
            }
            if (this.bAbs != 255) {
                this.bAbs = 255;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    public void guardarFichero(String nuevoNombre) throws Exception {

        FileWriter fw = null;
        File f = new File(nuevoNombre);
        try {
            fw = new FileWriter(f);
            fw.write("P2" + "\n");
            fw.write(this.ancho + " " + this.alto + "\n");
            fw.write(String.valueOf((int) this.bAbs) + "\n");

            for (int i = 0; i < this.matriz.length; i++) {
                for (int j = 0; j < this.matriz[i].length; j++) {
                    fw.write(String.valueOf((int) Math.round(this.matriz[i][j])) + "\n"); //1 pixel por linea
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (Exception e) {
                    throw e;
                }
            }
        }
    }

    public void negativo() {
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                this.matriz[i][j] = this.matriz[i][j] - this.bAbs;
                if (this.matriz[i][j] < 0) {
                    this.matriz[i][j] = Math.abs(this.matriz[i][j]);
                }
            }
        }
    }

    public void flipHorizontal() {
        int aux = this.matriz.length - 1;
        for (int i = aux / 2; i >= 0; i--) {
            double[] temp = this.matriz[i];
            this.matriz[i] = this.matriz[aux - i];
            this.matriz[aux - i] = temp;
        }
    }

    public void flipVertical() {

        int auxAncho = this.matriz[0].length;

        for (double[] fila : this.matriz) {
            for (int p1 = 0; p1 < auxAncho / 2; p1++) {
                int p2 = auxAncho - p1 - 1;
                int temp = (int) fila[p1];
                fila[p1] = fila[p2];
                fila[p2] = temp;
            }
        }
    }

    public void girarIzquierda() {
        double a[][] = new double[this.ancho][this.alto];
        for (int i = 0; i < this.matriz[0].length; i++) {
            for (int j = 0; j < this.matriz.length; j++) {
                a[i][j] = this.matriz[j][this.matriz[0].length - i - 1];
            }
        }
        this.ancho = this.matriz.length;
        this.alto = this.matriz[0].length;
        this.matriz = a;
    }

    public void girarDerecha() {

        double aux[][] = new double[this.ancho][this.alto];
        int auxColumna = this.alto - 1;
        for (int i = 0; i < this.alto; i++, auxColumna--) {
            for (int j = 0; j < this.ancho; j++) {
                aux[j][auxColumna] = this.matriz[i][j];
            }
        }

        this.ancho = aux[0].length;
        this.alto = aux.length;
        this.matriz = aux;
    }

    public void filtroCaja() {

        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                double top = 0;
                double bottom = 0;
                double left = 0;
                double right = 0;
                double topLeft = 0;
                double topRight = 0;
                double bottomLeft = 0;
                double bottomRight = 0;
                int total = 0;
                int contador = 1;

                if (j - 1 >= 0) {
                    left = this.matriz[i][j - 1];
                    contador++;
                }
                if (j + 1 <= this.matriz[0].length - 1) {
                    right = this.matriz[i][j + 1];
                    contador++;
                }
                if (i - 1 >= 0) {
                    top = this.matriz[i - 1][j];
                    contador++;
                }
                if (i + 1 <= this.matriz.length - 1) {
                    bottom = this.matriz[i + 1][j];
                    contador++;
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    topLeft = this.matriz[i - 1][j - 1];
                    contador++;
                }
                if (i - 1 >= 0 && j + 1 <= this.matriz[0].length - 1) {
                    topRight = this.matriz[i - 1][j + 1];
                    contador++;
                }
                if (i + 1 <= this.matriz.length - 1 && j - 1 >= 0) {
                    bottomLeft = this.matriz[i + 1][j - 1];
                    contador++;
                }
                if (i + 1 <= this.matriz.length - 1 && j + 1 <= this.matriz[0].length - 1) {
                    bottomRight = this.matriz[i + 1][j + 1];
                    contador++;
                }

                total = (int) Math.round((this.matriz[i][j] + left + right + top + bottom + topLeft + topRight + bottomLeft + bottomRight) / contador);
                this.matriz[i][j] = total;
            }
        }

    }

}
