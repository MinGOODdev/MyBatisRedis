package mybatis.hackday.service;

import mybatis.hackday.dto.Comment;
import mybatis.hackday.dto.Likes;
import mybatis.hackday.dto.Post;
import mybatis.hackday.dto.User;
import mybatis.hackday.mapper.PostMapper;
import mybatis.hackday.model.DefaultResponse;
import mybatis.hackday.model.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class PostService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikesService likesService;

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

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteByCategoryIdAndNo(int categoryId, int no) throws IllegalAccessException {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String userId = principal.getName();
        User user = userService.findByUserId(userId);

        if(user.getId() == findByCategoryIdAndNo(categoryId, no).getUserId()) {
            List<Comment> comments = commentService.findByCategoryIdAndPostNo(categoryId, no);

            for(Comment c : comments) {
                List<Likes> likes = likesService.findByCategoryIdAndPostNoAndCommentId(categoryId, no, c.getId());

                for(Likes like : likes)
                    likesService.delete(like.getId());

                commentService.delete(c.getId());
            }
            postMapper.deleteByCategoryIdAndNo(categoryId, no);
        }
        else {
            throw new IllegalAccessException("게시글을 작성한 사용자가 아닙니다.");
        }
    }

}
