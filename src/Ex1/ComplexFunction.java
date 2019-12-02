package Ex1;

import java.util.Currency;

public class ComplexFunction implements complex_function {
    PolynomTree pt;
    PolynomNode current;

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
        return null;
    }

    @Override
    public double f(double x) {
        return 0;
    }

    @Override
    public function initFromString(String s) {
        return null;
    }

    @Override
    public function copy() {
        return null;
    }

    public static void main(String[] args) {
        Polynom p = new Polynom("3x^2");
        function t = p;
        ComplexFunction r = new ComplexFunction();
        r.plus(p);
    }
}
