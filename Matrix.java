// Calvin Vuong
// APCS1 pd5
// HW54 -- Red vs Blue
// 2016-01-05

/*====================================
  class Matrix -- models a square matrix

  TASK: Implement methods below.
        Categorize runtime of each. 
        Test in your main method.
  ====================================*/ 

public class Matrix {

    //constant for default matrix size
    private final static int DEFAULT_SIZE = 2;

    private Object[][] matrix;


    //default constructor intializes a DEFAULT_SIZE*DEFAULT_SIZE matrix
    public Matrix() {
	matrix = new Object[DEFAULT_SIZE][DEFAULT_SIZE];
    } //O(1)


    //constructor intializes an a*a matrix
    public Matrix( int a ) {
	matrix = new Object[a][a];
    } //O(1)


    //return size of this matrix, where size is 1 dimension
    private int size() {
	return matrix.length;
    } //O(1)


    //return the item at the specified row & column   
    private Object get( int r, int c ) {
	return matrix[r][c];
    } //O(1)


    //return true if this matrix is empty, false otherwise
    private boolean isEmpty( int r, int c ) {
	return get(r,c) == null;
    } //O(1)


    //overwrite item at specified row and column with newVal
    //return old value
    private Object set( int r, int c, Object newVal ) {
	Object old = get(r,c); //get old value
	matrix[r][c] = newVal; //set to new value
	return old;
    } //O(1)


    //return String representation of this matrix
    // (make it look like a matrix)
    public String toString() {
	String retStr = "";
	for ( Object[] r : matrix ){ //for each row in matrix
	    for ( Object o : r ){ //for each Object in row
		retStr += o + "\t"; //print Object
	    }
	    retStr += "\n"; //prepare for new row
	}
	return retStr;
    } //O(n^2)


    //override inherited equals method
    //criteria for equality: matrices have identical dimensions,
    // and identical values in each slot
    public boolean equals( Object rightSide ) {
	//check to make sure rightSide is a Matrix
	if (!( rightSide instanceof Matrix )){
	    return false;
	}
	
	Matrix other = (Matrix) rightSide; //type cast rightside into other

	//check to see if same dimensions
	if ( this.size() == other.size() && //check if # rows same
	     matrix.length == other.matrix.length ){ //if #rows is same
	    
	    //check to see if identical values in each slot
	    for (int i = 0; i < this.size(); i++){ //for each row #
		for (int k = 0; k < matrix[i].length; k++){ //for each column/index in this row
		    if (!( this.get(i,k).equals(other.get(i,k)) )){ //if vals at same index not equal
			return false;
		    }
		}
	    } //exits only if all vals match at proper indices
	    return true;
	}
	
	//line only executed if if block doesn't return anything
	return false;
    } //O(n^2)


    //swap two columns of this matrix 
    //(0,0) is top left corner of matrix
    //row values increase going down
    //column value increase L-to-R
    public void swapColumns( int c1, int c2  ) {
	for (int row = 0; row < size(); row++){ //loops through vals in column
	    set( row, c1, set( row, c2, get(row, c1) ) ); //swap vals
	}
    } //O(n)


    //swap two rows of this matrix 
    //(1,1) is top left corner of matrix
    //row values increase going down
    //column value increase L-to-R
    public void swapRows( int r1, int r2  ) {
	for (int col = 0; col < matrix[r1].length; col++){ //looop through vals in row
	    set( r1, col, set( r2, col, get(r1, col) ) ); //swap vals
	}
    } //O(n)


    //main method for testing
    public static void main( String[] args ) {
	
	Matrix M = new Matrix();
	//test return value of set()
	System.out.println(M.set(0,0, "hi") + "\n"); //should print out "null"
	M.set(0,1, 6);
	M.set(1,0, "bonjour");
	M.set(1,1, "hallo");
	System.out.println(M);
	
	//test equals aliasing
	Matrix L = M;
	System.out.println(M.equals(L) + "\n"); //true

	//test equals with "smaller" matrix	
	Matrix Q = new Matrix(1);
	Q.set(0,0, "hola");
	System.out.println(Q);
       	System.out.println(M.equals(Q) + "\n"); //false

	//test equals with "identical larger" matrix
	Matrix P = new Matrix(3);
	P.set(0,0, "hi");
	P.set(0,1, "hola");
	P.set(1,0, "bonjour");
	P.set(1,1, "hallo");
	System.out.println(P);
	System.out.println(M.equals(P) + "\n"); //false

	//test equals for identical matrix
	Matrix M1 = new Matrix();
	M1.set(0,0, "hi");
	M1.set(0,1, 6);
	M1.set(1,0, "bonjour");
	M1.set(1,1, "hallo");
	System.out.println(M1);
	System.out.println(M.equals(M1)); //true

	Matrix S = new Matrix(4);
	S.set(0,0, 6);
	S.set(0,2, 89);
	S.set(0,3, 99);
	S.set(1,1, 0);
	S.set(1,2, 8);
	S.set(2,0, 23);
	S.set(3,0, 1);
	S.set(3,1, 66);
	S.set(3,2, 9);
	S.set(3,3, 5);
	System.out.println(S);
	
	//test row swap
	S.swapRows(1,2); //swap 2nd and third rows
	System.out.println(S);
	S.swapRows(2,2); //no change
	System.out.println(S);

	//test column swap
	S.swapColumns(0,2); //swap first and third columns	
	System.out.println(S);
	S.swapRows(3,3); //no change
	System.out.println(S);

    }

}//end class Matrix
