package com.yangzhuo.service;

import com.yangzhuo.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//添加事务
public interface BookService {
    //提供业务层接口

    /**
     * 保存
     * @param book
     * @return
     */
    public boolean save(Book book);

    /**
     * 修改
     * @param book
     * @return
     */
    public boolean update(Book book);

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 按照id查询
     * @param id
     * @return
     */
    public Book getById(Integer id);

    /**
     * 查询所有
     * @return
     */
    public List<Book> getAll();
}
