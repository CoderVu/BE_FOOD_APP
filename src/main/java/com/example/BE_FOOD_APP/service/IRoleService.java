package com.example.BE_FOOD_APP.service;

import com.example.BE_FOOD_APP.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> getRoles();
    Role createRole(Role theRole);
}
