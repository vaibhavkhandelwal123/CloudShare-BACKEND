package com.cloudshare.cloudshare.api;

import com.cloudshare.cloudshare.entity.FileInitDTO;
import com.cloudshare.cloudshare.exception.CloudShareException;
import com.cloudshare.cloudshare.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileAPI {

    @Autowired
    private FileService fileService;

    @PostMapping("/create")
    public ResponseEntity<String> createFile(@RequestBody FileInitDTO fileInitDTO) throws CloudShareException {
        return new ResponseEntity<>(fileService.createLink(fileInitDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getFile(@PathVariable Long id) throws CloudShareException {
        return new ResponseEntity<>(fileService.getFile(id), HttpStatus.OK);
    }

}
