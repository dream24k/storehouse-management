package com.lx.mapper;

import com.lx.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lixiang
 * @since 2023-04-20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
