package GraphOptimazer;

import cern.colt.matrix.DoubleFactory2D;

public class MainClass {
    public static void main(String[] args){
        GraphOptimazer graphOptimazer = new GraphOptimazer(15);
        graphOptimazer.optimize();
    }
}
