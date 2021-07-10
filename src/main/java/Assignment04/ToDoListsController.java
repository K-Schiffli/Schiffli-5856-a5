package Assignment04;

import Assignment04.Lists.Items.ToDoItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.*;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.cell.TextFieldTableCell.*;

public class ToDoListsController {
    public TableView<ToDoItem> itemsTable;
    public TableColumn<Object, Boolean> doneColumn;
    public TableColumn<Object, String> dueDateColumn;
    public TableColumn<Object,String> descriptionColumn;
    public Button newItemButton;
    public Button deleteItemButton;
    public Button saveListButton;
    public Button completeButton;
    public Button loadListButton;
    public TextField listTitleBox;

    //Create a new instance of the ListHandler  class
    ListHandler lh = new ListHandler();

    //Create a variable for the last chosen file directory for the save/load functions
    File lastChosenDirectory = null;


    private final ObservableList<ToDoItem> items = FXCollections.observableArrayList();

    public void editedDueDate(TableColumn.CellEditEvent editedCell) {

        //Get the currently selected item
        ToDoItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //Set the DueDate of the selected item to the new text
        selItem.setDueDate(editedCell.getNewValue().toString());
    }

    public void editedDescription(TableColumn.CellEditEvent editedCell) {

        //Get the currently selected item
        ToDoItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //Set the DueDate of the selected item to the new text
        selItem.setDescription(editedCell.getNewValue().toString());
    }

    public void clickedNewItem(ActionEvent actionEvent) {
        //Call the addItem method of the ToDoList
        lh.tdl.addItem();
        //Refresh the displayed list
        itemsTable.getItems().clear();
        items.addAll(lh.tdl.getItems());
        itemsTable.setItems(items);
    }

    public void clickedDeleteItem(ActionEvent actionEvent) {
        //Get the selected ToDoItem
        ToDoItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //Display a pop-up confirmation alert to confirm deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //If the user selects Yes, call the removeItem method of the current object with the selected item
            lh.tdl.removeItem(selItem);
            //Refresh the displayed list
            itemsTable.getItems().clear();
            items.addAll(lh.tdl.getItems());
            itemsTable.setItems(items);
        }
    }

    public void clickedSaveList(ActionEvent actionEvent) {
        //Open an Explorer window to get the save folder path
        String saveFilePath = null;
        DirectoryChooser dc = new DirectoryChooser();
        if (lastChosenDirectory != null)dc.setInitialDirectory(lastChosenDirectory);

        dc.setTitle("Select Directory To Save To");
        File saveFile = dc.showDialog(saveListButton.getScene().getWindow());
        if (saveFile != null)
        {
            saveFilePath = saveFile.getPath();
            lastChosenDirectory=saveFile;
        }
        else System.out.println("ERROR: Failed to find file path");

        //Call the saveList function of the ToDoList object with the requested save folder
        lh.saveList(saveFilePath, listTitleBox.getText());
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
            lh.loadList(loadFilePath);
            listTitleBox.setText(loadFile.getName().replace(".json", ""));

            //Refresh the content of the table
            itemsTable.getItems().clear();
            items.addAll(lh.tdl.getItems());
            itemsTable.setItems(items);
        }
    }

    public void clickedCompleteButton(ActionEvent actionEvent) {
        //Get the selected ToDoItem
        ToDoItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //Check the completeness of the selected item.
        //If it's true, set it to false
        if (selItem.getCompleteness()) selItem.setCompleteness(false);
            //Else if it's false, set it to true
        else selItem.setCompleteness(true);

        //Refresh the table with the current values
        itemsTable.getItems().clear();
        items.addAll(lh.tdl.getItems());
        itemsTable.setItems(items);
    }

    public void clickedFilterComp(ActionEvent actionEvent) {
        //Call the filterItems method of the ToDoList with true as the parameter
        //and load the returned list into the table
        itemsTable.getItems().clear();
        items.addAll(lh.tdl.filterItems(true));
        itemsTable.setItems(items);
    }

    public void clickedFilterIncomp(ActionEvent actionEvent) {
        //Call the filterItems method of the ToDoList with false as the parameter
        //and load the returned list into the table
        itemsTable.getItems().clear();
        items.addAll(lh.tdl.filterItems(false));
        itemsTable.setItems(items);
    }

    public void clickedFilterAll(ActionEvent actionEvent) {
        //Refresh the table by calling the getItems method of the ToDoList object
        itemsTable.getItems().clear();
        items.addAll(lh.tdl.getItems());
        itemsTable.setItems(items);
    }

    public void clickedClearList(ActionEvent actionEvent) {
        //Display a confirmation pop-up window
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to clear all items?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //If the user clicks Yes, loop through the list of ToDoList items and call the removeItem method on each one.
            int length = lh.tdl.getItems().size();
            for (int i = (length - 1); i >= 0; i--){
                lh.tdl.removeItem(lh.tdl.itemList.get(i));
            }

            //Clear the displayed items
            listTitleBox.clear();
            itemsTable.getItems().clear();
        }
    }

    public void editDueDateEnd(TableColumn.CellEditEvent<?,?> editedCell) {
        //Get the currently selected item
        ToDoItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //Set the DueDate of the selected item to the new text
        selItem.setDueDate(editedCell.getNewValue().toString());
    }

    public void editDescriptionEnd(TableColumn.CellEditEvent<?,?> editedCell) {
        //Get the currently selected item
        ToDoItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //Set the DueDate of the selected item to the new text
        selItem.setDescription(editedCell.getNewValue().toString());
    }
}
