package my.pack.api.model.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DownloadFileRequest {
    private String fileName;
}
