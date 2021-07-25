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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
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

    public final ObservableList<Item> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Print the table view
        //Set the rows to the table
        //Make the table view editable
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
        AddItem addItem = new AddItem();
        list.add(addItem.ItemAdder(ItemValue.getText(), ItemNumber.getText(), ItemName.getText()));
    }

    public void buttonRemove(javafx.event.ActionEvent actionEvent) {
        //remove selected item from the list
        int number = PrintTable.getSelectionModel().getSelectedIndex();
        RemoveItem removeItem = new RemoveItem();
        removeItem.ItemRemover(list,number);
    }

    public void clearList(ActionEvent event) {
        //Remove all items form the list
        ClearList clearList = new ClearList();
        clearList.ListClear(list);
    }

    public void SaveAsTxt(ActionEvent event) throws IOException {
        //Save the list as a txt when the menu item is hit
        int counter ;
        ArrayList<String> listSaver = new ArrayList<>();
        for (counter = 0;counter < list.size();counter++){
            String Output = list.get(counter).getItemValue() +"\t"+ list.get(counter).getItemSerial() +" \t"+list.get(counter).getItemName();
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
            fileWriter.write(listSaver.get(counter)+"\n");
        }
        fileWriter.close();
    }

    public void SaveAsJSON(ActionEvent event) throws IOException {
        //Save the list as a JSON when the menu item is hit
        int counter ;
        JSONArray jsonArray = new JSONArray();
        for (counter = 0;counter < list.size();counter++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Value",ItemValue.getText());
            jsonObject.put("Serial Number",ItemNumber.getText());
            jsonObject.put("Name",ItemName.getText());
            jsonArray.put(jsonObject);
        }
        JSONObject object = new JSONObject();
        object.put("List",jsonArray);

        FileChooser fileChooser = new FileChooser();
        Stage primaryStage = new Stage();
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);
        primaryStage.show();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(object.toString());
        fileWriter.close();
    }

    public void LoadAFile(ActionEvent event) {
        //print selected file to the Table
        FileChooser fileChooser = new FileChooser();
        Stage primaryStage = new Stage();

        File file = fileChooser.showOpenDialog(primaryStage);
        primaryStage.show();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] split =  line.split("\t");
                Item item = new Item(split[0],split[1],split[2]);
                list.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveAsHTML(ActionEvent event) throws IOException {
        //Save the list as a JSON when the menu item is hit
        int counter;
        FileChooser fileChooser = new FileChooser();
        Stage primaryStage = new Stage();
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);
        primaryStage.show();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "  border: 1px solid black;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Inventory</h1>\n" +
                "\n" +
                "<table>\n" +
                "  <tr>\n" +
                "    <th>Item Value</th>\n" +
                "    <th>Item Serial Number</th>\n" +
                "    <th>Item Name</th>\n"+
                "  </tr>\n");
        for (counter = 0;counter < list.size();counter++) {
            fileWriter.write("  <tr>\n" +
                    "    <td>"+list.get(counter).getItemValue()+"</td>\n" +
                    "    <td>"+list.get(counter).getItemSerial()+"</td>\n" +
                    "    <td>"+list.get(counter).getItemName()+"</td>\n" +
                    "  </tr>\n");
        }
        fileWriter.write("</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n");
        fileWriter.close();
    }

}
