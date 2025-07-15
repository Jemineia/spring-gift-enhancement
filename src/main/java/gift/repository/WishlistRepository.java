package gift.repository;

import gift.model.Member;
import gift.model.Product;
import gift.model.WishItem;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface WishlistRepository extends JpaRepository<WishItem, Long> {
  Optional<WishItem> findByMemberAndProduct(Member member, Product product);

  List<WishItem> findAllByMember(Member member);

  void deleteByMemberAndProduct(Member member, Product product);

  boolean existsByMemberAndProduct(Member member, Product product);
}
