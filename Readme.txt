Quoridor
Stephen Sherratt, Matthew Todd, Rebecca Wiley

Usage:
Commands: 
To start a new game type: newgame
To load a game type: loadgame (follow the promts to load the current game)
To exit the program, type: exit

When playing a game the commands you can use are:
undo: in human vs AI mode will undo the last two moves, in human vs human will undo the last move made
redo: in human vs AI mode will redo the last two moves, in human vs human will redo the last move made
savegame: Lets you save a game
quit: Returns to the prompt

To enter a move: type the column you want to go to ("a"->"i"), followed by the row number (1->9) (eg "a5")
To place a wall: Enter the top left square column and row, followed by either:
 - h for horizontal wall (eg "e4h")
 - v for vertical wall (eg "b5v")


Changes since previous version:
As the project was implemented, several design issues arose, and were dealt with by changing the design.
The classes being used to store moves underwent many changes throughout the project.
Initially, the plan was to store a set of instructions for making a move, which would be passed to the constructor for the appropriate class.
This proved difficult however, as all the required information was not present at the desired times.
Instead of this approach, moves are stored will all the information they need to apply the move, and have a method which does just that.

Previous versions of the project did not factor AI players into the design.
The only changes that the addition of AI players brought about was the introduction of a new interface, and several implementing classes.

Project Requirements:
Checking Validity       [DONE]
Command Parsing         [DONE]
User Interface          [DONE]
Inputing Moves          [DONE]
Saving Game State       [DONE]
Loading Game State      [DONE]
Undoing Moves           [DONE]
Redoing Moves           [DONE]
Implement minmax AI     [DONE]
Implement other AI      [DONE]


Limitations of Implementation:
There may be some cases where AI players get stuck or cause errors.
The main reason of  this is a possible problem with validity checking, causing the player to run out of possible moves, when there are still moves remaining.
The depth of the minmax algorithm covered by the AI player varies from one to two.
It was found that using all two took an unreasonable amount of time, and so a system of switching between one and two was devised.


Game Scoring Function:
The minmax algorithm used in the AI player uses a breadth first search to find it's shortest path to its goal edge the first  time it is run.
A list of squares is created to representing this path and is stored in a static field.
Every time the board is changed, the list is checked to see if the addition of a wall cut the path.
If the path is cut, another breadth first search is performed to determine the new shortest path.
If a pawn is moved, a new breadth first search is also performed.



