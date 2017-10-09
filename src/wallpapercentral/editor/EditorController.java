package wallpapercentral.editor;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import wallpapercentral.app.SceneController;
import wallpapercentral.utils.FileChooserUtils;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.UIImageView;

public class EditorController{

    //USE THE BOUNDS PROPERTY OF THE RECTANGLE NODE!

    @FXML private AnchorPane ap;
    @FXML private AnchorPane container;
    @FXML private VBox vbox;
    @FXML private Button crop;
    @FXML private Button back;
    @FXML private Button save;
    @FXML private Button caption;


    private UIImageView currentImgView;
    private UIImageScrollPane scroll;
    private SceneController sceneController;
    private WallpaperModel model;
    private final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);

    public void setSceneController(SceneController sceneController) {this.sceneController = sceneController;}

    private void setListeners() {

        //BUTTON DISABLEPROPERTY.BIND!!!

        model.getWallpaperData().addListener((ListChangeListener)(c -> {
            while (c.next())
                if (c.wasAdded()) {
                    System.out.println(c.getList().subList(c.getFrom(),c.getTo()));
                    c.getList().subList(c.getFrom(),c.getTo()).forEach(image ->
                            ((UIImageView)image).addPropertyChangeListener(evt -> {
                                //Create the scroll pane and display the clicked image inside of it
                                if (((Boolean) evt.getNewValue()) == true) {
                                    container.getChildren().clear();
                                    scroll = new UIImageScrollPane();
                                    container.getChildren().add(scroll);
//                                    Parent scrollParent = scroll.getParent();
//                                    scroll.fitToWidthProperty().bind(scrollParent.wi);
//                                    scroll.prefWidthProperty().bind(container.prefWidthProperty());
//                                    scroll.prefHeightProperty().bind(container.prefHeightProperty());
//                                    scroll.setPrefHeight(550.0);
//                                    scroll.setPrefWidth(800.0);
                                    AnchorPane.setTopAnchor(scroll,0.0);
                                    AnchorPane.setBottomAnchor(scroll, 0.0);
                                    AnchorPane.setLeftAnchor(scroll, 0.0);
                                    AnchorPane.setRightAnchor(scroll, 0.0);
                                    scroll.getImgView().croppedProperty().set(false);
                                    currentImgView = (UIImageView) evt.getSource();
                                    scroll.getImgView().setImage(currentImgView.getImage());
                                    scroll.update();
                                    scroll.setZoom(true);
                                    bindSave();
//                                    setZoom();
//                                    System.out.println("ImgV height: "+scroll.getImgView().fitWidthProperty().getValue()+
//                                                        "ImgV width: "+scroll.getImgView().fitHeightProperty().getValue());
//                                    System.out.println("Canvas width: "+scroll.getCanvas().getWidth()+
//                                            " Canvas height: "+scroll.getCanvas().getHeight());

                                }
                            }));
                }
        }));

        save.setOnAction(action -> {
            FileChooserUtils.saveImage(scroll.getImg(),ap.getScene().getWindow());
        });

        back.setOnAction(event -> {
            scroll.getRubberband().reset();
            currentImgView.setSelected(false);
            sceneController.activate("content");
        });

        crop.setOnAction(event -> {
//            System.out.println("ImgView height: "+scroll.getImgView().getBoundsInParent().getWidth()+
//                    "ImgView width: "+scroll.getImgView().getBoundsInParent().getHeight());
//            System.out.println("Canvas width: "+scroll.getCanvas().getWidth()+
//                    " Canvas height: "+scroll.getCanvas().getHeight());
//            System.out.println("initialX: "+initX+" initialY: "+initY+"\nFinalX: "+finalX+" finalY: "+finalY);
            RubberbandSelection rubberband = scroll.getRubberband();
            if (rubberband.selectionProperty().get()) {
                System.out.println("We croppin");
                scroll.getImgView().cropImage(rubberband.getUpperLeftPoint().getX(),rubberband.getUpperLeftPoint().getY(),
                        rubberband.getSelectionWidth(),rubberband.getSelectionHeight());
                scroll.update();
                rubberband.reset();
            }
        });
    }

    private void bindSave() {
        save.disableProperty().bind(scroll.getImgView().croppedProperty().not());
    }

    public void initModel(WallpaperModel model) {
        this.model = model;
        setListeners();
        System.out.println(this.model);
    }
}
