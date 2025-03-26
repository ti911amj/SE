

final class worksheet$_ {
def args = worksheet_sc.args$
def scriptPath = """worksheet.sc"""
/*<script>*/
// Create Minefield with a 2D array
var row = 9
var col = 9
var mineField = Array.ofDim[Int](row, col) // 2D array of Ints

var mine = "💣"
var mineCount = 10 // Number of mines
var empty = "⬜️" // Empty cell
var flag = "🚩" // Flag cellg

/*</script>*/ /*<generated>*//*</generated>*/
}

object worksheet_sc {
  private var args$opt0 = Option.empty[Array[String]]
  def args$set(args: Array[String]): Unit = {
    args$opt0 = Some(args)
  }
  def args$opt: Option[Array[String]] = args$opt0
  def args$: Array[String] = args$opt.getOrElse {
    sys.error("No arguments passed to this script")
  }

  lazy val script = new worksheet$_

  def main(args: Array[String]): Unit = {
    args$set(args)
    val _ = script.hashCode() // hashCode to clear scalac warning about pure expression in statement position
  }
}

export worksheet_sc.script as `worksheet`

