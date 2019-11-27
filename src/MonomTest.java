import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MonomTest {

    @Test
    void derivative() {
        Monom test1 = new Monom("5x^3");
        Monom test2 = new Monom("x");
        Monom test3 = new Monom("x^2");
        assertEquals(new Monom("15x^2").returnMonomToString(),test1.derivative().returnMonomToString());
        assertEquals(new Monom("1").returnMonomToString(),test2.derivative().returnMonomToString());
        assertEquals(new Monom("2x").returnMonomToString(),test3.derivative().returnMonomToString());
    }

    @Test
    void f() {
        Monom test1 = new Monom("5x^3");
        Monom test2 = new Monom("x");
        Monom test3 = new Monom("-x^2");
        Monom test4 = new Monom("2x^3");
        Monom test5 = new Monom("-1");
        Monom test6 = new Monom("0");
        assertEquals(5.0,test1.f(1));
        assertEquals(40.0,test1.f(2));
        assertEquals(0.0,test1.f(0));
        assertEquals(1.0,test2.f(1));
        assertEquals(-5,test2.f(-5));
        assertEquals(-25,test3.f(-5));
        assertEquals(-250,test4.f(-5));
        assertEquals(-1,test5.f(0));
        assertEquals(0,test6.f(100));
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
        assertEquals("6.0x^5",test1.returnMonomToString());
        assertEquals("1.0x^2",test3.returnMonomToString());
        assertEquals("-1.0x^0",test5.returnMonomToString());
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
}