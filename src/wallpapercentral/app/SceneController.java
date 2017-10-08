package wallpapercentral.app;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import wallpapercentral.model.WallpaperModel;

import java.util.HashMap;

public class SceneController {
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene main;
    private WallpaperModel model;

    public SceneController(Scene main) {
        this.main = main;
    }

    public void addScene(String name, Pane pane){
        screenMap.put(name, pane);
    }

    public void removeScene(String name){
        screenMap.remove(name);
    }

    public void activate(String name){
        main.setRoot( screenMap.get(name) );
    }
}
