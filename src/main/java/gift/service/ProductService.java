package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Page<Product> findAll(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }

  public Product save(Product product) {
    return productRepository.save(product);
  }

  public Optional<Product> update(Long id, Product updateProduct) {
    return productRepository.findById(id).map(existing -> {
      existing.setName(updateProduct.getName());
      existing.setPrice(updateProduct.getPrice());
      existing.setImageUrl(updateProduct.getImageUrl());
      return productRepository.save(existing);
    });
  }

  public boolean delete(Long id) {
    if (productRepository.existsById(id)) {
      productRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
