package com.in28Min.restwebservice.restfullwebservice.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<UserJPA> users = new ArrayList<UserJPA>();

	private static AtomicInteger counter = new AtomicInteger(3);
	static {
		users.add(new UserJPA(1, "kotb", LocalDate.now().minusYears(30)));
		users.add(new UserJPA(2, "kotb2", LocalDate.now().minusYears(33)));
		users.add(new UserJPA(3, "kotb3", LocalDate.now().minusYears(37)));
	}

	public List<UserJPA> findAll() {
		return users;
	}

	public UserJPA save(UserJPA user) {
		user.setId(counter.addAndGet(1));
		users.add(user);
		return user;
	}

	public UserJPA findOne(int id) {
		Predicate<? super UserJPA> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public void deleteById(int id) {
		Predicate<? super UserJPA> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
}
