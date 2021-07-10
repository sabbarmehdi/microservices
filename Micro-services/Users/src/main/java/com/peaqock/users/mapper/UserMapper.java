package com.peaqock.users.mapper;

import com.peaqock.users.Model.User;
import com.peaqock.users.dto.UserDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(config = MapStructConfig.class)
public interface UserMapper {

    @Named(value = "entity")
    User mapToEntity(UserDTO userDTO);

    User mapToEntityNoBackup(UserDTO userDTO);

    @Named(value = "dto")
    UserDTO mapToDTO(User user);

    @IterableMapping(qualifiedByName = "entity")
    List<User> mapToEntityList(List<UserDTO> userDTOS);

    @IterableMapping(qualifiedByName = "dto")
    List<UserDTO> mapToDTOList(List<User> users);

    @IterableMapping(qualifiedByName = "dto")
    Set<UserDTO> mapToDTOSet(Set<User> users);
}
