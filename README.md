# World-Simulation
________________________________________________________________________

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
                       
