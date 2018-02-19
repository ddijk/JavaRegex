package dijkrosoft.nl;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;

public class TokenReplacer {

    public static String replaceAllMonthTokens(String xml) {
        final String token = "${for-month-(\\d)}";
        return xml.replaceAll(token, replace(token));

    }

    static String replace(String token) {


        int n = getNumberOfMonthsFromToken(token);

        LocalDate oneMonthBack = LocalDate.now().minusMonths(n);
        ;
        return oneMonthBack.format(BASIC_ISO_DATE);

    }

    static int getNumberOfMonthsFromToken(String inputXml) {

        Pattern pattern = Pattern.compile(".*\\$\\{for-month-(\\d)\\}.*");

        Matcher matcher = pattern.matcher(inputXml);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("token not found: " + inputXml);
        }


        return Integer.parseInt(matcher.group(1));

    }


}
