package wallpapercentral.view;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.PopupWindow;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.WallpaperView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MenuController{

    //IMPLEMENT ALL OF THE MENU OPTIONS IN THE FXML AND SPECIFY FUNCTION CALLS THERE AS WELL!!
    @FXML
    private MenuBar menuBar;

    private WallpaperModel model;
    private ObservableList<Menu> menus;
    private Map<String,MenuItem> itemMap;

    public MenuController() {
            model = new WallpaperModel();
            //initMenubar();
    }

    public void initMenubar() {
        System.out.println("IN HERE");
        menuBar = new MenuBar(new Menu("File"));
//        menus = menuBar.getMenus(); , null, new MenuItem("Close"))
//        //Put all the menuItems into a map with the text value as the key!
//        for (Menu menu : menus)
//            for (MenuItem menuItem : menu.getItems()) {
//                itemMap.put(menuItem.getText(),menuItem);
//                System.out.println(menu.getText());
//            }

//        itemMap.get("close").setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent e) {
//                System.out.println("We clicked it!");
//            }
//        });
    }

    public void initModel(WallpaperModel model) {this.model = model;}

    @FXML
    public void close(ActionEvent actionEvent) {
        //Implement Closing
        menuBar.getScene().getWindow().hide();
    }

    @FXML
    public void load(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.JPEG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);

        List<File> files = fileChooser.showOpenMultipleDialog(menuBar.getScene().getWindow());
        model.addImageFiles(files);
    }

    @FXML
    public void save(ActionEvent actionEvent) {
    }
}
