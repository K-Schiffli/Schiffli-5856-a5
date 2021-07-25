/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments;

import ucf.assignments.Lists.Items.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class ItemSearchHandler {

    //Create a function to search the list items
    public List<InventoryItem> getSearchedItems(String searchTerm, String searchColumn) {

        if (!searchTerm.equals("")&&!searchColumn.equals("Show All")) {
            return searchItems(searchTerm, searchColumn);
        }
        else return ListHandler.il.getItems();
    }

    //Create a method to search the list based on the term and the column
    public List<InventoryItem> searchItems(String searchTerm, String searchColumn) {

        //Create a new List of ToDoItems to hold the output
        List<InventoryItem> searchedItemList = new ArrayList<>();

        //Loop through the list
        for (InventoryItem inventoryItem : ListHandler.il.itemList) {
            if (searchColumn.equals("serialNum") && inventoryItem.getSerialNum().contains(searchTerm))
                searchedItemList.add(inventoryItem);
            else if (searchColumn.equals("name") && inventoryItem.getName().contains(searchTerm))
                searchedItemList.add(inventoryItem);
        }

        //Return the resulting list
        return searchedItemList;
    }
}
