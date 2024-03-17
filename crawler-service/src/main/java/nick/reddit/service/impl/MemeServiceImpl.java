package nick.reddit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nick.reddit.pojo.Meme;
import nick.reddit.service.MemeService;
import nick.reddit.mapper.MemeMapper;
import nick.reddit.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author qinwang
* @description 针对表【tb_memes】的数据库操作Service实现
* @createDate 2024-03-17 12:22:36
*/
@Service
public class MemeServiceImpl extends ServiceImpl<MemeMapper, Meme>
    implements MemeService{
}




