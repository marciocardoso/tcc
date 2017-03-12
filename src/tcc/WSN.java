/*
 * Simula uma WSN 
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
public class WSN {

//inicializa a WSN com dado numero de sensores sensorList
    public WSN() {
        for (int i = 0; i < ConstantNumber.NUMBER_OF_SENSORS; i++) {
            Sensor sensor = new Sensor();

            //adiciona o sensor ao vetor
            this.sensorList.add(sensor);

            //adiciona o vetor ao grafo
            this.graph.addVertex(sensor);
        }

        //cria as arestas do grafo
        this.setGraphEdges();
    }

    //simula o grafo para esta WSN
    private UndirectedGraph<Sensor, DefaultEdge> graph
            = new SimpleGraph<Sensor, DefaultEdge>(DefaultEdge.class);

    //vetor com sensorList 
    private ArrayList sensorList = new ArrayList<Sensor>();

    //retorna um sensor especifico
    public Sensor getSensor(int index) {
        return (Sensor) this.sensorList.get(index);
    }

    //retorna o número de sensorList na rede
    public int getNumberOfSensores() {
        return this.sensorList.size();
    }

    public UndirectedGraph<Sensor, DefaultEdge> getGraph() {
        return this.graph;
    }

    public ArrayList getSensorList() {
        return this.sensorList;
    }

    //encontra todas as arestas do grafo deste WSN
    public void setGraphEdges() {
        for (int i = 0; i < this.sensorList.size(); i++) {
            for (int j = 0; j < this.sensorList.size(); j++) {
                if (i != j) {
                    if (getSensor(i).distanceTo(getSensor(j)) <= ConstantNumber.SENSOR_RANGE) {
                        this.graph.addEdge(getSensor(i), getSensor(j));
                    }
                }
            }
        }
    }

    //retorna o numero de grafos conexos (Gi)
    public int getNumberOfConnectedComps() {
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
    public void removeAllGraphEdges() {
        for (int i = 0; i < this.sensorList.size(); i++) {
            for (int j = 0; j < this.sensorList.size(); j++) {
                if (i != j) {
                    this.graph.removeAllEdges(getSensor(i), getSensor(j));
                }
            }
        }
    }
    
    //retorna o fitness de cada wsn.
    //quanto menor o numero de grafos conexos, melhor.
    //então quanto maior for o valor do fitness, 
    //mais apto é o individuo
    public double getFitness() {
        double fitness = 1.0/getNumberOfConnectedComps();
        return fitness;
    }
}
