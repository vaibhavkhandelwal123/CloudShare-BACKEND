package com.cloudshare.cloudshare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInitDTO {

    private String senderEmail;
    private String receiverEmail;
    private String name;
    private String file;
    private LocalDateTime localDateTime;
    public FileInit toEntity(){
        return new FileInit(this.senderEmail,this.receiverEmail,this.name,this.file!=null? Base64.getDecoder().decode(this.file):null,this.localDateTime);
    }
}
