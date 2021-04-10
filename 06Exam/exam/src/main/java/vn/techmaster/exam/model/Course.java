package vn.techmaster.exam.model;
import javax.persistence.*;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity(name="course")
@Table(name="course")
public class Course {
  @Id
  private long id;

  private String name;

  @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
  private Set<StudentCourse> studentCourses=new HashSet<>();

  public void addCourse(StudentCourse studentCourse){
    studentCourses.add(studentCourse);
    studentCourse.setCourse(this);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<StudentCourse> getStudentCourses() {
    return studentCourses;
  }

  public void setStudentCourses(Set<StudentCourse> studentCourses) {
    this.studentCourses = studentCourses;
  }
}
