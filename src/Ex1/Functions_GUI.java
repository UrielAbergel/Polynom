package Ex1;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Functions_GUI implements functions {
    LinkedList<function> Flist;
    @Override
    public void initFromFile(String file) throws IOException {

    }

    @Override
    public void saveToFile(String file) throws IOException {

    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

    }

    @Override
    public void drawFunctions(String json_file) {

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
}
