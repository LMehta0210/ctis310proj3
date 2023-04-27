package edu.guilford;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DicePane extends GridPane{
    //attributes
    //Array of dice
    private Die[] dice;

    //array of TextField for face values and number of sides
    private TextField[] dieNumSidesFields;

    //array of Labels
    private Label[] dieFaceLabels;
    private Label[] dieNumSidesLabels;

    private Label sumLabel;

    //roll button
    private Button rollButton = new Button("Roll");

    //constructors
    public DicePane(Die[] dice) {
        this.dice = dice;
        this.dieNumSidesFields = new TextField[dice.length];
        this.dieFaceLabels = new Label[dice.length];
        this.dieNumSidesLabels = new Label[dice.length];
        this.sumLabel = new Label("Sum: " + this.sum());

        //add the roll button to the pane
        this.add(this.rollButton, 0, 0);

        //add the sum label to the pane
        this.add(this.sumLabel, 1, 0);

        //add the labels and text fields for each die
        for (int i = 0; i < dice.length; i++) {
            //create a label for the face value
            this.dieFaceLabels[i] = new Label("Face Value: " + dice[i].getFaceValue());
            //add the label to the pane
            this.add(this.dieFaceLabels[i], 0, i + 1);

            //create a label for the number of sides
            this.dieNumSidesLabels[i] = new Label("Number of Sides: " + dice[i].getNumSides());
            //add the label to the pane
            this.add(this.dieNumSidesLabels[i], 1, i + 1);

            //create a text field for the number of sides
            this.dieNumSidesFields[i] = new TextField("" + dice[i].getNumSides());
            //add the text field to the pane
            this.add(this.dieNumSidesFields[i], 2, i + 1);
        }

        //add an action listener to the roll button
        this.rollButton.setOnAction(e -> {
            //roll each die
            for (Die die : this.dice) {
                die.roll();
            }

            //update the labels
            for (int i = 0; i < dice.length; i++) {
                this.dieFaceLabels[i].setText("Face Value: " + dice[i].getFaceValue());
                this.dieNumSidesLabels[i].setText("Number of Sides: " + dice[i].getNumSides());
            }

            //update the sum label
            this.sumLabel.setText("Sum: " + this.sum());
        });

        //Give the pane a border
        this.setStyle("-fx-border-color: black");
        //and a background color
        this.setStyle("-fx-background-color: lightblue");

    }

    //methods
    //sum
    public int sum() {
        int sum = 0;
        for (Die die : this.dice) {
            sum += die.getFaceValue();
        }
        return sum;
    }
}
