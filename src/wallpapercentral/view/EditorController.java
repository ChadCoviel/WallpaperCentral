package wallpapercentral.view;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import wallpapercentral.SceneController;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.WallpaperView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EditorController implements PropertyChangeListener, ListChangeListener {

    @FXML private ImageView image;
    @FXML private AnchorPane ap;
    @FXML private AnchorPane imageContainer;
    @FXML private Canvas canvas;

    private SceneController sceneController;
    private WallpaperModel model;
    private boolean displayingView;
    private double initX, initY;
    //private final double maxX = canvas.getWidth();
    //private final double maxY = canvas.getHeight();

    @FXML
    public void initialize() {
        //sceneController.addScene("editor",this);
        displayingView = false;
        setListeners();
    }


    public void setSceneController(SceneController sceneController) {this.sceneController = sceneController;}

    private void setListeners() {

        ap.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                displayingView = true;
            }
        });
        canvas.setOnMouseClicked(event -> System.out.println("Suuuuuuuh"));
        canvas.setOnMousePressed(event -> {
                //System.out.println("Clicked, x:" + me.getSceneX() + " y:" + me.getSceneY());
                //the event will be passed only to the circle which is on front
                setInitialCoordinates(event.getSceneX(),event.getSceneY());
                event.consume();
            }
        );
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                //System.out.println("Dragged, x:" + me.getSceneX() + " y:" + me.getSceneY());
                GraphicsContext gc = canvas.getGraphicsContext2D();
//                if (me.getSceneX() < maxX && me.getSceneY() < maxY) {
//                    Line line = new Line(initX, initY, me.getSceneX(), me.getSceneY());
//                    line.setFill(null);
//                    line.setStroke(Color.RED);
//                    line.setStrokeWidth(2);
//                    anchorRoot.getChildren().add(line);
//                }
//
//                initX = me.getSceneX() > maxX ? maxX : me.getSceneX();
//                initY = me.getSceneY() > maxY ? maxY : me.getSceneY();
            }
        });
    }

    private void setInitialCoordinates(double sceneX, double sceneY) {
        initX = sceneX;
        initY = sceneY;
    }

    public void initModel(WallpaperModel model) {
        this.model = model;
        this.model.getWallpaperData().addListener(this);
        System.out.println(this.model);
        //this.model.getWallpaperData().forEach(wallpaper -> wallpaper.addPropertyChangeListener(this));
    }
//    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
//            new EventHandler<MouseEvent>() {
//
//                @Override
//                public void handle(MouseEvent t) {
//                    orgSceneX = t.getSceneX();
//                    orgSceneY = t.getSceneY();
//                    orgTranslateX = ((Circle)(t.getSource())).getTranslateX();
//                    orgTranslateY = ((Circle)(t.getSource())).getTranslateY();
//                }
//            };
//
//    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
//            new EventHandler<MouseEvent>() {
//
//                @Override
//                public void handle(MouseEvent t) {
//                    double offsetX = t.getSceneX() - orgSceneX;
//                    double offsetY = t.getSceneY() - orgSceneY;
//                    double newTranslateX = orgTranslateX + offsetX;
//                    double newTranslateY = orgTranslateY + offsetY;
//
//                    ((Circle)(t.getSource())).setTranslateX(newTranslateX);
//                    ((Circle)(t.getSource())).setTranslateY(newTranslateY);
//                }
//            };

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Yeah boiiiii "+evt.getNewValue());
        if (((Boolean) evt.getNewValue()) == true) {
            System.out.println(image);
//            image = (WallpaperView) evt.getSource();
            image.setImage(((WallpaperView) evt.getSource()).getImage());
            System.out.println(image +"     susssss");
        }
    }

    @Override
    public void onChanged(Change c) {
        System.out.println("huh?");
        while (c.next())
            if (c.wasAdded())
                model.getWallpaperData().subList(c.getFrom(),c.getTo())
                        .forEach(wallpaper -> wallpaper.addPropertyChangeListener(this));

    }
}
