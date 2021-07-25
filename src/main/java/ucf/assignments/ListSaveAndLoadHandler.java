/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ucf.assignments.Lists.Items.InventoryItem;

import java.io.*;

public class ListSaveAndLoadHandler {
    //Create a method to save a single list as a JSON file
    public void saveListAsJSON(String folderPath, String listTitle) {
        //Enclose the file writing function in a try/catch in case the operation fails
        try {
            //Create an instance of the FileWriter class and pass it the name of the file
            FileWriter fw = new FileWriter(folderPath + "/" + listTitle + ".json");
            fw.write("{\n\t\"items\": [{\n");
            for (int i = 0; i < ListHandler.il.itemList.size(); i++) {
                //Because each object is simple enough, serialize it manually
                fw.write("\t\"serialNum\": " + ListHandler.il.itemList.get(i).getSerialNum()+",\n");
                fw.write("\t\t\t\"price\": \"" + ListHandler.il.itemList.get(i).getPrice()+"\",\n");
                fw.write("\t\t\t\"name\": \"" + ListHandler.il.itemList.get(i).getName()+"\"\n");
                if (i+1 == ListHandler.il.itemList.size()) fw.write("\n\t\t}");
                else fw.write("\n\t\t},\n\t\t{");
            }
            fw.write("\n\t]\n}");
            //Remember to close the file
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
        int length = ListHandler.il.getItems().size();
        for (int i = (length - 1); i >= 0; i--){
            ListHandler.il.removeItem(ListHandler.il.itemList.get(i));
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
                ListHandler.il.itemList.add(item);
            }
            //Catch any exceptions thrown and print an error message and the stack trace
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
            FileWriter fw = new FileWriter(folderPath + "/" + listTitle + ".txt");
            for (InventoryItem inventoryItem : ListHandler.il.itemList) {
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
        //Clear the current InventoryItems list
        int length = ListHandler.il.getItems().size();
        for (int i = (length - 1); i >= 0; i--){
            ListHandler.il.removeItem(ListHandler.il.itemList.get(i));
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
                ListHandler.il.itemList.add(newItem);
            }
            //Catch any exceptions thrown and print an error message and the stack trace
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
            //Serialize the data manually again.
            fw.write("""
                    <!DOCTYPE html>
                    <html>
                    <body>

                    <table style="width:20%">
                    <tr>
                       <td>Serial Number</td>
                       <td>Price</td>
                       <td>Name</td>      \s
                    </tr>""".indent(2));
            for (InventoryItem inventoryItem : ListHandler.il.itemList) {
                fw.write("  <tr>\n" +
                        "     <td>"+inventoryItem.getSerialNum()+"</td>\n" +
                        "     <td>"+inventoryItem.getPrice()+"</td>\n" +
                        "     <td>"+inventoryItem.getName()+"</td>\n" +
                        " </tr>\n");
            }
            fw.write(" </table>");
            //Remember to close the file
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
        int length = ListHandler.il.getItems().size();
        for (int i = (length - 1); i >= 0; i--){
            ListHandler.il.removeItem(ListHandler.il.itemList.get(i));
        }
        //Create a File object to read the data from
        File inputFile = new File(filePath);

        //Wrap the operation in a try/catch statement to handle any errors
        try {
            Document doc = Jsoup.parse(inputFile, "UTF-8", "");
            Element table = doc.select("table").get(0);
            Elements rows = table.select("tr");

            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");

                InventoryItem newItem = new InventoryItem(cols.get(0).text(), cols.get(1).text(), cols.get(2).text());
                ListHandler.il.itemList.add(newItem);

            }
            //Catch any exceptions thrown and print an error message and the stack trace
        } catch (IOException e) {
            System.out.println("Could not parse data");
            e.printStackTrace();
        }
    }
}
