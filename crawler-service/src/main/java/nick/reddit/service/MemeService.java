package nick.reddit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nick.reddit.pojo.Meme;
import nick.reddit.vo.R;

import java.util.List;

/**
 * @author qinwang
 * @description 针对表【tb_meme】的数据库操作Service
 * @createDate 2024-03-16 10:58:19
 */
public interface MemeService extends IService<Meme> {

    /**
     * 1. crawls https://www.reddit.com/r/memes/ and returns top 20 voted posts for the past 24 hours. Sorted by top voted post first, descending order.
     * 2. Stores the crawled data into a database for historical tracking and future data visualization.
     *
     * @return true for successful operations
     */
    boolean getTopMemes();

    R<List<Meme>> listTopMemes();
}
