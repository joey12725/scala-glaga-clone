package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scala.math._

abstract class Sprite (protected val img:Image, protected var pos:Vec2, var xDim:Int = 50, var yDim:Int = 50) {

  def move (direction:Vec2):Unit = { pos += direction }

  def moveTo (location:Vec2):Unit = { pos = location }

  def display (g:GraphicsContext):Unit = { g.drawImage(img, pos.x, pos.y, xDim, yDim) }

  def dimensions ():Vec2 = { new Vec2(this.xDim, this.yDim) }

  def position ():Vec2 = { new Vec2(this.pos.x, this.pos.y) }

  def checkIntersection[A <% Sprite](intersected:A):Boolean = {
    var isIntersecting = false
    var dim2 = intersected.dimensions
    var pos2 = intersected.position
    if (abs(this.pos.x-pos2.x) < (xDim + dim2.x)/2 && abs(this.pos.y-pos2.y) < (this.yDim + dim2.y)/2) {
      isIntersecting = true
    }
    isIntersecting
}

  def checkBounds(canvasDimensions:Vec2):Boolean = {
    var inBounds = false
    if(pos.x < 0 || pos.x > canvasDimensions.x || pos.y < 0 || pos.y > canvasDimensions.y){
      inBounds = true
    }
    inBounds
  }

}
