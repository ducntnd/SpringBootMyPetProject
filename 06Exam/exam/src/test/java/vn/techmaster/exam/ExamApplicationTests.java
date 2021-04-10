package vn.techmaster.exam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import vn.techmaster.exam.model.Course;
import vn.techmaster.exam.model.Student;
import vn.techmaster.exam.model.StudentCourse;
import vn.techmaster.exam.repository.*;

import java.awt.print.Pageable;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
//@Sql({"/student.sql","/course.sql"})
class ExamApplicationTests {
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentCourseRepo studentCourseRepo;

	@Test
	public void TestStudentCourse() {
		Student student=studentRepository.findById(1L).get();
		Course course=courseRepository.findById(1L).get();
		Course course2=courseRepository.findById(2L).get();
		Course course3=courseRepository.findById(3L).get();

		StudentCourse studentCourse=new StudentCourse();

		student.addCourse(studentCourse);
		student.setScore(7,studentCourse);
		studentRepository.save(student);
		course.addCourse(studentCourseRepo.findAll().get(studentCourseRepo.findAll().size()-1));
		courseRepository.save(course);

		StudentCourse studentCourse2=new StudentCourse();
		Student student1=studentRepository.findById(1L).get();
		student1.addCourse(studentCourse2);
		student1.setScore(5,studentCourse2);
		studentRepository.save(student1);
		course2.addCourse(studentCourseRepo.findAll().get(studentCourseRepo.findAll().size()-1));
		courseRepository.save(course2);

		StudentCourse studentCourse3=new StudentCourse();
		Student student2=studentRepository.findById(2L).get();
		student2.addCourse(studentCourse3);
		student2.setScore(9,studentCourse3);
		studentRepository.save(student2);
		course2.addCourse(studentCourseRepo.findAll().get(studentCourseRepo.findAll().size()-1));
		courseRepository.save(course2);

//		assertThat(studentCourseRepo.findAll().size()).isEqualTo(2);
	}

	@Test
	public void TestMapCourseStudent(){
		TestStudentCourse();

//		List<StudentCourse> list=studentCourseRepo.findAll();
		Map<Course,List<StudentCourse>> map= studentCourseRepo.findAll().stream().collect(Collectors.groupingBy(StudentCourse::getCourse));
		Map<String,List<Student>> result=new HashMap<>();
		for(Map.Entry<Course, List<StudentCourse>> m1:map.entrySet()){
			List<StudentCourse> list=m1.getValue();
			List<Student> students=new ArrayList<>();
			for (StudentCourse stdcourse:list){
				if(!students.contains(stdcourse.getStudent())) {
					students.add(stdcourse.getStudent());
				}
			}
			result.put(m1.getKey().getName(),students);
		}
		for (Map.Entry<String, List<Student>> map1:result.entrySet()){
			for (int i=0;i<map1.getValue().size();i++){
				System.out.println(map1.getKey()+": "+map1.getValue().get(i).getName());
			}
		}

		List<AverageCourse> averageCourses=studentCourseRepo.averageCourse(courseRepository.findByName("music").get().getId());
		for(int i=0;i<averageCourses.size();i++) {
			System.out.println(averageCourses.get(i).getAverage());
			System.out.println(averageCourses.get(i).getCourse());
		}
	}

	@Test
	public void getMathStudent(){
		TestStudentCourse();
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
		students.forEach(e-> System.out.println(e.getName()));
	}
}
