package mybatis.hackday.service;

import mybatis.hackday.dto.Post;
import mybatis.hackday.mapper.PostMapper;
import mybatis.hackday.model.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PostMapper postMapper;

    public List<Post> findAll() {
        return postMapper.findAll();
    }

    public List<Post> findByCategoryId(int categoryId) {
        return postMapper.findByCategoryId(categoryId);
    }

    public Post findByCategoryIdAndNo(int categoryId, int no) {
        return postMapper.findByCategoryIdAndNo(categoryId, no);
    }

    public Post findTopByCategoryIdOrderByNoDesc(int categoryId) {
        return postMapper.findTopByCategoryIdOrderByNoDesc(categoryId);
    }

    public void updateHit(Post post) {
        postMapper.updateHit(post);
    }

    public void insert(int categoryId, int userId, PostModel postModel) {
        Post last = findTopByCategoryIdOrderByNoDesc(categoryId);
        int no = (last == null) ? 1 : last.getNo() + 1;

        postModel.setCategoryId(categoryId);
        postModel.setUserId(userId);
        postModel.setNo(no);
        postMapper.insert(postModel);
    }

}
