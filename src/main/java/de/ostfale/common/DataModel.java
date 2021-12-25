package de.ostfale.common;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

public class DataModel<T> {

    private final Logger log = LoggerFactory.getLogger(DataModel.class);

    private final ObservableList<T> objectList = FXCollections.observableArrayList();
    private final ObjectProperty<T> currentObject = new SimpleObjectProperty<>();

    private ChangeListener<T> changeListener = null;
    private Comparator<T> comparator = null;

    public ObservableList<T> getObjectList() {
        return objectList;
    }

    public void updateModel(List<T> aList, TableView<T> tableView) {
        log.debug("Update model for TableView");
        if (!currentObject.isBound()) {
            log.trace("Bind selected table row");
            currentObject.bind(tableView.getSelectionModel().selectedItemProperty());
            currentObject.addListener(changeListener);
        }

        if (comparator != null) {
            var sortedList = this.getSortedList(comparator);
            tableView.setItems(sortedList);
            sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        } else {
            tableView.setItems(objectList);
        }
        setItemList(aList);
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

    public void setChangeListener(ChangeListener<T> changeListener) {
        this.changeListener = changeListener;
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private void setItemList(List<T> aList) {
        objectList.clear();
        objectList.addAll(aList);
    }
}
