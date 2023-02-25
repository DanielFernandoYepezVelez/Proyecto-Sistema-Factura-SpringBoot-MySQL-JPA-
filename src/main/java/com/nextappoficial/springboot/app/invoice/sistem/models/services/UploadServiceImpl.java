package com.nextappoficial.springboot.app.invoice.sistem.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements IUploadFileService {
    private final static String UPLOAD_DIRECTORY = "uploads";
    private final Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Override
    public Resource loadImage(String photoName) throws MalformedURLException {
        Resource resource = null;
        Path filePath = getPath(photoName);

        log.info(filePath.toString());
        resource = new UrlResource(filePath.toUri());

        // Validando Que El Recurso Exista Y Se Pueda Leer
        if(!resource.exists() && !resource.isReadable()) {
            filePath = Paths.get("src/main/resources/static/image").resolve("not-user-profile.png").toAbsolutePath();
            resource = new UrlResource(filePath.toUri());

            log.error("Error No Se Pudo Cargar La Imagen: " + photoName);
        }

        return resource;
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        String fileName =   UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
        Path filePath = getPath(fileName);

        log.info(filePath.toString());
        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }

    @Override
    public boolean delete(String photoName) {
        if(photoName != null && photoName.length() > 0) {
            Path beforePhotoPath = Paths.get("uploads").resolve(photoName).toAbsolutePath();
            File beforePhotoFile = beforePhotoPath.toFile();

            if(beforePhotoFile.exists() && beforePhotoFile.canRead()) {
                beforePhotoFile.delete();
                return true;
            }
        }

        return false;
    }

    @Override
    public Path getPath(String photoName) {
        return Paths.get(UPLOAD_DIRECTORY).resolve(photoName).toAbsolutePath();
    }
}
