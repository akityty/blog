package konkon.service;

import konkon.model.Blog;
import konkon.model.BlogForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface BlogService extends GeneralService<Blog> {
  String uploadFile(@ModelAttribute BlogForm blogForm, BindingResult result);
}
