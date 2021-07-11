/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments.Lists;

import org.junit.jupiter.api.Test;
import ucf.assignments.Lists.ToDoList;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    //Create a new ToDoList object
    ToDoList tdl = new ToDoList();

    @Test
    void validateDesc_should_return_true_for_valid_descriptions() {
        //Create a valid description
        String description = "Write a functional program";

        //Assert that the description will be accepted as valid
        assertTrue(tdl.validateDesc(description));
    }

    @Test
    void validateDesc_should_return_false_for_too_long_descriptions() {
        //Create a valid description
        String description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of type and scrambled it to make a type " +
                "specimen book. It has survived not only five centuries, " +
                "but also the leap into electronic typesetting, remaining essentially unchanged. " +
                "It was popularised in the 1960s with the release of " +
                "Letraset sheets containing Lorem Ipsum passages, and more recently with " +
                "desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

        //Assert that the description will be discarded as invalid
        assertFalse(tdl.validateDesc(description));
    }

    @Test
    void validateDesc_should_return_false_for_empty_descriptions() {
        //Create a valid description
        String description = "";

        //Assert that the description will be discarded as invalid
        assertFalse(tdl.validateDesc(description));
    }
}