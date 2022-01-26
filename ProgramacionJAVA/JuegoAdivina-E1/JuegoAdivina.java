/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Curso1.PracticaEntregable;

import java.util.Scanner;

/**
 *
 * @author web1_13
 */
public class JuegoAdivina {

    static int min, max, contador;
//--------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String continuar, adivina;

        do {
            System.out.println("Introduce un 1 si quieres que la máquina adivine el número");
            System.out.println("Introduce un 2 si quieres ser tú quien adivine el número");
            do {
                adivina = sc.nextLine();
                if (!(adivina.equals("1") || adivina.equals("2"))) {
                    System.out.println("Lo siento, para jugar tienes que introducir el número 1 o el número 2");
                }
            } while (!(adivina.equals("1") || adivina.equals("2")));
            System.out.println();
            min = 1;
            max = 100;
            contador = 0;
            if (adivina.equals("1")) {
                adivinaMaquina(sc);
            } else if (adivina.equals("2")) {
                adivinaUsuario(sc);
            }
            System.out.print("¿Quieres continuar jugando?(Si/No) : ");
            do {
                continuar = sc.nextLine();
                if (!(continuar.equalsIgnoreCase("si") || continuar.equalsIgnoreCase("no"))) {
                    System.out.println("Lo siento, no he entendido tu respuesta , responde Si o No ");
                } else {
                    System.out.println("-----------------------------------------------------------");
                    System.out.println();
                }
            } while (!(continuar.equalsIgnoreCase("si") || continuar.equalsIgnoreCase("no")));

        } while (continuar.equalsIgnoreCase("si"));
        System.out.println("¡HASTA LA PRÓXIMA! :) ");
    }
//--------------------------------------------------------------------------------------------------------

    public static void adivinaMaquina(Scanner sc) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("HAS INTRODUCIDO '1' LA MAQUINA ADIVINARÁ EL NUMERO EN EL QUE HAS PENSADO ...");
        System.out.println("Piensa un número entre el 1 y el 100, y escribe mayor o menor según vayan apareciendo los números que da la máquina");
        int aleatorio;
        String respuesta = "";
        do {
            aleatorio = generarAleatorio(max, min);
            contador++;
            System.out.println("Intento " + contador + " : " + aleatorio);
            do {
                if (!(respuesta.equalsIgnoreCase("correcto"))) {
                    //En el caso de que min == max,la máquina ya habrá adivinado el número
                    if (max == min) {
                        System.out.println("El número es el " + max + " ¿Correcto?");
                    }
                    do {
                        System.out.print("Responde Mayor/Menor/Correcto : ");
                        respuesta = sc.nextLine();/*SI EL NUMERO QUE HAS PENSADO ES MAYOR/MENOR QUE EL DE LA MAQUINA 
                ENTONCES RESPONDES MAYOR/MENOR*/
                        if (!(respuesta.equalsIgnoreCase("correcto")) && max == min) {
                            System.out.println("No es posible, el número tiene que ser el " + max);
                        }
                    } while (!(respuesta.equalsIgnoreCase("correcto")) && max == min);
                }
                if (!(respuesta.equalsIgnoreCase("menor") || respuesta.equalsIgnoreCase("mayor") || respuesta.equalsIgnoreCase("correcto"))) {
                    System.out.println("Lo siento, no he entendido tu respuesta");
                }
            } while (!(respuesta.equalsIgnoreCase("menor") || respuesta.equalsIgnoreCase("mayor") || respuesta.equalsIgnoreCase("correcto")));
            if (!(respuesta.equalsIgnoreCase("Correcto"))) {
                if (respuesta.equalsIgnoreCase("Mayor")) {
                    min = actualizarMin(aleatorio, min);
                } else if (respuesta.equalsIgnoreCase("Menor")) {
                    max = actualizarMax(aleatorio, max);
                }
                if (aleatorio == max && aleatorio != 100 || aleatorio == min && aleatorio != 1) {
                    reinicioMaquina(aleatorio);
                }
            }
        } while (!(respuesta.equalsIgnoreCase("Correcto")));
        System.out.println("¡LA MAQUINA HA ACERTADO EL NÚMERO!, HA NECESITADO " + contador + " INTENTOS");
    }
//--------------------------------------------------------------------------------------------------------

    public static void adivinaUsuario(Scanner sc) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("HAS INTRODUCIDO '2' TE TOCA ADIVINAR EL NÚMERO ...");
        System.out.println("Tienes que adivinar un número aleatorio entre 1 y 100 , la máquina te irá dando pistas para que lo puedas adivinar.");
        int numUsuario, aleatorio;
        aleatorio = generarAleatorio(max, min);
        do {
            System.out.print("Introduce un número : ");
            numUsuario = Integer.parseInt(sc.nextLine());
            contador++;
            if (numUsuario < aleatorio) {
                System.out.println("El número que has elegido es menor");
                min = actualizarMin(numUsuario, min);
            } else if (numUsuario > aleatorio) {
                System.out.println("El número que has elegido es mayor");
                max = actualizarMax(numUsuario, max);
            }
            if (numUsuario != aleatorio) {
                if (min != max) {
                    System.out.println("'PISTA' : El número que debes acertar se ecuentra entre " + max + " y " + min);
                } else {
                    System.out.println("Casi lo tienes ... , solo queda un número posible");
                }
            }
        } while (numUsuario != aleatorio);
        System.out.println("¡ENHORABUENA , HAS ACERTADO!, HAS NECESITADO " + contador + " INTENTOS");
    }
//--------------------------------------------------------------------------------------------------------

    public static int generarAleatorio(int max, int min) {
        //Genera un numero aleatorio entre max y min ambos incluidos
        int aleatorio = (int) (Math.random() * (max - min + 1) + min);
        return aleatorio;
    }
//--------------------------------------------------------------------------------------------------------

    public static int actualizarMax(int aleatorio, int max) {

        if (aleatorio <= max && aleatorio > 1 && aleatorio != min) {
            max = aleatorio - 1;
        }
        //Si la maquina te da el numero 1 y respondes menor, te dirá que eso no es posible y descontará el intento.
        if (aleatorio == 1) {
            System.out.println("No es posible...  el número no puede ser menor que " + 1);
            contador--;
        }
        return max;
    }
//--------------------------------------------------------------------------------------------------------

    public static int actualizarMin(int aleatorio, int min) {

        if (aleatorio >= min && aleatorio < 100 && aleatorio != max) {
            min = aleatorio + 1;
        }
        //Si la maquina te da el numero 100 y respondes mayor, te dirá que eso no es posible y descontará el intento.
        if (aleatorio == 100) {
            System.out.println("No es posible... el número no puede ser mayor que " + 100);
            contador--;
        }
        return min;
    }
//--------------------------------------------------------------------------------------------------------

    public static void reinicioMaquina(int aleatorio) {
        /*
        Si por ejemplo, respondes que el número es mayor que 90 pero menor que 91 , la maquina dirá que te has equivocado
        ya que eso no es posible, y reiniciara el juego (contador = 0 , min = 1 , max  = 100).
         */
        if (aleatorio == max && aleatorio != 100) {
            System.out.println("No es posible... debes haberte equivocado , según tus respuestas anteriores el número no puede ser mayor que " + max);
        }
        if (aleatorio == min && aleatorio != 1) {
            System.out.println("No es posible... debes haberte equivocado , según tus respuestas anteriores el número no puede ser menor que " + min);
        }
        System.out.println("Volvamos a empezar...");
        max = 100;
        min = 1;
        contador = 0;
    }
}
//--------------------------------------------------------------------------------------------------------
