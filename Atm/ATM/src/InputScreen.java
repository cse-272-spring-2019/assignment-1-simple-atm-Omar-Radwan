
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

public class InputScreen extends Screen {

	private Card card;
	private HomeScreen homeScreen;
	private String operationType;

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public void setHomeSceen(HomeScreen homeScreen) {
		this.homeScreen = homeScreen;
	}

	public InputScreen(Stage stage) {
		this.stage = stage;

	}

	private double LabelToDouble(Label customerInput) {
		double a;
		try {
			a = Double.parseDouble(customerInput.getText());
		} catch (NumberFormatException e) {
			a = -1;
		}
		return a;
	}

	public void prepareScene() {

		GridPane grid = new GridPane();

		Button operationButton = new Button(operationType);
		Label customerInput = new Label();
		Label message = new Label("Enter the Amount You Want To " + operationType);
		ColumnConstraints cc = new ColumnConstraints();

		RowConstraints rc = new RowConstraints();

		cc.setPrefWidth(250);
		cc.setHgrow(Priority.ALWAYS);
		grid.getColumnConstraints().add(cc);

		rc.setPrefHeight(50);
		rc.setVgrow(Priority.ALWAYS);
		grid.getRowConstraints().add(rc);

		rc = new RowConstraints();
		rc.setPrefHeight(50);
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

		numbersPad.setButton1("Return");
		numbersPad.getButton1().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				homeScreen.setSize(scene.getWidth(), scene.getHeight());
				homeScreen.prepareScene();
				stage.setScene(homeScreen.getScene());
			}
		});

		numbersPad.setButton2("Clear");

		numbersPad.getButton2().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				customerInput.setText("");
				;
			}

		});

		customerInput.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		operationButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		grid.add(message, 0, 0);
		grid.add(customerInput, 0, 1);
		grid.add(operationButton, 0, 2);

		operationButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				card.getHistory().resetHistoryToLast();

				double value = LabelToDouble(customerInput);

				if (value >= 0) {

					if (operationType.equals("Deposit")) {
						if (card.deposit(value)) {
							homeScreen.setMessageToDisplay(
									"Successful " + operationType + " Operation " + card.getStringBalance());
						} else {
							double remaining = value - card.getMaxDepositValue();
							homeScreen.setMessageToDisplay(
									"Maximum Deposit Limit is 30,000\n" + remaining + " Couldn't Be Deposited");
						}

					} else {
						if (card.withdraw(value)) {
							homeScreen.setMessageToDisplay(
									"Successful " + operationType + " Operation " + card.getStringBalance());
						}

						else {
							homeScreen.setMessageToDisplay(
									"Couldn't Process The Withdraw Operation Because of Insufficient Credit\n"
											+ card.getStringBalance());
						}
					}

				}

				else {

				}

				homeScreen.setSize(scene.getWidth(), scene.getHeight());
				homeScreen.prepareScene();
				stage.setScene(homeScreen.getScene());
			}
		});

		this.scene = new Scene(grid, width, height);

	}

}
