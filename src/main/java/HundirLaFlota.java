/**
 * Entregable UD 7 programacion para 1º de DAW curso 2021 / 2022
 * 
 * Hundir la Flota
 * 
 * @author Carlos Casas Rodriguez 
 * @version 1.0
 */

import java.util.Arrays;
import java.util.Scanner;
public class HundirLaFlota {
    /**
     * Esta funcion crea un tablero de a x b y lo rellena con los simbolos -
     * 
     * @param x numero de columnas del tablero
     * @param y numero de filas del tablero
     * 
     * @return devuelve la matriz de char de a x b
     */
    public static char[][] ctablero (int y, int x){
        char [][] tablero = new char [x][y];
        for (int i = 0; i < x;i++){
            Arrays.fill(tablero[i],'-');
        }
        return tablero;
    }
    /**
     *  Crea una matriz para cada barco de tam*2, se guarda el valor [[x1][y1],[x2][y2]...,[xn][yn]]
     * 
     * @param tam numero de posiciones que ocupa el barco
     * 
     * @return devuelve la matriz del barco temporal que se usa para buscar espacio en el tablero
     */
    public static int [][] cbarco (int tam){
        int [][] barconuevo = new int [tam][2];
        for (int i = 0 ; i< barconuevo.length; i++){
            Arrays.fill(barconuevo[i], -2);
        }
        return barconuevo;
    }
    /**
     * Genera un numero aleatorio entre 0 y max
     * 
     * @param max numero maximo 
     * 
     * @return devuelve el valor
     */
    public static int aleatorio (int max){
        return ((int)(Math.random()*(max)));
    }
    /**
     * Mira la posicion del "tablero" si esta libre o no y devuelve un boleano
     * 
     * @param tablero matriz con los valores de todo el juego
     * @param y numero de columna
     * @param x numero de fila
     * 
     * @return devuelve true si la poscion esta libre
     */
    public static boolean plibre (char[][]tablero, int y, int x){
        if (tablero[y][x] == '-'){
            return true;
        }
        return false;
    }
    /**
     * Funcion que devuelve la siguiente posicion si esta es posible
     * 
     * @param tablero matriz ccon todos los datos del juego
     * @param min posicion minima que acupara el barco que se esta posicionando
     * @param max posicion maxima que acupara el barco que se esta posicionando
     * @param filcol si la horientacion que se esta poniendo es horizontal == 0, vertical  ==1
     * @param direccion 4 valores posibles, 0 horizontal izquierda, 1 vertical arriba, 2 horizontal derecha, 3 vertical abajo
     * 
     * @return devuelve un array con la posicion [x][y]
     */
    public static int[] sigposicion (char[][] tablero, int min, int max, int filcol, int direccion ){
        int []posicion= new int[2];
        Arrays.fill(posicion, -2);
        switch (direccion) 
        {
            case 0:  if (min > 0){
                        if (plibre (tablero,filcol, min-1)){
                           posicion[0]= filcol;
                           posicion[1]=min -1;
                        }
                    }
                     break;
            case 1:  if (min > 0){
                        if(plibre(tablero, min-1, filcol)){
                            posicion[0]= min -1;
                            posicion[1]=filcol;
                        }
                    }
                     break;
            case 2:  if (max < tablero[0].length -1){
                        if(plibre (tablero,filcol,max +1)){
                            posicion[0]= filcol;
                            posicion[1]=max +1;
                        }
                    }
                     break;
            case 3:  if (max < tablero.length-1 ){
                        if(plibre (tablero, max +1, filcol)){
                            posicion[0]= max +1;
                            posicion[1]=filcol;
                        }
                     }
                     break;
            //default: return posicion;
        }
        return posicion;
    }
    /**
     * Recibe la matriz de un barco y guarda en la matriz del tablero los valores
     * 
     * @param tablero Matriz con los datos del juego, y va rellendo la lista de cada tipo de barco
     * @param barco Matriz con las coordenadas del barco
     * @param letra Letra del barco que se guarda en el tablero
     * @param listabarco lista de enteros donde el primer valor es el numero de barcos de ese tipo, el segundo la grandaria de ese barco
     * y despues continua poniendo los valores de sus posiciones ejem: 2 barcos de tam 3, situados en la fila o y 1 possiciones de la 0 a la 2 -> [2][3][0][0][0][1][0][2][1][0][1][1][1][2]
     * @param nbarco el numero del barco que se esta relleando en este momento de la lista anterior
     */    
    public static void pintatablero (char[][] tablero, int[][] barco, char letra, int[] listabarco, int nbarco){
        for(int x = 0; x< barco.length;x++){
            tablero[barco[x][0]][barco[x][1]] = letra;
            listabarco[2+ (nbarco*(barco.length*2)  )+(x*2)] = barco[x][0];
            listabarco[2+ (nbarco*(barco.length*2)  )+(x*2)+1] = barco[x][1];
        }
    }
    /**
     * Imprimir por pantalla el tablero de juego en su estado actual
     * 
     * @param tablero Matriz con los datos del juego
     * @param tipo valor para imprimir matriz visible por el jugador != 0 o la que usa el ordenador == 0
     */
    public static void imprimetablero (char[][] tablero, int tipo){
        //cabecera
        System.out.print("   ");
        for (int i = 0; i<tablero[0].length;i++){
            System.out.print(" " +  (i) + " " );
        }
        System.out.println();
        //siguientes lineas
        String espacios = new String(""); //para que cuando haya mas de 10 o mas de 100 columnas la cabecera siga saliendo bien
        String espacio = new String(" ");
        for (int i = 0; i<tablero.length;i++){
            for (int j = 0; j<tablero[0].length +1;j++){
                espacios = (j-1) + "";
                espacios = espacio.repeat(espacios.length()) ;
                if (j == 0){
                    System.out.print(' ');
                    System.out.print((char)('A' +i));
                    System.out.print(' ');
                }
                else{
                    if (tipo == 0){ //impresion ordenador lo enseña tal cual
                        System.out.print(espacios + tablero[i][j-1] + " ");
                    }
                    else{
                        if(tablero[i][j-1] == '-' || tablero[i][j-1] == 'A' || tablero[i][j-1] == 'X'){
                            System.out.print(espacios + tablero[i][j-1] + " ");
                        }
                        else{
                            System.out.print(espacios + "-" + " ");
                        }
                    }
                    
                }
            }
            System.out.println();
        }
    }
    /**
     * Esta funcion es la que llamando a los diferentes random decide la direccion a la que ira la siguiente posicion del barco
     * 
     * @param tablero Matriz con el juego
     * @param barco Matriz con el barco que se esta formando
     * @param cruceta Posicion a la que se va buscar 
     * @param posicion Numero de la posicion del barco
     * 
     */
    public static void posicion (char [][] tablero, int[][] barco, int cruceta, int posicion){
        boolean encontrado = false;
        int aleatorio = aleatorio(2), probar1, probar2, max,min;
        int [] sigposicion;
        if (aleatorio == 0){ //izquierda primero
            probar1 = 0; probar2 = 2;
        }
        else { //derecha primero
            probar1 = 2; probar2 = 0;
        }
        if (cruceta == 0 || cruceta == 2){//horizontal
            max= barco[0][1];
            min= barco[0][1];
            for (int j =1; j< barco.length;j++){//buscar el maximo y el minimo
                if (barco[j][1]>max ){max = barco[j][1];}
                else if (barco[j][1]<min && barco[j][1]>= 0 ){min = barco[j][1];}
            }
            sigposicion = sigposicion (tablero, min, max, barco[0][0], probar1);
            if (sigposicion[0] != -2 && !encontrado){//valido
                barco[posicion][0] =  sigposicion[0];
                barco[posicion][1] =  sigposicion[1];
                encontrado = true;
            }
            if (!encontrado){ 
                sigposicion = sigposicion (tablero, min, max, barco[0][0], probar2);
                if (sigposicion[0] != -2 && !encontrado){//valido
                    barco[posicion][0] =  sigposicion[0];
                    barco[posicion][1] =  sigposicion[1];
                    encontrado = true;
                }
            }
        }
        else{//cruceta == 1 y 3
            max= barco[0][0];
            min= barco[0][0];
            for (int j =1; j< barco.length;j++){//buscar el maximo y el minimo
                if (barco[j][0]>max ){max = barco[j][0];}
                else if (barco[j][0]<min && barco[j][0]>= 0 ){min = barco[j][0];}
            }
            sigposicion = sigposicion (tablero, min, max, barco[0][1], probar1 +1);
            if (sigposicion[0] != -2 && !encontrado){
                barco[posicion][0] =  sigposicion[0];
                barco[posicion][1] =  sigposicion[1];
                encontrado = true;
            }
            if (!encontrado){
                sigposicion = sigposicion (tablero, min, max, barco[0][1], probar2 +1);
                if (sigposicion[0] != -2 && !encontrado){//valido
                    barco[posicion][0] =  sigposicion[0];
                    barco[posicion][1] =  sigposicion[1];
                    encontrado = true;
                }
            }
        }
    }
    /**
     * Esta funcion es la encargada de colocar los barcos dentro del tablero, primero tira un random para la primera posicion (si esta ocupado el valor se llama asi misma 
     * hasta el momento que localice un punto libre) despues va llamando a las diferentes funciones que tiraran un random para ver si el barco va en posicion horizontal o vertical
     * y despues cada de la horientacion si va hacia un lado (izquieda arriba) o hacia el otro (derecha abajo), si el barco se puede poner se coloca en el tablero si no se 
     * vuelve a empezar la funcion desde cero otra vez, asi hasta 250 intentos, si llegados a este punto no consigue colocar el barco se descarta esa opcion.
     * ejemplo en un tablero de 4x4 no cabe un portaaviones (esto se podria limitar directamente antes de llamar a esta funcion) intentara colocar el barco 250 veces si no lo consigue
     * pasara al siguiente barco a a colocar olvidandose de este.
     * @param tablero
     * @param barcorellenar
     * @param letra
     * @return 
     */
    public static int pbarco (char[][] tablero, int[][] barcorellenar, char letra, int intento, int [] listabarco, int nbarco){
        if (intento>250){
            return 0;
        }
        intento ++;
        barcorellenar[0][0] = aleatorio(tablero.length);
        barcorellenar[0][1] = aleatorio(tablero[0].length);
        if (! plibre(tablero, barcorellenar[0][0], barcorellenar[0][1])){//si no esta libre volvemos a empezar la posicion del barco
            pbarco(tablero, barcorellenar, letra, intento, listabarco, nbarco);
            return 0;
        }
        if (barcorellenar.length > 1){//barcos mayores que una posicion
            int [] cruceta = new int[2];
            int aleatorio;
            //cruceta = crucetarandom ();     //esta funcion devuelve un array con 4 posibles direcciones en modo aleatorio
            aleatorio = aleatorio(2);
            if (aleatorio == 0){ //izquierda primero
                cruceta[0] = 0; cruceta[1] = 1;
            }
            else { //derecha primero
                cruceta[0] = 1; cruceta[1] = 0;
            }
            for (int i = 0; i< cruceta.length;i++){//horizontal y vertical de todo el barco 
                for (int k=1; k<barcorellenar.length;k++){ // cada una de las posiciones del barco
                    posicion (tablero, barcorellenar, cruceta[i], k); // prueba las 2 posibles movimientos, con el punto inicial la horientacion y el resto valores que tenga
                } 
                if (barcorellenar[barcorellenar.length -1][0] >= 0){ //valores validos, se pone en el tablero
                    pintatablero (tablero, barcorellenar, letra, listabarco, nbarco);
                    return barcorellenar.length;
                }
                else { // valores no validos, se inician todos a -2 menos el primero, y se sigue con otra direccion
                    for(int x = 1; x< barcorellenar.length;x++){
                        barcorellenar[x][0]= -2;
                        barcorellenar[x][1]= -2;
                    }
                }
            } //for i horizontal vertical
            //si llegamos a este punto no encontro posiciones correctas para ese barco tomando el primer valor de inicio se llama de nuevo a la funcion
            pbarco(tablero, barcorellenar, letra,intento, listabarco, nbarco);
            return 0;
        }// barco mayor que 1
        pintatablero (tablero, barcorellenar, letra, listabarco, nbarco);
        return barcorellenar.length;
    }        
    /**
     * Muestra el menu inicial del juego y pide un valor
     * 
     * @return devuelve el numero que elija el usuario
     */
    public static int mostrarmenu(){
        System.out.println("Bienvenido al hundir la flota:");
        System.out.println("Autor Carlos Casas:");
        System.out.println("");
        System.out.println("Selecciona el nivel de dificultad:");
        System.out.println("1.- Facil         (5 lanchas 3 barcos, 1 acorazado 1 portaviones, 10x10 50 intentos");
        System.out.println("2.- Medio         (2 lanchas 1 barcos, 1 acorazado 1 portaviones, 10x10 30 intentos");
        System.out.println("3.- Dificil       (1 lanchas 1 barcos, 10x10 10 intentos");
        System.out.println("4.- Personalizado (numero de barcos, tamaño del tablero e intentos");
        Scanner entrada = new Scanner (System.in);
        int x = -1;
         while (x < 0  || x > 4){
                try{
                    x = entrada.nextInt();
                    if (x < 0  || x > 4){
                        System.out.println("Introduciste un dato erroneo.");    
                    }
                }
                catch(Exception e){
                    System.out.println("Introduciste un dato erroneo.");
                    entrada.nextLine();
                }
            }
        return x;
    }
    /**
     * Esta funcion inicializa la estructura de dato de la lista de barcos, primer valor numero de barcos, segundo valor tamaño, y el resto sus posiciones
     * 
     * @param cantbarcos cantidad de barcos
     * @param tambarco tamaño de estos barcos
     * 
     * @return  devuelve el vector formado con los dos datos iniciales y todo lo demas a -2
     */
    public static int [] crealistabarco (int cantbarcos, int tambarco){
        int [] listabarco = new int [ ((tambarco*2)*cantbarcos  ) +2];
        Arrays.fill(listabarco, -2);
        listabarco[0] = cantbarcos;
        listabarco[1] = tambarco;
        return listabarco;
    }
    /**
     * Una ver terminado el proceso se revisa cada lista para asegurarse que todos los barcos iniciales se pudieron poner en el tablero
     * 
     * @param barco lista de cada uno de los barcos
     */
    public static void limpialistabarcos (int [] barco){
        int cont = 0;
        for (int i = 2; i < barco[0] * barco[1] * 2 + 2 ; i=i+(barco[1]*2)  ){
            if (barco[i] < 0){
                barco[0] = cont;
            }
            else{
                cont++;    
            }
        }
        barco[0] = cont;
    }
    /**
     * Recibe el tablero del juego y el nivel elegido y genera la cantidad de barcos con su tamaño poniendolos en el tablero
     * 
     * @param tablero Matriz con el tablero del juego
     * @param nivel Nivel elegido
     * @return Devuelve el numero de posiciones ocupadas en el tablero que hay que descubrir
     */
    public static int crearbarcos(char[][] tablero, int nivel){
        int [][]barco;
        int l =0, b=0, z=0, p =0, tlancha= 1, tbarco=3, tacorazado =4, tportaaviones =5, max= 0;
        if (nivel == 1){        l=5; b=3; z=1; p=1;}
        else if (nivel == 2){   l=2; b=1; z=1; p=1;}
        else if (nivel == 3){   l=1; b=1; z=0; p=0;}
        else if (nivel ==4){
            boolean correcto = false;
            while (!correcto){
                Scanner entrada = new Scanner (System.in);
                
                l=-1;
                max = ( (tablero.length * tablero[0].length)/tlancha  ); // es un numero teorico, que en muchisimos casos no es real pero si aproximado
                while (l == -1){
                    System.out.println("Numero de lanchas apartir de 0 hasta " + max);
                    l = valorcorrecto(0, max);
                }
                b=-1;
                max = ( (tablero.length * tablero[0].length)/tbarco  ); // es un numero teorico, que en muchisimos casos no es real pero si aproximado
                while (b == -1){
                    System.out.println("Numero de barcos apartir de 0 hasta " + max);
                    b = valorcorrecto(0, max);
                }
                z=-1;
                max = ( (tablero.length * tablero[0].length)/tacorazado  ); // es un numero teorico, que en muchisimos casos no es real pero si aproximado
                while (z == -1){
                    System.out.println("Numero de acorazados apartir de 0 hasta " + max);
                    z = valorcorrecto(0, max);
                }
                p=-1;
                max = ( (tablero.length * tablero[0].length)/tportaaviones  ); // es un numero teorico, que en muchisimos casos no es real pero si aproximado
                while (p == -1){
                    System.out.println("Numero de portaaviones apartir de 0 hasta " + max);
                    p = valorcorrecto(0, max);
                }
                if (  (l + (b*tbarco) + (z*tacorazado) + (p*tportaaviones)) > (tablero[0].length * tablero.length)  ){
                    System.out.println("Imposible no caben, intentalo de nuevo");
                }
                else{
                    correcto = true;
                }
            }
        }
        int [] listalancha = crealistabarco(l, tlancha);
        int [] listabarco = crealistabarco(b,tbarco);
        int [] listaacorazado = crealistabarco(z,tacorazado);
        int [] listaportaaviones = crealistabarco(p,tportaaviones);
        int numeroposiciones=0;
        for (int i = 0; i<p ;i++){ //se meten primeros los grandes y despues los pequeños para que hayan menos "colisiones"
            barco = cbarco(tportaaviones);
            pbarco(tablero, barco, 'P',0, listaportaaviones, i);
        }
        for (int i = 0; i<z;i++){
            barco = cbarco(tacorazado);
            pbarco(tablero, barco, 'Z',0, listaacorazado, i);
        }
        for (int i = 0; i<b;i++){
            barco = cbarco(tbarco);
            pbarco(tablero, barco, 'B',0, listabarco, i);
        }
        for (int i = 0; i<l;i++){
            barco = cbarco(tlancha);
            pbarco(tablero, barco, 'L',0, listalancha, i);
        }
        //limpiar listas 
        limpialistabarcos(listaportaaviones);limpialistabarcos(listaacorazado);limpialistabarcos(listabarco);limpialistabarcos(listalancha);
        numeroposiciones = (listaportaaviones[0] * listaportaaviones[1] + listaacorazado[0] * listaacorazado[1] + listabarco[0] * listabarco[1] + listalancha[0] * listalancha[1]);
        // si hiciera falta controlar que barcos se rompen en cada disparo se podria devolver los vectores de cada tipo de barco e ir mirandolo ahi
        return (numeroposiciones); 
    }
    /**
     * Funcion que recibe tablero intentos y posiciones a encontrar, si posiciones llega a cero gana la partida y devuelve true, si intentos llega a cero pierde y devuelve false
     * 
     * @param tablero Matriz con el tablero de juego
     * @param intentos Numero de intentos que tiene para encontrar todos las posiciones de los barcos
     * @param posiciones Numero de posiciones del tablero que ocupan los barcos
     * 
     * @return True si gana la partida False si pierde
     */
    public static boolean jugar (char [][] tablero, int intentos, int posiciones){
        Scanner entrada = new Scanner (System.in);
        int x, intentostotales = intentos;
        char ejey;
        for (int i = 0; i< intentostotales;i++){
            x = -1;
            ejey = 'a';
            System.out.println("Numero de misiles dispobles: " + intentos);
            System.out.println("Numero posiciones por descubrir: " + posiciones);
            while (x < 0  || x > tablero.length-1){
                System.out.println("Coordenada X, valor entre 0 y " +  (tablero.length -1) );
                try{
                    x = entrada.nextInt();
                    if (x < 0  || x > tablero.length-1){
                        System.out.println("Introduciste un dato erroneo.");
                    }   
                }
                catch(Exception e){
                    System.out.println("Introduciste un dato erroneo.");
                    entrada.nextLine();
                }
            }
            while ((int)ejey < (int)'A'  || (int)ejey >tablero.length-1 + (int)'A'){
                System.out.println("Coordenada Y, valor entre A y " +  (char)(tablero[0].length -1) );
                ejey = entrada.next() .charAt(0);
                if ((int)ejey < (int)'A'  || (int)ejey >tablero.length-1 + (int)'A'){
                    System.out.println("Introduciste un dato erroneo.");
                }
            }
            int y = ejey - 'A';
            if (tablero[y][x]== 'L' || tablero[y][x]== 'B' || tablero[y][x]== 'Z' || tablero[y][x]== 'P' || tablero[y][x]== 'X'  ){
                System.out.println("Tocado");
                tablero[y][x] = 'X';
                posiciones --;
            }
            else{
                System.out.println("Agua");
                tablero[y][x] = 'A';
            }
            intentos --;
            imprimetablero(tablero,1);
            if (posiciones == 0){
                System.out.println("Felicidaes has ganado");
                imprimetablero(tablero,0);
                return true;
            }
        }
        System.out.println("Te quedaste sin misiles lo siento");
        imprimetablero(tablero,0);
        return false;
    }
    /**
     * Hace una copia inicial del tablero para mostrarlo al finalizar el juego
     * 
     * @param tablerooriginal original con los barcos situados
     * @param tablerocopia  copia despues de la funcion
     */
    public static void copiatablero (char[][] tablerooriginal , char[][] tablerocopia){    
        for (int i =0; i<tablerooriginal.length;i++){
            for (int j = 0; j< tablerooriginal[0].length ;j++){
                tablerocopia[i][j] = tablerooriginal[i][j];
            }
        }
    }
    /**
     * Funcion que devuelve un valor entero tomado por teclado si introduce un valor correcto
     * 
     * @param min entre min
     * @param max y max
     * 
     * @return entero o min -1 si es erroneo
     */
    public static int valorcorrecto (int min, int max){
        Scanner entrada = new Scanner (System.in);
        int x = min -1;
        try{
            x = entrada.nextInt();
        }
        catch(Exception e){
            //pass
        }
        return x;
    }
    /**
     * Funcion principal, inicializa las variables necesarias para jugar y llama al resto de funciones
     * 
     * @param args 
     */
    public static void main (String[]args) {
        int eleccion, posiciones;
        char [][] tablero;
        char [][] tablerocopia;
        int tablerohorizontal = 10;
        int tablerovertical = 10;
        int intentos = 50;
        eleccion = mostrarmenu();
        if (eleccion == 1){
            tablero = ctablero (tablerohorizontal,tablerovertical);
            tablerocopia = ctablero (tablerohorizontal,tablerovertical);
            posiciones = crearbarcos(tablero, 1);
            copiatablero(tablero, tablerocopia);
            imprimetablero(tablero,1);
            jugar (tablero, intentos, posiciones);
            System.out.println("Tablero con todos los barcos");
            imprimetablero(tablerocopia,0);

        }
        else if (eleccion == 2){
            tablero = ctablero (tablerohorizontal,tablerovertical);
            tablerocopia = ctablero (tablerohorizontal,tablerovertical);
            intentos = 30;
            posiciones = crearbarcos(tablero, 2);
            imprimetablero(tablero,1);
            jugar (tablero, intentos, posiciones);
            System.out.println("Tablero con todos los barcos");
            imprimetablero(tablerocopia,0);

        }
        else if (eleccion == 3){
            tablero = ctablero (tablerohorizontal,tablerovertical);
            tablerocopia = ctablero (tablerohorizontal,tablerovertical);
            intentos = 10;
            posiciones = crearbarcos(tablero, 3);
            copiatablero(tablero, tablerocopia);
            imprimetablero(tablero,1);
            jugar (tablero, intentos, posiciones);
            System.out.println("Tablero con todos los barcos");
            imprimetablero(tablerocopia,0);

        }
        else if (eleccion == 4){
            Scanner entrada = new Scanner (System.in);
            intentos = 0;
            while (intentos == 0){
                System.out.println("Numero de intentos entre 1 y 10000");
                intentos = valorcorrecto(1, 10000);
            }
            tablerohorizontal = 0;
            while (tablerohorizontal == 0){
                System.out.println("Tamaño tablero, numero de columnas, eje X:");
                tablerohorizontal = valorcorrecto(1, 1000);
            }
            tablerovertical=0;
            while (tablerovertical == 0){
                System.out.println("Tamaño tablero, numero de lineas, eje Y:");
                tablerovertical = valorcorrecto(1, 1000);
            }
            tablero = ctablero (tablerohorizontal,tablerovertical);
            tablerocopia = ctablero (tablerohorizontal,tablerovertical);
            posiciones = crearbarcos(tablero, 4);
            copiatablero(tablero, tablerocopia);
            imprimetablero(tablero,1);
            jugar (tablero, intentos, posiciones);
            System.out.println("Tablero con todos los barcos");
            imprimetablero(tablerocopia,0);

        }
        else{
            System.out.println("Introduce un valor valido");
        }
    }
}