public class Immovable extends WorldObject
{

    public Immovable(String name, int row, int col) {super(name, 'I', row, col);}

    public void step() {}

    //instantiated but not used
    @Override
    public boolean bump(int row, int col, int newRow, int newCol)
    {
        return false;
    }

    @Override
    public boolean checkHasMoved() { return false; }

    @Override
    public void setHasMoved(boolean hasMoved) {}

}
