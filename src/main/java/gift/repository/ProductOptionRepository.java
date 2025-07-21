package gift.repository;

import gift.model.ProductOption;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

  List<ProductOption> findByProductId(Long productId);

  boolean existsByProductIdAndOption(Long productId, String optionName);
}
