/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Fouad Braimoh
 */

package ucf.assignments;

import javafx.scene.control.Alert;

public class AddItem {
    //create an Item to add to a list
    public Item ItemAdder(String value, String serialNumber, String Name){
        if (value.isEmpty()){
            //validate Item Value
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Enter an Item Value");
            errorAlert.showAndWait();
        }else if (!value.matches("[0-9.]+")){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Non-Integer characters are not allowed. Enter an Integer.");
            errorAlert.showAndWait();
        } else if (serialNumber.isEmpty()){
            //Validate Item serial Number
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Item Serial Number can not be empty enter a serial number.");
            errorAlert.showAndWait();
        }else if (!(serialNumber.length() == 10)){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Item Serial Number must be ten characters long.");
            errorAlert.showAndWait();
        }else if (!serialNumber.matches("[a-zA-Z0-9 ]+")){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Item Serial Number can only contain letters and numbers.");
            errorAlert.showAndWait();
        } else if (Name.isEmpty()){
            //Validate Item Name
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Item Name can not be empty enter a name.");
            errorAlert.showAndWait();
        }else if (Name.length() > 256){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Item Name can not be more than 256 characters.");
            errorAlert.showAndWait();
        }else {
            //Create the Item and return it to the main
            Item item = new Item("$" + value, serialNumber, Name);
            return item;
        }
        //wrong input storage
        Item item = new Item("null", "null", "null");
        return item;
    }
}
