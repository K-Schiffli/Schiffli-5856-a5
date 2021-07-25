/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments.Lists;

import ucf.assignments.Lists.Items.InventoryItem;
import java.util.ArrayList;
import java.util.List;

public class InventoryList {

    //Create a List of InventoryItem objects
    public List<InventoryItem> itemList = new ArrayList<>();

    //Create a method to create a new InventoryItem object and add it to the List of InventoryItem objects
    public void addItem() {

        //Create a new InventoryItem object with placeholder values
        InventoryItem newItem = new InventoryItem("XXXXXXXXXX", "Input Price", "Input Name");

        //Add the new InventoryItem to the list
        itemList.add(newItem);
    }

    //Create a method to delete a specified InventoryItem from the list
    public void removeItem(InventoryItem item){

        //Remove the passed-in InventoryItem from the List
        itemList.remove(item);
    }

    //Create a method to return all the items in the List of InventoryItem objects
    public List<InventoryItem> getItems() {

        //Return the list of InventoryItems
        return itemList;
    }

    public boolean validateSerialNum(String serialNum) {
        //Check that the serial number is the correct length
        if (serialNum.length() != 10) return false;

        //Check that the serial number contains only characters and digits
        for (int i = 0; i < serialNum.length(); i++) {
            if (!Character.isLetterOrDigit(serialNum.charAt(i))) return false;
        }
        return true;
    }

    public boolean checkForSerialNumDupe(String serialNum) {

        //Check that the new serial number isn't duplicated
        for (InventoryItem inventoryItem : itemList) {
            if (serialNum.equals(inventoryItem.getSerialNum())) return false;
        }
        return true;
    }

    public boolean validatePrice(String price) {
        //Check that the price is the correct format

        //Check that the number begins with a dollar sign
        if (price.charAt(0) != '$') return false;
        //Check that the dollar amount doesn't have a leading 0
        else if (price.charAt(1) == '0'&& price.charAt(2) != '.') return false;
        //Check that the third-from-last character is a decimal point.
        else if (price.charAt(price.length() - 3) != '.') return false;

        //Check that every index before the decimal point and after it are numbers
        for (int i = 1; i < price.length() - 4; i++)
        {
            if (price.charAt(i) < '0' || price.charAt(i) > '9') return false;
        }
        for (int i = price.length() - 2; i < price.length(); i++)
        {
            if (price.charAt(i) < '0' || price.charAt(i) > '9') return false;
        }

        //If all checks pass, return true
        return true;
    }

    public boolean validateName(String name) {
        //Check that the name is between 2 and 256 characters, inclusive
        return name.length() <= 256 && name.length() >= 2;
    }
}
