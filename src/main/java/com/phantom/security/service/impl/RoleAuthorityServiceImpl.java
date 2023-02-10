package com.phantom.security.service.impl;

import com.phantom.security.repository.RoleAuthorityRepository;
import com.phantom.security.service.RoleAuthorityService;
import com.phantom.security.entity.RoleAuthority;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
  * 角色-权限表(RoleAuthority)表服务实现类
  *
  * @author lei.tan
  * @since  2023-02-09 11:03:42
  */
@Service("roleAuthorityService")
public class RoleAuthorityServiceImpl implements RoleAuthorityService {
    @Resource
    private RoleAuthorityRepository roleAuthorityRepository;

    /**
      * 分页查询
      *
      * @param offset 起始页码
      * @param limit 每页多少条数据
      * @return 分页集合数据
      */
    @Override
    public Page<RoleAuthority> queryAllByLimit(int offset, int limit) {
        return this.roleAuthorityRepository.findAll(PageRequest.of((offset-1)*limit,limit));
    }

    /**
      * 根据主键查询一条数据
      *
      * @param id 主键id
      * @return 单条数据
      */
    @Override
    public Optional<RoleAuthority> findById(Long id) {
        return roleAuthorityRepository.findById(id);
    }

    /**
      * 根据参数查询多条数据
      *
      * @param roleAuthority 条件参数
      * @return 多条集合数据
      */
    @Override
    public List<RoleAuthority> findByParam(RoleAuthority roleAuthority) {
        return null;
    }

    /**
      * 保存一条数据
      *
      * @param roleAuthority 实体类
      * @return 单条数据
      */
    @Override
    public RoleAuthority save(RoleAuthority roleAuthority) {
        return roleAuthorityRepository.save(roleAuthority);
    }

    /**
      * 保存多条数据
      *
      * @param roleAuthoritys 实体类
      * @return 多条集合数据
      */
    @Override
    public List<RoleAuthority> save(List<RoleAuthority> roleAuthoritys) {
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
        this.roleAuthorityRepository.deleteById(id);
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
            this.roleAuthorityRepository.deleteById(id);
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
        return roleAuthorityRepository.existsById(id);
    }

    /**
      * 查询所有数据
      *
      * @return 多条集合数据
      */
    @Override
    public Iterable<RoleAuthority> findAll() {
        return roleAuthorityRepository.findAll();
    }

    /**
      * 统计个数
      *
      * @return 条数
      */
    @Override
    public long count() {
        return roleAuthorityRepository.count();
    }
    
}

