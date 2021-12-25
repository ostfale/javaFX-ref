package de.ostfale.app.person.controller;

import de.ostfale.app.person.model.Person;
import de.ostfale.common.DataModel;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonContextMenuHandler {

    private final Logger log = LoggerFactory.getLogger(PersonContextMenuHandler.class);

    private final TableView<Person> tableView;
    private final DataModel<Person> dataModel;

    public PersonContextMenuHandler(TableView<Person> tableView, DataModel<Person> dataModel) {
        this.tableView = tableView;
        this.dataModel = dataModel;
    }

    public void initContextMenu() {
        tableView.setRowFactory(tv -> {
            final TableRow<Person> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            contextMenu.getItems().addAll(createAddPersonMenu(), createDeletePersonMenu());
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );
            return row;
        });
    }

    private MenuItem createAddPersonMenu() {
        log.debug("Create add person menu item..");
        MenuItem menuItem = new MenuItem("Add Person");
        menuItem.setOnAction(e -> addPerson(dataModel));
        return menuItem;
    }

    private MenuItem createDeletePersonMenu() {
        log.debug("Create delete person menu item..");
        MenuItem menuItem = new MenuItem("Delete Person");
        menuItem.setOnAction(e -> deletePerson(dataModel));
        return menuItem;
    }

    private void addPerson(DataModel<Person> dataModel) {
        log.debug("Add new (default) person object...");
        dataModel.getObjectList().add(Person.getDefault());
    }

    private void deletePerson(DataModel<Person> dataModel) {
        var selectedPerson = dataModel.getCurrentObject();
        log.debug("Remove selected person: {} {}", selectedPerson.firstName(), selectedPerson.lastName());
        dataModel.getObjectList().remove(selectedPerson);
    }
}
