import javafx.application.Application;
import javafx.stage.Stage;

public class AtmApplication extends Application {

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Atm Application");

		LoginScreen loginScreen = new LoginScreen(primaryStage);
		loginScreen.setSize(400, 400);
		
		HomeScreen homeScreen = new HomeScreen(primaryStage);
		InputScreen inputScreen = new InputScreen(primaryStage);
		

		loginScreen.setHomeScreen(homeScreen);
		homeScreen.setLoginform(loginScreen);
		homeScreen.setInputScreen(inputScreen);
		inputScreen.setHomeSceen(homeScreen);

		loginScreen.prepareScene();
		

		primaryStage.setScene(loginScreen.getScene());
		primaryStage.show();

	}

}
