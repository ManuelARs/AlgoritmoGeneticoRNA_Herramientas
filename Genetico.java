import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;
import java.lang.Object;

/**
 * Clase genetico
 */
public class Genetico {
    // Factor de mutación
    public static final float FACTOR_MUTACION = 0.2f;

    public Genetico() {
    };

    /**
     * Funcion que genera una Poblacion de N individuos
     * se almacena en un ArrayList
     * La poblacion generada es totalmente ale
     * 
     * @param cantidadIndividuos
     * @return
     */
    public ArrayList<PhiInstance> generarPoblacion(int cantidadIndividuos) {
        ArrayList<PhiInstance> poblacionaux = new ArrayList<>();
        for (int i = 0; i < cantidadIndividuos; i++) {
            System.out.println("Numero: " + i);
            poblacionaux.add(generaIndividuo());
        }
        return poblacionaux;
    }

    /**
     * Genera un individuo de la la clase
     * PhiInstance de forma aleatorio
     * 
     * @return
     */
    public PhiInstance generaIndividuo() {
        BitSetTo conversor = new BitSetTo();
        int neuronas = (int) (Math.random() * 12);
        int capas = (int) (Math.random() * 3);
        int epocas = (int) (Math.random() * 81);
        int rl = (int) (Math.random() * 101);
        int mom = (int) (Math.random() * 201);

        // System.out.println("n: "+(neuronas+3)+" c: "+(capas+1)+" e: "+(epocas+10)+"
        // rl: "+((rl+200)/1000f)+" m:"+((mom+200)/1000f) );

        BitSet ne = conversor.longToBitSet(neuronas, PhiInstance.SIZE_NEURONA);
        BitSet ca = conversor.longToBitSet(capas, PhiInstance.SIZE_CAPAS);
        BitSet ep = conversor.longToBitSet(epocas, PhiInstance.SIZE_EPOCAS);
        BitSet lr = conversor.longToBitSet(rl, PhiInstance.SIZE_LR);
        BitSet mo = conversor.longToBitSet(mom, PhiInstance.SIZE_MOMENTUM);

        // Crea un BitSet del tamaño de SIZE_INSTANCE = 35
        PhiInstance aux = new PhiInstance(ne, ca, ep, lr, mo);

        System.out.println("" + aux.toString());
        // Regresa el nuevo individuo
        return aux;
    }

