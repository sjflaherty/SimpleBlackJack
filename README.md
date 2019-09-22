# Simple Blackjack
### Contrinuters: Sarah Flaherty / Patrick Sacchet

## Time Estimations 
Sarah:
Estimated Lines of Code:
Estimated Hours of Work:

Patrick:
Estimated Lines of Code:
Estimated Hours of Work:
 
## Design 
### Classes:
#### Card 
##### Attributes:
- Suite [Hearts / Spades / Clubs / Diamonds]
- Value [1 - 10]
- In Play [True / False]

#### Game
##### Attributes:
- Status [Completed / Ongoing]
- Player Count [2 - 4]
- Card Deck [52]
- Pot [0-$1000000]
- Minimum bet [$25]
- Max play vlaue [21]

#### Player 
##### Attributes:
- Won [True / False]
- Card count [2 - 5]
- Money [0 - $100000]
- Play value [0-50]

## Functionality:
1. Game initializes (game is ongoing, player count is set to two, card deck is initialized)
2. Each player is initialized and is prompted to place bet 
2. Each player gets two cards, including dealer, player one is prompted to go
3. Player one has choice to hit or stand, if hit:
    - Player gets another card, play value increases, deck count decreases, player card count increases 
    - We check to see if player went over 21, if so they lose, otherwise go back to step 3
If stand:
    - We move to next player or dealer if player one went 
4. Player two is prompted the same as step 3
5. Dealer is prompted the same as step 3
6 We check all play values, if one is equal to 21 they win (meaning game is over, player gets pot which is set to 0, and cards are reset)
7. Ask user if they would like to continue (if money != 0), if so restart the game with same money values, otherwise end the game 
