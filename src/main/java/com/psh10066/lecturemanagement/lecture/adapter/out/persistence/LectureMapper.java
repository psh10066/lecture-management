package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.adapter.in.web.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.dto.LectureModifyInfoDTO;
import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.lecture.domain.LectureToCurriculum;
import com.psh10066.lecturemanagement.lecture.domain.Section;
import com.psh10066.lecturemanagement.lecture.domain.Study;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LectureMapper {

    LectureMapper INSTANCE = Mappers.getMapper(LectureMapper.class);

    @Mapping(source = "lectureToCurriculumList", target = "curriculums")
    LectureInfoDTO toLectureInfo(Lecture lecture);

    @Mapping(source = "curriculum", target = ".")
    @Mapping(source = "curriculum.lecturer", target = ".")
    @Mapping(source = "curriculum.sectionList", target = "sections")
    LectureInfoDTO.CurriculumDTO toCurriculumInfo(LectureToCurriculum lectureToCurriculum);

    @Mapping(source = "studyList", target = "studies")
    LectureInfoDTO.SectionDTO toSectionInfo(Section section);

    LectureInfoDTO.StudyDTO toStudyInfo(Study study);

    @Mapping(source = "lectureToCurriculumList", target = "curriculums")
    LectureModifyInfoDTO toLectureModifyInfo(Lecture lecture);

    @Mapping(source = "curriculum", target = ".")
    @Mapping(source = "curriculum.lecturer", target = ".")
    LectureModifyInfoDTO.CurriculumDTO toCurriculumModifyInfo(LectureToCurriculum lectureToCurriculum);
}
