package org.fastcampus.student_management;

import org.fastcampus.student_management.application.course.CourseService;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.application.student.dto.StudentInfoDto;
import org.fastcampus.student_management.repo.CourseRepository;
import org.fastcampus.student_management.repo.StudentRepository;
import org.fastcampus.student_management.ui.UserInputType;
import org.fastcampus.student_management.ui.course.CourseController;
import org.fastcampus.student_management.ui.course.CoursePresenter;
import org.fastcampus.student_management.ui.student.StudentController;
import org.fastcampus.student_management.ui.student.StudentPresenter;

public class Main {

  public static void main(String[] args) {
    StudentRepository studentRepository = new StudentRepository();
    CourseRepository courseRepository = new CourseRepository();

    StudentService studentService = new StudentService(studentRepository);
    CourseService courseService = new CourseService(courseRepository, studentService);

    CoursePresenter coursePresenter = new CoursePresenter();
    StudentPresenter studentPresenter = new StudentPresenter();

    CourseController courseController = new CourseController(coursePresenter, courseService,
        studentPresenter);
    StudentController studentController = new StudentController(studentPresenter, studentService);

    addTestData(studentService, courseService);

    studentPresenter.showMenu();
    UserInputType userInputType = studentController.getUserInput();
    while (userInputType != UserInputType.EXIT) {
      switch (userInputType) {
        case NEW_STUDENT:
          studentController.registerStudent();
          break;
        case NEW_COURSE:
          courseController.registerCourse();
          break;
        case SHOW_COURSE_DAY_OF_WEEK:
          courseController.showCourseDayOfWeek();
          break;
        case ACTIVATE_STUDENT:
          studentController.activateStudent();
          break;
        case DEACTIVATE_STUDENT:
          studentController.deactivateStudent();
          break;
        case CHANGE_FEE:
          courseController.changeFee();
          break;
        default:
          studentPresenter.showErrorMessage();
          break;
      }
      studentPresenter.showMenu();
      userInputType = studentController.getUserInput();
    }
  }

  private static void addTestData(StudentService studentService, CourseService courseService) {
    // add default test dataset
    StudentInfoDto student1 = new StudentInfoDto("KIM", 28, "Seoul");
    StudentInfoDto student2 = new StudentInfoDto("LEE", 33, "Suwon");

    studentService.saveStudent(student1);
    studentService.saveStudent(student2);

    CourseInfoDto student1Course1 = new CourseInfoDto("math1", 300000, "MONDAY", "KIM", 10L);
    CourseInfoDto student1Course2 = new CourseInfoDto("math2", 300000, "FRIDAY", "KIM", 10L);

    courseService.registerCourse(student1Course1);
    courseService.registerCourse(student1Course2);

    CourseInfoDto student2Course1 = new CourseInfoDto("fine art1", 20000, "SUNDAY", "LEE", 13L);
    CourseInfoDto student2Course2 = new CourseInfoDto("math3", 300000, "FRIDAY", "LEE", 10L);

    courseService.registerCourse(student2Course1);
    courseService.registerCourse(student2Course2);
  }
}