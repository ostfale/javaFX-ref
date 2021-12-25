package de.ostfale.common;

import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditCell<S, T> extends TextFieldTableCell<S, T> {

    private final Logger log = LoggerFactory.getLogger(EditCell.class);

    private TextField textField;
    private boolean escapePressed = false;
    private TablePosition<S, ?> tablePos = null;

    public EditCell(final StringConverter<T> converter) {
        super(converter);
    }

    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> forTableColumn(final StringConverter<T> converter) {
        return list -> new EditCell<>(converter);
    }

    @Override
    public void startEdit() {
        if (!isEditable() || !getTableView().isEditable() || !getTableColumn().isEditable()) {
            log.trace("ECell: start edit failed...");
            return;
        }
        super.startEdit();
        if (isEditing()) {
            if (textField == null) {
                textField = getTextField();
            }
            escapePressed = false;
            startEdit(textField);
            final TableView<S> table = getTableView();
            tablePos = table.getEditingCell();
        }
    }

    @Override
    public void commitEdit(T newValue) {
        if (!isEditing()) {
            return;
        }
        final TableView<S> table = getTableView();
        if (table != null) {
            // inform the TableView of the edit being ready to be committed.
            var editEvent = new TableColumn.CellEditEvent(table, tablePos, TableColumn.editCommitEvent(), newValue);
            Event.fireEvent(getTableColumn(), editEvent);
        }
        super.cancelEdit();
        updateItem(newValue, false);
        if (table != null) {
            table.edit(-1, null);
        }
    }

    @Override
    public void cancelEdit() {
        if (escapePressed) {
            super.cancelEdit();
            setText(getItemText());
        } else {
            // this is not a cancel event after escape key we interpret it as commit.
            String newText = textField.getText();
            this.commitEdit(getConverter().fromString(newText)); // commit new text to model
        }
        setGraphic(null);
    }

    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        updateItem();
    }

    private TextField getTextField() {
        log.trace("ECell: create new TextField...");
        final TextField tf = new TextField(getItemText());

        return tf;
    }

    private void updateItem() {
        if (isEmpty()) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getItemText());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getItemText());
                setGraphic(null);
            }
        }
    }

    private void startEdit(final TextField textField) {
        if (textField != null) {
            textField.setText(getItemText());
        }
        setText(null);
        setGraphic(textField);
        // requesting focus so that key input can immediately go into the TextField
        textField.requestFocus();
    }

    private String getItemText() {
        if (getConverter() == null) {
            if (getItem() == null) return "";
            return getItem().toString();
        } else {
            return getConverter().toString(getItem());
        }
    }
}
