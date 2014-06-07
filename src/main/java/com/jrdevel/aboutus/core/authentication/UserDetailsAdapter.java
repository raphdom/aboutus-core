package com.jrdevel.aboutus.core.authentication;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jrdevel.aboutus.core.common.model.Permission;
import com.jrdevel.aboutus.core.common.model.User;

/**
 * @author Raphael Domingues
 *
 */
public class UserDetailsAdapter implements UserDetails{

	
	private static final long serialVersionUID = -3460617418272718827L;
	
	private User user;
	private List<Permission> roles;
	
	public UserDetailsAdapter(User user, List<Permission> roles) { 
		this.user = user; 
		this.roles = roles;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_AUTHENTICATED_USER"));
		for(Permission role: roles){
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}
	
	public List<Permission> getRoles(){
		return roles;
	}

	public String getPassword() {
		return user.getPassword();
	}
	
	public Integer getId(){
		return user.getId();
	}

	public String getUsername() {
		return user.getEmail();
	}
	
	public String getLocale(){
		return user.getLocale();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
	
	public User getUser(){
		return user;
	}

}
