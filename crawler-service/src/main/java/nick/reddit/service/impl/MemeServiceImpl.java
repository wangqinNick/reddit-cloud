package nick.reddit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nick.reddit.mapper.MemeMapper;
import nick.reddit.pojo.Meme;
import nick.reddit.service.MemeService;
import org.springframework.stereotype.Service;

/**
 * @author qinwang
 * @description 针对表【tb_memes】的数据库操作Service实现
 * @createDate 2024-03-17 12:22:36
 */
@Service
public class MemeServiceImpl extends ServiceImpl<MemeMapper, Meme>
        implements MemeService {
}




