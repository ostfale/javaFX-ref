package de.ostfale.common;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.Initializable;

public abstract class BaseController<T> implements Initializable {
    protected DataModel<T> dataModel;
    protected BooleanProperty modifiedProperty = new SimpleBooleanProperty(false);
    private ChangeListener<T> changeListener;
}
