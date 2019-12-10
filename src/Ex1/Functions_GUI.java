package Ex1;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.*;
import  Ex1.ComplexFunction;




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

        //=====================================================
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
            StdDraw.setPenColor(Color.DARK_GRAY);
            StdDraw.setPenRadius(0.0008);
            StdDraw.line(rx.get_min(),i,rx.get_max(),i);
        }
        for (int i = (int)ry.get_min(); i <= ry.get_max(); i++) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.005);
            //StdDraw.line(-0.1,i,0.1,i);
            String s = "" + i;
            StdDraw.text(0.3,i,s);
            StdDraw.setPenColor(Color.DARK_GRAY);
            StdDraw.setPenRadius(0.0008);
            StdDraw.line(i,ry.get_min(),i,ry.get_max());
        }

        double ResSUL = (rx.get_max()-rx.get_min())/res;
        double saveMIN= rx.get_min() ;
        for (int i= 0; i < ff.size(); i++) {
            ResSUL = (rx.get_max()-rx.get_min())/res;
            for (int j = 0; j < res; j++) {
                StdDraw.setPenColor(Colors[i]);
                StdDraw.setPenRadius(0.003);
                StdDraw.line(saveMIN,ff.get(i).f(saveMIN),saveMIN+ResSUL,ff.get(i).f(saveMIN+ResSUL));
                saveMIN=saveMIN+ResSUL;
            }
        }
    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        StdDraw.setCanvasSize(width,height);
        StdDraw.setPenColor(Color.RED);
        StdDraw.setXscale(10,9);
        StdDraw.setYscale();
        StdDraw.setPenRadius(10);
        for (int i = 0; i < 100; i++) {
            StdDraw.line(i,10,i+20,i+20);

        }

    }

    @Override
    public void drawFunctions(String json_file) {

    }

    public void drawFunctions() {

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
        p.initFromFile("function_file.txt");
        p.saveToFile("TheSAVE");
        Range r1 = new Range(-20,20);
        Range r2 = new Range(-20,20);
        drawFunctions(p.Flist,300  ,300,r1,r2,50);
        //  drawFunctions(p.Flist);
//        ComplexFunction p = new ComplexFunction();

//        ComplexFunction r = (ComplexFunction) p.Flist.get(0);
//        String s = "";
//        s = r.toStringNodeTree(r.pt.root);
//        System.out.println(s);

    }
}