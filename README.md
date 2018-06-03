# GalaxyWars

## Introduction

**Galaxy Wars** is a game that consists in a space ship that continuously tries to reach further distance until infinity. The main objective of the game is to last for the longest time possible. 
The game consists in three different difficulties (easy, medium, hard) differing of the quantity of enemies being thrown at.

## User Manual

* **Main Menu**
Once the game is launched, the user can select:
	* **New Game**  
	Initializes the screen with two options. Play game or quit game. If play game option is chosen it redirects to difficulty choice menu.
![alt text](imgs/menu.JPG)

	* **Difficulty Choice Menu**  
		Gives the user three options of difficulty:
		* Easy
		* Medium
		* Hard
![alt text](imgs/difficulty.JPG)

	* Action  
	After the user choice of the game difficulty the game will start being the user the spaceship in the left and the enemies are all of the bodies coming from the right.
	
![alt text](imgs/example1.JPG)
	
* **Controls**
	* **"W"**  
	Gives the player's spaceship an upwards impulse (must be pressed repeatedly)

	* **"S"**  
	Gives the player's spaceship a downwards impulse (must be pressed repeatedly)


* **Game cicle**
	- At the start of the game, the user must keep his spaceship from falling down, hitting the ground will cause the player's death.
	There are 3 different tipes of enemies:
	* ZigZag
	Bounces up and down unpredictably while moving left towards the player
	![alt text](https://github.com/francismaria/GalaxyWars/blob/master/android/assets/zigzag.png)

	* Shooter
	Fires projectiles while trying to aim at the player
	![alt text](https://github.com/francismaria/GalaxyWars/blob/master/android/assets/shooter.png)

	* Kamikaze
	Tries to lock the player's altitude and then quickly launches itself towards him.
	![alt text](https://github.com/francismaria/GalaxyWars/blob/master/android/assets/kamikaze.png)
	

	If any of the enemyes collide with the player, the game is over. 
	
	The player can pause the game at any point by pressing "esc", entering the pause menu:
	
* **Pause menu**



## Design Patterns

	* **Model View Controller**
		To make testing easier and the code more organized.

	* **Object Pool**
		This design pattern is used to save memory and improve the performance when firing the projectiles, as they exist in a limited amount and are contantly being created and destroyed

	* **Singleton**
		It's implemented in the GameModel and GameController classes


## Major Difficulties

## Lessons Learned

