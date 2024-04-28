package com.communityconnect.spring.payload.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetDTO {
    private Long id;
    private String assetFileName;
    private String assetContentType;
    private Integer assetFileSize;
    private LocalDateTime assetFileUpdatedAt;

    private Integer assetableId;
    private String assetableType;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}