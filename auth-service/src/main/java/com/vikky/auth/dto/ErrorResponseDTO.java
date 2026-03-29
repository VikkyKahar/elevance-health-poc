package com.vikky.auth.dto;

import lombok.Getter;

@Getter
public class ErrorResponseDTO {

    private String message;
    private int status;

    public ErrorResponseDTO(String message, int status) {
        this.message = message;
        this.status = status;
    }

}