package Ex1;

import java.util.Stack;

public class ComplexFunction implements complex_function {

    public function left  = null ,right = null, head = null;
    public Operation OP  = Operation.None;
    public static final double EPSILON = 0.0000001;


    public ComplexFunction(){
        head=left=right=null;
        OP = Operation.None;
    }

    public ComplexFunction(ComplexFunction cf){
        this.OP = cf.OP;
        this.left = cf.left;
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
    public ComplexFunction(Operation op, function left, function right){
        this.OP = op;
        this.left = left;
        this.right = right;
    }

    public ComplexFunction(function f) {
        ComplexFunction a = (ComplexFunction)f;
        this.OP = a.OP;
        this.head = a.head;
        this.left = a.left;
        this.right = a.right;
    }

    public void setInTree(function f1,Operation op){
        if(this.OP == Operation.None && this.left == null)
        {
            OP = op;
            left = f1;
        }
        else if(this.OP == Operation.None && this.right == null){
            this.OP = op;
            this.right = f1;
        }
        else
        {
            ComplexFunction f2 = new ComplexFunction(this.OP,this.left,this.right);
            this.left = f2;
            this.right = f1;
            this.OP = op;
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
        setInTree(f1,Operation.Max);
    }

    @Override
    public void min(function f1) {
        setInTree(f1,Operation.Min);
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

    public void setOP(Operation op){
        this.OP = op;
    }

    private  double sumf = 0;
    @Override
    public double f(double x) {
        if (this.OP == Operation.None && this.left == null && this.right == null) return 0;
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
                    double comply = left.f(x);
                    sumf = right.f(comply);
                    break;
                default:
                    break;
            }
        }
        else{
            sumf = this.f(x);
        }
        return sumf;

    }
    @Override
    public function initFromString(String s) {
        s = fixThisFileText(s);
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
            case "Times":
                return Operation.Times;
            case "div":
                return Operation.Divid;
            case "Divid":
                return Operation.Divid;
            case "plus":
                return Operation.Plus;
            case "Plus":
                return Operation.Plus;
            case "max":
                return Operation.Max;
            case "Max":
                return Operation.Max;
            case "min":
                return Operation.Min;
            case "Min":
                return Operation.Min;
            case "error":
                return Operation.Error;
            case "Error":
                return Operation.Error;
            case "comp":
                return Operation.Comp;
            case "Comp":
                return Operation.Comp;
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
    public boolean equals(Object cf){
        if(cf instanceof ComplexFunction || cf instanceof function){
            for (int i = 0 ; i < 500 ; i++) {
                try {
                    double f1 = this.f(i);
                    double f2 = ((function) cf).f(i);
                    if(Math.abs(f1-f2)>EPSILON) return false;
                } catch (Exception e) {
                    System.out.println("You cannot divide by 0" + i);
                }
            }
        }
        return true;
    }
    @Override
    public function copy() {
        String s = this.toString();
        ComplexFunction temp = new ComplexFunction();
        function f = temp.initFromString(s);
        return f;
    }
    private String fixThisFileText(String ans) {
        ans = ans.replaceAll(" ", "");
        return ans;
    }

    public static void main(String[] args) {
        ComplexFunction r = new ComplexFunction();
        String q = "mul(div(mul(8,8),4x^2),div(10,5))";
        String q1 = "mul(div(mul(8,8),4x^2),div(10,2))";
        ComplexFunction f  =(ComplexFunction) r.initFromString(q);
        ComplexFunction f1  =(ComplexFunction) r.initFromString(q1);
        System.out.println(f.equals(f1));
        System.out.println(f.f(1));
        function a = (f.copy());
        System.out.println(q.toString());
        System.out.println(a.toString());
        //System.out.println("ggg");
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