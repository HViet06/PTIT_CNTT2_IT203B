package SS3;

import java.util.*;
import java.util.stream.*;

class Post {
    private String title;
    private List<String> tags;
    public Post(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }
    public List<String> getTags(){
        return tags;
    }
}

public class ex6 {
    public static void main(String[] args) {
        List<Post> posts = List.of(
                new Post("Java", List.of("java", "backend")),
                new Post("Python", List.of("python", "data")));
        List<String> allTags = posts.stream().flatMap(post -> post.getTags().stream()).collect(Collectors.toList());
        System.out.println(allTags);
    }
}
