package org.fastcampus.student_management.domain;

import java.util.Objects;

public class Student {

  private final String name;
  private final int age;
  private final String address;
  private boolean activated;

  public Student(String name, int age, String address) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("이름은 필수 입력값입니다.");
    }

    this.name = name;
    this.age = age;
    this.address = address;
    this.activated = true;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getAddress() {
    return address;
  }

  public boolean isActivate() {
    return activated;
  }

  public void activate() {
    if (activated) {
      throw new IllegalStateException("이미 활성화된 학생입니다.");
    }
    this.activated = true;
  }

  public void deactivate() {
    if (!activated) {
      throw new IllegalStateException("이미 비활성화된 학생입니다.");
    }
    this.activated = false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Student student = (Student) o;

    return name.equals(student.name) &&
        age == student.age &&
        address.equals(student.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age, address, activated);
  }
}