    /**
     * Funcion que genera un hijo dado una instancia phi padre y madre
     * 
     * @param poblacion
     * @param pad
     * @param mad
     */
    public void generaHijo(ArrayList<PhiInstance> poblacion, int pad, int mad) {

        // Boolean primogenito = false;
        // Boolean segundo = false;
        Validador valida = new Validador();
        // Obtiene el bitSet del padre
        BitSet padre = poblacion.get(pad).valor;

        // Obtiene el bitSet del Hijo
        BitSet madre = poblacion.get(mad).valor;

        // Hijos
        BitSet hijo1 = new BitSet(PhiInstance.SIZE_INSTANCE);
        BitSet hijo2 = new BitSet(PhiInstance.SIZE_INSTANCE);
        int i = 0;
        int elige = (Math.random() <= 0.5) ? 1 : 2;
        int Prende;
        int cruzOpulso;
        int neu, cap, epoc, lrn, momnt;
        Random rn = new Random();
        // System.out.println("Primer Elige: " + elige);
        // elige, Prende y cruzOpulso son 1 o 2, cuando elige(este se decide una vez en
        // el principio) y prende son iguales significa que ese subconjunto sera
        // modificado en sus bits por cruz o pulso segun se decida con el aleatorio
        // resultado de cruzOpulso igual entre 1 o 2, en caso de ser diferentes
        // simplemente se pasan sus bits
        Prende = (Math.random() <= 0.5) ? 1 : 2;
        cruzOpulso = (Math.random() <= 0.5) ? 1 : 2;
        neu = rn.nextInt(3 - 1 + 1) + 1;
        // System.out.println("Prende: " + Prende);
        // System.out.println("Neuronas random: " + neu);
        while (i < PhiInstance.SIZE_NEURONA) {
            if (Prende == elige) {
                if (cruzOpulso == 1) {
                    if (i < neu) {
                        hijo1.set(i, padre.get(i));
                        hijo2.set(i, madre.get(i));
                    } else {
                        hijo1.set(i, madre.get(i));
                        hijo2.set(i, padre.get(i));
                    }
                }
                if (cruzOpulso == 2) {
                    if (i % 2 == 0) {
                        hijo1.set(i, padre.get(i));
                        hijo2.set(i, madre.get(i));
                    } else {
                        hijo1.set(i, madre.get(i));
                        hijo2.set(i, padre.get(i));
                    }
                }
            } else {
                hijo1.set(i, padre.get(i));
                hijo2.set(i, madre.get(i));
            }
            i++;
        }
        Prende = (Math.random() <= 0.5) ? 1 : 2;
        cruzOpulso = (Math.random() <= 0.5) ? 1 : 2;
        cap = rn.nextInt(1 - 1 + 1) + 1;
        // System.out.println("Prende: " + Prende);

        // System.out.println("Cap random: " + cap);
        while (i < (PhiInstance.SIZE_CAPAS + 4)) {
            if (Prende == elige) {
                if (cruzOpulso == 1) {
                    if (i < (cap+ 4)) {
                        hijo1.set(i, padre.get(i));
                        hijo2.set(i, madre.get(i));
                    } else {
                        hijo1.set(i, madre.get(i));
                        hijo2.set(i, padre.get(i));
                    }
                }
                if (cruzOpulso == 2) {
                    if (i % 2 == 0) {
                        hijo1.set(i, padre.get(i));
                        hijo2.set(i, madre.get(i));
                    } else {
                        hijo1.set(i, madre.get(i));
                        hijo2.set(i, padre.get(i));
                    }
                }
            } else {
                hijo1.set(i, padre.get(i));
                hijo2.set(i, madre.get(i));
            }
            i++;
        }
        Prende = (Math.random() <= 0.5) ? 1 : 2;
        cruzOpulso = (Math.random() <= 0.5) ? 1 : 2;
        epoc = rn.nextInt(6 - 1 + 1) + 1;
        // System.out.println("Prende: " + Prende);

        // System.out.println("Epoc random: " + epoc);
        while (i < (PhiInstance.SIZE_EPOCAS + 6)) {
            if (Prende == elige) {
                if (cruzOpulso == 1) {
                    if (i < (epoc+ 6)) {
                        hijo1.set(i, padre.get(i));
                        hijo2.set(i, madre.get(i));
                    } else {
                        hijo1.set(i, madre.get(i));
                        hijo2.set(i, padre.get(i));
                    }
                }
                if (cruzOpulso == 2) {
                    if (i % 2 == 0) {
                        hijo1.set(i, padre.get(i));
                        hijo2.set(i, madre.get(i));
                    } else {
                        hijo1.set(i, madre.get(i));
                        hijo2.set(i, padre.get(i));
                    }
                }
            } else {
                hijo1.set(i, padre.get(i));
                hijo2.set(i, madre.get(i));
            }
            i++;
        }
        Prende = (Math.random() <= 0.5) ? 1 : 2;
        cruzOpulso = (Math.random() <= 0.5) ? 1 : 2;
        lrn = rn.nextInt(8 - 1 + 1) + 1;
        // System.out.println("Prende: " + Prende);

        // System.out.println("Learning random: " + lrn);
        while (i < (PhiInstance.SIZE_LR + 13)) {
            if (Prende == elige) {
                if (cruzOpulso == 1) {
                    if (i < (lrn+13)) {
                        hijo1.set(i, padre.get(i));
                        hijo2.set(i, madre.get(i));
                    } else {
                        hijo1.set(i, madre.get(i));
                        hijo2.set(i, padre.get(i));
                    }
                }
                if (cruzOpulso == 2) {
                    if (i % 2 == 0) {
                        hijo1.set(i, padre.get(i));
                        hijo2.set(i, madre.get(i));
                    } else {
                        hijo1.set(i, madre.get(i));
                        hijo2.set(i, padre.get(i));
                    }
                }
            } else {
                hijo1.set(i, padre.get(i));
                hijo2.set(i, madre.get(i));
            }
            i++;
        }
        Prende = (Math.random() <= 0.5) ? 1 : 2;
        cruzOpulso = (Math.random() <= 0.5) ? 1 : 2;
        momnt = rn.nextInt(8 - 1 + 1) + 1;
        // System.out.println("Prende: " + Prende);

        // System.out.println("Momentum random: " + momnt);
        while (i < (PhiInstance.SIZE_MOMENTUM + 22)) {
            if (Prende == elige) {
                if (cruzOpulso == 1) {
                    if (i < (momnt+22)) {
                        hijo1.set(i, padre.get(i));
                        hijo2.set(i, madre.get(i));
                    } else {
                        hijo1.set(i, madre.get(i));
                        hijo2.set(i, padre.get(i));
                    }
                }
                if (cruzOpulso == 2) {
                    if (i % 2 == 0) {
                        hijo1.set(i, padre.get(i));
                        hijo2.set(i, madre.get(i));
                    } else {
                        hijo1.set(i, madre.get(i));
                        hijo2.set(i, padre.get(i));
                    }
                }
            } else {
                hijo1.set(i, padre.get(i));
                hijo2.set(i, madre.get(i));
            }
            i++;
        }

        if (valida.validaInstancia(new PhiInstance(hijo1), poblacion) ) {
            poblacion.add(new PhiInstance(hijo1));
            // primogenito = true;

        }
        // else{
        // // System.out.println("NO jalo 1");
        // }
        if (valida.validaInstancia(new PhiInstance(hijo2), poblacion) ) {
            poblacion.add(new PhiInstance(hijo2));
            // segundo = true;

        }

        // else{
        // // System.out.println("NO jalo 2");
        // }
        // while (primogenito == false || segundo == false) {
        // && primogenito == false
        // primogenito = true;
        // && segundo == false
        // segundo = true;
        // }

    }

