package com.psh10066.lecturemanagement.user.adapter.out.persistence;

import com.psh10066.lecturemanagement.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserJpaEntity from(User user);

    User toModel(UserJpaEntity userJpaEntity);
}
