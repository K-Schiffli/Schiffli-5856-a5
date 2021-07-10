package Assignment04;

import Assignment04.Lists.Items.ToDoItem;
import Assignment04.Lists.ToDoList;
import com.google.gson.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ListHandler {

    //Create a new ToDoList object
    static ToDoList tdl = new ToDoList();

    //Create a method to save a single list
    public static void saveList(String folderPath, String listTitle) {
        //Enclose the file writing function in a try/catch in case the operation fails
        try {
            //Create an instance of the FileWriter class and pass it the name of the file
            FileWriter fw = new FileWriter(folderPath + "/" + listTitle + ".json");
            fw.write("{\n\t\"items\": [{\n");
            for (int i = 0; i < tdl.itemList.size(); i++) {
            //Because each object is simple enough, serialize it manually
                fw.write("\t\"completeness\": " + tdl.itemList.get(i).getCompleteness()+",\n");
                fw.write("\t\t\t\"dueDate\": \"" + tdl.itemList.get(i).getDueDate()+"\",\n");
                fw.write("\t\t\t\"description\": \"" + tdl.itemList.get(i).getDescription()+"\"\n");
                if (i+1 == tdl.itemList.size()) fw.write("\n\t\t}");
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

    //Create a method to load a single list
    public static void loadList (String filePath)
    {
        //Clear the current ToDoItems list
        int length = tdl.getItems().size();
        for (int i = (length - 1); i >= 0; i--){
            tdl.removeItem(tdl.itemList.get(i));
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
                boolean completeness = itemJsonObject.get("completeness").getAsBoolean();
                String dueDate = itemJsonObject.get("dueDate").getAsString();
                String description = itemJsonObject.get("description").getAsString();

                //Build a new ToDoList object and add it to a list of Products
                ToDoItem item = new ToDoItem(completeness, dueDate, description);
                tdl.itemList.add(item);
            }
            //Catch any exceptions thrown and print ane error message and the stack trace
        } catch (FileNotFoundException e) {
            System.out.println("Could not parse data");
            e.printStackTrace();
        }
    }
}
