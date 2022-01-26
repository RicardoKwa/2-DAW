package Curso1.Entregable2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author WEB1_13
 *
 * HECHO POR RICARDO KWAPISZ PAREJO Y VICTOR CORTINAS RUIZ
 *
 */
public class LigaPadel {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String entrada, categoria;
        int puntosLocal, puntosVisitante, contadorPartidos;
        String[] partido;
        HashMap<String, Integer> puntuacion = new HashMap<>();

        do {
            categoria = sc.nextLine();

            if (!(categoria.equals("FIN"))) {

                puntuacion.clear();
                contadorPartidos = 0;

                do {
                    entrada = sc.nextLine();
                    partido = entrada.split(" ");//Introducimos la entrada, en el String[] partido

                    if (!(partido[0].equals("FIN"))) {

                        contadorPartidos++;

                        //Se reparten los puntos del partido
                        if (Integer.parseInt(partido[1]) > Integer.parseInt(partido[3])) {
                            puntosLocal = 2;
                            puntosVisitante = 1;
                        } else {
                            puntosLocal = 1;
                            puntosVisitante = 2;
                        }

                        rellenarMap(puntuacion, partido[0], puntosLocal);
                        rellenarMap(puntuacion, partido[2], puntosVisitante);
                    }

                } while (!(entrada.equals("FIN")));

                //PartidosNoJugados = PartidosTotales - PartidosJugados
                contadorPartidos = (puntuacion.size() * (puntuacion.size() - 1)) - contadorPartidos;

                System.out.println(resultadoFinal(puntuacion) + " " + contadorPartidos);

            }

        } while (!(categoria.equals("FIN")));
    }

    /*
    El método rellenarMap rellena el HashMap con los pares Equipo-puntos(key-value)
    En el caso de que el Equipo ya exista en el map, no se duplica la clave sino que se actualiza el valor puntos
     */
    public static HashMap<String, Integer> rellenarMap(HashMap<String, Integer> p, String equipo, int puntos) {

        if (p.containsKey(equipo)) {
            p.put(equipo, p.get(equipo) + puntos);
        } else {
            p.put(equipo, puntos);
        }
        return p;
    }

    /*
    El método resultadoFinal se recorre el HashMap para devolver el ganador de la categoría
    El ganador será "EMPATE", en el caso de que más de un equipo tenga la puntuacion máxima
    */
    
    public static String resultadoFinal(HashMap<String, Integer> p) {
        String ganador = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : p.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (max < value) {
                max = value;
                ganador = key;
            } else if (max == value) {
                ganador = "EMPATE";
            }
        }
        return ganador;
    }

}
