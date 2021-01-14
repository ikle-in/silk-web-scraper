package sample;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.*;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    static ArrayList<String> savedLinks = new ArrayList<String>();
    static ArrayList<String> savedWords = new ArrayList<String>();
    static ArrayList<String> readHTML = new ArrayList<String>();
    static String savedMainURL = "";
    static String savedURL = "";
    static File file;
    static ReadOnlyDoubleProperty height;
    static ReadOnlyDoubleProperty width;
    static int theme = 1;

    static final String THEME_ONE_COLOR_ONE = "#17252a";
    static final String THEME_ONE_COLOR_TWO = "#2b7a78";
    static final String THEME_ONE_COLOR_THREE = "#3aafa9";
    static final String THEME_ONE_COLOR_FOUR = "#def2f1";
    static final String THEME_ONE_COLOR_FIVE = "#feffff";

    static final String THEME_TWO_COLOR_ONE = "#414a4c";
    static final String THEME_TWO_COLOR_TWO = "#3b444b";
    static final String THEME_TWO_COLOR_THREE = "#353839";
    static final String THEME_TWO_COLOR_FOUR = "#232b2b";
    static final String THEME_TWO_COLOR_FIVE = "#0e1111";

    static final String THEME_THREE_COLOR_ONE = "#ff4747";
    static final String THEME_THREE_COLOR_TWO = "#00bcb4";
    static final String THEME_THREE_COLOR_THREE = "#c4e86b";
    static final String THEME_THREE_COLOR_FOUR = "#ffb547";
    static final String THEME_THREE_COLOR_FIVE = "#e1ee32";

    static final String THEME_FOUR_COLOR_ONE = "#ff4747";
    static final String THEME_FOUR_COLOR_TWO = "#00bcb4";
    static final String THEME_FOUR_COLOR_THREE = "#c4e86b";
    static final String THEME_FOUR_COLOR_FOUR = "#ffb547";
    static final String THEME_FOUR_COLOR_FIVE = "#e1ee32";

    static final String THEME_FIVE_COLOR_ONE = "#ff00c7";
    static final String THEME_FIVE_COLOR_TWO = "#ff8b00";
    static final String THEME_FIVE_COLOR_THREE = "#fff800";
    static final String THEME_FIVE_COLOR_FOUR = "#53ff00";
    static final String THEME_FIVE_COLOR_FIVE = "#00adff";



    @Override
    public void start(Stage primaryStage) throws Exception {

        readPreferencesFile();
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

        final String startupScene = fxmlFileName;
        Parent root = FXMLLoader.load(getClass().getResource("splash.fxml"));
        primaryStage.setTitle("Silk Web Scraper");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();

        //Load splash screen with fade in effect
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), root);
        fadeIn.setFromValue(0.5);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        fadeIn.play();

        fadeIn.setOnFinished((e) -> {
            try {
                Parent root2 = FXMLLoader.load(getClass().getResource(startupScene));
                primaryStage.setTitle("Silk Web Scraper");
                primaryStage.setScene(new Scene(root2, 900, 600));
                primaryStage.show();
            } catch(IOException ex){};
        });

        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            width = primaryStage.widthProperty();
        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            height = primaryStage.heightProperty();
        });

    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void getNiceHTML() throws IOException{
        if(readHTML.size()>1){
            readHTML.clear();
        }
        URL niceURL = new URL(savedMainURL);
        Scanner sc = new Scanner(niceURL.openStream());
        String currentWord = null;
        while(sc.hasNext()){
            currentWord = sc.nextLine();
            readHTML.add(currentWord);
        }
    }


    public static void getHTML(URL url, ArrayList<String> links,ArrayList<String> words) throws IOException {
        Scanner sc = new Scanner(url.openStream());
        int lineCount = 0;
        String currentWord = null;
        int startIndex = 0;
        int endIndex = 0;
        Byte current = null;

        while(sc.hasNext()){
            currentWord = sc.next();

            //adds ALL WORDS to words ArrayList
            words.add(currentWord);

            currentWord = currentWord.replaceAll("<.*?>", "");
            //System.out.println(currentWord);

            //grabs links
            if (currentWord.contains("HREF=") | currentWord.contains("href=") | currentWord.contains("SRC=") | currentWord.contains("src=")){
                startIndex = currentWord.indexOf("\"");

                try {
                    endIndex = currentWord.indexOf("\"", startIndex + 1);
                    currentWord = currentWord.substring(startIndex + 1, endIndex);
                    //System.out.println("LINK DETECTED: " + currentWord);
                    lineCount++;
                } catch (Exception e){}

                //handles links that point to same site
                if(!currentWord.contains("http")){
                    if(currentWord.charAt(0) == '/'){
                        //System.out.println("INTERNAL LINK DETECTED!!!!!!! REFORMATTING..");
                        String tempLink = null;
                        String baseLink = null;
                        int period = url.toString().indexOf('.');
                        int endSlash = url.toString().indexOf('/',period);
                        baseLink = url.toString().substring(0, endSlash);
                        //System.out.println("Base site: " +baseLink);
                        tempLink = baseLink + currentWord;
                        currentWord = tempLink;
                    } else if (currentWord.contains("../../") && !currentWord.contains("../../../")){
                        //2 sets of ellipses
                        String tempLink = null;
                        String baseLink = null;
                        int period = url.toString().indexOf('.');
                        int endSlash = url.toString().indexOf('/',period);

                        endSlash = url.toString().indexOf('/',endSlash +1);
                        endSlash = url.toString().indexOf('/',endSlash +1);
                        endSlash = url.toString().indexOf('/',endSlash +1);
                        endSlash = url.toString().indexOf('/',endSlash +1);


                        baseLink = url.toString().substring(0, endSlash);
                        //System.out.println("Base site: " +baseLink);
                        tempLink = currentWord.substring(5,currentWord.length());
                        currentWord = baseLink + tempLink;
                        //System.out.println("Link changed to: "+currentWord);

                    } else if(currentWord.contains("../../../")){
                        //3 sets of ellipses
                        String tempLink = null;
                        String baseLink = null;
                        int period = url.toString().indexOf('.');
                        int endSlash = url.toString().indexOf('/',period);
                        endSlash = url.toString().indexOf('/',endSlash +1);
                        endSlash = url.toString().indexOf('/',endSlash +1);
                        endSlash = url.toString().indexOf('/',endSlash +1);
                        endSlash = url.toString().indexOf('/',endSlash +1);
                        /*endSlash = url.toString().indexOf('/',endSlash +1);
                        endSlash = url.toString().indexOf('/',endSlash +1);*/
                        baseLink = url.toString().substring(0, endSlash);
                        //System.out.println("Base site: " +baseLink);
                        tempLink = currentWord.substring(8,currentWord.length());
                        currentWord = baseLink + tempLink;
                        //System.out.println("Link changed to: "+currentWord);
                    }



                }

                //adding links to ArrayList
                links.add(currentWord);

            }

        }
        //System.out.println("\n" +"Finished getting HTML data for: "+url);
        //System.out.println("Wrote "+lineCount+ " lines.");
    }



    public static void generateLinksReport() throws IOException{
        try{
            long start = System.currentTimeMillis();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(start);
            FileWriter fileWriter = new FileWriter(file);

            //add in report header- name date etc
            fileWriter.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            fileWriter.write("SILK WEB SCRAPER - Ver 1.0.0 - LINK REPORT: " + file + "\n");
            fileWriter.write("REPORT CREATED: " + formatter.format(date) +" BY " + System.getProperty("user.name") + " ON HOST " + InetAddress.getLocalHost().getHostName() + "\n");
            fileWriter.write("ENVIRONMENT: " + System.getProperty("os.name")+", ARCHITECTURE: "+System.getProperty("os.arch") +", VERSION: "+System.getProperty("os.version") + "\n");
            fileWriter.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n \n \n");

            for(int x=0; x<savedLinks.size();x++){
                fileWriter.write(savedLinks.get(x) + "\n");
            }
            //end of report-------------- finished time, starting time, total duration
            long end = System.currentTimeMillis();
            long total = end-start;

            fileWriter.write("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            fileWriter.write("END OF REPORT - "+savedLinks.size()+" LINES WRITTEN\n");
            fileWriter.write("RUN DURATION: "+total+" milliseconds\n");
            fileWriter.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            fileWriter.close();
        } catch (Exception e){}
    }

    public static void generateHTMLReport() throws IOException{
        try{
            getNiceHTML();
            FileWriter fileWriter = new FileWriter(file);
            for(int x=0; x<readHTML.size();x++){
                fileWriter.write(readHTML.get(x) + "\n");
            }
            fileWriter.close();
        } catch (Exception e){}
    }

    public static void generateFullReport() throws IOException{
        try{
            long start = System.currentTimeMillis();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(start);
            getNiceHTML();
            FileWriter fileWriter = new FileWriter(file);

            //add in report header- name date etc
            fileWriter.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            fileWriter.write("SILK WEB SCRAPER - Ver 1.0.0 - FULL REPORT: " + file + "\n");
            fileWriter.write("REPORT CREATED: " + formatter.format(date) +" BY " + System.getProperty("user.name") + " ON HOST " + InetAddress.getLocalHost().getHostName() + "\n");
            fileWriter.write("ENVIRONMENT: " + System.getProperty("os.name")+", ARCHITECTURE: "+System.getProperty("os.arch") +", VERSION: "+System.getProperty("os.version") + "\n");
            fileWriter.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n \n");
            fileWriter.write("-----SECTION 1: LINKS DETECTED-----\n \n");

            for(int x=0; x<savedLinks.size();x++){
                fileWriter.write(savedLinks.get(x) + "\n");
            }

            fileWriter.write("\n\n-----SECTION 2: FULL HTML-----\n \n");

            for(int x=0; x<readHTML.size();x++){
                fileWriter.write(readHTML.get(x) + "\n");
            }

            //end of report-------------- finished time, starting time, total duration
            long end = System.currentTimeMillis();
            long total = end-start;

            fileWriter.write("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            fileWriter.write("END OF REPORT - "+(savedLinks.size()+readHTML.size())+" LINES WRITTEN\n");
            fileWriter.write("RUN DURATION: "+total+" milliseconds\n");
            fileWriter.write("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            fileWriter.close();
        } catch (Exception e){}

    }

    public static String parseRODS(ReadOnlyDoubleProperty in){
        String out = null;
        String temp = in.toString();
        int oldSpace = 0;
        int newSpace = 0;
        oldSpace = (temp.indexOf("value: ") + 7);
        newSpace = (temp.indexOf("]"));

        out = temp.substring(oldSpace,newSpace);

        return out;
    }

    public static void setTheme(int themeNumber){
        theme = themeNumber;
    }

    public static void savePreferencesFile() throws IOException{
        try{
            String preferencesFile = (new JFileChooser().getFileSystemView().getDefaultDirectory().toString());
            String fileName = preferencesFile + File.separatorChar + "Silk Web Scraper" + File.separatorChar + "Preferences.txt";
            File prefs = new File(fileName);
            prefs.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(fileName);
            String writeMe = Integer.toString(theme);
            writer.write(writeMe);
            writer.close();
            //System.out.println(writeMe);
        } catch (Exception e){}
    }

    public static void readPreferencesFile() throws IOException{
        try{
            String preferencesFile = (new JFileChooser().getFileSystemView().getDefaultDirectory().toString());
            String fileName = preferencesFile + File.separatorChar + "Silk Web Scraper" + File.separatorChar + "Preferences.txt";
            File prefs = new File(fileName);
            prefs.getParentFile().mkdirs();

            Scanner in = new Scanner(prefs);
            String input = in.next();
            while(in.hasNext()){
                input = in.next();
            }
            in.close();
            //System.out.println(input + " is the value");
            theme = Integer.parseInt(input);

        } catch (Exception e){
            //System.out.println("issue reading prefs");
            //e.printStackTrace();
        }
    }


}
