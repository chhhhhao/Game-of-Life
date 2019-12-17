package GameOfLife;

public class Unit {
    //    0 or 1
    private int state;
    private int age = 0;
    //  the coordinates of the unit
    private int x;
    private int y;
    private boolean hadBeenOccupied = false;
    //  the state in the next step
    //  "-1" represents that this unit hasn't been set
    private int nextState = -1;

    public Unit(int state, int x, int y) {
        this.state = state;
        this.x = x;
        this.y = y;
        if (state == 1) {
            this.age = 1;
            this.hadBeenOccupied = true;
        }
    }

    public int getAge() {
        return age;
    }

    public int getState() {
        return state;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getHadBeenOccupied() {
        return hadBeenOccupied;
    }

    public void setNextState(int nextState) {
        this.nextState = nextState;
    }

    public void evolution() {
        if (nextState == 0) {
            state = nextState;
            age = 0;
        } else if (nextState == 1) {
            state = nextState;
            age++;
            hadBeenOccupied = true;
        }

        this.nextState = -1;
    }
}
