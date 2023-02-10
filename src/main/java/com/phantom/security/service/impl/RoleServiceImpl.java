package com.phantom.security.service.impl;

import com.phantom.security.repository.RoleRepository;
import com.phantom.security.service.RoleService;
import com.phantom.security.entity.Role;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 角色(Role)表服务实现类
 *
 * @author lei.tan
 * @since 2023-02-09 11:03:42
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleRepository roleRepository;

    /**
     * 分页查询
     *
     * @param offset 起始页码
     * @param limit  每页多少条数据
     * @return 分页集合数据
     */
    @Override
    public Page<Role> queryAllByLimit(int offset, int limit) {
        return this.roleRepository.findAll(PageRequest.of((offset - 1) * limit, limit));
    }

    /**
     * 根据主键查询一条数据
     *
     * @param id 主键id
     * @return 单条数据
     */
    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    /**
     * 根据参数查询多条数据
     *
     * @param role 条件参数
     * @return 多条集合数据
     */
    @Override
    public List<Role> findByParam(Role role) {
        return null;
    }

    /**
     * 保存一条数据
     *
     * @param role 实体类
     * @return 单条数据
     */
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    /**
     * 保存多条数据
     *
     * @param roles 实体类
     * @return 多条集合数据
     */
    @Override
    public List<Role> save(List<Role> roles) {
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
        this.roleRepository.deleteById(id);
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
            this.roleRepository.deleteById(id);
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
        return roleRepository.existsById(id);
    }

    /**
     * 查询所有数据
     *
     * @return 多条集合数据
     */
    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    /**
     * 统计个数
     *
     * @return 条数
     */
    @Override
    public long count() {
        return roleRepository.count();
    }

}

