package iii;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/employees/*")//對應servletpath  "/"環境跟目錄
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String[][] employees = {
			{ "1", "Nancy", "Davolio", "Sales Representative", "1948-12-08", "1992-05-01", "507 - 20th Ave. E. Apt. 2A",
					"Seattle" },
			{ "2", "Andrew", "Fuller", "Vice President, Sales", "1952-02-19", "1992-08-14", "908 W. Capital Way",
					"Tacoma" },
			{ "3", "Janet", "Leverling", "Sales Representative", "1963-08-30", "1992-04-01", "722 Moss Bay Blvd.",
					"Kirkland" },
			{ "4", "Margaret", "Peacock", "Sales Representative", "1937-09-19", "1993-05-03", "4110 Old Redmond Rd.",
					"Redmond" },
			{ "5", "Steven", "Buchanan", "Sales Manager", "1955-03-04", "1993-10-17", "14 Garrett Hill", "London" },
			{ "6", "Michael", "Suyama", "Sales Representative", "1963-07-02", "1993-10-17", "Coventry House Miner Rd.",
					"London" },
			{ "7", "Robert", "King", "Sales Representative", "2015-03-11", "1994-01-02",
					"Edgeham Hollow Winchester Way", "London" },
			{ "8", "Laura", "Callahan", "Inside Sales Coordinator", "2015-03-19", "1994-03-05", "4726 - 11th Ave. N.E.",
					"Seattle" },
			{ "9", "anne", "Dodsworth", "Sales Representative", "2015-03-04", "1994-11-15", "7 Houndstooth Rd.",
					"London" } };
	private static String[] empColName = { "員工編號", "名字", "姓氏", "職稱", "生日", "到職日", "地址-街道", "地址-市鎮" };
							//<很多員工陣列<"姓名"，"NANCY"><"姓氏"，"Davolio">>
	private static ArrayList<LinkedHashMap<String, String>> emps = new ArrayList<>();//2維振烈
	static {
		for(int i =0;i<employees.length;i++) {
			LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
			for(int j =0;j<employees[i].length;j++) {
				map.put(empColName[j],employees[i][j]);
			}
			emps.add(map);
		}
		
	}

	private String getEmpsJsonString() throws JSONException {
		JSONArray ary = new JSONArray();
		for(LinkedHashMap<String,String> emp:emps) {
			JSONObject obj = new JSONObject();
			Set<String> keySet=emp.keySet();
			for(String key:keySet) {
				obj.put(key, emp.get(key));
				
			}
			ary.put(obj);
		}
		
		
		
		return ary.toString();
	}

	// 查詢
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");//回應JSON格式
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();

		String id = request.getPathInfo();
		if (id == null) { // 如: /api/employees (ContextPath=>/api ServletPath=>/employees PathInfo=>null)
			try {
				out.printf(getEmpsJsonString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return;
		}

		id = id.replaceFirst("/", "");
		if ("".equals(id) // 如: /api/employees/
				|| "*".equals(id)) { // 如: /api/employees/*
			try {
				out.printf(getEmpsJsonString());
			} catch (JSONException ex) {
				ex.printStackTrace();
			}

		} else if (id.matches("\\d+")) { // 如:/api/employees/3

			for (LinkedHashMap<String, String> emp : emps) {

				if (id.equals(emp.get("員工編號"))) {// 找到符合指定員工編號的員工資料
					JSONObject obj = new JSONObject();
					for (Map.Entry<String, String> entry : emp.entrySet()) {
						try {
							obj.put(entry.getKey(), entry.getValue());
						} catch (JSONException ex) {
							ex.printStackTrace();
						}
					}
					out.printf("%s", obj.toString());
					return;
				}

			}
			out.printf("{}"); // 找不到指定員工編號的員工資料 如:/api/employees/99

		} else {
			out.printf("null"); // 如:/api/employees/abc
		}
	}

	// 新增
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		try {
			LinkedHashMap<String, String> emp = new LinkedHashMap<>();
			emp.put("員工編號", request.getParameter("employeeid"));
			emp.put("名字", request.getParameter("firstname"));
			emp.put("姓氏", request.getParameter("lastname"));
			emp.put("職稱", request.getParameter("title"));
			emp.put("生日", request.getParameter("birthdate"));
			emp.put("到職日", request.getParameter("hiredate"));
			emp.put("地址-街道", request.getParameter("address"));
			emp.put("地址-市鎮", request.getParameter("city"));
			emps.add(emp);
			out.printf("{ \"insert-status\": \"success\" }");
		} catch (Exception ex) {
			out.printf("{ \"insert-status\": \"fail\" }");
		}
	}

	// 修改
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();		
	
		String id = request.getPathInfo();
		if (id != null ) {
			 id = id.replaceFirst("/", "");			 
			 if( id.matches("\\d+")){  //如:/api/employees/3
				
				 for (LinkedHashMap<String, String> emp : emps) {				

						if (id.equals(emp.get("員工編號"))) {//找到符合指定員工編號的員工資料							
							emp.put("名字", request.getParameter("firstname"));
							emp.put("姓氏", request.getParameter("lastname"));
							emp.put("職稱", request.getParameter("title"));
							emp.put("生日", request.getParameter("birthdate"));
							emp.put("到職日", request.getParameter("hiredate"));
							emp.put("地址-街道", request.getParameter("address"));
							emp.put("地址-市鎮", request.getParameter("city"));								
							out.printf("{ \"update-status\": \"success\" }");
							return;
						}
				 }
				 out.printf("{ \"update-status\": \"fail\" }"); //如:/api/employees/99
			 }
			 else {
				 out.printf("{ \"update-status\": \"fail\" }"); //如:/api/employees/abc
			 }
		}
		else {		
			out.printf("{ \"update-status\": \"fail\" }"); //如:/api/employees
		}
	}

	//Chrome對於跨域的PUT請求的特殊處理
	//-------------------------------------------------------------//
	//Chrome will preflight the request to look for CORS headers. If the request is acceptable, it will then send the real request. 
	//"Preflighted" requests first send an HTTP request by the OPTIONS method to the resource on the other domain, in order to determine whether the actual request is safe to send. 
	//In particular, a request is preflighted if:
    //(1) It uses methods other than GET, HEAD or POST. 
	//(2) If POST is used to send request data with a Content-Type other than application/x-www-form-urlencoded, multipart/form-data, or text/plain, then the request is preflighted.
   	protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {	    
	    resp.setHeader("Access-Control-Allow-Origin", "*");
	    resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//略
	}

}
