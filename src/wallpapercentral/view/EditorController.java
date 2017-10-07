package wallpapercentral.view;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import wallpapercentral.SceneController;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.UIImageView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EditorController implements PropertyChangeListener, ListChangeListener {

    //USE THE BOUNDS PROPERTY OF THE RECTANGLE NODE!

    @FXML private AnchorPane ap;
    @FXML private Button crop;
//    @FXML private StackPane stack;
    @FXML private Button back;

//    private UIImageView wallpaper;
    private ModelListListener modelListener;
    private UIImageStackPane stack;
    private SceneController sceneController;
    private WallpaperModel model;

    @FXML
    public void initialize() {
        modelListener = new ModelListListener();
        stack = new UIImageStackPane();
        stack.setPrefWidth(800.0);
        stack.setPrefWidth(550.0);
        ap.getChildren().add(stack);
        setListeners();
    }

    public void setSceneController(SceneController sceneController) {this.sceneController = sceneController;}

    private void setListeners() {
        ap.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
            }
        });

        back.setOnAction(event -> {
            stack.getRubberband().reset();
            sceneController.activate("content");
        });

        crop.setOnAction(event -> {
//            System.out.println("initialX: "+initX+" initialY: "+initY+"\nFinalX: "+finalX+" finalY: "+finalY);
            RubberbandSelection rubberband = stack.getRubberband();
            if (rubberband.selectionCreated())
                System.out.println("We croppin");
                stack.getImg().cropImage(rubberband.getUpperLeftPoint().getX(),rubberband.getUpperLeftPoint().getX(),
                        rubberband.getSelectionWidth(),rubberband.getSelectionHeight());
                rubberband.reset();
        });
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
//            stack.setImageView(new UIImageView(((UIImageView) evt.getSource()).getImage()));
            stack.setImage(((UIImageView) evt.getSource()).getImage());
//            wallpaper = stack.getImg();
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
