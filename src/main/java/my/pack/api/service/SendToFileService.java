package my.pack.api.service;

import my.pack.api.model.service.DownloadFileRequest;
import my.pack.api.model.service.DownloadFileResponse;
import my.pack.api.model.service.SendToFileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface SendToFileService {
    SendToFileResponse uploadData(MultipartFile file);

    DownloadFileResponse downloadData(DownloadFileRequest request);
}

