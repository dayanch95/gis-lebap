package DirBrowser;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirectoryBrowserApp extends Application {
    // private Object String;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    private Parent createContent(){
        ListView<String> listView = new ListView<String>();
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(Paths.get(System.getProperty("user.dir")).toFile());
        var file = chooser.showOpenDialog(null);
        if (file != null) {
            var startDir = file.toPath();
            try{
                listView.getItems().clear();
                Files.walk(startDir)
                        .filter(path -> Files.isDirectory(path))
                        .forEach(dir -> {
                            listView.getItems().add(dir.toString());
                        });
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }


        var btn = new Button("Browse...");
        btn.setOnAction(e ->{

        });
        var root = new VBox(btn, listView);
        root.setPrefSize(800, 600);
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
