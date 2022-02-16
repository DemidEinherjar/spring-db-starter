package com.demidrostovtsev.springdbstarter.repository;

import com.demidrostovtsev.springdbstarter.model.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, String> {

    /* НЕ РАБОТАЕТ! 4) получить всех автолюбителей у которых есть машина */
    List<Driver> findByCarsIsNotNull();

    /* 5) получить всех автолюбителей у которых есть машина белого цвет */
    List<Driver> findByCarsColor(String color);

    /* 6) получить всех автолюбителей у которых нет машины */
    List<Driver> findByCarsIsNull();

    /*7) получить всех автолюбителей у которых номер 700 */
    List<Driver> findByCarsSrpContaining(String pattern);

    /* 8) получить всех автолюбителей старше 30 лет */
    List<Driver> findByBirthdayBefore(Date date);
}
