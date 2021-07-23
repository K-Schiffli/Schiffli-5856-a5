/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments;

import ucf.assignments.Lists.Items.InventoryItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.*;

import java.io.File;

public class InventoryListsController {
    public TableView<InventoryItem> itemsTable;
    public TableColumn<InventoryItem, String> serialNumberColumn;
    public TableColumn<InventoryItem, String> priceColumn;
    public TableColumn<InventoryItem,String> nameColumn;
    public Button saveListButton;
    public Button loadListButton;
    public TextField listTitleBox;
    public Button helpButton;
    public MenuItem deleteItemButton;
    public MenuItem clearListButton;
    public MenuItem addItemButton;
    public MenuButton editButton;
    public MenuItem searchByNameButton;
    public MenuItem searchBySerialNumButton;
    public TextField searchTermBox;

    //Create a variable for the last chosen file directory for the save/load functions
    File lastChosenDirectory = null;
    String searchTerm = "";
    String searchColumn = "Show All";

    private final ObservableList<InventoryItem> items = FXCollections.observableArrayList();
    private final ObservableList<InventoryItem> currentItems = FXCollections.observableArrayList();

    public void clickedAddItem(ActionEvent actionEvent) {
        //Call the addItem method of the ToDoList
        ListHandler.il.addItem();
        //Clear current filter and refresh the displayed list
        itemsTable.getItems().clear();
        currentItems.addAll(ListHandler.getSearchedItems("", "Show All"));
        itemsTable.setItems(currentItems);
    }

