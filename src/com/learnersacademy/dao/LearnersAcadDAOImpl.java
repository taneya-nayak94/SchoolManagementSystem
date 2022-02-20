package com.learnersacademy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbutil.OracleConnection;
import com.exception.BusinessException;
import com.learnersacademy.model.ClassObj;
import com.learnersacademy.model.StudentObj;
import com.learnersacademy.model.SubjectObj;
import com.learnersacademy.model.TeacherObj;

public class LearnersAcadDAOImpl implements LearnersAcadDAO {

	@Override
	public boolean userLogin(String username, String password) throws BusinessException {
		Boolean bool = false;
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "select 1 from tab_user where username=? and password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			System.out.println(username + " --> " + password);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				bool = true;
				return bool;
			} else {
				throw new BusinessException("Username and Password dont match");
			}

		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		} catch (SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

	@Override
	public List<SubjectObj> listSubject() throws BusinessException {
		List<SubjectObj> subList = new ArrayList<SubjectObj>();

		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "select sub_name,sub_id,sub_lang,class_id,teacher_id from tab_subject";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				SubjectObj subject = new SubjectObj();
				System.out.println(result.getInt("sub_id"));
				subject.setClassId(result.getInt("class_id"));
				subject.setSubjectID(result.getInt("sub_id"));
				subject.setSubLang(result.getString("sub_lang"));
				subject.setSubName(result.getString("sub_name"));
				subject.setTeacherId(result.getInt("teacher_id"));
				subList.add(subject);
			}

			return subList;
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		} catch (SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

	@Override
	public List<ClassObj> listClasses() throws BusinessException {
		List<ClassObj> classList = new ArrayList<ClassObj>();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "select class_id,division,std from tab_class";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				ClassObj cls = new ClassObj();

				cls.setClassID(result.getInt("class_id"));
				cls.setDivision(result.getString("division"));
				cls.setStandard(result.getInt("std"));
				classList.add(cls);
			}

			return classList;
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		} catch (SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

	@Override
	public List<TeacherObj> listTeacher() throws BusinessException {
		List<TeacherObj> teacherList = new ArrayList<TeacherObj>();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "select teacher_id,teacher_name,teacher_category,experience from tab_teacher";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				TeacherObj teacher = new TeacherObj();

				teacher.setID(result.getInt("teacher_id"));
				teacher.setTeacherCategory(result.getString("teacher_category"));
				teacher.setTeacherName(result.getString("teacher_name"));
				teacher.setExperience(result.getInt("experience"));

				teacherList.add(teacher);
			}

			return teacherList;
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		} catch (SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

	@Override
	public List<StudentObj> listStudents() throws BusinessException {
		List<StudentObj> studentList = new ArrayList<StudentObj>();
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "select student_id,student_name,student_dob,class_id from tab_student";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				StudentObj student = new StudentObj();

				student.setStudentId(result.getInt("student_id"));
				student.setClassID(result.getInt("class_id"));
				student.setStudentDOB(result.getDate("student_dob"));
				student.setStudentName(result.getString("student_name"));

				studentList.add(student);

			}

			return studentList;
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		} catch (SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

	@Override
	public boolean deleteSubject(Integer subjectID) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "delete from tab_subject where sub_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, subjectID);
			int c = preparedStatement.executeUpdate();
			if (c != 1) {
				throw new BusinessException("Deletion Failed");
			} else {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

	@Override
	public boolean deleteClass(Integer classID) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "delete from tab_class where class_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, classID);
			int c = preparedStatement.executeUpdate();
			if (c != 1) {
				throw new BusinessException("Deletion Failed");
			} else {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

	@Override
	public boolean deleteTeacher(Integer teacherID) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "delete from tab_teacher where teacher_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, teacherID);
			int c = preparedStatement.executeUpdate();
			if (c != 1) {
				throw new BusinessException("Deletion Failed");
			} else {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

	@Override
	public boolean deleteStudent(Integer studentID) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "delete from tab_student where student_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, studentID);
			int c = preparedStatement.executeUpdate();
			if (c != 1) {
				throw new BusinessException("Deletion Failed");
			} else {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

	@Override
	public boolean addSubject(SubjectObj subject) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "insert into tab_subject (sub_name,sub_id,sub_lang,class_id,teacher_id) values (?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, subject.getSubName());
			preparedStatement.setInt(2, subject.getSubjectID());
			preparedStatement.setString(3, subject.getSubLang());
			preparedStatement.setInt(4, subject.getClassId());
			preparedStatement.setInt(5, subject.getTeacherId());
			int c = preparedStatement.executeUpdate();
			if (c != 1) {
				throw new BusinessException("Insertion Failed");
			} else {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

	@Override
	public boolean addClass(ClassObj cls) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "insert into tab_class (std,division,class_id) values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cls.getStandard());
			preparedStatement.setString(2, cls.getDivision());
			preparedStatement.setInt(3, cls.getClassID());
			int c = preparedStatement.executeUpdate();
			if (c != 1) {
				throw new BusinessException("Insertion Failed");
			} else {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

	@Override
	public boolean addTeacher(TeacherObj teacher) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "insert into tab_teacher (teacher_name,teacher_category,experience,teacher_id) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teacher.getTeacherName());
			preparedStatement.setString(2, teacher.getTeacherCategory());
			preparedStatement.setInt(3, teacher.getExperience());
			preparedStatement.setInt(4, teacher.gettID());
			int c = preparedStatement.executeUpdate();
			if (c != 1) {
				throw new BusinessException("Insertion Failed");
			} else {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

	@Override
	public boolean addStudent(StudentObj student) throws BusinessException {
		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "insert into tab_student(student_id,student_name,student_dob,class_id) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, student.getStudentId());
			preparedStatement.setString(2, student.getStudentName());
			preparedStatement.setDate(3, student.getStudentDOB());
			preparedStatement.setInt(4, student.getClassID());
			int c = preparedStatement.executeUpdate();
			if (c != 1) {
				throw new BusinessException("Insertion Failed");
			} else {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

	@Override
	public List<Object> generateReport(ClassObj cls) throws BusinessException {
		List<Object> reportList = new ArrayList<Object>();

		try (Connection connection = OracleConnection.getConnection()) {
			String sql = "select sub_name,sub_id,sub_lang,class_id,teacher_id from tab_subject where class_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cls.getClassID());
			ResultSet resultSubject = preparedStatement.executeQuery();

			while (resultSubject.next()) {
				SubjectObj subject = new SubjectObj();
				subject.setClassId(resultSubject.getInt("class_id"));
				subject.setSubjectID(resultSubject.getInt("sub_id"));
				subject.setSubLang(resultSubject.getString("sub_lang"));
				subject.setSubName(resultSubject.getString("sub_name"));
				subject.setTeacherId(resultSubject.getInt("teacher_id"));
				reportList.add(subject);
			}

			String sql2 = "select teacher_id,teacher_name,teacher_category,experience from tab_teacher tt where "
					+ "tt.teacher_id in (select ts.teacher_id from tab_subject ts where ts.class_id=?)";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			preparedStatement2.setInt(1, cls.getClassID());
			ResultSet resultTeacher = preparedStatement2.executeQuery();

			while (resultTeacher.next()) {
				TeacherObj teacher = new TeacherObj();

				teacher.setID(resultTeacher.getInt("teacher_id"));
				teacher.setTeacherCategory(resultTeacher.getString("teacher_category"));
				teacher.setTeacherName(resultTeacher.getString("teacher_name"));
				teacher.setExperience(resultTeacher.getInt("experience"));
				reportList.add(teacher);
			}
			String sql3 = "select student_id,student_name,student_dob,class_id from tab_student where class_id=?";
			PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
			preparedStatement3.setInt(1, cls.getClassID());
			ResultSet resultStudent = preparedStatement3.executeQuery();

			while (resultStudent.next()) {
				StudentObj student = new StudentObj();

				student.setStudentId(resultStudent.getInt("student_id"));
				student.setClassID(resultStudent.getInt("class_id"));
				student.setStudentDOB(resultStudent.getDate("student_dob"));
				student.setStudentName(resultStudent.getString("student_name"));
				reportList.add(student);

			}

			return reportList;
		} catch (ClassNotFoundException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		} catch (SQLException e) {
			throw new BusinessException("Internal Error contact sysadmin with error message " + e);
		}
	}

}
