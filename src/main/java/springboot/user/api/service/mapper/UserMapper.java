package springboot.user.api.service.mapper;

import springboot.user.api.model.User;
import springboot.user.api.model.dto.request.UserRequestDto;
import springboot.user.api.model.dto.responce.UserResponseDto;
import springboot.user.api.service.SequenceGeneratorService;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final SequenceGeneratorService sequenceGeneratorService;

    public UserMapper(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        responseDto.setFullName(user.getFullName());
        return responseDto;
    }

    public User mapToModel(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        return user;
    }
}
