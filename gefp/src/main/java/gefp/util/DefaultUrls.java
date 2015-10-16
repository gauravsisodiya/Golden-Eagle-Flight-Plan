/*
 * This file is part of the CSNetwork Services (CSNS) project.
 * 
 * Copyright 2014, Chengyu Sun (csun@calstatela.edu).
 * 
 * CSNS is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * CSNS is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with CSNS. If not, see http://www.gnu.org/licenses/agpl.html.
 */
package gefp.util;

import gefp.model.User;
import gefp.security.SecurityUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;



@Component
public class DefaultUrls {

    public String homeUrl( HttpServletRequest request )
    {
        return SecurityUtils.isAuthenticated() ? userHomeUrl( request )
            : anonymousHomeUrl( request );
    }

    public String userHomeUrl( HttpServletRequest request )
    {
        User user = SecurityUtils.getUser();
        String homeUrl;
        if( user.isSysadmin() )
            homeUrl = "/homepage.html";
        else if( user.hasRole("ROLE_ADVISOR"))
            homeUrl = "/advisor/advisorHomepage.html?advisor_id="+user.getId();
        else
            homeUrl = "/studentView.html?id="+user.getId();

        return homeUrl;
    }

    public String anonymousHomeUrl( HttpServletRequest request )
    {
       return "/login.html";
    }

}
