package wallpapercentral.view;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.FlowPane;

import java.util.function.Consumer;

public class ModelListListener implements ListChangeListener {

    private Consumer imgConsumer;
    private FlowPane fp;
//    private

    public ModelListListener() {
        imgConsumer = value -> System.out.println("Not implemented!");
        fp = new FlowPane();
    }

    @Override
    public void onChanged(Change c) {
        while (c.next())
            if (c.wasAdded()) {
                fp.getChildren().addAll(c.getList().subList(c.getFrom(),c.getTo()));
                System.out.println(c.getList().subList(c.getFrom(),c.getTo()));
                c.getList().forEach(imgConsumer);
//                setImageListeners(c.getList().subList(c.getFrom(),c.getTo()));
            }
    }

    public void applyConsumer(Consumer c) {imgConsumer = c;}
    public void setContent(FlowPane flow) {this.fp = flow;}
}
