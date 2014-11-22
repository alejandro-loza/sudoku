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
}