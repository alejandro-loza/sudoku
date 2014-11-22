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
        def fact = length-1
        println length

            def (int rowStart, int rowFinish, int colStart, int colFinish) = setIndex(row, fact, length, col)

            for ( int curRow = rowStart; curRow <= rowFinish; curRow++ ) {
                for ( int curCol = colStart; curCol <= colFinish; curCol++ ) {
                    if([curRow,curCol] != [row,col]){
                        vecinos << [curRow,curCol]
                    }
                }
            }

        vecinos
    }

    private List setIndex(int row, int fact, int length, int col) {
        int rowStart = Math.max(row - fact, 0)
        int rowFinish = Math.min(row + fact, length - 1)
        int colStart = Math.max(col - fact, 0)
        int colFinish = Math.min(col + fact, length - 1)
        [rowStart, rowFinish, colStart, colFinish]
    }

    def findAxis(def matrizLenght) {
        List<Integer> scala = [0]
        Integer factor = Math.pow(matrizLenght,0.5).intValue()
        while(factor <=9){
            scala.add(factor)
            factor+=factor
        }
        scala

    }
}