package Ex1;
import java.io.*;
import java.util.Scanner;
import java.util.*;
import  Ex1.ComplexFunction;

public class Functions_GUI implements functions {
    LinkedList<function> Flist;

    public Functions_GUI(){
        LinkedList<function> temp = new LinkedList<function>();
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
                t = (ComplexFunction) t.initFromString(Ans);
                if(Flist == null)
                {
                    LinkedList<function> p = new LinkedList<function>();
                    Flist = p ;
                }
                Flist.add(t);
                Ans = "";

            }
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
            ComplexFunction p = (ComplexFunction) Flist.get(i);
            s = p.pt.toString();
            writeOnes.write(s);
            s = "";
            writeOnes.write("\n") ;

        }
        writeOnes.close();

    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

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
        System.out.println("pppp");
        String s = "]";
        p.saveToFile("TheSAVE");
//        ComplexFunction r = (ComplexFunction) p.Flist.get(0);
//        String s = "";
//        s = r.toStringNodeTree(r.pt.root);
//        System.out.println(s);

    }
}

