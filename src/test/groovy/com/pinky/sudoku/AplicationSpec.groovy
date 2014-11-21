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



}
