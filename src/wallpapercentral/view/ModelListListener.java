package wallpapercentral.view;

import javafx.collections.ListChangeListener;

public class ModelListListener implements ListChangeListener {


    @Override
    public void onChanged(Change c) {
        while (c.next())
            if (c.wasAdded()) {
//                content.getChildren().addAll(model.getWallpaperData().subList(c.getFrom(),c.getTo()));
                System.out.println(c.getList().subList(c.getFrom(),c.getTo()));
//                setImageListeners(c.getList().subList(c.getFrom(),c.getTo()));
            }
    }
}
