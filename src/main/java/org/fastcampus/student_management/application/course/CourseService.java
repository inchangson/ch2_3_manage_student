package org.fastcampus.student_management.application.course;

import java.util.List;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.CourseList;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.CourseRepository;

public class CourseService {

  private final CourseRepository courseRepository;
  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(),
        courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    List<Course> courseDayOfWeek = courseRepository.getCourseDayOfWeek(dayOfWeek)
        .stream()
        .filter(Course::isActivateUser)
        .toList();
    return courseDayOfWeek.stream()
        .map(CourseInfoDto::new)
        .toList();
  }

  public void changeFee(String studentName, int fee) {
    List<Course> courseListByStudent = courseRepository.getCourseListByStudent(studentName)
        .stream()
        .filter(Course::isActivateUser)
        .toList();
    if (courseListByStudent.isEmpty()) {
      throw new IllegalArgumentException("요청정보에 해당하는 활성화 상태의 학생이 없습니다.");
    }

    CourseList courseList = new CourseList(courseListByStudent);
    courseList.changeAllCoursesFee(fee);
  }
}
