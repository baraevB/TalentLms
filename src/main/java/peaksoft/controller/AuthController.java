package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.*;
import peaksoft.entity.User;
import peaksoft.repository.UserRepository;
import peaksoft.security.jvt.JwtTokenUtil;
import peaksoft.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class AuthController {
    private  final UserService userService;
    private final UserRepository repository;
    private final JwtTokenUtil jwtTokenUtil;
    private final LoginMapper loginMapper;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody UserRequest request){
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            User user = repository.findByEmail(token.getName()).get();
            return ResponseEntity.ok().body(loginMapper.loginView(jwtTokenUtil.generateToken(user), ValidationType.SUCCESSFUL,user));
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMapper.loginView("",ValidationType.LOGIN_FAILED,null));
        }

    }

    @PostMapping("registration")
    public UserResponse create (@RequestBody UserRequest request){
        return userService.createUser(request);
    }
}
