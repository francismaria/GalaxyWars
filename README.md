# GalaxyWars

## Architecture Design

Below is a representation of the diagram representing our program structure.

![alt text](imgs/first-uml.JPG)

### Design Patterns

---

## GUI Design

* **Main functionalities**
  
  * Highest scores record
  * Game difficulty choice
  * Volume mixer

* **GUI mock-ups**

The next images are just a sketch of what the game interface should be as we haven't defined a gui that we find appropriate. 

![alt text](imgs/StandardGame.png)

![alt text](imgs/Cave.png)

![alt text](imgs/Boss.png)

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
