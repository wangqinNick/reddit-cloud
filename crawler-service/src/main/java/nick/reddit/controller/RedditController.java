package nick.reddit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import masecla.reddit4j.client.Reddit4J;
import masecla.reddit4j.client.UserAgentBuilder;
import masecla.reddit4j.exceptions.AuthenticationException;
import masecla.reddit4j.objects.RedditPost;
import masecla.reddit4j.objects.Sorting;
import nick.reddit.pojo.Meme;
import nick.reddit.service.MemeService;
import nick.reddit.util.DateTimeUtil;
import nick.reddit.vo.PageResult;
import nick.reddit.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/meme")
public class RedditController {

    @Autowired
    private MemeService memeService;

    /**
     * 1. crawls https://www.reddit.com/r/memes/ and returns top 20 voted posts for the past 24 hours. Sorted by top voted post first, descending order.
     * 2. Stores the crawled data into a database for historical tracking and future data visualization.
     */
    @GetMapping("/top")
    public R getTopMemes() {
        boolean isSuccessful;
        try {
            String id = "JpZnY7pW8YpEF5vofvHn2w";
            String secret = "pO0uSKxmvBstbaHIN5BpG-iJcPGP9g";

            Reddit4J reddit4J = Reddit4J.rateLimited()
                    .setClientId(id).setClientSecret(secret)
                    .setUserAgent(new UserAgentBuilder().appname("reddit-crawler").author("nick").version("1.0"));
            reddit4J.userlessConnect();

            List<Meme> memeList = new ArrayList<>();
            // 获取帖子数据
            List<RedditPost> posts = reddit4J.getSubredditPosts("memes", Sorting.TOP).submit();
            long twentyFourHoursAgo = Instant.now().minusSeconds(24 * 60 * 60).getEpochSecond();
            int count = 0;

            for (RedditPost post : posts) {
                if (post.getCreatedUtc() >= twentyFourHoursAgo) {
                    memeList.add(
                            Meme.builder()
                                    .title(post.getTitle())
                                    .score(post.getScore())
                                    .url(post.getUrl())
                                    .author(post.getAuthor())
                                    .comments(post.getNumComments())
                                    .created_utc((Long) post.getCreatedUtc())
                                    .permalink(post.getPermalink())
                                    .collect_time((DateTimeUtil.getCurDate()))
                                    .build()
                    );
                    count++;
                    if (count >= 20) {
                        break;
                    }
                }
            }
            isSuccessful = memeService.saveBatch(memeList);

        } catch (IOException | InterruptedException | AuthenticationException e) {
            throw new RuntimeException(e);
        }
        if (isSuccessful) return R.ok("Crawl succeed!");
        else return R.error("Crawl failed");
    }

    @GetMapping("/list")
    public PageResult listTopMemes(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Page<Meme> paging = new Page<>(page, size);
        QueryWrapper<Meme> wrapper = Wrappers.query();
        wrapper.orderByDesc("collect_time").orderByDesc("score");

        Page<Meme> result = memeService.page(paging, wrapper);
        return new PageResult(result.getTotal(), result.getRecords());
    }
}
