package wallpapercentral.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface Listenable {
    PropertyChangeSupport getPropertyChangeSupport ();

    default void addPropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport ().addPropertyChangeListener(listener);
    }

    default void removePropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport ().removePropertyChangeListener(listener);
    }
}