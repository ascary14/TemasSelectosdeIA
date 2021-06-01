/*German Ascary Galindo Guzman
2163010475
Temas Selectos de Inteligencia Artificial

 */
package tarea4;

import java.util.Random;

public class Tarea4 {
    //Metodo de funcion oobjetivo se hace por partes igual que la tarea anterior por comodidad
    public static float FuncionObj(Float x, Float y) {
        float i, j, z;
        z = (float) 0.1;
        float solucion;
        i = (float) Math.sin(x);//sen x
        j = (float) Math.sin(y);//seny
        solucion = (x * i) + (z * (x * x)) + (y * j) + (z * (y * y));//x.sen(x)+0.1x²+ysen(y)+0.1y²

        return (solucion);

    }
    
    //Metodo GenerarMutante que regresa un arreglo y recibe la matriz poblacion 
    public static float[] GenerarMutante(int p1, int p2, int p3, float[][] Poblacion) {
        float[] mutante = new float[2];//declaro el arreglo mutante
        float F;//la variable de 0-1
        F = (float) 0.9;

        mutante[0] = Poblacion[p1][0] + F * (Poblacion[p2][0] - Poblacion[p3][0]);
        mutante[1] = Poblacion[p1][1] + F * (Poblacion[p2][1] - Poblacion[p3][1]);
        return mutante;//regreso del arreflo mutante

    }

    //Metodo Generarhijo que regresa un arregki y recibe un arreglo "mutante" y una matriz "poblacion"
    public static float[] Generarhijo(int p1, float[] mutante, float[][] Poblacion) {
        float[] hijo = new float[2];//declaro el arreglo hijo
        Random random = new Random();
        float cr;// de 0-1
        int i, j;
        float rand;
        rand = random.nextFloat() * 1;
        cr = (float) 0.8;
        //verificamos cual elegir 
        if (rand < 0.5) {
            i = 0;
        } else {
            i = 1;
        }
        //afignamos los hijos
        for (j = 0; j < 2; j++) {
            if (j == i) {
                hijo[i] = mutante[i];
            } else {
                if (rand < cr) {
                    hijo[j] = mutante[j];
                } else {
                    hijo[j] = Poblacion[p1][j];

                }

            }
        }

        return hijo;
    }

    //Principal
    public static void main(String[] args) {
        //Declaracion de variables
        float[][] poblacion = new float[13][2];
        float[] Hijo = new float[2];
        float[] Mutante = new float[2];
        float[] costo = new float[13];
        Random rnd = new Random();
        float costohijo, Mejorcosto;

        int i, n, j, k, numero;
        //en un for asignamos los x,y y la funcion objetivo de cada costo
        for (i = 0; i < 13; i++) {
            poblacion[i][0] = (rnd.nextFloat() * (-10 - 10) + 10);
            poblacion[i][1] = (rnd.nextFloat() * (-10 - 10) + 10);
            costo[i] = FuncionObj(poblacion[i][0], poblacion[i][1]);
        }
        //lo cambie a 300 interacciones 
        for (n = 0; n < 300; n++) {
            //un ciclo for para que recorra cada miembro
            for (i = 0; i < 13; i++) {
                j = i;
                k = i;
                //Asignamos padrinos diferente entre ellos
                while (i == j) {
                    j = (int) (Math.random() * 13);
                }
                while (i == k || j==k) {
                    k = (int) (Math.random() * 13);
                }

                Mutante = GenerarMutante(i, j, k, poblacion);//llamamos al metodo y asignamos
                Hijo = Generarhijo(i, Mutante, poblacion);//llamamos al metodo y asignamos
                costohijo = FuncionObj(Hijo[0], Hijo[1]); //llamamos al metodo y asignamos
                //si el costo es menor actualizamos
                if (costohijo < costo[i]) {
                    poblacion[i][0] = Hijo[0];
                    poblacion[i][1] = Hijo[1];
                    costo[i] = costohijo;
                }
            }
        }
        Mejorcosto = costo[0];//decimos que el mejor costo por ahora es el primero
        numero = 0;//el mejor numero tambien es el primero
        for (i = 0; i < 13; i++) {
            if (costo[i] < Mejorcosto) {//si encontramos uno menos se actualiza
                Mejorcosto = costo[i];
                numero = i;

            }
        }
        //imprimo el mejor costo y la posicion en x,y
        System.out.println("Mejor costo= " + Mejorcosto);
        System.out.println("poblacion x= " + poblacion[numero][0]);
        System.out.println("poblacion y= " + poblacion[numero][1]);
    }

}
