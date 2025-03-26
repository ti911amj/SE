import scala.util.Random
import scala.io.StdIn

object Minesweeper {
  val size = 9
  val mineCount = 10
  val board: Array[Array[Char]] = Array.fill(size, size)('_')
  val mineField: Array[Array[Boolean]] = Array.fill(size, size)(false)
  val flagField: Array[Array[Boolean]] = Array.fill(size, size)(false)

  def main(args: Array[String]): Unit = {
    placeMines()
    playGame()
  }

  def placeMines(): Unit = {
    var placedMines = 0
    while (placedMines < mineCount) {
      val row = Random.nextInt(size)
      val col = Random.nextInt(size)
      if (!mineField(row)(col)) {
        mineField(row)(col) = true
        placedMines += 1
      }
    }
  }

  def playGame(): Unit = {
    var gameOver = false
    while (!gameOver) {
      printBoard()
      print("Enter your move (F to flag, e.g., 'F C3' or 'C3'): ")
      val input = StdIn.readLine().trim.toUpperCase
      val isFlag = input.startsWith("F ")
      val move = if (isFlag) input.substring(2) else input
      if (move.matches("[A-I][1-9]")) {
        val row = move.charAt(0) - 'A'
        val col = move.charAt(1) - '1'
        if (isFlag) {
          flagField(row)(col) = !flagField(row)(col)
        } else {
          if (mineField(row)(col)) {
            println("BOOOM! You lose.")
            gameOver = true
          } else {
            board(row)(col) = getMineCount(row, col)
            if (checkWin()) {
              println("You win!")
              gameOver = true
            }
          }
        }
      } else {
        println("Invalid input. Use format like 'C3' or 'F C3'.")
      }
    }
    printBoard(reveal = true)
  }

    val visited: Array[Array[Boolean]] = Array.fill(size, size)(false)

    def getMineCount(row: Int, col: Int): Char = {
        val directions = List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))
        val count = directions.count { case (dr, dc) =>
        val nr = row + dr
        val nc = col + dc
        nr >= 0 && nr < size && nc >= 0 && nc < size && mineField(nr)(nc)
        }
        if (count == 0) {
            revealAdjacent(row, col) // reveal adjacent cells if no mines around
            '#'
        } else {
            (count + '0').toChar
        }
    }
// if # is next to # then reveal all adjacent cells
    def revealAdjacent(row: Int, col: Int): Unit = {
        if (visited(row)(col)) return
        visited(row)(col) = true

        val directions = List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))
        for ((dr, dc) <- directions) {
            val nr = row + dr
            val nc = col + dc
            if (nr >= 0 && nr < size && nc >= 0 && nc < size && board(nr)(nc) == '_') {
                board(nr)(nc) = getMineCount(nr, nc)
                if (board(nr)(nc) == '#') revealAdjacent(nr, nc)
            }
        }
  }
  def checkWin(): Boolean = {
    var flaggedMines = 0
    var correctlyFlagged = 0
    for (r <- 0 until size; c <- 0 until size) {
      if (flagField(r)(c)) {
        flaggedMines += 1
        if (mineField(r)(c)) correctlyFlagged += 1
      }
    }
    flaggedMines == mineCount && correctlyFlagged == mineCount
  }

  def printBoard(reveal: Boolean = false): Unit = {
    println("  1 2 3 4 5 6 7 8 9")
    for (r <- 0 until size) {
      print((r + 'A').toChar + " ")
      for (c <- 0 until size) {
        if (reveal && mineField(r)(c)) print("* ") // mine emoji
        else if (flagField(r)(c)) print("P ") // flag emoji
        else print(s"${board(r)(c)} ")
      }
      println()
    }
  }
}
