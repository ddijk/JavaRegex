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
public class AppTest {

    @Test
    public void testReplaceFirst() {

        String input = "<a>${for-month-5}</a>";
        assertEquals("<a>20170919</a>", TokenReplacer.replaceMonthTokens(input));
    }

    @Test
    public void testReplaceRepeating() {

        String input = "<a>${for-month-5}</a><a>${for-month-2}</a>";
        String actual = TokenReplacer.replaceMonthTokens(input);
        assertEquals("<a>20170919</a><a>20171219</a>", actual);
    }

    @Test
    public void testOneMonthBack() {

        String expected = LocalDate.now().minusMonths(1).format(BASIC_ISO_DATE);
        assertEquals(expected, TokenReplacer.replaceMonthTokens("${for-month-1}"));
    }

    @Test
    public void testTwoMonthsBack() {

        String expected = LocalDate.now().minusMonths(2).format(BASIC_ISO_DATE);
        assertEquals(expected, TokenReplacer.replaceMonthTokens("${for-month-2}"));
    }

    @Test
    public void testRegex() {
        Pattern pattern = Pattern.compile(".*\\$\\{for-month-(\\d)\\}.*");

        Matcher matcher = pattern.matcher("<a>${for-month-5}</a>");

        assertTrue(matcher.matches());
    }

    @Test
    public void testReplaceSingleDate() {

        String input = "<a>${for-month-5}</a>";


        assertEquals("<a>20170919</a>", TokenReplacer.replaceMonthTokens(input));
    }

    @Test
    public void testReplaceAll() {
        String input = "<a>${for-month-5}</a><a>${for-month-2}</a>";

        assertEquals("<a>xxx</a><a>xxx</a>", input.replaceAll("\\$\\{for-month-(\\d)\\}", "xxx"));
    }


    @Test
    public void testGreedyReplace() {
        String input = "<a>${for-month-5}</a>";
        String regex = "\\$\\{for-month-\\d\\}";   //TokenReplacer.REGEX_FOR_MONTHS_BACK,
        assertEquals("<a>20170919</a>", input.replaceAll(regex, "20170919"));

    }

    @Test
    public void test() {
        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one cat two cats in the yard");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "dog");
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
        assertEquals("one dog two dogs in the yard", sb.toString());
    }

    @Test
    public void test2() {

        String input = "<a>${for-month-5}</a><a>${for-month-2}</a>";
        assertEquals("<a>20170919</a><a>20171219</a>", TokenReplacer.replaceMonthTokens(input));
    }

    private String formatDate(int n) {
        LocalDate oneMonthBack = LocalDate.now().minusMonths(n);

        return oneMonthBack.format(BASIC_ISO_DATE);
    }

}
