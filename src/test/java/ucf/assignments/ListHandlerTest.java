/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments;

import ucf.assignments.Lists.Items.InventoryItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ListHandlerTest {

    @Test
    void getFilteredItems_should_return_all_items_when_given_all_as_filter_key() {
        //Create an expected list containing all the test items
        List<InventoryItem> expected = new ArrayList<>();

        //Populate the itemList list in the ToDoList object with test items
        //Populate the expected list with every item at the same time
        InventoryItem testItem = new InventoryItem(true, "2021-07-11", "Turn in programming assignment");
        ListHandler.tdl.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem(false, "2021-07-13", "Feed the cat");
        ListHandler.tdl.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem(true, "2021-07-08", "Take out the trash");
        ListHandler.tdl.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem(false, "2021-07-28", "Get a job");
        ListHandler.tdl.itemList.add(testItem);
        expected.add(testItem);

        //Call the getFilteredItems method with "all" as the key word
        List<InventoryItem> result = ListHandler.getSearchItems("all");
        //Assert that every item in the returned list is present in the expected list
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getCompleteness(), result.get(i).getCompleteness());
            assertEquals(expected.get(i).getDueDate(), result.get(i).getDueDate());
            assertEquals(expected.get(i).getDescription(), result.get(i).getDescription());
        }
        //Clear the itemsList
        ListHandler.tdl.itemList.clear();
    }

    @Test
    void getFilteredItems_should_return_complete_items_when_given_true_as_filter_key() {
        //Create an expected list containing all the test items
        List<InventoryItem> expected = new ArrayList<>();

        //Populate the itemList list in the ToDoList object with test items
        //Populate the expected list with every complete item at the same time
        InventoryItem testItem = new InventoryItem(true, "2021-07-11", "Turn in programming assignment");
        ListHandler.tdl.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem(false, "2021-07-13", "Feed the cat");
        ListHandler.tdl.itemList.add(testItem);
        testItem = new InventoryItem(true, "2021-07-08", "Take out the trash");
        ListHandler.tdl.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem(false, "2021-07-28", "Get a job");
        ListHandler.tdl.itemList.add(testItem);

        //Call the getFilteredItems method with "true" as the key word
        List<InventoryItem> result = ListHandler.getSearchItems("true");
        //Assert that every item in the returned list is present in the expected list
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getCompleteness(), result.get(i).getCompleteness());
            assertEquals(expected.get(i).getDueDate(), result.get(i).getDueDate());
            assertEquals(expected.get(i).getDescription(), result.get(i).getDescription());
        }
        //Clear the itemsList
        ListHandler.tdl.itemList.clear();
    }


    @Test
    void getFilteredItems_should_return_incomplete_items_when_given_false_as_filter_key() {
        //Create an expected list containing all the test items
        List<InventoryItem> expected = new ArrayList<>();

        //Populate the itemList list in the ToDoList object with test items
        //Populate the expected list with every incomplete item at the same time
        InventoryItem testItem = new InventoryItem(true, "2021-07-11", "Turn in programming assignment");
        ListHandler.tdl.itemList.add(testItem);
        testItem = new InventoryItem(false, "2021-07-13", "Feed the cat");
        ListHandler.tdl.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem(true, "2021-07-08", "Take out the trash");
        ListHandler.tdl.itemList.add(testItem);
        testItem = new InventoryItem(false, "2021-07-28", "Get a job");
        ListHandler.tdl.itemList.add(testItem);
        expected.add(testItem);

        //Call the getFilteredItems method with "false" as the key word
        List<InventoryItem> result = ListHandler.getSearchItems("false");
        //Assert that every item in the returned list is present in the expected list
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getCompleteness(), result.get(i).getCompleteness());
            assertEquals(expected.get(i).getDueDate(), result.get(i).getDueDate());
            assertEquals(expected.get(i).getDescription(), result.get(i).getDescription());
        }
        //Clear the itemsList
        ListHandler.tdl.itemList.clear();
    }


    @Test
    void saveList_should_properly_save_the_list_and_loadList_should_properly_load_it() {
        //Create an expected list containing all the test items
        List<InventoryItem> expected = new ArrayList<>();

        //Populate the itemList list in the ToDoList object with test items
        //Populate the expected list with every item at the same time
        InventoryItem testItem = new InventoryItem(true, "2021-07-11", "Turn in programming assignment");
        ListHandler.tdl.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem(false, "2021-07-13", "Feed the cat");
        ListHandler.tdl.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem(true, "2021-07-08", "Take out the trash");
        ListHandler.tdl.itemList.add(testItem);
        expected.add(testItem);
        testItem = new InventoryItem(false, "2021-07-28", "Get a job");
        ListHandler.tdl.itemList.add(testItem);
        expected.add(testItem);

        //Run the saveList method with a test file path
        ListHandler.saveList("TestSaves","TestList");

        //Clear the itemsList
        ListHandler.tdl.itemList.clear();

        //Run the loadList method on the save file that should now exist
        ListHandler.loadList("TestSaves/TestList.json");

        //Get the repopulated list
        List<InventoryItem> result = ListHandler.tdl.getItems();

        //Assert that every item in the result list is present in the expected list
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getCompleteness(), result.get(i).getCompleteness());
            assertEquals(expected.get(i).getDueDate(), result.get(i).getDueDate());
            assertEquals(expected.get(i).getDescription(), result.get(i).getDescription());
        }
        //Clear the itemsList
        ListHandler.tdl.itemList.clear();
    }
}