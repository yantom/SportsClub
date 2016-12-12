package cz.muni.fi.pa165.sportsClub;

import javax.servlet.Filter;
import javax.servlet.ServletContext;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootWebContext.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("utf-8", true);
		return new Filter[] { encodingFilter };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws javax.servlet.ServletException {

		super.onStartup(servletContext);
		// not working.. YET
		servletContext.addListener(AppServletContextListener.class);
	}

}
