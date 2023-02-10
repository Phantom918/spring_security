package com.phantom.security.service.impl;

import com.phantom.security.repository.UserRoleRepository;
import com.phantom.security.service.UserRoleService;
import com.phantom.security.entity.UserRole;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 用户-角色表(UserRole)表服务实现类
 *
 * @author lei.tan
 * @since 2023-02-09 11:03:42
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleRepository userRoleRepository;

    /**
     * 分页查询
     *
     * @param offset 起始页码
     * @param limit  每页多少条数据
     * @return 分页集合数据
     */
    @Override
    public Page<UserRole> queryAllByLimit(int offset, int limit) {
        return this.userRoleRepository.findAll(PageRequest.of((offset - 1) * limit, limit));
    }

    /**
     * 根据主键查询一条数据
     *
     * @param id 主键id
     * @return 单条数据
     */
    @Override
    public Optional<UserRole> findById(Long id) {
        return userRoleRepository.findById(id);
    }

    /**
     * 根据参数查询多条数据
     *
     * @param userRole 条件参数
     * @return 多条集合数据
     */
    @Override
    public List<UserRole> findByParam(UserRole userRole) {
        return null;
    }

    /**
     * 保存一条数据
     *
     * @param userRole 实体类
     * @return 单条数据
     */
    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    /**
     * 保存多条数据
     *
     * @param userRoles 实体类
     * @return 多条集合数据
     */
    @Override
    public List<UserRole> save(List<UserRole> userRoles) {
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
        this.userRoleRepository.deleteById(id);
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
            this.userRoleRepository.deleteById(id);
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
        return userRoleRepository.existsById(id);
    }

    /**
     * 查询所有数据
     *
     * @return 多条集合数据
     */
    @Override
    public Iterable<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    /**
     * 统计个数
     *
     * @return 条数
     */
    @Override
    public long count() {
        return userRoleRepository.count();
    }

}

