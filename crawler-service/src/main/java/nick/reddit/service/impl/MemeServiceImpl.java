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

    @PostConstruct
    public void init() {

        try {
            String id = "JpZnY7pW8YpEF5vofvHn2w";
            String secret = "pO0uSKxmvBstbaHIN5BpG-iJcPGP9g";

            this.reddit4J = Reddit4J.rateLimited()
                    .setClientId(id).setClientSecret(secret)
                    .setUserAgent(new UserAgentBuilder().appname("reddit-crawler").author("nick").version("1.0"));
            reddit4J.userlessConnect();
            this.memeList = new ArrayList<>();
            // 获取帖子数据
            List<RedditPost> posts = this.reddit4J.getSubredditPosts("memes", Sorting.TOP).submit();

            long twentyFourHoursAgo = Instant.now().minusSeconds(24 * 60 * 60).getEpochSecond();
            int count = 0;

            for (RedditPost post : posts) {
                if (post.getCreatedUtc() >= twentyFourHoursAgo) {
                    this.memeList.add(
                            Meme.builder()
                                    .title(post.getTitle())
                                    .score(post.getScore())
                                    .url(post.getUrl())
                                    .author(post.getAuthor())
                                    .comments(post.getNumComments())
                                    .created_utc((Long)post.getCreatedUtc())
                                    .permalink(post.getPermalink())
                                    .build()
                    );
                    count++;

                    if (count >= 20) {
                        break;
                    }
                }
            }
        } catch (IOException | InterruptedException | AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 1. crawls https://www.reddit.com/r/memes/ and returns top 20 voted posts for the past 24 hours. Sorted by top voted post first, descending order.
     * 2. Stores the crawled data into a database for historical tracking and future data visualization.
     *
     * @return true for successful operations
     */
    @Override
    public boolean getTopMemes() {
        return saveBatch(this.memeList);
    }

    @Override
    public R<List<Meme>> listTopMemes() {
        List<Meme> list = list();
        return R.ok(list);
    }
}




