package wallpapercentral.content;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import wallpapercentral.model.UIImageView;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.main.MainController;
//import wallpapercentral.view.ModelListListener;


public class ContentController{
    @FXML private FlowPane content;
    @FXML private AnchorPane ap;

    private WallpaperModel model;
    private Stage stage;
    private MainController main;
//    private ModelListListener modelListener;

    public void initMediator(MainController main) {this.main = main;}

    private void setListeners() {
//        modelListener.applyConsumer(uiImage -> ((UIImageView)uiImage).setOnMouseClicked(event -> main.changeToEditor()));
        ap.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        stage = (Stage) ap.getScene().getWindow();
                    }
                });
            }
        });
        model.getWallpaperData().addListener((ListChangeListener)(c -> {
            while (c.next())
                if (c.wasAdded()) {
                    content.getChildren().addAll(c.getList().subList(c.getFrom(),c.getTo()));
                    System.out.println(c.getList().subList(c.getFrom(),c.getTo()));
                    c.getList().subList(c.getFrom(),c.getTo()).forEach(image ->
                            ((UIImageView)image).setOnMouseClicked(event -> {
                                ((UIImageView) image).setSelected(true);
                                main.changeToEditor();
                            }));
                }
        }));
    }

    public void initModel(WallpaperModel model) {
        this.model = model;
        System.out.println(this.model);
        setListeners();
        System.out.println(content);
    }
}
