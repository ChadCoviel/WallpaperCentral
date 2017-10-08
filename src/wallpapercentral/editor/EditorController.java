package wallpapercentral.editor;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private UIImageScrollPane stack;
    private SceneController sceneController;
    private WallpaperModel model;
//    private boolean cropped = false;

    @FXML
    public void initialize() {
        stack = new UIImageScrollPane();
        stack.prefWidthProperty().bind(container.prefWidthProperty());
        stack.prefHeightProperty().bind(container.prefHeightProperty());
        container.getChildren().add(stack);
    }

    public void setSceneController(SceneController sceneController) {this.sceneController = sceneController;}

    private void setListeners() {

        //BUTTON DISABLEPROPERTY.BIND!!!

        model.getWallpaperData().addListener((ListChangeListener)(c -> {
            while (c.next())
                if (c.wasAdded()) {
                    System.out.println(c.getList().subList(c.getFrom(),c.getTo()));
                    c.getList().subList(c.getFrom(),c.getTo()).forEach(image ->
                            ((UIImageView)image).addPropertyChangeListener(evt -> {
                                if (((Boolean) evt.getNewValue()) == true) {
                                    container.getChildren().clear();
                                    stack = new UIImageScrollPane();
                                    container.getChildren().add(stack);
                                    stack.prefWidthProperty().bind(container.prefWidthProperty());
                                    stack.prefHeightProperty().bind(container.prefHeightProperty());
//                                    stack.setHvalue(0.5);
//                                    stack.setVvalue(0.5);
                                    stack.getImgView().croppedProperty().set(false);
                                    currentImgView = (UIImageView) evt.getSource();
                                    stack.getImgView().setImage(currentImgView.getImage());
                                    stack.resizeCanvas();
                                    System.out.println("ImgV height: "+stack.getImgView().fitWidthProperty().getValue()+
                                                        "ImgV width: "+stack.getImgView().fitHeightProperty().getValue());
                                    System.out.println("Canvas width: "+stack.getCanvas().getWidth()+
                                            " Canvas height: "+stack.getCanvas().getHeight());

                                }
                            }));
                }
        }));

        save.disableProperty().bind(stack.getImgView().croppedProperty().not());
        save.setOnAction(action -> {
            FileChooserUtils.saveImage(stack.getImg(),ap.getScene().getWindow());
        });

        back.setOnAction(event -> {
            stack.getRubberband().reset();
            currentImgView.setSelected(false);
            sceneController.activate("content");
        });

        crop.setOnAction(event -> {
            System.out.println("ImgView height: "+stack.getImgView().getBoundsInParent().getWidth()+
                    "ImgView width: "+stack.getImgView().getBoundsInParent().getHeight());
            System.out.println("Canvas width: "+stack.getCanvas().getWidth()+
                    " Canvas height: "+stack.getCanvas().getHeight());
//            System.out.println("initialX: "+initX+" initialY: "+initY+"\nFinalX: "+finalX+" finalY: "+finalY);
            RubberbandSelection rubberband = stack.getRubberband();
            if (rubberband.selectionProperty().get()) {
                System.out.println("We croppin");
                stack.getImgView().cropImage(rubberband.getUpperLeftPoint().getX(),rubberband.getUpperLeftPoint().getY(),
                        rubberband.getSelectionWidth(),rubberband.getSelectionHeight());
                stack.resizeCanvas();
                rubberband.reset();
            }
        });
    }

    public void initModel(WallpaperModel model) {
        this.model = model;
        setListeners();
        System.out.println(this.model);
    }
}
