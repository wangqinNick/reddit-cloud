package nick.reddit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import masecla.reddit4j.client.Reddit4J;
import masecla.reddit4j.client.UserAgentBuilder;
import masecla.reddit4j.exceptions.AuthenticationException;
import masecla.reddit4j.objects.RedditPost;
import masecla.reddit4j.objects.Sorting;
import nick.reddit.mapper.MemeMapper;
import nick.reddit.pojo.Meme;
import nick.reddit.service.MemeService;
import nick.reddit.vo.R;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinwang
 * @description 针对表【tb_meme】的数据库操作Service实现
 * @createDate 2024-03-16 10:58:19
 */
@Service
public class MemeServiceImpl extends ServiceImpl<MemeMapper, Meme>
        implements MemeService {

    private Reddit4J reddit4J;
    private List<Meme> memeList;

}




