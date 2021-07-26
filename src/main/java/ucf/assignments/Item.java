/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Fouad Braimoh
 */

package ucf.assignments;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private final SimpleStringProperty ItemValue;
    private final SimpleStringProperty ItemSerial;
    private final SimpleStringProperty ItemName;

    Item(String ItemValue, String ItemSerial, String ItemName){
        this.ItemValue = new SimpleStringProperty(ItemValue);
        this.ItemSerial = new SimpleStringProperty(ItemSerial);
        this.ItemName = new SimpleStringProperty(ItemName);
    }

    public String getItemValue(){
        return ItemValue.get();
    }

    public void setItemValue(String ItemValue){

        this.ItemValue.set(ItemValue);
    }

    public String getItemSerial(){

        return ItemSerial.get();
    }

    public void setItemSerial(String ItemSerial){

        this.ItemSerial.set(ItemSerial);
    }

    public String getItemName(){

        return ItemName.get();
    }

    public void setItemName(String ItemName){
        this.ItemName.set(ItemName);
    }
}
