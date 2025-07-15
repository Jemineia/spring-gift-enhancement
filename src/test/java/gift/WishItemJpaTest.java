package gift;

import gift.model.Member;
import gift.model.Product;
import gift.model.WishItem;
import gift.repository.MemberRepository;
import gift.repository.ProductRepository;
import gift.repository.WishlistRepository;
import gift.service.WishlistService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Import(WishlistService.class)
public class WishItemJpaTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    WishlistService wishlistService;

    @Test
    @DisplayName("[1] 찜 상품 정상 저장 & 조회")
    void saveAndFindWishItem(){
        //given
        Member member = new Member(null, "abc123@gmail.com", "qwer1234!");
        member = memberRepository.save(member);

        Product product = new Product(null, "테스트용 물건1", 1723, "http://example.com/img.jpg");
        product = productRepository.save(product);

        WishItem wishItem = new WishItem();
        wishItem.setProduct(product);
        wishItem.setMember(member);
        wishItem.setQuantity(996);

        wishlistRepository.save(wishItem);

        //when
        Optional<WishItem> found = wishlistRepository.findByMemberAndProduct(member, product);

        //then
        assertThat(found).isPresent();
        assertThat(found.get().getQuantity()).isEqualTo(996);
        assertThat(found.get().getMember().getEmail()).isEqualTo("abc123@gmail.com");
        assertThat(found.get().getProduct().getName()).isEqualTo("테스트용 물건1");
    }

    @Test
    @DisplayName("[2] 존재하지 않는 상품 찜 시도 시 예외 발생")
    void inValidItemToWishlist() {
        // given
        Member member = new Member(null, "testuser@email.com", "encodedpw");
        member = memberRepository.save(member);

        Long invalidProductId = 999L; // 존재하지 않는 상품 ID

        // when & then
        Member finalMember = member;
        assertThrows(IllegalArgumentException.class, () -> {
            wishlistService.addToWishlist(finalMember.getId(), invalidProductId);
        }, "상품이 존재하지 않습니다");
    }
}
