package com.wilhelmsen.orm.controllers;

import com.wilhelmsen.orm.BadHibernateHelper;
import com.wilhelmsen.orm.HibernateHelper;
import com.wilhelmsen.orm.entity.Account;
import com.wilhelmsen.orm.entity.GoodAccount;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping("/add")
    public String add() {
        return addElement();
    }

    @GetMapping("/badAdd")
    public String badAdd() {
        return addBadElement();
    }

    @GetMapping("/editMultiple")
    public String editMultiple() {

        List<Thread> threads = new ArrayList<>();
        List<String> statusList = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            TransactionRunnable t = new TransactionRunnable(statusList, i, 11);
            threads.add(new Thread(t));
            t.run();
        }
        for (Thread t :
                threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : statusList) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    @GetMapping("/richestBitches")
    public String richGuys() {
        StringBuilder sb = new StringBuilder();
        for(String s : getRich()) {
            sb.append("\n");
            sb.append(s);
        }
        return sb.toString();
    }

    @GetMapping("/editSameMultipleTimes")
    public String EditSameMultipleTimes() {
        List<Thread> threads = new ArrayList<>();
        List<String> statusList = new ArrayList<>();

        threads.add(new Thread(new MoveMoolahRunnable(
                statusList, 1, 2, 3, 400, false)));
        threads.add(new Thread(new MoveMoolahRunnable(
                statusList, 3, 2, 3, 200, false)));
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : statusList) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    @GetMapping("/newname")
    public void newName() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("from  konto where id= :id");
        q.setParameter("id", 4);
        GoodAccount accFrom = (GoodAccount) q.uniqueResult();
        accFrom.setOwner(String.valueOf(Math.random()));
        transaction.commit();
    }

    @GetMapping("/badEditSameMultipleTimes")
    public String badEditSameMultipleTimes() {
        List<Thread> threads = new ArrayList<>();
        List<String> statusList = new ArrayList<>();

        threads.add(new Thread(new MoveMoolahRunnable(
                statusList, 1, 2, 3, 400, true)));
        threads.add(new Thread(new MoveMoolahRunnable(
                statusList, 3, 2, 3, 200, true)));
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : statusList) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    private List<String> getRich() {
        Session s = HibernateHelper.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Query query = s.createQuery("select owner from konto where balance > :balance");
        query.setParameter("balance", 50.0);
        return (List<String>) query.list();
    }

    public String addElement() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        GoodAccount entity = new GoodAccount();
        entity.setBalance(0);
        entity.setOwner(String.valueOf(Math.random()));
        session.save(entity);
        transaction.commit();
        return transaction.getStatus().toString();
    }

    public String addBadElement() {
        Session session = BadHibernateHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Account entity = new Account();
        entity.setBalance(0);
        entity.setOwner(String.valueOf(Math.random()));
        session.save(entity);
        transaction.commit();
        return transaction.getStatus().toString();
    }

    public String addToBalance(int accountId, double toAdd) {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("from badkonto where id= :id");
        q.setParameter("id", accountId);
        GoodAccount entity = (GoodAccount) q.uniqueResult();
        entity.setBalance(entity.getBalance() + toAdd);
        session.save(entity);
        transaction.commit();
        return transaction.getStatus().toString();
    }

    public String moveMoolah(int accountFrom, int accountTo, double amount, int sleepAmount) {
        String accountTableName = "konto";
        System.out.println("Move moolah started " + sleepAmount);
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("from " + accountTableName + " where id= :id");
        q.setParameter("id", accountFrom);
        GoodAccount accFrom = (GoodAccount) q.uniqueResult();
        accFrom.pull(amount);

        try {
            Thread.sleep(sleepAmount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String status = transaction.getStatus().toString();

        Transaction transaction2 = session.beginTransaction();
        q = session.createQuery("from " + accountTableName + " where id= :id");
        q.setParameter("id", accountTo);
        GoodAccount accTo = (GoodAccount) q.uniqueResult();
        accTo.add(amount);

        try {
            Thread.sleep(sleepAmount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            transaction.commit();
            transaction2.commit();
        } catch (StaleStateException e) {
            System.out.println("Version different. Unable to commit. Rolling back");
            transaction.rollback();
            transaction2.rollback();
        }

        session.clear();
        session.close();
        System.out.println("Move moolah ended " + sleepAmount);
        return status + "\n" + transaction2.getStatus().toString();
    }

    public String badMoveMoolah(int accountFrom, int accountTo, double amount, int sleepAmount) {
        String accountTableName = "badkonto";
        System.out.println("Move moolah started " + sleepAmount);
        Session session = BadHibernateHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("from " + accountTableName + " where id= :id");
        q.setParameter("id", accountFrom);
        Account accFrom = (Account) q.uniqueResult();
        accFrom.pull(amount);

        try {
            Thread.sleep(sleepAmount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        session.save(accFrom);
        transaction.commit();
        session.clear();
        session.close();

        String status = transaction.getStatus().toString();

        session = BadHibernateHelper.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        q = session.createQuery("from " + accountTableName + " where id= :id");
        q.setParameter("id", accountTo);
        Account accTo = (Account) q.uniqueResult();
        accTo.add(amount);

        try {
            Thread.sleep(sleepAmount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        session.save(accTo);
        transaction.commit();
        System.out.println("Move moolah ended " + sleepAmount);
        session.close();
        return status + "\n" + transaction.getStatus().toString();
    }

    private class TransactionRunnable implements Runnable {
        private List<String> statusList;
        private int accountId;
        private double toAdd;

        public TransactionRunnable(List<String> statusList, int accountId, double toAdd) {
            this.statusList = statusList;
            this.accountId = accountId;
            this.toAdd = toAdd;
        }

        @Override
        public void run() {
            statusList.add(addToBalance(accountId, toAdd));
        }
    }

    private class MoveMoolahRunnable implements Runnable {
        private List<String> statusList;
        private int accountFrom;
        private int accountTo;
        private double amount;
        private int sleepAmount;
        private boolean bad;

        public MoveMoolahRunnable(List<String> statusList, int accountFrom, int accountTo, double amount, int sleepAmount, boolean bad) {
            this.statusList = statusList;
            this.accountFrom = accountFrom;
            this.accountTo = accountTo;
            this.amount = amount;
            this.sleepAmount = sleepAmount;
            this.bad = bad;
        }

        @Override
        public void run() {
            if(bad) {
                statusList.add(badMoveMoolah(accountFrom, accountTo, amount, sleepAmount));
            } else {
                statusList.add(moveMoolah(accountFrom, accountTo, amount, sleepAmount));
            }
        }
    }

}
