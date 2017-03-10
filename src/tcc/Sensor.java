/*
 * Simula um SENSOR
 */
package tcc;

import java.util.ArrayList;

/**
 *
 * @author Labin-L1
 */
public class Sensor {
    
    //Alcance Padrão do sensor
    public int ALCANCE = 15;
    
    //coordenadas do sensor no Plano
    int x;
    int y;
    
    //guarda todos os componentes conexos deste sensor
    public ArrayList connected_sensors = new ArrayList<Sensor>();
    
    //atribui uma posição aleatória ao sensor;
    public Sensor()
    {
        this.x = (int)(Math.random()*100);
        this.y = (int)(Math.random()*100);
    }
    
    //gets para as coordenadas
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    //retorna o número de sensores conexos a este
    public int numberOfConnectedSensors()
    {
        return this.connected_sensors.size();
    }
    
    
    //calcula e retorna a distance deste sensor até qualquer outro        
    public double distanceTo (Sensor sensor)
    {
        int xDistance = Math.abs( getX() - sensor.getX() );
        int yDistance = Math.abs( getY() - sensor.getY() );
        
        double distance = Math.sqrt( xDistance*xDistance + yDistance*yDistance );
        
        return distance;
    }
    
}
