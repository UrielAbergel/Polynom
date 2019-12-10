package Tests;

import Ex1.ComplexFunction;
import Ex1.function;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexFunctionTest {

    @Test
    public void plus() {
        String s = "mul(5x^2+3x+10,div(div(10x,10),plus(10,8)))";
        ComplexFunction cf = new ComplexFunction();
        function f = cf.initFromString(s);
        cf.plus(f);
        System.out.println(f.toString());
        System.out.println(cf.toString());

    }

    @Test
    public void mul() {
    }

    @Test
    public void div() {
    }

    @Test
    public void max() {
    }

    @Test
    public void min() {
    }

    @Test
    public void comp() {
    }

    @Test
    public void left() {
    }

    @Test
    public void right() {
    }

    @Test
    public void getOp() {
    }

    @Test
    public void f() {
    }

    @Test
    public void initFromString() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void copy() {
    }
}