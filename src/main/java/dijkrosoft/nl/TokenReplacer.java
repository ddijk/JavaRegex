package dijkrosoft.nl;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;

public class TokenReplacer {

    final static String REGEX_FOR_MONTHS_BACK = "\\$\\{for-month-(\\d)\\}";
    final static String REGEX_FOR_MONTHS_BACK_GREEDY = String.format(".*%s.*", REGEX_FOR_MONTHS_BACK);


    public static String replaceFirstMonthTokens(String xml) {
        return xml.replaceFirst(REGEX_FOR_MONTHS_BACK, calculateDate(xml));

    }

    static String calculateDate(String xml) {


        int n = getNumberOfMonthsFromToken(xml);

        System.out.println("n:"+n);

        LocalDate oneMonthBack = LocalDate.now().minusMonths(n);

        return oneMonthBack.format(BASIC_ISO_DATE);

    }

    static int getNumberOfMonthsFromToken(String inputXml) {


        Pattern pattern = Pattern.compile(REGEX_FOR_MONTHS_BACK_GREEDY);
        Matcher matcher = pattern.matcher(inputXml);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("token not found: " + inputXml);
        }


        return Integer.parseInt(matcher.group(1));

    }


}
