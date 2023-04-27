package edu.guilford;

public class Die {
    //attributes
    private int faceValue;
    private int numSides;

    //constructors
    public Die() {
        this.faceValue = 1;
        this.numSides = 6;
    }

    public Die(int numSides) {
        this.faceValue = 1;
        this.numSides = numSides;
    }

    //methods
    public void roll() {
        this.faceValue = (int) (Math.random() * this.numSides) + 1;
    }

    public int getFaceValue() {
        return this.faceValue;
    }

    public int getNumSides() {
        return this.numSides;
    }

    public void setNumSides(int numSides) {
        this.numSides = numSides;
    }

    @Override
    public String toString() {
        return "Die with " + this.numSides + " sides and a face value of " + this.faceValue;
    }

    public boolean equals(Die other) {
        return this.faceValue == other.faceValue && this.numSides == other.numSides;
    }

}
