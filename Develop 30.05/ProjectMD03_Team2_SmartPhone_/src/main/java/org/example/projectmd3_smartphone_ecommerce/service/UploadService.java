package org.example.projectmd3_smartphone_ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.google.cloud.storage.*;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadService {
    private static final String bucketName = "projectm3-d16f7.appspot.com";
    private final ServletContext servletContext;
    private final Storage storage;

    public String uploadFileToServer(MultipartFile file) {
        String uploadPath = servletContext.getRealPath("/upload");
        File fileUpload = new File(uploadPath);
        if (!fileUpload.exists()) {
            fileUpload.mkdir();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-mm-ss");
        String fileName = dtf.format(LocalDateTime.now()) + file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(uploadPath + File.separator + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return uploadFileFromServerToFirebase(uploadPath + File.separator + fileName);
    }

    private String uploadFileFromServerToFirebase(String filePath) {
        Path localPath = Paths.get(filePath);
        String fileName = localPath.getFileName().toString();
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        blobInfo = blobInfo.toBuilder().setAcl(acls).build();
        try {
            Blob blob = storage.create(blobInfo, Files.readAllBytes(localPath));
            return blob.getMediaLink();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
