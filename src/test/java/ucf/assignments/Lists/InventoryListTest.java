/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments.Lists;

import org.junit.jupiter.api.Test;
import ucf.assignments.ItemSearchHandler;
import ucf.assignments.Lists.Items.InventoryItem;

import static org.junit.jupiter.api.Assertions.*;

class InventoryListTest {

    InventoryList il = new InventoryList();

    @Test
    void validateName_should_return_true_for_valid_names() {
        //Create a valid name
        String name = "Test item name";
        //Assert that the name will be accepted as valid
        assertTrue(il.validateName(name));
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
        assertFalse(il.validateName(name));
    }

    @Test
    void validateName_should_return_false_for_empty_names() {
        //Create an invalid name
        String name = "";
        //Assert that the name will be discarded as invalid
        assertFalse(il.validateName(name));
    }

    @Test
    void validateName_should_return_false_for_too_short_names() {
        //Create an invalid name
        String name = "F";
        //Assert that the name will be discarded as invalid
        assertFalse(il.validateName(name));
    }

    @Test
    void validateSerialNum_should_validate_valid_numbers() {
        //Create a valid serial number
        String serialNum = "57HKR10L82";
        //Assert that the serial number will be returned as valid
        assertTrue(il.validateSerialNum(serialNum));
    }

    @Test
    void validateSerialNum_should_invalidate_short_numbers() {
        //Create a valid serial number
        String serialNum = "57HKRL82";
        //Assert that the serial number will be rejected as invalid
        assertFalse(il.validateSerialNum(serialNum));
    }

    @Test
    void validateSerialNum_should_invalidate_long_numbers() {
        //Create a valid serial number
        String serialNum = "57HKR84510L82";
        //Assert that the serial number will be rejected as invalid
        assertFalse(il.validateSerialNum(serialNum));
    }

    @Test
    void validateSerialNum_should_invalidate_numbers_with_invalid_characters() {
        //Create a valid serial number
        String serialNum = "57$^@10L82";
        //Assert that the serial number will be rejected as invalid
        assertFalse(il.validateSerialNum(serialNum));
    }

    @Test
    void checkForSerialNumDupe_should_validate_unique_serial_numbers() {
        //Populate the itemList list in the InventoryList object with test items
        InventoryItem testItem = new InventoryItem("16USD73K93", "$1.99", "Trinket");
        il.itemList.add(testItem);
        testItem = new InventoryItem("38USF24G82", "$24.50", "Thing");
        il.itemList.add(testItem);
        testItem = new InventoryItem("57HKR10L82", "$145.45", "Widget");
        il.itemList.add(testItem);
        testItem = new InventoryItem("14CJT99T21", "$1999.00", "Doodad");
        il.itemList.add(testItem);

        //Create a new unique serial number
        String newSerialNum = "123YU27GH6";

        //Call the checkForSerialNumDupe function and assert that it will return true
        assertTrue(il.checkForSerialNumDupe(newSerialNum));

        //Clear the itemsList
        il.itemList.clear();
    }

    @Test
    void checkForSerialNumDupe_should_invalidate_duplicate_serial_numbers() {
        //Populate the itemList list in the InventoryList object with test items
        InventoryItem testItem = new InventoryItem("16USD73K93", "$1.99", "Trinket");
        il.itemList.add(testItem);
        testItem = new InventoryItem("38USF24G82", "$24.50", "Thing");
        il.itemList.add(testItem);
        testItem = new InventoryItem("57HKR10L82", "$145.45", "Widget");
        il.itemList.add(testItem);
        testItem = new InventoryItem("14CJT99T21", "$1999.00", "Doodad");
        il.itemList.add(testItem);

        //Create a new unique serial number
        String newSerialNum = "16USD73K93";

        //Call the checkForSerialNumDupe function and assert that it will return true
        assertFalse(il.checkForSerialNumDupe(newSerialNum));

        //Clear the itemsList
        il.itemList.clear();
    }

    @Test
    void validatePrice_should_validate_valid_prices() {
        //Create a valid price
        String price = "$12.50";
        //Assert that the price will be returned as valid
        assertTrue(il.validatePrice(price));
    }

    @Test
    void validatePrice_should_validate_prices_with_only_cents() {
        //Create a valid price
        String price = "$.50";
        //Assert that the price will be returned as valid
        assertTrue(il.validatePrice(price));
    }
    @Test
    void validatePrice_should_validate_prices_with_only_cents_and_a_leading_zero() {
        //Create a valid price
        String price = "$0.50";
        //Assert that the price will be returned as valid
        assertTrue(il.validatePrice(price));
    }

    @Test
    void validatePrice_should_invalidate_too_short_prices() {
        //Create an invalid price
        String price = "$12.5";
        //Assert that the price will be returned as invalid
        assertFalse(il.validatePrice(price));
    }

    @Test
    void validatePrice_should_invalidate_too_long_prices() {
        //Create an invalid price
        String price = "$12.567";
        //Assert that the price will be returned as invalid
        assertFalse(il.validatePrice(price));
    }

    @Test
    void validatePrice_should_invalidate_prices_with_leading_0s() {
        //Create an invalid price
        String price = "$02.54";
        //Assert that the price will be returned as invalid
        assertFalse(il.validatePrice(price));
    }

    @Test
    void validatePrice_should_invalidate_prices_without_dollar_signs() {
        //Create an invalid price
        String price = "12.50";
        //Assert that the price will be returned as invalid
        assertFalse(il.validatePrice(price));
    }

}