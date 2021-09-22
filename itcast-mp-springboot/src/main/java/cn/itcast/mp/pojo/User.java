package cn.itcast.mp.pojo;

import cn.itcast.mp.enums.SexEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
//@KeySequence(value = "SEQ_USER",clazz = Long.class)
public class User extends Model<User> {
    @TableId(type = IdType.AUTO)//指定id类型为自增长
    private Long id;
    private String userName;

    // select = false：查询时不返回该字段的值
    // fill = FieldFill.INSERT：插入数据时进行填充
    @TableField(select = false, fill = FieldFill.INSERT)
    private String password;
    private String name;
    private Integer age;

    @TableField(value = "email")//解决字段名不一致
    private String mail;

    @TableField(exist = false)
    private String address;//该字段在数据库表中不存在

    @Version //乐观锁的版本字段
    private Integer version;

    @TableLogic // 逻辑删除字段 ，1-删除，0-未删除
    private Integer deleted;

    private SexEnum sex; //性别，枚举类型
}