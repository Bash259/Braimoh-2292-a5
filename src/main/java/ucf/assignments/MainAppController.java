package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.attribute.FileTime;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static javafx.application.Application.launch;
import static org.testng.reporters.jq.BasePanel.C;

public class MainAppController implements Initializable {
    @FXML
    public TextField ItemValue;
    @FXML
    public TextField ItemNumber;
    @FXML
    public TextField ItemName;
    @FXML
    public TextField FilterTable;

    public TableView<Item> PrintTable;
    public TableColumn<Item,String> ItemValueList;
    public TableColumn<Item,String> ItemSerialList;
    public TableColumn<Item,String> ItemNameList;

    private final ObservableList<Item> list = FXCollections.observableArrayList();
    public Button AddItem;
    public Button buttonRemove;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ItemValueList.setCellValueFactory(new PropertyValueFactory<>("ItemValue"));
        ItemSerialList.setCellValueFactory(new PropertyValueFactory<>("ItemSerial"));
        ItemNameList.setCellValueFactory(new PropertyValueFactory<>("ItemName"));

        PrintTable.setEditable(true);
        ItemValueList.setCellFactory(TextFieldTableCell.forTableColumn());
        ItemSerialList.setCellFactory(TextFieldTableCell.forTableColumn());
        ItemNameList.setCellFactory(TextFieldTableCell.forTableColumn());


        Item item = new Item("Pog","ABCDE23J","Xbox");
        Item item1 = new Item("Soy","LOLSUSY","CaLin");
        Item item2 = new Item("Boy","BALSOY","Zoom");
        Item item3 = new Item("Toy","LULSUS","Damn");

        list.addAll(item,item1,item2,item3);
        PrintTable.setItems(list);

        FilteredList<Item> filteredList = new FilteredList<>(list, b->true);
            FilterTable.textProperty().addListener((observable ,oldValue, newValue ) ->{
                filteredList.setPredicate(itemA ->{
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowercase = newValue.toLowerCase();

                    if (itemA.getItemName().toLowerCase().indexOf(lowercase) != -1){
                        return true;
                    }else if (itemA.getItemSerial().toLowerCase().indexOf(lowercase) != -1){
                        return true;
                    }else if (String.valueOf(itemA.getItemValue()).indexOf(lowercase) != -1){
                        return true;
                    }else
                        return false;
                });
            });
        SortedList<Item> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(PrintTable.comparatorProperty());
        PrintTable.setItems(sortedList);
    }

    public void buttonRemove(ActionEvent actionEvent) {
        ObservableList<Item> allProduct,SingleProduct;
        allProduct = PrintTable.getItems();
        SingleProduct = PrintTable.getSelectionModel().getSelectedItems();
        SingleProduct.forEach(allProduct::remove);
    }

    public void onEditChanged(TableColumn.CellEditEvent cellEditEvent) {

    }

    public void PrintTableTable(SortEvent<TableView> tableViewSortEvent) {

    }

    public void AddItem(ActionEvent event) {
        
        Item item = new Item(ItemValue.getText(),ItemSerialList.getText(),ItemName.getText());
        PrintTable.getItems().add(item);
    }
}
