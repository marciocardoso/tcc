/*
 * Simula uma RelayNodeNetwork 
 * 
 * 
 */
package tcc;

import java.util.ArrayList;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.PrimMinimumSpanningTree;
import org.jgrapht.graph.SimpleGraph;

/**
 *
 * @author Labin-L1
 */
public class RelayNodeNetwork {

//inicializa a RelayNodeNetwork com dado numero de sensores relayNodeList
    public RelayNodeNetwork() {
        for (int i = 0; i < ConstantNumber.NUMBER_OF_SENSORS; i++) {
            Sensor sensor = new Sensor();

            //adiciona o sensor ao vetor
            this.relayNodeList.add(sensor);

            //adiciona o vetor ao grafo
            this.graph.addVertex(sensor);
        }

        //cria as arestas do grafo
        this.setGraphEdges();
    }

    public RelayNodeNetwork(int size) {
        for (int i = 0; i < size; i++) {
            this.relayNodeList.add(new Sensor());
        }
    }

    //simula o grafo para esta RelayNodeNetwork
    private UndirectedGraph<Sensor, DefaultEdge> graph
            = new SimpleGraph<Sensor, DefaultEdge>(DefaultEdge.class);

    //vetor com relayNodeList 
    private ArrayList relayNodeList = new ArrayList<Sensor>();

    //retorna um sensor especifico
    public Sensor getRelayNode(int index) {
        return (Sensor) this.relayNodeList.get(index);
    }

    //adicona mais um sensor à rede
    public void addRelayNode(int index, Sensor sensor) {
        if (this.relayNodeList.get(index) == null) {
            this.relayNodeList.add(index, sensor);
            this.graph.addVertex(sensor);
        } else {
            this.relayNodeList.remove(index);
            this.relayNodeList.add(index, sensor);
            this.graph.addVertex(sensor);
        }

    }

    //retorna o número de relayNodeList na rede
    public int size() {
        return this.relayNodeList.size();
    }

    public UndirectedGraph<Sensor, DefaultEdge> getGraph() {
        return this.graph;
    }

    public ArrayList getRelayNodeList() {
        return this.relayNodeList;
    }

    //encontra todas as arestas do grafo deste RelayNodeNetwork
    private void setGraphEdges() {
        for (int i = 0; i < this.relayNodeList.size(); i++) {
            for (int j = 0; j < this.relayNodeList.size(); j++) {
                if (i != j) {
                    if (getRelayNode(i).distanceTo(getRelayNode(j)) <= ConstantNumber.SENSOR_RANGE) {
                        this.graph.addEdge(getRelayNode(i), getRelayNode(j));
                    }
                }
            }
        }
    }

    //retorna o numero de grafos conexos (Gi)
    public int numberOfConnectedRnComps() {
        PrimMinimumSpanningTree sPanningTree;

        if (this.graph.edgeSet().size() != 0) {
            sPanningTree = new PrimMinimumSpanningTree(this.graph);
        } else {
            return this.graph.vertexSet().size();
        }

        int numberOfVertex = this.graph.vertexSet().size();

        int nummberOfEdgeOfSpanningTree;

        if (sPanningTree.getMinimumSpanningTreeEdgeSet() == null) {
            nummberOfEdgeOfSpanningTree = 0;
        } else {
            nummberOfEdgeOfSpanningTree = sPanningTree.getMinimumSpanningTreeEdgeSet().size();
        }

        return numberOfVertex - nummberOfEdgeOfSpanningTree;
    }

    //Caso algum sensor mude de lugar, seja removido
    //ou adicionado: Remove todas as arestas
    //para que novas sejam adicionadas
    public void refreshGraphEdges() {
        for (int i = 0; i < this.relayNodeList.size(); i++) {
            for (int j = 0; j < this.relayNodeList.size(); j++) {
                if (i != j) {
                    this.graph.removeAllEdges(getRelayNode(i), getRelayNode(j));
                }
            }
        }
        this.setGraphEdges();
    }

    //remove um sensor da rede
    public void removeRelyNode(int index) {
        //remove tanto da lista de sensores quanto do grafo
        this.relayNodeList.remove(index);
        this.graph.removeVertex(getRelayNode(index));

        //atualiza as arestas
        this.refreshGraphEdges();
    }

    //muda as coordenadas do sensor na posição INDEX, para as coordenadas dadas
    public void changeRNposition(int index, int newX, int newY) {
        getRelayNode(index).setX(newX);
        getRelayNode(index).setY(newY);

        //atualiza as arestas
        this.refreshGraphEdges();
    }

    //retorna o fitness de cada wsn.
    //quanto menor o numero de grafos conexos, melhor.
    //então quanto maior for o valor do fitness, 
    //mais apto é o individuo
    public double getFitness() {
        double fitness = 1.0 / numberOfConnectedRnComps();
        return fitness;
    }
}
