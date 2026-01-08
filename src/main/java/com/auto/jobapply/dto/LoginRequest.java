package com.auto.jobapply.dto;

/**
 * The record automatically creates fields, getters, and a constructor.
 * Access fields using .username() and .password()
 */
public record LoginRequest(String username, String password) {}
