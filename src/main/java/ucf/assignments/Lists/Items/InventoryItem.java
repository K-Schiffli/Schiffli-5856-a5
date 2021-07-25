/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kevin Schiffli
 */
package ucf.assignments.Lists.Items;

import javafx.beans.property.SimpleStringProperty;

public class InventoryItem {

    //Create a serial number parameter
    public SimpleStringProperty serialNum;

    //Create a price parameter
    private final SimpleStringProperty price;

    //Create a name parameter
    private final SimpleStringProperty name;

    //Create a model for the InventoryItem object
    public InventoryItem(String serialNumVal, String priceVal, String nameVal) {
        this.serialNum = new SimpleStringProperty(serialNumVal);
        this.price = new SimpleStringProperty(priceVal);
        this.name = new SimpleStringProperty(nameVal);
    }

    //create a setter method for the serial number
    public void setSerialNum(String serialNumberVal) { serialNum.set(serialNumberVal); }

    //Create a setter method for the price
    public void setPrice(String priceVal) {
        price.set(priceVal);
    }

    //Create a setter method for the name
    public void setName(String nameVal) {
        name.set(nameVal);
    }

    //Create a getter method for the serial number
    public String getSerialNum() {
        return serialNum.get();
    }

    //Create a getter method for the price
    public String getPrice() {
        return price.get();
    }

    //Create a getter method for the name
    public String getName() { return name.get(); }
}
