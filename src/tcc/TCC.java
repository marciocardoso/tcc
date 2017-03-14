/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

/**
 *
 * @author Labin-L1
 */
public class TCC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Population pop = new Population(30, true);
        
        for( int i = 0; i < 30; i++) {            
            System.out.print(pop.getWSN(i).getNumberOfConnectedComps() + "  ");
            if( i==9 || i==19 || i==29) {
                System.out.println("");
            }
        }
        
        //teste3
        GA ga = new GA();
        WSN child;
        
        child = ga.crossover(pop.getWSN(0), pop.getWSN(1));
        
        System.out.println(ga.teste[0]);
        System.out.println(ga.teste[1]);        
        System.out.println(ga.teste[2]);
        System.out.println(ga.teste[3]);
        System.out.println(ga.teste[4]);
        
        for (int i = 0; i < 20; i++) {
            if(i%5==0) {
                System.out.println("");
            }
            System.out.print(pop.getWSN(0).getSensor(i) + "\t");
            
        }
        System.out.println("");
        for (int i = 0; i < 20; i++) {
            if(i%5==0) {
                System.out.println("");
            }
            System.out.print(pop.getWSN(1).getSensor(i) + "\t");
            
        }
        System.out.println("");
        for (int i = 0; i < 20; i++) {
            if(i%5==0) {
                System.out.println("");
            }
            System.out.print(child.getSensor(i) + "\t");
            
        }
                
    }

}
