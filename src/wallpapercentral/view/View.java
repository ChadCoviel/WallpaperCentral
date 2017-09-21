package wallpapercentral.view;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import wallpapercentral.MainApp;

import java.io.File;

public class View {

    private MainApp mainApp;

    public View() {
        System.out.println("View Made");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new ExtensionFilter("All Files", "*.*"));
        Stage primary = mainApp.getPrimaryStage();
        File selectedFile = fileChooser.showOpenDialog(primary);
        if (selectedFile != null) {
            //primary.display(selectedFile);
        }
    }

    public void setMainApp(MainApp main) {
        this.mainApp = main;
    }
}
