package com.psh10066.lecturemanagement.core;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsWithId extends UserDetails {

    Long getUserId();
}
