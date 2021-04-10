package vn.techmaster.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.techmaster.exam.model.Student;
import java.util.List;

@Repository

public interface StudentRepository extends JpaRepository<Student,Long> {
//    @Query("SELECT * from student_course s where s.")
//    List<Student> mathStudents();
}
