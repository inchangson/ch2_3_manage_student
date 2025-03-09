package org.fastcampus.student_management.ui.student;

import java.util.Scanner;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.application.student.dto.StudentInfoDto;
import org.fastcampus.student_management.ui.UserInputType;

public class StudentController {

  private final Scanner scanner = new Scanner(System.in);
  private final StudentPresenter studentPresenter;
  private final StudentService studentService;

  public StudentController(StudentPresenter studentPresenter, StudentService studentService) {
    this.studentPresenter = studentPresenter;
    this.studentService = studentService;
  }

  public UserInputType getUserInput() {
    while (true) {
      try {
        int value = Integer.parseInt(scanner.nextLine());
        return UserInputType.of(value);
      } catch (NumberFormatException e) {
        studentPresenter.showInvalidInputMessage();
      }
    }
  }

  public void registerStudent() {
    String name = getStudentName();
    int age = getStudentAge();
    String address = getStudentAddress();

    StudentInfoDto dto = new StudentInfoDto(name, age, address);
    studentService.saveStudent(dto);

    studentPresenter.showStudentRegistered();
  }

  public void activateStudent() {
    String name = getStudentName();
    try {
      studentService.activateStudent(name);
    } catch (IllegalArgumentException | IllegalStateException e) {
      studentPresenter.showInvalidInputMessage(e.getMessage());
      return;
    }
    studentPresenter.showStudentActivated();
  }

  public void deactivateStudent() {
    String name = getStudentName();
    try {
      studentService.deactivateStudent(name);
    } catch (IllegalArgumentException | IllegalStateException e) {
      studentPresenter.showInvalidInputMessage(e.getMessage());
      return;
    }
    studentPresenter.showStudentDeactivated();
  }

  private String getStudentName() {
    studentPresenter.showInputStudentName();
    return scanner.nextLine();
  }

  private int getStudentAge() {
    while (true) {
      studentPresenter.showInputStudentAge();
      try {
        return Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        studentPresenter.showInvalidInputMessage();
      }
    }
  }

  private String getStudentAddress() {
    studentPresenter.showInputStudentAddress();
    return scanner.nextLine();
  }
}
