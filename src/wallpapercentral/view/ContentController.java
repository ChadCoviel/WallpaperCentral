package wallpapercentral.view;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import wallpapercentral.model.WallpaperModel;


public class ContentController implements ListChangeListener{
    @FXML private FlowPane content;
    @FXML private AnchorPane ap;

    private WallpaperModel model;
    private Stage stage;
    private MainController main;

    @FXML
    public void initialize() {
        setListeners();
    }

    public void initMediator(MainController main) {this.main = main;}

    public void setListeners() {
        ap.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        stage = (Stage) ap.getScene().getWindow();
                    }
                });
            }
        });
    }

    public void initModel(WallpaperModel model) {
        this.model = model;
        System.out.println(this.model);
        this.model.getWallpaperData().addListener(this);
        System.out.println(content);
        if (this.model.getWallpaperData().size() != 0)
            this.model.getWallpaperData().forEach(wallpaper -> {
                wallpaper.setOnMouseClicked(event -> stage.setScene(createEditScene()));
                wallpaper.setSelected(true);
            });
    }

    private Scene createEditScene() {

        return new Scene(null);
    }

    @Override
    public void onChanged(Change c) {
        System.out.println("This far");
        //c.next();
        while (c.next())
            if (c.wasAdded()) {
                content.getChildren().addAll(model.getWallpaperData().subList(c.getFrom(),c.getTo()));
                System.out.println(model.getWallpaperData().subList(c.getFrom(),c.getTo()));
                model.getWallpaperData().subList(c.getFrom(),c.getTo())
                        .forEach(wallpaper -> {
                            wallpaper.setOnMouseClicked(event -> stage.setScene(createEditScene()));
                            wallpaper.setSelected(true);
                        });
            }
    }
}
