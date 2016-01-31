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
public class TwynoServlet extends HttpServlet {

	private static final String ACTION_LIST = "list";
	private static final String ACTION_FORM = "form";
	private static final String ID = "i";
	private static final String TYPE = "t";
	private static final String ACTION_DELETE_ALL = "deleteAll";
	private static final String ACTION_DELETE = "delete";
	private static final String ACTION_SAVE = "save";
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
		for (String theClass : getClasses()) {

			resp.getWriter().println(
					"<a class='btn btn-primary' href='/twyno?a=list&t=" + theClass + "'>" + theClass
							+ "</a>");
		}
		resp.getWriter().println("<br><hr>");

		String action = req.getParameter(ACTION);
		String type = req.getParameter(TYPE);
		String id = req.getParameter(ID);

		Objectify ofy = OfyService.ofy();

		if (ACTION_SAVE.equals(action))
			save(req, resp, type, id, ofy);
		
		else if (ACTION_DELETE_ALL.equals(action))
			deleteAll(resp, ofy);
		
		else if (ACTION_DELETE.equals(action))
			delete(resp, type, id, ofy);
		
		else if (ACTION_VIEW.equals(action))
			view(resp, type, id, ofy);
		
		else if (ACTION_FORM.equals(action))
			form(resp, type, id, ofy);
		
		else if (ACTION_LIST.equals(action))
			list(resp, type, ofy);
		
		else {
			resp.getWriter().print("No action executed");
		}

		resp.getWriter().print("</div></body>");
	}

	/**
	 * @param req
	 * @param resp
	 * @param type
	 * @param id
	 * @param ofy
	 * @throws IOException
	 */
	private void save(HttpServletRequest req, HttpServletResponse resp,
			String type, String id, Objectify ofy) throws IOException {
		{

			try {
				String theClassName = "com.twyno.model." + type;
				Class theClass = Class.forName(theClassName);

				Object o;
				if (id != null) {
					Query q = ofy.query(theClass).filter("id",
							Long.parseLong(id));
					o = q.get();
				} else
					o = theClass.newInstance();

				TwynoObject to = (TwynoObject) o;
				String fields[] = to.getFields();

				resp.getWriter().println(
						"<a class='btn btn-sm btn-warning' href='/twyno?a=list&t=" + type + "'>List</a><br>");
				for (String field : fields) {

					// Identify the type of the field
					String fieldname = lowerCaseFirstCharacter( field); 
					Type theType = theClass.getDeclaredField( fieldname).getGenericType();
					
					
					if ( theType.toString().contains( "String")) {
						Method m = theClass.getMethod("set" + field, String.class);
						Object ret = m.invoke(o, req.getParameter(field));
					}
					else if ( theType.toString().contains( "Boolean")) {
						Method m = theClass.getMethod("set" + field, Boolean.class);
						
						Object ret;
						if ( req.getParameter(field).equals( "true") )
							ret = m.invoke(o, new Boolean(true));
						else
							ret = m.invoke(o, new Boolean(false));
					}
				}

				ofy.put(o);
			} catch (Exception e) {
				e.printStackTrace();
			}

			resp.getWriter().print("Object saveed.<br>");
		}
	}
	
	private String lowerCaseFirstCharacter( String str) {
		str = Character.toLowerCase(
				  str.charAt(0)) + (str.length() > 1 ? str.substring(1) : "");
		return str;
	}

	/**
	 * @param resp
	 * @param ofy
	 * @throws IOException
	 */
	private void deleteAll(HttpServletResponse resp, Objectify ofy)
			throws IOException {
		{

			List<Restaurant> ol = ofy.query(Restaurant.class).list();
			for (Restaurant o : ol)
				ofy.delete(o);

			resp.getWriter().print("All objects deleted.");
		}
	}

	/**
	 * @param resp
	 * @param type
	 * @param id
	 * @param ofy
	 */
	private void delete(HttpServletResponse resp, String type, String id,
			Objectify ofy) {
		{

			try {
				String theClassName = "com.twyno.model." + type;
				Class theClass = Class.forName(theClassName);

				Query q = ofy.query(theClass).filter("id", Long.parseLong(id));
				Object o = q.get();

				ofy.delete(o);

				resp.getWriter().print("Objects deleted.");
			} catch (Exception e) {

				System.out.println(e);
			}
		}
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

				resp.getWriter().println(
						"<a class='btn btn-sm btn-warning' href='/twyno?a=list&t=" + type + "'>List</a>");
				resp.getWriter().println(
						"<a class='btn btn-sm btn-warning' href='/twyno?a=form&t=" + type + "&i=" + id
								+ "'>Edit</a>");
				resp.getWriter().println(
						"<a class='btn btn-sm btn-warning' href='/twyno?a=delete&t=" + type + "&i=" + id
								+ "'>Delete</a><br><br>");
				
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

	/**
	 * @param resp
	 * @param type
	 * @param id
	 * @param ofy
	 */
	private void form(HttpServletResponse resp, String type, String id,
			Objectify ofy) {
		{
			try {

				String theClassName = "com.twyno.model." + type;
				Class theClass = Class.forName(theClassName);

				Object o;
				if (id != null) {
					Query q = ofy.query(theClass).filter("id",
							Long.parseLong(id));
					o = q.get();
				} else
					o = theClass.newInstance();

				TwynoObject to = (TwynoObject) o;
				String fields[] = to.getFields();

				resp.getWriter().println(
						"<a class='btn btn-sm btn-warning' href='/twyno?a=list&t=" + type + "'>List</a>");
				resp.getWriter().println("<form method='get'><table>");
				resp.getWriter().print(
						"<input name='a' type='hidden' value='save'>");
				resp.getWriter().print(
						"<input name='t' type='hidden' value='" + type + "'>");
				if (id != null)
					resp.getWriter()
							.print("<input name='i' type='hidden' value='" + id
									+ "'>");

				for (String field : fields) {

					Method m = theClass.getMethod("get" + field);
					Object ret = m.invoke(o);
					if (ret == null) ret = "";

					resp.getWriter().print(
							"<tr><td>" + field + "</td><td><input name='"
									+ field + "' type='text' value='" + ret
									+ "'></td></tr>");

				}

				resp.getWriter().println("</table>");
				resp.getWriter().println("<input type='submit' value='Save'></form>");
			} catch (Exception e) {

				System.out.println(e);
			}

		}
	}

	/**
	 * @param resp
	 * @param type
	 * @param ofy
	 */
	private void list(HttpServletResponse resp, String type, Objectify ofy) {
		{
			try {
				String theClassName = "com.twyno.model." + type;
				Class theClass = Class.forName(theClassName);

				Query q = ofy.query(theClass);
				Iterator<TwynoObject> iter = q.iterator();

				resp.getWriter().println(
						"<a class='btn btn-sm btn-warning' href='/twyno?a=form&t=" + type + "'>Add new</a><br><br>");
				resp.getWriter().println("<table class='table'><tr><th>Id</th><th>Name</th></tr>");
				while (iter.hasNext()) {
					TwynoObject to = iter.next();

					resp.getWriter().println(
							"<tr><td><a href='/twyno?t=" + type + "&i="
									+ to.getId() + "&a=view'>" + to.getId()
									+ "</a></td><td>" + to.getName()
									+ "</td></tr>");

				}
				resp.getWriter().println("</table>");

			} catch (Exception e) {

				System.out.println(e);
			}

		}
	}

	public String[] getClasses() {
		return new String[] { "Restaurant", "Attraction", "PublicBBQPlace" };
	}
}
