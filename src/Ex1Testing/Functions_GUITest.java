package Ex1Testing;

import Ex1.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

import static Ex1.Functions_GUI.drawFunctions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Note: minor changes (thanks to Amichai!!)
 * The use of "get" was replaced by iterator!
 *
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
 1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
 2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
 3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
 4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
 5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
 6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

 * @author boaz_benmoshe
 *
 */

public class Functions_GUITest {
    public static void main(String[] a) {
        functions data = FunctionsFactory();
        int w = 1000, h = 600, res = 200;
        Range rx = new Range(-10, 10);
        Range ry = new Range(-5, 15);
        data.drawFunctions(w, h, rx, ry, res);
        String file = "function_file.txt";
        String file2 = "function_file2.txt";
        try {
            data.saveToFile(file);
            Functions_GUI data2 = new Functions_GUI();
            data2.initFromFile(file);
            data.saveToFile(file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String JSON_param_file = "GUI_params.txt";
        data.drawFunctions(JSON_param_file);
    }

    private Functions_GUI _data = null;

    @BeforeClass
    static void setUpBeforeClass() throws Exception {
        int width = 900;
        int height = 900;
        int res = 200;
        Range rx = new Range(-15, 15);
        Range ry = new Range(-15, 15);
    }

    @Before
    void setUp() throws Exception {
        _data = new Functions_GUI();
        _data = FunctionsFactory();
    }
    @Test
    void testFunctions_GUI() {
        _data = new Functions_GUI();
        try {
            _data = FunctionsFactory();
        } catch (Exception e) {
            fail("Not yet implemented");
        }
        try {
            _data.drawFunctions("GUI_params.txt");
        } catch (Exception e){
            System.out.println("The path is not correct");
        }
    }

    @Test
    void testInitFromFile() {
        String path = "function_file.txt";
        Functions_GUI f_GUI = new Functions_GUI();
        try {
            f_GUI.initFromFile(path);
        } catch (Exception e) {
            System.out.println("Your path is leading to nowhere");
        }
        ComplexFunction cf = new ComplexFunction();
        function f1 = cf.initFromString("Plus(-1.0x^4+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0)");
        function f2 = cf.initFromString("Plus(Divid(1.0x^1+1.0x^0,Times(Times(1.0x^1-2.0x^0,0.0x^0),1.0x^1-4.0x^0)),2.0x^0)");
        function f3 = cf.initFromString("Divid(Plus(-1.0x^4+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),-1.0x^4+3.1x^0)");
        function f4 = cf.initFromString("-1.0x^4+2.4x^2+3.1x^0");
        function f5 = cf.initFromString("0.1x^5-1.2999999999999998x^1+5.0x^0");
        function f6 = cf.initFromString("Max(Max(Max(Max(Plus(-1.0x^4+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),Plus(Divid(1.0x^1+1.0x^0,Times(Times(1.0x^1-2.0x^0,0.0x^0),1.0x^1-4.0x^0)),2.0x^0)),Divid(Plus(-1.0x^4+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),-1.0x^4+3.1x^0)),-1.0x^4+2.4x^2+3.1x^0),0.1x^5-1.2999999999999998x^1+5.0x^0)");
        function f7 = cf.initFromString("Min(Min(Min(Min(Plus(-1.0x^4+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),Plus(Divid(1.0x^1+1.0x^0,Times(Times(1.0x^1-2.0x^0,0.0x^0),1.0x^1-4.0x^0)),2.0x^0)),Divid(Plus(-1.0x^4+3.1x^0,0.1x^5-1.2999999999999998x^1+5.0x^0),-1.0x^4+3.1x^0)),-1.0x^4+2.4x^2+3.1x^0),0.1x^5-1.2999999999999998x^1+5.0x^0)");
        assertEquals(f1.toString(),f_GUI.get(0).toString());
        assertEquals(f2.toString(),f_GUI.get(1).toString());
        assertEquals(f3.toString(),f_GUI.get(2).toString());
        assertEquals(f4.toString(),f_GUI.get(3).toString());
        assertEquals(f5.toString(),f_GUI.get(4).toString());
        assertEquals(f6.toString(),f_GUI.get(5).toString());
        assertEquals(f7.toString(),f_GUI.get(6).toString());
    }

    @Test
    public void saveToFile()  {
        String path = "function_file.txt";
        Functions_GUI f_GUI = new Functions_GUI();
        try {
            f_GUI.initFromFile(path);
        } catch (Exception e) {
            System.out.println("Your path is leading to nowhere");
        }
        try {
            f_GUI.saveToFile("My_New_Function_file.txt");
        } catch (Exception e) {
            System.out.println("Error with the save to file");
        }
        Functions_GUI f_GUI2 = new Functions_GUI();
        try {
            f_GUI2.initFromFile("My_New_Function_file.txt");
        } catch (IOException e) {
            System.out.println("Fail to InitFromString");
        }
        assertEquals(f_GUI2.get(0).toString(),f_GUI.get(0).toString());
        assertEquals(f_GUI2.get(1).toString(),f_GUI.get(1).toString());
        assertEquals(f_GUI2.get(2).toString(),f_GUI.get(2).toString());
        assertEquals(f_GUI2.get(3).toString(),f_GUI.get(3).toString());
        assertEquals(f_GUI2.get(4).toString(),f_GUI.get(4).toString());
        assertEquals(f_GUI2.get(5).toString(),f_GUI.get(5).toString());
        assertEquals(f_GUI2.get(6).toString(),f_GUI.get(6).toString());
    }
    @Test
    public void drawFunctionstest() {
        int width = 900;
        int height = 900;
        int res = 200;
        Range rx = new Range(-15, 15);
        Range ry = new Range(-15, 15);
        String path = "function_file.txt";
        Functions_GUI FG = new Functions_GUI();
        try {
            FG.initFromFile(path);
        }catch (Exception e){
            System.out.println("The path is not exists");
        }
        try {
            drawFunctions(FG.Flist, width, height, rx, ry, res);
        } catch (Exception e){
            System.out.println("Fail to print the functions");
        }
    }

    @Test
    void testDrawFunctions() {
        _data = new Functions_GUI();
        try {
            _data.initFromFile("function_file.txt");
        }catch (Exception e){
            System.out.println("The path is not exists");
        }
        try {
            _data.drawFunctions();
        } catch (Exception e) {
            fail("Not yet implemented");
        }
    }


    @Test
    void testDrawFunctionsIntIntRangeRangeInt() {
        _data = new Functions_GUI();
        int width = 900;
        int height = 900;
        int res = 200;
        Range rx = new Range(-15, 15);
        Range ry = new Range(-15, 15);
        String path = "function_file.txt";
        try{
            drawFunctions(_data.Flist,width,height,rx,ry,res);
        }catch (Exception e) {
            fail("Not yet implemented");
        }
    }

    public static Functions_GUI FunctionsFactory() {
        Functions_GUI ans = new Functions_GUI();
        String s1 = "3.1 +2.4x^2 -x^4";
        String s2 = "5 +2x -3.3x +0.1x^5";
        String[] s3 = {"x +3","x -2", "x -4"};
        Polynom p1 = new Polynom(s1);
        Polynom p2 = new Polynom(s2);

        Polynom p3 = new Polynom(s3[0]);
        ComplexFunction cf3 = new ComplexFunction(p3);
        for(int i=1;i<s3.length;i++) {
            cf3.mul(new Polynom(s3[i]));
        }
        cf3.mul(cf3);
        ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
        ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
        cf4.plus(new Monom("2"));
        ans.add(cf.copy());
        ans.add(cf4.copy());
        cf.div(p1);
        ans.add(cf.copy());
        String s = cf.toString();
        function cf5 = cf4.initFromString(s1);
        function cf6 = cf4.initFromString(s2);
        ans.add(cf5.copy());
        ans.add(cf6.copy());
        Iterator<function> iter = ans.iterator();
        function f = iter.next();
        ComplexFunction max = new ComplexFunction(f);
        ComplexFunction min = new ComplexFunction(f);
        while(iter.hasNext()) {
            f = iter.next();
            max.max(f);
            min.min(f);
        }
        ans.add(max);
        ans.add(min);
        return ans;
    }
}
