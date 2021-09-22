package cn.itcast.mp.mapper;

import cn.itcast.mp.pojo.User;

//public interface UserMapper extends BaseMapper<User> {
public interface UserMapper extends MyBaseMapper<User> {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findById(Long id);
}