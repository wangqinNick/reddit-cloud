package nick.reddit;

import nick.reddit.pojo.Meme;
import nick.reddit.service.MemeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private MemeService memeService;

    @Test
    public void listTest() {
        List<Meme> list = memeService.list();
        System.out.println(list);
    }

}
