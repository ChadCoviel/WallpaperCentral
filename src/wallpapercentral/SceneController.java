package wallpapercentral;

import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import wallpapercentral.model.WallpaperModel;

import java.util.HashMap;

public class SceneController {
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene main;
    private WallpaperModel model;

    public SceneController(Scene main) {
        this.main = main;
        model = new WallpaperModel();
//        main.getRoot().
        ;
    }

    protected void addScene(String name, Pane pane){
        screenMap.put(name, pane);
    }

    protected void removeScene(String name){
        screenMap.remove(name);
    }

    protected void activate(String name){
        main.setRoot( screenMap.get(name) );
    }
}
