package Ex1;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.*;
import  Ex1.ComplexFunction;
import com.google.gson.Gson;

import java.io.File;






public class Functions_GUI implements functions {
    ArrayList<function> Flist;

    public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE,
            Color.red, Color.GREEN, Color.PINK};

    public Functions_GUI(){
        ArrayList<function> temp = new ArrayList<function>();
        Flist = temp;
    }

    @Override
    public void initFromFile(String file) throws IOException {
        File importedFile = new File(file);
        BufferedReader bofi = new BufferedReader(new FileReader(file));
        String Ans = "";
        while ((Ans = bofi.readLine()) != null)
            if (ComplexFunction.CheackSograim(Ans)) {
                ComplexFunction t = new ComplexFunction();
                Ans = fixThisFileText(Ans);
                function f =  t.initFromString(Ans);
                if(Flist == null)
                {
                    ArrayList<function> p = new ArrayList<function>();
                    Flist = p ;
                }
                Flist.add(f);
                Ans = "";

            }
    }

    private String fixThisFileText(String ans) {
        ans = ans.replaceAll(" ", "");
        return ans;
    }


    @Override
    public void saveToFile(String file) throws IOException {
        FileWriter writeOnes = new FileWriter(file);
        if(Flist == null){
            System.out.println("No read file to read");
            return;
        }
        for (int i = 0; i <this.Flist.size() ; i++) {
            String s = "";
            function p =  Flist.get(i);
            s = p.toString();
            writeOnes.write(s);
            s = "";
            writeOnes.write("\n") ;

        }
        writeOnes.close();

    }
    public static void drawFunctions(ArrayList<function> ff, int width, int height, Range rx, Range ry, int res) {
        StdDraw.setCanvasSize(width,height);
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(rx.get_min(),0,rx.get_max(),0);
        StdDraw.line(0,ry.get_min(),0,ry.get_max());
        StdDraw.setPenColor(Color.black);
        StdDraw.text(-rx.get_max()+5,ry.get_max()-1,"Uriel and Yair Graph");

        for (int i = (int) rx.get_min(); i <= (int)rx.get_max(); i++) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.005);
            //StdDraw.line(i,-0.1,i,0.1);
            String s = "" + i;
            StdDraw.text(i,0.3,s);

        }
        for (int i = (int)ry.get_min(); i <= ry.get_max(); i++) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.005);
            //StdDraw.line(-0.1,i,0.1,i);
            String s = "" + i;
            StdDraw.text(0.3,i,s);

        }

        for (int i = (int)rx.get_min()-10; i <= rx.get_max()+10; i++) {
            StdDraw.setPenColor(Color.DARK_GRAY);
            StdDraw.setPenRadius(0.0008);
            StdDraw.line(rx.get_min(),i,rx.get_max(),i);
        }

        for (int i = (int)ry.get_min()-10; i <= ry.get_max()+10; i++) {
            StdDraw.setPenColor(Color.DARK_GRAY);
            StdDraw.setPenRadius(0.0008);
            StdDraw.line(i,ry.get_min(),i,ry.get_max());
        }

        double ResSUL = (rx.get_max()-rx.get_min())/res;
        double saveMIN= rx.get_min() ;
        for (int i= 0; i < ff.size(); i++) {
            ResSUL = (rx.get_max()-rx.get_min())/res;
            saveMIN = rx.get_min();
            for (int j = 0; j < res; j++) {
                StdDraw.setPenColor(Colors[i%7]);
                StdDraw.setPenRadius(0.003);
                StdDraw.line(saveMIN,ff.get(i).f(saveMIN),saveMIN+ResSUL,ff.get(i).f(saveMIN+ResSUL));
                saveMIN=saveMIN+ResSUL;
            }
        }
    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        StdDraw.setCanvasSize(width,height);
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(rx.get_min(),0,rx.get_max(),0);
        StdDraw.line(0,ry.get_min(),0,ry.get_max());
        StdDraw.setPenColor(Color.black);
        StdDraw.text(-rx.get_max()+5,ry.get_max()-1,"Uriel and Yair Graph");

        for (int i = (int) rx.get_min(); i <= (int)rx.get_max(); i++) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.005);
            //StdDraw.line(i,-0.1,i,0.1);
            String s = "" + i;
            StdDraw.text(i,0.3,s);
        }

        for (int i = (int)ry.get_min(); i <= ry.get_max(); i++) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.005);
            //StdDraw.line(-0.1,i,0.1,i);
            String s = "" + i;
            StdDraw.text(0.3,i,s);
        }

        for (int i = (int)rx.get_min()-10; i <= rx.get_max()+10; i++) {
            StdDraw.setPenColor(Color.DARK_GRAY);
            StdDraw.setPenRadius(0.0008);
            StdDraw.line(rx.get_min(),i,rx.get_max(),i);
        }

        for (int i = (int)ry.get_min()-10; i <= ry.get_max()+10; i++) {
            StdDraw.setPenColor(Color.DARK_GRAY);
            StdDraw.setPenRadius(0.0008);
            StdDraw.line(i,ry.get_min(),i,ry.get_max());
        }

        double ResSUL = (rx.get_max()-rx.get_min())/resolution;
        double saveMIN= rx.get_min() ;
        for (int i= 0; i < this.Flist.size(); i++) {
            ResSUL = (rx.get_max()-rx.get_min())/resolution;
            saveMIN = rx.get_min();
            for (int j = 0; j < resolution; j++) {
                StdDraw.setPenColor(Colors[i%7]);
                StdDraw.setPenRadius(0.003);
                StdDraw.line(saveMIN,this.Flist.get(i).f(saveMIN),saveMIN+ResSUL,this.Flist.get(i).f(saveMIN+ResSUL));
                saveMIN=saveMIN+ResSUL;
            }
        }

        }



    @Override
    public void drawFunctions(String json_file) {
        Gson json = new Gson();
        try{
            FileReader filereader = new FileReader(json_file);
            JsonParametrs parm = json.fromJson(filereader,JsonParametrs.class);
            Range rx = new Range(parm.Range_X[0],parm.Range_X[1]);
            Range ry = new Range(parm.Range_Y[0],parm.Range_Y[1]);
            drawFunctions(parm.Width,parm.Height,rx,ry,parm.Resolution);

        }
        catch(Exception e){
            System.out.println("The Json file is not correct");
        }
    }

    public void drawFunctions() {
        StdDraw.setCanvasSize(900,900);
        StdDraw.setXscale(-15, 15);
        StdDraw.setYscale(-15, 15);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(-15,0,15,0);
        StdDraw.line(0,-15,0,15);
        StdDraw.setPenColor(Color.black);
        StdDraw.text(-10,14,"Uriel and Yair Graph");

        for (int i =-15; i <=15; i++) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.005);
            //StdDraw.line(i,-0.1,i,0.1);
            String s = "" + i;
            StdDraw.text(i,0.3,s);
            StdDraw.setPenColor(Color.DARK_GRAY);
            StdDraw.setPenRadius(0.0008);
            StdDraw.line(-15,i,15,i);
        }
        for (int i = -15; i <= 15; i++) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.005);
            //StdDraw.line(-0.1,i,0.1,i);
            String s = "" + i;
            StdDraw.text(0.3,i,s);
            StdDraw.setPenColor(Color.DARK_GRAY);
            StdDraw.setPenRadius(0.0008);
            StdDraw.line(i,-15,i,15);
        }

        for (int i = -25; i <= 35; i++) {
            StdDraw.setPenColor(Color.DARK_GRAY);
            StdDraw.setPenRadius(0.0008);
            StdDraw.line(-15,i,15,i);
        }

        for (int i = -25; i <= 35; i++) {
            StdDraw.setPenColor(Color.DARK_GRAY);
            StdDraw.setPenRadius(0.0008);
            StdDraw.line(i,-15,i,15);
        }

        double ResSUL = 0.15;
        double saveMIN= -15 ;
        for (int i= 0; i < this.Flist.size(); i++) {
            ResSUL = 0.15;
            saveMIN = -15;
            for (int j = 0; j < 200; j++) {
                StdDraw.setPenColor(Colors[i%7]);
                StdDraw.setPenRadius(0.003);
                StdDraw.line(saveMIN,this.Flist.get(i).f(saveMIN),saveMIN+ResSUL,this.Flist.get(i).f(saveMIN+ResSUL));
                saveMIN=saveMIN+ResSUL;
            }
        }
    }

    public function get(int i) {
        return Flist.get(i);
    }

    @Override
    public int size() {
        return Flist.size();
    }

    @Override
    public boolean isEmpty() {
        return Flist.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return Flist.contains(o);
    }

    @Override
    public Iterator<function> iterator() {
        return Flist.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return Flist.toArray(ts);
    }

    @Override
    public boolean add(function function) {
        return Flist.add(function);
    }

    @Override
    public boolean remove(Object o) {
        return Flist.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return Flist.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends function> collection) {
        return Flist.addAll(collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return Flist.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return Flist.retainAll(collection);
    }

    @Override
    public void clear() {
        Flist.clear();
    }



    public static void main(String[] args) throws IOException {
        Functions_GUI  p = new Functions_GUI();
        p.initFromFile("function_file2.txt");
        p.saveToFile("TheSAVE");
        Range r1 = new Range(-20,20);
        Range r2 = new Range(-20,20);
        p.drawFunctions("GUI_params.txt");
        //  drawFunctions(p.Flist);
//        ComplexFunction p = new ComplexFunction();

//        ComplexFunction r = (ComplexFunction) p.Flist.get(0);
//        String s = "";
//        s = r.toStringNodeTree(r.pt.root);
//        System.out.println(s);

    }
}