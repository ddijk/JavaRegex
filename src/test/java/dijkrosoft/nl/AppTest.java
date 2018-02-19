package dijkrosoft.nl;


import org.junit.Test;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void testOneMonthBack() {

       String expected = LocalDate.now().minusMonths(1).format(BASIC_ISO_DATE);
        assertEquals(expected, TokenReplacer.replace("${for-month-1}"));
    }

    @Test
    public void testTwoMonthsBack() {

        String expected = LocalDate.now().minusMonths(2).format(BASIC_ISO_DATE);
        assertEquals(expected, TokenReplacer.replace("${for-month-2}"));
    }

    @Test
    public void testRegex() {
        Pattern pattern = Pattern.compile(".*\\$\\{for-month-(\\d)\\}.*");

        Matcher matcher = pattern.matcher("<a>${for-month-5}</a>");

        assertTrue(matcher.matches());
    }

    @Test
    public void testRegex2() {
//        final String input = "${for-month-(\\d)}";

        String input = "<a>${for-month-5}</a>";


        assertEquals("<a>20170920</a>", TokenReplacer.replaceAllMonthTokens(input));
    }

}
