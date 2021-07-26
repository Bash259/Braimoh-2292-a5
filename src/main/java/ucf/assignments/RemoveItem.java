/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Fouad Braimoh
 */


package ucf.assignments;

import javafx.collections.ObservableList;

public class RemoveItem {
    //takes in a list
    //Remove selected item from list
    public void ItemRemover(ObservableList<Item> list, int number){
        list.remove(number);
    }
}
