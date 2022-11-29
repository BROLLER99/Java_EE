package example;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.RollbackException;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class JavaBean {
    @PersistenceContext(unitName = "myUnit")
    EntityManager entityManager;

        @Resource
        private UserTransaction transaction;
    public void saveUser(UserEntity user) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        try {
            transaction.begin();
        entityManager.persist(user);
            transaction.commit();
        }
        catch (RollbackException e) {
            transaction.rollback();
        }
    }
    public UserEntity findUser(int id) throws Exception {
        if (entityManager.find(UserEntity.class, id) == null) {
            throw new Exception("Not Found");
        }
        return entityManager.find(UserEntity.class, id);
    }
}

