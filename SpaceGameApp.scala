package cs2.game

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import cs2.util.Vec2
import scalafx.animation.AnimationTimer
import scalafx.scene.paint.Color
import scalafx.scene.image.Image
import scalafx.scene.text.Font
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
//import scala.collection.mutable.ListBuffer
import scala.collection.mutable._

//how display text?

object SpaceGameApp extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Galaxy Game!"
    scene = new Scene(1080,720) {
      val endScreen = new Screen(new Image(getClass.getResource("/images/endScreen.jpg").toString))
      val splashScreen = new Screen(new Image(getClass.getResource("/images/splashScreen.jpg").toString))
      val grass = new Background(new Image(getClass.getResource("/images/grass.jpg").toString), 50, 1080, 720)
      val house = new Background(new Image(getClass.getResource("/images/house.png").toString), 40, 250, 200)
      val bird = new Background(new Image(getClass.getResource("/images/bird.png").toString), 25, 45, 45)
      val canvas = new Canvas(width.value, height.value)
      content = canvas
      val g = canvas.graphicsContext2D
      val playerPath = getClass.getResource("/images/dragon.png")
      val playerSprite = new Image(playerPath.toString)
      val bulletPath = getClass.getResource("/images/Smoke.png")
      val bulletSprite = new Image(bulletPath.toString)
      val enemyPath = getClass.getResource("/images/enemy.png")
      val enemySprite = new Image(enemyPath.toString)
      val defaultFont:Font = g.getFont()
      var keysPressed: Set[String] = Set()
      val canvasDimensions = new Vec2(width.value, height.value)
      var showSplashScreen = true
      var showEndScreen = false
      var player = new Player(playerSprite, new Vec2(width.value/2, 620), bulletSprite, 100, 100, 3)
      var boss = new Boss(playerSprite, new Vec2(5000, 5000), bulletSprite, -150, -150)
      var bossMoveQueue = new Queue[Set[String]]()
      var swarm = new EnemySwarm(3,3)
      var bulletList = ListBuffer[Bullet]()
      var score = 0
      //var rewind = 0
      var livesLocation = new Vec2(10,660)
      var livesDisplay = new UiElement(new Image(getClass.getResource("/images/3heart.png").toString),livesLocation)
    //  var history = new Stack[GameState]
      for(i <- 0 until 100){ bossMoveQueue.enqueue(Set("VALUE"))
      }
      var temp = bossMoveQueue.dequeue
      def initialize():Unit = {
        livesDisplay = new UiElement(new Image(getClass.getResource("/images/3heart.png").toString),livesLocation)
        player = new Player(playerSprite, new Vec2((width.value/2), 620) , bulletSprite, 100, 100)
        swarm = new EnemySwarm(3,3)
        showSplashScreen = true
        score = 0
        showEndScreen = false
        bulletList = ListBuffer[Bullet]()
    }
      //def loadState():Unit = {
      //  println(history.size)
      //  if(!history.isEmpty && rewind > 0) {
      //    rewind -= 1
      //  var load = history.pop()
      //  player = new Player(playerSprite, load.playerPos, bulletSprite, 100, 100, load.playerLives)
      //  swarm.reconstruct(load.swarmLocations)
      //  score = load.score
      //  bulletList = ListBuffer[Bullet]()
    //    for (i <- 0 until load.bList.length){
      //    bulletList += new Bullet(bulletSprite, load.bList(i)._1, load.bList(i)._2) }
    //    println(history.size)
    //  def writeState():Unit = {
      //  println("state recorded")
    //    if(rewind < 180)
    //    rewind += 1
    //    history.push(new GameState(player, swarm, bulletList, score))
    //  }
      canvas.onKeyPressed = (e: KeyEvent) => {
      if (e.code == KeyCode.Left || e.code == KeyCode.A){
        keysPressed += "Left"
      }
      else if (e.code == KeyCode.Right || e.code == KeyCode.D){
        keysPressed += "Right"
      }
      else if (e.code == KeyCode.Up || e.code == KeyCode.W){
        keysPressed += "Up"
      }
      else if (e.code == KeyCode.Down || e.code == KeyCode.S){
        keysPressed += "Down"
      }
      else if (e.code == KeyCode.R){
        keysPressed += "R"
      }
      else if (e.code == KeyCode.Space){
        keysPressed += "Space"
        showSplashScreen = false
      }

      }
      canvas.onKeyReleased = (e: KeyEvent) => {
        if (e.code == KeyCode.Left || e.code == KeyCode.A){
        keysPressed -= "Left"
      }
      else if (e.code == KeyCode.Right || e.code == KeyCode.D){
        keysPressed -= "Right"
      }
      else if (e.code == KeyCode.Up || e.code == KeyCode.W){
        keysPressed -= "Up"
      }
      else if (e.code == KeyCode.Down || e.code == KeyCode.S){
        keysPressed -= "Down"
      }
      else if (e.code == KeyCode.Space){
        keysPressed -= "Space"
      }
      else if (e.code == KeyCode.R){
        keysPressed -= "R"
      }

      }
      canvas.requestFocus()
      var frames = 0
      var playerCooldown = 0
      val timer = AnimationTimer(t =>{
      if(showSplashScreen == true){
        splashScreen.display(g)
      }
      else if(player.lives < 1){
        endScreen.display(g)
        //println("Game Over!")
        if(keysPressed("Space")) {
          initialize()
        }
        if(keysPressed("Down")){
          System.exit(0);
        }
      }
      else {
      frames += 1
      playerCooldown += 1
      g.setFill(Color.White)
      g.fillRect(0, 0, width.value, height.value)
      grass.display(g)
      house.display(g)
      bird.display(g)
      grass.timeStep()
      bird.timeStep()
      house.timeStep()
      player.display(g)
      swarm.display(g)
      g.setFill(Color.Chocolate)
      g.setFont(defaultFont)
      if(boss.lives > 0) boss.display(g)

      g.fillText("Score: " + score.toString, 10, 650)
      //g.fillText("Rewind: " + rewind.toString, 10, 620)
      bulletList.foreach(_.display(g))
      livesDisplay.display(g)
    //  if (keysPressed("R")){
    //    loadState()
    //  }
  //    else {
      //tempEnemy.display(g)
    //  writeState()
      println("remaining: " + swarm.remaining.toString)
      println("Boss has spawned: " + boss.hasSpawned.toString)
      if(swarm.remaining == 5 && boss.hasSpawned == false) {
        println("BOSS SPAWNING")
        boss.hasSpawned = true
        boss.moveTo(new Vec2(player.position.x, player.position.y-150))
      }
      if (keysPressed.contains("Up")) {
        player.moveUp()
      }
      if (keysPressed.contains("Down")) {
        player.moveDown()
      }
      if (keysPressed.contains("Left")) {
        player.moveLeft()
      }
      if (keysPressed.contains("Right")) {
        player.moveRight()
      }
      if (keysPressed.contains("Space")) {
        if(playerCooldown > 50) {
          bulletList += player.shoot()
          playerCooldown = 0
        } }
      keysPressed += "VALUE"
      bossMoveQueue.enqueue(keysPressed)
      if(frames % 2 == 0){
        temp = bossMoveQueue.dequeue
    }
      if (temp.contains("Up")) {
        boss.moveUp()
      }
      if (temp.contains("Down")) {
        boss.moveDown()
      }
      if (temp.contains("Left")) {
        boss.moveLeft()
      }
      if (temp.contains("Right")) {
        boss.moveRight()
      }
      if (temp.contains("Space")) {
        println("SHOOTING!!!!!!!!!!!!!!!!!!!!!!!!!!")
        if(playerCooldown > 30) {
          bulletList += boss.shoot()
        } }

      if(player.lives < 1){ showEndScreen == true }
      if(frames % 1000 == 0) { bulletList += swarm.shoot }
      if(swarm.checkIntersection(player)){
        player.returnToSpawn()
        if(player.lives == 2){ livesDisplay = new UiElement(new Image(getClass.getResource("/images/2heart.png").toString),livesLocation)}
        if(player.lives == 1){ livesDisplay = new UiElement(new Image(getClass.getResource("/images/1heart.png").toString),livesLocation)}
        println(player.lives)}
      if(player.checkBounds(canvasDimensions)){ player.returnToSpawn() }
      swarm.move()
      if(swarm.remaining == 2 && !boss.checkBounds(canvasDimensions)) { boss.moveTo(new Vec2(player.position.x, player.position.y-200)) }
      var removeQueue = ListBuffer[Bullet]()
      for (i <- 0 until bulletList.length){
        bulletList(i).timeStep()
        if(swarm.checkIntersection(bulletList(i))){ removeQueue += bulletList(i); score +=1 }
        if(boss.checkIntersection(bulletList(i))){ removeQueue += bulletList(i); boss.lives-=1; if(boss.lives == 0){score += 10} }
        if(player.checkIntersection(bulletList(i))) {
          player.returnToSpawn()
          removeQueue += bulletList(i)
          player.lives -= 1}
        if(boss.checkIntersection(bulletList(i))) {
          boss.lives -= 1
          removeQueue += bulletList(i) }
        if(bulletList(i).checkBounds(canvasDimensions)){
          println("Bullet Removed")
          removeQueue += bulletList(i)
        }
        for(j <- 0 until bulletList.length){
          if(i != j){
            if(bulletList(i).checkIntersection(bulletList(j))){
              removeQueue += bulletList(i)
              removeQueue += bulletList(j)
            }
          }
        }
    }
    if(swarm.isEmpty){initialize()}
    bulletList --= removeQueue
  } } )
    timer.start()
    }
  }
}
