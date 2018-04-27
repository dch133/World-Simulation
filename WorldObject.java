
public abstract class WorldObject
{
    private String name;

    private char token;

    private int row, col;



    public WorldObject(String name, char token, int row, int col)
    {
        this.name = name;
        this.token = token;
        this.row = row;
        this.col = col;
    }

    public char getToken() { return token;}

    public void setToken(char token) { this.token = token; }

    public int getRow() { return row; }

    public void setRow(int row) { this.row = row; }

    public int getCol() { return col; }

    public void setCol(int col) { this.col = col; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public abstract void step();//used by autonomous to randomly move
    public abstract boolean bump(int row, int col, int newRow, int newCol); //used by autonomous if it need to bump

    //used by movable/autonomous to move it by 1 step in the right direction after its bumped into given the bumper's coordinates
    public boolean step(int bumperX, int bumperY)
    {
        int x = getRow();
        int y = getCol();
        int newX, newY;

        //Calculate where to move ( direction)
        int difX = x - bumperX;
        int difY = y - bumperY;

        //update new coordinates
        newX = x + difX;
        newY = y + difY;

        //make sure new position exists
        if (newX < 0 || newY < 0 || newX > World.getInstance().getArray().length ||
                newY > World.getInstance().getArray()[0].length) return false;

        //check if there is already an object at new position
        WorldObject ObjInTheWay = World.getInstance().getArray()[newX][newY];

        //if spot is empty move there right away
        if (ObjInTheWay == null)
        {   //move this object to its new place
            this.setRow(newX);
            this.setCol(newY);
            World.getInstance().add(this, newX,newY);
            World.getInstance().add(null, x,y);
            return true;
        }
        //If Immovable, try another step
        else if (ObjInTheWay.getToken() == 'I' ) return false;

            //If Movable, try to make it move. If fails, try another step
        else if (ObjInTheWay.getToken() == 'M' || ObjInTheWay.getToken() == 'A' )
        {
            if (!ObjInTheWay.step(x,y)) return false;
            else
            {   //move this object to its new place
                this.setRow(newX); //Update new row value
                this.setCol(newY); //Update new col value
                World.getInstance().add(this, newX,newY); //update position on map
                World.getInstance().add(null, x,y); //remove object from current position on map
                return true;
            }
        }

        return false;

    }
    //used to determine if the autonomous function has moved (or tried to move)
    //We want to avoid calling step() more than once in an iteration
    public abstract boolean checkHasMoved();
    public abstract void setHasMoved(boolean hasMoved);

}
