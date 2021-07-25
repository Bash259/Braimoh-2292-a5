package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddItemTest {

    @Test
    void itemAdderTest_valid_name() {
        AddItem addItem = new AddItem();
        String actual = (addItem.ItemAdder("100","ASDFGHJKLQ","Plug")).getItemName();
        String expected = "Plug";
        assertEquals(actual,expected);
    }

    @Test
    void itemAdderTest_valid_value() {
        AddItem addItem = new AddItem();
        String actual = (addItem.ItemAdder("100","ASDFGHJKLQ","Plug")).getItemValue();
        String expected = "$100";
        assertEquals(actual,expected);
    }
    @Test
    void itemAdderTest_invalid_value() {
        AddItem addItem = new AddItem();
        String actual = (addItem.ItemAdder("pan","ASDFGHJKLQ","Plug")).getItemValue();
        String expected = "null";
        assertEquals(actual,expected);
    }
}