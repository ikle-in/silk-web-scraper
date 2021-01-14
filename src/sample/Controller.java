package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URL;
import java.nio.file.attribute.AttributeView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class Controller {
    @FXML
    private Button enterButton;

    @FXML
    private Button linksButton;

    @FXML
    private TextField urlEntry;

    @FXML
    private Label confirmationText;

    @FXML
    private ListView linksView;

    @FXML
    private ListView wordsView;

    @FXML
    private ListView linksPageList;

    @FXML
    private ListView wordsPageList;

    @FXML
    private Label linksLabel;

    @FXML
    private Label wordsLabel;

    @FXML
    private Button refreshWebButton;

    @FXML
    private WebView webWindow;

    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;

    @FXML
    private RadioButton rb3;

    @FXML
    private RadioButton rb4;

    @FXML
    private VBox bgPane;

    @FXML
    private BorderPane divider;

    @FXML
    private Button homeButton;

    @FXML
    private Button wordsButton;

    @FXML
    private Button prefButton;


    final ToggleGroup group = new ToggleGroup();

    public void changeToLinks(ActionEvent event) throws IOException{

        String fxmlFileName = "lines.fxml";
        //switch case to check theme and set string
        switch(Main.theme) {
            case 1:
                fxmlFileName = "lines.fxml";
                break;
            case 2:
                fxmlFileName = "lines2.fxml";
                break;
            case 3:
                fxmlFileName = "lines3.fxml";
                break;
            case 4:
                fxmlFileName = "lines4.fxml";
                break;
        }


        if(Main.width != null && Main.height != null){
            String height = Main.parseRODS(Main.height);
            String width = Main.parseRODS(Main.width);
            double h = Double.parseDouble(height);
            double w = Double.parseDouble(width);
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent,w-16,h-39);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        } else if (Main.width != null){
            //only w
            String width = Main.parseRODS(Main.width);
            double w = Double.parseDouble(width);
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent,w-16, 600);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        } else if(Main.height != null){
            //only h
            String height = Main.parseRODS(Main.height);
            double h = Double.parseDouble(height);
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent,900,h-39);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        }else {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        }

    }

    public void changeToHome(ActionEvent event) throws IOException{

        String fxmlFileName = "sample.fxml";
        //switch case to check theme and set string
        switch(Main.theme) {
            case 1:
                fxmlFileName = "sample.fxml";
                break;
            case 2:
                fxmlFileName = "sample2.fxml";
                break;
            case 3:
                fxmlFileName = "sample3.fxml";
                break;
            case 4:
                fxmlFileName = "sample4.fxml";
                break;
        }

        if(Main.width != null && Main.height != null){
            String height = Main.parseRODS(Main.height);
            String width = Main.parseRODS(Main.width);
            double h = Double.parseDouble(height);
            double w = Double.parseDouble(width);
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent,w-16,h-39);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        } else if (Main.width != null){
            //only w
            String width = Main.parseRODS(Main.width);
            double w = Double.parseDouble(width);
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent,w-16, 600);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        } else if(Main.height != null){
            //only h
            String height = Main.parseRODS(Main.height);
            double h = Double.parseDouble(height);
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent,900,h-39);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        }else {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        }


    }

    public void changeToWords(ActionEvent event) throws IOException{

        String fxmlFileName = "words.fxml";
        //switch case to check theme and set string
        switch(Main.theme) {
            case 1:
                fxmlFileName = "words.fxml";
                break;
            case 2:
                fxmlFileName = "words2.fxml";
                break;
            case 3:
                fxmlFileName = "words3.fxml";
                break;
            case 4:
                fxmlFileName = "words4.fxml";
                break;
        }

        if(Main.width != null && Main.height != null){
            String height = Main.parseRODS(Main.height);
            String width = Main.parseRODS(Main.width);
            double h = Double.parseDouble(height);
            double w = Double.parseDouble(width);
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent,w-16,h-39);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        } else if (Main.width != null){
            //only w
            String width = Main.parseRODS(Main.width);
            double w = Double.parseDouble(width);
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent,w-16, 600);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        } else if(Main.height != null){
            //only h
            String height = Main.parseRODS(Main.height);
            double h = Double.parseDouble(height);
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent,900,h-39);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        }else {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        }
    }


    public void changeToPref(ActionEvent event) throws IOException{
        String fxmlFileName = "pref.fxml";
        //switch case to check theme and set string
        switch(Main.theme) {
            case 1:
                fxmlFileName = "pref.fxml";
                break;
            case 2:
                fxmlFileName = "pref2.fxml";
                break;
            case 3:
                fxmlFileName = "pref3.fxml";
                break;
            case 4:
                fxmlFileName = "pref4.fxml";
                break;
        }

        if(Main.width != null && Main.height != null){
            String height = Main.parseRODS(Main.height);
            String width = Main.parseRODS(Main.width);
            double h = Double.parseDouble(height);
            double w = Double.parseDouble(width);
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent,w-16,h-39);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        } else if (Main.width != null){
            //only w
            String width = Main.parseRODS(Main.width);
            double w = Double.parseDouble(width);
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent,w-16, 600);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        } else if(Main.height != null){
            //only h
            String height = Main.parseRODS(Main.height);
            double h = Double.parseDouble(height);
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent,900,h-39);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        }else {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFileName));
            Scene links = new Scene(parent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(links);
            window.show();
            window.widthProperty().addListener((obs, oldVal, newVal) -> {
                Main.width = window.widthProperty();
            });
            window.heightProperty().addListener((obs, oldVal, newVal) -> {
                Main.height = window.heightProperty();
            });
        }

    }

    public void getText(ActionEvent event) throws IOException{
      try {
          Main.savedWords.clear();
          Main.savedLinks.clear();
          linksView.getItems().clear();
          Main.savedURL = "";
          String input = urlEntry.getText();
          Main.savedMainURL = input;

        //ArrayList<String> allLinks = new ArrayList<String>();
       // String[] links = new String[0];
        URL url = new URL(input);
        Main.getHTML(url, Main.savedLinks, Main.savedWords);

        confirmationText.setText("Displaying data for: " +Main.savedMainURL);

        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<String> wordsList = FXCollections.observableArrayList();

        for(int y=0;y<Main.savedLinks.size();y++){
            list.add(Main.savedLinks.get(y));
        }

          for(int y=0;y<Main.savedWords.size();y++){
              wordsList.add(Main.savedWords.get(y));
          }

          linksView.setItems(list);
          wordsView.setItems(wordsList);


      } catch(Exception e){};
    }


    public void getLinkClicked(MouseEvent event) throws IOException{
        try {
            int selection = linksView.getSelectionModel().getSelectedIndex();
            Main.savedURL = Main.savedLinks.get(selection).toString();
            //System.out.println("you clicked on: "+Main.savedURL);
        } catch (Exception e){

        }
    }

    public void openInBrowser(ActionEvent e) throws Exception{
        try {
            if(Main.savedURL != ""){
                URI open = new URI(Main.savedURL);
                java.awt.Desktop.getDesktop().browse(open);
                //System.out.println(Main.savedURL);
            }
        } catch (Exception ee){
                //ee.printStackTrace();
        }
    }



    public void refreshHome(ActionEvent event){
        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<String> wordsList = FXCollections.observableArrayList();

        for(int y=0;y<Main.savedLinks.size();y++){
            list.add(Main.savedLinks.get(y));
        }

        for(int y=0;y<Main.savedWords.size();y++){
            wordsList.add(Main.savedWords.get(y));
        }

        linksView.setItems(list);
        wordsView.setItems(wordsList);

        confirmationText.setText("Displaying data for: " +Main.savedMainURL);
    }

    public void setupLinksPage(ActionEvent event){
        ObservableList<String> list = FXCollections.observableArrayList();
        for(int y=0;y<Main.savedLinks.size();y++){
            list.add(Main.savedLinks.get(y));
        }
        linksPageList.setItems(list);
        linksLabel.setText("Displaying links found at: " + Main.savedMainURL);

    }

    public void getLinkOnLinksPage(MouseEvent event) throws IOException{
        try {
            int selection = linksPageList.getSelectionModel().getSelectedIndex();
            Main.savedURL = Main.savedLinks.get(selection).toString();
            //System.out.println("you clicked on: "+Main.savedURL);
        } catch (Exception e){

        }
    }

    public void setupWordsPage(ActionEvent event) throws IOException{
        try{
            Main.readHTML.clear();
            Main.getNiceHTML();
            ObservableList<String> wordsList = FXCollections.observableArrayList();
            for(int y=0;y<Main.readHTML.size();y++){
                wordsList.add(Main.readHTML.get(y));
            }
            wordsPageList.setItems(wordsList);
        } catch(Exception e){}

        wordsLabel.setText("Displaying HTML data for: "+Main.savedMainURL);
    }

    public void openSaveDialog(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Links");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt file", "*.txt"));
        Main.file = fileChooser.showSaveDialog(null);
        Main.generateLinksReport();
    }

    public void openHTMLSaveDialog(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export HTML");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".html file", "*.html"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt file", "*.txt"));
        Main.file = fileChooser.showSaveDialog(null);
        Main.generateHTMLReport();
    }

    public void openFullReportSaveDialog(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Full Report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt file", "*.txt"));
        Main.file = fileChooser.showSaveDialog(null);
        Main.generateFullReport();
    }

    public void onToggleClicked(ActionEvent event) throws Exception{
        if(rb1.isSelected()){
            //System.out.println("rb1");
            Main.setTheme(1);
        } else if(rb2.isSelected()){
            //System.out.println("rb2");
            Main.setTheme(2);
        } else if(rb3.isSelected()){
            //System.out.println("rb3");
            Main.setTheme(3);
        } else if(rb4.isSelected()){
            //System.out.println("rb4");
            Main.setTheme(4);
        }
    }

    public void onSavePreferencesClicked(ActionEvent event) throws IOException{
        Main.savePreferencesFile();
        changeToPref(event);
    }



}
