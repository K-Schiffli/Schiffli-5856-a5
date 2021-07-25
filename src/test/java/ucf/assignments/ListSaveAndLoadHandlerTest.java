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

class ListSaveAndLoadHandlerTest {
    ListSaveAndLoadHandler snlHandler = new ListSaveAndLoadHandler();

    @Test
    void saveListAsJSON_should_properly_save_the_list_and_loadListAsJSON_should_properly_load_it() {
        //Create an expected list containing all the test items
        List<InventoryItem> expected = new ArrayList<>();

        //Populate the itemList list in the ToDoList object with test items
        //Populate the expected list with every item at the same time
        InventoryItem testItem = new InventoryItem("16USD73K93", "$1.99", "Trinket");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("38USF24G82", "$24.50", "Thing");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("57HKR10L82", "$145.45", "Widget");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("14CJT99T21", "$1999.00", "Doodad");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);

        //Run the saveList method with a test file path
        snlHandler.saveListAsJSON("TestSaves","TestListJSON");

        //Clear the itemsList
        ListHandler.il.itemList.clear();

        //Run the loadList method on the save file that should now exist
        snlHandler.loadListAsJSON("TestSaves/TestListJSON.json");

        //Get the repopulated list
        List<InventoryItem> result = ListHandler.il.getItems();

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
    void saveListAsTSV_should_properly_save_the_list_and_loadListAsTSV_should_properly_load_it() {
        //Create an expected list containing all the test items
        List<InventoryItem> expected = new ArrayList<>();

        //Populate the itemList list in the ToDoList object with test items
        //Populate the expected list with every item at the same time
        InventoryItem testItem = new InventoryItem("16USD73K93", "$1.99", "Trinket");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("38USF24G82", "$24.50", "Thing");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("57HKR10L82", "$145.45", "Widget");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("14CJT99T21", "$1999.00", "Doodad");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);

        //Run the saveList method with a test file path
        snlHandler.saveListAsTSV("TestSaves","TestListTSV");

        //Clear the itemsList
        ListHandler.il.itemList.clear();

        //Run the loadList method on the save file that should now exist
        snlHandler.loadListAsTSV("TestSaves/TestListTSV.txt");

        //Get the repopulated list
        List<InventoryItem> result = ListHandler.il.getItems();

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
    void saveListAsHTML_should_properly_save_the_list_and_loadListAsHTML_should_properly_load_it() {
        //Create an expected list containing all the test items
        List<InventoryItem> expected = new ArrayList<>();

        //Populate the itemList list in the ToDoList object with test items
        //Populate the expected list with every item at the same time
        InventoryItem testItem = new InventoryItem("16USD73K93", "$1.99", "Trinket");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("38USF24G82", "$24.50", "Thing");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("57HKR10L82", "$145.45", "Widget");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("14CJT99T21", "$1999.00", "Doodad");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);

        //Run the saveList method with a test file path
        snlHandler.saveListAsHTML("TestSaves","TestListHTML");

        //Clear the itemsList
        ListHandler.il.itemList.clear();

        //Run the loadList method on the save file that should now exist
        snlHandler.loadListAsHTML("TestSaves/TestListHTML.HTML");

        //Get the repopulated list
        List<InventoryItem> result = ListHandler.il.getItems();

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