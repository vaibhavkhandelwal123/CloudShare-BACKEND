package com.cloudshare.cloudshare.repository;

import com.cloudshare.cloudshare.entity.FileInit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<FileInit, Long> {
}
