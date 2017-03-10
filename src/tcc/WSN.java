/*
 * Simula uma WSN 
 * 
 * 
 */
package tcc;

import java.util.ArrayList;

/**
 *
 * @author Labin-L1
 */
public class WSN {

//inicializa a WSN com 20 sensores
    public WSN()
    {
        for( int i = 0; i < 20; i++ )
        {
            Sensor sensor = new Sensor();
            this.sensores.add(sensor);
        }
    }    
    
    //vetor com sensores 
    private ArrayList sensores = new ArrayList<Sensor>();
    
    //retorna um sensor especifico
    public Sensor getSensor( int index )
    {
        return (Sensor)this.sensores.get(index);
    }
    
    //retorna o número de sensores na rede
    public int getNumberOfSensores()
    {
        return this.sensores.size();
    }
    
    
    public void findConnectedSensors()
    {
        for( int i = 0; i < this.sensores.size(); i++ )
        {
            for( int j = 0; j < this.sensores.size(); j ++)
            {
                if( i != j )
                {
                    if( getSensor(i).distanceTo(getSensor(j)) <= new Sensor().ALCANCE ) 
                    {
                        getSensor(i).addConnectedSensor( getSensor(j) );
                    }
                }
            }
        }
    }
    
}
