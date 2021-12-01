package my.pack.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.pack.api.exception.RequiredValueException;
import my.pack.api.model.service.DownloadFileRequest;
import my.pack.api.model.service.DownloadFileResponse;
import my.pack.api.model.service.SendToFileResponse;
import my.pack.api.service.SendToFileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Api(tags = "Контроллер по работе с минио")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class MinioController {

    private final SendToFileService sendToFileService;

    @ApiOperation(value = "Отправить в минио файл")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Успешно"),
            @ApiResponse(code = 500, message = "Ошибка при получение результата")
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/uploadData")
    public SendToFileResponse uploadData(@RequestParam("file") MultipartFile file) {
        Optional.ofNullable(file).orElseThrow(() -> new RequiredValueException("file"));
        log.info("uploadData size={}", file.getSize());
        return sendToFileService.uploadData(file);
    }


    @ApiOperation(value = "Получение контента файла")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Успешно"),
            @ApiResponse(code = 500, message = "Ошибка при получение результата")
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/getContent")
    public DownloadFileResponse downloadData(DownloadFileRequest request) {
        return sendToFileService.downloadData(request);
    }
}