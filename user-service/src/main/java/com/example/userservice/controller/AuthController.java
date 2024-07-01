package com.example.userservice.controller;

import com.example.userservice.config.JwtProveedor;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.request.LoginRequest;
import com.example.userservice.response.AuthResponse;
import com.example.userservice.service.CustomerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserService customerUserService;

    @PostMapping("/registrarse")
        public ResponseEntity<AuthResponse> createUserHandler(
            @RequestBody User user) throws Exception {

        String email = user.getEmail();
        String password = user.getPassword();
        String nombre = user.getNombre();
        String rol = user.getRol();

        User isEmailExist = userRepository.findByEmail(email);

        if (isEmailExist!=null){
            throw new Exception("El correo electrónico ya se utiliza con otra cuenta");
        }

        //crear nuevo usuario

        User createUser = new User();
        createUser.setEmail(email);
        createUser.setNombre(nombre);
        createUser.setRol(rol);
        createUser.setPassword(passwordEncoder.encode(password));

        User savedUser = userRepository.save(createUser);


        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProveedor.generarToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Registro exitoso");
        authResponse.setStatus(true);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    //iniciar sesion
    @PostMapping("/InSesion")
    public ResponseEntity<AuthResponse> InSesion(@RequestBody LoginRequest loginRequest){

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        System.out.println(username + " -------- " + password);

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProveedor.generarToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Inicio de sesión exitoso");
        authResponse.setJwt(token);
        authResponse.setStatus(true);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);


    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserService.loadUserByUsername(username);

        System.out.println("Iniciar sesión detalles de usuario - " + userDetails);

        if (userDetails == null){
            System.out.println("Iniciar sesión detalles de usuario - nulo " + userDetails);
            throw new BadCredentialsException("Nombre de usuario o contraseña inválidos");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            System.out.println("Iniciar sesión detalles de usuario - la contraseña no coincide" + userDetails);
            throw new BadCredentialsException("Nombre de usuario o contraseña inválidos");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    }
}