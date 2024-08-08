package com.example;

import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TransactionService {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public long callUnpaidTransactionsFunction() {
        Session session = entityManager.unwrap(Session.class);
        Transaction tx = session.beginTransaction();
        Query<Long> query = session.createNativeQuery("select count(*) FROM public.get_unpaid_transactions(:param1, :param2, :param3, :param4, :param5, :param6, :param7, :param8, :param9)")
                .unwrap(Query.class);
        var res = query.setParameter("param1", "unlocked")
                .setParameter("param2", "locked")
                .setParameter("param3", 10)
                .setParameter("param4", List.of("win").toArray(new String[0]), StringArrayType.INSTANCE)
                .setParameter("param5", List.of("cash").toArray(new String[0]), StringArrayType.INSTANCE)
                .setParameter("param6", List.of("created").toArray(new String[0]), StringArrayType.INSTANCE)
                .setParameter("param7", "testInstance1534")
                .setParameter("param8", null)
                .setParameter("param9", null)
                .getSingleResult();
        session.flush();
        tx.commit();
        return res;

    }
}
