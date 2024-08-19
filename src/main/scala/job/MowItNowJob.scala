package job

import schemas.Model.{Command, Coordinates, Direction, Lawn, LawnMower}

object MowItNowJob {

  /**
   * given a lawnMower it generates a new lawnMower with the updated position
   *
   * @param lawnMower
   * @param lawn
   * @param command
   * @return LawnMower with the new coordinates and direction
   */
  def moveLawnMower(lawn: Lawn, lawnMower: LawnMower, command: Command): LawnMower = {
    command match {
      case Command.G | Command.D =>
        //handle rotation command
        val newHead = command match {
          case Command.D => rotateLawnMower(lawnMower.headTo)._2
          case Command.G => rotateLawnMower(lawnMower.headTo)._1
        }
        lawnMower.copy(headTo = newHead)

      case Command.A =>
        //handle advance command
        val newCoordinates = calculateNewPosition(lawnMower.coordinates, lawnMower.headTo)
        if (isWithinBounds(newCoordinates, lawn)) lawnMower.copy(coordinates = newCoordinates) else lawnMower

      case _ => lawnMower
    }
  }

  /**
   * calculate the new coordinate based on the actual coordinate and direction
   *
   * @param coordinates
   * @param direction
   * @return new coordinates
   */
   def calculateNewPosition(coordinates: Coordinates, direction: Direction): Coordinates = {
     val (x, y) = direction match {
       case Direction.N => (0, 1)
       case Direction.E => (1, 0)
       case Direction.S => (0, -1)
       case Direction.W => (-1, 0)
     }
     Coordinates(coordinates.x + x, coordinates.y + y)
  }

  /**
   * define the directions that we get if we rotate the lawnMower by 90 degrees in the left and right direction
   *
   * @param direction
   * @return tuple of Directions
   */
   def rotateLawnMower(direction: Direction): (Direction, Direction) = {
    direction match {
      case Direction.N => (Direction.W, Direction.E)
      case Direction.E => (Direction.N, Direction.S)
      case Direction.S => (Direction.E, Direction.W)
      case Direction.W => (Direction.S, Direction.N)
    }
  }

  /**
   * Helper function to check if coordinates are within the lawn bounds
   *
   * @param coordinates
   * @param lawn
   * @return True if the coordinates are within else False
   */
   def isWithinBounds(coordinates: Coordinates, lawn: Lawn): Boolean = {
      coordinates.x >= lawn.lowerLeft.x &&
      coordinates.y >= lawn.lowerLeft.y &&
      coordinates.x <= lawn.upperRight.x &&
      coordinates.y <= lawn.upperRight.y
  }

}