    public void clickedDeleteItem(ActionEvent actionEvent) {
        //Get the selected ToDoItem
        InventoryItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //If the selected item exists...
        if (selItem != null) {
            //Display a pop-up confirmation alert to confirm deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                //If the user selects Yes, call the removeItem method of the current object with the selected item
                ListHandler.il.removeItem(selItem);

                //Refresh the displayed list
                itemsTable.getItems().clear();
                currentItems.addAll(ListHandler.getSearchedItems(searchTerm, searchColumn));
                itemsTable.setItems(currentItems);
            }
        }
    }

    public void clickedSaveList(ActionEvent actionEvent) {
        //Check that the list has a title
        if (!listTitleBox.getText().equals("")) {
            //Open an Explorer window to get the save folder path
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
            //Display an alert if the list has no title
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a list title.", ButtonType.OK);
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
                currentItems.addAll(ListHandler.getSearchedItems("", "Show All"));
                itemsTable.setItems(currentItems);
            }
        }
    }

    public void clickedClearList(ActionEvent actionEvent) {
        //Display a confirmation pop-up window
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to clear all items?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //If the user clicks Yes, loop through the list of ToDoList items and call the removeItem method on each one.
            int length = ListHandler.il.getItems().size();
            for (int i = (length - 1); i >= 0; i--){
                ListHandler.il.removeItem(ListHandler.il.itemList.get(i));
            }

            //Clear the displayed items
            listTitleBox.clear();
            itemsTable.getItems().clear();
        }
    }

    public void editedName(TableColumn.CellEditEvent editedCell) {

        //Get the currently selected item
        InventoryItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //If the selected item exists...
        if (selItem != null) {
            //Get the new description from the box
            String newName = editedCell.getNewValue().toString();

            //Validate the description
            //If the description is within length requirements, set it as the new description
            if (ListHandler.il.validateName(newName)) selItem.setName(newName);
            //Else notify the user of the constraint
            Alert alert = new Alert(Alert.AlertType.WARNING, "Item name must be between 2 and 256 characters, inclusive.", ButtonType.OK);
            alert.showAndWait();

            //Refresh the displayed items
            itemsTable.getItems().clear();
            currentItems.addAll(ListHandler.getSearchedItems(searchTerm, searchColumn));
            itemsTable.setItems(currentItems);
        }
    }

    public void editNameEnd(TableColumn.CellEditEvent<?,?> editedCell) {

        //Get the currently selected item
        InventoryItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //If the selected item exists...
        if (selItem != null) {
            //Get the new description from the box
            String newName= editedCell.getNewValue().toString();

            //Validate the description
            //If the description is within length requirements, set it as the new description
            if (ListHandler.il.validateName(newName)) selItem.setName(newName);
                //Else notify the user of the constraint
            Alert alert = new Alert(Alert.AlertType.WARNING, "Item name must be between 2 and 256 characters, inclusive.", ButtonType.OK);
            alert.showAndWait();

            //Refresh the displayed items
            itemsTable.getItems().clear();
            currentItems.addAll(ListHandler.getSearchedItems(searchTerm, searchColumn));
            itemsTable.setItems(currentItems);
        }
    }

    public void editedSerialNumber(TableColumn.CellEditEvent<InventoryItem, String> editedCell) {
        //Get the currently selected item
        InventoryItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //If the selected item exists...
        if (selItem != null) {
            //Get the new serial number from the box
            String newSerialNum = editedCell.getNewValue().toString();

            //Validate the serial number
            //Check that the serial number matches the required format and it's not a duplication
            if (!ListHandler.il.validateSerialNum(newSerialNum)) {
                //Else notify the user of the constraint
                Alert alert = new Alert(Alert.AlertType.WARNING, "Serial Number doesn't match required format", ButtonType.OK);
                alert.showAndWait();
            }
            else if (ListHandler.il.checkForSerialNumDupe(newSerialNum)){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Serial Number already in use", ButtonType.OK);
                alert.showAndWait();
            }
            //If it checks out, set it as the new serial number
            else
            {
                selItem.setSerialNum(newSerialNum);
            }

            //Refresh the displayed items
            itemsTable.getItems().clear();
            currentItems.addAll(ListHandler.getSearchedItems(searchTerm, searchColumn));
            itemsTable.setItems(currentItems);
        }
    }

    public void editSerialNumberEnd(TableColumn.CellEditEvent<InventoryItem, String> editedCell) {
        //Get the currently selected item
        InventoryItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //If the selected item exists...
        if (selItem != null) {
            //Get the new serial number from the box
            String newSerialNum = editedCell.getNewValue().toString();

            //Validate the serial number
            //Check that the serial number matches the required format and it's not a duplication
            if (!ListHandler.il.validateSerialNum(newSerialNum)) {
                //Else notify the user of the constraint
                Alert alert = new Alert(Alert.AlertType.WARNING, "Serial Number doesn't match required format", ButtonType.OK);
                alert.showAndWait();
            }
            else if (ListHandler.il.checkForSerialNumDupe(newSerialNum)){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Serial Number already in use", ButtonType.OK);
                alert.showAndWait();
            }
            //If it checks out, set it as the new serial number
            else
            {
                selItem.setSerialNum(newSerialNum);
            }

            //Refresh the displayed items
            itemsTable.getItems().clear();
            currentItems.addAll(ListHandler.getSearchedItems(searchTerm, searchColumn));
            itemsTable.setItems(currentItems);
        }
    }

    public void editedPrice(TableColumn.CellEditEvent<InventoryItem, String> editedCell) {
        //Get the currently selected item
        InventoryItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //If the selected item exists...
        if (selItem != null) {
            //Get the new description from the box
            String newPrice = editedCell.getNewValue().toString();

            //Validate the description
            //If the description is within length requirements, set it as the new description
            if (ListHandler.il.validatePrice(newPrice)) selItem.setPrice(newPrice);
            //Else notify the user of the constraint
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Price must be a valid U.S. dollar amount", ButtonType.OK);
                alert.showAndWait();
            }

            //Refresh the displayed items
            itemsTable.getItems().clear();
            currentItems.addAll(ListHandler.getSearchedItems(searchTerm, searchColumn));
            itemsTable.setItems(currentItems);
        }
    }
    
    public void editPriceEnd(TableColumn.CellEditEvent<InventoryItem, String> editedCell) {
        //Get the currently selected item
        InventoryItem selItem = itemsTable.getSelectionModel().getSelectedItem();

        //If the selected item exists...
        if (selItem != null) {
            //Get the new description from the box
            String newPrice = editedCell.getNewValue().toString();

            //Validate the description
            //If the description is within length requirements, set it as the new description
            if (ListHandler.il.validatePrice(newPrice)) selItem.setPrice(newPrice);
            //Else notify the user of the constraint
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Price must be a valid U.S. dollar amount", ButtonType.OK);
                alert.showAndWait();
            }

            //Refresh the displayed items
            itemsTable.getItems().clear();
            currentItems.addAll(ListHandler.getSearchedItems(searchTerm, searchColumn));
            itemsTable.setItems(currentItems);
        }
    }

    public void clickedSearchBySerialNum(ActionEvent actionEvent) {
        //Set the searchColumn variable to the selected column
        searchColumn = "serialNum";

        //Get the search term from the box
        searchTerm = searchTermBox.getText();

        //Call the search function and load that into the table
        itemsTable.getItems().clear();
        currentItems.addAll(ListHandler.getSearchedItems(searchTerm, searchColumn));
        itemsTable.setItems(currentItems);
    }

    public void clickedSearchByName(ActionEvent actionEvent) {
        //Set the searchColumn variable to the selected column
        searchColumn = "name";

        //Get the search term from the box
        searchTerm = searchTermBox.getText();

        //Call the search function and load that into the table
        itemsTable.getItems().clear();
        currentItems.addAll(ListHandler.getSearchedItems(searchTerm, searchColumn));
        itemsTable.setItems(currentItems);
    }

    public void clickedHelp(ActionEvent actionEvent) {
        //Display an information pop-up with instructions
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "To add a new item, click the \"New Item\" button.\n\n" +
                "To edit an it's description, double-click its description box. Press enter to save your changes.\n\n" +
                "To give an item a due date, click to select it, then pick or enter a date using the date box at the bottom.\n\n" +
                "To mark an item as complete, click the check box next to it.\n\n" +
                "To delete an item, click on it to select it and click the \"Delete Item\" button.\n\n" +
                "To clear the list, click the \"Clear List\" button.\n\n" +
                "To sort the list, double-click the header of the column you want the list sorted by.\n\n" +
                "To filter the list, click the \"Show Complete\", \"Show Incomplete\", or \"Show All\" buttons.\n\n" +
                "To save a list, enter a name for it in the box at the bottom and click the \"Save List\" button.\n\n" +
                "To load a list, click the \"Load List\" button and choose the list you want to load. This will clear the current list.\n\n" +
                "This help screen is dedicated to everyone except Rey.", ButtonType.OK);
        alert.showAndWait();
    }

}
