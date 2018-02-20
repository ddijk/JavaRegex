package dijkrosoft.nl;


import org.junit.Test;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TokenReplacerTest {

    @Test
    public void testReplaceOnce() {

        String input = "<a>${for-month-5}</a>";
        assertEquals(String.format("<a>%s</a>",getDateNMonthsBack(5)), TokenReplacer.replaceMonthTokens(input));
    }

    @Test
    public void testReplaceMultiple() {

        String input = "<a>${for-month-5}</a><a>${for-month-2}</a>";
        String actual = TokenReplacer.replaceMonthTokens(input);
        assertEquals(String.format("<a>%s</a><a>%s</a>",getDateNMonthsBack(5),getDateNMonthsBack(2)), actual);
    }

    @Test
    public void testDoubleDigitsMonthsBack() {

        String input = "<a>${for-month-15}</a>";
        assertEquals(String.format("<a>%s</a>",getDateNMonthsBack(15)), TokenReplacer.replaceMonthTokens(input));
    }

    private String getDateNMonthsBack(int n) {
        LocalDate oneMonthBack = LocalDate.now().minusMonths(n);

        return oneMonthBack.format(BASIC_ISO_DATE);
    }

}
