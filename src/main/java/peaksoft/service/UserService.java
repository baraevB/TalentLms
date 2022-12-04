package peaksoft.service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;
import peaksoft.entity.User;
import peaksoft.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest request){
        User user = mapToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return mapToResponse(user);
    }
    public UserResponse update(long id,UserRequest request){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw   new UsernameNotFoundException(format("User with email - %d , not found",id));
        }
        mapToUpdate(request,user.get());
        return mapToResponse(userRepository.save(user.get()));
    }
    public UserResponse findById(long id){
        Optional<User>user=userRepository.findById(id);
        if (user.isEmpty()){
            throw new UsernameNotFoundException(format("User with emil - %d , not found",id));
        }
        return mapToResponse(user.get());
    }
    public UserResponse deleteById(long id){
        Optional<User>user=userRepository.findById(id);
        if (user.isEmpty()){
            throw new UsernameNotFoundException(format("User with emil - %d , not found",id));
        }
        userRepository.deleteById(id);
        return mapToResponse(user.get());
    }


    public User mapToEntity(UserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setSurName(request.getSurName());
        user.setCreatDate(LocalDate.now());
        return user;
    }

    public User mapToUpdate(UserRequest request,User user){
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setSurName(request.getSurName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }
    public UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surName(user.getSurName())
                .email(user.getEmail())
                .creatDate(user.getCreatDate())
                .build();

    }



}
