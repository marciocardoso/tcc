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
            newPop.addWSN(0, population.getFittest());
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
            WSN parent1 = tournamentSelection(population);
            WSN parent2 = tournamentSelection(population);
            //filho
            WSN child = crossover(parent1, parent2);
            newPop.addWSN(i, child);
        }
        // efetua a mutação, exceto no indivíduo que possuia elitismo
        for (int i = elitismOffset; i < newPop.size(); i++) {
            mutate(newPop.getWSN(i));
        }
        //devolve a nova população
        return newPop;
    }
    //crossover

    public static WSN crossover(WSN parent1, WSN parent2) {
        //filho que será retornado    
        WSN child;
        int childSize;

        int startPos;
        int endPos;

        //tamanhos do maior e menor pais
        WSN biggestParent;
        WSN smallerParent;

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
        child = new WSN(childSize);
        for (int i = 0; i < childSize; i++) {
            //coloca os primeiros elementos do maior pai
            if (i < startPos) {
                child.addSensor(i, biggestParent.getSensor(i));
            } //coloca os elementos intermediários sorteados do menor pai
            else if (i <= endPos) {
                child.addSensor(i, smallerParent.getSensor(i));
            } //completa o filho com os elementos restantes do maior pai
            else {
                child.addSensor(i, biggestParent.getSensor(i));
            }
        }
        child.refreshGraphEdges();
        return child;
    }

    //método de mutação
    public static void mutate(WSN wsn) {
        //percorre os sensores da rede, decidindo ao acaso quais
        //sofrerão mutação
        for (int i = 0; i < wsn.size(); i++) {
            if (Math.random() <= mutationRate) {
                //gera as novas coordenadas do sensor
                int newX = (int) (Math.random() * ConstantNumber.X_MAX);
                int newY = (int) (Math.random() * ConstantNumber.Y_MAX);
                //aplica as novas coordenadas
                wsn.changeSensorPosition(i, newX, newY);
            }
        }
    }

    //seleciona indivíduos para crossover
    private static WSN tournamentSelection(Population pop) {
        // Cria uma população para torneio
        Population tournament = new Population(tournamentSize, false);
        // para cada indivíduo da população de torneio
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.addWSN(i, pop.getWSN(randomId));
        }
        // pega o de maior fitness e retona
        WSN fittest = tournament.getFittest();
        return fittest;
    }

}
