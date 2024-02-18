package com.swipeio.wesocket.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagePayLoad {

    @NotBlank
    private String sender;
    @NotBlank
    private String receiver;
    @NotBlank
    private String content;
    @NotBlank
    private String timestamp;

}
