package site.binghai.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by binghai on 2017/9/9.
 *
 * @ MoGuJie
 */
public class HttpRequestUtils {
    public static String getRequestParams(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            if (sb.length() == 0) {
                sb.append(paraName + "=" + request.getParameter(paraName));
            } else {
                sb.append("&" + paraName + "=" + request.getParameter(paraName));
            }
        }
        return sb.toString();
    }

    public static String getRequestURL(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    public static String getRequestFullPath(HttpServletRequest request) {
        return getRequestURL(request) + "?" + getRequestParams(request);
    }

    public static String getRequestPath(HttpServletRequest request) {
        String full = getRequestFullPath(request);
        return full.substring(full.lastIndexOf("/"), full.length());
    }
}
