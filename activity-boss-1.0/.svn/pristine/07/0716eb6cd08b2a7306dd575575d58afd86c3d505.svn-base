package com.yeepay.g3.boss.activity.utils;

import com.yeepay.g3.facade.employee.dto.sso.DataDictDTO;
import com.yeepay.g3.facade.employee.user.dto.UserDTO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author：menghao.chen
 * @since：2014年7月30日 下午5:29:38
 */
public class PermissionUtils {

    public static UserDTO getCurrentUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        UserDTO userDTO = (UserDTO) session
                .getAttribute(DataDictDTO.SESSION_USERINFO);
        return userDTO;
    }
}
