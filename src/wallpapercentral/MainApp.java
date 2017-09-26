package wallpapercentral;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.WallpaperView;
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
    private BorderPane rootLayout;
    private SplitPane splitpane1;//using id tag
    private ObservableList<WallpaperView> wallpaperData = FXCollections.observableArrayList();
    public WallpaperView wp = new WallpaperView();

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

            SplitPane sp = new SplitPane();
            AnchorPane ap = new AnchorPane();
            AnchorPane ap2 = new AnchorPane();
            sp.setOrientation(Orientation.HORIZONTAL);
            FXMLLoader listLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/ListView.fxml"));
            ap2.getChildren().add(listLoader.load());
            sp.getItems().add(ap);
            sp.getItems().add(ap2);
            root.setCenter(sp);
            ListController listController = listLoader.getController();

            WallpaperModel model = new WallpaperModel();
            menuController.initModel(model);
            listController.initModel(model);
            model.addWallpaperData(wallpaperData);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 600, 600);
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
 */
