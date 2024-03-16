package nick.reddit.controller;

import nick.reddit.pojo.Meme;
import nick.reddit.service.MemeService;
import nick.reddit.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        boolean isSuccessful = memeService.getTopMemes();
        if (isSuccessful) return R.ok("Crawl succeed!");
        else return R.error("Crawl failed");
    }

    @GetMapping("/list")
    public R<List<Meme>> listTopMemes() {
        System.out.println("hits");
        return memeService.listTopMemes();
    }
}
