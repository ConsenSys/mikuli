package benchmarks;

import org.apache.milagro.amcl.BLS381.ECP;
import org.apache.milagro.amcl.BLS381.ECP2;
import org.apache.milagro.amcl.BLS381.FP12;
import org.apache.milagro.amcl.BLS381.PAIR;
import org.apache.milagro.amcl.RAND;
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(3)

public class PairingBenchmarks {

  private ECP pointInG1;
  private ECP2 pointInG2;

  @Setup
  public void prepare() {
    RAND rng = new RAND();
    rng.sirand(123);
    pointInG1 = Utils.createRandomPointInG1(rng);
    pointInG2 = Utils.createRandomPointInG2(rng);
  }

  @Benchmark
  public FP12 pairing() {
    FP12 e = PAIR.ate(pointInG2, pointInG1);
    return PAIR.fexp(e);
  }
}
