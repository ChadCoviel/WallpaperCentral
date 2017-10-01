package wallpapercentral.view;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import wallpapercentral.SceneController;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.WallpaperView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EditorController implements PropertyChangeListener, ListChangeListener {

    @FXML private ImageView image;
    @FXML private AnchorPane ap;

    private SceneController sceneController;
    private WallpaperModel model;
    private boolean displayingView;

    @FXML
    public void initialize() {
        //sceneController.addScene("editor",this);
        displayingView = false;
        setListeners();
    }

    private void setListeners() {
        ap.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                displayingView = true;
            }
        });
    }

    public void initModel(WallpaperModel model) {
        this.model = model;
        this.model.getWallpaperData().addListener(this);
        System.out.println(this.model);
        //this.model.getWallpaperData().forEach(wallpaper -> wallpaper.addPropertyChangeListener(this));
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Yeah boiiiii "+evt.getNewValue());
        if (((Boolean) evt.getNewValue()) == true) {
            System.out.println(image);
//            image = (WallpaperView) evt.getSource();
            image.setImage(((WallpaperView) evt.getSource()).getImage());
            System.out.println(image +"     susssss");
        }
    }

    @Override
    public void onChanged(Change c) {
        System.out.println("huh?");
        while (c.next())
            if (c.wasAdded())
                model.getWallpaperData().subList(c.getFrom(),c.getTo())
                        .forEach(wallpaper -> wallpaper.addPropertyChangeListener(this));

    }
}
