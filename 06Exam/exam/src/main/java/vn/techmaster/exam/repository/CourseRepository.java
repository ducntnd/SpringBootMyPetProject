package vn.techmaster.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.exam.model.Course;

import java.util.Optional;

@Repository

public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findByName(String name);
}
