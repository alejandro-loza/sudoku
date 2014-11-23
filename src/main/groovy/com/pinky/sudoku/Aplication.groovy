package com.pinky.sudoku
/**
 * Created by pinks on 21/11/14.
 */
class Aplication {
    def hello(){
        "hello world"
    }

    def openFile() {
        File file = new File("input/source.txt")
        file.readLines() as List
    }

    def validateEntry(List<String> entry) {
       entry.collect {
            List<Integer> list =it.tokenize(',;') as List<Integer>
            Integer size = list.remove(0) as Integer
            if(validSize(size,list.size())){
              list.collect{
                  it as Integer
              }
            } else  "invalid format"//todo poner mejor respuesta
       }

    }

    boolean validSize(int size, int listSize) {
      if((size==4 || size ==9) && Math.pow(size,2)==listSize){
         true
      }else false
    }

    def convertToGrid(List<Integer> entries) {
        Integer size = Math.pow(entries.size(),0.5)
        entries.collate(size) as int[][]
    }

    def findGridPartners(int row, int col, int length) {
        List vecinos = []
        Integer fact = Math.pow(length,0.5).intValue()-1

        println "row $row col $col matriz $length"
        List limits = findLimits(row, col,findAxis(length))

            def (int rowStart, int colStart, int rowFinish, int colFinish) = setIndex(row,  col, fact, length, limits)

           println "rowStart $rowStart row $rowFinish colStart $colStart colFinish $colFinish"
            for ( int curRow = rowStart; curRow <= rowFinish; curRow++ ) {
                for ( int curCol = colStart; curCol <= colFinish; curCol++ ) {
                    if([curRow,curCol] != [row,col]){
                        vecinos << [curRow,curCol]
                    }
                }
            }

        vecinos
    }

    private List setIndex(int row, int col, int fact, int length, List<List> limits ) {

        def inferiorLimitRow = limits.first().get(0)
        def inferiorLimitCol = limits.first().get(1)
        def superiorLimitRow = limits.get(1).get(0)
        def superiorLimitCol = limits.get(1).get(1)
      println limits
        println "fact" + fact
        println "inferiorLimitRow" + inferiorLimitRow
        println "inferiorLimitCol" + inferiorLimitCol
        println "superiorLimitRow" + superiorLimitRow
        println "superiorLimitCol" + superiorLimitCol

        int rowStart = Math.max(row - 1, inferiorLimitRow) // limite inferior row
        int colStart = Math.max(col - 1, inferiorLimitCol) // limite unferior col
        int rowFinish = Math.min(row + fact, superiorLimitRow - 1)//limitsuperior row
        int colFinish = Math.min(col + fact, superiorLimitRow - 1)//limitesuperior col end

        println "SET INDEX rowStart $rowStart colStart $colStart rowFinish $rowFinish colFinish $colFinish"
        [rowStart, colStart, rowFinish, colFinish]
    }

    def findAxis(def matrizLenght) {
        List<Integer> scala = [0]
        def input = 0
        Integer factor = Math.pow(matrizLenght,0.5).intValue()
        while(input <matrizLenght){
            input = factor + input
            scala.add(input)
        }
        scala
    }

    List findLimits(int row, int col, List<Integer> axis) {
          def inicialLimitRow
          def inicialLimitCol
          def finalLimitRow
          def finalLimitCol
        (inicialLimitRow, inicialLimitCol) = findInferiorLimit(axis, row, col)
        (finalLimitRow, finalLimitCol) = findSuperiorLimit(axis, row, col)
        [ [inicialLimitRow,inicialLimitCol],[finalLimitRow,finalLimitCol]]

    }

    private List findInferiorLimit(List<Integer> axis, int row, int col) {
        def inicialLimitCol, inicialLimitRow
        if (axis.contains(row)) {
            inicialLimitRow = row
        } else {
            inicialLimitRow = (axis.findAll { it < row }).max()
        }

        if (axis.contains(col)) {
            inicialLimitCol = col
        } else {
            inicialLimitCol = axis.findAll { it < col }.max()
        }
        [inicialLimitRow, inicialLimitCol]
    }

    private List findSuperiorLimit(List<Integer> axis, int row, int col) {

         def finalLimitRow = (axis.findAll { it > row }).min()?:row

         def finalLimitCol = axis.findAll { it > col }.min()?:col

        [finalLimitRow, finalLimitCol]
    }
}