// Create Minefield with a 2D array
val row = 9
val col = 9
val mineField = Array.ofDim[Int](row, col) // 2D array of Ints

val mine = "💣"
val mineCount = 10 // Number of mines
val settedFlags = 0 // Number of flags to set
val empty = "⬜️" // Empty cell
val flag = "🚩" // Flag cellg
val directions = List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))

val currentSetFlag = mineCount - settedFlags 


if (currentSetFlag == 0) print("YOU WON!")




print("------------MINE SWEEPER------------- \n"+
      "=====================================\n"+
      "|   |   |   |   |   |   |   |   |   |  \n"+
      "=====================================\n"+
      "|   |   |   |   |   |   |   |   |   |  \n"+
      "=====================================\n"+
      "|   |   |   |   |   |   |   |   |   |  \n"+
      "=====================================\n"+
      "|   |   |   |   |   |   |   |   |   |  \n"+
      "=====================================\n"+
      "|   |   |   |   |   |   |   |   |   |  \n"+
      "=====================================\n"+
      "|   |   |   |   |   |   |   |   |   |  \n"+
      "=====================================\n"+
      "---Flags to set:"+ currentSetFlag+"---- \n")

