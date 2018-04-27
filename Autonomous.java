public class Autonomous extends WorldObject

{

    //Make sure the Object moves only once per iteration of the World array
    private boolean hasMoved = false;
    public boolean checkHasMoved() { return this.hasMoved; }
    public void  setHasMoved(boolean hasMoved) { this.hasMoved = hasMoved; }

    public Autonomous(String name, int row, int col) { super(name, 'A', row, col); }


    //Randomly chooses where to go
    public void step()
    {
        // get new coordinates
        int x = getRow();
        int y = getCol();
        int newX, newY;

        newX = -1; newY = -1;

        double getStep = Math.random(); //randomly choose where to go

        if (getStep >= 0 && getStep < 0.25)
        {
            newX = x;
            newY = y + 1;
        }
        if (getStep > 0.25 && getStep < 0.50)
        {
            newX = x + 1;
            newY = y;
        }
        if (getStep > 0.50 && getStep < 0.75)
            {
                newX = x;
                newY = y - 1;
            }
            if (getStep > 0.75 && getStep <= 1.0)
            {
                newX = x - 1;
                newY = y;
            }
            // make sure new position exists
            if (newX < 0 || newY < 0 || newX >= World.getInstance().getArray().length||
                    newY >= World.getInstance().getArray()[0].length) return;

            // Check if object bumps into anything in the way then try to move those objects
            if (this.bump(x,y,newX,newY))
            {   //move this object to its new place
                this.setRow(newX); //Update new row value
                this.setCol(newY); //Update new col value
                World.getInstance().add(this, newX,newY); //update position on map
                World.getInstance().add(null, x,y); //remove object from current position on map
            }
        }


    //Try to make the object move it if is bumped into
    public boolean bump(int x, int y, int newX, int newY) {

        //Make sure new position is valid
        if (newX < 0 || newY < 0 || newX > World.getInstance().getArray().length||
                newY > World.getInstance().getArray()[0].length) return false;

        //check if there is already an object at new position
        WorldObject ObjInTheWay = World.getInstance().getArray()[newX][newY];

        //if spot is empty move there right away
        if (ObjInTheWay == null) return true;

        //If Immovable, try another step
        else if (ObjInTheWay.getToken() == 'I' ) return false;

        //If Movable/Autonomous, try to make it move
        else if (ObjInTheWay.getToken() == 'M' || ObjInTheWay.getToken() == 'A')
            return (ObjInTheWay.step(x,y));

        else
            return false;
    }

}
