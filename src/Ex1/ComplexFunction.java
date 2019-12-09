package Ex1;

import java.util.Currency;
import  java.util.Stack;

public class ComplexFunction implements complex_function {

    function left  = null ,right = null, head = null;
    Operation OP  = Operation.None;


    public ComplexFunction(){
        head=left=right=null;
        OP = Operation.None;
    }

    public ComplexFunction(ComplexFunction cf){
        this.OP=cf.OP;
        this.left=cf.left;
        this.right = cf.right;
        this.head = cf.head;
    }

    public ComplexFunction(String operation, function p1, function p2){
        Operation op = ReturnOpString(operation);
        this.OP = op;
        this.left = p1;
        this.right = p2;
    }

    public ComplexFunction(Polynom p1){
        function left = p1;
        this.left = left;

    }

    public ComplexFunction( String operation , function p , ComplexFunction cf){
        Operation op = ReturnOpString(operation);
        this.OP = op;

        this.left = p ;
        this.right =cf ;
    }


    public ComplexFunction( String operation , ComplexFunction cf , Polynom p){
        Operation op = ReturnOpString(operation);
        this.OP = op;
        function right = p ;
        this.right = right;
        this.left =cf ;
    }

    public ComplexFunction( String operation , ComplexFunction cf1 , ComplexFunction cf2){
        Operation op = ReturnOpString(operation);
        this.OP = op;
        this.left= cf1;
        this.right = cf2;
    }

    public ComplexFunction(function f) {
        this.left = f ;
    }

    public void setInTree(function f1,Operation op){
        if(this.OP == Operation.None)
        {
            OP = op;
            left = f1 ;
        }
        else
        {
            ComplexFunction cf = new ComplexFunction();
            cf.left = this;
            cf.right = f1;
            cf.OP = op;
            this.head = cf;
        }


    }
    @Override
    public void plus(function f1) {
        setInTree(f1,Operation.Plus);
    }

    @Override
    public void mul(function f1) {
        setInTree(f1,Operation.Times);
    }

    @Override
    public void div(function f1) {
        setInTree(f1,Operation.Divid);
    }

    @Override
    public void max(function f1) {

    }

    @Override
    public void min(function f1) {
        setInTree(f1,Operation.Max);
    }

    @Override
    public void comp(function f1) {
        setInTree(f1,Operation.Comp);
    }

    @Override
    public function left() {
        return this.left;
    }

    @Override
    public function right() {
        return this.right;
    }

    @Override
    public Operation getOp() {
        return this.OP;
    }
    private  double sumf = 0;
    @Override
    public double f(double x) {
        if (this == null) return 0;
        if (OP != Operation.None) {
            switch (OP) {
                case Times:
                    sumf = left.f(x) * right.f(x);
                    break;
                case Divid:
                    sumf = left.f(x) / right.f(x);
                    break;
                case Plus:
                    sumf = left.f(x) + right.f(x);
                    break;
                case Max:
                    sumf = Math.max(left.f(x), right.f(x));
                    break;
                case Min:
                    sumf = Math.min(left.f(x), right.f(x));
                    break;
                case Error:
                    throw new NullPointerException("Error");
                case Comp:
                    double comply = right.f(x);
                    sumf = left.f(comply);
                    break;
                default:
                    this.left.f(x);
                    break;
            }
        }
        return sumf;

    }


    @Override
    public function initFromString(String s) {
        ComplexFunction tempComplex = new ComplexFunction();
        int Psik =findPsik(s);
        if(Psik == 0)
        {
            function f = new Polynom(s);
        }
        function f = tempComplex.initFromStringRec(s);
      return f;
    }

    public function initFromStringRec(String s){
        if(!s.contains(",")){
            return new Polynom(s);
        }
        String TempString ="";
        int i = s.indexOf("(");
        TempString =  s.substring(0,i);
       if(!CheackSograim(TempString)) return null;

        int psik = findPsik(s);

        function Poly1 =   initFromStringRec(s.substring(i + 1, psik));
        function Poly2 =   initFromStringRec(s.substring(psik + 1, s.length() - 1));

        function Create =  new ComplexFunction(TempString,Poly1,Poly2);
        return Create;
    }



    public static Operation ReturnOpString(String s) {
        switch(s) {
            case "mul":
                return Operation.Times;

            case "div":
                return Operation.Divid;

            case "plus":
                return Operation.Plus;

            case "max":
                return Operation.Max;

            case "min":
                return Operation.Min;

            case "error":
                return Operation.Error;

            case "comp":
                return  Operation.Comp;

            default:
                return Operation.None;

        }
    }


    private static int findPsik(String s) {
        if(!CheackSograim(s)) return 0;
        Stack stack = new Stack();
        boolean flag = false;
        int ans = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '(') {
                if(flag){
                    stack.push('(');
                }
                flag=true;
            }
            if (s.charAt(i) == ')') stack.pop();
            if (s.charAt(i) == ',') {
                if (stack.isEmpty()) ans = i;
            }
        }
        return ans;
    }


    public static Boolean CheackSograim(String s){
        Stack stack = new Stack();
        for (int i = 0; i < s.length() ; i++) {
            if(s.charAt(i) == '(') stack.push('(');
            else if(s.charAt(i) == ')' && !stack.isEmpty())  stack.pop();
            else if(s.charAt(i) == ')' && stack.isEmpty()) return false;
        }
        if(!stack.isEmpty()) return false;
        return true;
    }




    @Override
    public String toString(){
       return this.OP + "(" + this.left + "," + this.right +")";
    }



    @Override
    public function copy() {
        function f = new ComplexFunction(this);
        return f;
    }

    public void setOP(Operation op){
        this.OP = op;
    }




//=============================================================

    public void printInOrder() {
        printInOrder(this);
        System.out.println();
    }

    private void printInOrder(ComplexFunction cf) {//PreOrder
        if (cf != null) {
            printInOrder((ComplexFunction)cf.left);
            System.out.print(cf.OP + ", ");
            printInOrder((ComplexFunction)cf.right);
        }
    }


    public static void main(String[] args) {
        ComplexFunction r = new ComplexFunction();
        String q = "mul(div(mul(8,8),4x^2),div(10,5))";


       function f  = r.initFromString(q);
        System.out.println(f.toString());
        System.out.println("ggg");
        // r.printInOrder();
        // double x = r.f(1);
        //  System.out.println(x);
        // System.out.println("t");

        //ComplexFunction s = (ComplexFunction)r.copy();
        //   ComplexFunction s = new ComplexFunction(r.copy());

//        System.out.println("t");
//        ComplexFunction s = new ComplexFunction(r.copy());
        // s.pt.printInOrder();
    }
}