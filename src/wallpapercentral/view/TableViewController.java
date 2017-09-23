package wallpapercentral.view;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import wallpapercentral.MainApp;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.WallpaperView;

//This class will be responsible for setting up all of our event listeners and communicate with out model.
public class TableViewController{

    private MainApp mainApp;
    private WallpaperModel wpModel;

    //Make sure the are the same name as the id in the scene builder!
    @FXML
    private TableView<WallpaperView> wallpaperTable;

    @FXML
    private SplitPane splitpane;


    public TableViewController() {
        //wallpaperTable.setItems(/*ObservableList<WallpaperView> value*/);
    }

    public void setMainApp(MainApp main) {
        this.mainApp = main;
    }

//    public void addModel(AbstractModel model) {
//        registeredModels.add(model);
//        model.addPropertyChangeListener(this);
//    }
//
//    public void removeModel(AbstractModel model) {
//        registeredModels.remove(model);
//        model.removePropertyChangeListener(this);
//    }
//
//    public void addView(AbstractViewPanel view) {
//        registeredViews.add(view);
//    }

}
