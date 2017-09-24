package wallpapercentral.view;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import wallpapercentral.MainApp;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.WallpaperView;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

//This class will be responsible for setting up all of our event listeners and communicate with out model.
public class ListController implements ListChangeListener, Observer {
    //Make sure the are the same name as the id in the scene builder!
    @FXML
    private ListView<WallpaperView> listView;

    //private MainApp mainApp;
    private WallpaperModel model;
    //private MenuControlller menuControlller;
    private BorderPane root;

    //Contructors
    public ListController() {
        this.model = new WallpaperModel();
        this.listView = new ListView<>();
    }
    //public TableViewController(WallpaperModel model) {this.model = model;}

    //Methods
    //public void setMainApp(MainApp main) {this.mainApp = main;}
    public void setRoot(BorderPane root) {this.root = root;}

    public void showWallpapersOverview() {
        try {
            // Load wallpaper overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/wallpapercentral/view/ListView.fxml"));
            AnchorPane wallpapersOverview = (AnchorPane) loader.load();
            //wallpapersOverview.setPadding(new Insets(25,25,25,25));
            //wallpapersOverview.getChildren().add(myImageView);

            // Set person overview into the center of root layout.
            //rootLayout.setCenter(wallpapersOverview);

//            // Give the controller access to the main app.
//            WallpapersOverviewController controller = loader.getController();
//            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initModel(WallpaperModel model) {this.model = model;}


    public void updateList() {
        listView.setItems(model.getWallpaperData());
    }

    @Override
    public void onChanged(Change c) {
        System.out.println("we made it");
        updateList();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
