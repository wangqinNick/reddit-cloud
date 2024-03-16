package nick.reddit.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName tb_meme
 */
@TableName(value = "tb_meme")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meme implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "title")
    private String title;

    /**
     *
     */
    @TableField(value = "score")
    private Integer score;

    /**
     *
     */
    @TableField(value = "url")
    private String url;

    /**
     *
     */
    @TableField(value = "created_utc")
    private Long created_utc;

    /**
     *
     */
    @TableField(value = "comments")
    private Integer comments;

    /**
     *
     */
    @TableField(value = "author")
    private String author;

    /**
     *
     */
    @TableField(value = "permalink")
    private String permalink;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Meme other = (Meme) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
                && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
                && (this.getCreated_utc() == null ? other.getCreated_utc() == null : this.getCreated_utc().equals(other.getCreated_utc()))
                && (this.getComments() == null ? other.getComments() == null : this.getComments().equals(other.getComments()))
                && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
                && (this.getPermalink() == null ? other.getPermalink() == null : this.getPermalink().equals(other.getPermalink()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getCreated_utc() == null) ? 0 : getCreated_utc().hashCode());
        result = prime * result + ((getComments() == null) ? 0 : getComments().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getPermalink() == null) ? 0 : getPermalink().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", score=").append(score);
        sb.append(", url=").append(url);
        sb.append(", created_utc=").append(created_utc);
        sb.append(", comments=").append(comments);
        sb.append(", author=").append(author);
        sb.append(", permalink=").append(permalink);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}