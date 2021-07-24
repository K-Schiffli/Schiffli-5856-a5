/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments;

import ucf.assignments.Lists.InventoryList;
import ucf.assignments.Lists.Items.InventoryItem;
import java.util.List;

public class ListHandler {

    //Create a new ToDoList object
    static InventoryList il = new InventoryList();


    //Create a function to search the list items
    public static List<InventoryItem> getSearchedItems(String searchTerm, String searchColumn) {

        if (!searchTerm.equals("")&&!searchColumn.equals("Show All")) {
            return il.searchItems(searchTerm, searchColumn);
        }
        else return il.getItems();
    }
}
