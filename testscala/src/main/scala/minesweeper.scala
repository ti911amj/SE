import scala.util.Random
import scala.io.StdIn // user imput

object Minesweeper {
  val size = 9
  val mineCount = 10  // Anzahl mines
  val board: Array[Array[Char]] = Array.fill(size, size)('_') // fill with '_'
  val mineField: Array[Array[Boolean]] = Array.fill(size, size)(false) 
  val flagField: Array[Array[Boolean]] = Array.fill(size, size)(false)
  val visited: Array[Array[Boolean]] = Array.fill(size, size)(false)
  val directions = List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))

  def main(args: Array[String]): Unit = {
    placeMines()
    print("\n"+
        "Wähle eine Koodinate aus mit dem Format Buchstabe Zahel zB: 'C3' \n" +
        "Wenn du einen Flag setzen willst, dann tippe ein 'F' vor der Koodinate zB: 'F C3' \n" +
        " \n")
    playGame()
    
  }

  def placeMines(): Unit = {
    var placedMines = 0
    while (placedMines < mineCount) {
      val row = Random.nextInt(size)
      val col = Random.nextInt(size)
      if (!mineField(row)(col)) {  // solange false, setze mine true
        mineField(row)(col) = true
        placedMines += 1
      }
    }
  }

  // Unit = void
  def playGame(): Unit = {
    var gameOver = false
    while (!gameOver) {
      printBoard()
      val input = StdIn.readLine().trim.toUpperCase
      val isFlag = input.startsWith("F ")
      val move =  if (isFlag) input.substring(2)else input

                /*} else if(quit) {
                    gameOver = true 
                } else if(restart) { // reset game
                    for (r <- 0 until size; c <- 0 until size) {
                      board(r)(c) = '_'
                      mineField(r)(c) = false
                      flagField(r)(c) = false
                      visited(r)(c) = false
                  }*/
                
              

      if (move.matches("[A-I][1-9]")) {
        // Indizes für Zeile und Spalte. Zeile geht von 0-8 und Spalte von 0-8
        // zB 'C3': '3' hat ACII 3 und 'C' hat ASCII 67, A hat ASCII 65. 
        // 67-65 = 2 -> Zeile am Index 2
        // 3-1 = 2 -> Spalte am Index 2
        val row = move.charAt(0) - 'A' 
        val col = move.charAt(1) - '1'

        if (isFlag) {
            flagField(row)(col) = !flagField(row)(col) // toggle flag true/false
        } else if (flagField(row)(col)) {
            println("Du kannst keine Zelle anzeigen wenn ein Flag drauf ist\n" +
            "Entferne zuerst den Flag indem du ein 'F ' vor der Koordinate setzt.\n")
        } else if (board(row)(col) != '_') {
          println("This cell has already been revealed.")
        } /*else if (mineField(row)(col) && flagField(row)(col)) {
          println("You can't place a flag on a mine.")
        }*/
        else {
          if (mineField(row)(col)) {
            gameOver = true
            println("     _.-^^---....,,--\n"+      
                    " _--                  --_\n"+  
                    "<                        >)\n"+
                    "|                         |\n"+ 
                    " \\._                   _./\n"+  
                    "    ```--. . , ; .--'''\n"+       
                    "          | |   |\n"+             
                    "       .-=||  | |=-.\n"+   
                    "       `-=#$%&%$#=-'\n"+   
                    "          | ;  :|\n"+     
                    " _____.,-#%&$@%#&#~,._____\n"+
            "\n"+"BOOOOOOOOOOOOOOOOOOOOOOOOOOOOOM! You lose."+ 
            "\n")
          } else {
            board(row)(col) = getMineCount(row, col)
            if (checkWin()) {
              gameOver = true
              println("\n"+
                      "▄██   ▄    ▄██████▄  ███    █▄        ▄█     █▄   ▄█ ███▄▄▄▄\n"+   
                      "███   ██▄ ███    ███ ███    ███      ███     ███ ███ ███▀▀▀██▄\n"+ 
                      "███▄▄▄███ ███    ███ ███    ███      ███     ███ ███ ███   ███\n"+ 
                      "▀▀▀▀▀▀███ ███    ███ ███    ███      ███     ███ ███ ███   ███\n"+ 
                      "▄██   ███ ███    ███ ███    ███      ███     ███ ███ ███   ███\n"+ 
                      "███   ███ ███    ███ ███    ███      ███     ███ ███ ███   ███\n"+ 
                      "███   ███ ███    ███ ███    ███      ███ ▄█▄ ███ ███ ███   ███\n"+ 
                      " ▀█████▀   ▀██████▀  ████████▀        ▀███▀███▀  █▀   ▀█   █▀\n" )
            }
          }
        }
      } else {
          if (move.matches("Q")){
            gameOver = true
            println("Das Spiel wurde beendet.")
          }else if(move.matches("R")){
            for (r <- 0 until size; c <- 0 until size) {
              board(r)(c) = '_'
              mineField(r)(c) = false
              flagField(r)(c) = false
              visited(r)(c) = false
            }
            placeMines()
            println("Das Spiel wurde zurückgesetzt.")
          }else{
            println("Invalider Input.\n" +
            "Wähle eine Koodinate aus mit dem Format Buchstabe Zahel zB: 'C3' \n" +
            "Wenn du einen Flag setzen willst, dann tippe ein 'F' vor der Koodinate zB: 'F C3' \n" )
          }
      }
    }
    printBoard(reveal = true)
  }

  

  def getMineCount(row: Int, col: Int): Char = {
      
      val count = directions.count { case (dr, dc) =>
      val nr = row + dr
      val nc = col + dc
      nr >= 0 && nr < size && nc >= 0 && nc < size && mineField(nr)(nc)
      }
      if (count == 0) {
        revealAdjacent(row, col) // reveal adjacent cells if no mines around
        '0' // no neighboring mines
      } else {
        (count + '0').toChar
      }
    }
// if # is next to # then reveal all adjacent cells
  def revealAdjacent(row: Int, col: Int): Unit = {
    if (visited(row)(col)) return
    visited(row)(col) = true

   
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
        if (reveal && mineField(r)(c)) print(164.toChar + " ") // mine emoji
        else if (flagField(r)(c)) print(182.toChar + " ") // flag emoji
        else print(s"${board(r)(c)} ")
      }
      println()
    }
  }
}
