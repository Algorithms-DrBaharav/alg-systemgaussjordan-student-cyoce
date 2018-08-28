
/**
 * Solves a linear system of equations, using Gauss elimination
 * 
 * @author Dr. Baharav
 */

public class GaussJordanSolver {



    
    /**
     * Holds all coefficients and results
     */
    
    private int n;              // number of unknowns
    private double[][] bigA;    // matrix of size n x (n+1)

    
    /**
     * @param a - matrix of coefficients
     * @param b - vector of results
     * 
     * Precondition: a[][] is a square matrix of size nxn, and b.length is n.
     * 
     * Deep copy parameters into the big array, combining coefficients and values.
     * 
     */
        
    public GaussJordanSolver(double[][] a, double[] b){
        
        n = a.length;
        
        bigA = new double[n][n+1];
        
        for (int rr=0; rr<n; ++rr) {
            for (int cc=0; cc<n; ++cc){
                bigA[rr][cc] = a[rr][cc];       
            }
         bigA[rr][n] = b[rr];
        }
    }
    
    /**
     * @return the two variables solution. If none, return null.
     */
    public double[] Solve(){

        
        // Gauss upper-right matrix creation
        
        // Iterate over all rows
        for (int rr=0; rr<n; ++rr) {
        
            // find the right pivoting row
            if (bigA[rr][rr] == 0){
                
                // need to switch with a row that does not have 0 there
                int r0 = rr;
                while (r0<n && bigA[r0][rr] ==0 ) ++r0;
                
                // check if we did NOT find a row to pivot on 
                if (r0==n)
                    return null;
                
                // If we found good one to serve as pivot, switch the rows.
                // will do it direct, rather than clone, as clone is 1D by nature.
                for (int ii=rr; ii<n+1; ++ii){
                    double tmp = bigA[rr][ii];
                    bigA[rr][ii] = bigA[r0][ii];
                    bigA[r0][ii] = tmp;
                }
            }
            
            // Normalize this one line
            double scale = bigA[rr][rr];   // it cannot be 0 here
            for (int cc=rr; cc<n+1 ; ++cc)
                bigA[rr][cc] /= scale;
            
            // Zero all the remaining lines
            // This is one of the two differences here for the Jordan one:
            // you do it for ALL the lines
            for (int rs=0 ; rs<n ; ++rs) {
                if (rs != rr) {
                    scale = bigA[rs][rr];
                    for (int cc=rr; cc<n+1 ; ++cc)
                        bigA[rs][cc] -= scale*bigA[rr][cc];
                }
            }
            
        }

        //  Here, the matrix should look ok!

        
        // back substituting (= solving)
        // The second difference: No need to back substitute.
        // It is simply the value!
        double[] x = new double[n];
        
        for (int rr=n-1 ; rr >=0 ; --rr) 
            x[rr] = bigA[rr][n];
        
        return x;
    }
    
        
    // Utility function
    static public void printMatrix(double[][] X,int minr, int minc, int maxr, int maxc, String str) {
        int rows=X.length ;
        int cols=X[0].length;

        maxr = Math.min(rows, maxr);
        maxc = Math.min(cols, maxc);
        
        System.out.println("\n% "+str);
        System.out.println("A=[...");
        for (int rr=minr; rr<maxr; ++rr) {
            for (int cc=minc; cc<maxc; ++cc) {
                System.out.format(" %5.2f ",X[rr][cc]);
            }   
            System.out.println();
        }
        System.out.println("];");
        
    }
    static public void printVector(double[] X,int minr, int maxr, String str) {
        int rows=X.length ;

        maxr = Math.min(rows, maxr);
        
        System.out.println("\n% "+str);
        System.out.print("b=[ ");
        for (int rr=minr; rr<maxr; ++rr) {
            System.out.format(" %5.2f ",X[rr]);
        }
        System.out.println("]; b=b';");
        System.out.println("x=A\\b; %y=reshape(x,rows,cols); imagesc(y)");
        
    }
    
}
