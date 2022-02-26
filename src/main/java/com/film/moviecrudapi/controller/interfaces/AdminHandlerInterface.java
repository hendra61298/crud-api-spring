package com.film.moviecrudapi.controller.interfaces;

import javax.servlet.http.HttpServletRequest;
import com.film.moviecrudapi.common.enums.UserEnumRole;

public interface AdminHandlerInterface {
    public default boolean validateRoleAdmin(HttpServletRequest user) {
        if (!user.getAttribute("role").toString().equals(UserEnumRole.UserRoleEnums.Admin.toString())) {
            return false;
        }
        return true;
    }
}
