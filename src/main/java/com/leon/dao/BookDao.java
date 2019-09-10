package com.leon.dao;

import com.leon.domain.Book;

import java.util.List;

/***
 *
 * @Author:Leon
 * @Description:itheima
 * @date: 2019/4/7 10:04
 *
 ****/
public interface BookDao {
    List<Book> queryBookList() throws Exception;
}
