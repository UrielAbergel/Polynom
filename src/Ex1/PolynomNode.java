package Ex1;

public class PolynomNode {

    Monom mon;
    Polynom poly;
    Operation OP;
    function func;
    PolynomNode left = null , right =null , parent = null;
    double afterF=0;

    PolynomNode(Object p) {
        if(p instanceof Polynom){
            Polynom p1 = (Polynom)(p);
            new PolynomNode(p1);
        }
        else if(p instanceof Monom){
            Monom m1 = (Monom)(p);
            new PolynomNode(m1);
        }
        else if(p instanceof function){
                function f = (function)(p);
                new PolynomNode(f);
        }
        else if(p instanceof Operation){
            Operation O = (Operation)(p);
            new PolynomNode(O);
        }
    }
    PolynomNode(Polynom p){
        this.poly.PolynomList = p.PolynomList;
        OP = Operation.None;
        left = null;
        right = null;
    }
    PolynomNode(Monom m){
        OP = Operation.None;
        this.mon = m;
        left = null;
        right = null;
    }
    PolynomNode(function f){
        OP = Operation.None;
        this.func = f;
        left = null;
        right = null;
    }
    PolynomNode(Operation O){
        OP = O;
        left = null;
        right = null;
    }
    PolynomNode(function left, function right){
        this.left.func = left;
        this.right.func = right;
    }
    public void setOP(Operation op){
        this.OP = op;
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
}