package wallpapercentral.view;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
    private double maxX;
    private double maxY;
    private GraphicsContext gc;

    @FXML
    public void initialize() {
        //sceneController.addScene("editor",this);
        maxX = canvas.getWidth();
        maxY = canvas.getHeight();
        displayingView = false;
        setListeners();
        gc = canvas.getGraphicsContext2D();
    }

    public void setSceneController(SceneController sceneController) {this.sceneController = sceneController;}

    private void setListeners() {

        ap.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                displayingView = true;
            }
        });
        canvas.setOnMousePressed(event -> {
                //System.out.println("Clicked, x:" + me.getSceneX() + " y:" + me.getSceneY());
                //the event will be passed only to the circle which is on fron
                setInitialCoordinates(event.getX(),event.getY());
//                resetDraw()
                event.consume();
            }
        );
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                //System.out.println("Dragged, x:" + me.getSceneX() + " y:" + me.getSceneY());
                //gc.closePath();
                System.out.println("canvas height: "+canvas.getHeight()+" canvas width: "+canvas.getWidth());
                //gc.clearRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());
                gc.fillRect(0.0,canvas.getHeight(),canvas.getWidth(),-canvas.getHeight());
                //gc.beginPath();
                gc.setFill(Color.GREEN);
                gc.setStroke(Color.BLUE);
                gc.setLineWidth(5);
                gc.setGlobalBlendMode(BlendMode.SCREEN);
                gc.setGlobalAlpha(0.5);
                //gc.setEffect(new BoxBlur(3 * 2, 3 * 2, 3));
                System.out.println("initialX: "+initX+" initialY: "+initY+"\ncurrentX: "+me.getX()+" currentY: "+me.getY());
                gc.fillRect(initX,initY,me.getX() - initX,me.getY() - initY);
            }
        });
        canvas.setOnMouseReleased(event -> {
            System.out.println("released");
            //gc.closePath();
        });
    }

    private void setInitialCoordinates(double sceneX, double sceneY) {
        System.out.println(initX+ "      "+initY);
        initX = sceneX;
        initY = sceneY;
    }

    public void initModel(WallpaperModel model) {
        this.model = model;
        this.model.getWallpaperData().addListener(this);
        System.out.println(this.model);
        //this.model.getWallpaperData().forEach(wallpaper -> wallpaper.addPropertyChangeListener(this));
    }

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
