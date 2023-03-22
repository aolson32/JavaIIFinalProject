/**
 * This is the main class class, it just implements the main class and start class
 */
package javaiifinalprojectforrealmaybe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaIIFinalProjectForRealMaybe extends Application
{
    /**
     * this loads the user interface
     * @param stage - this is the user interface that loads upon running
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("MaybeActuallyFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * this launches the program
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
}
