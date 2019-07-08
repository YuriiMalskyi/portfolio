package com.malskyi.project.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority{

	ROLE_GLOBAL_ADMIN, ROLE_ADMIN, ROLE_MODERATOR, ROLE_SELLER;

	@Override
	public String getAuthority() {
		return name();
	}
		
}
