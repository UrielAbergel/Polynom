# ![polynom-logo](https://user-images.githubusercontent.com/54840897/68753489-cce32080-060d-11ea-87a8-793152128a46.png)

## descreption
The project can create a monom and a polynomial. Exporting them to a picture that shows the polynomial as a function, the program allows, among other things, to do mathematical operations between polynomial and monom.
Calculate the area, and calculate the intersection points with the X-axis

   a*x1^b + a*x2^b + .... + a*xn^b
   a -> incomplete number
   b -> Integer  

.
#### Polynom Graphically displayed
![a](https://user-images.githubusercontent.com/44754325/50425276-adb79500-087b-11e9-80cf-c1a4b29ace8b.png)


### Monom class
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


### Polynom class
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


