package schemas

object Model {

  /**
   * N represent the direction North
   * E represent the direction East
   * W represent the direction West
   * S represent the direction South
   */
  sealed trait Direction
  object Direction {
    case object N extends Direction
    case object E  extends Direction
    case object W  extends Direction
    case object S extends Direction
  }

  /**
   * The command D represent the action to turn 90 degrees to the right direction
   * The command G represent the action to turn 90 degrees to the left direction
   *  The command D represent the action to move ahead by one step
   */
  sealed trait Command
  object Command {
    case object D extends Command
    case object G extends Command
    case object A extends Command
  }

  /**
   * Schema of Coordinates (Coordonnées)
   * @param x   Represent the coordinate X
   * @param y   Represent the coordinate Y
   */
  case class Coordinates (x: Int, y: Int)

  /**
   * Schema of Lawn (Pelouse)
   * @param lowerLeft
   * @param upperRight
   */
  case class Lawn(lowerLeft: Coordinates, upperRight: Coordinates)

  /**
   * Schema of LawnMower (Tondeuse)
   * @param coordinates
   * @param headTo
   */
  case class LawnMower(coordinates: Coordinates, headTo: Direction)

}
