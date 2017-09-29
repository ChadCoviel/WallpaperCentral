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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import wallpapercentral.MainApp;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.WallpaperView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Function;

//This class will be responsible for setting up all of our event listeners and communicate with out model.
public class ListController implements ListChangeListener, Observer {
    //Make sure the are the same name as the id in the scene builder!
    @FXML
    private ListView<HBox> listView;

    //private MainApp mainApp;
    private WallpaperModel model;
    //private MenuControlller menuControlller;
    private BorderPane root;
    //private

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
        //Function<Integer,Integer> add1 = x -> x + 1;
        //(["jade","chad"]).forEach(System.out::println);
    }


    //Don't worry to much about chifting when deleting items. Add a button to readjust the cells and compact all items.
    //Have the button called CleanUp.
    public void updateList() {
        System.out.println("huh");
        ObservableList<WallpaperView> temp = model.getWallpaperData();
        ObservableList<HBox> imageRows = FXCollections.observableArrayList();
        //listView.setItems(model.getWallpaperData())

        //Simplify this loop tomorrow. Make it more flexible. HBOX.GETCHILDREN().SIZE() for number of nodes currently inside
        HBox h = new HBox();
        setHBoxPreferences(h);


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

//        mpcListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
//            @Override
//            public ListCell<String> call(ListView<String> param){
//                return createXCell();
//            }
//        });
    }

    public void setHBoxPreferences(HBox hBox) {
        hBox.setFillHeight(true);
        hBox.setPrefSize(600.0, 200.0);
    }

    //Creates the cells and adds listeners for changes and handlers
//    public ListCell<String> createWallpaperCell() {
//        final ListCell<String> cell = new ListCell<String>();
//        final ListCellHBox hbox = new ListCellHBox();
//
////        hbox.setSpacing(120);
//        hbox.getChildren().addAll(label,button);
//
//        cell.itemProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
//                if (newValue == null) {
//                    cell.setText(null);
//                    cell.setGraphic(null);
//                } else {
//                    cell.setText(newValue);
//                    cell.setGraphic(hbox);
//                }
//            }
//        });
//
//        return cell ;
//
//    }

    private void addToList(List<WallpaperView> wallpaperViews) {

    }

    @Override
    public void onChanged(Change c) {
        if (c.wasAdded() && c.getFrom() != c.getTo())
            addToList(model.getWallpaperData().subList(c.getFrom(),c.getTo()));

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

