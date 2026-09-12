package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

public class UtilTest {

    @Test
    void get_time_str_by_sec2_formats_days_hours_minutes_seconds() {
        assertEquals("0d 0h 0p 0s", Util.get_time_str_by_sec2(0));
        assertEquals("0d 1h 1p 1s", Util.get_time_str_by_sec2(3_661_000L));
        assertEquals("1d 1h 1p 1s", Util.get_time_str_by_sec2(90_061_000L));
    }

    @Test
    void isnumber_accepts_only_parseable_integers() {
        assertTrue(Util.isnumber("123"));
        assertTrue(Util.isnumber("-5"));
        assertFalse(Util.isnumber("12.3"));
        assertFalse(Util.isnumber("abc"));
        assertFalse(Util.isnumber(""));
    }

    @Test
    void number_format_uses_dot_as_thousands_separator() {
        assertEquals("1.234.567", Util.number_format(1_234_567L));
        assertEquals("0", Util.number_format(0L));
    }

    @Test
    void is_same_day_compares_calendar_date_only() {
        DateTime morning = new DateTime(2024, 5, 1, 8, 0);
        DateTime evening = new DateTime(2024, 5, 1, 23, 0);
        DateTime nextDay = new DateTime(2024, 5, 2, 0, 1);
        assertTrue(Util.is_same_day(morning, evening));
        assertFalse(Util.is_same_day(morning, nextDay));
    }
}
