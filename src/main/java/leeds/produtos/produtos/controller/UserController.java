package leeds.produtos.produtos.controller;

import leeds.produtos.produtos.entity.Usuario;
import leeds.produtos.produtos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/PaginaUsuario") // pagina de onde vem as informações

public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<String> CriarUsuario(@RequestBody UsuarioDto dto) {

        var userId = userService.CriarUsuario(dto);
        return ResponseEntity.created(URI.create("/leads/" + userId)).body("Usuário " + dto.nome() + " criado com sucesso. Seu ID é: "+userId);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> ListarUsuarios() {

        var users = userService.ListarUsuarios();

        return ResponseEntity.ok(users);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<String> AtualizarUsuario(@PathVariable("userId") String userId,@RequestBody UsuarioDto dto){
        userService.AtualizarUsuario(userId,dto);
        return ResponseEntity.ok("Usuario atualizado com sucesso!");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> DeletarUsuario(@PathVariable("userId") String userId) {
        userService.deletarUsuario(userId);
        return ResponseEntity.ok("usuario " + userId + " deletado com sucesso");
    }

}
