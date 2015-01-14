package com.itso.example.webapp.servlet;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.itso.example.osgi.api.HelloAPI;

/**
 * Servlet implementation class HelloWABServlet
 */
@WebServlet("/HelloWABServlet")
public class HelloWABServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HelloAPI helloService;
       
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext servletCtx = config.getServletContext();
		BundleContext bundleCtx = (BundleContext)servletCtx.getAttribute("osgi-bundlecontext");
		try {
			ServiceReference[] refs = bundleCtx.getServiceReferences(HelloAPI.class.getName(), null);
			for(ServiceReference ref:refs){
				Object obj = bundleCtx.getService(ref);
				System.out.println(obj.getClass());
			}
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
		ServiceReference ref = bundleCtx.getServiceReference(HelloAPI.class.getName());
		helloService = (HelloAPI)bundleCtx.getService(ref);
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWABServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = helloService.getMsg();
		response.getWriter().write(msg);
		//getServiceByJNDI();
	}

	private void getServiceByJNDI(){
		try {
			InitialContext ic = new InitialContext();
			Object obj = ic.lookup("osgi:service/"+HelloAPI.class.getName());
			System.out.println("JNDI found: "+obj);
			Object obj2 = ic.lookup("aries:services/"+HelloAPI.class.getName());
			System.out.println("JNDI found aries: "+obj2);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
