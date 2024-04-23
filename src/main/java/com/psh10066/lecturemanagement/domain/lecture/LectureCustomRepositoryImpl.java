package com.psh10066.lecturemanagement.domain.lecture;

import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;
import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.presentation.dto.LectureListDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.psh10066.lecturemanagement.domain.lecture.QLecture.lecture;

@RequiredArgsConstructor
public class LectureCustomRepositoryImpl implements LectureCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<LectureListDTO> findAllLecture(User user, Pageable pageable, String lectureName, LecturePlatform lecturePlatform) {
        List<LectureListDTO> fetch = queryFactory.select(Projections.constructor(LectureListDTO.class,
                lecture.lectureId,
                lecture.lectureName,
                lecture.lecturePlatform,
                lecture.lecturePath
            ))
            .from(lecture)
            .where(
                lecture.user.eq(user),
                StringUtils.isNotBlank(lectureName) ? lecture.lectureName.contains(lectureName) : null,
                lecturePlatform != null ? lecture.lecturePlatform.eq(lecturePlatform) : null
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Long> count = queryFactory.select(lecture.count())
            .from(lecture)
            .where(
                lecture.user.eq(user),
                StringUtils.isNotBlank(lectureName) ? lecture.lectureName.contains(lectureName) : null,
                lecturePlatform != null ? lecture.lecturePlatform.eq(lecturePlatform) : null
            );

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchOne);
    }
}
