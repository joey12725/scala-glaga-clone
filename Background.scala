package cs2.game

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

class Background(img: Image, z: Int, xDim: Int = 1080, yDim: Int = 720) extends Sprite(img, new Vec2(0, 50/z)){
  var primaryImagePosition:Vec2 = new Vec2(0,0)
  var secondaryImagePosition: Vec2 = new Vec2(0, -720)
  var vel = (100-z) * 0.05
  var velocity = new Vec2(0, vel)
  override def display(g: GraphicsContext){
    println(vel)
      g.drawImage(img, primaryImagePosition.x, primaryImagePosition.y, xDim, yDim)
      g.drawImage(img, secondaryImagePosition.x, secondaryImagePosition.y, xDim, yDim)
      if (secondaryImagePosition.y >= 0){
        primaryImagePosition = new Vec2(0,0)
      }
      if (primaryImagePosition.y == 0){
        secondaryImagePosition = new Vec2(0, -720)
      }
  }
  def timeStep(): Unit = {
    primaryImagePosition += velocity
    secondaryImagePosition += velocity
  }
}
