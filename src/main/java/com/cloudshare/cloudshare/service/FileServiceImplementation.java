package com.cloudshare.cloudshare.service;


import com.cloudshare.cloudshare.entity.FileInit;
import com.cloudshare.cloudshare.entity.FileInitDTO;
import com.cloudshare.cloudshare.exception.CloudShareException;
import com.cloudshare.cloudshare.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        fileRepository.save(file);
        return String.valueOf(id);
    }

    @Override
    public String getFile(Long id) throws CloudShareException {
        if(!fileRepository.existsById(id)){
            throw new CloudShareException("file not exist");
        };
        Optional<FileInit> file = fileRepository.findById(id);
        FileInitDTO fileInitDTO = file.get().toDTO();
        String res = fileInitDTO.getName() + " " + fileInitDTO.getFile();
        return res;
    }
}
