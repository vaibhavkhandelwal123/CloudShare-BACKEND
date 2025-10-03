package com.cloudshare.cloudshare.service;

import com.cloudshare.cloudshare.entity.FileInitDTO;
import com.cloudshare.cloudshare.exception.CloudShareException;
import org.springframework.stereotype.Service;

@Service
public interface FileService {

    public String createLink(FileInitDTO fileInitDTO) throws CloudShareException;

    public String getFile(Long id) throws CloudShareException;
}
