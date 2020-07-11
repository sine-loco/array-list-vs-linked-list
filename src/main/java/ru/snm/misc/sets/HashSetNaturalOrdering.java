package ru.snm.misc.sets;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * check HashSet behavior while inserting non-Comparable items into the same bucket.
 *
 * @author sine-loco
 */
public class HashSetNaturalOrdering {
    private final Set<SoreHashcode> setUnderTest = new HashSet<>();


    public void add(int count) {
        for ( int i = 0; i < count; i++) {
            setUnderTest.add( new SoreHashcode() );
        }
    }


    final static class SoreHashcode {
        private final AtomicInteger counter = new AtomicInteger( 0 );

        final int marker;

        SoreHashcode() {
            marker = counter.getAndIncrement();
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals( Object obj ) {
            if ( this == obj ) {
                return true;
            }

            if ( obj instanceof SoreHashcode ) {
                return ( ( SoreHashcode ) obj ).marker == this.marker;
            }
            return false;
        }
    }
}
