package wallpapercentral.view;

import javafx.collections.ObservableList;
import wallpapercentral.model.UIImageView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UIImageViewListener implements PropertyChangeListener{
    private ObservableList<UIImageView> images;
    public UIImageViewListener(ObservableList<UIImageView> imgs) {
        this.images = imgs;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
