package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2

class Screen(pic:Image, initPos:Vec2 = new Vec2(0,0), xDim: Int = 1080, yDim: Int = 720) extends Sprite(pic, initPos, xDim, yDim) { }
