package com.phantom.security.service.impl;

import com.phantom.security.repository.UserRepository;
import com.phantom.security.service.UserService;
import com.phantom.security.entity.User;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
  * 用户表(User)表服务实现类
  *
  * @author lei.tan
  * @since  2023-02-09 11:03:42
  */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    /**
      * 分页查询
      *
      * @param offset 起始页码
      * @param limit 每页多少条数据
      * @return 分页集合数据
      */
    @Override
    public Page<User> queryAllByLimit(int offset, int limit) {
        return this.userRepository.findAll(PageRequest.of((offset-1)*limit,limit));
    }

    /**
      * 根据主键查询一条数据
      *
      * @param id 主键id
      * @return 单条数据
      */
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
      * 根据参数查询多条数据
      *
      * @param user 条件参数
      * @return 多条集合数据
      */
    @Override
    public List<User> findByParam(User user) {
        return null;
    }

    /**
      * 保存一条数据
      *
      * @param user 实体类
      * @return 单条数据
      */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
      * 保存多条数据
      *
      * @param users 实体类
      * @return 多条集合数据
      */
    @Override
    public List<User> save(List<User> users) {
        return null;
    }


    /**
      * 通过主键删除数据
      *
      * @param id 主键
      * @return 
      */
    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    /**
      * 删除多条数据
      *
      * @param ids 多条主键
      * @return 
      */
    @Override
    public void deleteByIds(List<Long> ids) {
        ids.forEach(id -> {
            this.userRepository.deleteById(id);
        });
    }


    /**
      * 根据id查询数据是否存在
      *
      * @param id 主键Id
      * @return 布尔值
      */
    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    /**
      * 查询所有数据
      *
      * @return 多条集合数据
      */
    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    /**
      * 统计个数
      *
      * @return 条数
      */
    @Override
    public long count() {
        return userRepository.count();
    }
    
}

