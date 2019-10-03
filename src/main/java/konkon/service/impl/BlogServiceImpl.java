package konkon.service.impl;

import konkon.model.Blog;
import konkon.model.BlogForm;
import konkon.repository.BlogRepository;
import konkon.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
@PropertySource("classpath:global_config_app.properties")
public class BlogServiceImpl implements BlogService {
  @Autowired
  Environment env;
  @Autowired
  private BlogRepository blogRepository;
  @Override
  public Blog findById(Long id) {
    return blogRepository.findById(id);
  }

  @Override
  public List<Blog> findByName(String name) {
    return blogRepository.findByName(name);
  }

  @Override
  public List<Blog> findAll() {
    return blogRepository.findAll();
  }

  @Override
  public void update(Blog blog) {
    blogRepository.update(blog);
  }

  @Override
  public void add(Blog blog) {
    blogRepository.add(blog);
  }

  @Override
  public void delete(Long id) {
    blogRepository.delete(id);
  }
  public String uploadFile(BlogForm blogForm, BindingResult result) {
    // thong bao neu xay ra loi
    if (result.hasErrors()) {
      System.out.println("Result Error Occured" + result.getAllErrors());
    }

    // lay ten file
    MultipartFile multipartFile = blogForm.getImage();
    String fileName = multipartFile.getOriginalFilename();
    String fileUpload = env.getProperty("file_upload");

    // luu file len server
    try {
      //multipartFile.transferTo(imageFile);

      FileCopyUtils.copy(blogForm.getImage().getBytes(), new File(fileUpload + fileName));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return fileName;
  }
}