    /**
     * funcion mutadora
     * 
     * @param instancia
     * @return
     */
    public PhiInstance mutacion(PhiInstance instancia, ArrayList<PhiInstance> poblacion) {
        Random r = new Random(); // Aqui se elige si sera o no sera mutado o sea que es random la mutacion segun
                                 // el factor?mas complejo de lo necesario

        PhiInstance aux = new PhiInstance(new BitSet(PhiInstance.SIZE_INSTANCE));
        Validador v = new Validador();
        do {

            aux.setNeurona(instancia.getNeuronasBin());
            aux.setCapas(instancia.getCapasBin());
            aux.setEpocas(instancia.getEpocasBin());
            aux.setLR(instancia.getLRBin());
            aux.setMomentum(instancia.getMomentumBin());

            // System.out.println("INSTANCIA: "+instancia);
            // System.out.println("AUX antes: "+aux);
            // obtiene indice random de 0 a 35
            int indiceRandom = r.nextInt(PhiInstance.SIZE_INSTANCE);
            // System.out.println("indicerandom: "+indiceRandom);
            // obtiene valor de bit en el indice random
            Boolean valorBit = aux.valor.get(indiceRandom);
            // System.out.println("VALORBIT: "+valorBit);
            // invierte el valor del bit en el indice random
            aux.valor.set(indiceRandom, !valorBit);
            // System.out.println("AUX despues: "+aux);
            // System.out.println("Valida: " + v.validaInstancia(aux, poblacion));
        } while (!v.validaInstancia(aux, poblacion));
        instancia = aux;
        return instancia;
    }
}