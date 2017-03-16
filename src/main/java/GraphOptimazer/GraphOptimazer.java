package GraphOptimazer;
import cern.colt.function.DoubleDoubleFunction;
import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.EigenvalueDecomposition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;


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
        printMatrix(graph, true,true);
    }

    public void fillGraph() {

        /*
       new ArrayList<Position>() {
            {

                // normal graph
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
            /*
           // without 4 point
           add(new Position(1, 6));
           add(new Position(2, 3));
           add(new Position(2, 7));
           add(new Position(3, 2));
           add(new Position(3, 8));
           add(new Position(4, 10));
           add(new Position(5, 6));
           add(new Position(5, 12));
           add(new Position(6, 5));
           add(new Position(6, 1));
           add(new Position(6, 13));
           add(new Position(7, 2));
           add(new Position(7, 8));
           add(new Position(7, 14));
           add(new Position(8, 7));
           add(new Position(8, 9));
           add(new Position(8, 3));
           add(new Position(9, 8));
           add(new Position(9, 10));
           add(new Position(10, 4));
           add(new Position(10, 9));
           add(new Position(11, 12));
           add(new Position(12, 11));
           add(new Position(12, 13));
           add(new Position(12, 5));
           add(new Position(13, 6));
           add(new Position(13, 12));
           add(new Position(13, 14));
           add(new Position(14, 7));
           add(new Position(14, 13));
       }
        }.forEach((elem)->{
            graph[elem.getRowIndex() - 1][elem.getColumnIndex() - 1] = 1.0;
        });


        */


        new ArrayList<Position>() {
            {
                add(new Position(1, 2));
                add(new Position(1, 3));
                add(new Position(2, 1));
                add(new Position(2, 4));
                add(new Position(3, 4));
                add(new Position(3, 1));
                add(new Position(3, 6));
                add(new Position(4, 2));
                add(new Position(4, 3));
                add(new Position(4, 7));
                add(new Position(5, 6));
                add(new Position(6, 3));
                add(new Position(6, 5));
                add(new Position(6, 7));
                add(new Position(7, 6));
                add(new Position(7, 4));
                add(new Position(7, 16));
                add(new Position(8, 12));
                add(new Position(8, 9));
                add(new Position(9, 8));
                add(new Position(9, 10));
                add(new Position(9, 13));
                add(new Position(10, 9));
                add(new Position(10, 11));
                add(new Position(10, 14));
                add(new Position(11, 10));
                add(new Position(11, 15));
                add(new Position(12, 8));
                add(new Position(12, 16));
                add(new Position(12, 13));
                add(new Position(13, 12));
                add(new Position(13, 14));
                add(new Position(13, 9));
                add(new Position(14, 13));
                add(new Position(14, 15));
                add(new Position(14, 10));
                add(new Position(15, 14));
                add(new Position(15, 11));
                add(new Position(16, 7));
                add(new Position(16, 12));
            }
        }.forEach((elem)->{
            graph[elem.getRowIndex() - 1][elem.getColumnIndex() - 1] = 1.0;
        });

    }

    public void createGraphs(ArrayList<Integer> firstGraphPoints, ArrayList<Integer> secondGraphPoints){

    }

    public void printMatrix(double[][] matrix, boolean useColorizedOutput, boolean useFormatter) {
        final AtomicInteger idx = new AtomicInteger();
        System.out.format("%5s", "");
        for(int i = 0; i < matrix.length; ++i)
            System.out.format("%5s", i + 1);
        System.out.println();
        Arrays.stream(matrix).forEach((row) -> {
            System.out.format("%5s", idx.incrementAndGet() + " |");
            Arrays.stream(row).forEach(elem -> {
                if (useFormatter) {
                    if (elem == 1.0) {
                        if (useColorizedOutput) {
                            System.out.print("\u001B[31m");
                            System.out.format("%5.1f", elem);
                            System.out.print("\u001B[0m");
                        } else
                            System.out.format("%5.1f", elem);
                    } else
                        System.out.format("%5.1f", elem);
                }
                else{
                    if (elem == 1.0) {
                        if (useColorizedOutput) {
                            System.out.print("\u001B[31m");
                            System.out.print(elem);
                            System.out.print("\u001B[0m");
                        } else
                            System.out.print(elem);
                    } else
                        System.out.print(elem);
                }
            });
            System.out.println();
        });
    }

    public void printMatrix(double[] matrix, boolean useColorizedOutput, boolean useFormatter){
        final AtomicInteger idx = new AtomicInteger();
        Arrays.stream(matrix).forEach((elem) -> {
            System.out.format("%5s" , + idx.incrementAndGet() +  " |");
            if (useFormatter) {
                if (elem == 1.0) {
                    if (useColorizedOutput) {
                        System.out.print("\u001B[31m");
                        System.out.format("%5.1f%n", elem);
                        System.out.print("\u001B[0m");
                    } else
                        System.out.format("%5.1f%n", elem);
                } else
                    System.out.format("%5.1f%n", elem);
            }
            else{
                if (elem == 1.0) {
                    if (useColorizedOutput) {
                        System.out.print("\u001B[31m");
                        System.out.println(elem);
                        System.out.print("\u001B[0m");
                    } else
                        System.out.println(elem);
                } else
                    System.out.println(elem);
            }
        });
        System.out.println();
    }

    public void printMatrix(DoubleMatrix2D matrix, boolean useColorizedOutput, boolean useFormatter) {
        System.out.format("%5s", "");
        for(int i =0; i < matrix.columns(); ++i)
            System.out.format("%5s", i + 1);
        System.out.println();
        for (int i = 0; i < matrix.rows(); ++i) {
            System.out.format("%5s" ,i + 1 + " |");
            for (int j = 0; j < matrix.columns(); ++j) {
                if (useFormatter) {
                    if (matrix.get(i,j) != 0.0) {
                        if (useColorizedOutput) {
                            System.out.print("\u001B[31m");
                            System.out.format("%5.1f", matrix.get(i,j));
                            System.out.print("\u001B[0m");
                        } else
                            System.out.format("%5.1f", matrix.get(i,j));
                    } else
                        System.out.format("%5.1f", matrix.get(i,j));
                }
                else{
                    if (matrix.get(i,j) == 1.0) {
                        if (useColorizedOutput) {
                            System.out.print("\u001B[31m");
                            System.out.print(matrix.get(i,j));
                            System.out.print("\u001B[0m");
                        } else
                            System.out.print(matrix.get(i,j));
                    } else
                        System.out.print(matrix.get(i,j));
                }
            }
            System.out.println();
        }
    }

    public void printMatrix(DoubleMatrix1D matrix, boolean useColorizedOutput, boolean useFormatter){
        for(int i = 0; i < matrix.size(); ++i) {
            if(matrix.get(i) != 0.0)
                System.out.format("%5s" ,i + 1 + " | ");
                if (useFormatter) {
                    if (matrix.get(i) == 1.0) {
                        if (useColorizedOutput) {
                            System.out.print("\u001B[31m");
                            System.out.format("%5.1f%n", matrix.get(i));
                            System.out.print("\u001B[0m");
                        } else
                            System.out.format("%5.1f", matrix.get(i));
                    } else
                        System.out.format("%5.1f%n", matrix.get(i));
                }
                else{
                    if (matrix.get(i) == 1.0) {
                        if (useColorizedOutput) {
                            System.out.print("\u001B[31m");
                            System.out.println(matrix.get(i));
                            System.out.print("\u001B[0m");
                        } else
                            System.out.println(matrix.get(i));
                    } else
                        System.out.println(matrix.get(i));
                }
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
        printMatrix(LMatrix, true, true);

        EigenvalueDecomposition eigenvalueDecomposition = new EigenvalueDecomposition(LMatrix);
        DoubleMatrix1D UVector = eigenvalueDecomposition.getV().viewColumn(1);
        //printMatrix(eigenvalueDecomposition.getD(),true);
        //printMatrix(UVector, false);
        double UAvg = Arrays.stream(UVector.toArray()).sum() / UVector.size();
        ArrayList<Integer> firstGraphPoints = new ArrayList<>();
        ArrayList<Integer> secondGraphPoints = new ArrayList<>();
        double[] uVector = UVector.toArray();
        System.out.println("UAvg = " + UAvg);
        System.out.println("UVector = ");
        printMatrix(UVector,false,false);
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
