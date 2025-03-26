package leeds.produtos.produtos.service;

import leeds.produtos.produtos.controller.CriarUsuarioDto;
import leeds.produtos.produtos.entity.Usuario;
import leeds.produtos.produtos.repository.UserRepository;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
   public UUID CriarUsuario(CriarUsuarioDto dto) {

       var entity = new Usuario(
               UUID.randomUUID(),
               "interessado",
               dto.nome(),
               dto.email(),
               dto.senha()
       );

       var UsuarioSalvo = userRepository.save(entity);
       return UsuarioSalvo.getId();
   }

   public List<Usuario> ListarUsuarios() {
        return userRepository.findAll();
   }

   public void deletarUsuario(String userID) {

        var id = UUID.fromString(userID);
        var UsuarioExiste = userRepository.existsById(id);

        if (UsuarioExiste){
            userRepository.deleteById(id);
        } else {
            log.error("Usuario com ID {} não encontrado", userID);
        }
   }
}
