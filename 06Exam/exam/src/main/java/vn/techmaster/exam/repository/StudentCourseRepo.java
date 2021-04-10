package vn.techmaster.exam.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.techmaster.exam.model.Course;
import vn.techmaster.exam.model.Student;
import vn.techmaster.exam.model.StudentCourse;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentCourseRepo extends JpaRepository<StudentCourse,Long> {
    @Query(value = "SELECT course_id AS course,AVG(score) AS average "+
    "FROM student_course sc"+ " WHERE sc.course_id=:id GROUP BY sc.course_id",nativeQuery = true)
    List<AverageCourse> averageCourse(@Param("id") long id);
}
