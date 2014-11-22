package com.pinky.sudoku

import spock.lang.Specification
import spock.lang.Unroll

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
        assert response ==[[1, 4, 2, 3, 2, 3, 1, 4, 4, 2, 3, 1, 3, 1, 4, 2], [2, 1, 3, 2, 3, 2, 1, 4, 1, 4, 2, 3, 2, 3, 4, 1]]
    }
    @Unroll
    void "recibe a list.size: #listSize  an a size: #size and  list.size #listSize equal size *size #result "(){
        setup:
          Aplication apliation = new Aplication()
        when:
          def response = apliation.validSize(size,listSize)
        then:
          assert response == result
        where:

        size | listSize | result
         2   |     4    |   4
         3   |     9    |   9
         4   |    16    |   16
         5   |    25    |   25
         6   |    36    |   36
         1   |    12    |   null
    }








}
