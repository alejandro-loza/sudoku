package com.pinky.sudoku
/**
 * Created by pinks on 21/11/14.
 */
class Grid {
    def main(){
       def response = validateEntry(openFile())
        if(response){
        def grid
            response.collect{ entry ->
                  def resp
                    grid = convertToGrid(entry)
                    grid.each {println it}
                    grid.eachWithIndex { def row, int i ->
                        int j =0
                        while(j<=row.length-1 && resp != false ){
                            if( evaluateCurrentRow(i,j,row as List<Integer>,grid)){
                                j++
                                resp = true
                            }
                            else{
                                resp = false
                                break
                            }
                        }
                    }
                println resp
                resp
            }
        }else println "invalid entry"

    }

    private boolean evaluateCurrentRow(int i,int j,List<Integer> row,Integer[][] grid) {
        def response = []
        response.addAll(findNumberInList(grid[i][j], getGridValues(findGridPartners(i, j, grid.length), grid)))
        row.remove(j)
        response.addAll(findNumberInList(grid[i][j], row))
        response.addAll(findNumberInList(grid[i][j],findVerticalPartners(i, j, grid)))
        if(response.contains(true)){
            false
        }else true
    }

    def openFile() {
        File file = new File("input/source.txt")
        file.readLines() as List
    }

    List<Integer> validateEntry(List<String> entry) {
       entry.collect {
            List<Integer> list =it.tokenize(',;') as List<Integer>
            Integer size = list.remove(0) as Integer
            if(validSize(size,list.size())){
              list.collect{
                  it as Integer
              }
            }else null
       }

    }

    boolean validSize(int size, int listSize) {
      if((size==4 || size ==9) && Math.pow(size,2)==listSize){
         true
      }else false
    }

    def convertToGrid(List<Integer> entries) {
        Integer size = Math.pow(entries.size(),0.5)
        entries.collate(size) as Integer[][]
    }

    List<List<Integer>> findGridPartners(int row, int col, int length) {
        List partners = []
        Integer fact = Math.pow(length,0.5).intValue()-1
        List limits = findLimits(row, col,findAxis(length))
            def (int rowStart, int colStart, int rowFinish, int colFinish) = setIndex(row,  col, fact, length, limits)
            for ( int curRow = rowStart; curRow <= rowFinish; curRow++ ) {
                for ( int curCol = colStart; curCol <= colFinish; curCol++ ) {
                    if([curRow,curCol] != [row,col]){
                        partners << [curRow,curCol]
                    }
                }
            }
        partners
    }

    private List setIndex(int row, int col, int fact, int length, List<List> limits ) {

        def inferiorLimitRow = limits.first().get(0)
        def inferiorLimitCol = limits.first().get(1)
        def superiorLimitRow = limits.get(1).get(0)
        def superiorLimitCol = limits.get(1).get(1)


        int rowStart = Math.max(row - 1, inferiorLimitRow) // limite inferior row
        int colStart = Math.max(col - 1, inferiorLimitCol) // limite unferior col
        int rowFinish = Math.min(row + fact, superiorLimitRow - 1)//limitsuperior row
        int colFinish = Math.min(col + fact, superiorLimitCol - 1)//limitesuperior col end

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

    def findVerticalPartners(def row, def col, Integer[][]matriz ) {
        def counter = 0
        List response = []
         while(counter<matriz.length){
             if (counter!=row){
                 response << matriz[counter][col]
             }
             counter++
        }
        response
    }

    List <Integer>getGridValues(ArrayList<List<Integer>> coordinates, Integer[][] matrix) {
        coordinates.collect{i,j ->
            matrix[i][j]
        }
    }

    boolean findNumberInList(Integer numberToFind, List<Integer> numberList) {
        numberList.contains(numberToFind)
    }
}