package Assignment04.Lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    //Create a new instance of the ToDoList class


    @Test
    void filterItems_should_return_only_complete_items_if_called_with_true() {
        //Populate the List of ToDoList objects with test items, some of which are complete, some of which aren't
        //Create an expected List containing only the complete items
        //Call the filterItems method with "true" as the input
        //Assert that the resultant list and the expected list are the same
    }

    @Test
    void filterItems_should_return_only_incomplete_items_if_called_with_false() {
        //Populate the List of ToDoList objects with test items, some of which are complete, some of which aren't
        //Create an expected List containing only the incomplete items
        //Call the filterItems method with "false" as the input
        //Assert that the resultant list and the expected list are the same
    }

    @Test
    void filterItems_should_handle_empty_lists_correctly() {
        //Populate the List of ToDoList objects with test items, all of which are complete
        //Create an expected empty List
        //Call the filterItems method with "false" as the input
        //Assert that the resultant List is empty
    }
}