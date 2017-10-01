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

        try {
              FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/Main.fxml"));
              root = mainLoader.load();
              MainController mainController = mainLoader.getController();

              FXMLLoader editorLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/ImageEditor.fxml"));
              editor = editorLoader.load();
              EditorController editorController = editorLoader.getController();

              mainController.initModel(model);
              editorController.initModel(model);
              model.addImageFiles(FileChooserUtils.openImagesDialog(primaryStage));
//            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/Menu.fxml"));
//            root.setTop(menuLoader.load());
//            MenuController menuController = menuLoader.getController();
//
//
//            SplitPane sp = new SplitPane();
//            sp.setOrientation(Orientation.HORIZONTAL);
//
//            FXMLLoader commandLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/Commands.fxml"));
//            //root.setTop(commandLoader.load());
//            sp.getItems().add(commandLoader.load());
//            CommandController commandController = commandLoader.getController();
//
//            FXMLLoader contentLoader = new FXMLLoader(getClass().getResource("/wallpapercentral/view/Content.fxml"));
//            //root.setTop(commandLoader.load());
//            sp.getItems().add(contentLoader.load());
//            ContentController contentController = contentLoader.getController();
//
//            sp.setDividerPositions(0.25f, 0.75f);
//            root.setCenter(sp);
//            //ListController listController = listLoader.getController();
//
//            WallpaperModel model = new WallpaperModel();
//            menuController.initModel(model);
//            contentController.initModel(model);
//            commandController.initModel(model);
//            model.addImageFiles(FileChooserUtils.openImagesDialog(this.primaryStage));
            //model.addWallpaperData(wallpaperData);
            //fp.getChildren().addAll(wallpaperData);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 800, 600);
        SceneController sceneController = new SceneController(scene);
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

