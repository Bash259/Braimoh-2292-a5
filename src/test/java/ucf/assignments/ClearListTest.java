package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClearListTest {

    @Test
    void listClear() {
        ObservableList<Item> list = FXCollections.observableArrayList();
        Item item = new Item("100","1234567890","Pops");
        Item item1 = new Item("50","ABCDEFGHIJ","Moms");
        list.addAll(item,item1);
        ClearList clearList = new ClearList();
        clearList.ListClear(list);

        int actual = list.size();
        int expected = 0;
        assertEquals(actual,expected);
    }
}