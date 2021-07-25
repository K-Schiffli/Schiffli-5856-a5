/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;
import ucf.assignments.Lists.InventoryList;
import ucf.assignments.Lists.Items.InventoryItem;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemSearchHandlerTest {
    ItemSearchHandler isHandler = new ItemSearchHandler();

    @Test
    void searchItems_should_return_matching_serial_numbers() {
        //Create an array of expected items
        List<InventoryItem> expected = new ArrayList<>();

        //Populate the itemList list in the InventoryList object with test items
        //Populate the expected list with every item at the same time
        InventoryItem testItem = new InventoryItem("16USD73K93", "$1.99", "Trinket");
        ListHandler.il.itemList.add(testItem);
        testItem = new InventoryItem("38USF24G82", "$24.50", "Thing");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("57HKR10L82", "$145.45", "Widget");
        ListHandler.il.itemList.add(testItem);
        testItem = new InventoryItem("14USF2321R", "$1999.00", "Doodad");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);

        //Call the searchItems function with the name column and a term that should result in the expected list
        List<InventoryItem> result = isHandler.searchItems("USF", "serialNum");

        //Assert that every item in the result list is present in the expected list
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getSerialNum(), result.get(i).getSerialNum());
            assertEquals(expected.get(i).getPrice(), result.get(i).getPrice());
            assertEquals(expected.get(i).getName(), result.get(i).getName());
        }

        //Clear the itemsList
        ListHandler.il.itemList.clear();
    }

    @Test
    void searchItems_should_return_matching_names() {
        //Create an array of expected items
        List<InventoryItem> expected = new ArrayList<>();

        //Populate the itemList list in the InventoryList object with test items
        //Populate the expected list with every item at the same time
        InventoryItem testItem = new InventoryItem("16USD73K93", "$1.99", "Trinket");
        ListHandler.il.itemList.add(testItem);
        testItem = new InventoryItem("38USF24G82", "$24.50", "Thing Item");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("57HKR10L82", "$145.45", "Item Widget");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("14CJT99T21", "$1999.00", "Doodad");
        ListHandler.il.itemList.add(testItem);

        //Call the searchItems function with the name column and a term that should result in the expected list
        List<InventoryItem> result = isHandler.searchItems("Item", "name");

        //Assert that every item in the result list is present in the expected list
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getSerialNum(), result.get(i).getSerialNum());
            assertEquals(expected.get(i).getPrice(), result.get(i).getPrice());
            assertEquals(expected.get(i).getName(), result.get(i).getName());
        }

        //Clear the itemsList
        ListHandler.il.itemList.clear();
    }
}