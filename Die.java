package CLASS;

class Die {
    private int value;
    private boolean isKept;

    public Die() {
        roll();
        this.isKept = false; // start with the die new time each round
    }

    // current dice
    public int getValue() {
        return value;
    }

    public void roll() {
        if (!isKept) {
            value = (int) (Math.random() * 6) + 1; // value between 1 and 6
        }
    }

    // make sure dice is kept
    public boolean isKept() {
        return isKept;

    }

    // keep or unkeep certain dice
    public void toggleKeep() {
        this.isKept = !this.isKept;

    }

    // state if it kept or not
    public void setKept(boolean isKept) {
        this.isKept = isKept;
    }
}
