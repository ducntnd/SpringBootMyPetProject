package vn.techmaster.exam.model;
import javax.persistence.*;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity(name="student")
@Table(name="student")
public class Student {
  @Id
  private long id;

  private String name;

  @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
  Set<StudentCourse> studentCourses=new HashSet<>();

  public void addCourse(StudentCourse studentCourse){
    studentCourses.add(studentCourse);
    studentCourse.setStudent(this);
  }

  public void setScore(int score,StudentCourse studentCourse){
    studentCourse.setScore(score);
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
