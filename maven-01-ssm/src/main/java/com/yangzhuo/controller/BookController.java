package com.yangzhuo.controller;


import com.yangzhuo.domain.Book;
import com.yangzhuo.exception.BusinessException;
import com.yangzhuo.exception.SystemException;
import com.yangzhuo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//控制器类
@RequestMapping("/books")//配置映射
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Result save(@RequestBody Book book) {
        boolean flag=bookService.save(book);
        return new Result(flag? Code.SAVE_OK: Code.SAVE_ERR,flag);
    }

    @PutMapping
    public Result update(@RequestBody Book book) {
        boolean flag=bookService.update(book);
        return new Result(flag? Code.UPDATE_OK: Code.UPDATE_ERR,flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag=bookService.delete(id);
        return new Result(flag? Code.DELETE_OK: Code.DELETE_ERR,flag);
    }


    @GetMapping({"/{id}"})
    public Result getById(@PathVariable Integer id) {
        if(id==1){
            System.out.println("测试");
            throw new BusinessException(Code.BUSINESS_ERR,"您的输入有误!");
        }
        //将可能出现的异常进行包装，转换成自定义异常
        try{
            int i=1/0;
        }catch (ArithmeticException e){
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR,"服务器访问超时，请重试!",e);
        }
        Book book = bookService.getById(id);
        Integer code=book!=null?Code.GET_OK:Code.GET_ERR;
        String msg=book!=null?"":"数据查询失败,请重试!";
        return new Result(code,book,msg);
    }

    @GetMapping
    public Result getAll() {
        List<Book> all = bookService.getAll();
        Integer code=all!=null?Code.GET_OK:Code.GET_ERR;
        String msg=all!=null?"":"数据查询失败,请重试!";
        return new Result(code,all,msg);
    }
}
