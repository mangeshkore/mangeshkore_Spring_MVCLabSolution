package in.college.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.college.entities.Book;

@Service
public class BookServiceImpl implements BookService {

	private SessionFactory sessionFactory;

	private Session session;

	@Autowired
	public BookServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		List<Book> books = session.createQuery("from Book").list();
		tx.commit();
		return books;
	}

	@Override
	@Transactional
	public Book findById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Book book = session.get(Book.class, id);
		tx.commit();
		return book;
	}

	@Override
	public void save(Book book) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(book);
		tx.commit();

	}

	@Transactional
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Book book = session.get(Book.class, id);
		if (book != null)
			session.delete(book);
		tx.commit();

	}

	@Transactional
	@Override
	public List<Book> searchBy(String name, String author) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		String query = "";
		if (name.length() != 0 && author.length() != 0) {
			query = "from Book where name like '%" + name + "%' or author like '%" + author + "%'";
		} else if (name.length() != 0) {
			query = "from Book where name like '%" + name + "%'";
		} else if (author.length() != 0) {
			query = "from Book where author like '%" + author + "%'";
		}

		List<Book> books = session.createQuery(query).list();
		tx.commit();
		return books;
	}

}
