package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class MainAppController implements Initializable {
    @FXML
    public TextField ItemValue;
    @FXML
    public TextField ItemNumber;
    @FXML
    public TextField ItemName;
    @FXML
    public TextField FilterTable;
    @FXML
    public TableView<Item> PrintTable;
    public TableColumn<Item,String> ItemValueList;
    public TableColumn<Item,String> ItemSerialList;
    public TableColumn<Item,String> ItemNameList;

    private final ObservableList<Item> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ItemValueList.setCellValueFactory(new PropertyValueFactory<>("ItemValue"));
        ItemSerialList.setCellValueFactory(new PropertyValueFactory<>("ItemSerial"));
        ItemNameList.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        PrintTable.setEditable(true);

        ItemValueList.setCellFactory(TextFieldTableCell.forTableColumn());
        ItemSerialList.setCellFactory(TextFieldTableCell.forTableColumn());
        ItemNameList.setCellFactory(TextFieldTableCell.forTableColumn());

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


    public void AddItem(ActionEvent event) throws IOException {
        if (ItemValue.getText().isEmpty()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Enter an Item Value");
            errorAlert.showAndWait();
        }else if (!ItemValue.getText().matches("[0-9]+")){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Non-Integer characters are not allowed. Enter an Integer.");
            errorAlert.showAndWait();
        } else if (ItemNumber.getText().isEmpty()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Item Serial Number can not be empty enter a serial number.");
            errorAlert.showAndWait();
        }else if (!(ItemNumber.getText().length() == 7)){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Item Serial Number must be seven characters long.");
            errorAlert.showAndWait();
        }else if (!ItemNumber.getText().matches("[a-zA-Z0-9 ]+")){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Item Serial Number can only contain letters and numbers.");
            errorAlert.showAndWait();
        } else if (ItemName.getText().isEmpty()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Item Name can not be empty enter a name.");
            errorAlert.showAndWait();
        }else {
            Item item = new Item("$" + ItemValue.getText(), ItemNumber.getText(), ItemName.getText());
            list.add(item);
        }
    }

    public void onEditChanged(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
    }

    public void buttonRemove(javafx.event.ActionEvent actionEvent) {
        int number = PrintTable.getSelectionModel().getSelectedIndex();
        list.remove(number);
    }

    public void BackToList(ActionEvent event) {

    }

    public void clearList(ActionEvent event) {
        ObservableList<Item> allProduct,SingleProduct;
        list.clear();
    }

    public void SaveAsTxt(ActionEvent event) throws IOException {
        int counter ;
        ArrayList<String> listSaver = new ArrayList<>();
        for (counter = 0;counter < list.size();counter++){
            String Output = list.get(counter).getItemValue() +" "+ list.get(counter).getItemSerial() +" "+list.get(counter).getItemName();
            listSaver.add(Output);
            System.out.println(Output);
        }
        FileChooser fileChooser = new FileChooser();
        Stage primaryStage = new Stage();
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);
        primaryStage.show();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("Value"+"\t"+"Serial Number"+"\t"+"Name\n");
        for (counter = 0;counter < list.size();counter++) {
            fileWriter.write(listSaver.get(counter).replaceAll(" ","\t")+"\n");
        }
        fileWriter.close();
    }
}
