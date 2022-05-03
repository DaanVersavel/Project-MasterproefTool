package com.example.MasterproofTool.user.appUser;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.MasterproofTool.user.role.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping(path ="/User")
public class UserController {

    //niet gebruiken
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService= userService;
    }

    //list of users
    @GetMapping(value = "/users")
    public ResponseEntity<List<Appuser>> getUsers(){
        return ResponseEntity.ok().body(userService.getAppusers());
    }

    //save user
    //us the more specific
    @PostMapping(value = "/users/save")
    public ResponseEntity<Appuser> saveAppuser(@RequestBody Appuser appuser){
        URI uri=  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/User/users/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveAppuser(appuser));
    }

    //save role
    @PostMapping(value = "/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri=  URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/User/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    //save role to user
    @PostMapping(value = "/role/addtouser")
    public ResponseEntity<Role> addRoleToAppuser(@RequestBody RoleToAppuserForm form){
        userService.addRoleToAppuser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }

    //refresh token
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String email = decodedJWT.getSubject();
                Appuser appuser = userService.getAppuser(email);
                String access_token = JWT.create()
                        .withSubject(appuser.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", appuser.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @DeleteMapping(path ="/delete/{id}")
    public void deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
    }
}

@Data
class RoleToAppuserForm{
    private String username;
    private String rolename;
}

