package com.example.coco_spring.Auth;


import com.example.coco_spring.Entity.User;
import com.example.coco_spring.Repository.TokenRepository;
import com.example.coco_spring.Repository.UserRepository;
import com.example.coco_spring.Service.User.UserService;
import com.example.coco_spring.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request,
            BindingResult result
    ) throws MessagingException {
        /*if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest()
                    .body(AuthenticationResponse.builder()
                            .errors(errors)
                            .build());
        }*/
        return ResponseEntity.ok(service.register(request));
    }

    /*@PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest()
                    .body(AuthenticationResponse.builder()
                            .errors(errors)
                            .build());
        }
        return ResponseEntity.ok(service.authenticate(request));
    }*/
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest()
                    .body(AuthenticationResponse.builder()
                            .errors(errors)
                            .build());
        }
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        if (!user.getExpired()&& !user.getLocked()){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            var jwtToken = jwtService.generateToken(user);
            //revokeAllUserTokens(user); hedhi eli lezem nraja33ha
            service.saveUserToken(user, jwtToken);
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .build());
        }
        else if(user.getLocked()){
            return ResponseEntity.badRequest()
                    .body(AuthenticationResponse.builder()
                            .errors(Collections.singletonList("This profile is not yet verified. Please check your mail to activate it."))
                            .build());
        }
        else {
            return ResponseEntity.badRequest()
                    .body(AuthenticationResponse.builder()
                            .errors(Collections.singletonList("This profile is blocked."))
                            .build());
        }
    }

	@PostMapping("/webAuthenticate")
	public ResponseEntity<AuthenticationResponse> webAuthenticate(
		@RequestBody AuthenticationRequest request,
		BindingResult result
	) {
		if (result.hasErrors()) {
			List<String> errors = result.getAllErrors()
				.stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.toList());
			return ResponseEntity.badRequest()
				.body(AuthenticationResponse.builder()
					.errors(errors)
					.build());
		}
		return ResponseEntity.ok(service.authenticateViaWeb(request));
	}

    @PostMapping("/verif/{mail}/{code}")
    public String verifAccount(@PathVariable("mail") String mail,@PathVariable("code") Integer code){
        return service.verifAccount(mail,code);
    }

	@PostMapping("/demResetPassword/{email}")
	public ResponseEntity<?> demResetPassword(@PathVariable("email") String email) throws MessagingException {
		Optional<User> user = repository.findByEmail(email);
		if (user.isPresent()) {
			service.demResetPassword(email);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	private UserService userService;
	@PostMapping("/ResetPassword/{code}/{pwd}")
	public String demResetPassword(@PathVariable("code") Integer code,@PathVariable("pwd") String pwd) throws MessagingException {
		return userService.reserPassword(code,pwd);
	}

}
