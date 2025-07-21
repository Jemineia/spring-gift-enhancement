package gift.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ProductOptionDto {

  @NotBlank(message = "옵션명을 입력해주세요.")
  @Pattern(regexp = "^[가-힣a-zA-Z0-9 \\(\\)\\[\\]\\+\\-\\&/_]+$", message = "옵션명은 특수문자를 제한하며 50자 이하여야 합니다.")
  private String option;

  @Min(value = 1, message = "수량은 1 이상이어야 합니다.")
  @Max(value = 99999999, message = "옵션 수량은 1억 미만이어야 합니다.")
  private int quantity;

  public String getOption() {
    return option;
  }

  public void setOption(String option) {
    this.option = option;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
