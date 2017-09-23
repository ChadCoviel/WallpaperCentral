package wallpapercentral.view;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import wallpapercentral.MainApp;
import wallpapercentral.model.WallpaperView;

public class WallpapersOverviewController {

    private MainApp mainApp;

    //Make sure the are the same name as the id in the scene builder!
    @FXML
    private TableView<WallpaperView> wallpaperTable;

    @FXML
    private SplitPane splitpane;


    public WallpapersOverviewController() {
        //wallpaperTable.setItems(/*ObservableList<WallpaperView> value*/);
    }

    public void setMainApp(MainApp main) {
        this.mainApp = main;
    }
}
