# Reddit Cloud

Reddit Cloud is a web service designed to crawl the subreddit r/memes on Reddit, retrieve the top 20 voted posts for the past 24 hours, store the crawled data into a database, and generate a report file of the top 20 trending memes. Additionally, it provides the functionality to send the generated report file via a Telegram Chatbot.

## Features

1. **Crawling Top 20 Voted Posts**: The web service crawls the subreddit r/memes on Reddit and retrieves the top 20 voted posts for the past 24 hours. The posts are sorted by the top-voted post first in descending order.

2. **Storing Data in Database**: The crawled data, including details such as post title, score, URL, author, and comments, is stored in a database for historical tracking and future data visualization. This allows users to analyze trends and patterns over time.

3. **Generating Report File**: Reddit Cloud generates a report file containing the top 20 trending memes for the past 24 hours. The report file provides insights into the most popular memes on Reddit within a specific timeframe.

4. **Telegram Chatbot Integration**: Users can interact with a Telegram Chatbot to receive the generated report file directly in their chat. This feature enhances accessibility and convenience for users who prefer to receive updates via messaging platforms.

## Setup Instructions

To set up Reddit Cloud on your local machine, follow these steps:

1. **Clone the repository**: git clone https://github.com/yourusername/reddit-cloud.git
2. **Set up environment variables**:
   server:
   port: 8080
   spring:
   datasource:
   username: root
   password: root
   url: jdbc:mysql://localhost:3306/reddit_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
   driver-class-name: com.mysql.cj.jdbc.Driver

3. **Set up a database**:
   CREATE TABLE tb_memes
   (
   id          INT AUTO_INCREMENT PRIMARY KEY,
   title       VARCHAR(255) NOT NULL,
   score       INT          NOT NULL,
   url         VARCHAR(255) NOT NULL,
   created_utc BIGINT       NOT NULL,
   comments    INT          NOT NULL,
   author      VARCHAR(100) NOT NULL,
   permalink   VARCHAR(255) NOT NULL,
   cur_time    DATETIME     NOT NULL
   );
4. **Run the application**: CrawlerApplication
5. **Access the web service**: Open your web browser and navigate to `http://localhost:8080` to access the Reddit Cloud web service.
6. **Interact with the Telegram Chatbot**: Search for the Telegram Chatbot using the username provided during setup and start interacting to receive the report file.


