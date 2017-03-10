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
    
    //inicializa a WSN com pelo menos 20 sensores
    
    
    
}
