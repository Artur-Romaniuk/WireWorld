package logic;

import logic.elements.WWElement;
import logic.elements.complex.WWComplexElement;
import logic.elements.simple.WWElementConductor;
import logic.elements.simple.WWElementElectronHead;
import logic.elements.simple.WWElementElectronTail;
import logic.elements.simple.WWSimpleElement;

import java.util.Iterator;
import java.util.LinkedList;

public class WWElementGroup {

    private LinkedList<WWComplexElement> elemList;

    private LinkedList<WWElementElectronHead> electronHeadList;
    private LinkedList<WWElementElectronTail> electronTailList;
    private LinkedList<WWElementConductor> conductorList;
    private LinkedList<WWElementConductor> allConductorList;

    private int maxRow;                     //skrajne współrzędne elementów, dzięki nim będzie można przeskalować mapę
    private int minRow;                     //tak żeby móc dodawać współrzędne ujemne
    private int maxColumn;
    private int minColumn;

    public WWElementGroup() {
        elemList = new LinkedList<>();              //LISTA ZŁOŻONYCH ELEMENTÓW NP DIODA
        electronHeadList = new LinkedList<>();
        electronTailList = new LinkedList<>();
        conductorList = new LinkedList<>();         //LISTA PRZEWODNIKÓW WCZYTANYCH Z WEJŚCIA
        allConductorList = new LinkedList<>();      //LISTA WSZYSTKICH PRZEWODNIKÓW
        maxRow = maxColumn = Integer.MIN_VALUE;
        minRow = minColumn = Integer.MAX_VALUE;
    }

    public void add(WWElement element) {        //DODAJE ELEMENT DO GRUPY
        if (element instanceof WWComplexElement) {
            elemList.add((WWComplexElement) element);
            allConductorList.addAll(((WWComplexElement) element).getConductors());
        } else if (element instanceof WWElementElectronHead) {
            electronHeadList.add((WWElementElectronHead) element);
            conductorList.add(new WWElementConductor(element.getRow(),element.getColumn()));
        } else if (element instanceof WWElementElectronTail) {
            electronTailList.add((WWElementElectronTail) element);
            conductorList.add(new WWElementConductor(element.getRow(),element.getColumn()));
        } else if (element instanceof WWElementConductor) {
            conductorList.add((WWElementConductor) element);
            allConductorList.add((WWElementConductor) element);
        }
        checkElement(element);
    }

    private void checkElement(WWElement element) {  //SPRAWDZA CZY ELEMENT NIE WYKRACZA POZA ZAKRES
        checkElementSize(element);

        if (element instanceof WWComplexElement) {
            LinkedList<WWElementConductor> list = ((WWComplexElement) element).getConductors();
            for (WWElementConductor e : list) {
                checkElementSize(e);
            }
        }
    }

    private void checkElementSize(WWElement element){
        if (element.getColumn() > this.maxColumn)
            this.maxColumn = element.getColumn();
        if (element.getColumn() < this.minColumn)
            this.minColumn = element.getColumn();
        if (element.getRow() > this.maxRow)
            this.maxRow = element.getRow();
        if (element.getRow() < this.minRow)
            this.minRow = element.getRow();
    }

    public int getMaxRow() {
        return maxRow;
    }

    public int getMinRow() {
        return minRow;
    }

    public int getMaxColumn() {
        return maxColumn;
    }

    public int getMinColumn() {
        return minColumn;
    }

    public LinkedList<WWElementConductor> getConductorList() {
        return conductorList;
    }

    public LinkedList<WWElementConductor> getAllConductorList() {
        return allConductorList;
    }

    public LinkedList<WWElementElectronHead> getElectronHeadList() {
        return electronHeadList;
    }

    public LinkedList<WWElementElectronTail> getElectronTailList() {
        return electronTailList;
    }

    public LinkedList<WWComplexElement> getElemList() {
        return elemList;
    }

    public Iterator<WWComplexElement> elementIterator() {
        return elemList.iterator();
    }

    public Iterator<WWElementElectronHead> electronHeadIterator() {
        return electronHeadList.iterator();
    }

    public Iterator<WWElementElectronTail> electronTailIterator() {
        return electronTailList.iterator();
    }

    public Iterator<WWElementConductor> conductorIterator() {
        return conductorList.iterator();
    }

}
