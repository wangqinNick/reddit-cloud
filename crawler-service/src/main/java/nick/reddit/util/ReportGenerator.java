package nick.reddit.util;

import nick.reddit.pojo.Meme;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class ReportGenerator {
    public File generateReport(List<Meme> memes) {
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("Top 20 Trending Memes Report\n\n");

        for (Meme meme : memes) {
            reportContent.append("Title: ").append(meme.getTitle()).append("\n");
            reportContent.append("Score: ").append(meme.getScore()).append("\n");
            reportContent.append("URL: ").append(meme.getUrl()).append("\n");
            reportContent.append("Created UTC: ").append(meme.getCreated_utc()).append("\n");
            reportContent.append("Comments: ").append(meme.getComments()).append("\n");
            reportContent.append("Author: ").append(meme.getAuthor()).append("\n");
            reportContent.append("Permalink: ").append(meme.getPermalink()).append("\n\n");
        }

        File reportFile = new File("/Users/qinwang/IdeaProjects/reddit-cloud/crawler-service/report.txt");
        try (FileWriter writer = new FileWriter(reportFile)) {
            writer.write(reportContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reportFile;
    }
}
