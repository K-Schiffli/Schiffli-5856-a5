/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments.Lists;

import ucf.assignments.Lists.Items.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class InventoryList {

    //Create a List of InventoryItem objects
    public List<InventoryItem> itemList = new ArrayList<>();

    //Create a method to create a new InventoryItem object and add it to the List of InventoryItem objects
    public void addItem() {

        //Create a new InventoryItem object with placeholder values
        InventoryItem newItem = new InventoryItem("XXXXXXXXXX", "Input Price", "Input Name");

        //Add the new InventoryItem to the list
        itemList.add(newItem);
    }

    //Create a method to delete a specified InventoryItem from the list
    public void removeItem(InventoryItem item){

        //Remove the passed-in InventoryItem from the List
        itemList.remove(item);
    }

    //Create a method to return all the items in the List of InventoryItem objects
    public List<InventoryItem> getItems() {

        //Return the list of InventoryItems
        return itemList;
    }

    //Create a method to search the list by completeness
    public List<InventoryItem> searchItems(String searchTerm, String searchColumn) {

        //Create a new List of ToDoItems to hold the output
        List<InventoryItem> searchedItemList = new ArrayList<>();

        //Loop through the list

        //Return the resulting list
        return searchedItemList;
    }

    public boolean validateDesc(String description) {
        //Check that the description is no longer than than 256 characters
        return description.length() <= 256 && description.length() != 0;
    }
}
