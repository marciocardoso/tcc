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

    //guarda todos os componentes conexos deste sensor
    //private ArrayList connected_sensors = new ArrayList<Sensor>();
    //atribui uma posição aleatória ao sensor;
    public Sensor() {
        this.x = (int) (Math.random() * ConstantNumber.X_MAX);
        this.y = (int) (Math.random() * ConstantNumber.Y_MAX);
    }

    public Sensor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //gets para as coordenadas
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    //retorna o número de sensores conexos a este
    /*public int getNumberOfConnectedSensors() {
        return this.connected_sensors.size();
    }

    public void addConnectedSensor(Sensor sensor) {
        this.connected_sensors.add(sensor);
    }*/
    //calcula e retorna a distance deste sensor até qualquer outro        
    public double distanceTo(Sensor sensor) {
        int xDistance = Math.abs(getX() - sensor.getX());
        int yDistance = Math.abs(getY() - sensor.getY());

        double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);

        return distance;
    }

}
