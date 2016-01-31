package com.twyno;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.*;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;
import com.twyno.model.Restaurant;
import com.twyno.model.TwynoObject;
import com.twyno.services.OfyService;

@SuppressWarnings("serial")
public class TwynoViewServlet extends HttpServlet {


	private static final String ID = "i";
	private static final String TYPE = "t";
	private static final String ACTION_VIEW = "view";
	private static final String ACTION = "a";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/html");
		resp.getWriter()
				.println(
					   
					    "<head><link href='css/bootstrap.min.css' rel='stylesheet'>"
						+"<meta name='viewport' content='width=device-width, initial-scale=1'>"
					    +"<link href='css/bootstrap-theme.min.css' rel='stylesheet'>"
					    +"<link href='css/theme.css' rel='stylesheet'></head> <body role='document'> <div class='container theme-showcase' role='main'>" );

		resp.getWriter().println("<br><hr>");

		String action = req.getParameter(ACTION);
		String type = req.getParameter(TYPE);
		String id = req.getParameter(ID);

		Objectify ofy = OfyService.ofy();

		
		view(resp, type, id, ofy);
		

		resp.getWriter().print("</div></body>");
	}



	/**
	 * @param resp
	 * @param type
	 * @param id
	 * @param ofy
	 */
	private void view(HttpServletResponse resp, String type, String id,
			Objectify ofy) {
		{
			try {
				String theClassName = "com.twyno.model." + type;
				Class theClass = Class.forName(theClassName);

				Query q = ofy.query(theClass).filter("id", Long.parseLong(id));
				Object o = q.get();

				TwynoObject to = (TwynoObject) o;
				String fields[] = to.getFields();
				
				resp.getWriter().println("<table class='table'><tr><th>Field</th><th>Value</th></tr>");
				for (String field : fields) {

					Method m = theClass.getMethod("get" + field);
					Object ret = m.invoke(o);

					resp.getWriter().print( "<tr><td>" +field + "</td><td>" + ret + "</td></tr>");

				}
				resp.getWriter().println("</table>");
			} catch (Exception e) {

				System.out.println(e);
			}

		}
	}


}
