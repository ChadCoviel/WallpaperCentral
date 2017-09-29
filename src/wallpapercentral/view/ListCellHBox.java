package wallpapercentral.view;

import javafx.scene.layout.HBox;

public class ListCellHBox extends HBox {

    public static final double MAX_NODES = 3;

    public boolean isFull() { return this.getChildren().size() == MAX_NODES; }

}
