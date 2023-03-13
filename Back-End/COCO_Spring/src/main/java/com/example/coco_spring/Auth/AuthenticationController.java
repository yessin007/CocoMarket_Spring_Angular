package com.example.coco_spring.Auth;

import com.example.coco_spring.Entity.TokenDto;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;import org.springframework.context.annotation.Lazy;


@RestController
@Lazy
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService service;
    @Value("${google.clientId}")
    String googleClientId;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request,
            BindingResult result
    ) throws MessagingException {
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
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody TokenDto tokenDto) throws IOException{
        final NetHttpTransport transport = new NetHttpTransport();
        final GsonFactory jacksonFactory = GsonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                .setAudience(Collections.singletonList(googleClientId));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
        return new ResponseEntity(payload, HttpStatus.OK);
    }

    @PostMapping("/facebook")
    public ResponseEntity<?> loginWithFacebook(@RequestBody TokenDto tokenDto) throws IOException{
        Facebook facebook = new FacebookTemplate(tokenDto.getValue());
        final String [] fields = {"email", "picture"};
        org.springframework.social.facebook.api.User user = facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
