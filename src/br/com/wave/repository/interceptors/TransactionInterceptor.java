package br.com.wave.repository.interceptors;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import br.com.wave.repository.annotations.Transactional;
import br.com.wave.repository.enums.InfoEnum;
import br.com.wave.repository.exceptions.RepositoryException;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object intercept(InvocationContext context) throws RepositoryException {
		EntityTransaction transaction = this.manager.getTransaction();

		Object proceed = null;
		try {
			this.logger.info(InfoEnum.BEGIN.getMessage());
			transaction.begin();

			proceed = context.proceed();

			this.logger.info(InfoEnum.COMMIT.getMessage());
			transaction.commit();
		} catch (RepositoryException e) {
			this.logger.info(InfoEnum.ROLLBACK.getMessage());
			transaction.rollback();
			
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			this.logger.info(InfoEnum.ROLLBACK.getMessage());
			transaction.rollback();
			
			e.printStackTrace();
			throw new RepositoryException(e.getMessage());
		}

		return proceed;
	}

}
