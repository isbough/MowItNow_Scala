import job.MowItNowJob
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import schemas.Model.{Command, Coordinates, Direction, Lawn, LawnMower}

class MowItNowTest extends AnyFlatSpec with should.Matchers {

  val lawn = Lawn(Coordinates(0, 0), Coordinates(5, 5))

  "Lawn Mower final position" should "be 1 3 N" in {

    val lawnMower = LawnMower(Coordinates(1, 2), Direction.N)
    val commands = Array(Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.A)
    val lawnMowerFinalPosition = commands.foldLeft(lawnMower)
    {
      (temp, command) => MowItNowJob.moveLawnMower(lawn, temp, command)
    }

    lawnMowerFinalPosition.coordinates.x should be (1)
    lawnMowerFinalPosition.coordinates.y should be (3)
    lawnMowerFinalPosition.headTo should be (Direction.N)
  }

  "Lawn Mower final position" should "be 5 1 E" in {

    val lawnMower = LawnMower(Coordinates(3, 3), Direction.E)
    val commands = Array(Command.A, Command.A, Command.D, Command.A, Command.A, Command.D, Command.A, Command.D, Command.D, Command.A)
    val lawnMowerFinalPosition = commands.foldLeft(lawnMower)
    {
      (temp, command) => MowItNowJob.moveLawnMower(lawn, temp, command)
    }

    lawnMowerFinalPosition.coordinates.x should be (5)
    lawnMowerFinalPosition.coordinates.y should be (1)
    lawnMowerFinalPosition.headTo should be (Direction.E)
  }
}

