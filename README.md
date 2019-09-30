# Simple Blackjack
### Contributers: Sarah Flaherty / Patrick Sacchet

## Demo:

https://youtu.be/cjJswxBcbS8

## Time Estimations 

Estimated Lines of Code: 500 Java, 300 xml

Estimated Hours of Work: 20

 
## Design 
### Classes:
#### Card 
##### Attributes:
- Suite [Hearts / Spades / Clubs / Diamonds]
- Value [1 - 11]
- In Play [True / False]

#### Game
##### Attributes:
- Status [Completed / Ongoing]
- Card Deck [52]
- Max play value [21]
- Deck [ArrayList of 52 cards]
- CurrentPlayer [Player object]
- PlayerList [Arraylist of all players]
##### Methods:
- CheckLose - check all player scores, if over 21 they are out 
- CheckWin - called at end of all turns, closest to 21 is the winner 
- GenerateDeck - generates deck of 52 cards 

#### Player 
##### Attributes:
- Won [True / False]
- Card count [2 - 5]
- Score [0-50]
- Hand [Array list of cards in player hand]
- Name [String]
- Playing [boolen]
##### Methods: 
- DrawCard - deck decreases by one, player card count increases by one, player score increases by card value, returns nothing 
- Stand - switches games current player to next player 
- CalculateScore - returns the card score of the player 

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
