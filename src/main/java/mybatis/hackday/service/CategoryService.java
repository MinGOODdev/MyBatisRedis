package mybatis.hackday.service;

import mybatis.hackday.dto.Category;
import mybatis.hackday.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public Category findById(int categoryId) {
        return categoryMapper.findById(categoryId);
    }

}
