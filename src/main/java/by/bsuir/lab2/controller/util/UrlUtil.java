package by.bsuir.lab2.controller.util;

import jakarta.servlet.http.HttpServletRequest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UrlUtil {

    private static final String REFERER_HEADER = "referer";

    private UrlUtil() {
    }

    public static String getRefererUrl(HttpServletRequest request) {
        return request.getHeader(REFERER_HEADER);
    }

    public static Map<String, String[]> getQueryParameters(String url) {
        URL objUrl;
        Map<String, List<String>> queryParameters = new HashMap<>();
        try {
            objUrl = new URL(url);
        } catch (MalformedURLException e) {
            return null;
        }
        String queryString = objUrl.getQuery();
        String[] parameters = queryString.split("&");
        for (String parameter : parameters) {
            String[] parameterPair = parameter.split("=");
            String parameterName = parameterPair[0];
            String parameterValue = parameterPair.length > 1 ? parameterPair[1] : "";
            List<String> parameterValues = queryParameters.computeIfAbsent(parameterName, k -> new ArrayList<>());
            parameterValues.add(parameterValue);
        }
        return queryParameters.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().toArray(new String[0])
                ));
    }
}