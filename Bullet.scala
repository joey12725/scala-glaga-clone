package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2

class Bullet(pic:Image, initPos:Vec2, var vel:Vec2, xDim: Int = 25, yDim: Int = 25) extends Sprite(pic, initPos, xDim, yDim) {
  def timeStep():Unit = { pos += vel }
  override def clone():Bullet = new Bullet(pic, initPos, vel)
}
