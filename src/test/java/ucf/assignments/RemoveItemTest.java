package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveItemTest {

    @Test
    void itemRemover_Test() {
        ObservableList<Item> list = FXCollections.observableArrayList();
        Item item = new Item("100","1234567890","Pops");
        Item item1 = new Item("50","ABCDEFGHIJ","Moms");
        list.addAll(item,item1);
        RemoveItem removeItem = new RemoveItem();
        removeItem.ItemRemover(list,0);

        String actual = list.get(0).getItemName();
        String expected = "Moms";
        assertEquals(actual,expected);
    }
}