/*German Ascary Galindo Guzman
2163010475
Inteligencia Artificial
Tarea 3

 */
package tarea3ascary;

//Import//
import java.util.Random;


public class Tarea3Ascary {
    //Colores que utilizo para diferenciar mis pruebas
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    //Metodo que regresa el valor de la Funcion Obj
    public static float FuncionObj(Float x) {
        float y, z;
        z = (float) 0.1;
        float solucion;
        y = (float) Math.sin(x);
        solucion = (x * y) + (z * (x * x));

        return (solucion);

    }

    public static void main(String[] args) {
        //Declaracion y asignacion de variables
        float SolActual, u, b, Resta_Sol_Vecina, SolVecina, Mejor_Solucion, Temp, Temp_Inicial, Temp_Final, alfa, iter, Mejor_Costo, Costo_Actual, Costo_Vecina;
        Temp_Inicial = 400;
        Temp_Final = (float) 0.01;
        alfa = (float) 0.9;
        iter = 300;

        int i;
        float j, Epsilon;
        Epsilon = (float) 0.09;

        Random rnd = new Random();
        SolActual = rnd.nextFloat() * (-10 - 10) + 10;//Declaramos el random de -10 a 10
        Costo_Actual = FuncionObj(SolActual);//llamamos a la funcion objetivo
        Mejor_Costo = Costo_Actual;//Por ahorita son el mejor costo
        Mejor_Solucion = SolActual;//Por ahorita son la mejor solucion
        Temp = Temp_Inicial;

        //Ciclo while////
        while (Temp > Temp_Final) {
            //Ciclo for 
            for (i = 0; i < iter; i++) {

                //Geneta la solucion vecina y evalua cuando esta pasa de 10 y -10
                SolVecina = SolActual + (Epsilon * (rnd.nextFloat() * (-1 - 1) + 1));//ya se comprobo que dieran valores entre -1 y 1

                if (SolVecina > 10) {

                    Resta_Sol_Vecina = SolVecina - 10;
                    SolVecina = 10 - Resta_Sol_Vecina;
                }
                if (SolVecina < -10) {
                    Resta_Sol_Vecina = SolVecina + 10;
                    SolVecina = (-10) + Resta_Sol_Vecina;
                }
                //Aqui termina la solucion vecina

                //LLamamos a la funcion objetivo con la vecina
                Costo_Vecina = FuncionObj(SolVecina);
                //comparamos y asignamos valore si son mejores o adecuados
                if (Costo_Vecina < Costo_Actual) {
                    Costo_Actual = Costo_Vecina;
                    SolActual = SolVecina;
                    if (Costo_Vecina < Mejor_Costo) {
                        Mejor_Costo = Costo_Vecina;
                        Mejor_Solucion = SolVecina;
                    }
                } else {
                    u = rnd.nextFloat() * 1;
                    b = (float) Math.exp((Costo_Actual - Costo_Vecina) / (Temp));

                    if (u < b) {
                        Costo_Actual = Costo_Vecina;
                        SolActual = SolVecina;
                    }

                }
                Temp = Temp * alfa;
            }

        }
        System.out.println(ANSI_RED + " Mejor Solucion=  " + SolActual + ANSI_RESET);
        System.out.println(" Mejor costo=  " + Costo_Actual);
        System.out.println("");

    }

}
