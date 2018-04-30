# GalaxyWars

## Architecture Design

Below is a representation of the diagram representing our program structure.

//AQUI

### Design Patterns

---

## GUI Design

* Main functionalities
The game will keep the record of the highest records (points). When starting a new game the player has to choose the difficulty of the game (easy/medium/hard) in which will influence the speed and the number of enemies in the game. It will have a volume controller in which the player may adjust the volume to its choice.

* GUI mock-ups

---

## Test Designs

* **Button testing**
  * Galaxy "jumps" when space bar is pressed
  * Galaxy "fires" bullet when right button is clicked
  
* **Enemy testing**
  * "Boss" enemy dies when its health is "0"
  * Galaxy wins game when "BOSS" enemy is defeated
  
* **Collision testing**
  * Galaxy "dies" when it colides with the floor
  * Galaxy loses health when bullet hits it
  * Galaxy "dies" when enemy collides with it
  * Galaxy "dies" when plasma ball collides with it
