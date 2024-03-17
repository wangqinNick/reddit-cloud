package nick.reddit.vo;

import lombok.Data;
import nick.reddit.pojo.Meme;

import java.util.List;

@Data
public class PageResult {
    private Long total;
    private List<Meme> memes;

    public PageResult() {
    }

    public PageResult(Long total, List<Meme> memes) {
        this.total = total;
        this.memes = memes;
    }
}