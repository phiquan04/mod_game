package template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LevelTest {

    @Test
    void get_total_point_by_level_grants_two_points_per_level_plus_base() {
        assertEquals(2, Level.get_total_point_by_level(0));
        assertEquals(4, Level.get_total_point_by_level(1));
        assertEquals(12, Level.get_total_point_by_level(5));
    }

    @Test
    void exp_curve_has_250_strictly_increasing_entries() {
        // Currently FAILS around level 197: Level's static initializer computes
        // `value + (value * ((i - 1) + 2)) / 712` and that intermediate product overflows
        // a signed long once value passes roughly 4.6e16 (around level 196), which corrupts
        // every exp requirement from that point on (values dip and then go flat at level 201+).
        // This is a pre-existing bug in the exp curve, not a bug in this test - left failing
        // on purpose so it is visible until someone decides how the curve should be fixed
        // (e.g. widen the arithmetic or cap the multiplier) rather than silently wrong in prod.
        assertEquals(250, Level.ENTRYS.length);
        for (int i = 1; i < Level.ENTRYS.length; i++) {
            assertTrue(Level.ENTRYS[i].exp > Level.ENTRYS[i - 1].exp,
                    "exp at level " + (i + 1) + " should exceed the previous level");
        }
    }

    @Test
    void thongthao_curve_starts_at_level_99_and_grows_ten_percent() {
        assertEquals(150, Level.LEVEL_THONGTHAO.length);
        assertEquals(Level.ENTRYS[98].exp, Level.LEVEL_THONGTHAO[0]);
        assertEquals((Level.LEVEL_THONGTHAO[0] * 11) / 10, Level.LEVEL_THONGTHAO[1]);
    }
}
