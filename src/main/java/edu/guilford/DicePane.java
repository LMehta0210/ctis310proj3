package edu.guilford;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class DicePane extends GridPane {
    // attributes
    // Array of dice
    private Die[] dice;

    // array of TextField for face values and number of sides
    private TextField dieNumSidesField = new TextField();

    // array of Labels
    private Label[] dieFaceLabels;

    private Label dieNumSidesLabel;
    private Label sumLabel;

    // roll button
    // Step 1: Declare button
    // Step 2: Instantiate button
    private Button rollButton = new Button("Roll");

    // array of keep checkboxes
    private CheckBox[] keepCheckBoxes;

    // ImageView attribute
    private ImageView rollingImageView;

    // constructors
    public DicePane(Die[] dice) {
        this.dice = dice;
        this.dieFaceLabels = new Label[dice.length];
        this.sumLabel = new Label("Sum: " + this.sum());
        this.dieNumSidesLabel = new Label("Number of Sides: " + dice[0].getNumSides());
        this.keepCheckBoxes = new CheckBox[dice.length];

        //Instantiate the imageView with image we want
        File rollIcon = new File(this.getClass().getResource("dice.gif").getPath());
        this.rollingImageView = new ImageView(rollIcon.toURI().toString());

        //Step 3: add the roll button to the pane
        this.add(this.rollButton, 0, 0);

        //add the sum label to the pane
        this.add(this.sumLabel, 0, 1);
        //add the number of sides label to the pane
        this.add(this.dieNumSidesLabel, 1, 0);
        //add the number of sides text field to the pane
        this.add(this.dieNumSidesField, 1, 1);

        //add the rolling image view to the pane
        this.add(this.rollingImageView, 2, 0, 3, 10);

        //add the labels and keep checkboxes for each die
        for (int i = 0; i < dice.length; i++) {
            //create a label for the face value
            this.dieFaceLabels[i] = new Label("Face Value: " + dice[i].getFaceValue());
            //add the label to the pane
            this.add(this.dieFaceLabels[i], 0, i + 2);
            //create Checkbox
            this.keepCheckBoxes[i] = new CheckBox("Keep");
            //add the checkbox to the pane
            this.add(this.keepCheckBoxes[i], 1, i + 2);
        }

        //Step 4 and 5: add an event listener and connect it to the component
        //add an action listener to the roll button
        this.rollButton.setOnAction(e -> {
            //rool each die if keep is not checked
            for (int i = 0; i < dice.length; i++) {
                if (!this.keepCheckBoxes[i].isSelected()) {
                    dice[i].roll();
                    this.dieFaceLabels[i].setText("Face Value: " + dice[i].getFaceValue());
                }
            }

            //update the sum label
            this.sumLabel.setText("Sum: " + this.sum());
        });

        //add an action listener to the number of sides text field
        this.dieNumSidesField.setOnAction(e -> {
            try{    
                //get the number of sides from the text field
                int numSides = Integer.parseInt(this.dieNumSidesField.getText());
                if (numSides<1){
                    throw new InvalidNumberOfSidesException("Number of sides must be greater than 0");
                }
                //set the number of sides for each die
                for (Die die : this.dice) {
                    die.setNumSides(numSides);
                }

                //update the number of sides label
                this.dieNumSidesLabel.setText("Number of Sides: " + dice[0].getNumSides());

            } catch (NumberFormatException ex) {
                System.out.println("Please enter an integer number of sides");
            } catch (InvalidNumberOfSidesException ex){
                System.out.println(ex.getMessage());
            }
        });

        //Give the pane a border
        this.setStyle("-fx-border-color: black");
        //and a background color
        this.setStyle("-fx-background-color: lightblue");

    }

    // methods
    // sum
    public int sum() {
        int sum = 0;
        for (Die die : this.dice) {
            sum += die.getFaceValue();
        }
        return sum;
    }

    //Exception for invalid number of sides
    public class InvalidNumberOfSidesException extends Exception {
        public InvalidNumberOfSidesException(String message) {
            super(message);
        }
    }
}
