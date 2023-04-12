package com.esprit.pidev.services.ForumServices;

import com.esprit.pidev.entities.Forum.LikeEntity;
import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.repository.ForumRepository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class LikeService implements ILike{
    @Autowired
    private LikeRepository likeRepository;

    public LikeEntity addLike(LikeEntity like) {
        return likeRepository.save(like);
    }

    public void removeLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    public List<LikeEntity> getAllLikesForPost(Post pt) {
        return likeRepository.findByPost(pt);
    }

  /* public List<Like> getAllLikesByUser(User user) {
        return likeRepository.findByUser(user);
   }*/

   /*public Like getLikeByPostAndUser(Post post, User user) {
       return likeRepository.findByPostAndUser(post, user);
   }*/
}
