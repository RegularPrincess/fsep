package ru.fsep.enterprise.fseper.controllers.converters;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import ru.fsep.enterprise.fseper.controllers.dto.PersonInfoDto;
import ru.fsep.enterprise.fseper.controllers.dto.PostDto;
import ru.fsep.enterprise.fseper.controllers.dto.UserDto;
import ru.fsep.enterprise.fseper.controllers.dto.UsersDto;
import ru.fsep.enterprise.fseper.models.PersonInfo;
import ru.fsep.enterprise.fseper.models.Post;
import ru.fsep.enterprise.fseper.models.User;

import java.util.List;

/**
 * Author Fedorov Juriy on 25.08.2015
 */
public class UserConverterImplV2 implements UserConverter {
    public UserDto fromUser(User entity) {

        UserDto userDto = new UserDto();
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter();

        return null;
    }

    public UsersDto fromUsers(List<User> entities) {
        return null;
    }

    public PostDto fromPost(Post entity) {
        return null;
    }

    public List<PostDto> fromPosts(List<Post> entities) {
        return null;
    }

    public PersonInfoDto fromPersonInfo(PersonInfo entity) {
        return null;
    }

    public User toUser(UserDto dto) {
        return null;
    }

    public List<User> toUsers(UsersDto dto) {
        return null;
    }

    public Post toPost(PostDto dto) {
        return null;
    }

    public List<Post> toPosts(List<PostDto> dtos) {
        return null;
    }

    public PersonInfo toPersonInfo(PersonInfoDto dto) {
        return null;
    }


}
