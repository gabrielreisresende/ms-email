package com.resendegabriel.emailms.dto;

import lombok.Builder;

@Builder
public record EmailData(String to,

                        String subject,

                        String body) {
}
