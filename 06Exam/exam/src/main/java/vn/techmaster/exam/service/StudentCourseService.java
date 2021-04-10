package vn.techmaster.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import vn.techmaster.exam.model.Course;
import vn.techmaster.exam.model.Student;
import vn.techmaster.exam.model.StudentCourse;
import vn.techmaster.exam.repository.AverageCourse;
import vn.techmaster.exam.repository.CourseRepository;
import vn.techmaster.exam.repository.StudentCourseRepo;
import vn.techmaster.exam.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentCourseService implements IStudentCourse{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentCourseRepo studentCourseRepo;



    @Override
    public Map<String, List<Student>> listStudentCourse() {
        Map<Course,List<StudentCourse>> map=studentCourseRepo.findAll().stream().collect(Collectors.groupingBy(StudentCourse::getCourse));
        Map<String,List<Student>> result=new HashMap<>();
            for(Map.Entry<Course, List<StudentCourse>> m1:map.entrySet()){
                List<StudentCourse> list=m1.getValue();
                List<Student> students=new ArrayList<>();
                for (StudentCourse studentCourse:list){
                    if(!students.contains(studentCourse.getStudent())) {
                        students.add(studentCourse.getStudent());
                    }
                }
                result.put(m1.getKey().getName(),students);
            }
        return result;
    }

    public List<Student> getMathStudent(){
        List<Set<StudentCourse>> Mathcourses= courseRepository.findAll().stream().filter(v->v.getName().equals("math")).map(Course::getStudentCourses)
                .collect(Collectors.toList());
        List<Set<StudentCourse>> Musiccourses= courseRepository.findAll().stream().filter(v->v.getName().equals("music")).map(Course::getStudentCourses)
                .collect(Collectors.toList());
        List<Student> students=new ArrayList<>();
        for(int i=0;i<Mathcourses.size();i++){
            for (StudentCourse studentCourse:Mathcourses.get(i)){
                if(!students.contains(studentCourse.getStudent())){
                    students.add(studentCourse.getStudent());
                }
            }
        }
        for(int i=0;i<Musiccourses.size();i++){
            for (StudentCourse studentCourse:Musiccourses.get(i)){
                if(students.contains(studentCourse.getStudent())){
                    students.remove(studentCourse.getStudent());
                }
            }
        }
        return students;
    }
}
