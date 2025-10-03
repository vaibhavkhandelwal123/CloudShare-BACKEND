package com.cloudshare.cloudshare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "file")
public class FileInit {
    @Id
    private Long id;
    private String senderEmail;
    private String receiverEmail;
    private String name;
    private byte[] fileData;

    public FileInit(String senderEmail, String receiverEmail,String name, byte[] bytes) {

        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.name = name;
        this.fileData = bytes;
    }


    public FileInitDTO toDTO(){
        return new FileInitDTO(this.senderEmail,this.receiverEmail,this.name,this.fileData!=null? Base64.getEncoder().encodeToString(this.fileData):null);
    }

}
