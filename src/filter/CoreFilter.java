package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CoreFilter implements Filter{

    private FilterConfig config=null;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config=filterConfig;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			
		    String pass=config.getInitParameter("pass");
		    HttpServletRequest req=(HttpServletRequest)request;
		    String[] path=pass.split(";");
		    String uri=req.getRequestURI();
		    if(uri.contains(".jsp")){
		       for(int i=0;i<path.length;i++){
		            	if(uri.contains(path[i])){
		    	     	chain.doFilter(request, response);
		    		    return;
		         	  }
		          }
		        HttpSession session =req.getSession();
			    if(session.getAttribute("user")!=null){
			    	chain.doFilter(request, response);
			    }else{
			    	((HttpServletResponse)response).sendRedirect("/bbs/index.jsp");
			    }
		    }else{
		    	chain.doFilter(request, response);	
		    }
	}

	@Override
	public void destroy() {
		
	}
 
}
