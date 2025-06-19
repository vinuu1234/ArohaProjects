package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.ExtractingJsonDataApplication;
import com.example.demo.dto.PostDto;
import com.example.demo.entities.Post;
import com.example.demo.repository.PostRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private PostRepository postRepository;


	public void fetchAndStorePosts() {
		String url = "https://jsonplaceholder.typicode.com/posts/1/comments";
		
		
		ResponseEntity<PostDto[]> response = restTemplate.getForEntity(url, PostDto[].class);
		PostDto[] posts = response.getBody();
		//System.out.println(Arrays.toString(posts));

		Map<Integer, PostDto> postMap = new HashMap<>();

		// storing in hash map
		for (PostDto post : posts) {
			postMap.put(post.getId(), post);
		}

		// processing hash map

		List<Post> postList = postMap.values().stream().map(c -> new Post(c.getId(), c.getName(), c.getEmail()))
				.collect(Collectors.toList());

		postRepository.saveAll(postList);

	}
}
