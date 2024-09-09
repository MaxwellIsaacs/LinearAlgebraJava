import java.util.function.Function;
import java.util.Random;

public class Matrix {
  
  private double elements[][];
  private int rows;
  private int columns;


  public Matrix (int rows, int columns) {
    this.elements = new double [rows][columns];
    this.rows = rows;
    this.columns = columns; 
  }
  
  // gets rows
  public int getX () {
    return rows;
  }

  // gets columns
  public int getY () {
    return columns;
  }

  // returns individual element
  public double getElement (int x, int y) {
    if (x > rows) {
      Error.outOfBound("Get element", 'x');
      return -1;
    } 
    if (y > columns) {
      Error.outOfBound("Get element", 'y');
      return -1;
    }
    return elements[x][y];
   } 

  // sets individual element
  public void setElement (int x, int y, double value) {
    elements[x][y] = value;
  }
  
  // sets entire elements list
  public void setElements (double elements[][]) {
    this.elements = elements;
  }

  public double[][] getElements () {
    return elements;
  }

  // returns shape as a custom pair class
  public Pair <Integer, Integer> getShape () {
    return new Pair<>(rows, columns);
  }

  // returns a new sum matrix
  public Matrix add (Matrix b) {
    Pair<Integer, Integer> otherShape = b.getShape();
    if (rows != otherShape.getFirst () || columns != otherShape.getSecond ()) {
      Error.incorrectMatrixShape ("Matrix addition");
    } 

    Matrix temp = new Matrix (rows, columns);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; i < columns; j++) {
        double tempVal= elements[i][j] + b.getElement (i, j);
        temp.setElement (i, j, tempVal);
      }
    }
    return temp;
  }

  // returns a new sum matrix between a matrix and a scalar
  public Matrix addScalar (double s) {
    Matrix temp = new Matrix (this.rows, this.columns);
    for (int i = 0; i < rows; i++){
      for (int j = 0; j < columns; j++) {
        temp.setElement (i, j, this.elements[i][j] + s);
      }
    }
    return temp;
  }

  // returns a new product matrix
  public Matrix multiply (Matrix b) { 
    if (b.getX () != columns) { 
      Error.incorrectMatrixShape ("Matrix multiply");
    }
    Pair<Integer, Integer> newShape = new Pair<>(getX(), b.getY());
    Matrix newMatrix = new Matrix (newShape.getFirst(), newShape.getSecond()); 
    double newElements[][] = new double[newShape.getFirst()][newShape.getSecond()];

    for (int i = 0; i < newShape.getFirst(); i++) {
      for (int j = 0; j < newShape.getSecond(); j++) {
        double sum = 0;
        for (int k = 0; k < columns; k++) {
          sum += elements[i][k] * b.getElement (k, j);
        }          
        newElements[i][j] = sum;
      } 
    }
    newMatrix.setElements (newElements);
    return newMatrix;
  }

  // transposes current matrix in place
  public void transpose () {
    double temp[][] = new double [columns][rows];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++){
        temp[j][i] = elements[i][j];
      }
    }
    elements = temp;
    int tempRows = rows;
    this.rows = columns;
    this.columns = tempRows;
  }

  // returns a new difference matrix 
  // public 

  // returns the determinant of a square matrix
  // public
  //

  //argmax
  public Pair<Integer, Integer> argmax () {
    double max = this.getElement (0, 0);
    
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        max = Math.max (this.getElement (i, j), max);
      }
    }
    return max;
  }

  // apply function to each element of the matrix
  public Matrix applyFunction (Function <Double, Double> function) {
    Matrix temp = new Matrix (rows, columns);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        double tempVal = function.apply (elements[i][j]);
        temp.setElement (i, j, tempVal);
      }
    }
    return temp;
  }
  

  // returns randomized elements array (for neural networks)
  public static Matrix randomMatrix (int rows, int columns, int min, int max) {

    Matrix randMatrix = new Matrix (rows, columns);
    Random random = new Random ();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        randMatrix.setElement (i, j, min + (max- min) * random.nextDouble ());
      }
    }
    return randMatrix;
  }


  public void print () {
    System.out.println("Matrix: ");
    int cols = elements[0].length;
    int rows = elements.length;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        System.out.print(elements[i][j] + " ");
      }
      System.out.println();
    }
  }


  public void printShape () {
    System.out.print("Matrix Shape: " + rows + ", " + columns);
  }
}

