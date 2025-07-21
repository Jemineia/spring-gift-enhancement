package gift.repository;

import gift.model.ProductOption;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

  List<ProductOption> findByProductId(Long productId);

  boolean existsByProductIdAndOption(Long productId, String optionName);

  @Modifying
  @Query("UPDATE ProductOption po SET po.quantity = po.quantity - :amount WHERE po.id = :optionId AND po.quantity >= :amount")
  int decreaseQuantity(@Param("optionId") Long optionId, @Param("amount") int amount);

}
