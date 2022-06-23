package com.ladykoala.service;

import com.ladykoala.dao.AccountDao;
import com.ladykoala.dao.UserDao;
import com.ladykoala.model.RequestUserDto;
import com.ladykoala.repository.AccountRepository;
import com.ladykoala.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private AccountRepository accountDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDao user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public UserDao save(RequestUserDto user) {
		try{
			UserDao newUser = new UserDao();
			newUser.setUsername(user.getUsername());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			UserDao u = userDao.save(newUser);

			AccountDao ac = new AccountDao();
			ac.setUserId(u.getId());
			ac.setLastname(user.getLastname());
			ac.setFirstname(user.getFirstname());
			ac.setBirthday(user.getBirthday());
			ac.setAddress(user.getAddress());
			ac.setEmail(user.getEmail());
			ac.setContactNo(user.getContactNo());
			ac.setVerified(false);
			ac.setUserType(user.getUserType());

			accountDao.save(ac);

			return u;
		}catch(Exception ex){
			throw ex;
		}
	}

	public UserDao findByUsername(String username){
		return userDao.findByUsername(username);
	}
}