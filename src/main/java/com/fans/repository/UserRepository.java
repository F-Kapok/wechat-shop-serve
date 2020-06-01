package com.fans.repository;

import com.fans.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interfaceName: UserRepository
 *
 * @author k
 * @version 1.0
 * @description 用户数据操作层
 * @date 2020-05-31 23:48
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByOpenid(String openId);

}
