package lib.logging;

import org.junit.Test;

public class main {

    @Test
    public void main () {

        long i2 = System.currentTimeMillis()/1000;
        long i = 0;
        long z = i;
        while(i<5){

            i = (System.currentTimeMillis()/1000)-i2;
            System.err.println(i);

            if (z == i){
                System.err.println("No");
            }else{
                System.err.println("Yes");
            }


        }

    }
}
