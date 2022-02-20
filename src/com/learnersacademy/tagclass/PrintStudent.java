package com.learnersacademy.tagclass;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import com.learnersacademy.model.StudentObj;

public class PrintStudent extends SimpleTagSupport{
	
	private List<StudentObj> studentList;

	public void setStudentList(List<StudentObj> studentList) {
		this.studentList = studentList;
	}
	
	@Override
	public void doTag() throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		JspWriter out = getJspContext().getOut();
		String headings[]= {"ID","Name","Date of Birth", "Class ID"};
		
		out.print("<table border='1px' style='text-align:center; margin-left:auto;margin-right:auto;'>");		out.print("<tr>");
		for (String heading : headings) {
			out.print("<th>" + heading + "</th>");
		}
		out.print("</tr>");
		
		for(StudentObj std:studentList)
		{	
			out.print("<tr>");
			out.print("<form action='student' method='post'>");
			out.print("<td><input type='hidden' name='studentId_delete' value='" +std.getStudentId() + "'/>"+std.getStudentId()+"</td>");
			out.print("<td>"+std.getStudentName()+"</td>");
			out.print("<td>"+format.format(std.getStudentDOB())+"</td>");
			out.print("<td>"+std.getClassID()+"</td>");
			out.print("<td><input type='submit' value='Delete'/></td>");
			out.print("</tr>");
			out.print("</form>");
			
		}
		
		out.print("</table>");
		
	}

}
