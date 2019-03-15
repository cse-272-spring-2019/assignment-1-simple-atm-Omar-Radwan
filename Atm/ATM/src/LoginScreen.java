import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class LoginScreen extends Screen {

	public HomeScreen homeScreen;

	public LoginScreen(Stage stage) {
		this.stage = stage;
	}

	public HomeScreen getHomeScreen() {
		return homeScreen;
	}

	public void setHomeScreen(HomeScreen homeScreen) {
		this.homeScreen = homeScreen;
	}


	public void prepareScene() {
		
		Authentication authentication = new Authentication();
		
		Label cardNumberLabel = new Label("Please Enter Your Card Number");
		Label customerInput = new Label();
		Button submit = new Button("Submit");
		GridPane grid = new GridPane();

		ColumnConstraints cc = new ColumnConstraints();
		RowConstraints rc = new RowConstraints();
		
		cc.setPrefWidth(250);
		cc.setHgrow(Priority.ALWAYS);
		grid.getColumnConstraints().add(cc);

		rc.setPrefHeight(25);
		rc.setVgrow(Priority.ALWAYS);
		grid.getRowConstraints().add(rc);

		rc = new RowConstraints();
		rc.setPrefHeight(25);
		rc.setVgrow(Priority.ALWAYS);
		grid.getRowConstraints().add(rc);

		rc = new RowConstraints();
		rc.setPrefHeight(50);
		rc.setVgrow(Priority.ALWAYS);
		grid.getRowConstraints().add(rc);

		rc = new RowConstraints();
		rc.setPrefHeight(200);
		rc.setVgrow(Priority.ALWAYS);
		grid.getRowConstraints().add(rc);

		NumbersPad numbersPad = new NumbersPad(170, 250, customerInput);

		numbersPad.prepareNumbersPad();

		numbersPad.setNumbersOnAction();

		grid.add(numbersPad.getNumbers(), 0, 3);

		submit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		customerInput.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		numbersPad.setButton2("Clear");
		numbersPad.setButton1("");
		numbersPad.getButton2().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				customerInput.setText("");
			}

		});

		grid.add(cardNumberLabel, 0, 0);
		grid.add(customerInput, 0, 1);
		grid.add(submit, 0, 2);

		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Card card = authentication.check(customerInput.getText());
				if (card != null) {
					homeScreen.setMessageToDisplay(card.getStringBalance());
					homeScreen.setCard(card);

					homeScreen.setSize(scene.getWidth(), scene.getHeight());
					homeScreen.prepareScene();
					stage.setScene(homeScreen.getScene());
				}
				else {
					cardNumberLabel.setText("Please Enter A valid Card Number");
					customerInput.setText("");
				}

			}
		});

		this.scene = new Scene(grid, height, width);

	}
}
