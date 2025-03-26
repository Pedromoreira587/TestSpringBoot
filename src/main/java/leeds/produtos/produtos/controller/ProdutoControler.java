package leeds.produtos.produtos.controller;

import leeds.produtos.produtos.entity.Produtos;
import leeds.produtos.produtos.service.ProdutoServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Produtos")
public class ProdutoControler {

    private final ProdutoServices produtoServices;
    public ProdutoControler(ProdutoServices produtoServices) {
        this.produtoServices = produtoServices;
    }
    @PostMapping
    public ResponseEntity<String> criarproduto(@RequestBody ProdutoDto produto) {
        var prodID = produtoServices.CriarProduto(produto);
        return ResponseEntity.created(URI.create("/Produtos/" + prodID)).body("Usuário " + produto.nome() + " Produto com sucesso. Seu ID é: "+prodID);
    }
    @GetMapping
    public ResponseEntity<List<Produtos>> ListarProdutos() {
        var produtos = produtoServices.ListarProdutos();

        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{ProdID}")
    public ResponseEntity<String> AtualizarUsuario(@PathVariable("ProdID") String ProdID,@RequestBody AtualizarProdDto dto) {
        produtoServices.AtualizarProduto(ProdID, dto);
        return ResponseEntity.ok("Usuario atualizado com sucesso!");
    }
    @DeleteMapping("/{ProdID}")
    public ResponseEntity<String> DeletarUsuario(@PathVariable("ProdID") String ProdID) {
        produtoServices.deletarprodutos(ProdID);
        return ResponseEntity.ok("Produto " + ProdID + " deletado com sucesso");
    }
}
