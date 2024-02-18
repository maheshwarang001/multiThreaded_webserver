package com.example.imageservice.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Component
@Slf4j
public class FileConverter {

    public File convertMultiPartFiletoFile(MultipartFile multipartFile) throws IOException {

        File convert = new File(multipartFile.getName());
        try {

            FileOutputStream fos = new FileOutputStream(convert);
            fos.write(multipartFile.getBytes());

        }catch (IOException e){
            log.error(e.getMessage());
            throw e;
        }


        return convert;
    }
}
