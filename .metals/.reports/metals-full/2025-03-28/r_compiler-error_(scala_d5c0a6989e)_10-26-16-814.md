file:///C:/HTWG_Module/Software%20engeneering/testscala/src/main/scala/.scala-build/scala_d5c0a6989e/src_generated/main/worksheet.sc.scala
### java.nio.file.InvalidPathException: Illegal char <:> at index 3: jar:file:///C:/Users/tiemo/AppData/Local/Coursier/cache/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.15/scala-library-2.13.15-sources.jar!/scala/Int.scala

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 521
uri: file:///C:/HTWG_Module/Software%20engeneering/testscala/src/main/scala/.scala-build/scala_d5c0a6989e/src_generated/main/worksheet.sc.scala
text:
```scala


final class worksheet$_ {
def args = worksheet_sc.args$
def scriptPath = """worksheet.sc"""
/*<script>*/
// Create Minefield with a 2D array
val row = 9
val col = 9
val mineField = Array.ofDim[Int](row, col) // 2D array of Ints

val mine = "💣"
val mineCount = 10 // Number of mines
val flagsToSet = 10 // Number of flags to set
val empty = "⬜️" // Empty cell
val flag = "🚩" // Flag cellg
val directions = List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))

if (flagsToSet . m@@)

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
      "---Flags to set:"+ flagsToSet+"---- \n")

}
```



#### Error stacktrace:

```
java.base/sun.nio.fs.WindowsPathParser.normalize(WindowsPathParser.java:182)
	java.base/sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:153)
	java.base/sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:77)
	java.base/sun.nio.fs.WindowsPath.parse(WindowsPath.java:92)
	java.base/sun.nio.fs.WindowsFileSystem.getPath(WindowsFileSystem.java:232)
	java.base/java.nio.file.Path.of(Path.java:147)
	java.base/java.nio.file.Paths.get(Paths.java:69)
	scala.meta.io.AbsolutePath$.apply(AbsolutePath.scala:58)
	scala.meta.internal.metals.MetalsSymbolSearch.$anonfun$definitionSourceToplevels$2(MetalsSymbolSearch.scala:70)
	scala.Option.map(Option.scala:242)
	scala.meta.internal.metals.MetalsSymbolSearch.definitionSourceToplevels(MetalsSymbolSearch.scala:69)
	dotty.tools.pc.completions.CaseKeywordCompletion$.dotty$tools$pc$completions$CaseKeywordCompletion$$$sortSubclasses(MatchCaseCompletions.scala:342)
	dotty.tools.pc.completions.CaseKeywordCompletion$.matchContribute(MatchCaseCompletions.scala:292)
	dotty.tools.pc.completions.Completions.advancedCompletions(Completions.scala:349)
	dotty.tools.pc.completions.Completions.completions(Completions.scala:122)
	dotty.tools.pc.completions.CompletionProvider.completions(CompletionProvider.scala:139)
	dotty.tools.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:150)
```
#### Short summary: 

java.nio.file.InvalidPathException: Illegal char <:> at index 3: jar:file:///C:/Users/tiemo/AppData/Local/Coursier/cache/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.15/scala-library-2.13.15-sources.jar!/scala/Int.scala