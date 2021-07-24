/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments.Lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryListTest {

    //Create a new InventoryList object
    InventoryList tdl = new InventoryList();

    @Test
    void validateName_should_return_true_for_valid_names() {
        //Create a valid name
        String name = "Test item name";

        //Assert that the name will be accepted as valid
        assertTrue(tdl.validateName(name));
    }

    @Test
    void validateName_should_return_false_for_too_long_names() {
        //Create an invalid name
        String name = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of type and scrambled it to make a type " +
                "specimen book. It has survived not only five centuries, " +
                "but also the leap into electronic typesetting, remaining essentially unchanged. " +
                "It was popularised in the 1960s with the release of " +
                "Letraset sheets containing Lorem Ipsum passages, and more recently with " +
                "desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

        //Assert that the name will be discarded as invalid
        assertFalse(tdl.validateName(name));
    }

    @Test
    void validateName_should_return_false_for_empty_names() {
        //Create an invalid name
        String name = "";

        //Assert that the name will be discarded as invalid
        assertFalse(tdl.validateName(name));
    }

    @Test
    void validateName_should_return_false_for_too_short_names() {
        //Create an invalid name
        String name = "F";

        //Assert that the name will be discarded as invalid
        assertFalse(tdl.validateName(name));
    }

    @Test
    void searchItems() {
    }

    @Test
    void validateSerialNum_should_validate_for_valid_numbers() {
    }

    @Test
    void checkForSerialNumDupe() {
    }

    @Test
    void validatePrice_should_validate_valid_prices() {
        //Create a valid price
        String price = "$12.50";
        assertTrue(tdl.validatePrice(price));
    }

    @Test
    void validatePrice_should_invalidate_too_short_prices() {
        //Create an invalid price
        String price = "$12.5";
        assertFalse(tdl.validatePrice(price));
    }

    @Test
    void validatePrice_should_invalidate_too_long_prices() {
        //Create an invalid price
        String price = "$12.567";
        assertFalse(tdl.validatePrice(price));
    }

    @Test
    void validatePrice_should_invalidate_prices_with_leading_0s() {
        //Create an invalid price
        String price = "$02.54";
        assertFalse(tdl.validatePrice(price));
    }

    @Test
    void validatePrice_should_invalidate_prices_without_dollar_signs() {
        //Create an invalid price
        String price = "12.50";
        assertFalse(tdl.validatePrice(price));
    }

}