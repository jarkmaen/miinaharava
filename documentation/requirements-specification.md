# Requirements specification

This application is a classic Minesweeper game. The goal of the game is to reveal all safe tiles on the board without triggering any mines. If a mine is revealed, the game ends in a loss. The game features three difficulties and tracks the user's personal best times for each of them.

## Basic version functionalities

### Main menu

- When the application opens, the main menu is displayed.
    - The user can start a new game by choosing a difficulty:
        - BEGINNER (9x9 board, 10 mines).
        - INTERMEDIATE (16x16 board, 40 mines).
        - EXPERT (30x16 board, 99 mines).
    - The user can view their personal bests (game results) for each difficulty.
        - If no completions exist for a specific difficulty, no times are shown.

### During the game

- The game board view contains:
    - A button that returns the user to the main menu when pressed.
    - A mine counter that shows how many mines remain to be flagged.
    - A timer that displays the elapsed time since the game started.
    - The board itself, where the game is played.
- The user can interact with the board as follows:
    - Left clicking a tile reveals its content (either a mine, a number or a blank).
        - If the revealed tile has zero adjacent mines, all adjacent tiles are automatically revealed recursively.
    - Right clicking a tile places a flag to mark it as a suspected mine location. Flags help the user keep track of where they believe mines are located.
    - If a tile contains a number, it indicates how many mines are adjacent to that tile.
    - If the user clicks a tile containing a mine, the game ends immediately.
    - When all safe tiles are revealed, the timer stops and the user is prompted to enter their name and save their completion time.

## Future development ideas

- Add the ability to set a custom board size and number of mines.
- Enable saving personal bests to a web page with a high score table.