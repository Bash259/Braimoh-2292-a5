package ucf.assignments;

import javafx.collections.ObservableList;

public class RemoveItem {
    //takes in a list
    //Remove selected item from list
    public void ItemRemover(ObservableList<Item> list, int number){
        list.remove(number);
    }
}
