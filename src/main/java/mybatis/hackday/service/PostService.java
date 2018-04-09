package mybatis.hackday.service;

import mybatis.hackday.dto.Post;
import mybatis.hackday.dto.User;
import mybatis.hackday.mapper.PostMapper;
import mybatis.hackday.model.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class PostService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserService userService;

    // 댓글 포함 게시글 조회 (commentId DESC)
    public Post findAllByCategoryIdAndPostNoWithCommentsOrderByCommentIdDesc(int categoryId, int postNo) {
        return postMapper.findAllByCategoryIdAndPostNoWithCommentsOrderByCommentIdDesc(categoryId, postNo);
    }

    public Post findAllByCategoryIdAndPostNoWithCommentsOrderByLikesCountDesc(int categoryId, int postNo) {
        return postMapper.findAllByCategoryIdAndPostNoWithCommentsOrderByLikesCountDesc(categoryId, postNo);
    }

    // 전체 게시글 보기 (board/all)
    public List<Post> findAll() {
        return postMapper.findAll();
    }

    // postNo DESC
    public List<Post> findByCategoryIdOrderByNoDesc(int categoryId) {
        return postMapper.findByCategoryIdOrderByNoDesc(categoryId);
    }

    public Post findByCategoryIdAndNo(int categoryId, int no) {
        return postMapper.findByCategoryIdAndNo(categoryId, no);
    }

    // 마지막 게시글 no 계산하는 메소드
    public Post findTopByCategoryIdOrderByNoDesc(int categoryId) {
        return postMapper.findTopByCategoryIdOrderByNoDesc(categoryId);
    }

    public void updateHit(Post post) {
        postMapper.updateHit(post);
    }

    public void insert(int categoryId, PostModel postModel) {
        Post last = findTopByCategoryIdOrderByNoDesc(categoryId);
        int no = (last == null) ? 1 : last.getNo() + 1;

        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String userId = principal.getName();
        User user = userService.findByUserId(userId);

        postModel.setCategoryId(categoryId);
        postModel.setUserId(user.getId());
        postModel.setNo(no);
        postMapper.insert(postModel);
    }

}
