# Yogi Bear Game

A classic arcade-style game where Yogi Bear collects picnic baskets while avoiding rangers in Yellowstone National Park.

## Description

Navigate Yogi Bear through multiple levels of Yellowstone National Park, collecting picnic baskets while avoiding patrolling rangers. Watch out for obstacles like mountains and trees that will block your path!

### Features

- Multiple challenging levels
- WASD movement controls
- Obstacle avoidance (mountains and trees)
- Patrolling rangers with horizontal and vertical movement patterns
- Score tracking system
- High score database with top 10 leaderboard
- Three lives system with respawn mechanics
- Level progression system

## Getting Started

### Prerequisites

- Java Runtime Environment (JRE)
- PostgreSQL database

### Installation

1. Clone the repository
2. Set up the PostgreSQL database using the provided schema
3. Configure the database connection in the `DatabaseManager` class
4. Run the game using `java -jar yogibear.jar`

## How to Play

- Use WASD keys to move Yogi Bear
- Collect all picnic baskets to complete each level
- Avoid rangers - getting within one unit of distance costs a life
- Press ESC to pause the game
- Game ends when all lives are lost
- Enter your name to save your high score

## Game Components

- **Player Character**: Yogi Bear with WASD movement
- **Rangers**: Patrol either horizontally or vertically
- **Picnic Baskets**: Collectibles that increase score
- **Obstacles**: Mountains and trees that restrict movement
- **Lives System**: Three lives with park entrance respawn
- **Database Integration**: PostgreSQL for high score storage

## Technical Implementation

- Built using Java Swing for GUI
- Object-oriented architecture
- Level loading system using text files
- PostgreSQL database integration for score management
- Collision detection system
- Event handling for player input


## Game Preview

The game includes:
- Main game interface with score display
- High score leaderboard
- Game over screen
- Dynamic ranger movement
- Obstacle interaction

## Development Notes

The game follows a modular architecture with these key components:
- `GameObject`: Base class for all game entities
- `GameEngine`: Core game logic and rendering
- `Level`: Level management and loading
- `DatabaseManager`: High score handling
- `YogiBearGUI`: Main game window and controls

## Author

Joseph Paul
