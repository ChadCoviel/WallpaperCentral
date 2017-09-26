/*
 * Classname
 *
 * Version information
 *
 * Date
 *
 * Copyright notice
 */

package wallpapercentral.view;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import wallpapercentral.MainApp;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.WallpaperView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

//This class will be responsible for setting up all of our event listeners and communicate with out model.
public class ListController implements ListChangeListener, Observer {
    //Make sure the are the same name as the id in the scene builder!
    @FXML
    private ListView<HBox> listView;

    //private MainApp mainApp;
    private WallpaperModel model;
    //private MenuControlller menuControlller;
    private BorderPane root;

    //Contructors
    public ListController() {
        //this.model = new WallpaperModel();
        this.listView = new ListView<>();
        listView.setPrefSize(800, 200);
        listView.setEditable(true);
        //HBox hbox = new HBox();
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

    public void initModel(WallpaperModel model) {
        this.model = model;
        this.model.getWallpaperData().addListener(this);
    }


    //Don't worry to much about chifting when deleting items. Add a button to readjust the cells and compact all items.
    //Have the button called CleanUp.
    public void updateList() {
        System.out.println("huh");
        ObservableList<WallpaperView> temp = model.getWallpaperData();
        ObservableList<HBox> imageRows = FXCollections.observableArrayList();
        //listView.setItems(model.getWallpaperData())
        int n = (temp.size() / 3) + ((temp.size() % 3 == 0) ? 0 : 1);
        for (int i = 0; i < n; i++) {
            HBox h = new HBox(temp.get(i * 3),temp.get(i * 3 + 1), temp.get(i * 3 + 2));
            h.setFillHeight(true);
            h.setPrefSize(400.0,200.0);
            //h.setHgrow(listView, Priority.ALWAYS);
            imageRows.add(h);
        }
        listView.setItems(imageRows);
        listView.setCellFactory(listView -> new ListCell<HBox>() {
            //private HBox hbox;
            @Override
            public void updateItem(HBox row, boolean empty) {
                super.updateItem(row, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    //hbox = new HBox(temp.get(0),temp.get(1),temp.get(2));
                    //setText(row);
                    setGraphic(row);
                }
            }
        });
    }

    @Override
    public void onChanged(Change c) {
        System.out.println("we made it");
        updateList();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

//    static class HBoxImageCell extends HBox {
//        private ArrayList<ImageView> images;
//
//        public HBoxImageCell(ArrayList<ImageView> img) {
//            super();
//            this.images = img;
//        }
//
//        @Override
//        protected void updateItem(String item, boolean empty) {
//            super.updateItem(item, empty);
//            setText(null);  // No text in label of super class
//            if (empty) {
//                lastItem = null;
//                setGraphic(null);
//            } else {
//                lastItem = item;
//                label.setText(item!=null ? item : "<null>");
//                setGraphic(hbox);
//            }
//        }
//    }

    //Runnable when you don't want to create classes for each callback
//    runCallback(new Runnable()
//    {
//        @Override
//        public void run()
//        {
//            // Running callback
//        }
//    });
}

