# Simple Blackjack
### Contributers: Sarah Flaherty / Patrick Sacchet

## Demo:

https://youtu.be/cjJswxBcbS8

## Time Estimations 

Estimated Lines of Code: 500 Java, 300 xml

Estimated Hours of Work: 20

Final Lines of Code: 800 Java, 300 xml

Final Hours of Work: ~17

## Design 
### Classes:
#### Card 
##### Attributes:
- Suites [Hearts / Spades / Clubs / Diamonds]
- Facecards [King / Queen / Jack / Ace]
- Values [Two / Three / Four / Five / Six / Seven / Eight / Nine / Ten]
- Face
- Filename
- Value [1 - 11]
- In Play [True / False]

##### Methods:
- updateValue - updates the value of the card
- updateSuite - updates the suite of the card
- updateFilename - updates the filename of the card
- checkFilename - returns the filename of the card
- checkValue - returns the value of the card
- checkFace - returns the face value of the card
- updateFace - updates the face value of the card

#### Game
##### Attributes:
- Status [Completed / Ongoing]
- Card Deck Size
- Player hits
- Max play value [21]
- Deck [ArrayList of 52 cards]
- CurrentPlayer [Player object]
- PlayerList [Arraylist of all players]
##### Methods:
- getPlayerHits - returns the number of player hits
- resetPlayerHits - sets player hits back to one
- incPlayerHits - increases player hits by one
- addPlayer - adds a player to the player list
- checkPlayerList - returns the arraylist of players
- start - starts game by giving each player in the game two cards
- randomCard - grabs a random card from the deck in the game
- checkCurrentPlayer - returns the current player
- changeToDealer - changes the current player to the dealer
- changeToUser - changes the current player to the user
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
- Playing [boolean]
##### Methods: 
- addCard - deck decreases by one, player card count increases by one, player score increases by card value, returns nothing
- CalculateScore - returns the card score of the player
- checkHand - returns the array list of cards belonging to the player
- checkPlayerName - returns the name of the player
- updatePlaying - changes the players status to currently playing

#### MainActivity
##### Attributes:
- game - current instance of the game running
##### Methods:
- onStart
- onCreate
- displayCards - displays cards for both users on startup
- displayCard - displays card after each hit
- displayScore - displays new updated score
- getIdString - returns the proper id format for each card image
- stand - switches from user to dealer after user hits stand
- dealerHand - processes dealer's activities
- disableButtons - disables hit and stand buttons
- addCard - adds card to a users hand, calls updates to display card and score
- isOver - starts new game instance
- lostAlert - alerts user to loss
- wonAlert - alerts user to win

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
