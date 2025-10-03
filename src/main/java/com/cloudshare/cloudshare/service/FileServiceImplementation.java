package com.cloudshare.cloudshare.service;


import com.cloudshare.cloudshare.entity.FileInit;
import com.cloudshare.cloudshare.entity.FileInitDTO;
import com.cloudshare.cloudshare.exception.CloudShareException;
import com.cloudshare.cloudshare.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class FileServiceImplementation implements FileService{

    @Autowired
    private FileRepository fileRepository;

    @Override
    public String createLink(FileInitDTO fileInitDTO) throws CloudShareException {
        FileInit file = fileInitDTO.toEntity();
        long id = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        file.setId(id);
        file.setLocalDateTime(LocalDateTime.now());
        fileRepository.save(file);
        return String.valueOf(id);
    }

    @Override
    public String getFile(Long id) throws CloudShareException {
        LocalDateTime now = LocalDateTime.now();
        List<FileInit> allFiles = fileRepository.findAll();
        for (FileInit f : allFiles) {
            Duration duration = Duration.between(f.getLocalDateTime(), now);
            if (duration.toSeconds() > 3600) {
                fileRepository.delete(f);
            }
        }

        FileInit fileInit = fileRepository.findById(id)
                .orElseThrow(() -> new CloudShareException("File does not exist or expired"));

        FileInitDTO fileInitDTO = fileInit.toDTO();
        return fileInitDTO.getName() + "||" + fileInitDTO.getFile();
    }


}
