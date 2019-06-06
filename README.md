# Pawn's Tour

A pawn can move on 10x10 chequerboard horizontally, vertically and diagonally by these rules:
1) 3 tiles moving North (N), West (W), South (S) and East (E)
2) 2 tiles moving NE, SE, SW and NW
3) Moves are only allowed if the ending tile exists on the board
4) Starting from initial position, the pawn can visit each cell only once
On the following picture, you can

In fact, a given task is a variation of the Knight's Tour problem (https://en.wikipedia.org/wiki/Knight%27s_tour) and it is an instance of the more general Hamiltonian path problem in graph theory. The problem of finding a closed knight's tour is similarly an instance of the Hamiltonian cycle problem. https://en.wikipedia.org/wiki/Hamiltonian_path_problem

In the mathematical field of graph theory, the Hamiltonian path problem and the Hamiltonian cycle problem are problems of determining whether a Hamiltonian path (a path in an undirected or directed graph that visits each vertex exactly once) or a Hamiltonian cycle exists in a given graph (whether directed or undirected). Both problems are NP-complete.[1]

So, let's apply the following algorithm from Knight's Tour problem:

# Backtracking
Backtracking works in an incremental way to attack problems. Typically, we start from an empty solution vector and one by one add items (Meaning of item varies from problem to problem. In the context of current task problem, an item is a paw's move). When we add an item, we check if adding the current item violates the problem constraint, if it does then we remove the item and try other alternatives. If none of the alternatives work out then we go to the previous stage and remove the item added in the previous stage. If we reach the initial stage back then we say that no solution exists. If adding an item doesn’t violate constraints then we recursively add items one by one. If the solution vector becomes complete then we print the solution.

# solution:

```
The problem is solved using H C Warnsdorff's technique which does the following:

Start from any tile and mark it as visited.
To decide next tile in path, look at all possible unmarked tiles based on moving rules.
Rank each possibility by the number of next moves from that tile.
Move to any tile with the lowest rank.
Add chosen tile to knight's tour path (i.e marked) and repeat the process from last chosen tile.
```
   
# Warnsdorf's rule
Warnsdorf's rule is a heuristic for finding a knight's tour. The knight is moved so that it always proceeds to the square from which the knight will have the fewest onward moves. When calculating the number of onward moves for each candidate square, we do not count moves that revisit any square already visited. It is, of course, possible to have two or more choices for which the number of onward moves is equal; there are various methods for breaking such ties. This rule may also more generally be applied to any graph. In graph-theoretic terms, each move is made to the adjacent vertex with the least degree. Although the Hamiltonian path problem is NP-hard in general, on many graphs that occur in practice this heuristic is able to successfully locate a solution in linear time.


# Result
* Each step in a discovered (Pawn's tour) path are printed in the console.
* matrix representation is shown to analyse moves of the Pawn in the tour according to legal moving rules
* Time taken to complete the entire tour

# Sample Output
```
Welcome the board sizes are: 10 X 10

The initial position for the pawn is: [row:7, column:7]
Step: 1

  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  1  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  0

Step: 2

  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  1  0  0
  0  0  0  0  0  0  0  0  0  0
  0  0  0  0  0  0  0  0  0  2
  .
  .
  .
  Step: 99
  
   10 41 38 11 42 37 90 43 36 87
   70 27  8 69 80  7 68 79  6 67
   39 12 74 40 91 98 45 86 97 44
    9 54 81 26 65 82 89 66 35 88
   71 28 61 95 75 62 96 78  5 85
   52 13 73 53 92 99 46 83  0 47
   22 55 76 25 64 77 18 63 34 17
   72 29 60 94 30 59 93  1  4 84
   51 14 21 50 15 20 49 16 19 48
   23 56 31 24 57 32  3 58 33  2
 
  Step: 100
  
   10 41 38 11 42 37 90 43 36 87
   70 27  8 69 80  7 68 79  6 67
   39 12 74 40 91 98 45 86 97 44
    9 54 81 26 65 82 89 66 35 88
   71 28 61 95 75 62 96 78  5 85
   52 13 73 53 92 99 46 83100 47
   22 55 76 25 64 77 18 63 34 17
   72 29 60 94 30 59 93  1  4 84
   51 14 21 50 15 20 49 16 19 48
   23 56 31 24 57 32  3 58 33  2
  
  Found path for pawn tour
  
  Time taken to complete the entire valid tour : [69ms]
``` 
# Pre requisites 
- Mac(10.14)
- Maven - 3.3.9
- Spring Boot - 2.1.2.RELEASE
- Java - 11.0.2 2019-01-15 LTS(OpenJDK build by Oracle)

# Build & Run
> Application can be build and started two ways.
> Make sure to cd to project folder.

- Maven build & Run Default

```
mvn clean package && java -jar target/pawntour-boot.jar

```

- Maven build & Run with commandline arg

```
mvn clean package && java -jar target/pawntour-boot.jar {boardsize}

```
- Docker build & Run Default
```
docker build -t pawntour-boot .

docker run -ti pawntour-boot
```

- Docker build & Run with commandline arg
```
docker build -t pawntour-boot .

docker run -ti pawntour-boot {boardsize}
```
# Running instruction
> Default board size would be 10x10
> If the user need to change the boarad size can be given via command line
> Example {boardsize} can be used as 8
