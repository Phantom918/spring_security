package com.phantom.security.service;

import com.phantom.security.entity.UserRole;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
/**
 * 用户-角色表(UserRole)表服务接口
 *
 * @author lei.tan
 * @since 2023-02-09 10:21:35
 */
public interface UserRoleService {
    
    
    /**
     * 分页查询
     *
     * @param offset 起始页码
     * @param limit 每页多少条数据
     * @return 分页集合数据
     */
    Page<UserRole> queryAllByLimit(int offset, int limit);
    
    /**
     * 根据主键查询一条数据
     *
     * @param id 主键id
     * @return 单条数据
     */
    Optional<UserRole> findById(Long id);
    
    /**
     * 根据参数查询多条数据
     *
     * @param userRole 条件参数
     * @return 集合数据
     */
    List<UserRole> findByParam(UserRole userRole);
    
    /**
    * 保存一条数据
    *
    * @param userRole 实体类
    * @return 单条数据
    */
    UserRole save(UserRole userRole);
    
    /**
    * 保存多条数据 
    *
    * @param userRoles 实体类
    * @return 多条数据
    */
    List<UserRole> save(List<UserRole> userRoles);  
  
    /**
    * 删除一条数据
    *
    * @param id 主键id
    * @return 
    */
    void deleteById(Long id);
    
    /**
    * 删除多条数据
    *
    * @param ids 多条主键
    * @return 
    */
    void deleteByIds(List<Long> ids);
    
    
  /**
    * 根据id查询数据是否存在
    *
    * @param id 主键Id
    * @return 布尔值
    */
    boolean existsById(Long id);

    /**
    * 查询所有数据
    *
    * @return 集合数据
    *
    */
    Iterable<UserRole> findAll();

    /**
    * 统计个数
    *
    * @return 条数
    */
    long count();
}

