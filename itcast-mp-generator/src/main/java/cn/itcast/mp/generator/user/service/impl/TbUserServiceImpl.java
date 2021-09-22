package cn.itcast.mp.generator.user.service.impl;

import cn.itcast.mp.generator.user.entity.TbUser;
import cn.itcast.mp.generator.user.mapper.TbUserMapper;
import cn.itcast.mp.generator.user.service.ITbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itcast
 * @since 2021-09-22
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

}
