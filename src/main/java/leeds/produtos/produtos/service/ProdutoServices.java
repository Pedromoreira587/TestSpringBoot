package leeds.produtos.produtos.service;

import leeds.produtos.produtos.controller.AtualizarProdDto;
import leeds.produtos.produtos.controller.ProdutoDto;
import leeds.produtos.produtos.controller.UsuarioDto;
import leeds.produtos.produtos.entity.Produtos;
import leeds.produtos.produtos.entity.Usuario;
import leeds.produtos.produtos.repository.ProdutoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service

public class ProdutoServices {

    private static final Logger log = LogManager.getLogger(ProdutoServices.class);
    private final ProdutoRepository ProdutoRepository;
    public ProdutoServices(ProdutoRepository ProdutoRepository) {
        this.ProdutoRepository = ProdutoRepository;
    }
    public UUID CriarProduto(ProdutoDto Dto) {

        var entity = new Produtos(
                UUID.randomUUID(),
                "disponivel",
                Dto.nome(),
                Dto.valor());

        var ProdutoSalvo = ProdutoRepository.save(entity);
        return ProdutoSalvo.getId();
    }

    public List<Produtos> ListarProdutos() {
        return ProdutoRepository.findAll();
    }

    public void deletarprodutos(String prodID) {

        var id = UUID.fromString(prodID);
        var UsuarioExiste = ProdutoRepository.existsById(id);

        if (UsuarioExiste){
           ProdutoRepository.deleteById(id);
        } else {
            log.error("Produto com ID {} não encontrado", prodID);
        }
    }

    public void AtualizarProduto(String prodID, AtualizarProdDto dto) {

        var id = UUID.fromString(prodID);
        var UsuarioExiste = ProdutoRepository.findById(id);

        if (UsuarioExiste.isPresent()) {
            var user = UsuarioExiste.get();

            if (dto.nome() != null) {
                user.setNome(dto.nome());
            }
            if (dto.valor() != null) {
                user.setValor(dto.valor());
            }
            if (dto.status() != null) {
                user.setStatus(dto.status());
            }
            ProdutoRepository.save(user);
            log.info("Produto atualizado com sucesso!");
        }else {
            log.error("Produto com ID {} não encontrado", prodID);
        }
    }
}
