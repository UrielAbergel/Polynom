package Ex1Testing;
import Ex1.*;
import Ex1.ComplexFunction;
import Ex1.function;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComplexFunctionTest {

    @Test
    public void plus() {
        function f1 = new ComplexFunction();
        f1.initFromString("5x");
        function f2 = new ComplexFunction();
        f1 = f2.initFromString("15x");
        f2 = f1.initFromString("10x");
        String s = "mul(5x^2+3x+10,div(div(10x,10),plus(10,8)))";
        ComplexFunction cf = new ComplexFunction("plus",f1,f2);
        function f = cf.initFromString(s);
        cf.plus(f);
        String test = "Plus(Plus(15.0x^1,10.0x^1),Times(5.0x^2+3.0x^1+10.0x^0,Divid(Divid(10.0x^1,10.0x^0),Plus(10.0x^0,8.0x^0))))";
        assertEquals(test,cf.toString());
        ComplexFunction cf1 = new ComplexFunction();
        cf1.plus(f1);
        String test2 = "Plus(15.0x^1,null)";
        assertEquals(test2, cf1.toString());
    }

    @Test
    public void mul() {
        function f1 = new ComplexFunction();
        f1.initFromString("5x");
        function f2 = new ComplexFunction();
        f1 = f2.initFromString("15x");
        f2 = f1.initFromString("10x");
        String s = "mul(5x^2+3x+10,div(div(10x,10),plus(10,8)))";
        ComplexFunction cf = new ComplexFunction("plus",f1,f2);
        function f = cf.initFromString(s);
        cf.mul(f);
        String test = "Times(Plus(15.0x^1,10.0x^1),Times(5.0x^2+3.0x^1+10.0x^0,Divid(Divid(10.0x^1,10.0x^0),Plus(10.0x^0,8.0x^0))))";
        assertEquals(test,cf.toString());
        ComplexFunction cf1 = new ComplexFunction();
        cf1.mul(f1);
        String test2 = "Times(15.0x^1,null)";
        assertEquals(test2, cf1.toString());
    }

    @Test
    public void div() {
        function f1 = new ComplexFunction();
        f1.initFromString("5x");
        function f2 = new ComplexFunction();
        f1 = f2.initFromString("15x");
        f2 = f1.initFromString("10x");
        String s = "mul(5x^2+3x+10,div(div(10x,10),plus(10,8)))";
        ComplexFunction cf = new ComplexFunction("plus",f1,f2);
        function f = cf.initFromString(s);
        cf.div(f);
        String test = "Divid(Plus(15.0x^1,10.0x^1),Times(5.0x^2+3.0x^1+10.0x^0,Divid(Divid(10.0x^1,10.0x^0),Plus(10.0x^0,8.0x^0))))";
        assertEquals(test,cf.toString());
        ComplexFunction cf1 = new ComplexFunction();
        cf1.div(f1);
        String test2 = "Divid(15.0x^1,null)";
        assertEquals(test2, cf1.toString());
    }

    @Test
    public void max() {
        function f1 = new ComplexFunction();
        f1.initFromString("5x");
        function f2 = new ComplexFunction();
        f1 = f2.initFromString("15x");
        f2 = f1.initFromString("10x");
        String s = "mul(5x^2+3x+10,div(div(10x,10),plus(10,8)))";
        ComplexFunction cf = new ComplexFunction("plus",f1,f2);
        function f = cf.initFromString(s);
        cf.max(f);
        String test = "Max(Plus(15.0x^1,10.0x^1),Times(5.0x^2+3.0x^1+10.0x^0,Divid(Divid(10.0x^1,10.0x^0),Plus(10.0x^0,8.0x^0))))";
        assertEquals(test,cf.toString());
        ComplexFunction cf1 = new ComplexFunction();
        cf1.max(f1);
        String test2 = "Max(15.0x^1,null)";
        assertEquals(test2, cf1.toString());
    }

    @Test
    public void min() {
        function f1 = new ComplexFunction();
        f1.initFromString("5x");
        function f2 = new ComplexFunction();
        f1 = f2.initFromString("15x");
        f2 = f1.initFromString("10x");
        String s = "mul(5x^2+3x+10,div(div(10x,10),plus(10,8)))";
        ComplexFunction cf = new ComplexFunction("plus",f1,f2);
        function f = cf.initFromString(s);
        cf.min(f);
        String test = "Min(Plus(15.0x^1,10.0x^1),Times(5.0x^2+3.0x^1+10.0x^0,Divid(Divid(10.0x^1,10.0x^0),Plus(10.0x^0,8.0x^0))))";
        assertEquals(test,cf.toString());
        ComplexFunction cf1 = new ComplexFunction();
        cf1.min(f1);
        String test2 = "Min(15.0x^1,null)";
        assertEquals(test2, cf1.toString());
    }

    @Test
    public void comp() {
        function f1 = new ComplexFunction();
        function f2 = new ComplexFunction();
        f1 = f2.initFromString("15x");
        f2 = f1.initFromString("10x");
        String s = "mul(5x^2+3x+10,div(div(10x,10),plus(10,8)))";
        ComplexFunction cf = new ComplexFunction("plus",f1,f2);
        function f = cf.initFromString(s);
        cf.comp(f);
        String test = "Comp(Plus(15.0x^1,10.0x^1),Times(5.0x^2+3.0x^1+10.0x^0,Divid(Divid(10.0x^1,10.0x^0),Plus(10.0x^0,8.0x^0))))";
        assertEquals(test,cf.toString());
        ComplexFunction cf1 = new ComplexFunction();
        cf1.comp(f1);
        String test2 = "Comp(15.0x^1,null)";
        assertEquals(test2, cf1.toString());
    }

    @Test
    public void left() {
        function f1 = new ComplexFunction();
        function f2 = new ComplexFunction();
        f1 = f2.initFromString("15x");
        f2 = f1.initFromString("10x");
        String s = "mul(5x^2+3x+10,div(div(10x,10),plus(10,8)))";
        ComplexFunction cf = new ComplexFunction("plus",f1,f2);
        function f3 = cf.left();
        assertEquals(cf.left.toString(),f3.toString());
    }

    @Test
    public void right() {
        function f1 = new ComplexFunction();
        function f2 = new ComplexFunction();
        f1 = f2.initFromString("15x");
        f2 = f1.initFromString("10x");
        String s = "mul(5x^2+3x+10,div(div(10x,10),plus(10,8)))";
        ComplexFunction cf = new ComplexFunction("plus",f1,f2);
        function f3 = cf.right();
        assertEquals(cf.right.toString(),f3.toString());
    }

    @Test
    public void getOp() {
        function f1 = new ComplexFunction();
        function f2 = new ComplexFunction();
        f1 = f2.initFromString("15x");
        f2 = f1.initFromString("10x");
        String s = "mul(5x^2+3x+10,div(div(10x,10),plus(10,8)))";
        ComplexFunction cf = new ComplexFunction("plus",f1,f2);
        assertEquals(Operation.Plus,cf.OP);
        ComplexFunction cf1 = new ComplexFunction();
        assertEquals(Operation.None,cf1.OP);
    }

    @Test
    public void f() {
        String test1 = "10x^2+10x+1000";
        String test2 = "mul(10x^2+10x+8,1000)";
        String test3 = "mul(div(10x^2+4,plus(100,4x^6-10)),mul(5,5))";
        String test4 = "min(10x,mul(5,5))";
        String test5 = "mul(max(x^2+20x+1,x^3+100),10)";
        String test6 = "comp(x,comp(3x^5,mul(10,5x)))";
        ComplexFunction cf = new ComplexFunction();
        function testF1 = cf.initFromString(test1);
        function testF2 = cf.initFromString(test2);
        function testF3 = cf.initFromString(test3);
        function testF4 = cf.initFromString(test4);
        function testF5 = cf.initFromString(test5);
        function testF6 = cf.initFromString(test6);
        double exp = 1020;
        double try1 = testF1.f(1);
        boolean flag;
        if(exp == try1) flag =true;
        else flag = false;
        assertEquals(true,flag);
        try1 = testF1.f(-1);
        exp = 1000;
        if(exp == try1) flag =true;
        else flag = false;
        assertEquals(true,flag);

        try1 = testF2.f(5);
        exp = 308000;
        if(exp == try1) flag =true;
        else flag = false;
        assertEquals(true,flag);
        try1 = testF2.f(-10);
        exp = 908000;
        if(exp == try1) flag =true;
        else flag = false;
        assertEquals(true,flag);

        try1 = testF3.f(3);
        exp = 0.7817697937;
        if(Math.abs(exp - try1)<0.001) flag =true;
        else flag = false;
        assertEquals(true,flag);
        try1 = testF3.f(0);
        exp = 1.11111111;
        if(Math.abs(exp - try1)<0.001) flag =true;
        else flag = false;
        assertEquals(true,flag);

        try1 = testF4.f(1);
        exp = 10;
        if(Math.abs(exp - try1)<0.001) flag =true;
        else flag = false;
        assertEquals(true,flag);
        try1 = testF4.f(5);
        exp = 25;
        if(Math.abs(exp - try1)<0.001) flag =true;
        else flag = false;
        assertEquals(true,flag);

        try1 = testF5.f(3);
        exp = 700;
        if(Math.abs(exp - try1)<0.001) flag =true;
        else flag = false;
        assertNotEquals(true,flag);

        try1 = testF5.f(1);
        exp = 1010;
        if(Math.abs(exp - try1)<0.001) flag =true;
        else flag = false;
        assertEquals(true,flag);

        try1 = testF6.f(5);
        exp = 2500;
        if(Math.abs(exp - try1)<0.001) flag =true;
        else flag = false;
        assertNotEquals(true,flag);
        try1 = testF6.f(1);
        exp = 150;
        if(Math.abs(exp - try1)<0.001) flag =true;
        else flag = false;
        assertEquals(true,flag);



    }

    @Test
    public void initFromString() {
        String test1 = "Plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0)";
        String test2 =  "Divid(Plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),-1.0x^4+2.4x^2+3.1x^1)";
        String test3 = "0.1x^5-1.2999999999999998x^1+5.0x^0";
        ComplexFunction p = new ComplexFunction();
        function test11 = p.initFromString(test1);
        function test22 = p.initFromString(test2);
        function test33 = p.initFromString(test3);
        assertEquals(test11.toString(),test1);
        assertEquals(test22.toString(),test2);
        assertEquals(test33.toString(),test3);
    }

    @Test
    public void testToString() {
        String s1 = "mul(5x^2+3x+10,div(div(10x,10),plus(10,8)))";
        String s2 = "-1";
        String s3 = "0";
        String s4 = "x";
        String s5 = "x^2+1";
        String test1 = "Times(5.0x^2+3.0x^1+10.0x^0,Divid(Divid(10.0x^1,10.0x^0),Plus(10.0x^0,8.0x^0)))";
        String test2 = "-1.0x^0";
        String test3 = "0.0x^0";
        String test4 = "1.0x^1";
        String test5 = "1.0x^2+1.0x^0";
        ComplexFunction cf = new ComplexFunction();
        function f1 = cf.initFromString(s1);
        function f2 = cf.initFromString(s2);
        function f3 = cf.initFromString(s3);
        function f4 = cf.initFromString(s4);
        function f5 = cf.initFromString(s5);
        assertEquals(test1,f1.toString());
        assertEquals(test2,f2.toString());
        assertEquals(test3,f3.toString());
        assertEquals(test4,f4.toString());
        assertEquals(test5,f5.toString());

    }

    @Test
    public void testEquals() {
        String test1 = "Plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0)";
        String test2 =  "Divid(Plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),-1.0x^4+2.4x^2+3.1x^1)";
        String test3 = "0.1x^5-1.2999999999999998x^1+5.0x^0";
        ComplexFunction p = new ComplexFunction();
        function test11 = p.initFromString(test1);
        function test22 = p.initFromString(test2);
        function test33 = p.initFromString(test3);
        function test12 = p.initFromString(test1);
        function test23 = p.initFromString(test2);
        function test34 = p.initFromString(test3);
        String test111 = "Plus(-1.0x^4+2.4x^2+3.1x^0,0.1x^5-1.2999999999999998x^9+4.5x^0+5.0x^0)";
        String test222 =  "Divid(Plus(-1.0x^4+2.4x^5+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),-1.0x^4+2.4x^2+3.1x^1)";
        String test333 = "0.1x^2-1.2999999999999998x^1+5.7x^1";
        function test3333 = p.initFromString(test333);
        function test1111 = p.initFromString(test111);
        function test2222 = p.initFromString(test222);
        boolean b = test11.equals(test34);
        assertEquals(test11.equals(test12),true);
        assertEquals(test22.equals(test23),true);
        assertEquals(test33.equals(test34),true);
        assertNotEquals(test11.equals(test1111),true);
        assertNotEquals(test33.equals(test3333),true);
        assertNotEquals(test22.equals(test2222),true);
    }

    @Test
    public void copy() {
        ComplexFunction cf1 = new ComplexFunction();
        ComplexFunction cf2 = new ComplexFunction();
        String s = "mul(5x^2+3x+10,div(div(10x,10),plus(10,8)))";
        function f1 = cf1.initFromString("15x");
        function f2 = cf2.initFromString(s);
        ComplexFunction cf3 = new ComplexFunction("plus",f1,f2);
        ComplexFunction cf4 = new ComplexFunction(cf3.copy());
        boolean flag = cf4.equals(cf3);
        assertEquals(true,flag);
    }

}