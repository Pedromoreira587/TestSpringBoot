package leeds.produtos.produtos.service;

import leeds.produtos.produtos.controller.UsuarioDto;
import leeds.produtos.produtos.entity.Usuario;
import leeds.produtos.produtos.repository.UserRepository;
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
   public UUID CriarUsuario(UsuarioDto dto) {

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

   public void AtualizarUsuario(String userID, UsuarioDto dto) {
       var id = UUID.fromString(userID);
       var UsuarioExiste = userRepository.findById(id);

       if (UsuarioExiste.isPresent()) {
           var user = UsuarioExiste.get();

           if (dto.nome() != null) {
               user.setNome(dto.nome());
           }
           if (dto.email() != null) {
               user.setEmail(dto.email());
           }
           if (dto.senha() != null) {
               user.setSenha(dto.senha());
           }
           userRepository.save(user);
           log.info("Usuario atualizado com sucesso!");
       }else {
           log.error("Usuario com ID {} não encontrado", userID);
       }


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
