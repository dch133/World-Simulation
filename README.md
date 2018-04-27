# World-Simulation
________________________________________________________________________
Description
-------------
The World:

o A 2D array that can store one Moveable, Immoveable, or Autonomous object per cell of the array.
o A method called public void step() that iterates through the cells of the array changing the state of the world by updating the position of all    the Autonomous and Moveable objects (see below). It does this once for each call to the method.
o The method public void display() to display the world on the screen using Swing. This must be a GUI grid displaying simple text tokens that represent the items in the world.
o The method public void add(item,x,y) is used to populate the world by adding items to the array at cell x,y. The cell needs to be available (empty) or the add fails.
o The constructor defines the size of the array. The array is empty.

• Immovable:
o Private string name, describing what it is.
o Private char token, a character that stores the symbol that represents the item when printed to the screen.
o Private int x, y, which specifies the location in the array this item exists.
o The method public char getToken() returns the character symbol. (If you want a greater challenge, you can replace the symbol by a graphic.)

• Moveable:
o Implemented exactly as Immovable, however it can be moved by one cell position if bumped into by an Autonomous object. It is displaced in the same direction as the bump. For example, if the item was bumped on its right side and the motion of the bump was towards the left, then the item will move to the left.

• Autonomous:
o Implemented exactly like Moveable (bumped by a Moveable object or another Autonomous object causes it to shift one cell in the direction it was bumped).
o A public void step() method that randomly picks a direction and updates the item to a new location by one cell.

• Construct a main method that builds a world and then runs a simulation for 100 iterations.
o My program will not prompt the user at the beginning.
o My main method will call private static buildWorld() method that will be hand coded to construct a world of a particular size and then populated with 5 immovable, 3 moveable and 2 autonomous objects.



Design Techniques:
------------------
Abstraction: WorldObject Class is abstract containing all the attributes
             - an instantiated step() method (used by Autonomous)
             - abstract getters (all children), overloaded step() (Movable object),
             - abstract: bump(), checkHasMoved(),setHasMoved() to be implemented all by Autonomous object

Hierarchy: WorldObject has 3 children: Movable, Immovable, Autonomous

Polymorphism: The step() and overloaded step are written for WorldObject allowing all its children to use it as well.
              
Encapsulation: All attributes in WorldObject (and children) are private accessible only through getters and setters 

Object class: step() with input was instantiated in World OBject class allowing all children to use it the same way 
              if they are bumped into.

Design Patterns:
----------------

Decorator Pattern:   

                     The overriden step() methods allow to use inputed coordinates that will calculate in which direction
                     the bumped object should move.
                     
                     The original step() is similar but allows to choose a random direction in which to move.

Template Pattern: 

                  WorldObject is abstract with attributes, method signatures, and an implemented step(int x, int y) method.
                  
                  Any object that gets bumped into will use that template and implemented step(x,y)
                  
                  to try to move in the direction given those coordinates (x,y)
                  
Model-View-Controller: 

                       - World is the model that stores the location on every object in its array.
                       
                       - World is the controller as it has a step() method that iterates over its array to try to move
                         all the Autonomous objects.
                         
                       - Each child of WorldObject is a view as it holds all the information for each component of the world
                         They hold the name, token, coordinates, which is used by World to create a simulation in diplay()
                         
                       - WorldMain can be considered a view as it literally shows (calls GUI) the simulation of all the data it has been gived..
                       
