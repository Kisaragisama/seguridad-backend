package seguridad.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import seguridad.entity.Role;
import seguridad.entity.User;
import seguridad.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {

        // Si ya existe un admin, asumimos que ya se inicializó la BD
        if (userRepository.findByUsername("admin").isPresent()) {
            System.out.println("✅ Usuarios de prueba ya existen");
            return;
        }

        // ✅ Crear usuarios de prueba
        crearUsuario("admin", "admin123", Role.ADMIN);
        crearUsuario("mesero1", "1234", Role.MESERO);
        crearUsuario("cajero1", "1234", Role.CAJERO);
        crearUsuario("supervisor1", "1234", Role.SUPERVISOR);
        crearUsuario("cliente1", "1234", Role.CLIENTE);

        System.out.println("✅ Usuarios de prueba creados correctamente");
    }

    private void crearUsuario(String username, String password, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Texto plano solo para pruebas
        user.setRole(role);
        userRepository.save(user);
    }
}
