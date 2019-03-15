import javafx.scene.Scene;
import javafx.stage.Stage;

public class Screen {

	Scene scene;
	Stage stage;
	double width;
	double height;

	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public Scene getScene() {
		return scene;
	}

}
