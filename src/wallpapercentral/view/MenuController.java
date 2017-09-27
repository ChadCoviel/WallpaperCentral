package wallpapercentral.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import wallpapercentral.model.WallpaperModel;

import java.util.Map;

public class MenuController{

    @FXML
    private MenuBar menuBar;

    private WallpaperModel model;
    private ObservableList<Menu> menus;
    private Map<String,MenuItem> itemMap;

    public MenuController() {
            model = new WallpaperModel();
            initMenubar();
    }

    public void initMenubar() {
        menuBar = new MenuBar(new Menu("File", null, new MenuItem("Close")));
//        menus = menuBar.getMenus();
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
}
