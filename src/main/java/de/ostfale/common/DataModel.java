package de.ostfale.common;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.util.Comparator;

public class DataModel<T> {

    private final ObservableList<T> objectList = FXCollections.observableArrayList();
    private final ObjectProperty<T> currentObject = new SimpleObjectProperty<>();

    public ObservableList<T> getObjectList() {
        return objectList;
    }

    public void setObjectList(ObservableList<T> aList) {
        objectList.clear();
        objectList.addAll(aList);
    }

    public SortedList<T> getSortedList(Comparator<T> aComparator) {
        SortedList<T> sortedList = new SortedList<>(objectList);
        sortedList.setComparator(aComparator);
        return sortedList;
    }

    public T getCurrentObject() {
        return currentObject.get();
    }

    public void setCurrentObject(T currentObject) {
        this.currentObject.set(currentObject);
    }

    public ObjectProperty<T> currentObjectProperty() {
        return currentObject;
    }
}
