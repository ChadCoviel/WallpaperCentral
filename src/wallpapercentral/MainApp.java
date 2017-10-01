package wallpapercentral;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import wallpapercentral.model.FileChooserUtils;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.WallpaperView;
import wallpapercentral.view.*;

public class MainApp extends Application {

    private Stage primaryStage;
    private ObservableList<WallpaperView> wallpaperData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WallpaperApp");

        WallpaperModel model = new WallpaperModel();
        Pane root = new Pane();
        Pane editor = new Pane();
        MainController mainController = new MainController();
        EditorController editorController = new EditorController();

        try {
              FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/Main.fxml"));
              root = mainLoader.load();
              mainController = mainLoader.getController();

              FXMLLoader editorLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/ImageEditor.fxml"));
              editor = editorLoader.load();
              editorController = editorLoader.getController();

              mainController.initModel(model);
              editorController.initModel(model);
              model.addImageFiles(FileChooserUtils.openImagesDialog(primaryStage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 800, 600);
        SceneController sceneController = new SceneController(scene);
        mainController.setSceneController(sceneController);
        editorController.setSceneController(sceneController);
        sceneController.addScene("editor",editor);
        primaryStage.setScene(scene);
        //primaryStage.sizeToScene();
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

