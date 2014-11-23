package com.pinky.sudoku

import spock.lang.Ignore
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
//    @Ignore
    void "must find its sub grid partners in 9x9 matrix"(){
        setup:"giving an 9x9 matrix lenght"
        Aplication aplication = new Aplication()
        Integer length = 9
        and:"giving a current  cordinate"
          def row = 1
          def col = 1

        when:
        def response = aplication.findGridPartners(row,col,length)
        then:
        assert response == [[0,0],[0,1],[0,2],[1,0],[1,2],[2,0],[2,1],[2,2]]
    }
//    @Ignore
    void "must find its sub grid partners in 9x9 matrix and "(){
        setup:"giving an 9x9 matrix lenght"
        Aplication aplication = new Aplication()
        Integer length = 9
        and:"giving a current  cordinate"
        def row = 3
        def col = 3

        when:
        def response = aplication.findGridPartners(row,col,length)
        then:
        assert response == [[3,4],[3,5],[4,3],[4,4],[4,5],[5,3],[5,4],[5,5]]
    }

    void "must find its sub grid partners in 4x4 matrix"(){
        setup:"giving an 9x9 matrix lenght"
        Aplication aplication = new Aplication()
        Integer length = 4
        and:"giving a current  cordinate"
        def row = 2
        def col = 2

        when:
        def response = aplication.findGridPartners(row,col,length)
        then:
        assert response == [[2,3],[3,2],[3,3]]


    }
    @Unroll
    void "giving a  cuadratic lenght #lenght must  find its axis #axis"(){
        setup:
        Aplication aplication = new Aplication()
        when:
          def response = aplication.findAxis(lenght)
        then:
         assert response ==  axis
        where:

               lenght |    axis
                  9   |  [0,3,6,9]
                  4   |  [0,2,4]
    }
    @Unroll
    void "giving an axis #axis and a actual position row #row col #col  must find an inicial limit #inicialLimit and a final limit #finalLimit"(){
        setup:
          Aplication aplication = new Aplication()
        when:
          def response = aplication.findLimits(row,col,axis)
        then:
          assert response == [inicialLimit,finalLimit]
        where:

            row | col |     axis   | inicialLimit |finalLimit
             1  |  1  |  [0,3,6,9] |     [0,0]    | [3,3]
             4  |  4  |  [0,3,6,9] |     [3,3]    | [6,6]
             3  |  3  |  [0,3,6,9] |     [3,3]    | [6,6]
             0  |  0  |  [0,3,6,9] |     [0,0]    | [3,3]
             3  |  3  |  [0,3,6,9] |     [3,3]    | [6,6]
             5  |  5  |  [0,3,6,9] |     [3,3]    | [6,6]
             0  |  4  |  [0,3,6,9] |     [0,3]    | [3,6]
             3  |  4  |  [0,3,6,9] |     [3,3]    | [6,6]
             1  |  5  |  [0,3,6,9] |     [0,3]    | [3,6]
             8  |  8  |  [0,3,6,9] |     [6,6]    | [9,9]
             0  |  5  |  [0,3,6,9] |     [0,3]    | [3,6]
             1  |  1  |  [0,2,4]   |     [0,0]    | [2,2]
             3  |  3  |  [0,2,4]   |     [2,2]    | [4,4]
             0  |  3  |  [0,2,4]   |     [0,2]    | [2,4]
    }
//
//    void "giving a row #row  col #col and lenght #lenght ,must find an vertical partners #result"(){
//        setup:
//        Aplication aplication = new Aplication()
//        when:
//        def response = aplication.findVerticalPartners(row,col,axis)
//        then:
//        assert response == [inicialLimit,finalLimit]
//        where:
//
//    }


















}
