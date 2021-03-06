package my.pack.api.model.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class SendToFileRequest {
    private MultipartFile file;
}
