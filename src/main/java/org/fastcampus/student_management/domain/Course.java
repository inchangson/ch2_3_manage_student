package org.fastcampus.student_management.domain;

import java.util.Objects;

public class Course {

  private final Student student;
  private final String courseName;
  private final DayOfWeek dayOfWeek;
  private final Long courseTime;
  private final CourseFee fee;

  public Course(Student student, String courseName, int fee, DayOfWeek dayOfWeek, Long courseTime) {
    if (student == null) {
      throw new IllegalArgumentException("학생은 필수 입력값입니다.");
    }

    this.student = student;
    this.courseName = courseName;
    this.fee = new CourseFee(fee);
    this.dayOfWeek = dayOfWeek;
    this.courseTime = courseTime;
  }

  public String getCourseName() {
    return courseName;
  }

  public boolean isSameDay(DayOfWeek dayOfWeek) {
    return this.dayOfWeek.equals(dayOfWeek);
  }

  public boolean isActivateUser() {
    return student.isActivate();
  }

  public String getStudentName() {
    return student.getName();
  }

  public int getFee() {
    return fee.getFee();
  }

  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }

  public Long getCourseTime() {
    return courseTime;
  }

  public void changeFee(int fee) {
    if (DayOfWeek.SATURDAY.equals(this.dayOfWeek) || DayOfWeek.SUNDAY.equals(this.dayOfWeek)) {
      fee = (int) Math.round(fee * 1.5);
    }
    this.fee.changeFee(fee);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    
    Course course = (Course) o;
    
    return courseName.equals(course.courseName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseName);
  }
}
