import java.util.ArrayList;
import java.util.BitSet;


/**
 * Clase donde se define la forma de una instancia
 * Cada instancia tiene 35 bits almacenado de litte endian
 *                                                        
 * Donde:                                                 
 * 00000|000|0000000|00000000|00000000
 * 5bits 3bi  7bits     8bits     8bits
 * neuro  capa  epocas   lr       momentum
 * 
 */
public class PhiInstance {
    //
    // Longitud de los bits
    //
    public static final int SIZE_INSTANCE = 31; //ESTA ES LA SUMA DE TODOS LOS DE ABAJO
    public static final int SIZE_NEURONA = 4;
    public static final int SIZE_CAPAS = 2;
    public static final int SIZE_EPOCAS = 7;
    public static final int SIZE_LR = 9;
    public static final int SIZE_MOMENTUM = 9;
    //
    // Valores maximos binarios posibles
    //
    public static final int MAX_NEURONA = 15;//Valor maximo en enteros
    public static final int MAX_CAPAS = 3;
    public static final int MAX_EPOCAS = 100;
    public static final int MAX_LR = 350;
    public static final int MAX_MOMENTUM = 300;

    // Valor de la instancia
    public BitSet valor = new BitSet(31);

    //Valor Fitness
    public Float valorFitness = 0f;

    //
    private final BitSetTo convertidor = new BitSetTo();

    // Maneja litte endian
    /**
     * Recibe un bitset 
     * @param valor
     */
    public PhiInstance(BitSet valor) {
        this.valor = valor;
    }

    /**
     * Constructor que recibe un tamaño
     * de instancia
     * @param valor
     */
    public PhiInstance(int size){
        this.valor = new BitSet(size);
    }

    /**
     * Constructor para establecer una nueva instancia con 
     * neuronas, capas, epocas, learningRatem, momentum
     * @param neurona
     * @param capas
     * @param epocas
     * @param learningR
     * @param momentum
     */
    public PhiInstance(BitSet neurona, BitSet capas, BitSet epocas, BitSet learningR, BitSet momentum) {
        BitSet valor = new BitSet(31);
        int j = 0;
        for (int i = 0; i < 4; i++) {//Se agrega la parte de neuronas
            valor.set(i, neurona.get(i));
            j++;
        }
        
        for (int i = 0; i < 2; i++) {//Se agregan las capas
            valor.set(j, capas.get(i));
            j++;
        }

        for (int i = 0; i < 7; i++) {//Se agregan las epocas
            valor.set(j, epocas.get(i));
            j++;
        }
        
        for (int i = 0; i < 9; i++) {//Se agrega El learning rate
            valor.set(j, learningR.get(i));
            j++;
        }
        
        for (int i = 0; i < 9; i++) {//Se agrega el momentum
            valor.set(j, momentum.get(i));
            j ++;
        }
        
        this.valor = valor;
    }

    //
    // setters
    //
    public void setNeurona(BitSet neurona){
        for (int i = 0; i < 4; i++) {//Se actualia los bits de neurona
            valor.set(i, neurona.get(i));
        }
    }
    public void setCapas(BitSet capas){
        int j = 4;
        for (int i = 0; i < 2; i++) {
            valor.set(j, capas.get(i));
            j++;
        }
    }
    public void setEpocas(BitSet epocas){
        int j = 6;
        for (int i = 0; i < 7; i++) {
            valor.set(j, epocas.get(i));
            j++;
        }
    }
    public void setLR(BitSet lr){
        int j = 13;
        for (int i = 0; i < 9; i++) {
            valor.set(j, lr.get(i));
            j++;
        }
    }
    public void setMomentum(BitSet momentum){
        int j = 22;
        for (int i = 0; i < 9; i++) {
            valor.set(j, momentum.get(i));
            j++;
        }
    }
    
    //
    // Getters Binarios
    //
    public BitSet getNeuronasBin(){
        BitSet aux = new BitSet(4);
        for (int i = 0; i < 4; i++) {//Se actualiza los bits de neurona
            aux.set(i, valor.get(i));
        }
        return aux;
    } 
    public BitSet getCapasBin(){
        BitSet aux = new BitSet(2);
        int j = 4;
        for (int i = 0; i < 2; i++) {
            aux.set(i, valor.get(j));
            j++;
        }
        return aux;
    }
    public BitSet getEpocasBin(){
        BitSet aux = new BitSet(7);
        int j = 6;
        for (int i = 0; i < 7; i++) {
            aux.set(i, valor.get(j));
            j++;
        }
        return aux;
    }
    public BitSet getLRBin(){
        BitSet aux = new BitSet(9);
        int j = 13;
        for (int i = 0; i < 9; i++) {
            aux.set(i, valor.get(j));
            j++;
        }
        return aux;
    }
    public BitSet getMomentumBin(){
        BitSet aux = new BitSet(9);
        int j = 22;
        for (int i = 0; i < 9; i++) {
            aux.set(i, valor.get(j));
            j++;
        }
        return aux;
    }
    
    //
    // Getters
    //
    public int getNeuronas(){
        BitSet aux = this.getNeuronasBin();
        int nuevo = (int) convertidor.bitSetToInt(aux);

        /**
         * si
         * 0---3
         * 1---4
         * nuevo---nuevo+3
         */
        return nuevo+4;
    } 
    public int getCapas(){
        BitSet aux = this.getCapasBin();
        int valor = (int) convertidor.bitSetToInt(aux);
        // System.out.println("Valor: "+ valor);

        /**
         * si
         * 0---1
         * 1---2
         * valor----valor+1
         */
        return valor+1;
    }
    public int getEpocas(){
        BitSet aux = this.getEpocasBin();
        int valor = (int) convertidor.bitSetToInt(aux);

        /**
         * si
         * 0---10
         * 1---11
         * 90---100
         */
        return valor+20;
    }
    public Float getLR(){
        BitSet aux = this.getLRBin();
        int valor = (int) convertidor.bitSetToInt(aux);
        // System.out.println("Valor: "+ valor);
        /**
         * si
         * 400 es a 199
         *   ?  es a valor
         */
        return  ( ((valor+250)/1000f));
    }
    public Float getMomentum(){
        BitSet aux = this.getMomentumBin();
        int valor = (int) convertidor.bitSetToInt(aux);
        // System.out.println("Valor: "+ valor);
        /**
         * si
         * 400 es a 199
         *   ?  es a valor
         */
        return ( (valor+100)/1000f );
    }
    
    /**
     * Retorna la cadena de los valores de la Instancia
     */
    public String toString(){
        String neuronasCapas = RNA.capas(this.getNeuronas(), this.getCapas());
        int epocas = this.getEpocas();
        Float learningRate = this.getLR();
        Float momentum = this.getMomentum();
        return "n/c: "+neuronasCapas+" e: "+epocas+" lr: "+learningRate+" m: "+momentum;
    }   
}