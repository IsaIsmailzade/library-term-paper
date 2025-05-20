package library.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private static final String FORMAT = "WEB-INF/jsp/%s.jsp";

    public static String getPath(String jspName) {
        return String.format(FORMAT, jspName);
    }
}
