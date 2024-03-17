package nick.reddit;

import nick.reddit.pojo.Meme;
import nick.reddit.service.MemeService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
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

    @Test
    public void timeTest() {
        DateTime now = DateTime.now(DateTimeZone.forID("Asia/Shanghai"));
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化日期时间对象为字符串
        String formattedDateTime = formatter.print(now);
        // 将字符串转换为 Java 的 java.util.Date 对象
        java.util.Date utilDate = formatter.parseDateTime(formattedDateTime).toDate();

        // 创建 MySQL 中的 DATETIME 对象
        Timestamp mysqlDateTime = new Timestamp(utilDate.getTime());
        System.out.println(mysqlDateTime);
    }

}
