 # TicTacToe – Minimax vs Alpha-Beta
 
## Run the Application

Start the Spring Boot application and open:

http://localhost:8080

to play the game using the graphical interface.

## Project Description

This project implements a TicTacToe AI using:

- Minimax algorithm
- Alpha-Beta pruning
- Depth-limited search
- Static evaluation function
- Node counting for performance comparison

The purpose is to compare Minimax and Alpha-Beta in terms of search efficiency and computational cost.

---

## Algorithms Implemented

### Minimax

Minimax explores the game tree recursively up to a given depth.

At each level:
- The maximizing player tries to maximize the score.
- The minimizing player tries to minimize the score.

Minimax evaluates every possible move sequence up to the chosen depth.

---

### Alpha-Beta Pruning

Alpha-Beta improves Minimax by pruning branches that cannot influence the final decision.

It maintains:

- **alpha** = best value found so far for the maximizing player
- **beta** = best value found so far for the minimizing player

Pruning condition:

beta <= alpha

When this condition is met, the remaining branches in that subtree are skipped.

Alpha-Beta always produces the same move as Minimax but explores fewer nodes.

---

## Depth-Limited Search

The depth parameter determines how many plies (half-moves) the algorithm searches ahead.

For TicTacToe:

- Maximum depth = 9
- Depth 1 → only evaluates immediate moves
- Depth 9 → searches the full game tree (perfect play)

---

## Static Evaluation Function

Non-terminal board positions are evaluated using the following positional matrix:

3 2 3  
2 4 2  
3 2 3  

### Position Values

- Center = 4  
- Corners = 3  
- Edges = 2  

### Why These Values?

The center participates in 4 possible winning lines.  
Each corner participates in 3 winning lines.  
Each edge participates in 2 winning lines.  

Therefore, the center is the most strategically valuable position.

---

## Terminal State Values

If the game reaches a terminal state:

- Computer win → +1000  
- Computer loss → -1000  
- Draw → 0  

The large terminal values ensure that winning and losing positions are prioritized over positional advantages.

---

## Node Counting

Each recursive call is counted as one visited node.

This allows performance comparison between algorithms.

Alpha-Beta should always visit fewer or equal nodes compared to Minimax.

---

## Conclusion

- Both algorithms produce identical moves.
- Alpha-Beta significantly reduces the number of explored nodes.
- Search complexity grows exponentially with depth.
- At depth 9, the AI plays perfectly.
