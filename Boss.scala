package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2

/** The player representation for a simple game based on sprites. Handles all
 *  information regarding the player's positions, movements, and abilities.
 *
 *  param avatar the image representing the player
 *  param initPos the initial position of the '''center''' of the player
 *  param bulletPic the image of the bullets fired by this player
 */
class Boss(avatar:Image, initPos:Vec2, private val bulletPic:Image, xDim:Int = 200, yDim:Int = 200) extends Sprite(avatar, initPos, xDim, yDim) {
  var hasSpawned = false
  var lives = 2
  def shoot():Bullet = { new Bullet(bulletPic, this.position, new Vec2(0, 1.4)) }
  //val spawnPoint = new Vec2(initPos.x, initPos.y)
  def moveLeft():Unit = { if(!checkBounds(new Vec2(1080,720))){ pos.x -= .3 } }
  def moveRight():Unit = { if(!checkBounds(new Vec2(1080,720))){ pos.x += .3 } }
  def moveUp():Unit = { if(!checkBounds(new Vec2(1080,720))){  pos.y -= .3 } }
  def moveDown():Unit = { if(!checkBounds(new Vec2(1080,720))){ pos.y += .3 } }
  //def copy():Player = {new Player(avatar, initPos, bulletPic, xDim, yDim)}
}
