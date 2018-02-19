package dijkrosoft.nl;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;

public class TokenReplacer {

    final static String REGEX_FOR_MONTHS_BACK = "\\$\\{for-month-(\\d)\\}";


    /**
     * Replace token by actual date. E.g. replace ${for-month-3} by 20180105.
     * @param input String, typically XML, that contains zero or more tokens
     * @return input with all the 'for-month-n' tokens replaced by actual dates.
     */
    public static String replaceMonthTokens(String input) {
        Pattern p = Pattern.compile(REGEX_FOR_MONTHS_BACK);

        Matcher m = p.matcher(input);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, formatDate(Integer.parseInt(m.group(1))));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private static String formatDate(int n) {
        LocalDate nMonthsBack = LocalDate.now().minusMonths(n);

        return nMonthsBack.format(BASIC_ISO_DATE);
    }


}
