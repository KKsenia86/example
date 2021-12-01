package my.pack.api.service;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.pack.api.config.MinioConf;
import my.pack.api.model.service.DownloadFileRequest;
import my.pack.api.model.service.DownloadFileResponse;
import my.pack.api.model.service.SendToFileResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendToFileServiceImpl implements SendToFileService {
    private final MinioConf minioConfig;
    private final MinioClient minioClient;

    @Override
    public SendToFileResponse uploadData(MultipartFile file) {
        try {
            InputStream data = file.getInputStream();
            PutObjectOptions options = new PutObjectOptions(data.available(), -1);
            options.setContentType(file.getContentType());
            minioClient.putObject(minioConfig.getBucket(), file.getOriginalFilename(), data, options);
            return new SendToFileResponse(true);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public DownloadFileResponse downloadData(DownloadFileRequest request) {
        byte[] content = null;
        try {
            InputStream is = minioClient.getObject(minioConfig.getBucket(), request.getFileName());
            content = IOUtils.toByteArray(is);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
        return new DownloadFileResponse(content);
    }
}
