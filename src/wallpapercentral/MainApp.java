package wallpapercentral;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.WallpaperView;
import wallpapercentral.view.CommandController;
import wallpapercentral.view.ListController;
import wallpapercentral.view.MenuController;

import javax.imageio.ImageIO;

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

    public MainApp() {
        //myImageView = new ImageView();
        Stage main = getPrimaryStage();
        //chooser.showOpenDialog(node.getScene().getWindow());
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
//        if (file != null) {
//            openFile(file);
//        }
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.JPEG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);
//        List<File> list =
//                fileChooser.showOpenMultipleDialog(main);
//        if (list != null) {
//            for (File file : list) {
//                openFile(file);
//            }
//        }
        List<File> files = fileChooser.showOpenMultipleDialog(main);
        //File file = fileChooser.showOpenDialog(main);

        try {
            //files.forEach();
            for (File file: files) {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                WallpaperView wp = new WallpaperView(file.toURI().toURL().toString());
                wp.setPreserveRatio(false);
                wp.setFitHeight(200);
                wp.setFitWidth(200);
                //myImageView.setImage(image);
                wallpaperData.add(wp);
            }
        } catch (IOException ex) {
            //Logger.getLogger(FileChooserSample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WallpaperApp");

        BorderPane root = new BorderPane();

        try {
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/Menu.fxml"));
            root.setTop(menuLoader.load());
            MenuController menuController = menuLoader.getController();

            FXMLLoader commandLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/Commands.fxml"));
            //root.setTop(commandLoader.load());
            CommandController commandController = commandLoader.getController();

            //Figure out how to make children resize dynamically to fit parent when it resizes!!!
            SplitPane sp = new SplitPane();
            FlowPane fp = new FlowPane();
            fp.setPrefWrapLength(600);
            ScrollPane scp = new ScrollPane();
//            scp.setContent(fp);
            scp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scp.setPrefSize(600,600);
            //scp.setFitToHeight(true);
            //scp.setFitToWidth(true);
            AnchorPane ap = new AnchorPane();
            AnchorPane ap2 = new AnchorPane();
            AnchorPane ap3 = new AnchorPane();
//            ap3.getChildren().add(fp);
//            ap3.setTopAnchor(fp,0.0);
//            ap3.setBottomAnchor(fp,0.0);
//            ap3.setRightAnchor(fp,0.0);
//            ap3.setLeftAnchor(fp,0.0);
            scp.setContent(fp);
            sp.setOrientation(Orientation.HORIZONTAL);
//            FXMLLoader listLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/ListView.fxml"));
//            ap2.getChildren().add(listLoader.load());
            ap2.getChildren().add(scp);
            sp.getItems().add(commandLoader.load());
            sp.getItems().add(ap2);
            //sp.setResizableWithParent(ap2,true);
            //sp.setResizableWithParent(ap,true);
            //sp.setDividerPositions(0.3f, 0.6f);
            root.setCenter(sp);
            //ListController listController = listLoader.getController();

            WallpaperModel model = new WallpaperModel();
            menuController.initModel(model);
            //listController.initModel(model);
            model.addWallpaperData(wallpaperData);
            fp.getChildren().addAll(wallpaperData);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
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
