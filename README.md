# Project ALG_SystemGaussJordan

Solve general set of equations using Gauss-Jordan method.

Two differences from Gauss elimination:
1. When subtracting the current row from others, you do not subtract only from 
the following rows, but ALSO from previous rows. This creates a diagonal matrix, 
which is the unit matrix (only ones along the diagonal).
2. When solving, no need to back-substitute. Each variable is equal to the 
corresponding element in the solution vector.

What you need to do here:
a. Modify our Gauss algorithm to be Gauss-Jordan  : See the solve() function, where you have //your code here
b. Add a 4x4 test case: You need to construct a set of 4 equations with 4 unknowns.  // In the test file
 
In general:
* Gauss elimination - creates an upper-right matrix, and solves by substitution from the bottom.
* Gauss-Jordan - Creates a unit matrix, and the values are given (no need to solve). 
Additional benefit: Inverse matrix. Disadvantage: More time and complex.


---




