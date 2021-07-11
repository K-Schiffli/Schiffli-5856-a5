/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package Assignment04.Lists.Items;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class ToDoItem {

    //Create a completeness parameter
    public SimpleBooleanProperty completeness;

    //Create a due date parameter
    private final SimpleStringProperty dueDate;

    //Create a description parameter
    private final SimpleStringProperty description;

    //Create a model for the ToDoItem object
    public ToDoItem(boolean completenessVal, String dueDateVal, String descriptionVal) {
        this.completeness = new SimpleBooleanProperty(completenessVal);
        this.dueDate = new SimpleStringProperty(dueDateVal);
        this.description = new SimpleStringProperty(descriptionVal);
    }

    //create a setter method for the completeness parameter
    public void setCompleteness(boolean completenessVal) { completeness.set(completenessVal); }

    //Create a setter method for the due date
    public void setDueDate(String dueDateVal) {
        dueDate.set(dueDateVal);
    }

    //Create a setter method for the description
    public void setDescription(String descriptionVal) {
        description.set(descriptionVal);
    }

    //Create a getter method for the completeness parameter
    public boolean getCompleteness() {
        return completeness.get();
    }

    //Create a getter method for the due date
    public String getDueDate() {
        return dueDate.get();
    }

    //Create a getter method for the description
    public String getDescription() { return description.get(); }
}
