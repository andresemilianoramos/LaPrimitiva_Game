import java.util.ArrayList;
import java.util.Arrays;
// Andres Emiliano Ramos Gimenez 2022.
public class Primitiva {
    // ATRIBUTOS
    private String fecha;
    private double recaudacion;
    private static   ArrayList<Integer> combinacionGanadora = new ArrayList<Integer>(6);
    private static  ArrayList<Integer> premiosConseguidos = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));
    // CONSTRUCTOR
    public Primitiva(String fecha, double recaudacion,
                     ArrayList<Integer> combinacionGanadora) {
        this.fecha = fecha;
        this.recaudacion = recaudacion;
        this.combinacionGanadora = combinacionGanadora;
    }

    public static Integer getCombinacionGanadora(int posicion) {
        return combinacionGanadora.get(posicion);
    }

    public void setCombinacionGanadora(ArrayList<Integer> combinacionGanadora) {
        this.combinacionGanadora = combinacionGanadora;
    }

    public static ArrayList<Integer> getPremiosConseguidos() {
        return premiosConseguidos;
    }

    public static void setPremiosConseguidos(ArrayList<Integer> premiosConseguidos) {
        Primitiva.premiosConseguidos = premiosConseguidos;
    }

    //METODO GENERA COMBINACION PRIMITIVA
    public static ArrayList<Integer> generarPrimitiva(){
        ArrayList<Integer> primitiva = new ArrayList<Integer>(6);
        int i = 0;
        int j = 0;

        primitiva.add(i, (int) (Math.random() * (49 - 1)) + 1);
        for (i = 1; i < 7; i++) {
            primitiva.add(i, (int) (Math.random() * (49 - 1)) + 1);
            for (j = 0; j < i; j++) {
                if (primitiva.get(i)== primitiva.get(j)) {
                    primitiva.remove(j);
                    i--;
                }
            }
        }
        return primitiva;
    }

    // METODO COMPRUEBA NUMEROS JUGADOS
    public static void compruebaNumeros(int[] numerosJugados) {
        int[] aux = new int[5];
        int numAciertos=0;
        int complementario=getCombinacionGanadora(6);
        boolean tieneComplementario=false;
        int premio=0;

        aux = Arrays.copyOf(numerosJugados, numerosJugados.length);

        for (int i = 0; i < aux.length-1; i++) {
            if (combinacionGanadora.contains(aux[i])){
                numAciertos++;
            }else if(complementario==aux[i]){
                tieneComplementario=true;
            }
        }
        switch (numAciertos) {
            case 3:
                premio=getPremiosConseguidos().get(0);
                getPremiosConseguidos().set(0, premio+1);
                break;
            case 4:
                premio=getPremiosConseguidos().get(1);
                getPremiosConseguidos().set(1, premio+1);
                break;
            case 5:

                if(tieneComplementario){
                    premio=getPremiosConseguidos().get(3);
                    getPremiosConseguidos().set(3, premio+1);
                }else{
                    premio=getPremiosConseguidos().get(2);
                    getPremiosConseguidos().set(2, premio+1);
                }
                break;
            case 6:
                premio=getPremiosConseguidos().get(4);
                getPremiosConseguidos().set(4, premio+1);
                break;
        }
    }
    //MOSTRAR PREMIOS CONSEGUIDOS
    public static void mostrarPremiosConseguidos(){
        int contador=5; // Andres Emiliano Ramos Gimenez 2022
        for (int i = 0; i < getPremiosConseguidos().size(); i++) {
            System.out.println((contador)+"?? Premio, veces conseguido: "+getPremiosConseguidos().get(i));
            contador--;
        }

    }
    //MOSTRAR NUMEROS JUGADOS
    public static void mostarNumerosJugados(int[] numeros){
        for (int i = 0; i < numeros.length; i++) {
            System.out.print("\t" + numeros[i]);
        }
    }
    //MOSTRAR NUMEROS SORTEO
    public static void mostarNumerosSorteo(ArrayList sorteo){
        System.out.print("[");
        for (int i = 0; i < sorteo.size()-1; i++) {
            System.out.print(sorteo.get(i)+", ");
        }System.out.println("Comp: " + sorteo.get(sorteo.size()-1)+"]");
    }

    public static void main(String[] args) {
        ArrayList<Integer> sorteo1 = new ArrayList<Integer>();
        sorteo1.addAll(generarPrimitiva());

        Primitiva primitiva1 = new Primitiva("01/06/2022",150000,sorteo1);

        System.out.println("\nEste sorteo se ha realizado en la fecha: "+primitiva1.fecha+ "; con una recaudacion de: "+primitiva1.recaudacion);
        System.out.println("===================================================================================");
        int [] numerosJugados={5,10,15,20,25,30,35,40,45};
        System.out.print("N??meros obetindos en el sorteo: "); mostarNumerosSorteo(sorteo1);
        System.out.println("N??meros jugados en el sorteo: " + Arrays.toString(numerosJugados));

        compruebaNumeros(numerosJugados);
        mostrarPremiosConseguidos();

        ArrayList<Integer> sorteo2 = new ArrayList<Integer>();
        sorteo2.addAll(generarPrimitiva());
        int [] numerosJugados2={5,10,15,20,25,30,35,40,45};
        System.out.print("\nN??meros obetindos en el sorteo: "); mostarNumerosSorteo(sorteo2);
        System.out.println("N??meros jugados en el sorteo: " + Arrays.toString(numerosJugados));

        compruebaNumeros(numerosJugados);
        mostrarPremiosConseguidos();

    }

}