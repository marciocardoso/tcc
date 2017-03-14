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

    RelayNodeNetwork[] RelayNodeNetworks;

    /*
    *Inicializa a populacao, com dado numero de individuos
     */
    public Population(int popSize, boolean fillPopulation) {
        RelayNodeNetworks = new RelayNodeNetwork[popSize];
        //caso tenhamos que inicializar a população
        if (fillPopulation) {
            for (int i = 0; i < popSize; i++) {
                RelayNodeNetwork novaRelayNodeNetwork = new RelayNodeNetwork();
                addRelayNodeNetwork(i, novaRelayNodeNetwork);
            }
        }
    }

    //coloca uma RelayNodeNetwork numa posicao específica da população
    public void addRelayNodeNetwork(int index, RelayNodeNetwork relayNodeNetWork) {
        RelayNodeNetworks[index] = relayNodeNetWork;
    }

    //retorna o individuo da posição index
    public RelayNodeNetwork getRelayNodeNetwork(int index) {
        return this.RelayNodeNetworks[index];
    }

    //retorna o individuo mais apto da população
    public RelayNodeNetwork getFittest() {
        RelayNodeNetwork fittest = this.RelayNodeNetworks[0];
        //percorre todos os inivíduos e encontra o mais apto
        for (int i = 1; i < size(); i++) {
            if (fittest.getFitness() <= getRelayNodeNetwork(i).getFitness()) {
                fittest = getRelayNodeNetwork(i);
            }
        }
        return fittest;
    }

    public int size() {
        return this.RelayNodeNetworks.length;
    }
}
