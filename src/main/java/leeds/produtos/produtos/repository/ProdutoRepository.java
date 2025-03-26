package leeds.produtos.produtos.repository;

import leeds.produtos.produtos.entity.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, UUID> {
}
