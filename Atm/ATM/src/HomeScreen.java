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

public class HomeScreen extends Screen {

	private Card card;
	private LoginScreen loginScreen;
	private InputScreen inputScreen;
	private String messageToDisplay;

	public HomeScreen(Stage stage) {
		this.stage = stage;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public LoginScreen getLoginform() {
		return loginScreen;
	}

	public void setLoginform(LoginScreen loginform) {
		this.loginScreen = loginform;
	}

	public InputScreen getInputScreen() {
		return inputScreen;
	}

	public void setInputScreen(InputScreen inputScreen) {
		this.inputScreen = inputScreen;
	}

	public void setMessageToDisplay(String messageToDisplay) {
		this.messageToDisplay = messageToDisplay;
	}

	public void prepareScene() {

		Button deposit = new Button("Deposit");
		Button withdraw = new Button("Withdraw");

		Button next = new Button("Next Transaction");
		Button previous = new Button("Previous Transaction");
		Button balance = new Button("Balance");

		Button logout = new Button("Logout");
		Label historyLabel = new Label(messageToDisplay);

		GridPane grid = new GridPane();


		RowConstraints rc = new RowConstraints();
		rc.setPrefHeight(100);
		rc.setVgrow(Priority.ALWAYS);

		ColumnConstraints cc = new ColumnConstraints();
		cc.setHgrow(Priority.ALWAYS);
		cc.setPrefWidth(250);
		grid.getColumnConstraints().add(cc);
		grid.getRowConstraints().add(rc);

		for (int i = 0; i < 6; i++) {
			rc = new RowConstraints();
			rc.setPrefHeight(150 / 6);
			rc.setVgrow(Priority.ALWAYS);
			grid.getRowConstraints().add(rc);
		}

		grid.add(historyLabel, 0, 0);
		grid.add(balance, 0, 1);
		grid.add(next, 0, 2);
		grid.add(previous, 0, 3);
		grid.add(deposit, 0, 4);
		grid.add(withdraw, 0, 5);
		grid.add(logout, 0, 6);



		historyLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		balance.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		next.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		previous.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		deposit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		withdraw.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		logout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);



		inputScreen.setCard(card);

		balance.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				historyLabel.setText(card.getStringBalance());

			}

		});

		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Transaction transaction = card.getHistory().getNext();
				if (transaction != null) {
					historyLabel.setText(card.getHistory().getTransactionNumber() + ". " + transaction.toString());
				}

			}
		});

		previous.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Transaction transaction = card.getHistory().getPrevious();
				if (transaction != null) {
					historyLabel.setText(card.getHistory().getTransactionNumber() + ". " + transaction.toString());
				}

			}
		});

		deposit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				inputScreen.setOperationType("Deposit");
				inputScreen.setSize(scene.getWidth(), scene.getHeight());

				inputScreen.prepareScene();
				stage.setScene(inputScreen.getScene());
			}
		});

		withdraw.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				inputScreen.setOperationType("Withdraw");
				inputScreen.setSize(scene.getWidth(), scene.getHeight());
				inputScreen.prepareScene();
				stage.setScene(inputScreen.getScene());
			}
		});

		logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				loginScreen.setSize(scene.getWidth(), scene.getHeight());
				loginScreen.prepareScene();
				stage.setScene(loginScreen.getScene());
			}
		});

		this.scene = new Scene(grid, width, height);

	}

}
