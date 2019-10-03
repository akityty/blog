package konkon.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class BlogForm {
  private Long id;
  private String createDate;
  private String tittle;
  private String category;
  private String content;
  private MultipartFile image;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public String getTittle() {
    return tittle;
  }

  public void setTittle(String tittle) {
    this.tittle = tittle;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }

  public BlogForm(Long id, String createDate, String tittle, String category, String content, MultipartFile image) {
    this.id = id;
    this.createDate = createDate;
    this.tittle = tittle;
    this.category = category;
    this.content = content;
    this.image = image;
  }

  public BlogForm() {
  }
}
