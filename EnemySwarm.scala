package cs2.game
import scala.util.Random
import scalafx.scene.image.Image
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scala.collection.mutable.Buffer

class EnemySwarm(private val nRows:Int, private val nCols:Int) {
val pathEnemy = getClass.getResource("/images/enemy.png")
val enemySprite = new Image(pathEnemy.toString)
val pathBullet = getClass.getResource("/images/Smoke.png")
val bullet = new Image(pathBullet.toString)
var enemies: Buffer[Enemy] = Buffer()
for (r <- 0 until nRows; c <- 0 until nCols) {
  enemies += new Enemy(
    enemySprite,
    new Vec2(c * 100 + 50, r * 100 + 50),
    bullet
  )
}

def checkIntersection[A <% Sprite](intersected:A):Boolean = {
  var removeQueue: Buffer[Enemy] = Buffer()
  var wasIntersected = false
  for (enemy <- enemies) {
    if (enemy.checkIntersection(intersected)) {
      removeQueue += enemy
      wasIntersected = true
    }
  }
  enemies --= removeQueue
  wasIntersected
}
//swarmArray --= removeQueue

def remaining():Int = { enemies.length }

def move() { enemies.foreach(_.move()) }

def display(g: GraphicsContext) { enemies.foreach(_.display(g)) }

def shoot(): Bullet = { enemies((math.random * enemies.length).toInt).shoot }


def isEmpty():Boolean = { enemies.length == 0 }

}
