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
    void "recibe a list.size: #listSize  an a size: #size and  list.size #listSize equals size * size #listSize valid #result "(){
        setup:
          Aplication apliation = new Aplication()
        when:
          def response = apliation.validSize(size,listSize)
        then:
          assert response == result
        where:

        size | listSize | result
         2   |     4    |   false
         3   |     9    |   false
         4   |    16    |   true
         5   |    25    |   false
         9   |    81    |   true
         1   |    12    |   false
    }

     void "must fill a bi dimencional int arra 4x4"(){
         setup:"giving a size of 4"
         Aplication aplication = new Aplication()
         and:"giving a valid list of number entries"
           List<Integer> gridEntry = [1, 4, 2, 3, 2, 3, 1, 4, 4, 2, 3, 1, 3, 1, 4, 2]
         when:
           def response = aplication.convertToGrid(gridEntry)
         then:
           assert response == [[1, 4, 2, 3], [2, 3, 1, 4], [4, 2, 3, 1], [3, 1, 4, 2]]
     }

    void "must fill a bi dimencional int array 9x9"(){
        setup:"giving a size of 9"
        Aplication aplication = new Aplication()
        and:"giving a valid list of number entries"
        List<Integer> gridEntry = [1, 4, 2, 3, 2, 3, 1, 4, 4,
                                   1, 4, 2, 3, 2, 3, 1, 4, 4,
                                   1, 4, 2, 3, 2, 3, 1, 4, 4,
                                   1, 4, 2, 3, 2, 3, 1, 4, 4,
                                   1, 4, 2, 3, 2, 3, 1, 4, 4,
                                   1, 4, 2, 3, 2, 3, 1, 4, 4,
                                   1, 4, 2, 3, 2, 3, 1, 4, 4,
                                   1, 4, 2, 3, 2, 3, 1, 4, 4,
                                   1, 4, 2, 3, 2, 3, 1, 4, 4,]
        when:
        def response = aplication.convertToGrid(gridEntry)
        then:
        assert response ==  [
                             [1, 4, 2, 3, 2, 3, 1, 4, 4,],
                             [1, 4, 2, 3, 2, 3, 1, 4, 4,],
                             [1, 4, 2, 3, 2, 3, 1, 4, 4,],
                             [1, 4, 2, 3, 2, 3, 1, 4, 4,],
                             [1, 4, 2, 3, 2, 3, 1, 4, 4,],
                             [1, 4, 2, 3, 2, 3, 1, 4, 4,],
                             [1, 4, 2, 3, 2, 3, 1, 4, 4,],
                             [1, 4, 2, 3, 2, 3, 1, 4, 4,],
                             [1, 4, 2, 3, 2, 3, 1, 4, 4,] ]
    }










}
