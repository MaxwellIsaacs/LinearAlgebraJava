
public class Main {
  public static void main(String[] args) {

    Matrix matrix = new Matrix (2, 3);
    double newMatrix[][] = {
      {3, 4, 7},
      {2, 1, 9},
    };

    Matrix matrix2 = Matrix.randomMatrix (3, 4, 1, 10);

    matrix.setElements (newMatrix);
    matrix.print ();
    matrix.printShape ();
    System.out.println();
    
    matrix2.print ();
    System.out.println();
    
    System.out.println();

    matrix.transpose();
    matrix.print ();
    matrix.printShape ();
    System.out.println();
    System.out.println();

    Vector vector = new Vector (2);
    double newVector[] = {1, 2};
    
    vector.setElements (newVector);
    vector.print ();
    System.out.println(); 
    System.out.println();

    System.out.println(vector.getL2Norm());
    vector = vector.scalarProduct (2.0);
    vector.print (); 
    System.out.println();
    System.out.println();
  }
}

