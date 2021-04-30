package logic;

import logic.elements.WWElement;

import java.util.Iterator;
import java.util.LinkedList;

public class WWElementGroup implements Iterable<WWElement> {

    private LinkedList <WWElement> elemList;

    public WWElementGroup(){
        elemList = new LinkedList<>();
    }

    public void add(WWElement element){
        elemList.add(element);
    }

    @Override
    public Iterator<WWElement> iterator() {
        return elemList.iterator();
    }
}
