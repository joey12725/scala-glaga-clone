package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2

class UiElement(pic:Image, initPos:Vec2 = new Vec2(0,0), xDim: Int = 100, yDim: Int = 50) extends Sprite(pic, initPos, xDim, yDim) { }
