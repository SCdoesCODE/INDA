/**
 * Created by Sabrina on 2/28/18.
 */
public class QuicksortRandomPivotTest extends IntSorterTest {
    @Override
    protected IntSorter getIntSorter() {
        return new QuicksortRandomPivot();
    }

}
