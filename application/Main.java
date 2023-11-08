package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent rootFXML = FXMLLoader.load(getClass().getResource("vue.fxml"));
			Scene sceneFXML = new Scene(rootFXML);
		    sceneFXML.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Choisir un velo");
			
			primaryStage.setScene(sceneFXML);
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
