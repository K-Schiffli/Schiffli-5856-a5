/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments.Lists;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ucf.assignments.Lists.Items.InventoryItem;

import java.io.*;
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

    //Create a method to search the list based on the term and the column
    public List<InventoryItem> searchItems(String searchTerm, String searchColumn) {

        //Create a new List of ToDoItems to hold the output
        List<InventoryItem> searchedItemList = new ArrayList<>();

        //Loop through the list
        for (InventoryItem inventoryItem : itemList) {
            if (searchColumn.equals("serialNum") && inventoryItem.getSerialNum().contains(searchTerm))
                searchedItemList.add(inventoryItem);
            else if (searchColumn.equals("name") && inventoryItem.getName().contains(searchTerm))
                searchedItemList.add(inventoryItem);
        }

        //Return the resulting list
        return searchedItemList;
    }

    public boolean validateSerialNum(String serialNum) {
        //Check that the serial number is the correct length
        if (serialNum.length() != 10) return true;

        //Check that the serial number contains only characters and digits
        for (int i = 0; i < serialNum.length(); i++) {
            if (!Character.isLetterOrDigit(serialNum.charAt(i))) return true;
        }
        return false;
    }

    public boolean checkForSerialNumDupe(String serialNum) {

        //Check that the new serial number isn't duplicated
        for (InventoryItem inventoryItem : itemList) {
            if (serialNum.equals(inventoryItem.getSerialNum())) return true;
        }
        return false;
    }

    public boolean validatePrice(String price) {
        //Check that the price is the correct format

        //Check that the third-from-last character is a decimal point.
        if (price.charAt(0) != '$') return false;
        else if (price.charAt(1) == '0') return false;
        else if (price.charAt(price.length() - 3) != '.') return false;
        for (int i = 1; i < price.length() - 4; i++)
        {
            if (price.charAt(i) < '0' || price.charAt(i) > '9') return false;
        }
        for (int i = price.length() - 2; i < price.length(); i++)
        {
            if (price.charAt(i) < '0' || price.charAt(i) > '9') return false;
        }
        return true;
    }

    public boolean validateName(String name) {
        //Check that the name is between 2 and 256 characters, inclusive
        return name.length() <= 256 && name.length() >= 2;
    }

    //Create a method to save a single list as a JSON file
    public void saveListAsJSON(String folderPath, String listTitle) {
        //Enclose the file writing function in a try/catch in case the operation fails
        try {
            //Create an instance of the FileWriter class and pass it the name of the file
            FileWriter fw = new FileWriter(folderPath + "/" + listTitle + ".json");
            fw.write("{\n\t\"items\": [{\n");
            for (int i = 0; i < itemList.size(); i++) {
                //Because each object is simple enough, serialize it manually
                fw.write("\t\"serialNum\": " + itemList.get(i).getSerialNum()+",\n");
                fw.write("\t\t\t\"price\": \"" + itemList.get(i).getPrice()+"\",\n");
                fw.write("\t\t\t\"name\": \"" + itemList.get(i).getName()+"\"\n");
                if (i+1 == itemList.size()) fw.write("\n\t\t}");
                else fw.write("\n\t\t},\n\t\t{");
            }
            fw.write("\n\t]\n}");
            //Remember to close the file and let the user know it worked
            fw.close();
            //Catch the exception thrown if the file fails to open, print an error message, and the stack trace
        } catch (IOException e) {
            System.out.println("Could not write to file.");
            e.printStackTrace();
        }
    }

    //Create a method to load a JSON file
    public void loadListAsJSON (String filePath)
    {
        //Clear the current ToDoItems list
        int length = getItems().size();
        for (int i = (length - 1); i >= 0; i--){
            removeItem(itemList.get(i));
        }
        //Create a File object to read the data from
        File inputFile = new File(filePath);

        //Wrap the operation in a try/catch statement to handle any errors
        try {
            //Create a json elements for use in parsing the array
            JsonElement fileElement = JsonParser.parseReader(new FileReader(inputFile));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray jsonArrayOfItems = fileObject.get("items").getAsJsonArray();

            //Loop through the array and parse the json into separate fields
            for (JsonElement itemElement : jsonArrayOfItems) {
                JsonObject itemJsonObject = itemElement.getAsJsonObject();
                String serialNum = itemJsonObject.get("serialNum").getAsString();
                String price = itemJsonObject.get("price").getAsString();
                String name = itemJsonObject.get("name").getAsString();

                //Build a new ToDoList object and add it to a list of Products
                InventoryItem item = new InventoryItem(serialNum, price, name);
                itemList.add(item);
            }
            //Catch any exceptions thrown and print ane error message and the stack trace
        } catch (FileNotFoundException e) {
            System.out.println("Could not parse data");
            e.printStackTrace();
        }
    }

    //Create a method to save a single list as a Tab Separated Values file
    public void saveListAsTSV(String folderPath, String listTitle) {
        //Enclose the file writing function in a try/catch in case the operation fails
        try {
            //Create an instance of the FileWriter class and pass it the name of the file
            FileWriter fw = new FileWriter(folderPath + "/" + listTitle + ".tsv");
            for (InventoryItem inventoryItem : itemList) {
                //Because each object is simple enough, serialize it manually
                fw.write(inventoryItem.getSerialNum() + "\t" + inventoryItem.getPrice() + "\t" + inventoryItem.getName() + "\n");
            }
            //Remember to close the file
            fw.close();
            //Catch the exception thrown if the file fails to open, print an error message, and the stack trace
        } catch (IOException e) {
            System.out.println("Could not write to file.");
            e.printStackTrace();
        }
    }

    //Create a method to load a Tab Separated Values file
    public void loadListAsTSV (String filePath)
    {
        //Clear the current ToDoItems list
        int length = getItems().size();
        for (int i = (length - 1); i >= 0; i--){
            removeItem(itemList.get(i));
        }
        //Create a File object to read the data from
        File inputFile = new File(filePath);
        //Wrap the operation in a try/catch statement to handle any errors
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(inputFile));
            //Loop through the array and parse each line into separate fields
            String line;
            while((line = bfr.readLine()) != null){
                String[] splitLine = line.split("\t");
                InventoryItem newItem = new InventoryItem(splitLine[0], splitLine[1], splitLine[2]);
                itemList.add(newItem);
            }
            //Catch any exceptions thrown and print ane error message and the stack trace
        } catch (IOException e) {
            System.out.println("Could not parse data");
            e.printStackTrace();
        }
    }

    //Create a method to save a single list as HTML
    public void saveListAsHTML(String folderPath, String listTitle) {
        //Enclose the file writing function in a try/catch in case the operation fails
        try {
            //Create an instance of the FileWriter class and pass it the name of the file
            FileWriter fw = new FileWriter(folderPath + "/" + listTitle + ".html");
            for (int i = 0; i < itemList.size(); i++) {

            }
            //Remember to close the file and let the user know it worked
            fw.close();
            //Catch the exception thrown if the file fails to open, print an error message, and the stack trace
        } catch (IOException e) {
            System.out.println("Could not write to file.");
            e.printStackTrace();
        }
    }

    //Create a method to load an HTML file
    public void loadListAsHTML (String filePath)
    {
        //Clear the current ToDoItems list
        int length = getItems().size();
        for (int i = (length - 1); i >= 0; i--){
            removeItem(itemList.get(i));
        }
        //Create a File object to read the data from
        File inputFile = new File(filePath);

        //Wrap the operation in a try/catch statement to handle any errors
        try {
            //Create a json elements for use in parsing the array
            JsonElement fileElement = JsonParser.parseReader(new FileReader(inputFile));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray jsonArrayOfItems = fileObject.get("items").getAsJsonArray();

            //Loop through the array and parse the json into separate fields
            for (JsonElement itemElement : jsonArrayOfItems) {
                JsonObject itemJsonObject = itemElement.getAsJsonObject();
                String serialNum = itemJsonObject.get("serialNum").getAsString();
                String price = itemJsonObject.get("price").getAsString();
                String name = itemJsonObject.get("name").getAsString();

                //Build a new ToDoList object and add it to a list of Products
                InventoryItem item = new InventoryItem(serialNum, price, name);
                itemList.add(item);
            }
            //Catch any exceptions thrown and print ane error message and the stack trace
        } catch (FileNotFoundException e) {
            System.out.println("Could not parse data");
            e.printStackTrace();
        }
    }
}
