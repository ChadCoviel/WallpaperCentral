package wallpapercentral.view;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import wallpapercentral.SceneController;
import wallpapercentral.model.WallpaperModel;
import wallpapercentral.model.UIImageView;

public class EditorController{

    //USE THE BOUNDS PROPERTY OF THE RECTANGLE NODE!

    @FXML private AnchorPane ap;
    @FXML private Button crop;
//    @FXML private StackPane stack;
    @FXML private Button back;

    private UIImageView currentImgView;
    private UIImageStackPane stack;
    private SceneController sceneController;
    private WallpaperModel model;

    @FXML
    public void initialize() {
        stack = new UIImageStackPane();
        stack.setPrefWidth(800.0);
        stack.setPrefWidth(550.0);
        ap.getChildren().add(stack);
    }

    public void setSceneController(SceneController sceneController) {this.sceneController = sceneController;}

    private void setListeners() {
        model.getWallpaperData().addListener((ListChangeListener)(c -> {
            while (c.next())
                if (c.wasAdded()) {
                    System.out.println(c.getList().subList(c.getFrom(),c.getTo()));
                    c.getList().subList(c.getFrom(),c.getTo()).forEach(image ->
                            ((UIImageView)image).addPropertyChangeListener(evt -> {
                                if (((Boolean) evt.getNewValue()) == true) {
                                    currentImgView = (UIImageView) evt.getSource();
                                    stack.setImage(currentImgView.getImage());
                                }
                            }));
                }
        }));

        ap.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
            }
        });

        back.setOnAction(event -> {
            stack.getRubberband().reset();
            currentImgView.setSelected(false);
            sceneController.activate("content");
        });

        crop.setOnAction(event -> {
//            System.out.println("initialX: "+initX+" initialY: "+initY+"\nFinalX: "+finalX+" finalY: "+finalY);
            RubberbandSelection rubberband = stack.getRubberband();
            if (rubberband.selectionCreated())
                System.out.println("We croppin");
                stack.getImgView().cropImage(rubberband.getUpperLeftPoint().getX(),rubberband.getUpperLeftPoint().getY(),
                        rubberband.getSelectionWidth(),rubberband.getSelectionHeight());
                rubberband.reset();
        });
    }

    public void initModel(WallpaperModel model) {
        this.model = model;
        setListeners();
        System.out.println(this.model);
    }
}
