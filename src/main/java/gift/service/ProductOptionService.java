package gift.service;

import gift.exception.DuplicateOptionException;
import gift.exception.InsufficientStockException;
import gift.model.Product;
import gift.model.ProductOption;
import gift.repository.ProductOptionRepository;
import gift.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductOptionService {

  private final ProductOptionRepository productOptionRepository;
  private final ProductRepository productRepository;

  public ProductOptionService(ProductOptionRepository productOptionRepository,
      ProductRepository productRepository) {
    this.productOptionRepository = productOptionRepository;
    this.productRepository = productRepository;
  }

  public List<ProductOption> findOptionsByProductId(Long productId) {
    return productOptionRepository.findByProductId(productId);
  }

  public ProductOption findById(Long optionId) {
    return productOptionRepository.findById(optionId)
        .orElseThrow(() -> new EntityNotFoundException("해당 옵션을 찾을 수 없습니다"));
  }

  public void validateDuplicateOption(Long productId, String optionName) {
    boolean exists = productOptionRepository.existsByProductIdAndOption(productId, optionName);
    if (exists) {
      throw new DuplicateOptionException("같은 상품에 이미 존재하는 옵션명입니다.");
    }
  }

  @Transactional
  public ProductOption save(Long productId, String option, int quantity) {

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new EntityNotFoundException("상품을 찾을 수 없습니다"));

    validateDuplicateOption(productId, option);

    ProductOption productOption = new ProductOption(product, option, quantity);

    return productOptionRepository.save(productOption);
  }


  @Transactional
  public void decreaseQuantity(Long optionId, int amount) {
    if (amount <= 0) {
      throw new InsufficientStockException("차감 수량은 1 이상이어야 합니다.", optionId);
    }

    int updatedRows = productOptionRepository.decreaseQuantity(optionId, amount);
    if (updatedRows == 0) {
      throw new InsufficientStockException("차감 수량이 현재 재고보다 많습니다.", optionId);
    }
  }

}
