package gift.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 15)
  private String name;

  @Column(nullable = false)
  private int price;

  @Column(name = "image_url", nullable = false)
  private String imageUrl;

  public Product() {
  }

  public Product(Long id, String name, int price, String imageUrl) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public boolean hasProhibitedName() {
    return name != null && name.contains("카카오");
  }

  public String prohibitedMessage() {
    return "'카카오'는 담당 MD 협의 시에만 사용할 수 있습니다.";
  }

  public void update(String name, int price, String imageUrl) {
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductOption> options = new ArrayList<ProductOption>();


  public void addOption(ProductOption option) {
    boolean duplicated = options.stream()
        .anyMatch(o -> o.getOption().equals(option.getOption()));
    if (duplicated) {
      throw new IllegalArgumentException("동일한 이름의 옵션은 추가할 수 없습니다.");
    }
    options.add(option);
  }
}
