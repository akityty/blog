package konkon.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blogs")

@NamedStoredProcedureQuery(
        name = "addBlog",
        procedureName = "sp_addBlog",
        parameters = {
                @StoredProcedureParameter(name = "inputCreateDate", mode = ParameterMode.IN, type = java.sql.Timestamp.class),
                @StoredProcedureParameter(name = "inputTittle", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "inputCategory", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "inputContent", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "inputImage", mode = ParameterMode.IN, type = String.class)})
@NamedStoredProcedureQuery(
        name = "deleteBlog",
        resultClasses = {Blog.class},
        procedureName = "sp_deleteBlog",
        parameters = {
                @StoredProcedureParameter(name = "inputId", mode = ParameterMode.IN, type = Long.class)})

@NamedStoredProcedureQuery(
        name = "findByName",
        resultClasses = {Blog.class},
        procedureName = "sp_findByNameBlog",
        parameters = {
                @StoredProcedureParameter(name = "inputName", mode = ParameterMode.IN, type = String.class)})

@NamedStoredProcedureQuery(
        name = "findById",
        resultClasses = {Blog.class},
        procedureName = "sp_findByIdBlog",
        parameters = {
                @StoredProcedureParameter(name = "inputId", mode = ParameterMode.IN, type = Long.class)})

@NamedStoredProcedureQuery(
        name = "findAllBlog",
        resultClasses = {Blog.class},
        procedureName = "sp_findAllBlog")
@NamedStoredProcedureQuery(
        name = "updateBlog",
        procedureName = "sp_updateBlog",
        parameters = {
                @StoredProcedureParameter(name = "inputId", mode = ParameterMode.IN, type = Long.class),
                @StoredProcedureParameter(name = "inputCreateDate", mode = ParameterMode.IN, type = java.sql.Timestamp.class),
                @StoredProcedureParameter(name = "inputTittle", mode = ParameterMode.IN,type = String.class),
                @StoredProcedureParameter(name = "inputCategory", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "inputContent", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "inputImage", mode = ParameterMode.IN, type = String.class)
        }
)
public class Blog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private Date createDate;
  private String tittle;
  private String category;
  private String content;
  private String image;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Blog(Date createDate, String tittle, String category, String content, String image) {
    this.createDate = createDate;
    this.tittle = tittle;
    this.category = category;
    this.content = content;
    this.image = image;
  }

  public Blog() {
  }
}
