package com.example.demo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Document
@AllArgsConstructor
public class Tool {
    @Id
    private long id;

    @Setter @NonNull
    private String title;

    @Setter
    private String link;

    @Setter @NonNull
    private String description;

    @Setter
    private List<String> tags;

}
