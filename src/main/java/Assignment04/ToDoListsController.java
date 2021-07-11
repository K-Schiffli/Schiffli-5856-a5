/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 first_name last_name
 */
package Assignment04;

import Assignment04.Lists.Items.ToDoItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.*;
import java.io.File;
import java.time.LocalDate;

public class ToDoListsController {
    public TableView<ToDoItem> itemsTable;
    public TableColumn<ToDoItem, Boolean> doneColumn;
    public TableColumn<Object, String> dueDateColumn;
    public TableColumn<Object,String> descriptionColumn;
    public Button newItemButton;
    public Button deleteItemButton;
    public Button saveListButton;
    public Button loadListButton;
    public TextField listTitleBox;
    public Button filterIncompButton;
    public Button filterAllButton;
    public Button filterCompButton;
    public Button clearListButton;
    public DatePicker datePicker;

    //Create a variable for the last chosen file directory for the save/load functions
    File lastChosenDirectory = null;
    String filter = "all";


    private final ObservableList<ToDoItem> items = FXCollections.observableArrayList();
    private final ObservableList<ToDoItem> currentItems = FXCollections.observableArrayList();

    public void initialize() {
        //This callback tell the cell how to bind the data model 'Registered' property to
        //the cell, itself.
        doneColumn.setCellValueFactory(
                param -> param.getValue().completeness);
    }

    public void editedDescription(TableColumn.CellEditEvent editedCell) {

        //Get the currently selected item
        ToDoItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //If the selected item exists...
        if (selItem != null) {
            //Get the new description from the box
            String newDescription = editedCell.getNewValue().toString();

            //Validate the description
            //If the description is within length requirements, set it as the new description
            if (ListHandler.tdl.validateDesc(newDescription)) selItem.setDescription(newDescription);
                //Else notify the user of the constraint
            else selItem.setDescription("Description must be less than 256 characters.");

            //Refresh the displayed items
            itemsTable.getItems().clear();
            currentItems.addAll(ListHandler.getFilteredItems(filter));
            itemsTable.setItems(currentItems);
        }
    }

    public void clickedNewItem(ActionEvent actionEvent) {
        //Call the addItem method of the ToDoList
        ListHandler.tdl.addItem();
        //Clear current filter and refresh the displayed list
        itemsTable.getItems().clear();
        currentItems.addAll(ListHandler.getFilteredItems("all"));
        itemsTable.setItems(currentItems);
    }

    public void clickedDeleteItem(ActionEvent actionEvent) {
        //Get the selected ToDoItem
        ToDoItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //If the selected item exists...
        if (selItem != null) {
            //Display a pop-up confirmation alert to confirm deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                //If the user selects Yes, call the removeItem method of the current object with the selected item
                ListHandler.tdl.removeItem(selItem);

                //Refresh the displayed list
                itemsTable.getItems().clear();
                currentItems.addAll(ListHandler.getFilteredItems(filter));
                itemsTable.setItems(currentItems);
            }
        }
    }

    public void clickedSaveList(ActionEvent actionEvent) {
        //Open an Explorer window to get the save folder path
        if (!listTitleBox.getText().equals("")) {
            String saveFilePath = null;
            DirectoryChooser dc = new DirectoryChooser();
            if (lastChosenDirectory != null) dc.setInitialDirectory(lastChosenDirectory);

            dc.setTitle("Select Directory To Save To");
            File saveFile = dc.showDialog(saveListButton.getScene().getWindow());
            if (saveFile != null) {
                saveFilePath = saveFile.getPath();
                lastChosenDirectory = saveFile;
                //Call the saveList function of the ToDoList object with the requested save folder
                ListHandler.saveList(saveFilePath, listTitleBox.getText());
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please enter a list title.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void clickedLoadList(ActionEvent actionEvent) {
        //Display a confirmation pop-up window
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to load a new list\n(This will clear the current list)", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            //Open an Explorer window to get the file path
            String loadFilePath = null;
            FileChooser fc = new FileChooser();
            if (lastChosenDirectory != null)fc.setInitialDirectory(lastChosenDirectory);

            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JSON", "*.json"));
            fc.setTitle("Select File To Load From");
            File loadFile = fc.showOpenDialog(loadListButton.getScene().getWindow());
            if (loadFile != null) {
                loadFilePath = loadFile.getPath();
                lastChosenDirectory=loadFile.getParentFile();
            } else System.out.println("ERROR: Failed to find file path");

            //Call the loadList method of the list handler to load the specified ToDoList
            ListHandler.loadList(loadFilePath);
            if (loadFile != null) {
                listTitleBox.setText(loadFile.getName().replace(".json", ""));

                //Refresh the content of the table
                itemsTable.getItems().clear();
                currentItems.addAll(ListHandler.getFilteredItems("all"));
                itemsTable.setItems(currentItems);
            }
        }
    }

    public void clickedFilterComp(ActionEvent actionEvent) {
        //Call the filterItems method of the ToDoList with true as the parameter
        //and load the returned list into the table
        filter = "true";
        itemsTable.getItems().clear();
        currentItems.addAll(ListHandler.getFilteredItems(filter));
        itemsTable.setItems(currentItems);
    }

    public void clickedFilterIncomp(ActionEvent actionEvent) {
        //Call the filterItems method of the ToDoList with false as the parameter
        //and load the returned list into the table
        filter = "false";
        itemsTable.getItems().clear();
        currentItems.addAll(ListHandler.getFilteredItems(filter));
        itemsTable.setItems(currentItems);
    }

    public void clickedFilterAll(ActionEvent actionEvent) {
        //Refresh the table by calling the getItems method of the ToDoList object
        filter = "all";
        itemsTable.getItems().clear();
        currentItems.addAll(ListHandler.getFilteredItems(filter));
        itemsTable.setItems(currentItems);
    }

    public void clickedClearList(ActionEvent actionEvent) {
        //Display a confirmation pop-up window
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to clear all items?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //If the user clicks Yes, loop through the list of ToDoList items and call the removeItem method on each one.
            int length = ListHandler.tdl.getItems().size();
            for (int i = (length - 1); i >= 0; i--){
                ListHandler.tdl.removeItem(ListHandler.tdl.itemList.get(i));
            }

            //Clear the displayed items
            listTitleBox.clear();
            itemsTable.getItems().clear();
        }
    }

    public void editDescriptionEnd(TableColumn.CellEditEvent<?,?> editedCell) {

        //Get the currently selected item
        ToDoItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //If the selected item exists...
        if (selItem != null) {
            //Get the new description from the box
            String newDescription = editedCell.getNewValue().toString();

            //Validate the description
            //If the description is within length requirements, set it as the new description
            if (ListHandler.tdl.validateDesc(newDescription)) selItem.setDescription(newDescription);
                //Else notify the user of the constraint
            else selItem.setDescription("Description must be less than 256 characters.");

            //Refresh the displayed items
            itemsTable.getItems().clear();
            currentItems.addAll(ListHandler.getFilteredItems(filter));
            itemsTable.setItems(currentItems);
        }
    }

    public void activatedDatePicker(ActionEvent actionEvent) {
        //Get the selected item
        ToDoItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //If the selected item exists...
        if (selItem != null) {
            //If the selected item exists, get the selected date from the datePicker
            LocalDate date = datePicker.getValue();
            //Convert it to a string
            String dateString = date.toString();
            selItem.setDueDate(dateString);
            //Refresh the displayed items
            itemsTable.getItems().clear();
            currentItems.addAll(ListHandler.getFilteredItems(filter));
            itemsTable.setItems(currentItems);
        }
        //Clear the text from the datePicker
        datePicker.getEditor().clear();
    }
}
