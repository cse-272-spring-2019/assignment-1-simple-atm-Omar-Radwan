import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class NumbersPad extends Screen {

	private GridPane numbers;
	private Button[] padButtons;
	private Label customerInput;

	public NumbersPad(int height, int width, Label customerInput) {
		super();
		this.height = height;
		this.width = width;
		this.numbers = new GridPane();
		padButtons = new Button[12];
		this.customerInput = customerInput;

	}

	public void prepareNumbersPad() {

		getNumbers().setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		for (int i = 0; i < 4; i++) {
			RowConstraints rc = new RowConstraints();
			rc.setPrefHeight((height) / 4);
			rc.setVgrow(Priority.ALWAYS);
			getNumbers().getRowConstraints().add(rc);
		}

		for (int i = 0; i < 3; i++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setPrefWidth((width) / 3);
			cc.setHgrow(Priority.ALWAYS);
			getNumbers().getColumnConstraints().add(cc);
		}

		for (int i = 0; i < 10; i++) {
			padButtons[i] = new Button(Integer.toString(i));
			padButtons[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				getNumbers().add(padButtons[i * 3 + j + 1], j, i);
			}
		}
		getNumbers().add(padButtons[0], 1, 3);

	}

	public void setButton2(String button2) {
		padButtons[11] = new Button(button2);
		padButtons[11].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		getNumbers().add(padButtons[11], 2, 3);
	}

	public void setButton1(String button1) {
		padButtons[10] = new Button(button1);
		padButtons[10].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		getNumbers().add(padButtons[10], 0, 3);
	}

	public Button getButton1() {
		return padButtons[10];
	}

	public Button getButton2() {
		return padButtons[11];
	}

	public void setNumbersOnAction() {
		for (int i = 0; i < 10; i++) {
			Button x = padButtons[i];
			x.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					customerInput.setText(customerInput.getText() + x.getText());
				}
			});
		}
	}

	public GridPane getNumbers() {
		return numbers;
	}

	public void setNumbers(GridPane numbers) {
		this.numbers = numbers;
	}

}
