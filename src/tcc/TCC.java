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
        
        System.out.println(child.getNumberOfConnectedComps());
        
        ga.mutate(child);
        
        System.out.println(child.getNumberOfConnectedComps());
        
        
        
                
    }

}
