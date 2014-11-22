package com.pinky.sudoku

import spock.lang.Specification

/**
 * Created by pinks on 21/11/14.
 */
class AplicationSpec extends Specification {
    void "must response an hello world"(){
        setup:
          Aplication aplication = new Aplication()
        when:
         def response = aplication.hello()
        then:
        assert response == "hello world"
    }

    void "must open a file"(){
        setup:
          Aplication aplication = new Aplication()
        when:
         def response = aplication.openFile()
        then:
         assert response instanceof List
         assert response == ["4;1,4,2,3,2,3,1,4,4,2,3,1,3,1,4,2", "4;2,1,3,2,3,2,1,4,1,4,2,3,2,3,4,1"]
    }

    void "must validate the entry"(){
        setup:"giving a list"
         Aplication apliation = new Aplication()
         List sudokuEntry = ["4;1,4,2,3,2,3,1,4,4,2,3,1,3,1,4,2", "4;2,1,3,2,3,2,1,4,1,4,2,3,2,3,4,1"]
        when:
          def response = apliation.validateEntry(sudokuEntry)
        then:
        assert response
    }








}
