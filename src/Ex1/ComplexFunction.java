package Ex1;

import java.util.Currency;
import  java.util.Stack;

public class ComplexFunction implements complex_function {
    PolynomTree pt;
    PolynomNode current;
    int flag = 0 ;

    public ComplexFunction(){
        this.pt= new PolynomTree();
    }
    public ComplexFunction(ComplexFunction cf){
        this.initFromString(cf.pt.SaveToFileReader);
    }
    public ComplexFunction(String operation, Polynom p1, Polynom p2){
        Operation op = ReturnOpString(operation);
        this.pt = new PolynomTree();
        this.pt.root = new PolynomNode("0");
        this.pt.root.OP = op;
        this.pt.root.left = new PolynomNode(p1);
        this.pt.root.right = new PolynomNode(p2);
    }
    public ComplexFunction(Polynom p1){
        this.pt = new PolynomTree();
        this.pt.root = new PolynomNode(p1);
    }
    public ComplexFunction( String operation , Polynom p , ComplexFunction cf){
        Operation op = ReturnOpString(operation);
        this.pt = new PolynomTree();
        this.pt.root = new PolynomNode("0");
        this.pt.root.OP = op;
        this.pt.root.left = new PolynomNode(p);
        this.pt.root.right = new PolynomNode(cf);
    }
    public ComplexFunction( String operation , ComplexFunction cf , Polynom p){
        Operation op = ReturnOpString(operation);
        this.pt.root.OP = op;
        this.pt.root.right.poly = p;
        this.pt.root.left.func = cf;
    }
    public ComplexFunction( String operation , ComplexFunction cf1 , ComplexFunction cf2){
        Operation op = ReturnOpString(operation);
        this.pt.root.OP = op;
        this.pt.root.right.func = cf1;
        this.pt.root.left.func = cf2;
    }

    public ComplexFunction(function f) {
        ComplexFunction cf = new ComplexFunction();
        cf = (ComplexFunction)f;
        this.pt = cf.pt;
    }


    public void setInTree(function f1 , Operation op){
        PolynomNode p = new PolynomNode(op);
        PolynomNode p1 = new PolynomNode(this);
        if(pt.root==null) {
            pt.root = p;
            p.left = new PolynomNode(f1);
            p.right = p1;
        }
        else {
            p.left = pt.root;
            pt.root.right =p1;
            pt.root = p;
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
        return current.left.func;
    }

    @Override
    public function right() {
        return current.right.func;
    }

    @Override
    public Operation getOp() {
        return this.pt.root.OP;
    }

    @Override
    public double f(double x) {
        return recursiveF(x, this.pt.root);
    }
    private double sumf=0;
    private double recursiveF(double x, PolynomNode p){
        if(p.OP == Operation.None && p.func == null && p.poly == null && p.mon == null) return 0;
        if(p.OP != Operation.None ){
            switch(p.OP) {
                case Times:
                    sumf = recursiveF(x,p.left)*recursiveF(x,p.right);
                    break;
                case Divid:
                    sumf = recursiveF(x,p.left)/recursiveF(x,p.right);
                    break;
                case Plus:
                    sumf = recursiveF(x,p.left)+recursiveF(x,p.right);
                    break;
                case Max:
                    sumf = Math.max(sumf += recursiveF(x,p.left),recursiveF(x,p.right));
                    break;
                case Min:
                    sumf = Math.min(sumf += recursiveF(x,p.left),recursiveF(x,p.right));
                    break;
                case Error:
                    throw new NullPointerException("Error");
                case Comp:
                    sumf = recursiveF(p.right.func.f(x),p.left);
                    break;
                default:
                    throw new NullPointerException("Illegal Operation");
            }
        }
        else{
            if(p.func!=null) {
                return p.func.f(x);
            }
            else if(p.poly!=null){
                return p.poly.f(x);
            }
        }
        return sumf;
    }

    @Override
    public function initFromString(String s) {
        ComplexFunction p = new ComplexFunction();
        p.pt = new PolynomTree();
        p.pt.root = new PolynomNode(Operation.None);
        RecursiveInitFromString(p.pt.root,s);
        parent(p.pt.root);
        p.pt.root = current;
        return p;
    }
    private static void parent(PolynomNode answer) {
        if(answer==null) return;
        if(answer.left!=null) answer.left.parent = answer;
        if(answer.right!=null) answer.right.parent = answer;
        parent(answer.left);
        parent(answer.right);
    }
    public void RecursiveInitFromString(PolynomNode Pnode, String s){
        if(flag == 0) { current =Pnode;}
        flag++;
        if(!s.contains(",")){
            Pnode.func = new Polynom(s);
            return;
        }
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)== '(' ) {
                temp =  s.substring(0,i);
                if(!CheackSograim(temp)) return;
                Operation op = ReturnOpString(temp);
                Pnode.setOP(op);
                int psik = findPsik(s);
                if(psik != 0) {
                    Pnode.left = new PolynomNode(Operation.None);
                    Pnode.right = new PolynomNode(Operation.None);
                    RecursiveInitFromString(Pnode.left, s.substring(i + 1, psik));
                    RecursiveInitFromString(Pnode.right, s.substring(psik + 1, s.length() - 1));
                }
            }
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

    @Override
    public function copy() {
        function f = new ComplexFunction();
        String s = this.pt.SaveToFileReader;
        f = f.initFromString(s);
        return f;
    }
//    public void recursiveCopy(PolynomNode p){
//        if(p == null) return;
//        if (p.OP == null && p.func == null && p.poly == null){
//            return;
//        }
//        if(p.OP!=null) this.pt.root = new PolynomNode(p.OP);
//        else if(p.poly!=null) this.pt.root = new PolynomNode(p.poly);
//        else if(p.func!=null) this.pt.root = new PolynomNode(p.func);
//        recursiveCopy(p.right);
//        recursiveCopy(p.left);
//    }

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

    public String toStringOP(Operation op){
    switch (op){
        case Plus:
            return "plus(";
        case Divid:
            return "div(";
        case Times:
            return  "mul(";
        case Comp:
            return  "comp(";
        case Max:
            return "max(";
        case Min:
            return  "min(";
        case None:
            return "";
        case Error:
            return "";
    }
        return null;
    }



    public static void main(String[] args) {
        ComplexFunction r = new ComplexFunction();
        String q = "mul(div(mul(8,8),4x^2),div(10,5))";
        r = (ComplexFunction) r.initFromString(q);
        r.pt.printInOrder();
       // double x = r.f(1);
      //  System.out.println(x);
//        System.out.println("t");
//        ComplexFunction s = new ComplexFunction(r.copy());
       // s.pt.printInOrder();
    }
}
