package amerikano.weatherdiary.service;

import amerikano.weatherdiary.domain.DateWeather;
import amerikano.weatherdiary.domain.Diary;
import amerikano.weatherdiary.repository.DiaryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DiaryServiceTest {
    @Autowired
    private DiaryRepository diaryRepository;

    @Test
    @DisplayName("일기 생성 테스트")
    void createDiaryTest() {
        Diary testDiary = new Diary();

        testDiary.setId(10);
        testDiary.setWeather("clear");
        testDiary.setIcon("00a");
        testDiary.setTemperature(250.00);
        testDiary.setText("Create Diary Test");
        testDiary.setDate(LocalDate.now());

        assertDoesNotThrow(() -> diaryRepository.save(testDiary));
    }

    @Test
    @DisplayName("일기 조회 테스트")
    void readDiaryTest() {
        Diary testDiary1 = new Diary();

        testDiary1.setId(10);
        testDiary1.setWeather("clear");
        testDiary1.setIcon("00a");
        testDiary1.setTemperature(250.00);
        testDiary1.setText("Create Diary Test1");
        testDiary1.setDate(LocalDate.now());

        Diary testDiary2 = new Diary();

        testDiary2.setId(11);
        testDiary2.setWeather("cloud");
        testDiary2.setIcon("01a");
        testDiary2.setTemperature(220.00);
        testDiary2.setText("Create Diary Test2");
        testDiary2.setDate(LocalDate.now());

        diaryRepository.save(testDiary1);
        diaryRepository.save(testDiary2);

        assertEquals(diaryRepository.findAllByDate(LocalDate.now()).size(), 2);
    }

    @Test
    @DisplayName("일기 수정 테스트")
    void updateDiary() {
        Diary testDiary = new Diary();

        testDiary.setId(10);
        testDiary.setWeather("clear");
        testDiary.setIcon("00a");
        testDiary.setTemperature(250.00);
        testDiary.setText("Create Diary Test1");
        testDiary.setDate(LocalDate.now());

        diaryRepository.save(testDiary);
        Diary savedDiary = diaryRepository.getFirstByDate(LocalDate.now());
        savedDiary.setText("Diary Updated");
        diaryRepository.save(savedDiary);

        assertEquals(diaryRepository.getFirstByDate(LocalDate.now()).getText(), "Diary Updated");
    }

    @Test
    @DisplayName("일기 삭제 테스트")
    void deleteDiary() {
        Diary testDiary = new Diary();

        testDiary.setId(10);
        testDiary.setWeather("clear");
        testDiary.setIcon("00a");
        testDiary.setTemperature(250.00);
        testDiary.setText("Create Diary Test1");
        testDiary.setDate(LocalDate.now());

        diaryRepository.save(testDiary);
        diaryRepository.deleteAllByDate(LocalDate.now());

        assertEquals(diaryRepository.findAllByDate(LocalDate.now()).size(), 0);
    }

}