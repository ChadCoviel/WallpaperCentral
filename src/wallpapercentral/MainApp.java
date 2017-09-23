package wallpapercentral;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import wallpapercentral.model.WallpaperView;

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
    private ImageView myImageView;
    public WallpaperView wp = new WallpaperView();

    public MainApp() {
        myImageView = new ImageView();
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
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
//        List<File> list =
//                fileChooser.showOpenMultipleDialog(main);
//        if (list != null) {
//            for (File file : list) {
//                openFile(file);
//            }
//        }
        //List<File> files = fileChooser.showOpenMultipleDialog(main);
        File file = fileChooser.showOpenDialog(main);

        try {
            //files.forEach();
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            myImageView.setImage(image);
        } catch (IOException ex) {
            //Logger.getLogger(FileChooserSample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WallpaperApp");

        initRootLayout();

        showWallpapersOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/wallpapercentral/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showWallpapersOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/wallpapercentral/view/WallpapersOverview.fxml"));
            AnchorPane wallpapersOverview = (AnchorPane) loader.load();
            //wallpapersOverview.setPadding(new Insets(25,25,25,25));
            wallpapersOverview.getChildren().add(myImageView);

            // Set person overview into the center of root layout.
            rootLayout.setCenter(wallpapersOverview);

//            // Give the controller access to the main app.
//            WallpapersOverviewController controller = loader.getController();
//            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
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