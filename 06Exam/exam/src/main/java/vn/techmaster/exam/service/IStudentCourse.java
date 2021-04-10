package vn.techmaster.exam.service;

import vn.techmaster.exam.model.Student;

import java.util.List;
import java.util.Map;

public interface IStudentCourse {
    Map<String, List<Student>> listStudentCourse();
}
