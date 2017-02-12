package ru.levry.topword.web;

import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.levry.topword.web.config.TopwordConfig;
import ru.levry.topword.web.config.WebMvcConfig;

/**
 * @author levry
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{
                getCharacterEncodingFilter()
        };
    }

    private CharacterEncodingFilter getCharacterEncodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                TopwordConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebMvcConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }
}
