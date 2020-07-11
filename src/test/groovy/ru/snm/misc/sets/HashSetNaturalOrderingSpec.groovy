package ru.snm.misc.sets

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll


/**
 *
 * @author sine-loco
 */
@Unroll
class HashSetNaturalOrderingSpec extends Specification {
    @Subject
    def subject = new HashSetNaturalOrdering()


    def "i dunno"() {
        when:
        subject.add 5

        then:
        1 == 1
    }

}