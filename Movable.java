public class Movable extends WorldObject
{

    public Movable(String name, int row, int col) {super(name, 'M', row, col);}

    //instantiated but not used
    @Override
    public void step() {}

    @Override
    public boolean bump(int row, int col, int newRow, int newCol) { return false; }

    @Override
    public boolean checkHasMoved() { return false; }

    @Override
    public void setHasMoved(boolean hasMoved) {}
}
