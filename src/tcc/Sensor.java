/*
 * Simula um SENSOR
 */
package tcc;

/**
 *
 * @author Labin-L1
 */
public class Sensor {
    
    //coordenadas do sensor no Plano
    int x;
    int y;
    
    //atribui uma posição aleatória ao sensor;
    public Sensor()
    {
        this.x = (int)(Math.random()*100);
        this.y = (int)(Math.random()*100);
    }
    
}
