package konkon.repository.impl;

import konkon.model.Blog;
import konkon.repository.BlogRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Transactional
public class BlogRepositoryImpl implements BlogRepository {
  @PersistenceContext
  private EntityManager em;
  @Override
  public Blog findById(Long id) {
    StoredProcedureQuery findByIdQuery = em.createNamedStoredProcedureQuery("findById");
    findByIdQuery.setParameter("inputId", id);
    findByIdQuery.execute();
    Blog blog = (Blog) findByIdQuery.getSingleResult();

    return blog;
  }

  @Override
  public List<Blog> findByName(String name) {
    StoredProcedureQuery findByNameQuery = em.createNamedStoredProcedureQuery("findByName");
    findByNameQuery.setParameter("inputName",'%'+name+'%');
    findByNameQuery.execute();
    List<Blog> blogs = findByNameQuery.getResultList();
    return blogs;
  }

  @Override
  public List<Blog> findAll() {
    StoredProcedureQuery findAllBlogQuery = em.createNamedStoredProcedureQuery("findAllBlog");
    List<Blog> blogs = findAllBlogQuery.getResultList();
    return blogs;
  }

  @Override
  public void update(Blog blog) {
    SimpleDateFormat sdf =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String createDate = sdf.format(blog.getCreateDate());
    StoredProcedureQuery spUppdateBlog = em.createNamedStoredProcedureQuery("updateBlog");
    spUppdateBlog.setParameter("inputId", blog.getId());
    spUppdateBlog.setParameter("inputCreateDate", Timestamp.valueOf(createDate));
    spUppdateBlog.setParameter("inputTittle", blog.getTittle());
    spUppdateBlog.setParameter("inputCategory", blog.getCategory());
    spUppdateBlog.setParameter("inputImage", blog.getImage());
    spUppdateBlog.setParameter("inputContent", blog.getContent());

    spUppdateBlog.execute();
  }

  @Override
  public void add(Blog blog) {
    SimpleDateFormat sdf =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String createDate = sdf.format(blog.getCreateDate());

    StoredProcedureQuery addBlog = em.createNamedStoredProcedureQuery("addBlog");
    addBlog.setParameter("inputCreateDate", Timestamp.valueOf(createDate));
    addBlog.setParameter("inputTittle", blog.getTittle());
    addBlog.setParameter("inputCategory", blog.getCategory());
    addBlog.setParameter("inputImage", blog.getImage());
    addBlog.setParameter("inputContent", blog.getContent());

    addBlog.execute();
  }

  @Override
  public void delete(Long id) {
    StoredProcedureQuery deleteBlogQuery = em.createNamedStoredProcedureQuery("deleteBlog");
    deleteBlogQuery.setParameter("inputId",id);
    deleteBlogQuery.execute();
  }
}
