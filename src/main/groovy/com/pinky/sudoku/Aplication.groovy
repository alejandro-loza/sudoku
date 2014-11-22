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
        def fact = 2
        while(fact !=0){

            def (int rowStart, int rowFinish, int colStart, int colFinish) = setIndex(row, fact, length, col)

            for ( int curRow = rowStart; curRow <= rowFinish; curRow++ ) {
                for ( int curCol = colStart; curCol <= colFinish; curCol++ ) {
                    if([curRow,curCol] != [row,col]){
                        vecinos << [curRow,curCol]
                    }
                }
            }
            fact--
        }

        vecinos
    }

    private List setIndex(int row, int fact, int length, int col) {
        int rowStart = Math.max(row - fact, 0)
        int rowFinish = Math.min(row + fact, length - fact)
        int colStart = Math.max(col - fact, 0)
        int colFinish = Math.min(col + fact, length - fact)
        [rowStart, rowFinish, colStart, colFinish]
    }
}