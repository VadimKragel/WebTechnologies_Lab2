package by.bsuir.lab2.controller.filter;

import by.bsuir.lab2.bean.Language;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageFilter implements Filter {

    private static final List<String> EXCLUDED_PREFIX_PATHS = Arrays.asList("/actions", "/static", "/favicon.ico");
    private static final List<String> SUPPORTED_LANGUAGES = Arrays.stream(Language.values())
            .map(Language::getStringValue)
            .collect(Collectors.toList());

    private static final Language DEFAULT_LANGUAGE = Language.EN;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpRequest.setAttribute("supportedLanguages", SUPPORTED_LANGUAGES);
        if (!isExcluded(httpRequest.getRequestURI())) {
            String lang = httpRequest.getParameter("lang");
            if (lang == null || !SUPPORTED_LANGUAGES.contains(lang)) {
                lang = DEFAULT_LANGUAGE.getStringValue();
                String uri = httpRequest.getRequestURI();
                String queryString = httpRequest.getQueryString();
                StringBuilder urlBuilder = new StringBuilder(uri + "?");
                if (queryString != null) {
                    urlBuilder.append(queryString).append("&");
                }
                urlBuilder.append("lang=").append(lang);
                httpResponse.sendRedirect(urlBuilder.toString());
                return;
            }
            httpRequest.setAttribute("lang", lang);
        }
        chain.doFilter(request, response);
    }

    private boolean isExcluded(String requestUri) {
        for (String excludedPrefix : EXCLUDED_PREFIX_PATHS) {
            if (requestUri.startsWith(excludedPrefix)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
