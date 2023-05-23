package amerikano.weatherdiary.controller;

import amerikano.weatherdiary.config.GlobalExceptionHandler;
import amerikano.weatherdiary.domain.Diary;
import amerikano.weatherdiary.error.InvalidDate;
import amerikano.weatherdiary.service.DiaryService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "DiaryController", description = "날씨 일기 API")
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/create/diary")
    @Operation(summary = "일기 텍스트와 날씨를 이용하여 DB에 일기 저장", description = "해당 날짜에 일기 작성")
    @Parameters({
            @Parameter(name = "date", description = "작성 날짜", example = "2023-01-01"),
            @Parameter(name = "text", description = "저장할 일기 내용", example = "text"),
    })
    void createDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
                     LocalDate date,
                     @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @GetMapping("/read/diary")
    @Operation(summary = "선택한 날짜의 모든 일기를 가져오기")
    @Parameter(name = "date", description = "조회할 날짜", example = "2023-01-01")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
                   LocalDate date) {
        return diaryService.readDiary(date);
    }

    @GetMapping("/read/diaries")
    @Operation(summary = "선택한 기간 사이의 모든 일기를 가져오기")
    @Parameters({
            @Parameter(name = "startDate", description = "조회할 시작 날짜", example = "2023-01-01"),
            @Parameter(name = "endDate", description = "조회할 마지막 날짜", example = "2023-12-31"),
    })
    @ApiResponse(responseCode = "200", description = "가져오기 성공")
    @ApiResponse(responseCode = "500", description = "내부 서버 오류")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
                            LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
                            LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @PutMapping("update/diary")
    @Operation(summary = "선택한 날짜의 첫 번째 일기를 수정")
    @Parameter(name = "date", description = "수정할 일기의 날짜", example = "2023-01-01")
    void updateDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
                     LocalDate date,
                     @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @DeleteMapping("/delete/diary")
    @Operation(summary = "선택한 날짜의 모든 일기 삭제")
    @Parameter(name = "date", description = "삭제하고 싶은 날짜", example = "2023-01-01")
    void deleteDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
                     LocalDate date) {
        diaryService.deleteDiary(date);
    }
}
