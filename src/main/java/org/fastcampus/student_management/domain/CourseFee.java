package org.fastcampus.student_management.domain;

public class CourseFee {

  private int fee;

  public CourseFee(final int fee) {
    checkFee(fee);
    this.fee = fee;
  }

  public void changeFee(final int fee) {
    checkFee(fee);
    this.fee = fee;
  }

  public int getFee() {
    return fee;
  }

  private static void checkFee(int fee) {
    if (fee < 0) {
      throw new IllegalArgumentException("금액은 0보다 작을 수 없습니다.");
    }
  }
}
