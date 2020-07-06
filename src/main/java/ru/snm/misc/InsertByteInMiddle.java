package ru.snm.misc;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@State( Scope.Thread )
@OutputTimeUnit( TimeUnit.NANOSECONDS )
@BenchmarkMode( Mode.AverageTime )
@Measurement( time = 10 )
@Warmup( time = 10 )
public class InsertByteInMiddle {

    @Param( {"10", "100", "1000", "10000"} )//, "100000", "1000000"} )
    private int size;
    private final Byte byteVal = 7;
    private List<Byte> arrayList = new ArrayList<>();
    private List<Byte> linkedList = new LinkedList<>();


    @Setup( Level.Invocation )
    public void doSetup() {
        for ( int i = 0; i < size; i++ ) {
            arrayList.add( byteVal );
        }
        for ( int i = 0; i < size; i++ ) {
            linkedList.add( byteVal );
        }
    }

    @TearDown( Level.Invocation )
    public void doTearDown() {
        arrayList = new ArrayList<>();
        linkedList = new LinkedList<>();
    }


    @Benchmark
    public void testArrayList() {
        arrayList.add( size / 2, byteVal );
    }

    @Benchmark
    public void testLinkedList() {
        linkedList.add( size / 2, byteVal );
    }

    public static void main( String[] args ) throws RunnerException {
        Options options = new OptionsBuilder()
                .include( InsertByteInMiddle.class.getSimpleName() )
                .warmupIterations( 10 )
                .measurementIterations( 10 )
                .jvmArgs( "-Xms10000m", "-Xmx10000m", "-XX:MaxDirectMemorySize=2500M" )
                .forks( 2 )
                .build();
        new Runner( options ).run();
    }
}