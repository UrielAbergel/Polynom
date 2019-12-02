package Tests;
import Ex1.Monom;
import Ex1.Polynom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MonomTest {

    @Test
    void derivative() {
        Monom test1 = new Monom("5x^3");
        Monom test2 = new Monom("x");
        Monom test3 = new Monom("x^2");
        Assertions.assertEquals(new Monom("15x^2").returnMonomToString(),test1.derivative().returnMonomToString());
        Assertions.assertEquals(new Monom("1").returnMonomToString(),test2.derivative().returnMonomToString());
        Assertions.assertEquals(new Monom("2x").returnMonomToString(),test3.derivative().returnMonomToString());
    }

    @Test
    void f() {
        Monom test1 = new Monom("5x^3");
        Monom test2 = new Monom("x");
        Monom test3 = new Monom("-x^2");
        Monom test4 = new Monom("2x^3");
        Monom test5 = new Monom("-1");
        Monom test6 = new Monom("0");
        Assertions.assertEquals(5.0,test1.f(1));
        Assertions.assertEquals(40.0,test1.f(2));
        Assertions.assertEquals(0.0,test1.f(0));
        Assertions.assertEquals(1.0,test2.f(1));
        Assertions.assertEquals(-5,test2.f(-5));
        Assertions.assertEquals(-25,test3.f(-5));
        Assertions.assertEquals(-250,test4.f(-5));
        Assertions.assertEquals(-1,test5.f(0));
        Assertions.assertEquals(0,test6.f(100));
    }

    @Test
    void add() {
        Monom test1 = new Monom("5x^5");
        Monom test2 = new Monom("x^5");
        test1.add(test2);
        Monom test3 = new Monom("-x^2");
        Monom test4 = new Monom("2x^2");
        test3.add(test4);
        Monom test5 = new Monom("-1");
        Monom test6 = new Monom("0");
        test5.add(test6);
        Assertions.assertEquals("6.0x^5",test1.returnMonomToString());
        Assertions.assertEquals("1.0x^2",test3.returnMonomToString());
        Assertions.assertEquals("-1.0x^0",test5.returnMonomToString());
    }

    @Test
    void multipy() {
        Polynom p = new Polynom("-5x^3+2x^2+4x+15");
        Polynom a = new Polynom("-x");
        Polynom b = new Polynom("x^2");
        Polynom c = new Polynom("-3x^3");
        Polynom r = new Polynom("-5x^3+2x^2+4x+15");
        Polynom t = new Polynom("-5x^3+2x^2+4x+15");
        int counter = 0;
        p.multiply(a);
        if (p.PolynomList.get(0).get_power() == 4 && p.PolynomList.get(0).get_coefficient() == 5) {
            counter++;
        }
        p = r;
        p.multiply(b);
        if (p.PolynomList.get(0).get_power() == 5 && p.PolynomList.get(0).get_coefficient() == -5) {
            counter++;
        }
        if (p.PolynomList.get(1).get_power() == 4 && p.PolynomList.get(1).get_coefficient() == 2) {
            counter++;
        }
        p = t;
        p.multiply(c);
        if (p.PolynomList.get(0).get_power() == 6 && p.PolynomList.get(0).get_coefficient() == 15) {
            counter++;
        }
        if (p.PolynomList.get(1).get_power() == 5 && p.PolynomList.get(1).get_coefficient() == -6) {
            counter++;
        }
        assertEquals(5,counter);

    }
    @Test
    void Monom(){
        Monom m1 = new Monom("5x");
        Monom m2 = new Monom("0x^3");
        Monom m3 = new Monom("-4.5");
        Monom m4 = new Monom("0");
        Assertions.assertEquals(5.0, m1.get_coefficient());
        Assertions.assertEquals(1,m1.get_power());
        Assertions.assertEquals(0.0,m2.get_coefficient());
        Assertions.assertEquals(3,m2.get_power());
        Assertions.assertEquals(-4.5,m3.get_coefficient());
        Assertions.assertEquals(0,m3.get_power());
        Assertions.assertEquals(0.0,m4.get_coefficient());
        Assertions.assertEquals(0,m4.get_power());

    }
    @Test
    void Monom2(){
        Monom m1 = new Monom(5.2,4);
        Assertions.assertEquals(5.2,m1.get_coefficient());
        Assertions.assertEquals(4,m1.get_power());
        Monom m2 = new Monom(0,0);
        Assertions.assertEquals(0.0,m2.get_coefficient());
        Assertions.assertEquals(0,m2.get_power());
    }

    @Test
    void MonomOt(){
        Monom m = new Monom(5,5);
        Monom m1 = new Monom(m);
        Assertions.assertEquals(5.0,m1.get_coefficient());
        Assertions.assertEquals(5,m1.get_power());
    }

    @Test
    void equals(){
        Monom m1 = new Monom("5.0x^3");
        Monom m2 = new Monom("3x^3");
        Monom m3 = new Monom("2.0x^3");
        m3.add(m2);
        Boolean ans1 = m1.equals(m3);
        Boolean ans2 = m1.equals(m2);
        assertEquals(true,ans1);
        assertEquals(false,ans2);
    }

}