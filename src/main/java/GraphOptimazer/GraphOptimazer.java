package GraphOptimazer;
import cern.colt.function.DoubleDoubleFunction;
import cern.colt.function.DoubleFunction;
import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.EigenvalueDecomposition;
import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;


public class GraphOptimazer {

    double[][] graph;
    int graphPoints;
    double[][] firstGraph;
    double[][] secondGraph;

    public GraphOptimazer(int size)
    {
        graphPoints = size;
        graph = new double[graphPoints][graphPoints];
        Arrays.stream(graph).forEach((elem)->{
            elem = new double[graphPoints];
            for(double val: elem) {
                val = 0.0;
            }
        });
        firstGraph = new double[graphPoints][graphPoints];
        secondGraph = new double[graphPoints][graphPoints];
        fillGraph();
        printMatrix(graph);
    }

    public void fillGraph() {
       new ArrayList<Position>() {
            {
                add(new Position(1, 7));
                add(new Position(2, 3));
                add(new Position(2, 8));
                add(new Position(3, 2));
                add(new Position(3, 4));
                add(new Position(3, 9));
                add(new Position(4, 3));
                add(new Position(4, 5));
                add(new Position(4, 10));
                add(new Position(5, 4));
                add(new Position(5, 11));
                add(new Position(6, 7));
                add(new Position(6, 13));
                add(new Position(7, 1));
                add(new Position(7, 6));
                add(new Position(7, 14));
                add(new Position(8, 2));
                add(new Position(8, 9));
                add(new Position(8, 15));
                add(new Position(9, 3));
                add(new Position(9, 8));
                add(new Position(9, 10));
                add(new Position(10, 4));
                add(new Position(10, 9));
                add(new Position(10, 11));
                add(new Position(11, 5));
                add(new Position(11, 10));
                add(new Position(12, 13));
                add(new Position(13, 6));
                add(new Position(13, 12));
                add(new Position(13, 14));
                add(new Position(14, 7));
                add(new Position(14, 13));
                add(new Position(14, 15));
                add(new Position(15, 8));
                add(new Position(15, 14));
            }
        }.forEach((elem)->{
            graph[elem.getRowIndex() - 1][elem.getColumnIndex() - 1] = 1.0;
        });

       /*
       new ArrayList<Position>() {
            {
                add(new Position(1, 3));
                add(new Position(1, 5));
                add(new Position(2, 4));
                add(new Position(2, 6));
                add(new Position(3, 1));
                add(new Position(3, 4));
                add(new Position(3, 5));
                add(new Position(4, 2));
                add(new Position(4, 3));
                add(new Position(4, 6));
                add(new Position(5, 1));
                add(new Position(5, 3));
                add(new Position(6, 2));
                add(new Position(6, 4));
            }
        }.forEach((elem)->{
            graph[elem.getRowIndex() - 1][elem.getColumnIndex() - 1] = 1.0;
        });
        */
    }

    public void createGraphs(ArrayList<Integer> firstGraphPoints, ArrayList<Integer> secondGraphPoints){

    }

    public void printMatrix(double[][] matrix) {
        Arrays.stream(matrix).forEach((row) -> {
            Arrays.stream(row).forEach(elem -> {
                if (elem == 1.0)
                    System.out.print("\u001B[31m " + elem + " \u001B[0m");
                else
                    System.out.print(elem + " ");
            });
            System.out.println();
        });
    }

    public void printMatrix(double[] matrix, boolean useColorizedOutput){
        Arrays.stream(matrix).forEach((elem) -> {
                if (elem == 1.0)
                    if(useColorizedOutput)
                        System.out.print("\u001B[31m " + elem + " \u001B[0m\n");
                    else
                        System.out.print(elem + "\n");

                else
                    System.out.print(elem + "\n");
            });
            System.out.println();
    }

    public void printMatrix(DoubleMatrix2D matrix, boolean useColorizedOutput){
        for(int i = 0; i < matrix.rows(); ++i) {
            for(int j = 0; j < matrix.columns(); ++j) {
                if(matrix.get(i,j) != 0.0)
                    if(useColorizedOutput)
                        System.out.print("\u001B[31m " + matrix.get(i,j) + " \u001B[0m");
                    else
                        System.out.print(matrix.get(i,j) + " ");

                else
                    System.out.print(matrix.get(i,j) + " ");
            }
            System.out.println();
        }
    }

    public void printMatrix(DoubleMatrix1D matrix, boolean useColorizedOutPut){
        for(int i = 0; i < matrix.size(); ++i) {
                if(matrix.get(i) != 0.0)
                    if(useColorizedOutPut)
                        System.out.print("\u001B[31m " + matrix.get(i) + " \u001B[0m\n");
                    else
                        System.out.print(matrix.get(i) + "\n");
                else
                    System.out.print(matrix.get(i) + "\n");
            }
            System.out.println();
        }

    public void optimize(){
        DoubleFactory2D matrixFactory = DoubleFactory2D.dense;
        DoubleMatrix2D AMatrix = matrixFactory.make(graph);
        DoubleMatrix2D BMatrix = matrixFactory.identity(graphPoints);

//        System.out.println();
        System.out.println();

        for(int i = 0; i < graphPoints; ++i) {
            int relatedCount = 0;
            for(int j = 0; j < graphPoints; ++j) {
                if(graph[i][j] == 1.0)
                    relatedCount++;
            }
            BMatrix.set(i,i,relatedCount);
        }
        DoubleDoubleFunction substract = new DoubleDoubleFunction() {
            @Override
            public double apply(double v, double v1) {
                return v - v1;
            }
        };
        DoubleMatrix2D LMatrix = BMatrix.assign(AMatrix, substract);
        printMatrix(LMatrix, true);

        EigenvalueDecomposition eigenvalueDecomposition = new EigenvalueDecomposition(LMatrix);
        DoubleMatrix1D UVector = eigenvalueDecomposition.getV().viewColumn(1);
        //printMatrix(eigenvalueDecomposition.getD(),true);
        //printMatrix(UVector, false);
        double UAvg = Arrays.stream(UVector.toArray()).sum() / UVector.size();
        ArrayList<Integer> firstGraphPoints = new ArrayList<>();
        ArrayList<Integer> secondGraphPoints = new ArrayList<>();
        double[] uVector = UVector.toArray();
        boolean nextFirstGraph = true;
        for(int i = 0 ; i < uVector.length; ++i){
            nextFirstGraph = firstGraphPoints.size() <= secondGraphPoints.size();
            if(uVector[i] < UAvg)
                firstGraphPoints.add(i + 1);
            else if(uVector[i] > UAvg)
                secondGraphPoints.add(i + 1);
            else {
                if(nextFirstGraph)
                    firstGraphPoints.add(i + 1);
                else
                    secondGraphPoints.add(i + 1);

            }
        }

        //printMatrix(uVector,false);
        //System.out.println("Avg = " + UAvg);
        firstGraphPoints.forEach((elem)->System.out.print(elem + " "));
        System.out.println();
        secondGraphPoints.forEach((elem)->System.out.print(elem + " "));
        createGraphs(firstGraphPoints, secondGraphPoints);

    }

}
