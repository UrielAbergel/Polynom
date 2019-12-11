# ![polynom-logo](https://user-images.githubusercontent.com/54840897/68753489-cce32080-060d-11ea-87a8-793152128a46.png)

## descreption
The project creates a data structure that can contain complex functions, and also provides a GUI for the user to calculate complex functional problems, such as cutting axes, extremes points, and multiplicity of functions.
   
 #### Example of proper Complex Function input:"Plus(Divid(1.0x+1.0x,Times(1.0x-2.0,1.0x^1-4.0)),2)"

.
#### The graphical user interface : 
![גרףףףףףףףףףף](https://user-images.githubusercontent.com/54840897/70615327-d9f72d80-1c14-11ea-9a06-05620697931b.PNG)




## Monom class
Use a monom from the format of a*x^b.

   Note: We assumed that the input is valid
   a*x^b

#### Monom algorithm:
at the beginning of the algorithm, The algorithm has an option to
produce Monom with a standard constructor that not receiving value, and
constructor who gets two String and creating a Monom. the algorithm receiving
String and distribute it according to the characters, so when at the end of the
reading of the string it generates two entries in one of the holdings of the Int and the
other promotes a Double. String correctly looks form "3.4x^5". 


## Polynom class
Use a ploynom from the format of a*x1^b + a*x2^b + .... + a*xn^b.

   Note: We assumed that the input is valid
   a*x1^b + a*x2^b + .... + a*xn^b

####  Polynom algorithm:
The polynomial algorithm uses a Linkedlist data structure. The algorithm has two
constructors, with one non-value constructor generating only the data structure.
And the second receives a String consisting of connecting and subtracting monoms,
the algorithm breaks down the monoms, sends them to a monom department
builder, and inserts the return object into the LinkedList. At the end of the run, the
algorithm sorts the LinkedList and the organ insertion.

The class polynom supports some functions as:
- add - there is two options to add function add Monom/Polynom(useing Polynom_able) . 
- derivative 
- f(x)
- multiply 
- subtract 
- root - Finds the point of intersection of the polynomial in the given range
- area - Returns the value of the area in the given range
- copy
- is equal 
- is zero

## Complex Function class
A function complex is a class that deals with functions that are more complex than a simple polynomial.

#### Complex Function algorithm:
The algorithm builds a tree, as a binary tree, where each operator is located and connects two functions, or two complex functions.
In any operation such as calculation, it recursively traverses the tree, the calculation or transition is performed in the form of a pre-order

The class polynom supports some functions as:
- constructor:A constructor has many ways to build when it is possible to put two functions.
   Or two function complex, there is an option of putting operator and two functions 
- Plus:Performs a Plus operation on two functions
- Times:Performs a multiplication operation on two functions
- Divid:Performs a Divid operation on two functions
- Max:check which two function have a bigger max point
- Min : check which two function have a minum min point
- Comp:Performs function-by-function assembly F (G (x))
- f(x) -Calculates the value of the complex function at a point
- is equal 

## GUI class

The GUI class builds a user image that simulates the function by X axis and Y axis

An image can be produced by three functions
1) A function that receives parameters from JSON
2) Static function that receives user parameters
3) Function that builds without parameters

The user-generated image can be seen in the example above
