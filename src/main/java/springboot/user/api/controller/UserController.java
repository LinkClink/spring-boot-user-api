package springboot.user.api.controller;

import java.util.stream.Collectors;
import springboot.user.api.model.User;
import springboot.user.api.model.dto.request.UserRequestDto;
import springboot.user.api.model.dto.responce.UserResponseDto;
import springboot.user.api.service.UserService;
import springboot.user.api.service.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PutMapping("/save")
    public UserResponseDto save(@RequestBody UserRequestDto userRequestDto) {
        return userMapper.mapToDto(
                userService.save(userMapper.mapToModel(userRequestDto)));
    }

    @PutMapping("/update/{id}")
    public UserResponseDto update(@PathVariable Long id,
                                  @RequestBody UserRequestDto userRequestDto) {
        User user = userMapper.mapToModel(userRequestDto);
        user.setId(id);
        return userMapper.mapToDto(userService.update(user));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAll().stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList()), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(userService.findById(id));
    }
}
