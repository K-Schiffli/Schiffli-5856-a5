/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package Assignment04.Lists;

import Assignment04.Lists.Items.ToDoItem;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {

    //Create a List of ToDoitem objects
    public List<ToDoItem> itemList = new ArrayList<>();

    //Create a method to create a new ToDoItem object and add it to the List of ToDoItem objects
    public void addItem() {

        //Create a new ToDoItem object with placeholder values
        ToDoItem newItem = new ToDoItem(false, "Select Date Below", "Enter a description");

        //Add the new ToDoItem to the list
        itemList.add(newItem);
    }

    //Create a method to delete a specified ToDoItem from the list
    public void removeItem(ToDoItem item){

        //Remove the passed-in ToDoItem from the List
        itemList.remove(item);
    }

    //Create a method to return all the items in the List of ToDoItem objects
    public List getItems() {

        //Return the list of ToDoItems
        return itemList;
    }

    //Create a method to filter the list by completeness
    public List<ToDoItem> filterItems(boolean selector) {

        //Create a new List of ToDoItems to hold the output
        List<ToDoItem> filteredItemList = new ArrayList<>();

        //Loop through the list
        for (ToDoItem toDoItem : itemList) {
            //Check each item's completeness against the selector
            //If they match, add them to the new list
            if (toDoItem.getCompleteness() == selector) filteredItemList.add(toDoItem);
        }

        //Return the resulting list
        return filteredItemList;
    }

    public boolean validateDesc(String description) {
        //Check that the description is no longer than than 256 characters
        return description.length() <= 256 && description.length() != 0;
    }
}
