package gift.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "product_option")
public class ProductOption {
  @Id
  @GeneratedValue
  private long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private Product product;

  @Column(length = 50, nullable = false)
  @Pattern(regexp = "^[\\w\\s\\(\\)\\[\\]\\+\\-\\&\\/]{1,50}$")
  private String option;

  @Column(nullable = false)
  private int quantity;

  public ProductOption() {}

  public ProductOption(Product product, String option, int quantity) {
    this.product = product;
    this.option = option;
    this.quantity = quantity;
  }

  public void update(Product product, String option, int quantity) {
    this.product = product;
    this.option = option;
    this.quantity = quantity;
  }

  public long getId() {
    return id;
  }
  public String getOption(){
    return option;
  }
  public int getQuantity() {
    return quantity;
  }

  public void getProduct(Product product) {
    this.product = product;
  }
}
