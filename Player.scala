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
class Player(avatar:Image, initPos:Vec2, private val bulletPic:Image, xDim:Int = 200, yDim:Int = 200, var lives:Int = 3) extends Sprite(avatar, initPos, xDim, yDim) {
  val spawnPoint = new Vec2(initPos.x, initPos.y)
  def moveLeft():Unit = { pos.x -= 1 }
  def moveRight():Unit = { pos.x += 1 }
  def moveUp():Unit = { pos.y -= 1 }
  def moveDown():Unit = { pos.y += 1 }
  def returnToSpawn():Unit = {
    moveTo(new Vec2(spawnPoint.x, spawnPoint.y))
    lives -= 1
  }
  def shoot():Bullet = {
    new Bullet(bulletPic, new Vec2(pos.x, pos.y - 80 ), new Vec2(0, -4)) }
}
