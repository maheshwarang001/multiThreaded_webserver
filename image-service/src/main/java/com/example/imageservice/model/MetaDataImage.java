package com.example.imageservice.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;
import java.util.UUID;


@Data
@Document(collection = "users_image")
public class MetaDataImage {

    @Indexed(unique = true)
    @Field(targetType = FieldType.STRING)
    @Id
    private UUID uuid;
    private String images;
}
