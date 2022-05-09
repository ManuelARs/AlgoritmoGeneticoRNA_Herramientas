import java.util.ArrayList;
import java.util.BitSet;

/**
 * validador
 */
public class Validador {
    private BitSetTo tranformador = new BitSetTo();
    public Validador(){};

    /**
     * Valida si el binario es una instancias validas de 
     * neuronas
     * Retorna true si es correcto
     * false si no es una instancia valida
     * @param neuronas
     * @return *falso* si no es valido
     * *verdadero* si es valido
     */
    public boolean validaNeuronas(BitSet neuronas){
        if (tranformador.bitSettoLong( neuronas ) +4 > PhiInstance.MAX_NEURONA) {
            return false;
        }
        return true;
    }

    /**
     * Valida si el binario es una instancias validas de 
     * capas
     * Retorna true si es correcto
     * false si no es una instancia valida
     * @param capas
     * @return *falso* si no es valido
     * *verdadero* si es valido
     */
    public boolean validaCapas(BitSet capas){
        if (tranformador.bitSettoLong( capas )+1 > PhiInstance.MAX_CAPAS) {
            return false;
        }
        return true;
    }

    /**
     * Valida si el binario es una instancias validas de 
     * epocas
     * Retorna true si es correcto
     * false si no es una instancia valida
     * @param capas
     * @return *falso* si no es valido
     * *verdadero* si es valido
     */
    public boolean validaEpocas(BitSet epocas){
        if (tranformador.bitSettoLong( epocas )+20 > PhiInstance.MAX_EPOCAS) {
            return false;
        }
        return true;
    }

    /**
     * Valida si el binario es una instancias validas de 
     * Learning rate
     * Retorna true si es correcto
     * false si no es una instancia valida
     * @param capas
     * @return *falso* si no es valido
     * *verdadero* si es valido
     */
    public boolean validaLR(BitSet lr){
        if (tranformador.bitSettoLong( lr )+250 > PhiInstance.MAX_LR) {
            return false;
        }
        return true;
    }
    /**
     * Valida si el binario es una instancias validas de 
     * momentum
     * Retorna true si es correcto
     * false si no es una instancia valida
     * @param capas
     * @return *falso* si no es valido
     * *verdadero* si es valido
     */
    public boolean validaMomentum(BitSet momentum){
        if (tranformador.bitSettoLong( momentum )+100> PhiInstance.MAX_MOMENTUM) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param instancia
     * PhiInstance a validar
     * @param poblacion
     * ArayList de PhiInstance
     * @return *true* en caso de ser valida toda la instancia en cada valor
     * *false* en caso de que algun campo no cumpla con su valor maximo
     */
    public boolean validaInstancia(PhiInstance instancia,ArrayList <PhiInstance> poblacion){
        if(!validaNeuronas(instancia.getNeuronasBin())){
            // System.out.println(" FalloNeuronas ");
            return false;
        }    
        if(!validaCapas(instancia.getCapasBin())){
            // System.out.println(" Fallo CAPAS ");
            return false;
        }
        if(!validaEpocas(instancia.getEpocasBin())){
            // System.out.println(" Fallo EPCOAS ");
            return false;
        }
        if(!validaLR(instancia.getLRBin())){
            // System.out.println(" Fallo LR ");
            return false;
        }
        if(!validaMomentum(instancia.getMomentumBin())){
            // System.out.println(" Fallo MOMENTUM ");
            return false;
        }
        for(PhiInstance i:poblacion)
            
            if(i.valor.equals(instancia.valor)){
                // System.out.println(" Fallo SE REPITE ");
                // System.out.println(" i: "+ i.toString());
                // System.out.println(" instancia: "+ instancia.toString());
                return false;
            }
        return true;
    }
    
    /**
     * 
     * @param instancia
     * @return *falso* si no es valido
     * *verdadero* si es valido
     */
    public boolean validaInstanciaPhi(PhiInstance instancia){
        if (instancia.valor.length() != PhiInstance.SIZE_INSTANCE) {
            return false;
        }
        return true;
    }
}