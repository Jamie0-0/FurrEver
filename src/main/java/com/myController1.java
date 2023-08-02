package com;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  //P65  77  139頁
public class myController1 {

	@Autowired
	DataSource ds;
	
	@GetMapping("/")//P65  77  139頁
	public String myMethod1() {
		return "form1"; // p137頁 -> src/main/resources裡的templates的form1.html網頁名稱
	}
	
	@GetMapping("/form2")//P65  77  139頁
	public String myMethod2() {
		return "form2"; // p137頁 -> src/main/resources裡的templates的form1.html網頁名稱
	}

	@GetMapping("/form3")//P65  77  139頁
	@ResponseBody
	public String myMethod3() throws SQLException {
		Connection con = ds.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * from emp3");

		while (rs.next()) {
			String str1 = rs.getString(1);
			String str2 = rs.getString(2);

			System.out.print(" EMPNO= " + str1);
			System.out.print(" ENAME= " + str2);
			System.out.print("\n");
		}
		
		return String.valueOf(ds);
	}
	
	@RequestMapping("/form4")
	@ResponseBody
	public String myMethod4() throws SQLException {
		Connection con = ds.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * from emp3");
		
		StringBuffer out = new StringBuffer();
		out.append("<TABLE border='1' bordercolor='blue'>\n");

		try {
			ResultSetMetaData rsmd = rs.getMetaData();

			int numcols = rsmd.getColumnCount();

			out.append("<TR>");
			for (int i = 1; i <= numcols; i++) {
				out.append("<TH>" + rsmd.getColumnLabel(i));
			}
			out.append("</TR>\n");

			while (rs.next()) {
				out.append("<TR>");
				for (int i = 1; i <= numcols; i++) {
					out.append("<TD>");
					Object obj = rs.getObject(i);
					if (obj != null)
						out.append(obj.toString());
					else
						out.append("&nbsp;");
				}
				out.append("</TR>\n");
			}

			out.append("</TABLE>\n");
		} catch (SQLException e) {
			out.append("</TABLE><H1>ERROR:</H1> " + e.getMessage() + "\n");
		}

		return out.toString();
	}
}
