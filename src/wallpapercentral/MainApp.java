package wallpapercentral;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.WallpaperView;
import wallpapercentral.view.CommandController;
import wallpapercentral.view.ContentController;
import wallpapercentral.view.MenuController;

/*Implement these classes later*/
//fx:controller="wallpapercentral.view.WallpapersOverview"
//fx:controller="wallpapercentral.view.RootLayout"
//FXCollections.observalearraylist with ListView item
//Image -> ImageView -> add to scene
//root.getChildren().add("your values/objects here");
//import javafx.scene.chart.BarChart;
//setUserAgentStylesheet(STYLESHEET_CASPIAN) Try it out. Modena theme is the default CSS applied at runtime.
//Try out adding other external stylesheets to provide consistent look and feel.
//Element.setId(ID_NUMBER) to have CSS be applied to the element
//scene.getStylesheets().add(YOUR_STYLESHEETS_HERE);
//CSS usually you do -fx-(property): value when working with javaFX
//getStyles().addClass(STYLE_CLASS);

public class MainApp extends Application {

    private Stage primaryStage;
    private ObservableList<WallpaperView> wallpaperData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WallpaperApp");

        BorderPane root = new BorderPane();

        try {
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/Menu.fxml"));
            root.setTop(menuLoader.load());
            MenuController menuController = menuLoader.getController();

            SplitPane sp = new SplitPane();
            sp.setOrientation(Orientation.HORIZONTAL);

            FXMLLoader commandLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/Commands.fxml"));
            //root.setTop(commandLoader.load());
            sp.getItems().add(commandLoader.load());
            CommandController commandController = commandLoader.getController();

            FXMLLoader contentLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/Content.fxml"));
            //root.setTop(commandLoader.load());
            sp.getItems().add(contentLoader.load());
            ContentController contentController = contentLoader.getController();

            sp.setDividerPositions(0.25f, 0.75f);
            root.setCenter(sp);
            //ListController listController = listLoader.getController();

            WallpaperModel model = new WallpaperModel();
            menuController.initModel(model);
            //listController.initModel(model);
            contentController.initModel(model);
            commandController.initModel(model);
            model.addImageFiles(promptUserSelection());
            //model.addWallpaperData(wallpaperData);
            //fp.getChildren().addAll(wallpaperData);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        //primaryStage.sizeToScene();
        primaryStage.show();
    }

    private List<File> promptUserSelection() {
        Stage main = getPrimaryStage();
        //chooser.showOpenDialog(node.getScene().getWindow());
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.JPEG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);

        List<File> files = fileChooser.showOpenMultipleDialog(main);
        return files;
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public ObservableList<WallpaperView> getWallpaperData() { return wallpaperData; }

    public void addEventListener() {
        //Set up a listeners object
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
*** Use the below code as a reference for how short we want to keep our Main class. It should be short and sweet. One
*** start method and then the public static void main...
// */
//
//    FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open Resource File");
//                fileChooser.getExtensionFilters().addAll(
//                new ExtensionFilter("Text Files", "*.txt"),
//                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
//                new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
//                new ExtensionFilter("All Files", "*.*"));
//                Stage primary = mainApp.getPrimaryStage();
//                File selectedFile = fileChooser.showOpenDialog(primary);
//                if (selectedFile != null) {
//                //primary.display(selectedFile);
//                }
