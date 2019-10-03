package konkon.controller;

import konkon.model.Blog;
import konkon.model.BlogForm;
import konkon.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
  @Autowired
  private BlogService blogService;

  @GetMapping("/create")
  public ModelAndView showCreateForm(){
    ModelAndView modelAndView = new ModelAndView("/blog/create");
    modelAndView.addObject("blog", new BlogForm());
    return modelAndView;
  }
  @PostMapping("/create")
  public ModelAndView createProduct(@ModelAttribute BlogForm blogForm, BindingResult result) {
    String fileName = blogService.uploadFile(blogForm, result);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
    /*    id,createDate,tittle,category,content,image*/
    try {
      Date lDate = simpleDateFormat.parse(blogForm.getCreateDate());
      Blog blog = new Blog(lDate, blogForm.getTittle(), blogForm.getCategory(), blogForm.getContent(), fileName);
      blogService.add(blog);
      ModelAndView modelAndView = new ModelAndView("redirect:/blog/list");
      modelAndView.addObject("blog", blog);
      return modelAndView;
    } catch (ParseException e) {
      e.printStackTrace();
    }
    ModelAndView modelAndView = new ModelAndView("/blog/error");
    return modelAndView;
  }

  @GetMapping("/list")
  public ModelAndView showProducts() {
    List<Blog> blogList = blogService.findAll();
    ModelAndView modelAndView = new ModelAndView("/blog/list");
    modelAndView.addObject("blogs", blogList);
    return modelAndView;
  }

  @GetMapping("/edit")
  public ModelAndView showEditForm(@ModelAttribute Blog blog) {
    ModelAndView modelAndView = new ModelAndView("/blog/edit");
    Blog blogObject = blogService.findById(blog.getId());
    modelAndView.addObject("blog", blogObject);
    return modelAndView;
  }

  @PostMapping("/edit")
  public ModelAndView editProduct(@ModelAttribute BlogForm blogForm, BindingResult result) throws ParseException {
    String fileName = blogService.uploadFile(blogForm, result);
    Blog blogObject = blogService.findById(blogForm.getId());
    if (!fileName.equals("")) {
      blogObject.setImage(fileName);
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
    Date lDate = simpleDateFormat.parse(blogForm.getCreateDate());
    blogObject.setCreateDate(lDate);
    blogObject.setTittle(blogForm.getTittle());
    blogObject.setCategory(blogForm.getCategory());
    blogObject.setContent(blogForm.getContent());
    blogService.update(blogObject);
    ModelAndView modelAndView = new ModelAndView("redirect:/blog/list");
    modelAndView.addObject("blog", blogObject);
    return modelAndView;
  }

  @PostMapping("/search")
  public ModelAndView search(@RequestParam String name) {
    List<Blog> foundBlogList = blogService.findByName(name);
    if (foundBlogList != null) {
      ModelAndView modelAndView = new ModelAndView(("/blog/search"));
      modelAndView.addObject("blogs", foundBlogList);
      return modelAndView;
    } else {
      ModelAndView modelAndView = new ModelAndView("redirect:/blog/list");
      return modelAndView;
    }
  }
  @GetMapping("/view")
  public ModelAndView showViewForm(@ModelAttribute Blog blog){
    ModelAndView modelAndView = new ModelAndView("/blog/view");
    Blog blogObject = blogService.findById(blog.getId());
    modelAndView.addObject("blog", blogObject);
    return modelAndView;
  }

  @GetMapping("/delete")
  public ModelAndView showDeleteForm(@ModelAttribute Blog blog){
    ModelAndView modelAndView = new ModelAndView("/blog/delete");
    Blog productObject = blogService.findById(blog.getId());
    modelAndView.addObject("blog", blogService);
    return modelAndView;
  }

  @PostMapping("/delete")
  public ModelAndView deleteProduct(@ModelAttribute BlogForm blogForm){
    Blog blogObject = blogService.findById(blogForm.getId());
    blogService.delete(blogObject.getId());
    ModelAndView modelAndView = new ModelAndView("redirect:/blog/list");
    modelAndView.addObject("blog", blogObject);
    return modelAndView;
  }
}

