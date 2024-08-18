package com.psh10066.lecturemanagement.lecture.adapter.in.web.factory;

import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.RegisterFastcampusLectureRequest;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RegisterLectureCommandFactoryTest {

    private RegisterLectureCommandFactory registerLectureCommandFactory;

    @BeforeEach
    void setUp() {
        registerLectureCommandFactory = new RegisterLectureCommandFactory(null);
    }

    @DisplayName("패스트캠퍼스 강의 정보에 맞춰 Command를 생성할 수 있다")
    @Test
    void fromFastcampusRequest() {
        // given
        RegisterFastcampusLectureRequest request = mockRegisterFastcampusLectureRequest();

        // when
        RegisterLectureCommand command = registerLectureCommandFactory.fromFastcampusRequest(request);

        // then
        assertThat(command.lectureName()).isEqualTo("Spring Webflux 완전 정복 : 코루틴부터 리액티브 MSA 프로젝트까지");
        assertThat(command.lecturePlatform()).isEqualTo(LecturePlatform.FASTCAMPUS);
        assertThat(command.lecturePath()).isEqualTo("https://fastcampus.co.kr/classroom/216172");

        List<RegisterLectureCommand.CurriculumDTO> curriculumList = command.curriculumList();
        assertThat(curriculumList.size()).isEqualTo(2);
        assertThat(curriculumList.get(0).curriculumName()).isEqualTo("Part 1. Reactive programming");
        assertThat(curriculumList.get(0).sectionList().size()).isEqualTo(9);
        assertThat(curriculumList.get(0).sectionList().get(0).sectionName()).isEqualTo("Ch 01. 전체 커리큘럼과 강의진행 소개");
        assertThat(curriculumList.get(0).sectionList().get(0).studyList().size()).isEqualTo(1);
        assertThat(curriculumList.get(0).sectionList().get(0).studyList().get(0).studyName()).isEqualTo("01. 전체 커리큘럼과 강의 진행 소개");
        assertThat(curriculumList.get(0).sectionList().get(0).studyList().get(0).studyTime()).isEqualTo(LocalTime.of(0, 16, 31));
    }

    private RegisterFastcampusLectureRequest mockRegisterFastcampusLectureRequest() {
        return new RegisterFastcampusLectureRequest(
            "Spring Webflux 완전 정복 : 코루틴부터 리액티브 MSA 프로젝트까지",
            "https://fastcampus.co.kr/classroom/216172",
            """
            1
            Part 1. Reactive programming

            1 / 32

            8:08:41
            Ch 01. 전체 커리큘럼과 강의진행 소개

            16:31

            01. 전체 커리큘럼과 강의 진행 소개
            16:31
            Ch 02. Reactive Programming

            2:24

            01. 챕터 소개
            2:24
            Ch 03. 비동기 Programming

            41:33

            01. 함수 관점에서 동기와 비동기의 차이
            10:45

            02. 함수 관점에서 blocking과 non-blocking의 차이
            16:19

            03. IO 관점에서 blocking과 non-blocking의 차이
            14:29
            Ch 04. CompletableFuture

            1:42:58

            01. Future 인터페이스
            20:46

            02. CompletionStage 인터페이스
            29:04

            03. CompletableFuture 클래스
            14:13

            04. CompletableFuture 사용해서 비동기 로직 처리하기
            37:04

            05. CompletableFuture 정리
            1:51
            Ch 05. Reactive Streams

            1:21:46

            01. Reactive streams의 역사
            6:02

            02. Reactive manifesto
            17:52

            03. Reactive programming
            12:31

            04. Reactive streams
            16:20

            05. Cold & Hot Publisher 구현
            27:25

            06. Reactive streams 정리
            1:36
            Ch 06. Reactive Streams 구현 라이브러리 소개

            38:58

            01. Reactor 소개, Publisher 중심
            19:09

            02. RxJava 소개, Publisher 중심
            12:29

            03. Mutiny 소개, Publisher 중심
            7:20
            Ch 07. Java NIO

            1:43:05

            01. Java IO - InputStream
            14:03

            02. Java IO - OutputStream
            12:43

            03. Java NIO - Buffer
            17:17

            04. Java NIO - DirectByteBuffer와 Channel
            15:14

            05. Java IO, NIO 사용해서 소켓 서버 구현
            32:11

            06. Java AIO(NIO2)
            11:37
            Ch 08. Reactor 패턴

            1:26:16

            01. Selector 소개
            16:45

            02. Selector 사용해서 소켓 서버 고도화
            11:13

            03. epoll 소개
            20:50

            04. Reactor pattern 소개
            5:45

            05. Reactor pattern 사용해서 http 서버 구현
            28:50

            06. Reactor pattern 정리
            2:53
            Ch 09. [부록] A. Proactor pattern

            15:10

            01. Reactor 패턴과 비교하여 개념 설명
            15:10
            2
            Part 2. Spring Webflux

            0 / 44

            10:32:10
            Ch 01. Spring Reactive Stack

            23:49

            01. 챕터 소개
            1:33

            02. spring servlet stack
            17:39

            03. spring reactive stack
            4:37
            Ch 02. Netty

            1:25:25

            01. eventLoop
            13:45

            02. channelHandlerContext
            9:06

            03. channelHandler
            13:51

            04. netty로 Echo server 구현 예제
            12:38

            05. bootstrap
            12:20

            06. netty 서버 구현
            23:09

            07. netty 정리
            0:36
            Ch 03. Reactor

            2:41:44

            01. reactor 복습
            8:39

            02. subscribe
            13:44

            03. sequence 생성
            22:31

            04. scheduler와 쓰레드
            19:09

            05. 에러 핸들링
            12:52

            06. 결합
            13:38

            07. 유용한 연산자
            18:52

            08. context
            18:23

            09. reactor로 비동기 로직 처리
            33:01

            10. reactor 정리
            0:55
            Ch 04. Spring Webflux

            1:29:23

            01. spring webflux 구조
            11:15

            02. httpHandler
            14:00

            03. webHandler
            9:29

            04. webHandler codec
            29:48

            05. webFilter
            16:45

            06. webExceptionHandler
            8:06
            Ch 05. Spring Webflux 심화

            2:38:40

            01. webHandler에 Spring 적용
            11:57

            02. dispatcherHandler
            9:23

            03. functional endpoints
            24:56

            04. annotated controllers
            8:01

            05. handler method
            15:21

            06. spring webflux로 서버 구현
            25:11

            07. webClient
            19:56

            08. spring security reactive
            9:27

            09. client, security 서버에 적용
            32:25

            10. spring webflux 심화 정리
            2:03
            Ch 06. Server Sent Event

            46:04

            01. event streaming
            10:36

            02. server sent event
            16:09

            03. sse로 알림 서버 구현
            17:31

            04. server sent event 정리
            1:48
            Ch 07. WebSocket

            1:07:05

            01. simpleUrlHandlerMapping
            9:00

            02. webSocketHandler
            12:00

            03. websocket으로 채팅 서버 구현
            41:03

            04. webSocket 정리
            5:02
            """.replace("\n", "\r\n")
        );
    }
}