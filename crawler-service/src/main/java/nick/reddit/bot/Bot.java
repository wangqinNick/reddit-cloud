package nick.reddit.bot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import nick.reddit.pojo.Meme;
import nick.reddit.service.MemeService;
import nick.reddit.util.ReportGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private MemeService memeService;

    @Autowired
    private ReportGenerator reportGenerator;

    @Override
    public String getBotUsername() {
        return "reddit_report_bot";
    }

    @Override
    public String getBotToken() {
        return "6839554445:AAGlxOsSSHUmuekKKCNfGWMNxgpB1D55lS0";
    }

    public void sendDocument(Long who) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(who.toString()); // 设置聊天ID

//        List<Meme> memes = memeService.list();
        Page<Meme> paging = new Page<>(1, 20);
        QueryWrapper<Meme> wrapper = Wrappers.query();
        wrapper.orderByDesc("collect_time").orderByDesc("score");

        Page<Meme> result = memeService.page(paging, wrapper);
        File file = reportGenerator.generateReport(result.getRecords());

        sendDocument.setDocument(new InputFile(file)); // 设置要发送的文件，这里需要提供文件路径
//        sendDocument.setDocument(new InputFile(new File("/Users/qinwang/IdeaProjects/reddit-cloud/crawler-service/aaa.txt"))); // 设置要发送的文件，这里需要提供文件路径

        try {
            execute(sendDocument); // 实际发送文件
        } catch (TelegramApiException e) {
            throw new RuntimeException(e); // 发生异常时抛出运行时异常
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        User user = message.getFrom();
        Long id = user.getId();

        System.out.println(user.getFirstName() + " wrote " + message.getText());
        String text = message.getText();
        this.sendDocument(id);
    }
}