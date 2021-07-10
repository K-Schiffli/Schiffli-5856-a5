package Assignment04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListHandlerTest {
    //Create a new instance of the ListHandler class

    @Test
    void addList_should_add_a_new_ToDoList_to_the_List() {
        //Create a new ToDoList object with test data
        //Call the addList method of the ListHandler class with the test object
        //Assert that the List now contains the new test object
    }

    @Test
    void removeList_should_remove_the_correct_ToDoList_from_the_list() {
        //Populate the ListHandler's list with test lists
        //Call the removeList method of the ListHandler class with an object that exists in the List
        //Assert that the specified object is no longer in the List
    }

    @Test
    void removeList_should_not_break_if_called_with_an_object_not_in_the_list() {
        //Populate the ListHandler's list with test lists
        //Call the removeList method of the ListHandler class with an object that doesn't exist in the List
        //Assert that the List is unchanged
    }

    @Test
    void saveList_should_save_the_specified_list_correctly() {
        //Populate the ListHandler's list with a test list
        //Create an expected filename that would result from saving the list
        //Call the ListHandler's saveList method with a test filepath

        //Create a boolean flag. set to true
        //Attempt to open the file that is now expected to exist. Wrap in a try/catch structure
        //If the fails to open, catch it and set the flag to false
        //Assert that the flag is still true
    }

    @Test
    void loadList_should_load_the_specified_list_correctly() {
        //Call the loadList method with a pre-created test list object
        //Assert that the ListHandler's List now contains the expected object
    }

    @Test
    void saveAllLists_should_save_all_the_lists_to_the_correct_files() {
        //Populate the ListHandler's list with test lists
        //Create a List of expected filenames that would result from saving the lists
        //Call the ListHandler's saveAllLists method with a test filepath

        //Create a boolean flag. set to true
        //Attempt to open every file that is now expected to exist. Wrap in a try/catch structure
        //If any file fails to open, catch it and set the flag to false
        //Assert that the flag is still true
    }

    @Test
    void loadAllLists_should_load_all_the_lists_from_the_specified_directory() {

        //Call the loadAllLists method with a pre-populated test directory
        //Assert that the ListHandler's List now contains all the expected items
    }
}