package cn.itcast.mp;

import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setUserName("liubei");
        user.setName("刘备");
        user.setAge(21);
        user.setMail("liubei@123.com");
        user.setPassword("999999");
        int result = this.userMapper.insert(user);//result数据库受影响的行数
        System.out.println("result => " + result);

        //获取自增长后的id值, 自增长后的id值会回填到user对象中
        System.out.println("id => " + user.getId());
    }

    @Test
    public void testInsert_1() {
        User user = new User();
        user.setUserName("diaochan");
        user.setName("貂蝉");
        user.setAge(21);
        user.setMail("diaochan@itcast.com");
        user.setVersion(1);
        //user.setPassword("123456");//有密码使用此密码填充，没有密码就自动填充
        int result = this.userMapper.insert(user);//result数据库受影响的行数
        System.out.println("result => " + result);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(7L); //条件，根据id更新
        user.setAge(22);//更新的字段
        int result = this.userMapper.updateById(user);//根据id更新，更新不为null的字段
        System.out.println("result => " + result);
    }


    @Test
    public void testUpdate() {
        User user = new User();
        user.setAge(21);//更新的字段
        user.setPassword("888888");

        //更新的条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //wrapper.eq("id", "6");
        wrapper.eq("user_name", "caocao"); //匹配user_name = zhangsan 的用户数据

        //根据条件做更新
        int result = this.userMapper.update(user, wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void testUpdate2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age", "21").set("password", "999999")//更新的字段
                .eq("user_name", "liubei");//更新的条件

        //根据条件做更新
        int result = this.userMapper.update(null, wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void testDeleteById() {
        // 根据id删除数据
        int result = this.userMapper.deleteById(2L);
        System.out.println("result => " + result);
    }

    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "liubei");
        map.put("password", "999999");

        // 将map中的元素设置为删除的条件，多条件之间是and关系
        int result = this.userMapper.deleteByMap(map);
        System.out.println("result => " + result);
    }

    @Test
    public void testDelete() {
        //用法一
        //QueryWrapper<User> wrapper = new QueryWrapper<>();
        //wrapper.eq("user_name", "liubei")
        //      .eq("password", "999999");

        //用法二
        User user = new User();
        user.setPassword("999999");
        user.setUserName("liubei");

        //将实体对象进行包装，包装为操作条件
        QueryWrapper<User> wrapper = new QueryWrapper<User>(user);

        // 根据包装条件做删除
        int result = this.userMapper.delete(wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void testDeleteBatchIds() {
        // 根据id批量删除数据
        int result = this.userMapper.deleteBatchIds(Arrays.asList(8L, 9L));
        System.out.println("result => " + result);
    }

    @Test
    public void testSelectById() {
        //根据id查询数据
        User user = this.userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds() {
        // 根据id批量查询数据
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(2L, 3L, 4L, 5L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("password", "888888");
        // 根据条件查询一条数据，查询的数据超过一条时，会抛出异常
        User user = this.userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20); // 条件：年龄大于20岁的用户

        // 根据条件查询数据条数
        Integer count = this.userMapper.selectCount(wrapper);
        System.out.println("count => " + count);
    }

    @Test
    public void testSelectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        //wrapper.gt("age", 20); // 条件：年龄大于20岁的用户
        wrapper.like("email", "itcast");

        //根据条件查询数据
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    // 测试分页查询
    @Test
    public void testSelectPage() {

        Page<User> page = new Page<>(1, 2); //查询第一页，查询2条数据

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        //wrapper.gt("age", 20); //年龄大于20岁
        wrapper.like("email", "itcast");

        //根据条件查询数据
        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数： " + iPage.getTotal());
        System.out.println("数据总页数： " + iPage.getPages());
        System.out.println("当前页数： " + iPage.getCurrent());

        List<User> records = iPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }

    @Test
    public void testFindById() {
        User user = userMapper.findById(2L);
        System.out.println(user);
    }

    @Test
    public void testAllEq() {
        Map<String, Object> params = new HashMap<>();
        //设置条件
        params.put("name", "李四");
        params.put("age", "20");
        params.put("password", null);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,userName,name,age,email AS mail FROM tb_user WHERE password IS NULL AND name = ? AND age = ?
        //wrapper.allEq(params);

        //SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE name = ? AND age = ?
        //wrapper.allEq(params,false);

        //SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE age = ?
        //wrapper.allEq((k, v) -> (k.equals("age") || k.equals("id")), params);

        //SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE name = ? AND age = ?
        wrapper.allEq((k, v) -> (k.equals("age") || k.equals("id") || k.equals("name")), params);

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println();
        }
    }

    @Test
    public void testEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE password = ? AND age >= ? AND name IN (?,?,?)
        wrapper.eq("password", "123456")
                .ge("age", 20)
                .in("name", "李四", "王五", "赵六");

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLike() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE name LIKE ?
        // 参数：%五(String)
        wrapper.likeLeft("name", "五");

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOrderByAgeDesc() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //按照年龄倒序排序
        // SELECT id,user_name,name,age,email AS mail FROM tb_user ORDER BY age DESC
        wrapper.orderByDesc("age");

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOr() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE name = ? OR age = ?
        wrapper.eq("name", "王五").or().eq("age", 21);

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelect() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,name,age FROM tb_user WHERE name = ? OR age = ?
        wrapper.eq("name", "王五")
                .or()
                .eq("age", 21)
                .select("id", "name", "age"); //指定查询的字段

        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void testFindAll() {
        List<User> users = this.userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}