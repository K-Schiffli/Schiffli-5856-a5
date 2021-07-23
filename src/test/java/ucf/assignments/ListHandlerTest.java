/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments;

import ucf.assignments.Lists.Items.InventoryItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class ListHandlerTest {

    @Test
    void saveList_should_properly_save_the_list_and_loadList_should_properly_load_it() {
        //Create an expected list containing all the test items
        List<InventoryItem> expected = new ArrayList<>();

        //Populate the itemList list in the ToDoList object with test items
        //Populate the expected list with every item at the same time
        InventoryItem testItem = new InventoryItem("XXXXXXXXXX", "2021-07-11", "Turn in programming assignment");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("XXXXXXXXXX", "2021-07-13", "Feed the cat");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("XXXXXXXXXX", "2021-07-08", "Take out the trash");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem("XXXXXXXXXX", "2021-07-28", "Get a job");
        ListHandler.il.itemList.add(testItem);
        expected.add(testItem);

        //Run the saveList method with a test file path
        ListHandler.saveList("TestSaves","TestList");

        //Clear the itemsList
        ListHandler.il.itemList.clear();

        //Run the loadList method on the save file that should now exist
        ListHandler.loadList("TestSaves/TestList.json");

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