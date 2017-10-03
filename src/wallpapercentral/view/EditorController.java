package wallpapercentral.view;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
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
    @FXML private Canvas canvas;
    @FXML private Button crop;

    private WallpaperView wallpaper;
    private SceneController sceneController;
    private WallpaperModel model;
    private double initX, initY;
    private double finalX, finalY;
    private double maxX;
    private double maxY;
    private GraphicsContext gc;

    @FXML
    public void initialize() {
        //sceneController.addScene("editor",this);
        maxX = canvas.getWidth();
        maxY = canvas.getHeight();
        setListeners();
        gc = canvas.getGraphicsContext2D();
    }

    public void setSceneController(SceneController sceneController) {this.sceneController = sceneController;}

    private void setListeners() {
        ap.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
            }
        });
        canvas.setOnMouseEntered(event -> {
            System.out.println("inside");
            ap.getScene().setCursor(Cursor.CROSSHAIR);
//            cp.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        });
        canvas.setOnMousePressed(event -> {
                //System.out.println("Clicked, x:" + me.getSceneX() + " y:" + me.getSceneY());
                //the event will be passed only to the circle which is on fron
                setInitialCoordinates(event.getX(),event.getY());
//                resetDraw()
                event.consume();
            }
        );
        canvas.setOnMouseDragged(event -> {
                //System.out.println("Dragged, x:" + me.getSceneX() + " y:" + me.getSceneY());
                //gc.closePath();
                System.out.println("canvas height: "+canvas.getHeight()+" canvas width: "+canvas.getWidth());
                //gc.clearRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());
                gc.fillRect(0.0,canvas.getHeight(),canvas.getWidth(),-canvas.getHeight());
                gc.setFill(Color.BLUE);
                gc.setStroke(Color.BLUE);
                gc.setLineWidth(5);
                gc.setGlobalBlendMode(BlendMode.SCREEN);
                gc.setGlobalAlpha(0.33);
                System.out.println("initialX: "+initX+" initialY: "+initY+"\ncurrentX: "+event.getX()+" currentY: "+event.getY());
//                gc.fillRect(initX,initY,event.getX() - initX,event.getY() - initY);
                if ((event.getX() >= 0) && (event.getX() <= canvas.getWidth()) && (event.getY() >= 0)
                    && (event.getY() <= canvas.getHeight())) {
                        GraphicsContextUtils.fillRectWithAnchor(gc,initX,initY,event.getX(),event.getY());
                        setCurrentCoordinates(event.getX(),event.getY());
                }
        });
        canvas.setOnMouseReleased(event -> {
            System.out.println("released");
        });
        canvas.setOnMouseExited(event -> ap.getScene().setCursor(Cursor.DEFAULT));
        crop.setOnAction(event -> cropImage());
    }

    private void setCurrentCoordinates(double x, double y) {
        finalX = x;
        finalY = y;
    }

    private void setInitialCoordinates(double x, double y) {
        System.out.println(initX+ "      "+initY);
        initX = x;
        initY = y;
    }

    public void initModel(WallpaperModel model) {
        this.model = model;
        this.model.getWallpaperData().addListener(this);
        System.out.println(this.model);
        //this.model.getWallpaperData().forEach(wallpaper -> wallpaper.addPropertyChangeListener(this));
    }

    public void cropImage() {
        
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
