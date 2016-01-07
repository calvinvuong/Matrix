// Team Human Batteries -- Alan Chen and Calvin Vuong
// APCS1 pd5
// HW55 -- Don't Think You Are. Know You Are.
// 2015-01-06

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

    //*****NEW METHODS HERE FOR HW55
    //returns values in row r as an array
    public Object[] getRow(int r){
	return matrix[r];
    } //O(1)
	
    //returns values in column c as an array
    public Object[] getCol(int c){
	Object[] col = new Object[this.size()];
	for (int i = 0; i < this.size(); i++){ //for each row
	    col[i] = this.get(i, c); //add val in specified column to return array
	}
	return col;
    } //O(n)	
	
    //takes row number r and an array newRow
    //sets row r to the values in newRow
    //returns old row before change
    public Object[] setRow(int r, Object[] newRow){
	Object[] tmp = this.getRow(r);
	matrix[r] = newRow;
	return tmp;
    } //O(1)
	
    //takes column number c and an array newCol
    //sets column c to the values in newCol
    //returns old column before change
    public Object[] setCol(int c, Object[] newCol){
	Object[] temp = this.getCol(c);
	for (int i = 0; i < this.size(); i++) { //for each row
	    this.set(i,c, newCol[i]); //set new val to val found in newCol at row index
	}
	return temp;
    } //O(n)
	
    //return true if matrix is full
    public boolean isFull(){
	//loops through each element
	for (int row = 0; row < matrix.length; row++){
	    for (int col = 0; col < matrix[0].length; col++){
		if (matrix[row][col] == null){
		    return false; //not full if object is null
		}
	    }
	}
	return true;
    } //O(n^2)
		
    //returns true if Object o is in this array
    //false otherwise
    public boolean contains(Object o){
	for (int row = 0; row < this.size(); row++) {
	    for (int col = 0; col < this.size(); col++) {
		if (!isEmpty(row, col) && this.get(row,col).equals(o)) //if object found in this position
		    return true;
	    }
	}
	return false;
    } //O(n^2)
	
    //transposes a matrix
    //algo: draw diagonal from top left to bottom right
    //      swap values above that diagonal with element w/ switched row/col 
    public void transpose() {
	for (int row = 0; row < this.size(); row++) {
	    for (int col = 0; col < this.size(); col++) {
		if ( row < col ){ //only swap vals above diagonal
		    //swap
		    Object tmp = this.get(row,col);
		    this.set(row, col, this.get(col, row));
		    this.set(col, row, tmp);
		}
	    }
	}
    } //O(n^2)
	

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
		    if (!( !isEmpty(i,k) && this.get(i,k).equals(other.get(i,k)) )){ //if vals at same index not equal
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
	
	//test isFull()
	System.out.println("is M full?\n" + M + M.isFull()); //true
	System.out.println("is S full?\n" + S + S.isFull()); //false

	//test contains()
	System.out.println("this is M:\n" + M);
	System.out.println("does M contain bonjour? " + M.contains("bonjour")); //true
	System.out.println("does M contain bonjourno? " + M.contains("bonjourno") + "\n"); //false
	
	//test setRow()
	System.out.println("Setting row 1 of M to ['adieu', 'wiedersehen']");
	Object[] newRow = {"adieu", "wiedersehen"};
	M.setRow(1, newRow);
	System.out.println("New M:\n" + M);
	
	//test setColumn()
	System.out.println("Setting column 0 of M to ['selina', 'meyer']");
	Object[] newColumn = {"selina", "meyer"};
	M.setCol(0, newColumn);
	System.out.println("New M:\n" + M);
	
 	//test transpose()
	System.out.println("M before transposition:\n" + M);
	M.transpose();
	System.out.println("M after transposition:\n" + M);

    }
}//end class Matrix
