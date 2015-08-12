package br.org.ovelha.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ContextNotActiveException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;



import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.pagination.PaginationContext;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import br.org.ovelha.domain.EntidadeIf;

public abstract class AbstractDAO<Entidade extends EntidadeIf, I> extends JPACrud<Entidade, I> {

	private static final long serialVersionUID = 1L;

	private Pagination pagination;

	@Transactional
	public void insert(List<Entidade> entidades) {
		for (Entidade entidade : entidades) {
			super.insert(entidade);
		}
	}

	@Transactional
	public void update(List<Entidade> entidades) {
		for (Entidade entidade : entidades) {
			super.update(entidade);
		}
	}

	@Transactional
	public void delete(List<I> idLista) {
		for (I id : idLista) {
			delete(id);
		}
	}

	@Transactional
	public void deleteAll() {
		List<Entidade> entidades = findAll();
		for (Entidade entidade : entidades) {
			getEntityManager().remove(entidade);
		}
	}

	@Transactional
	public Integer deleteBulkAll() {
		return executeUpdate("delete from " + getBeanClass().getName());
	}

	@Transactional
	public void refresh(Entidade entidade) {
		getEntityManager().refresh(entidade);
	}

	@Transactional
	public Entidade updateAndReturn(Entidade entidade) {
		return getEntityManager().merge(entidade);
	}

	/*
	protected Session getSession() {
		if (getEntityManager().getDelegate() instanceof Session) {
			return (Session) getEntityManager().getDelegate();
		}
		return null;
	}*/

	public void clearEntityManager() {
		getEntityManager().clear();
	}

	@Transactional
	public void flushEntityManager() {
		getEntityManager().flush();
	}

	@Override
	public List<Entidade> findAll() {
		return findByJPQL("select this from " + getBeanClass().getName() + " this");
	}

	public Long countAll() {
		return executeSingleResultQuery("select count(this) from " + getBeanClass().getName() + " this", Long.class);
	}

	protected Query createQuery(String jpql, HashMap<String, Object> parametros) {
		Query query = super.createQuery(jpql);
		if (parametros != null && !parametros.isEmpty()) {
			return preencherParametros(query, parametros);
		}
		return query;
	}

	private Query preencherParametros(Query query, Map<String, Object> parametros) {
		for (String key : parametros.keySet()) {
			query.setParameter(key, parametros.get(key));
		}
		return query;
	}

	@SuppressWarnings("unchecked")
	protected List<Entidade> executeQuery(String jpql, HashMap<String, Object> parametros) {
		return createQuery(jpql, parametros).getResultList();
	}

	@SuppressWarnings("unchecked")
	protected List<Entidade> executeQuery(String jpql) {
		return super.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	protected Entidade executeSingleResultQuery(String jpql, HashMap<String, Object> parametros) {
		try {
			return (Entidade) createQuery(jpql, parametros).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	protected Entidade executeSingleResultQuery(String jpql) {
		return executeSingleResultQuery(jpql, new HashMap<String, Object>());
	}

	@SuppressWarnings("unchecked")
	protected <T> TypedQuery<T> createQuery(String jpql, HashMap<String, Object> parametros, Class<T> resultClass) {
		TypedQuery<T> typedQuery = getEntityManager().createQuery(jpql, resultClass);
		if (parametros != null && !parametros.isEmpty()) {
			return (TypedQuery<T>) preencherParametros(typedQuery, parametros);
		}
		return typedQuery;
	}

	private <T> T executeSingleResultQuery(String jpql, HashMap<String, Object> parametros, Class<T> resultClass, boolean forceSingleResult) {
		try {
			TypedQuery<T> createQuery = createQuery(jpql, parametros, resultClass);
			if (forceSingleResult) {
				createQuery.setMaxResults(1);
			}
			return createQuery.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	protected <T> T executeSingleResultQuery(String jpql, HashMap<String, Object> parametros, Class<T> resultClass) {
		return executeSingleResultQuery(jpql, parametros, resultClass, false);
	}

	protected <T> T executeSingleResultQuery(String jpql, Class<T> resultClass) {
		return executeSingleResultQuery(jpql, null, resultClass, false);
	}

	protected <T> T executeForceSingleResultQuery(String jpql, HashMap<String, Object> parametros, Class<T> resultClass) {
		return executeSingleResultQuery(jpql, parametros, resultClass, true);
	}

	protected <T> T executeForceSingleResultQuery(String jpql, Class<T> resultClass) {
		return executeSingleResultQuery(jpql, null, resultClass, true);
	}

	@Transactional
	protected Integer executeUpdate(String jpql, HashMap<String, Object> parametros) {
		return createQuery(jpql, parametros).executeUpdate();
	}

	@Transactional
	protected Integer executeUpdate(String jpql) {
		return createQuery(jpql).executeUpdate();
	}

	protected Pagination getPagination(Class<?> clazz) {
		if (pagination == null) {
			try {
				PaginationContext context = Beans.getReference(PaginationContext.class);
				pagination = context.getPagination(clazz);
			} catch (ContextNotActiveException cause) {
				pagination = null;
			}
		}

		return pagination;
	}

	protected void enabledPagination(Query query, Class<?> typePaginationResultQuery, int totalResults) {
		Pagination pagination = getPagination(typePaginationResultQuery);
		if (pagination != null) {
			pagination.setTotalResults(totalResults);
			query.setFirstResult(pagination.getFirstResult());
			query.setMaxResults(pagination.getPageSize());
		}
	}

	}
