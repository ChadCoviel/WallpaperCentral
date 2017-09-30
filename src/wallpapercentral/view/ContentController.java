package wallpapercentral.view;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.WallpaperView;

import java.util.Observable;
import java.util.Observer;

public class ContentController implements ListChangeListener{
    @FXML
    private FlowPane content;

    @FXML
    private AnchorPane ap;

    private WallpaperModel model;

    public ContentController() {
        //model = new WallpaperModel();
    }

    public void initModel(WallpaperModel model) {
        this.model = model;
        this.model.getWallpaperData().addListener(this);
        if (this.model.getWallpaperData().size() != 0)
            this.model.getWallpaperData().forEach(wallpaper -> wallpaper.setOnMouseClicked(event -> enterEditMode()));
    }

    private void enterEditMode() {
        Scene editor = new Scene(null);
        ((Stage)ap.getScene().getWindow()).setScene(editor);
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
                        .forEach(wallpaper -> wallpaper.setOnMouseClicked(event -> enterEditMode()));
            }
    }
}
