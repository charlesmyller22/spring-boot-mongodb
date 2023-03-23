package com.charlespereira.springmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.charlespereira.springmongo.domain.Post;
import com.charlespereira.springmongo.resources.util.URL;
import com.charlespereira.springmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value ="text", defaultValue = "") String text) {
		
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
	
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<List<Post>> search(
			@RequestParam(value ="text", defaultValue = "") String text,
			@RequestParam(value ="text", defaultValue = "") String minDate,
			@RequestParam(value ="text", defaultValue = "") String maxDate) {
		
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());

		List<Post> list = service.search(text, min, max);
	
		return ResponseEntity.ok().body(list);
	}
}
