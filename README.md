Mower -SEAT CODE TEST

Dependencies
------------
* JDK 1.8
* Maven 3
* SLF4J + LogBack
* TestNG
* AssertJ
* Mockito

Compile & Run
-------------

    git clone https://github.com/garebon80/mower.git
    cd mower
    mvn clean install
    java -jar target/mower-0.0.1-SNAPSHOT.jar src/main/resources/mower.txt

Latest Sonar Statistics
-----------------------

* 80,1 unit tests coverage
* 100% tests success (55 unit tests)

How to use the Mower
-------------------

You simply have to describe the simulation, as a file:
* the first line must be the coordinates of the top right position (two numbers separated by a space),
* the second line must be the coordinates of the mower and the orientation (two **digits** and [NESW] separated by a space)
* the third line must be a list of command (m to move forward, L to rotate left, and R to rotate right).

The second and third lines can be repeated, if you want to simulate several mowers **sequentially**.

Here is an example of a configuration file:

5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM

The program finally prints the final position of each mowers.

Be careful of these three rules:
* if the initial position of a mower is outside the grid, the mower will not be taken into account in the simulation,
* if the position after movements is out of the field, mower does not move, retains its orientation, and processes the next command,
* if the position after movements is on an other mower, mower does not move, retains its orientation, and processes the next command.
