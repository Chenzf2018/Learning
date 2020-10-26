package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Chenzf
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {
    private String id;
    private String username;
    private String realname;
    private String userpassword;
    private String sexual;
}