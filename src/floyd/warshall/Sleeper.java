/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floyd.warshall;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author void
 */
public class Sleeper implements Runnable{
    Thread t;
    
    public Sleeper()
    {
        t = new Thread();
        t.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sleeper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
