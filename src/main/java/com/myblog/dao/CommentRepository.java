package com.myblog.dao;

import com.myblog.field.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by limi on 2017/10/22.
 */
public interface CommentRepository extends PagingAndSortingRepository<Comment,Long> {


    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
