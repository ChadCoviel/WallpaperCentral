package wallpapercentral.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import wallpapercentral.editor.EditorController;
import wallpapercentral.main.MainController;
import wallpapercentral.utils.FileChooserUtils;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.UIImageView;

public class MainApp extends Application {

    private Stage primaryStage;
    private ObservableList<UIImageView> wallpaperData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WallpaperApp");

        WallpaperModel model = new WallpaperModel();
        Pane content = new Pane();
        Pane editor = new Pane();
        MainController mainController = new MainController();
        EditorController editorController = new EditorController();

        try {
              FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/main/Main.fxml"));
              content = mainLoader.load();
              mainController = mainLoader.getController();

              FXMLLoader editorLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/editor/ImageEditor.fxml"));
              editor = editorLoader.load();
              editorController = editorLoader.getController();

              mainController.initModel(model);
              editorController.initModel(model);
              model.addImageFiles(FileChooserUtils.openImagesDialog(primaryStage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(content, 800, 600);
        SceneController sceneController = new SceneController(scene);
        sceneController.addScene("editor",editor);
        sceneController.addScene("content", content);
        mainController.setSceneController(sceneController);
        editorController.setSceneController(sceneController);
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
    public ObservableList<UIImageView> getWallpaperData() { return wallpaperData; }

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

