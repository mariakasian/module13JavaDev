package com.maria.module13.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Data
public class Note {
    private UUID id;
    private String title;
    private String content;
}
