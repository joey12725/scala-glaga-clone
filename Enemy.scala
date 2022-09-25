package cs2.game
import scalafx.scene.image.Image
import cs2.util.Vec2
import scala.util.Random
class Enemy(pic:Image, var initPos:Vec2, private val bulletPic:Image, xDim: Int = 40, yDim: Int = 75) extends Sprite(pic, initPos, xDim, yDim) {
  val r = scala.util.Random
  var direction = r.nextInt(4)
  var isDead = false
  var count = 0
  var vel = 1
  def kill():Unit = {println("enemy killed"); isDead = true}
  def shoot():Bullet = { new Bullet(bulletPic, new Vec2(initPos.x, initPos.y), new Vec2(0, .4)) }
  def move(): Unit = {
    if(checkBounds(new Vec2(1080,720))){
      vel *= -1
  }
    initPos.x += vel
  }

}
