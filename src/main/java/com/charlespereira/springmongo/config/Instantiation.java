package com.charlespereira.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.charlespereira.springmongo.domain.Post;
import com.charlespereira.springmongo.domain.User;
import com.charlespereira.springmongo.domain.repository.PostRepository;
import com.charlespereira.springmongo.domain.repository.UserRepository;
import com.charlespereira.springmongo.dto.AuthorDTO;
import com.charlespereira.springmongo.dto.CommentDTO;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GTM"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Lucia", "maria@gmail.com");
		User sarah = new User(null, "Sarah Braga", "sarah@gmail.com");
		User charles = new User(null, "Charles P", "charlespereira@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, sarah, charles));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Let's go", "I'm going to Budapeste", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Morning!", "I'm so happy!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Have a nice trip", sdf.parse("21/03/2018"), new AuthorDTO(sarah));
		CommentDTO c2 = new CommentDTO("Enjoy it!", sdf.parse("22/03/2018"), new AuthorDTO(charles));
		CommentDTO c3 = new CommentDTO("Have a nice day!", sdf.parse("23/03/2018"), new AuthorDTO(sarah));

		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
		
		
	}

}
