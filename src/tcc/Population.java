/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

/**
 *
 * @author Cardoso
 */
public class Population {
    
    WSN[] WSNS;
    
    /*
    *Inicializa a populacao, com dado numero de individuos
    */
    public Population(int popSize, boolean fillPopulation) {
        WSNS = new WSN[popSize];
        //caso tenhamos que inicializar a população
        if(fillPopulation) {
            for(int i = 0; i < popSize; i++) {
                WSN novaWsn = new WSN();
                setWSN(i, novaWsn);
            }
        }
    }
    
    //coloca uma WSN numa posicao específica da população
    public void setWSN(int index, WSN wsn) {
        WSNS[index] = wsn;
    }
    
    //retorna o individuo da posição index
    public WSN getWSN(int index) {
        return this.WSNS[index];
    }
    
    //retorna o individuo mais apto da população
    public WSN getFittest() {
        WSN fittest = this.WSNS[0];
        //percorre todos os inivíduos e encontra o mais apto
        for(int i = 1; i < getPopSize(); i++) {
            if(fittest.getFitness() <= getWSN(i).getFitness()) {
                fittest = getWSN(i);
            }
        }
        return fittest;
    }
    
    public int getPopSize() {
        return this.WSNS.length;
    }
}
