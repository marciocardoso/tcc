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
public class GA {

    private static double mutationRate = 0.0125;
    private static boolean elitism = true;
    private static int tournamentSize = 5;

    public static Population evolvePopulation(Population population) {
        //cria a nova população sem inicializá-la
        Population newPop = new Population(population.size(), false);
        //mantém o melhor indivíduo da nossa população
        if (elitism) {
            newPop.addRelayNodeNetwork(0, population.getFittest());
        }

        //regula o índice a partir do qual os novos indiívudos
        //serão adicionados
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        //loop por todos os indivíduos fazendo o crossover
        for (int i = elitismOffset; i < newPop.size(); i++) {
            //seleciona os pais
            RelayNodeNetwork parent1 = tournamentSelection(population);
            RelayNodeNetwork parent2 = tournamentSelection(population);
            //filho
            RelayNodeNetwork child = crossover(parent1, parent2);
            newPop.addRelayNodeNetwork(i, child);
        }
        // efetua a mutação, exceto no indivíduo que possuia elitismo
        for (int i = elitismOffset; i < newPop.size(); i++) {
            mutate(newPop.getRelayNodeNetwork(i));
        }
        //devolve a nova população
        return newPop;
    }
    //crossover

    public static RelayNodeNetwork crossover(RelayNodeNetwork parent1, RelayNodeNetwork parent2) {
        //filho que será retornado    
        RelayNodeNetwork child;
        int childSize;

        int startPos;
        int endPos;

        //tamanhos do maior e menor pais
        RelayNodeNetwork biggestParent;
        RelayNodeNetwork smallerParent;

        //o tamanho do filho terá o tamanho do maior pai
        if (parent1.size() >= parent2.size()) {
            childSize = parent1.size();
            biggestParent = parent1;
            smallerParent = parent2;
            //seleciona o inicio e o final do intervalo que será adicionado ao filho
            int temp1 = 1, temp2 = 1;
            while (temp1 == temp2) {
                temp1 = (int) (Math.random() * (parent2.size() - 1));
                temp2 = (int) (Math.random() * (parent2.size() - 1));
            }
            if (temp1 > temp2) {
                startPos = temp2;
                endPos = temp1;
            } else {
                startPos = temp1;
                endPos = temp2;
            }
        } else {
            childSize = parent2.size();
            biggestParent = parent2;
            smallerParent = parent1;
            //seleciona o inicio e o final do intervalo que será adicionado ao filho
            int temp1 = 1, temp2 = 1;
            while (temp1 == temp2) {
                temp1 = (int) (Math.random() * (parent1.size() - 1));
                temp2 = (int) (Math.random() * (parent1.size() - 1));
            }
            if (temp1 > temp2) {
                startPos = temp2;
                endPos = temp1;
            } else {
                startPos = temp1;
                endPos = temp2;
            }
        }
        //faz um loop colocando os sensores dos pais nos filhos
        child = new RelayNodeNetwork(childSize);
        for (int i = 0; i < childSize; i++) {
            //coloca os primeiros elementos do maior pai
            if (i < startPos) {
                child.addRelayNode(i, biggestParent.getRelayNode(i));
            } //coloca os elementos intermediários sorteados do menor pai
            else if (i <= endPos) {
                child.addRelayNode(i, smallerParent.getRelayNode(i));
            } //completa o filho com os elementos restantes do maior pai
            else {
                child.addRelayNode(i, biggestParent.getRelayNode(i));
            }
        }
        child.refreshGraphEdges();
        return child;
    }

    //método de mutação
    public static void mutate(RelayNodeNetwork relayNodeNetwork) {
        //percorre os sensores da rede, decidindo ao acaso quais
        //sofrerão mutação
        for (int i = 0; i < relayNodeNetwork.size(); i++) {
            if (Math.random() <= mutationRate) {
                //gera as novas coordenadas do sensor
                int newX = (int) (Math.random() * ConstantNumber.X_MAX);
                int newY = (int) (Math.random() * ConstantNumber.Y_MAX);
                //aplica as novas coordenadas
                relayNodeNetwork.changeRNposition(i, newX, newY);
            }
        }
    }

    //seleciona indivíduos para crossover
    private static RelayNodeNetwork tournamentSelection(Population pop) {
        // Cria uma população para torneio
        Population tournament = new Population(tournamentSize, false);
        // para cada indivíduo da população de torneio
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.addRelayNodeNetwork(i, pop.getRelayNodeNetwork(randomId));
        }
        // pega o de maior fitness e retona
        RelayNodeNetwork fittest = tournament.getFittest();
        return fittest;
    }

}
