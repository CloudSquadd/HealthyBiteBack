package com.esprit.pidev.RestController.ForumController;

import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.ForumRepository.PostRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import com.esprit.pidev.services.ForumServices.IPost;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Column;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequestMapping("/api/test")
@RestController
@AllArgsConstructor
public class PostController {

    IPost iPost;
    UserRepository userRepository;
    PostRepository postRepository;

    @PostMapping("/post")
    public ResponseEntity<Void> addPostAndImage(@RequestParam("title") String title,
                                                @RequestParam("content") String description,
                                                @RequestParam("image") MultipartFile image) throws IOException {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(description);
        post.setImageData(image.getBytes());
        //  post.setImageType(image.getContentType());
        //  post.setImagePath("/images/" + image.getOriginalFilename()); // replace with your desired image path
        postRepository.save(post);
        return ResponseEntity.ok().build();
    }


    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @PostMapping("/posts")
    public ResponseEntity<String> addPost(@RequestBody String content) {

        // Define your custom profanity dictionary
        List<String> profanityWords = Arrays.asList("badword1", "badword2", "badword3");

        // Define the regular expression pattern to match the profanity words
        Pattern profanityPattern = Pattern.compile("\\b(" + String.join("|", profanityWords) + ")\\b", Pattern.CASE_INSENSITIVE);

        // Validate the content against the profanity pattern
        Matcher matcher = profanityPattern.matcher(content);
        if (matcher.find()) {
            // Content contains profanity, return 400 Bad Request response
            return ResponseEntity.badRequest().body("Post contains profanity: " + matcher.group());
        } else {
            // Content is valid, create a new Post object and save it to the database
            Post post = new Post();
            post.setContent(content);
            post.setUser(getCurrentUser());
            postRepository.save(post);

            // Return 201 Created response with the URL of the newly created post
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(post.getId()).toUri();
            return ResponseEntity.created(location).build();
        }

    }







    @PostMapping("/{id}/image")
    public ResponseEntity<Void> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) throws IOException {
        Post post = postRepository.findById(id).orElseThrow(() ->  new NoSuchElementException("Post not found"));

        post.setImageData(image.getBytes());
        post.setImageType(image.getContentType()); // set the image type
        // set the image path based on your requirements
        post.setImagePath("/images/" + UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(image.getOriginalFilename()));
        postRepository.save(post);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/post/{id}/image")
    public ResponseEntity<byte[]> getImageData(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Post post = postRepository.findById(id).orElseThrow(() ->new NoSuchElementException("Post not found"));

        byte[] imageData = post.getImageData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageData.length);
        headers.setContentDisposition(ContentDisposition.attachment().filename("image.jpg").build());

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }



    @PostMapping("/addPost")
    public Post addPost(@RequestBody Post pt) {
        return iPost.addPost(pt);
    }


    @PutMapping("/updatePost/{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post pt){
        return iPost.updatePost(id, pt);
    }

    @GetMapping("/getPostById/{id}")
    public Post retrievePostById(@PathVariable("id") Long id){
        return iPost.retrievePostById(id);
    }

    @GetMapping("/getAllPost")
    public List<Post> retrieveAllPost(){
        return iPost.findAll();
    }

    @GetMapping("/getAllPosts")
    @ResponseBody
    public List<Post> retrieveAllPosts(){
        return iPost.retrieveAllPosts();
    }


    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable("id") Long id){
        iPost.deletePost(id);
    }
}
