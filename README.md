# N-Puzzle

[![](https://jitpack.io/v/shakram02/Npuzzle.svg)](https://jitpack.io/#shakram02/Npuzzle)


This library has algorithms for solving the 8-Puzzle problem. It's a defacto problem in AI.

The input of the game is a state of the 8-Puzzle


![Algorithm](images/game.png)

The library implements
- DFS
- BFS
- A* with heuristics
    - Manhattan Distance
    - Euclidean Distance
    
The code exists in `solvers.kt` and it's almost a one to one mapping of the Pseudocode

### A*

![A*](images/im3.png)

### DFS & BFS

![DFS](images/im2.png)

## Building

To build the project run
```bash
gradle jar
``` 

## Adding as a dependency

**Step 1.** Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

**Step 2.** Add the dependency
```
dependencies {
    compile 'com.github.shakram02:Npuzzle:v1.0.0'
}
```


## The code uses Data Structures from the following repos

[Queue](https://github.com/gazolla/Kotlin-Algorithm/tree/master/Queue), 
[Stack](https://github.com/gazolla/Kotlin-Algorithm/blob/master/Stack/Stack.kt)
