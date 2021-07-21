package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReturnTitleTest {
    @Test
    void returnToDoTitle() {
        ReturnTitle returnTitle = new ReturnTitle();

        String TitleA = returnTitle.ReturnToDoTitle();
        String TitleB = "ToDoList";
        assertEquals(TitleA,TitleB);
    }
}