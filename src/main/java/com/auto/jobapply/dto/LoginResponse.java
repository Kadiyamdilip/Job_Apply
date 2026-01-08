package com.auto.jobapply.dto;

/**
 * Proper record for handling the JWT token response.
 */
public record LoginResponse(String token) {
    // The record automatically provides the constructor,
    // getter (token()), equals, hashCode, and toString.
}